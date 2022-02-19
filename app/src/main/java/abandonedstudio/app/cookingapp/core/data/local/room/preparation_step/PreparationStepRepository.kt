package abandonedstudio.app.cookingapp.core.data.local.room.preparation_step

import androidx.annotation.WorkerThread
import javax.inject.Inject

class PreparationStepRepository @Inject constructor(private val preparationStepDao: PreparationStepDao) {

    @WorkerThread
    suspend fun insert(step: PreparationStep) = preparationStepDao.insert(step)

    @WorkerThread
    suspend fun update(step: PreparationStep) = preparationStepDao.update(step)

    @WorkerThread
    suspend fun delete(step: PreparationStep) = preparationStepDao.delete(step)

    @WorkerThread
    suspend fun getAllPreparationStepsFromDish(dishId: Int) = preparationStepDao.getAllPreparationStepsFromDish(dishId)
}