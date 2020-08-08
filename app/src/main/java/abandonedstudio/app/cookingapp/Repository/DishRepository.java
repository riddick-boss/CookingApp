package abandonedstudio.app.cookingapp.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import abandonedstudio.app.cookingapp.Database.Dish;
import abandonedstudio.app.cookingapp.Database.DishDao;
import abandonedstudio.app.cookingapp.Database.DishDatabase;

public class DishRepository {

    private DishDao dishDao;

    public DishRepository(Application application){
        DishDatabase dishDatabase = DishDatabase.getInstance(application);
        dishDao = dishDatabase.dishDao();
    }

    public void insert(Dish dish){
        new InsertDishAsyncTask(dishDao).execute(dish);
    }

    public void update(Dish dish){
        new UpdateDishAsyncTask(dishDao).execute(dish);
    }

    public void delete(Dish dish){
        new DeleteDishAsyncTask(dishDao).execute(dish);
    }

    public LiveData<List<Dish>> getAllDishesFromCategory(int categoryId){
        return dishDao.getAllDishesFromCategory(categoryId);
    }

    private static class InsertDishAsyncTask extends AsyncTask<Dish, Void, Void>{

        private DishDao dishDao;

        public InsertDishAsyncTask(DishDao dishDao) {
            this.dishDao = dishDao;
        }

        @Override
        protected Void doInBackground(Dish... dishes) {
            dishDao.insert(dishes[0]);
            return null;
        }
    }

    private static class UpdateDishAsyncTask extends AsyncTask<Dish, Void, Void>{

        private DishDao dishDao;

        public UpdateDishAsyncTask(DishDao dishDao) {
            this.dishDao = dishDao;
        }

        @Override
        protected Void doInBackground(Dish... dishes) {
            dishDao.update(dishes[0]);
            return null;
        }
    }

    private static class DeleteDishAsyncTask extends AsyncTask<Dish, Void, Void>{

        private DishDao dishDao;

        public DeleteDishAsyncTask(DishDao dishDao) {
            this.dishDao = dishDao;
        }

        @Override
        protected Void doInBackground(Dish... dishes) {
            dishDao.delete(dishes[0]);
            return null;
        }
    }
}
