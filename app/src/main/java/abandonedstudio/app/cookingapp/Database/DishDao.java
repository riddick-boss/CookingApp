package abandonedstudio.app.cookingapp.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DishDao {

    @Insert
    void insert(Dish dish);

    @Update
    void update(Dish dish);

    @Delete
    void delete(Dish dish);

    @Query("SELECT * FROM dishes WHERE category_id LIKE :categoryId ORDER BY dish_name COLLATE NOCASE")
    LiveData<List<Dish>> getAllDishesFromCategory(int categoryId);

    @Query("SELECT * FROM dishes ORDER BY dish_name COLLATE NOCASE")
    LiveData<List<Dish>> getAllDishes();

}