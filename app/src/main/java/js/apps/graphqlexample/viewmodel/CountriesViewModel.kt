package js.apps.graphqlexample.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import js.apps.graphqlexample.data.DetailedCountry
import js.apps.graphqlexample.data.SimpleCountry
import js.apps.graphqlexample.domain.CountryClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val countryClient: CountryClient
) : ViewModel() {


    private val _countryState = MutableStateFlow(CountryState())
    val countryState = _countryState.asStateFlow()

    private val _selectedCountry = mutableStateListOf<DetailedCountry>()
    val selectedCountry: List<DetailedCountry>
        get() = _selectedCountry

    init {
        viewModelScope.launch {
            _countryState.update {
                it.copy(
                    isLoading = true
                )
            }
            _countryState.update { state ->
                state.copy(
                    countries = countryClient
                        .getCountries()
                        .sortedBy { it.name },
                    isLoading = false
                )
            }
        }
    }
    fun selectCountry(code: String) {
        viewModelScope.launch {
            _countryState.update {
                it.copy(
                    selectedCountry = countryClient.getCountry(code)
                )
            }
        }
    }

    fun deselectCountry() {
        _countryState.update {
            it.copy(
                selectedCountry = null
            )
        }
    }

    data class CountryState(
        val countries: List<SimpleCountry> = emptyList(),
        val isLoading: Boolean = false,
        val selectedCountry: DetailedCountry? = null
    )


}