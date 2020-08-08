package abandonedstudio.app.cookingapp.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {DishCategory.class}, version = 1)
public abstract class DishCategoryDatabase extends RoomDatabase {

    private static DishCategoryDatabase instance;

    public abstract DishCategoryDao dishCategoryDao();

    public static synchronized DishCategoryDatabase getInstance(Context context){
        if(instance==null){
            //change addCallback
            instance = Room.databaseBuilder(context.getApplicationContext(), DishCategoryDatabase.class, "dish_category_database")
                    .fallbackToDestructiveMigration().addCallback(roomCallback).build();
        }
        return instance;
    }

    // just for testing
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private DishCategoryDao dishCategoryDao;
        private PopulateDbAsyncTask(DishCategoryDatabase db) {
            dishCategoryDao = db.dishCategoryDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            dishCategoryDao.insert(new DishCategory("one 1"));
            dishCategoryDao.insert(new DishCategory("Category 2"));
            dishCategoryDao.insert(new DishCategory("Category 3"));
            return null;
        }
    }
}
