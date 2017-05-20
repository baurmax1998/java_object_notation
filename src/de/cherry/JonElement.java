package de.cherry;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by mbaaxur on 19.05.17.
 * Time 3:20 h
 */
public class JonElement implements JON {
    private JonElement parent;
    private Method executeMethod;
    private Method setMethod;
    private Object value;
    private HashMap<String, JonElement> childreen = new HashMap<String, JonElement>(1);

    private JonElement(JonElement parent) {
        this.parent = parent;
    }

    JonElement() {
    }

    public Set<String> keys(){
        return childreen.keySet();
    }

    public JonElement get(String key){
        JonElement jonElement = childreen.get(key);
        if (jonElement == null){
            jonElement = new JonElement(this);
            childreen.put(key, jonElement);
        }
        return jonElement;
    }

    public Object get(){
        if(executeMethod != null)
            return execute(executeMethod, null);
        return value;
    }

    public Object get(Object... param){
        if(executeMethod != null)
            return execute(executeMethod, param);
        return value;
    }

    public void set(Object o){
        if(this.setMethod != null){
            try {
                execute(setMethod,o);
            } catch (IllegalArgumentException e){
               //todo einschätzen ob man überschreiben will oder nicht (aktuell überschreiben)
                setMethod = null;
                executeMethod = null;
                value = o;
            }
            return;
        }

        if (this.value != null){
            removeValueChilds();
        }
        this.value = o;
        Class<?> aClass = o.getClass();
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            declaredMethod.setAccessible(true);
            //todo herrausfinden welche funktionen public sind und somit auch abgebildet werden dürfen
           if (declaredMethod.isAccessible()) {
               String methodName = declaredMethod.getName();
               String realName = getRealName(methodName);
               JonElement child = getChiled(realName);
               String kuerzel = methodName.replaceAll(realName, "");
               if(Kuerzel.isReturning(kuerzel)){
                   child.setExecuteMethod(declaredMethod);
               } else {
                   child.setSetMethod(declaredMethod);
               }
           }

        }


    }

    private Object execute(Method method ,Object... param) throws IllegalArgumentException{
        JonElement parent = this.parent;
        if (parent == null)
            throw new IllegalStateException("call method from a deleted Objekt");
        Object o = parent.value;
        if (o == null)
            throw new IllegalStateException("call method from a missing Objekt");
        try {
             return method.invoke(o, param);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("normaly the access is checked by the set Method");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e);
        }
        throw new RuntimeException("Something go wrong while executing: " + method.getName());
    }


    private void removeValueChilds() {
        childreen.clear();
    }

    private JonElement getChiled(String realName) {
        JonElement child = childreen.get(realName);
        if(child == null){
            child = new JonElement(this);
            this.childreen.put(realName, child);
        }
        return child;
    }

    private String getRealName(String methodName) {
        return methodName.replaceAll("is","").replaceAll("get", "").replaceAll("set","");
    }

    /**
     *
     * @param executeMethod
     */
    private void setExecuteMethod(Method executeMethod) {
        this.executeMethod = executeMethod;
    }

    /**
     *
     * @param setMethod
     */
    private void setSetMethod(Method setMethod) {
        this.setMethod = setMethod;
    }
}
