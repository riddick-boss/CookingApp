package abandonedstudio.app.cookingapp.core.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

fun LazyListScope.progressIndication() {
    item("progress_indicator") {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    }
}

fun LazyListScope.emptyContent() {
    item("empty_content") {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
//        TODO: lottie maybe?
//        Text(text = stringResource(id = ))
        }
    }
}