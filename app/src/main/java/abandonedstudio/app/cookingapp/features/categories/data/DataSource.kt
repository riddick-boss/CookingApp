package abandonedstudio.app.cookingapp.features.categories.data

import abandonedstudio.app.cookingapp.core.data.local.room.dish_category.DishCategory
import abandonedstudio.app.cookingapp.core.data.local.room.dish_category.DishCategoryDao
import javax.inject.Inject

class DataSource @Inject constructor(
    private val dishCategoryDao: DishCategoryDao
) {
    val categories = dishCategoryDao.getAllCategories()

    suspend fun add(name: String) {
        dishCategoryDao.insert(DishCategory(name))
    }

    suspend fun update(id: Int, name: String) {
        dishCategoryDao.update(DishCategory(name, id))
    }

    suspend fun delete(id: Int) {
        dishCategoryDao.deleteById(id)
    }
}