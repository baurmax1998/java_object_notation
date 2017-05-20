package de.cherry;

/**
 * Created by mbaaxur on 19.05.17.
 */
public enum Kuerzel{
    IS("is", true),
    SET("set", false),
    ADD("add", false),
    GET("get", true);

    Kuerzel(String text, boolean isReturning) {
        this.text = text;
        this.isReturning = isReturning;
    }

    private String text;

    private boolean isReturning;

    public Kuerzel from(String text){
        for (Kuerzel kuerzel : Kuerzel.values()) {
            if(kuerzel.text.equals(text))
                return kuerzel;
        }
        throw new IllegalArgumentException(text + " ist kein Kuerzel!");
    }

    public static boolean isReturning(String text) {
        for (Kuerzel kuerzel : Kuerzel.values()) {
            if(kuerzel.text.equals(text))
                return kuerzel.isReturning;
        }
        return true;
    }
}
