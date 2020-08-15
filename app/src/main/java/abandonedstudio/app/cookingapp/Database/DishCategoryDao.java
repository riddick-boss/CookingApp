package abandonedstudio.app.cookingapp.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DishCategoryDao {

    @Insert
    void insert(DishCategory dishCategory);

    @Update
    void update(DishCategory dishCategory);

    @Delete
    void delete(DishCategory dishCategory);

    @Query("SELECT * FROM dish_category ORDER BY category COLLATE NOCASE")
    LiveData<List<DishCategory>> getAllCategories();
}
