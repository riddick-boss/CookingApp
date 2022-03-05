package abandonedstudio.app.cookingapp.features.categories.data

import abandonedstudio.app.cookingapp.core.data.local.room.dish_category.DishCategoryDao

class DataSource(
    private val dishCategoryDao: DishCategoryDao
) {
    suspend fun loadCategories() = run { dishCategoryDao.getAllCategories() }
}