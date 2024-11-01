package js.apps.graphqlexample.domain

import js.apps.graphqlexample.data.DetailedCountry
import js.apps.graphqlexample.data.SimpleCountry

interface CountryClient {

    suspend fun getCountries(): List<SimpleCountry>

    suspend fun getCountry(code: String): DetailedCountry?

}