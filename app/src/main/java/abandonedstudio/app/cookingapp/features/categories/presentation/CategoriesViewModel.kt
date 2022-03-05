package abandonedstudio.app.cookingapp.features.categories.presentation

import abandonedstudio.app.cookingapp.core.data.local.room.dish_category.DishCategory
import abandonedstudio.app.cookingapp.core.navigation.NavigationManager
import abandonedstudio.app.cookingapp.core.navigation.main_nav.MainNavDirections
import abandonedstudio.app.cookingapp.features.categories.data.DataSource
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val dataSource: DataSource,
//    private val savedStateHandle: SavedStateHandle,
    private val navigationManagerImpl: NavigationManager,
    application: Application
): AndroidViewModel(application) {

    val categoriesFlow: StateFlow<List<DishCategory>?> by lazy {
        dataSource.categories.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)
    }

    fun onCategoryClicked(id: String) {
        navigationManagerImpl.navigate(MainNavDirections.dishCategory.destination, id)
    }
}