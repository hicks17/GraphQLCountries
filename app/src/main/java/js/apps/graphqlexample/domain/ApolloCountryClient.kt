package js.apps.graphqlexample.domain

import com.apollographql.apollo.ApolloClient
import js.apps.CountriesQuery
import js.apps.CountryQuery
import js.apps.graphqlexample.data.DetailedCountry
import js.apps.graphqlexample.data.SimpleCountry
import js.apps.graphqlexample.mappers.toDetailedCountry
import js.apps.graphqlexample.mappers.toSimpleCountry

class ApolloCountryClient (private val apolloClient: ApolloClient) : CountryClient {
    override suspend fun getCountries(): List<SimpleCountry> {
        return apolloClient.query(CountriesQuery())
            .execute()
            .data
            ?.countries
            ?.map { it.toSimpleCountry() }
            ?: emptyList()
    }

    override suspend fun getCountry(code: String): DetailedCountry? {
        return apolloClient.query(CountryQuery(code)).
            execute().
            data?.
            country?.toDetailedCountry()
    }
}