package abandonedstudio.app.cookingapp.core.data.local.room.dish

import androidx.annotation.WorkerThread
import javax.inject.Inject

class DishRepository @Inject constructor(private val dishDao: DishDao) {

    @WorkerThread
    suspend fun insert(dish: Dish) = dishDao.insert(dish)

    @WorkerThread
    suspend fun update(dish: Dish) = dishDao.update(dish)

    @WorkerThread
    suspend fun delete(dish: Dish) = dishDao.delete(dish)

    @WorkerThread
    suspend fun getAllDishesFromCategory(categoryId: Int) = dishDao.getAllDishesFromCategory(categoryId)

    @WorkerThread
    suspend fun getAllDishes() = dishDao.getAllDishes()
}