package squarerock.bites.data

import squarerock.bites.network.WikiApiService

class WikiRepository(private val wikiApiService: WikiApiService) {

    suspend fun fetchArticles(limit: Int = 1) = wikiApiService.getTitles(limit = limit)

    suspend fun fetchExtracts(titles: List<String>) = wikiApiService.getExtracts(title = titles[0])
}