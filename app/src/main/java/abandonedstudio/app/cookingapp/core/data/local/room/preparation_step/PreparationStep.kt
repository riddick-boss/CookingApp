package abandonedstudio.app.cookingapp.core.data.local.room.preparation_step

import abandonedstudio.app.cookingapp.core.data.local.room.dish.Dish
import androidx.room.*

@Entity(tableName = "preparation_steps", indices = [Index("dish_id")], foreignKeys = [ForeignKey(entity = Dish::class, parentColumns = ["dishId"], childColumns = ["dish_id"], onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)])
data class PreparationStep(

    @ColumnInfo(name = "step_number")
    var stepNumber: Int,

    @ColumnInfo(name = "step_description")
    var stepDescription: String,

    @ColumnInfo(name = "dish_id")
    var dishId: Int,

    @PrimaryKey(autoGenerate = true)
    var stepId: Int? = null
)
