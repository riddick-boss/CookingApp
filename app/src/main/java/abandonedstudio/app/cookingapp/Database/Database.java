package abandonedstudio.app.cookingapp.Database;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@androidx.room.Database(entities = {DishCategory.class, Dish.class, PreparationStep.class, Ingredient.class}, version = 1)
public abstract class Database extends RoomDatabase {

    private static Database instance;

    public abstract DishCategoryDao dishCategoryDao();
    public abstract DishDao dishDao();
    public abstract PreparationStepDao preparationStepDao();
    public abstract IngredientDao ingredientDao();

    public static synchronized Database getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(), Database.class, "main_database")
                    .fallbackToDestructiveMigration().addCallback(roomCallback).build();
        }
        return instance;
    }

    // just for pre- populating database
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private DishCategoryDao dishCategoryDao;
        private DishDao dishDao;
        private PreparationStepDao preparationStepDao;
        private IngredientDao ingredientDao;
        private PopulateDbAsyncTask(Database db) {
            dishCategoryDao = db.dishCategoryDao();
            dishDao = db.dishDao();
            preparationStepDao = db.preparationStepDao();
            ingredientDao = db.ingredientDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            dishCategoryDao.insert(new DishCategory("Breakfast"));
            dishCategoryDao.insert(new DishCategory("Dinner"));
            dishCategoryDao.insert(new DishCategory("Lunch"));
            dishDao.insert(new Dish("Cereals", 6, 1, String.valueOf(Uri.parse("android.resource://abandonedstudio.app.cookingapp/drawable/cereals_photo"))));
            dishDao.insert(new Dish("Pizza", 67, 2, String.valueOf(Uri.parse("android.resource://abandonedstudio.app.cookingapp/drawable/pizza_photo"))));
            dishDao.insert(new Dish("lasagna", 53, 3, String.valueOf(Uri.parse("android.resource://abandonedstudio.app.cookingapp/drawable/lasagna_photo"))));
            preparationStepDao.insert(new PreparationStep(1, "warm up milk", 1));
            preparationStepDao.insert(new PreparationStep(2, "add cereals", 1));
            preparationStepDao.insert(new PreparationStep(1, "wash tomatoes", 2));
            preparationStepDao.insert(new PreparationStep(2, "slice tomatoes and wurst", 2));
            preparationStepDao.insert(new PreparationStep(3, "bake in 230 degrees for 25 min", 2));
            preparationStepDao.insert(new PreparationStep(1, "just buy and put in microwave", 3));
            ingredientDao.insert(new Ingredient("milk", 1));
            ingredientDao.insert(new Ingredient("cereals", 1));
            ingredientDao.insert(new Ingredient("tomatoes", 2));
            ingredientDao.insert(new Ingredient("wurst", 2));
            ingredientDao.insert(new Ingredient("pasta", 3));
            ingredientDao.insert(new Ingredient("meat", 3));
            ingredientDao.insert(new Ingredient("more pasta", 3));
            return null;
        }
    }
}
