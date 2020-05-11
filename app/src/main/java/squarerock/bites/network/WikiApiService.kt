package squarerock.bites.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import squarerock.bites.models.WikiModelExtract
import squarerock.bites.models.WikiModelRandom

interface WikiApiService {

    @GET("api.php")
    fun getRandom(
        @Query("action") action: String = "query",
        @Query("format") format: String = "json",
        @Query("list") list: String = "random",
        @Query("rnnamespace") rnnamespace: Int = 0,
        @Query("rnlimit") limit: Int
    ): Call<WikiModelRandom.Result>

    @GET("api.php")
    fun getExtract(
        @Query("action") action: String = "query",
        @Query("format") format: String = "json",
        @Query("prop") prop: String = "extracts",
        @Query("formatversion") formatversion: Int = 2,
        @Query("exlimit") exlimit: String = "max",
        @Query("explaintext") explainText: Int = 1,
        @Query("exsentences") exsentences: Int = 10,
        @Query("titles") title: String,
        @Query("redirects") redirects: String = ""
    ): Call<WikiModelExtract.Result>

    companion object {
        fun create(): WikiApiService {
            val baseUrl = "https://en.wikipedia.org/w/"
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build()

            return retrofit.create(WikiApiService::class.java)
        }
    }
}