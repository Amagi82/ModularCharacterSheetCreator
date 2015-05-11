package amagi82.modularcharactersheetcreator.models.modules;


import java.io.Serializable;

/*
    Module with header only
 */

public class HeaderModule extends Module implements Serializable {

    String header;

    public HeaderModule() {
        super(ViewType.HEADER);
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
