package abandonedstudio.app.cookingapp.core.data.local.room.dish

import androidx.room.*

@Dao
interface DishDao {

    @Insert
    suspend fun insert(dish: Dish)

    @Update
    suspend fun update(dish: Dish)

    @Delete
    suspend fun delete(dish: Dish)

    @Query("SELECT * FROM dishes WHERE category_id LIKE :categoryId ORDER BY dish_name COLLATE NOCASE")
    suspend fun getAllDishesFromCategory(categoryId: Int): List<Dish>

    @Query("SELECT * FROM dishes ORDER BY dish_name COLLATE NOCASE")
    suspend fun getAllDishes(): List<Dish>
}