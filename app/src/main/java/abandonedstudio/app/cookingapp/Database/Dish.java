package abandonedstudio.app.cookingapp.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "dishes", indices = {@Index("category_id")}, foreignKeys = @ForeignKey(entity = DishCategory.class, parentColumns = "categoryId", childColumns = "category_id", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE))
public class Dish {

    public Dish(String dishName, int preparationTime, int dishCategoryID, String photoUriString) {
        this.dishName = dishName;
        this.preparationTime = preparationTime;
        this.dishCategoryID = dishCategoryID;
        this.photoUriString = photoUriString;
    }

    @PrimaryKey(autoGenerate = true)
    private int dishId;

    @ColumnInfo(name = "dish_name")
    private String dishName;

    @ColumnInfo(name = "preparation_time")
    private int preparationTime;

    @ColumnInfo(name = "category_id")
    private int dishCategoryID;

    @ColumnInfo(name = "photo_uri_string")
    private String photoUriString;

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public int getDishCategoryID() {
        return dishCategoryID;
    }

    public String getPhotoUriString() {
        return photoUriString;
    }
}
