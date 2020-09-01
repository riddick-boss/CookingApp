package abandonedstudio.app.cookingapp.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import abandonedstudio.app.cookingapp.Database.Database;
import abandonedstudio.app.cookingapp.Database.Dish;
import abandonedstudio.app.cookingapp.Database.DishDao;
import io.reactivex.rxjava3.core.Single;

public class DishRepository {

    private DishDao dishDao;

    public DishRepository(Application application){
        Database database = Database.getInstance(application);
        dishDao = database.dishDao();
    }

    public void insert(Dish dish){
        new InsertDishAsyncTask(dishDao).execute(dish);
    }

    public Single<Long> insertAndWait(final Dish dish){
        return Single.fromCallable(() -> dishDao.insertAndWait(dish));
    }

    public void insertAndWaitA(Dish dish){
        new InsertDishAsyncTaskWithLong(dishDao).execute(dish);
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

    public LiveData<List<Dish>> getAllDishes(){
        return dishDao.getAllDishes();
    }

    private static class InsertDishAsyncTaskWithLong extends AsyncTask<Dish, Void, Void>{

        private DishDao dishDao;

        public InsertDishAsyncTaskWithLong(DishDao dishDao) {
            this.dishDao = dishDao;
        }

        @Override
        protected Void doInBackground(Dish... dishes) {
            dishDao.insertAndWait(dishes[0]);
            return null;
        }
    }

    private static class InsertDishAsyncTask extends AsyncTask<Dish, Void, Void>{

        private DishDao dishDao;
        private OnDishInsertedListener listener;

        public void setOnDishInsertedListener(OnDishInsertedListener listener){
            this.listener = listener;
        }

        public InsertDishAsyncTask(DishDao dishDao) {
            this.dishDao = dishDao;
        }

        @Override
        protected Void doInBackground(Dish... dishes) {
            dishDao.insert(dishes[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            listener.onDishInserted();
        }
    }

    public interface OnDishInsertedListener{
        void onDishInserted();
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
