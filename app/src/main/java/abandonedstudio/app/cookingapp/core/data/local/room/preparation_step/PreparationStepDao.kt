package abandonedstudio.app.cookingapp.core.data.local.room.preparation_step

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PreparationStepDao {

    @Insert
    suspend fun insert(preparationStep: PreparationStep)

    @Update
    suspend fun update(preparationStep: PreparationStep)

    @Delete
    suspend fun delete(preparationStep: PreparationStep)

    @Query("SELECT * FROM preparation_steps WHERE dish_id LIKE :dishId ORDER BY step_number")
    fun getAllPreparationStepsFromDish(dishId: Int): Flow<List<PreparationStep>>
}