package squarerock.bites.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import squarerock.bites.Constants
import squarerock.bites.R
import squarerock.bites.models.SmmryModel
import squarerock.bites.models.WikiModelExtract
import squarerock.bites.models.WikiModelRandom
import squarerock.bites.network.SmmryApiService
import squarerock.bites.network.WikiApiService

class HomeFragment : Fragment() {

    private val TAG = "HomeFragment"
    private lateinit var homeViewModel: HomeViewModel

    private val wikiApiService by lazy {
        WikiApiService.create()
    }

    private val smmryApiService by lazy {
        SmmryApiService.create()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        val btnRandom: Button = root.findViewById(R.id.btn_get_random)
        btnRandom.setOnClickListener {
            fetchRandom()
        }
        return root
    }

    private fun fetchRandom(limit: Int = 5) {
        wikiApiService.getRandom(
            limit = limit
        ).enqueue(object: Callback<WikiModelRandom.Result>{
            override fun onFailure(call: Call<WikiModelRandom.Result>, t: Throwable) {
                Log.e(TAG, "onFailure: ", t.cause)
            }

            override fun onResponse(
                call: Call<WikiModelRandom.Result>,
                response: Response<WikiModelRandom.Result>
            ) {
                val title =  response.body()?.query?.random?.get(0)?.title
                getExtract(title)
                Log.d(TAG, "onResponse: ${response.body()?.query?.random?.get(0)?.title}")
            }

        })
    }

    private fun getExtract(title: String?){
        if (title == null) {
            return
        }
        wikiApiService.getExtract(
           title = title
        ).enqueue(object : Callback<WikiModelExtract.Result>{
            override fun onFailure(call: Call<WikiModelExtract.Result>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<WikiModelExtract.Result>,
                response: Response<WikiModelExtract.Result>
            ) {
                val extract = response.body()?.query?.pages?.get(0)?.extract
                homeViewModel.updateText(extract)
            }

        })
    }
}