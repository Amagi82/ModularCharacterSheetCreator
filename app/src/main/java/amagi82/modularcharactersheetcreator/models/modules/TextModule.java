package amagi82.modularcharactersheetcreator.models.modules;


import java.io.Serializable;

/*
    Module for plain text. Useful for notes
 */

public class TextModule extends Module implements Serializable{

    String text;

    public TextModule() {
        super(ViewType.TEXT);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
