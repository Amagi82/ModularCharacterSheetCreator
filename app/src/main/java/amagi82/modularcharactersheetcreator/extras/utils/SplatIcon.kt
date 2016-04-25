package amagi82.modularcharactersheetcreator.extras.utils

//This class returns a url for an icon at reduced dimensions if necessary
object SplatIcon {

    fun getUrl(baseUrl: String?, size: Int): String? {
        if (baseUrl == null || size == 0) return null
        //The 20th Anniversary images are 500x500px, and all others are 200x200px. Request smaller images if needed.
        val big = baseUrl.contains("20th")
        if (!big && size > 200 || big && size > 500) return baseUrl
        val pos = baseUrl.lastIndexOf("/") + 1
        return StringBuilder(baseUrl).insert(pos, "img.php?h=$size+&w=$size+&img=").toString()
    }
}