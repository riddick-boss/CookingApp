package abandonedstudio.app.cookingapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import abandonedstudio.app.cookingapp.Database.Ingredient;
import abandonedstudio.app.cookingapp.Repository.IngredientRepository;

public class CreateShoppingListViewModel extends AndroidViewModel {

    private IngredientRepository ingredientRepository;

    public CreateShoppingListViewModel(@NonNull Application application) {
        super(application);
        ingredientRepository = new IngredientRepository(application);
    }

    public LiveData<List<Ingredient>> getAllIngredientsFromDish(int dishId){
        return ingredientRepository.getAllIngredientsFromDish(dishId);
    }
}
