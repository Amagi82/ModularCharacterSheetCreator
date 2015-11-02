package amagi82.modularcharactersheetcreator.ui._extras.utils;

//This class returns a url for an icon at reduced dimensions if necessary
public class SplatIcon {

    private SplatIcon() {
    }

    public static String getUrl(String baseUrl, int size) {
        //The 20th Anniversary images are 500x500px, and all others are 200x200px. Request smaller images if needed.
        boolean big = baseUrl.contains("20th");
        if ((!big && size > 200) || (big && size > 500)) return baseUrl;
        int pos = baseUrl.lastIndexOf("/") + 1;
        return new StringBuilder(baseUrl).insert(pos, ("img.php?h=" + size + "+&w=" + size + "+&img=")).toString();
    }
}