package amagi82.modularcharactersheetcreator.ui._extras.utils;

import com.squareup.otto.Bus;

public enum Otto {
    BUS;

    private final Bus bus = new Bus();

    public Bus get() {
        return bus;
    }
}