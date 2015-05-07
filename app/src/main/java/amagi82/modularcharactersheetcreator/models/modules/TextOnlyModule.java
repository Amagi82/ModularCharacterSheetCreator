package amagi82.modularcharactersheetcreator.models.modules;


import java.io.Serializable;

public class TextOnlyModule extends Module implements Serializable{

    String text;

    public TextOnlyModule() {
        super(ViewType.TEXTONLY);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
