package squarerock.bites.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import squarerock.bites.models.WikiModelExtract
import squarerock.bites.models.WikiModelRandom
import squarerock.bites.network.WikiApiService


class HomeViewModel : ViewModel() {

    private val _randomArticles = MutableLiveData<List<String>>()
    val randomArticles: LiveData<List<String>> = _randomArticles

    private val _articleExtracts = MutableLiveData<List<String>>()
    val articleExtracts: LiveData<List<String>> = _articleExtracts

    private val TAG = "HomeViewModel"

    private val wikiApiService by lazy {
        WikiApiService.create()
    }

    init {
        Log.d(TAG, "fetching random articles")
        fetchRandomArticles()
    }

    private fun fetchRandomArticles(limit: Int = 5) {
        wikiApiService.getRandom(
            limit = limit
        ).enqueue(object: Callback<WikiModelRandom.Result> {
            override fun onFailure(call: Call<WikiModelRandom.Result>, t: Throwable) {
                Log.e(TAG, "onFailure: ", t)
            }

            override fun onResponse(
                call: Call<WikiModelRandom.Result>,
                response: Response<WikiModelRandom.Result>
            ) {
                response.body()?.let { it ->
                    Log.d(TAG, "onResponse: ${it.query.random.size} articles fetched")
                    _randomArticles.value = it.query.random.map { it.title }
                }
            }
        })
    }

    fun fetchArticleExtracts(titles: List<String>){
        if (titles.isEmpty()) {
            return
        }

        val sb = StringBuilder()
        titles.forEach {
            sb.append(it)
            sb.append("|")
        }

        wikiApiService.getExtract(
            title = sb.toString()
        ).enqueue(object : Callback<WikiModelExtract.Result>{
            override fun onFailure(call: Call<WikiModelExtract.Result>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<WikiModelExtract.Result>,
                response: Response<WikiModelExtract.Result>
            ) {
                response.body()?.let {
                    Log.d(TAG, "onResponse: Implement UI update here")
                }
            }

        })
    }
}