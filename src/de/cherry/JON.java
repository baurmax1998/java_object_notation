package de.cherry;

import java.util.Set;

/**
 * Created by mbaaxur on 19.05.17.
 */
public interface JON {
    //todo ich will das JonElement nur einen Konstruktor hat ohne parameter
    //eigendlich ist es logisch das es noch nicht funktioniert
    //public JonElement JonElement();

    public JonElement get(String key);
    public Object get(Object... param);
    public void set(Object o);
    public Set<String> keys();
}
