package abandonedstudio.app.cookingapp.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "dishes", foreignKeys = @ForeignKey(entity = DishCategory.class, parentColumns = "categoryId", childColumns = "category_id", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE))
public class Dish {
    public Dish(String dishName, int preparationTime, int dishCategoryID) {
        this.dishName = dishName;
        this.preparationTime = preparationTime;
        this.dishCategoryID = dishCategoryID;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "dish_name")
    private String dishName;

    @ColumnInfo(name = "preparation_time")
    private int preparationTime;

    @ColumnInfo(name = "category_id")
    private int dishCategoryID;

    //need to add Uri to photo


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
