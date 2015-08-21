package amagi82.modularcharactersheetcreator.models.modules;

import com.bluelinelabs.logansquare.annotation.JsonObject;

import org.parceler.Parcel;

@Parcel
@JsonObject
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
