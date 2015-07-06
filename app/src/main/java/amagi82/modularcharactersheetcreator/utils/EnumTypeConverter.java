package amagi82.modularcharactersheetcreator.utils;

import com.bluelinelabs.logansquare.typeconverters.StringBasedTypeConverter;

import amagi82.modularcharactersheetcreator.models.modules.Module;

public class EnumTypeConverter extends StringBasedTypeConverter<Module.Type> {

    @Override
    public Module.Type getFromString(String s) {
        return Module.Type.valueOf(s);
    }

    public String convertToString(Module.Type object) {
        return object.toString();
    }
}
