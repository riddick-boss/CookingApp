package abandonedstudio.app.cookingapp.core.navigation

object NavigationRoute {

    fun route(destination: String, arguments: Array<out String>): String = run {
        var args = ""
        arguments.forEach { args += "/$it" }
        destination + args
    }
}