package abandonedstudio.app.cookingapp.core.data.local.room.dish

import abandonedstudio.app.cookingapp.core.data.local.room.dish_category.DishCategory
import androidx.room.*

@Entity(
    tableName = "dishes",
    indices = [Index("category_id")],
    foreignKeys = [ForeignKey(
        entity = DishCategory::class,
        parentColumns = ["categoryId"],
        childColumns = ["category_id"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )]
)
data class Dish(

    @ColumnInfo(name = "dish_name")
    var dishName: String,

    @ColumnInfo(name = "preparation_time")
    var preparationTime: Int,

    @ColumnInfo(name = "category_id")
    var dishCategoryId: Int,

    @ColumnInfo(name = "photo_uri")
    var photoUri: String,

    @PrimaryKey(autoGenerate = true)
    var dishId: Int? = null
)
