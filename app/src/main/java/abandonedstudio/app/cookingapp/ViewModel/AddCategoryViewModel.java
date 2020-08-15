package abandonedstudio.app.cookingapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import abandonedstudio.app.cookingapp.Database.DishCategory;
import abandonedstudio.app.cookingapp.Repository.DishCategoryRepository;

public class AddCategoryViewModel extends AndroidViewModel {

    private DishCategoryRepository dishCategoryRepository;

    public AddCategoryViewModel(@NonNull Application application) {
        super(application);
        dishCategoryRepository = new DishCategoryRepository(application);
    }

    public void insert(DishCategory dishCategory){
        dishCategoryRepository.insert(dishCategory);
    }
}
