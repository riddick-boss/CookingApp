package abandonedstudio.app.cookingapp.features.categories.presentation

import abandonedstudio.app.cookingapp.R
import abandonedstudio.app.cookingapp.core.presentation.components.*
import abandonedstudio.app.cookingapp.core.presentation.theme.Blue
import abandonedstudio.app.cookingapp.core.presentation.theme.DarkBlue
import abandonedstudio.app.cookingapp.core.presentation.theme.LightGreen
import abandonedstudio.app.cookingapp.core.presentation.util.contentDescription
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CategoriesScreen() {
    val viewModel: CategoriesViewModel = hiltViewModel()

    val categoriesItems = viewModel.categoriesFlow.collectAsState()
    val editModeActive = viewModel.editModeActive.collectAsState()

    val showDeleteDialog = viewModel.showDeleteCategoryDialog.collectAsState()
    val showAddCategoryDialog = viewModel.showAddCategoryDialog.collectAsState()
    val showEditCategoryDialog = viewModel.showEditCategoryDialog.collectAsState()

    val onAddCategoryClicked: () -> Unit = { viewModel.showAddCategoryDialog() }
    val onCategoryClicked: (Int) -> Unit = { viewModel.onCategoryClicked(it.toString()) }
    val onEditClicked: (String, Int) -> Unit =
        { name, id -> viewModel.showEditCategoryDialog(name, id) }
    val onDeleteClicked: (Int) -> Unit = { viewModel.showDeleteCategoryDialog(it) }

    showDeleteDialog.value?.also {
        TextAlertDialog(
            text = R.string.delete_category_dialog_message,
            onDismissRequest = { viewModel.dismissDeleteCategoryDialog() },
            onConfirmClicked = { viewModel.onConfirmDeleteCategoryClicked() })
    }

    showAddCategoryDialog.value?.also {
        TextInputDialog(
            title = R.string.add_new_category,
            onDismissRequest = { viewModel.dismissAddCategoryDialog() },
            onConfirmClicked = { viewModel.onConfirmAddCategoryClicked(it) }
        )
    }

    showEditCategoryDialog.value?.also { (name, _) ->
        TextInputDialog(
            title = R.string.change_category_name,
            text = name,
            onDismissRequest = { viewModel.dismissEditCategoryDialog() },
            onConfirmClicked = { viewModel.onConfirmEditCategoryClicked(it) }
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            TextToolbarWithSettings(R.string.categories) {
                viewModel.toggleEditMode()
            }

            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
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
                        CategoryCard(
                            it.category,
                            editModeActive.value,
                            onClicked = { onCategoryClicked(it.categoryId!!) },
                            onEditClicked = { onEditClicked(it.category, it.categoryId!!) },
                            onDeleteClicked = { onDeleteClicked(it.categoryId!!) }
                        )
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = onAddCategoryClicked,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 16.dp, end = 16.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_baseline_add_24),
                contentDescription = contentDescription(),
                tint = Color.Black
            )
        }
    }
}

@Composable
private fun CategoryCard(
    name: String,
    editModeActive: Boolean,
    onClicked: () -> Unit,
    onEditClicked: () -> Unit,
    onDeleteClicked: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClicked),
        shape = RoundedCornerShape(36.dp),
        backgroundColor = DarkBlue,
        border = BorderStroke(6.dp, Blue)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
//                .background(DarkBlue)
                .padding(start = 32.dp, end = 32.dp, top = 8.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
//                TODO: font
            Text(
                text = name,
                color = LightGreen,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1.0f, true)
            )
            if (editModeActive) {
                IconButton(onClick = onEditClicked) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_sharp_edit_24),
                        contentDescription = contentDescription(),
                        modifier = Modifier.size(32.dp)
                    )
                }
                IconButton(onClick = onDeleteClicked) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_round_delete_24),
                        contentDescription = contentDescription(),
                        tint = Color.Red,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    }
}
