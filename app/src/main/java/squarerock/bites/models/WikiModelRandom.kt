package squarerock.bites.models

object WikiModelRandom {
    data class Result(val query: Query)
    data class Query(val random: List<Random>)
    data class Random(val id: Int, val ns: Int, val title: String)
}