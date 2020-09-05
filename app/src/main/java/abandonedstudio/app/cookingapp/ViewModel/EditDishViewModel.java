package abandonedstudio.app.cookingapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import abandonedstudio.app.cookingapp.Adapters.AddDishIngredientsAdapter;
import abandonedstudio.app.cookingapp.Adapters.AddPreparationStepsAdapter;
import abandonedstudio.app.cookingapp.Database.Dish;
import abandonedstudio.app.cookingapp.Database.Ingredient;
import abandonedstudio.app.cookingapp.Database.PreparationStep;
import abandonedstudio.app.cookingapp.Dialogs.PreparationTimeNumberPicker;
import abandonedstudio.app.cookingapp.Repository.DishRepository;
import abandonedstudio.app.cookingapp.Repository.IngredientRepository;
import abandonedstudio.app.cookingapp.Repository.PreparationStepRepository;
import abandonedstudio.app.cookingapp.utils.AddDishCreator;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class EditDishViewModel extends AndroidViewModel {

    private DishRepository dishRepository;
    private PreparationStepRepository preparationStepRepository;
    private IngredientRepository ingredientRepository;
    public AddDishCreator addDishCreator;
    public PreparationTimeNumberPicker picker = new PreparationTimeNumberPicker();
    private CompositeDisposable disposable = new CompositeDisposable();
    public Long dishId;
    public ArrayList<String> ingredientsDescription = new ArrayList<>();
    private List<Ingredient> ingredients = new ArrayList<>();
    public ArrayList<String> preparationStepsDescription = new ArrayList<>();
    private List<PreparationStep> preparationSteps = new ArrayList<>();

    public EditDishViewModel(@NonNull Application application) {
        super(application);
        dishRepository = new DishRepository(application);
        ingredientRepository = new IngredientRepository(application);
        preparationStepRepository = new PreparationStepRepository(application);
        addDishCreator = new AddDishCreator();
    }

    public void update(Dish dish){
        dishRepository.update(dish);
    }

    public void insert(PreparationStep preparationStep){
        preparationStepRepository.insert(preparationStep);
    }

    public void insert(Ingredient ingredient){
        ingredientRepository.insert(ingredient);
    }

    public void delete(Ingredient ingredient){
        ingredientRepository.delete(ingredient);
    }

    public void delete(PreparationStep preparationStep){
        preparationStepRepository.delete(preparationStep);
    }

    public ArrayList<String> getIngredientsDescription() {
        return ingredientsDescription;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setUpListOfIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        for(int i=0; i<ingredients.size(); i++){
            ingredientsDescription.add(i, ingredients.get(i).getIngredient());
        }
    }

    public ArrayList<String> getPreparationStepsDescription() {
        return preparationStepsDescription;
    }

    public void setUpListOfPreparationSteps(List<PreparationStep> preparationSteps) {
        this.preparationSteps = preparationSteps;
        for (int i=0; i<preparationSteps.size(); i++){
            preparationStepsDescription.add(i, preparationSteps.get(i).getStepDescription());
        }
    }

    public List<PreparationStep> getPreparationSteps() {
        return preparationSteps;
    }

    public LiveData<List<Ingredient>> getAllIngredientsFromDish(int dishId){
        return ingredientRepository.getAllIngredientsFromDish(dishId);
    }

    public LiveData<List<PreparationStep>> getAllPreparationStepsFromDish(int dishId){
        return preparationStepRepository.getAllPreparationStepsFromDish(dishId);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
