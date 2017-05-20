package de.cherry;

/**
 * Created by mbaaxur on 20.05.17.
 */
public class TestMain {
    public static void main(String[] args){
        testDefault();
        testweitererParamAnParamEinesObjekts();
        testUeberschreibenEinesAttributsEinesObjekts();
    }

    private static void testDefault(){
        JON jon = JonFactory.getJON();
        TestObjekt testObjekt = new TestObjekt();
        jon.get("test").set(testObjekt);
        String text = (String) jon.get("test").get("Text").get();
        System.out.println(text);
    }

    private static void testweitererParamAnParamEinesObjekts(){
        JON jon = JonFactory.getJON();
        TestObjekt testObjekt = new TestObjekt();
        jon.get("test").set(testObjekt);
        String text = (String) jon.get("test").get("Text").get();
        jon.get("test").get("Text").get("weiterer").set(1);
        int o = (int) jon.get("test").get("Text").get("weiterer").get();
        System.out.println(o);
    }

    private static void testUeberschreibenEinesAttributsEinesObjekts(){
        JON jon = JonFactory.getJON();
        TestObjekt testObjekt = new TestObjekt();
        jon.get("test").set(testObjekt);
        String text = (String) jon.get("test").get("Text").get();
        jon.get("test").get("Text").set(1);
        int neuerWert = (int) jon.get("test").get("Text").get();
        System.out.println(text + " -> " + neuerWert);
    }
}
