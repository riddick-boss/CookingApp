package abandonedstudio.app.cookingapp.ViewModel;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import abandonedstudio.app.cookingapp.Database.Dish;
import abandonedstudio.app.cookingapp.Database.DishCategory;

public class SharedViewModel extends ViewModel {

    private int categoryId, dishId;
    private String categoryName, dishName;
    private DishCategory dishCategory;
    private Dish dish;
    private List<String> ingredientsToBuy = new ArrayList<>();

    public SharedViewModel() {
    }

    public DishCategory getDishCategory() {
        return dishCategory;
    }

    public void setDishCategory(DishCategory dishCategory) {
        this.dishCategory = dishCategory;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public List<String> getIngredientsToBuy() {
        return ingredientsToBuy;
    }

    public void setIngredientsToBuy(List<String> ingredientsToBuy) {
        this.ingredientsToBuy = ingredientsToBuy;
    }
}
