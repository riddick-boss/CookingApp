package abandonedstudio.app.cookingapp.core.data.local.room.dish_category

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DishCategoryDao {

    @Insert
    suspend fun insert(dishCategory: DishCategory)

    @Update
    suspend fun update(dishCategory: DishCategory)

    @Delete
    suspend fun delete(dishCategory: DishCategory)

    @Query("SELECT * FROM dish_category ORDER BY category COLLATE NOCASE")
    fun getAllCategories(): Flow<List<DishCategory>>
}