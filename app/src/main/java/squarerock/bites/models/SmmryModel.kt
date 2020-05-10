package squarerock.bites.models

object SmmryModel{

    data class Result(val smmry: Smmry)
    data class Smmry(
        val sm_api_character_count: String,
        val sm_api_content_reduced: String,
        val sm_api_title: String,
        val sm_api_content: String,
        val sm_api_limitation: String,
        val sm_api_error: Int
    )
}
