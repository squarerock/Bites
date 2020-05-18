package squarerock.bites.ui.home

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import squarerock.bites.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var tvTitle: TextView
    private lateinit var tvExtract: TextView
    private lateinit var btnLearnMore: Button
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        swipeRefreshLayout = root.findViewById(R.id.home_container)
        tvTitle = root.findViewById(R.id.tvTitle)
        tvExtract = root.findViewById(R.id.tvExtract)
        btnLearnMore = root.findViewById(R.id.btnLearnMore)

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.randomArticles.observe(viewLifecycleOwner, randomArticlesObserver)
        homeViewModel.articleExtracts.observe(viewLifecycleOwner, articleExtractsObserver)

        swipeRefreshLayout.setOnRefreshListener {
            homeViewModel.fetchRandomArticles()
            swipeRefreshLayout.isRefreshing = false
        }

        btnLearnMore.setOnClickListener {
            val baseUrl = "https://en.wikipedia.org/wiki/"
            val url = baseUrl + tvTitle.text
            launchLearnMore(url)
        }

        return root
    }

    private val randomArticlesObserver = Observer<List<String>>{
        homeViewModel.fetchArticleExtracts(it)
        tvTitle.text = it[0]
    }

    private val articleExtractsObserver = Observer<List<String>> {
        tvExtract.text = it[0]
    }

    fun launchLearnMore(url: String){
        val builder = CustomTabsIntent.Builder()
        builder.setToolbarColor(getColor(requireContext(), R.color.purple500))
        builder.addDefaultShareMenuItem()
        builder.setShowTitle(true)
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(requireContext(), Uri.parse(url))
    }
}