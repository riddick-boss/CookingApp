package abandonedstudio.app.cookingapp

import abandonedstudio.app.cookingapp.core.navigation.NavigationManagerImpl
import abandonedstudio.app.cookingapp.core.navigation.main_nav.MainNavigation
import abandonedstudio.app.cookingapp.core.presentation.theme.CookingAppTheme
import abandonedstudio.app.cookingapp.core.presentation.util.contentDescription
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationManagerImpl: NavigationManagerImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CookingAppTheme {
                Box(Modifier.fillMaxSize()) {
                    Image(
                        painter = painterResource(id = R.drawable.peaches_background),
                        contentDescription = contentDescription(),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    MainNavigation(navigationManagerImpl)
                }
            }
        }
    }
}
