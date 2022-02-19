package abandonedstudio.app.cookingapp.core.data.local.room.ingredient

import abandonedstudio.app.cookingapp.core.data.local.room.dish.Dish
import androidx.room.*

@Entity(
    tableName = "ingredients",
    indices = [Index("dish_id")],
    foreignKeys = [ForeignKey(
        entity = Dish::class,
        parentColumns = ["dishId"],
        childColumns = ["dish_id"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )]
)
data class Ingredient(

    @ColumnInfo(name = "ingredient")
    var ingredient: String,

    @ColumnInfo(name = "dish_id")
    var dishId: Int,

    @PrimaryKey(autoGenerate = true)
    var ingredientId: Int? = null
)
