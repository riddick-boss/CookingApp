package abandonedstudio.app.cookingapp.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Dish.class}, version = 1)
public abstract class DishDatabase extends RoomDatabase {

    private static DishDatabase instance;

    public abstract DishDao dishDao();

    public static synchronized DishDatabase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(), DishDatabase.class, "dish_database")
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
        private DishDao dishDao;

        private PopulateDbAsyncTask(DishDatabase db) {
            dishDao = db.dishDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dishDao.insert(new Dish("pizza", 45, 1));
            dishDao.insert(new Dish("kebab", 25, 2));
            dishDao.insert(new Dish("lasagna", 67, 3));
            return null;
        }
    }
}
