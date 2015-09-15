package amagi82.modularcharactersheetcreator.entities.modules;

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
