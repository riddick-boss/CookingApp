package abandonedstudio.app.cookingapp.ViewModel;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import abandonedstudio.app.cookingapp.Database.Dish;
import abandonedstudio.app.cookingapp.Database.DishCategory;

public class SharedViewModel extends ViewModel {

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

    public List<String> getIngredientsToBuy() {
        return ingredientsToBuy;
    }

    public void setIngredientsToBuy(List<String> ingredientsToBuy) {
        this.ingredientsToBuy = ingredientsToBuy;
    }
}
