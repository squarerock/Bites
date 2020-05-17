package squarerock.bites.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import squarerock.bites.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var tvTitle: TextView
    private lateinit var tvExtract: TextView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        tvTitle = root.findViewById(R.id.tvTitle)
        tvExtract = root.findViewById(R.id.tvExtract)

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.randomArticles.observe(viewLifecycleOwner, randomArticlesObserver)
        homeViewModel.articleExtracts.observe(viewLifecycleOwner, articleExtractsObserver)

        return root
    }

    private val randomArticlesObserver = Observer<List<String>>{
        homeViewModel.fetchArticleExtracts(it)
        tvTitle.text = it[0]
    }

    private val articleExtractsObserver = Observer<List<String>> {
        tvExtract.text = it[0]
    }
}