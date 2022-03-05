package abandonedstudio.app.cookingapp.core.data.local.room.ingredient

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface IngredientDao {

    @Insert
    suspend fun insert(ingredient: Ingredient)

    @Update
    suspend fun update(ingredient: Ingredient)

    @Delete
    suspend fun delete(ingredient: Ingredient)

    @Query("SELECT * FROM ingredients WHERE dish_id LIKE :dishId COLLATE NOCASE")
    fun getAllIngredientsFromDish(dishId: Int): Flow<List<Ingredient>>
}