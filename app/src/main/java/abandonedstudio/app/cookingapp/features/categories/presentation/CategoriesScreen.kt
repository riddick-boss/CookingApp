package abandonedstudio.app.cookingapp.features.categories.presentation

import abandonedstudio.app.cookingapp.R
import abandonedstudio.app.cookingapp.core.presentation.components.emptyContent
import abandonedstudio.app.cookingapp.core.presentation.components.progressIndication
import abandonedstudio.app.cookingapp.core.presentation.components.TextToolbarWithSettings
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CategoriesScreen() {
    val viewModel: CategoriesViewModel = hiltViewModel()

    val categoriesItems = viewModel.categoriesFlow.collectAsState()

    TextToolbarWithSettings(R.string.categories) {

    }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        if (categoriesItems.value?.isEmpty() == true) {
            emptyContent()
        }

        if (categoriesItems.value == null) {
            progressIndication()
        }

        if (!categoriesItems.value.isNullOrEmpty()) {
            items(items = categoriesItems.value!!, key = { it.categoryId!! }) {
                CategoryCard(it.category)
            }
        }
    }
}

@Composable
private fun CategoryCard(name: String) {

}