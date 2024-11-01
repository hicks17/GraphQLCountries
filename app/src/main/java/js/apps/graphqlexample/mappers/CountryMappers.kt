package js.apps.graphqlexample.mappers

import js.apps.CountriesQuery
import js.apps.CountryQuery
import js.apps.graphqlexample.data.DetailedCountry
import js.apps.graphqlexample.data.SimpleCountry

fun CountryQuery.Country.toDetailedCountry(): DetailedCountry {
    return DetailedCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No capital",
        currency = currency ?: "No currency",
        continent = continent.name,
        languages = languages.map { it.name }
    )

}

fun CountriesQuery.Country.toSimpleCountry(): SimpleCountry {
    return SimpleCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No capital"
    )

}