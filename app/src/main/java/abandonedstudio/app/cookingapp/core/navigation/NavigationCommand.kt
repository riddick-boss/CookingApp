package abandonedstudio.app.cookingapp.core.navigation

import androidx.navigation.NamedNavArgument

interface NavigationCommand {

    val arguments: List<NamedNavArgument>
        get() = emptyList()

    val destination: String
}
