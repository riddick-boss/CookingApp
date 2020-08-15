package abandonedstudio.app.cookingapp.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PreparationStepDao {

    @Insert
    void insert(PreparationStep preparationStep);

    @Delete
    void delete(PreparationStep preparationStep);

    @Update
    void update(PreparationStep preparationStep);

    @Query("SELECT * FROM preparation_steps WHERE dish_id LIKE :dishId ORDER BY step_number")
    LiveData<List<PreparationStep>> getAllPreparationStepsFromDish(int dishId);
}
