package js.apps.graphqlexample.data

data class DetailedCountry(
    val code: String,
    val name: String,
    val capital: String,
    val emoji: String,
    val currency: String,
    val continent: String,
    val languages: List<String>
)
