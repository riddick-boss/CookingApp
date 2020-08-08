package abandonedstudio.app.cookingapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import abandonedstudio.app.cookingapp.Database.DishCategory;
import abandonedstudio.app.cookingapp.Repository.DishCategoryRepository;

public class DishCategoryViewModel extends AndroidViewModel {

    private DishCategoryRepository dishCategoryRepository;
    private LiveData<List<DishCategory>> allCategories;

    public DishCategoryViewModel(@NonNull Application application) {
        super(application);
        dishCategoryRepository = new DishCategoryRepository(application);
        allCategories = dishCategoryRepository.getAllDishCategories();
    }

    public void insert(DishCategory dishCategory){
        dishCategoryRepository.insert(dishCategory);
    }

    public void update(DishCategory dishCategory){
        dishCategoryRepository.update(dishCategory);
    }

    public void delete(DishCategory dishCategory){
        dishCategoryRepository.delete(dishCategory);
    }

    public LiveData<List<DishCategory>> getAllCategories(){
        return allCategories;
    }
}
