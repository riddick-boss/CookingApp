package abandonedstudio.app.cookingapp.core.data.local.room.dish_category

import androidx.room.*

@Dao
interface DishCategoryDao {

    @Insert
    suspend fun insert(dishCategory: DishCategory)

    @Update
    suspend fun update(dishCategory: DishCategory)

    @Delete
    suspend fun delete(dishCategory: DishCategory)

    @Query("SELECT * FROM dish_category ORDER BY category COLLATE NOCASE")
    suspend fun getAllCategories(): HashMap<Int, DishCategory>
}