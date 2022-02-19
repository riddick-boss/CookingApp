package abandonedstudio.app.cookingapp.core.data.local.room.dish_category

import androidx.annotation.WorkerThread
import javax.inject.Inject

class DishCategoryRepository @Inject constructor(private val dishCategoryDao: DishCategoryDao) {

    @WorkerThread
    suspend fun insert(dishCategory: DishCategory) = dishCategoryDao.insert(dishCategory)

    @WorkerThread
    suspend fun update(dishCategory: DishCategory) = dishCategoryDao.update(dishCategory)

    @WorkerThread
    suspend fun delete(dishCategory: DishCategory) = dishCategoryDao.delete(dishCategory)

    @WorkerThread
    suspend fun getAllDishCategories() = dishCategoryDao.getAllCategories()
}