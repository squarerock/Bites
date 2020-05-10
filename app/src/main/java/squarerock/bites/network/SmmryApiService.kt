package squarerock.bites.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import squarerock.bites.models.SmmryModel

interface SmmryApiService {

    @GET(".")
    fun getSummary(
        @Query("SM_API_KEY") apiKey: String,
        @Query("SM_URL") url: String
    ): Call<SmmryModel.Result>

    companion object{
        fun create(): SmmryApiService {
            val baseUrl = "https://api.smmry.com"

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build()

            return retrofit.create(SmmryApiService::class.java)
        }
    }
}