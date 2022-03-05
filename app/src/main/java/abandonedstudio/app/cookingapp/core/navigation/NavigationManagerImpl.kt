package abandonedstudio.app.cookingapp.core.navigation

import kotlinx.coroutines.flow.MutableStateFlow

interface NavigationManager {

    val commands: MutableStateFlow<String>

    fun navigate(destination: String, vararg arguments: String)
}

class NavigationManagerImpl: NavigationManager {

    override val commands = MutableStateFlow("")

    override fun navigate(destination: String, vararg arguments: String) {
        commands.value = NavigationRoute.route(destination, arguments)
    }
}

