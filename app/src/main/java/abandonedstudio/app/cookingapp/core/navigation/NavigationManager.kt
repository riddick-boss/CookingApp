package abandonedstudio.app.cookingapp.core.navigation

import kotlinx.coroutines.flow.MutableStateFlow

class NavigationManager {

    val commands = MutableStateFlow("")

    fun navigate(destination: String, vararg arguments: String) {
        commands.value = NavigationRoute.route(destination, arguments)
    }
}

