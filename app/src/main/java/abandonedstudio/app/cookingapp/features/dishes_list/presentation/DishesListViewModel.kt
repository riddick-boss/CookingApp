package abandonedstudio.app.cookingapp.features.dishes_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DishesListViewModel @Inject constructor(): ViewModel() {

    private val categoryId = MutableStateFlow<Int?>(null)

    fun initializeCategoryId(id: Int){
        viewModelScope.launch {
            categoryId.emit(id)
        }
    }

}