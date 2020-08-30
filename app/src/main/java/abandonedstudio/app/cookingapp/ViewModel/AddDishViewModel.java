package abandonedstudio.app.cookingapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import abandonedstudio.app.cookingapp.Adapters.AddDishIngredientsAdapter;
import abandonedstudio.app.cookingapp.Adapters.AddPreparationStepsAdapter;
import abandonedstudio.app.cookingapp.AddDishCreator;
import abandonedstudio.app.cookingapp.Database.Dish;
import abandonedstudio.app.cookingapp.Database.Ingredient;
import abandonedstudio.app.cookingapp.Database.PreparationStep;
import abandonedstudio.app.cookingapp.Dialogs.PreparationTimeNumberPicker;
import abandonedstudio.app.cookingapp.Repository.DishRepository;
import abandonedstudio.app.cookingapp.Repository.IngredientRepository;
import abandonedstudio.app.cookingapp.Repository.PreparationStepRepository;

public class AddDishViewModel extends AndroidViewModel {

    private DishRepository dishRepository;
    private PreparationStepRepository preparationStepRepository;
    private IngredientRepository ingredientRepository;
    public AddDishCreator addDishCreator;
    public PreparationTimeNumberPicker picker = new PreparationTimeNumberPicker();
    public AddDishIngredientsAdapter ingredientsAdapter = new AddDishIngredientsAdapter();
    public AddPreparationStepsAdapter preparationStepsAdapter = new AddPreparationStepsAdapter();

    public AddDishViewModel(@NonNull Application application) {
        super(application);
        dishRepository = new DishRepository(application);
        ingredientRepository = new IngredientRepository(application);
        preparationStepRepository = new PreparationStepRepository(application);
        addDishCreator = new AddDishCreator();
    }

    public void insert(Dish dish){
        dishRepository.insert(dish);
    }

    public void insert(PreparationStep preparationStep){
        preparationStepRepository.insert(preparationStep);
    }

    public void insert(Ingredient ingredient){
        ingredientRepository.insert(ingredient);
    }
}
