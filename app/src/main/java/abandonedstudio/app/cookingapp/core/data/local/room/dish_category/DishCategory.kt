package abandonedstudio.app.cookingapp.core.data.local.room.dish_category

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "dish_category", indices = [Index("categoryId")])
data class DishCategory(

    @ColumnInfo(name = "category")
    var category: String,

    @PrimaryKey(autoGenerate = true)
    var categoryId: Int? = null

)
