package squarerock.bites.models

object WikiModelExtract {
    data class Result(val query: Query)
    data class Query(val pages: List<Pages>)
    data class Pages(val title: String, val extract: String)
}