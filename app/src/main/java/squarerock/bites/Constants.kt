package squarerock.bites

import android.os.Build

object Constants {
    val SMMRY_API_KEY = "36BFBFC398"
    val SMMRY_BASE_URL = "https://api.smmry.com"
    val WIKIPEDIA_BASE_URL = "https://en.wikipedia.org/w/"
    val WIKIPEDIA_ARTICLE_BASE_URL = "https://en.wikipedia.org/wiki/"

}

object BuildConstants {
    val ATLEAST_ORE0 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
}

