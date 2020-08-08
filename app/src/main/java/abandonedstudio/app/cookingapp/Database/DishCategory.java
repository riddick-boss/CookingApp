package abandonedstudio.app.cookingapp.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "dish_category", indices = {@Index("categoryId")})
public class DishCategory {

    public DishCategory(String category) {
        this.category = category;
    }

    @PrimaryKey(autoGenerate = true)
    private int categoryId;

    @ColumnInfo(name = "category")
    private String category;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }
}
