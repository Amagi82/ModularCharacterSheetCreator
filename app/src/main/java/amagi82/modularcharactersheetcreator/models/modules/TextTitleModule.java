package amagi82.modularcharactersheetcreator.models.modules;


import java.io.Serializable;

/*
    Module for plain text along with a title. TODO: allow user to place title above or below text
 */

public class TextTitleModule extends Module implements Serializable {

    String text;
    String title;

    public TextTitleModule() {
        super(ViewType.TEXTTITLE);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
