package abandonedstudio.app.cookingapp.core.data.local.room.ingredient

import androidx.annotation.WorkerThread
import javax.inject.Inject

class IngredientRepository @Inject constructor(private val ingredientDao: IngredientDao) {

    @WorkerThread
    suspend fun insert(ingredient: Ingredient) = ingredientDao.insert(ingredient)

    @WorkerThread
    suspend fun update(ingredient: Ingredient) = ingredientDao.update(ingredient)

    @WorkerThread
    suspend fun delete(ingredient: Ingredient) = ingredientDao.delete(ingredient)

    @WorkerThread
    suspend fun getAllIngredientsFromDish(dishId: Int) = ingredientDao.getAllIngredientsFromDish(dishId)
}