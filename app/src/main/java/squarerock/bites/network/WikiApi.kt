package squarerock.bites.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import squarerock.bites.Constants
import squarerock.bites.models.WikiModelExtract
import squarerock.bites.models.WikiModelRandom

interface WikiApi {

    @GET("api.php")
    suspend fun getRandomArticles(
        @Query("action") action: String = "query",
        @Query("format") format: String = "json",
        @Query("list") list: String = "random",
        @Query("rnnamespace") rnnamespace: Int = 0,
        @Query("rnlimit") limit: Int
    ): WikiModelRandom.Result

    @GET("api.php")
    suspend fun getArticleExtracts(
        @Query("action") action: String = "query",
        @Query("format") format: String = "json",
        @Query("prop") prop: String = "extracts",
        @Query("formatversion") formatversion: Int = 2,
        @Query("exlimit") exlimit: String = "max",
        @Query("explaintext") explainText: Int = 1,
        @Query("exsentences") exsentences: Int = 20,
        @Query("titles") title: String,
        @Query("redirects") redirects: Int = 1
    ): WikiModelExtract.Result

    companion object {
        fun create(): WikiApi {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.WIKIPEDIA_BASE_URL)
                .build()

            return retrofit.create(WikiApi::class.java)
        }
    }
}