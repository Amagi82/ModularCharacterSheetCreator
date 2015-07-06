package amagi82.modularcharactersheetcreator.models.modules;


import amagi82.modularcharactersheetcreator.R;

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

    private Type type;
    private int spanCount;
    private String title;
    private String text;
    private int layoutId;

    public Module() {
        this(Type.DEFAULT);
    }

    public Module(Type type) {
        this(type, R.layout.module_text);
    }

    public Module(Type type, int layoutId) {
        this.type = type;
        this.layoutId = layoutId;
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

    public int getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }
}
