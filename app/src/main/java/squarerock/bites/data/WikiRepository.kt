package squarerock.bites.data

import squarerock.bites.network.WikiApi

class WikiRepository {

    private val wikiApiService by lazy {
        WikiApi.create()
    }

    suspend fun fetchRandomArticles(limit: Int = 1)
            = wikiApiService.getRandomArticles(limit = limit)

    suspend fun fetchArticleExtracts(titles: List<String>)
        = wikiApiService.getArticleExtracts(title = titles[0])
}