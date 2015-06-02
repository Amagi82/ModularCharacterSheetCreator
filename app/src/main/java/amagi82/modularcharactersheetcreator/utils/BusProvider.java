package amagi82.modularcharactersheetcreator.utils;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

@Singleton
public class BusProvider {

    private static Bus bus;

    public static Bus getBus() {
        if (bus == null) bus = new Bus();
        return bus;
    }
}
