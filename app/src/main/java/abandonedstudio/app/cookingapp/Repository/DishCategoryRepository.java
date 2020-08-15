package abandonedstudio.app.cookingapp.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import abandonedstudio.app.cookingapp.Database.Database;
import abandonedstudio.app.cookingapp.Database.DishCategory;
import abandonedstudio.app.cookingapp.Database.DishCategoryDao;

public class DishCategoryRepository {

    private DishCategoryDao dishCategoryDao;
    private LiveData<List<DishCategory>> allDishCategories;

    public DishCategoryRepository(Application application){
        Database database = Database.getInstance(application);
        dishCategoryDao = database.dishCategoryDao();
        allDishCategories = dishCategoryDao.getAllCategories();
    }

    public void insert(DishCategory dishCategory){
        new InsertDishCategoryAsyncTask(dishCategoryDao).execute(dishCategory);
    }

    public void update(DishCategory dishCategory){
        new UpdateDishCategoryAsyncTask(dishCategoryDao).execute(dishCategory);
    }

    public void delete(DishCategory dishCategory){
        new DeleteDishCategoryAsyncTask(dishCategoryDao).execute(dishCategory);
    }

    public LiveData<List<DishCategory>> getAllDishCategories(){
        return allDishCategories;
    }


    private static class InsertDishCategoryAsyncTask extends AsyncTask<DishCategory, Void, Void>{

        private DishCategoryDao dishCategoryDao;

        private InsertDishCategoryAsyncTask(DishCategoryDao dishCategoryDao){
            this.dishCategoryDao = dishCategoryDao;
        }

        @Override
        protected Void doInBackground(DishCategory... dishCategories) {
            dishCategoryDao.insert(dishCategories[0]);
            return null;
        }
    }

    private static class UpdateDishCategoryAsyncTask extends AsyncTask<DishCategory, Void, Void>{

        private DishCategoryDao dishCategoryDao;

        private UpdateDishCategoryAsyncTask(DishCategoryDao dishCategoryDao){
            this.dishCategoryDao = dishCategoryDao;
        }

        @Override
        protected Void doInBackground(DishCategory... dishCategories) {
            dishCategoryDao.update(dishCategories[0]);
            return null;
        }
    }

    private static class DeleteDishCategoryAsyncTask extends AsyncTask<DishCategory, Void, Void>{

        private DishCategoryDao dishCategoryDao;

        private DeleteDishCategoryAsyncTask(DishCategoryDao dishCategoryDao){
            this.dishCategoryDao = dishCategoryDao;
        }

        @Override
        protected Void doInBackground(DishCategory... dishCategories) {
            dishCategoryDao.delete(dishCategories[0]);
            return null;
        }
    }
}
