package abandonedstudio.app.cookingapp.features.dishes_list.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Destination
@Composable
fun DishesListScreen(
    dishId: Int,
    navigator: DestinationsNavigator,
    viewModel: DishesListViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        launch {
            viewModel.initializeCategoryId(dishId)
        }
    }

}