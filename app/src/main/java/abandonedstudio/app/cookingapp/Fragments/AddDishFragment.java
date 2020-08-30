package abandonedstudio.app.cookingapp.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import abandonedstudio.app.cookingapp.Adapters.AddDishIngredientsAdapter;
import abandonedstudio.app.cookingapp.Adapters.AddPreparationStepsAdapter;
import abandonedstudio.app.cookingapp.CloseKeyboard;
import abandonedstudio.app.cookingapp.Database.Dish;
import abandonedstudio.app.cookingapp.Database.Ingredient;
import abandonedstudio.app.cookingapp.Database.PreparationStep;
import abandonedstudio.app.cookingapp.Dialogs.PreparationTimeNumberPicker;
import abandonedstudio.app.cookingapp.GlideModule.GlideApp;
import abandonedstudio.app.cookingapp.R;
import abandonedstudio.app.cookingapp.RequestCode;
import abandonedstudio.app.cookingapp.ViewModel.AddDishViewModel;
import abandonedstudio.app.cookingapp.ViewModel.SharedViewModel;

public class AddDishFragment extends Fragment {

    private EditText dishNameEditText, addIngredientEditText, addPreparationStepEditText;
    private ImageButton backToDishListButton;
    private ImageView clockImageView, dishPhotoImageView;
    private Button choosePhotoButton, addDishButton, addIngredientButton, addPreparationStepButton, deletePhotoButton;
    private RecyclerView ingredientsRecyclerView, preparationStepsRecyclerView;
    private TextView preparationTimeTextView;
    private AddDishViewModel addDishViewModel;
    private SharedViewModel sharedViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_change_dish_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dishNameEditText = view.findViewById(R.id.enter_dish_name_editText);
        backToDishListButton = view.findViewById(R.id.back_to_dish_list_from_change_dish_imageButton);
        clockImageView = view.findViewById(R.id.preparation_time_picker_imageView);
        dishPhotoImageView = view.findViewById(R.id.dish_photo_imageView);
        choosePhotoButton = view.findViewById(R.id.choose_photo_button);
        addDishButton = view.findViewById(R.id.ready_dish_button);
        ingredientsRecyclerView = view.findViewById(R.id.ingredients_add_recyclerView);
        preparationStepsRecyclerView = view.findViewById(R.id.preparation_steps_add_recyclerView);
        preparationTimeTextView = view.findViewById(R.id.preparation_time_add_textView);
        addIngredientButton = view.findViewById(R.id.add_ingredient_button);
        addPreparationStepButton = view.findViewById(R.id.add_preparation_step_button);
        addIngredientEditText = view.findViewById(R.id.add_ingredient_editText);
        addPreparationStepEditText = view.findViewById(R.id.add_preparation_step_editText);
        deletePhotoButton = view.findViewById(R.id.delete_photo_button);

