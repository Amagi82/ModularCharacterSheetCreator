package amagi82.modularcharactersheetcreator.utils;

import com.bluelinelabs.logansquare.LoganSquare;
import com.colintmiller.simplenosql.DataDeserializer;
import com.colintmiller.simplenosql.DataSerializer;

import java.io.IOException;

public class Logan implements DataSerializer, DataDeserializer{

    @Override public <T> T deserialize(String data, Class<T> clazz) {
        try {
            return LoganSquare.parse(data, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override public <T> String serialize(T data) {
        try {
            return LoganSquare.serialize(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
