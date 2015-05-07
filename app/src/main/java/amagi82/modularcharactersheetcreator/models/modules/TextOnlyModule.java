package amagi82.modularcharactersheetcreator.models.modules;


public class TextOnlyModule extends Module{

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