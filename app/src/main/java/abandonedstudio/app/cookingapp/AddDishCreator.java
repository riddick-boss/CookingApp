package abandonedstudio.app.cookingapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import abandonedstudio.app.cookingapp.Database.Dish;
import abandonedstudio.app.cookingapp.ViewModel.AddDishViewModel;

public class AddDishCreator {

    private DishPhotoPicker dishPhotoPicker;
    private Uri uri = Uri.parse("android.resource://abandonedstudio.app.cookingapp/drawable/empty_plate_graphic");
    private String dishName = null;
    private int preparationTime = 0;
    private ArrayList<String> ingredients = new ArrayList<>(), preparationSteps = new ArrayList<>();

    public AddDishCreator() {
        dishPhotoPicker = new DishPhotoPicker();
    }

    public boolean createDish(ArrayList<String> ingredients, ArrayList<String> preparationSteps, Context context){
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

    public void OnChoosePhotoResult(Bitmap bitmap){
        dishPhotoPicker.onResult(bitmap);
        setUri(dishPhotoPicker.getUri());
    }

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

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setDefaultValues(){
        setDefaultUri();
        setDishName(null);
        setPreparationTime(0);
    }

    //better to use interface
    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<String> getPreparationSteps() {
        return preparationSteps;
    }

    //better to use interface
    public void setPreparationSteps(ArrayList<String> preparationSteps) {
        this.preparationSteps = preparationSteps;
    }
}
