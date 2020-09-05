package abandonedstudio.app.cookingapp.utils;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class AddDishCreator {

    private DishPhotoPicker dishPhotoPicker;
    private Uri uri = Uri.parse("android.resource://abandonedstudio.app.cookingapp/drawable/empty_plate_graphic");
    private String dishName = null;
    private int preparationTime = 0;
    private ArrayList<String> ingredients = new ArrayList<>(), preparationSteps = new ArrayList<>();

    public AddDishCreator() {
        dishPhotoPicker = new DishPhotoPicker();
    }

    public boolean checkNecessaryDataEntered(ArrayList<String> ingredients, ArrayList<String> preparationSteps, Context context){
        if(dishName == null || dishName.isEmpty()){
            Toast.makeText(context, "Enter dish name!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(ingredients.isEmpty()){
            Toast.makeText(context, "Enter at least 1 ingredient", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (preparationSteps.isEmpty()){
            Toast.makeText(context, "Enter at least 1 step", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void pickDishPhoto(Fragment fragment){
        dishPhotoPicker.pickDishPhoto(fragment);
    }

    /*public void OnChoosePhotoResult(Bitmap bitmap, Context context){
        dishPhotoPicker.onResult(bitmap, context);
        setUri(dishPhotoPicker.getUri());
    } */

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public void setDefaultUri(){
        uri = Uri.parse("android.resource://abandonedstudio.app.cookingapp/drawable/empty_plate_graphic");
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public void setDefaultValues(){
        setDefaultUri();
        setDishName(null);
        setPreparationTime(0);
    }

}
