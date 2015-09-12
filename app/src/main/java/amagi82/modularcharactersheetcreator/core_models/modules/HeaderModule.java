package amagi82.modularcharactersheetcreator.core_models.modules;

public class HeaderModule extends Module {

    public HeaderModule() {
        super(Type.HEADER);
    }

    public HeaderModule(String header, int spanCount){
        super(Type.HEADER);
        setTitle(header);
        setSpanCount(spanCount);
    }
}
