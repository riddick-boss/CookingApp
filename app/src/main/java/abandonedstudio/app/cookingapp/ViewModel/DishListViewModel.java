package abandonedstudio.app.cookingapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import abandonedstudio.app.cookingapp.Database.Dish;
import abandonedstudio.app.cookingapp.Repository.DishRepository;

public class DishListViewModel extends AndroidViewModel {

    private DishRepository dishRepository;

    public DishListViewModel(@NonNull Application application) {
        super(application);
        dishRepository = new DishRepository(application);
    }

    public void insert(Dish dish){
        dishRepository.insert(dish);
    }

    public void update(Dish dish){
        dishRepository.update(dish);
    }

    public void delete(Dish dish){
        dishRepository.delete(dish);
    }

    public LiveData<List<Dish>> getAllDishesFromCategory(int categoryId){
        return dishRepository.getAllDishesFromCategory(categoryId);
    }

    public LiveData<List<Dish>> getAllDishes(){
        return dishRepository.getAllDishes();
    }

}
