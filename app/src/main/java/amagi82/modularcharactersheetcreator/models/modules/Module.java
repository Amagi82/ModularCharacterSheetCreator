package amagi82.modularcharactersheetcreator.models.modules;


import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import amagi82.modularcharactersheetcreator.utils.EnumTypeConverter;

@JsonObject
public class Module {

    public enum Type {
        DEFAULT,
        HEADER(new HeaderModule()),
        HEALTH(new HealthModule()),
        STATBLOCK(new StatBlockModule()),
        STATUS(new StatusModule()),
        TITLETEXTBLOCK(new TitleTextBlockModule()),
        BLOODPOOL(new BloodPoolModule());

        Module module;

        Type() {
        }

        Type(Module module) {
            this.module = module;
        }

        public Module getModule() {
            return module;
        }
    }

    @JsonField(typeConverter = EnumTypeConverter.class) private Type type;
    @JsonField private int spanCount;
    @JsonField private String title;
    @JsonField private String text;

    public Module() {
        this(null, null, 1);
    }

    public Module(Type type) {
        this.type = type;
    }

    public Module(String title, String text){
        this(title, text, 1);
    }

    public Module(String title, String text, int spanCount){
        this.type = Type.DEFAULT;
        this.title = title;
        this.text = text;
        this.spanCount = spanCount;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getSpanCount() {
        return spanCount;
    }

    public void setSpanCount(int spanCount) {
        this.spanCount = spanCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
