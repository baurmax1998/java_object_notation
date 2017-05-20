package de.cherry;

/**
 * Created by mbaaxur on 19.05.17.
 */
public class TestObjekt {
    private boolean halt = true;
    private String text = "hallo";
    private int zahl = 42;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getZahl() {
        return zahl;
    }

    public void setZahl(int zahl) {
        this.zahl = zahl;
    }

    public boolean isHalt() {
        return halt;
    }

    public void setHalt(boolean halt) {
        this.halt = halt;
    }
}
