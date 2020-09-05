package abandonedstudio.app.cookingapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

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
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AddDishViewModel extends AndroidViewModel {

    private DishRepository dishRepository;
    private PreparationStepRepository preparationStepRepository;
    private IngredientRepository ingredientRepository;
    public AddDishCreator addDishCreator;
    public PreparationTimeNumberPicker picker = new PreparationTimeNumberPicker();
    private CompositeDisposable disposable = new CompositeDisposable();
    public MutableLiveData<Boolean> isInserted = new MutableLiveData<>();
    public Long dishId;

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

    public void insertAndWait(Dish dish){
        disposable.add(
                dishRepository.insertAndWait(dish)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new DisposableSingleObserver<Long>() {
            @Override
            public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull Long aLong) {
                isInserted.postValue(true);
                dishId = aLong;
            }

            @Override
            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                isInserted.postValue(false);
            }
        }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
