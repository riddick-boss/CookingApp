package abandonedstudio.app.cookingapp

import abandonedstudio.app.cookingapp.core.presentation.theme.CookingAppTheme
import abandonedstudio.app.cookingapp.core.presentation.util.contentDescription
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CookingAppTheme {

            }
        }
    }
}

@Composable
private fun App() {
    Box(Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.peaches_background),
            contentDescription = contentDescription(),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
//        TODO
//        MainNavigation()
    }
}