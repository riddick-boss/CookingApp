package abandonedstudio.app.cookingapp.features.dish_category.presentation

import abandonedstudio.app.cookingapp.features.destinations.DishesListScreenDestination
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun DishCategoryScreen(
    navigator: DestinationsNavigator,
    viewModel: DishCategoryViewModel = hiltViewModel()
) {

//    TODO: not in launched effect
    LaunchedEffect(Unit) {
        navigator.navigate(
            DishesListScreenDestination(1)
        )
    }

}