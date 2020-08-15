package abandonedstudio.app.cookingapp.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "ingredients", foreignKeys = @ForeignKey(entity = Dish.class, parentColumns = "dishId", childColumns = "dish_id", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE))
public class Ingredient {

    public Ingredient(String ingredient, int dishId) {
        this.ingredient = ingredient;
        this.dishId = dishId;
    }

    @PrimaryKey(autoGenerate = true)
    private int ingredientId;

    @ColumnInfo(name = "ingredient")
    private String ingredient;

    @ColumnInfo(name = "dish_id")
    private int dishId;

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getIngredient() {
        return ingredient;
    }

    public int getDishId() {
        return dishId;
    }
}
