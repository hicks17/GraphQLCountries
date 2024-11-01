package js.apps.graphqlexample

import com.apollographql.apollo.ApolloClient

class CountriesClient {

    val apolloClient = ApolloClient.Builder()
        .serverUrl("https://countries.trevorblades.com/graphql")
        .build()
/*
    // Execute your query. This will suspend until the response is received.
    val response = apolloClient.query(Countries(id = "1")).execute()

    println("Hero.name=${response.data?.hero?.name}")*/
}