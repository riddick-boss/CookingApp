package abandonedstudio.app.cookingapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import abandonedstudio.app.cookingapp.Database.Ingredient;
import abandonedstudio.app.cookingapp.Database.PreparationStep;
import abandonedstudio.app.cookingapp.Repository.IngredientRepository;
import abandonedstudio.app.cookingapp.Repository.PreparationStepRepository;

public class DishViewModel extends AndroidViewModel {

    private PreparationStepRepository preparationStepRepository;
    private IngredientRepository ingredientRepository;

    public DishViewModel(@NonNull Application application) {
        super(application);
        preparationStepRepository = new PreparationStepRepository(application);
        ingredientRepository = new IngredientRepository(application);
    }
    
    public LiveData<List<PreparationStep>> getAllPreparationStepsFromDish(int dishId){
        return preparationStepRepository.getAllPreparationStepsFromDish(dishId);
    }

    public LiveData<List<Ingredient>> getAllIngredientsFromDish(int dishId){
        return ingredientRepository.getAllIngredientsFromDish(dishId);
    }
}
