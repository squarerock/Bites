package squarerock.bites.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import squarerock.bites.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        homeViewModel.randomArticles.observe(viewLifecycleOwner, randomArticlesObserver)
        homeViewModel.articleExtracts.observe(viewLifecycleOwner, articleExtractsObserver)

        return root
    }

    private val randomArticlesObserver = Observer<List<String>>{
        homeViewModel.fetchArticleExtracts(it)
    }

    private val articleExtractsObserver = Observer<List<String>> {
        Log.d("HomeFragment", it[0])
    }
}