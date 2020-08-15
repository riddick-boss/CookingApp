package abandonedstudio.app.cookingapp.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import abandonedstudio.app.cookingapp.Database.Database;
import abandonedstudio.app.cookingapp.Database.PreparationStep;
import abandonedstudio.app.cookingapp.Database.PreparationStepDao;

public class PreparationStepRepository {

    private PreparationStepDao preparationstepDao;

    public PreparationStepRepository(Application application){
        Database database = Database.getInstance(application);
        preparationstepDao = database.preparationStepDao();
    }

    public void insert(PreparationStep preparationStep){
        new InsertPreparationStepAsyncTask(preparationstepDao).execute(preparationStep);
    }
    public void update(PreparationStep preparationStep){
        new UpdatePreparationStepAsyncTask(preparationstepDao).execute(preparationStep);
    }

    public void delete(PreparationStep preparationStep){
        new DeletePreparationStepAsyncTask(preparationstepDao).execute(preparationStep);
    }

    public LiveData<List<PreparationStep>> getAllPreparationStepsFromDish(int dishId){
        return preparationstepDao.getAllPreparationStepsFromDish(dishId);
    }

    private static class InsertPreparationStepAsyncTask extends AsyncTask<PreparationStep, Void, Void>{

        private PreparationStepDao preparationStepDao;

        public InsertPreparationStepAsyncTask(PreparationStepDao preparationStepDao) {
            this.preparationStepDao = preparationStepDao;
        }

        @Override
        protected Void doInBackground(PreparationStep... preparationSteps) {
            preparationStepDao.insert(preparationSteps[0]);
            return null;
        }
    }

    private static class UpdatePreparationStepAsyncTask extends AsyncTask<PreparationStep, Void, Void>{

        private PreparationStepDao preparationStepDao;

        public UpdatePreparationStepAsyncTask(PreparationStepDao preparationStepDao) {
            this.preparationStepDao = preparationStepDao;
        }

        @Override
        protected Void doInBackground(PreparationStep... preparationSteps) {
            preparationStepDao.update(preparationSteps[0]);
            return null;
        }
    }

    private static class DeletePreparationStepAsyncTask extends AsyncTask<PreparationStep, Void, Void>{

        private PreparationStepDao preparationStepDao;

        public DeletePreparationStepAsyncTask(PreparationStepDao preparationStepDao) {
            this.preparationStepDao = preparationStepDao;
        }

        @Override
        protected Void doInBackground(PreparationStep... preparationSteps) {
            preparationStepDao.delete(preparationSteps[0]);
            return null;
        }
    }
}
