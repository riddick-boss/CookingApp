package abandonedstudio.app.cookingapp.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface IngredientDao {

    @Insert
    void insert(Ingredient ingredient);

    @Delete
    void delete(Ingredient ingredient);

    @Update
    void update(Ingredient ingredient);

    @Query("SELECT * FROM ingredients WHERE dish_id LIKE :dishId COLLATE NOCASE")
    LiveData<List<Ingredient>> getAllIngredientsFromDish(int dishId);
}
