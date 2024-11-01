package js.apps.graphqlexample.uii

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import js.apps.graphqlexample.data.SimpleCountry
import js.apps.graphqlexample.viewmodel.CountriesViewModel




    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CountryScreen(
        state: CountriesViewModel.CountryState,
        onSelectCountry: (code: String) -> Unit,
        onDismissCountryDialog: () -> Unit
    ) {

        val sheetState = rememberModalBottomSheetState()
        var isSheetOpen by remember { mutableStateOf(false) }
        if (state.isLoading) {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator()
                Text(text = "Cargando...")
            }
        }
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.countries) { country ->
                CountryItem(country = country,
                    modifier = Modifier.fillMaxSize(),
                    onClick = {
                        onSelectCountry(country.code)
                        isSheetOpen = true

                    })

            }
        }

            if (isSheetOpen && state.selectedCountry != null) {


                ModalBottomSheet(
                    sheetState = sheetState,
                    onDismissRequest = {
                        onDismissCountryDialog()
                        isSheetOpen = false
                    }
                ) {
                        Column {
                            Text(text = "País: ${state.selectedCountry.name}")
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "Capital: ${state.selectedCountry.capital}")
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "Continente: ${state.selectedCountry.continent}")
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "Lenguajes: ${state.selectedCountry.languages}")
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "Moneda: ${state.selectedCountry.currency}")
                        }


                }
            }
        }





@Composable
fun CountryItem(country: SimpleCountry, modifier: Modifier, onClick: () -> Unit) {

    Box(modifier = modifier) {
        ConstraintLayout(modifier = Modifier
            .fillMaxSize()
            .clickable { onClick() }) {
            val (name, capital, emoji) = createRefs()

            Text(
                text = country.emoji,
                modifier = Modifier
                    .constrainAs(emoji) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(80.dp)
            )

            Text(text = country.name,
                modifier = Modifier.constrainAs(name) {
                    top.linkTo(emoji.top)
                    start.linkTo(emoji.end, margin = 16.dp)
                })

            Text(text = country.capital,
                modifier = Modifier.constrainAs(capital) {
                    top.linkTo(name.bottom, margin = 8.dp)
                    start.linkTo(emoji.end, margin = 16.dp)
                })
        }
    }
}