        addDishViewModel = new ViewModelProvider(requireActivity()).get(AddDishViewModel.class);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        //navigate back to dish list
        backToDishListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(AddDishFragment.this)
                        .navigate(R.id.action_addDishFragment_to_dishesListFragment);
            }
        });

        //open photo picker
        choosePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDishViewModel.addDishCreator.pickDishPhoto(AddDishFragment.this);
            }
        });

        //load dish image into image view
        GlideApp.with(requireContext())
                .load(addDishViewModel.addDishCreator.getUri())
                .override(dishPhotoImageView.getWidth(), dishPhotoImageView.getHeight())
                .fitCenter()
                .into(dishPhotoImageView);

        //set dish name
        if(addDishViewModel.addDishCreator.getDishName() != null && !addDishViewModel.addDishCreator.getDishName().isEmpty()){
            dishNameEditText.setText(addDishViewModel.addDishCreator.getDishName());
        }

        //open number picker on clock image click
        clockImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDishViewModel.picker.numberPicker(requireContext());
                addDishViewModel.picker.setListener(new PreparationTimeNumberPicker.OnPreparationTimeChoseListener() {
                    @Override
                    public void getPreparationTime(int time) {
                        addDishViewModel.addDishCreator.setPreparationTime(time);
                        preparationTimeTextView.setText(String.valueOf(addDishViewModel.addDishCreator.getPreparationTime()));
                    }
                });
            }
        });

        //set preparation time if entered
        if (addDishViewModel.addDishCreator.getPreparationTime()>0){
            preparationTimeTextView.setText(String.valueOf(addDishViewModel.addDishCreator.getPreparationTime()));
        }

        //set up ingredients recycler view
        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ingredientsRecyclerView.setHasFixedSize(false);
        ingredientsRecyclerView.setAdapter(addDishViewModel.ingredientsAdapter);

        //set up preparation steps recycler view
        preparationStepsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        preparationStepsRecyclerView.setHasFixedSize(false);
        preparationStepsRecyclerView.setAdapter(addDishViewModel.preparationStepsAdapter);

        //adding new ingredient
        addIngredientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newIngredient = addIngredientEditText.getText().toString().trim();
                if(newIngredient.isEmpty()){
                    Toast.makeText(getContext(), "No ingredient entered!", Toast.LENGTH_SHORT).show();
                }
                else{
                    addDishViewModel.ingredientsAdapter.addIngredient(newIngredient);
                    addIngredientEditText.setText(null);
                }
            }
        });

        //adding new preparation step
        addPreparationStepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPreparationStep = addPreparationStepEditText.getText().toString().trim();
                if(newPreparationStep.isEmpty()){
                    Toast.makeText(getContext(), "No preparation step entered", Toast.LENGTH_SHORT).show();
                }
                else{
                    addDishViewModel.preparationStepsAdapter.addPreparationStep(newPreparationStep);
                    addPreparationStepEditText.setText(null);
                }
            }
        });

        //deleting photo and setting uri to default image uri
        deletePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDishViewModel.addDishCreator.setDefaultUri();
                GlideApp.with(requireContext())
                        .load(addDishViewModel.addDishCreator.getUri())
                        .override(dishPhotoImageView.getWidth(), dishPhotoImageView.getHeight())
                        .fitCenter()
                        .into(dishPhotoImageView);
            }
        });

        //adding new dish with preparation steps and ingredients, choosing photo is optional
        addDishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checking if all required data is entered (dish name, steps, ingredients, etc)
                addDishViewModel.addDishCreator.setDishName(dishNameEditText.getText().toString().trim());
                if(addDishViewModel.addDishCreator.createDish(addDishViewModel.ingredientsAdapter.getIngredients(), addDishViewModel.preparationStepsAdapter.getPreparationSteps(), getContext())){
                    Dish dish = new Dish(addDishViewModel.addDishCreator.getDishName(),
                            addDishViewModel.addDishCreator.getPreparationTime(), sharedViewModel.getDishCategory().getCategoryId(), String.valueOf(addDishViewModel.addDishCreator.getUri()));
                    addDishViewModel.insert(dish);
                    boolean b = false;
                    while (!b && dish.getDishId()==0){
                        try {
                            int dishId = dish.getDishId();
                            Log.d("dishId", String.valueOf(dishId));
                            b = true;
                        } catch (Exception ignored){
                        }
                    }
                    b = false;
                    for(int i = 0; i<addDishViewModel.ingredientsAdapter.getIngredients().size(); i++){
                        Ingredient ingredient = new Ingredient(addDishViewModel.ingredientsAdapter.getIngredients().get(i), dish.getDishId());
                        addDishViewModel.insert(ingredient);
                    }
                    for(int i = 0; i<addDishViewModel.preparationStepsAdapter.getPreparationSteps().size(); i++){
                        PreparationStep preparationStep = new PreparationStep(i+1, addDishViewModel.preparationStepsAdapter.getPreparationSteps().get(i), dish.getDishId());
                        addDishViewModel.insert(preparationStep);
                    }
                    addDishViewModel.addDishCreator.setDefaultValues();
                    addDishViewModel.ingredientsAdapter.getIngredients().clear();
                    addDishViewModel.ingredientsAdapter.setIngredients(null);
                    addDishViewModel.preparationStepsAdapter.getPreparationSteps().clear();
                    addDishViewModel.preparationStepsAdapter.setPreparationSteps(null);
                    Snackbar.make(requireView(), "Dish entered", Snackbar.LENGTH_SHORT).show();
                    NavHostFragment.findNavController(AddDishFragment.this)
                            .navigate(R.id.action_addDishFragment_to_dishesListFragment);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RequestCode.GALLERY_PICK_IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            if(data != null){
                try {
                    Uri uri = data.getData();
                    addDishViewModel.addDishCreator.setUri(uri);
                    //Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), uri);
                    //addDishViewModel.addDishCreator.OnChoosePhotoResult(bitmap);
                    GlideApp.with(requireContext())
                            .load(addDishViewModel.addDishCreator.getUri())
                            .override(dishPhotoImageView.getWidth(), dishPhotoImageView.getHeight())
                            .fitCenter()
                            .into(dishPhotoImageView);
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Failed to get photo", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
