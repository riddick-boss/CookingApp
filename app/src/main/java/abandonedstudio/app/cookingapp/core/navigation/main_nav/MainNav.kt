package abandonedstudio.app.cookingapp.core.navigation.main_nav

import abandonedstudio.app.cookingapp.core.navigation.NavigationCommand
import abandonedstudio.app.cookingapp.core.navigation.NavigationManagerImpl
import abandonedstudio.app.cookingapp.features.categories.presentation.CategoriesScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

object MainNavDirections {

    private const val CATEGORIES = "CATEGORIES"
    private const val DISH_CATEGORY = "DISH_CATEGORY"

    private fun <T> getNavArgument(name: String, navType: NavType<T>, isNullable: Boolean = false) =
        navArgument(name) {
            type = navType
            nullable = isNullable
        }

    val categories = object : NavigationCommand {

        override val destination = CATEGORIES
    }

    val dishCategory = object : NavigationCommand {

        override val arguments: List<NamedNavArgument> =
            listOf(getNavArgument("categoryId", NavType.StringType))

        override val destination = "$DISH_CATEGORY/{categoryId}"
    }
}

@Composable
fun MainNavigation(navigationManagerImpl: NavigationManagerImpl) {
    val navController = rememberNavController()

    navigationManagerImpl.commands.collectAsState().value.also {
        if (it.isNotEmpty()) navController.navigate(it)
    }

    NavHost(navController, startDestination = MainNavDirections.categories.destination) {
        composable(
            MainNavDirections.categories.destination,
            MainNavDirections.categories.arguments
        ) {
            CategoriesScreen()
        }
        composable(
            MainNavDirections.dishCategory.destination,
            MainNavDirections.dishCategory.arguments
        ) {
//            DishCategoryScreen()
        }
    }
}