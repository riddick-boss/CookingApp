package abandonedstudio.app.cookingapp.features.categories.data

import abandonedstudio.app.cookingapp.core.data.local.room.dish_category.DishCategory
import abandonedstudio.app.cookingapp.core.data.local.room.dish_category.DishCategoryDao

class DataSource(
    private val dishCategoryDao: DishCategoryDao
) {
    val categories = dishCategoryDao.getAllCategories()

    suspend fun add(name: String) = run { dishCategoryDao.insert(DishCategory(name)) }

    suspend fun update(id: Int, name: String) = run { dishCategoryDao.update(DishCategory(name, id)) }

    suspend fun delete(id: Int, name: String) = run { dishCategoryDao.delete(DishCategory(name, id)) }
}