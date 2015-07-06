package amagi82.modularcharactersheetcreator.models.modules;

import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject
public class HeaderModule extends Module {

    public HeaderModule() {
        super(Type.HEADER);
    }
}
