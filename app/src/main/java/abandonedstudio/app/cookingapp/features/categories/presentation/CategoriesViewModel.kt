package abandonedstudio.app.cookingapp.features.categories.presentation

import abandonedstudio.app.cookingapp.core.data.local.room.dish_category.DishCategory
import abandonedstudio.app.cookingapp.core.navigation.NavigationManager
import abandonedstudio.app.cookingapp.core.navigation.main_nav.MainNavDirections
import abandonedstudio.app.cookingapp.core.util.extension.showToast
import abandonedstudio.app.cookingapp.features.categories.data.DataSource
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val dataSource: DataSource,
//    private val savedStateHandle: SavedStateHandle,
    private val navigationManager: NavigationManager,
    application: Application
) : AndroidViewModel(application) {

    private val _editModeActive = MutableStateFlow(false)
    val editModeActive = _editModeActive.asStateFlow()

    fun toggleEditMode() {
        _editModeActive.value = !_editModeActive.value
    }

    val categoriesFlow: StateFlow<List<DishCategory>?> by lazy {
        dataSource.categories.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)
    }

    fun onCategoryClicked(id: String) {
        navigationManager.navigate(MainNavDirections.dishCategory.destination, id)
    }

    private val _showDeleteCategoryDialog = MutableStateFlow<Int?>(null)
    val showDeleteCategoryDialog = _showDeleteCategoryDialog.asStateFlow()

    fun showDeleteCategoryDialog(id: Int) {
        _showDeleteCategoryDialog.value = id
    }

    fun dismissDeleteCategoryDialog() {
        _showDeleteCategoryDialog.value = null
    }

    fun onConfirmDeleteCategoryClicked() {
        showDeleteCategoryDialog.value?.also {
            viewModelScope.launch {
                try {
                    dataSource.delete(it)
                    dismissDeleteCategoryDialog()
                } catch (e: Exception) {
                    showToast(e)
                }
            }
        }
    }

    private val _showAddCategoryDialog = MutableStateFlow<Unit?>(null)
    val showAddCategoryDialog = _showAddCategoryDialog.asStateFlow()

    fun showAddCategoryDialog() {
        _showAddCategoryDialog.value = Unit
    }

    fun dismissAddCategoryDialog() {
        _showAddCategoryDialog.value = null
    }

    fun onConfirmAddCategoryClicked(name: String) {
        viewModelScope.launch {
            try {
                dataSource.add(name)
                dismissAddCategoryDialog()
            } catch (e: Exception) {
                showToast(e)
            }
        }
    }

    private val _showEditCategoryDialog = MutableStateFlow<Pair<String, Int>?>(null)
    val showEditCategoryDialog = _showEditCategoryDialog.asStateFlow()

    fun showEditCategoryDialog(name: String, id: Int) {
        _showEditCategoryDialog.value = Pair(name, id)
    }

    fun dismissEditCategoryDialog() {
        _showEditCategoryDialog.value = null
    }

    fun onConfirmEditCategoryClicked(name: String) {
        _showEditCategoryDialog.value?.also { (_, id) ->
            viewModelScope.launch {
                try {
                    dataSource.update(id, name)
                    dismissEditCategoryDialog()
                } catch (e: Exception) {
                    showToast(e)
                }
            }
        }
    }
}