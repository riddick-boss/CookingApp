package abandonedstudio.app.cookingapp.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import abandonedstudio.app.cookingapp.Database.Database;
import abandonedstudio.app.cookingapp.Database.Ingredient;
import abandonedstudio.app.cookingapp.Database.IngredientDao;

public class IngredientRepository {

    private IngredientDao ingredientDao;

    public IngredientRepository(Application application){
        Database database = Database.getInstance(application);
        ingredientDao = database.ingredientDao();
    }

    public void insert(Ingredient ingredient){
        new InsertIngredientAsyncTask(ingredientDao).execute(ingredient);
    }

    public void update(Ingredient ingredient){
        new UpdateIngredientAsyncTask(ingredientDao).execute(ingredient);
    }

    public void delete(Ingredient ingredient){
        new DeleteIngredientAsyncTask(ingredientDao).execute(ingredient);
    }

    public LiveData<List<Ingredient>> getAllIngredientsFromDish(int dishId){
        return ingredientDao.getAllIngredientsFromDish(dishId);
    }

    private static class InsertIngredientAsyncTask extends AsyncTask<Ingredient, Void, Void>{

        private IngredientDao ingredientDao;

        public InsertIngredientAsyncTask(IngredientDao ingredientDao) {
            this.ingredientDao = ingredientDao;
        }

        @Override
        protected Void doInBackground(Ingredient... ingredients) {
            ingredientDao.insert(ingredients[0]);
            return null;
        }
    }

    private static class UpdateIngredientAsyncTask extends AsyncTask<Ingredient, Void, Void>{

        private IngredientDao ingredientDao;

        public UpdateIngredientAsyncTask(IngredientDao ingredientDao) {
            this.ingredientDao = ingredientDao;
        }

        @Override
        protected Void doInBackground(Ingredient... ingredients) {
            ingredientDao.update(ingredients[0]);
            return null;
        }
    }

    private static class DeleteIngredientAsyncTask extends AsyncTask<Ingredient, Void, Void>{

        private IngredientDao ingredientDao;

        public DeleteIngredientAsyncTask(IngredientDao ingredientDao) {
            this.ingredientDao = ingredientDao;
        }

        @Override
        protected Void doInBackground(Ingredient... ingredients) {
            ingredientDao.delete(ingredients[0]);
            return null;
        }
    }
}
