package squarerock.bites.network

class WikiApiService(private val wikiApi: WikiApi) {
    suspend fun getTitles(limit: Int) = wikiApi.getRandomArticles(limit = limit)

    suspend fun getExtracts(title: String) = wikiApi.getArticleExtracts(title = title)
}