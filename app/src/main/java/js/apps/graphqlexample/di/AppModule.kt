package js.apps.graphqlexample.di

import com.apollographql.apollo.ApolloClient
import dagger.Module
import dagger.Provides

import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import js.apps.graphqlexample.domain.ApolloCountryClient
import js.apps.graphqlexample.domain.CountryClient
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/graphql")
            .build()

    }
    @Provides
    @Singleton
    fun provideCountryClient(apolloClient: ApolloClient): CountryClient {
        return ApolloCountryClient(apolloClient)

    }


}