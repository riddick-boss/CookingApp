package abandonedstudio.app.cookingapp.features.add_dish_category.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun AddDishCategoryScreen(
    viewModel: AddDishCategoryViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
//        navigator.navigate(
//            AddDishDestination()
//        )
    }
}