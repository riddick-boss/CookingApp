package abandonedstudio.app.cookingapp.Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
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
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import abandonedstudio.app.cookingapp.Adapters.AddDishIngredientsAdapter;
import abandonedstudio.app.cookingapp.Adapters.AddPreparationStepsAdapter;
import abandonedstudio.app.cookingapp.Adapters.PreparationStepMoveCallback;
import abandonedstudio.app.cookingapp.Database.Dish;
import abandonedstudio.app.cookingapp.Database.Ingredient;
import abandonedstudio.app.cookingapp.Database.PreparationStep;
import abandonedstudio.app.cookingapp.Dialogs.PreparationTimeNumberPicker;
import abandonedstudio.app.cookingapp.GlideModule.GlideApp;
import abandonedstudio.app.cookingapp.R;
import abandonedstudio.app.cookingapp.RequestCode;
import abandonedstudio.app.cookingapp.ViewModel.EditDishViewModel;
import abandonedstudio.app.cookingapp.ViewModel.SharedViewModel;

public class EditDishFragment extends Fragment {

    private EditText dishNameEditText, addIngredientEditText, addPreparationStepEditText;
    private ImageButton backToDishListButton;
    private ImageView clockImageView, dishPhotoImageView;
    private Button choosePhotoButton, addDishButton, addIngredientButton, addPreparationStepButton, deletePhotoButton;
    private RecyclerView ingredientsRecyclerView, preparationStepsRecyclerView;
    private TextView preparationTimeTextView;
    private EditDishViewModel editDishViewModel;
    private SharedViewModel sharedViewModel;
    private AddDishIngredientsAdapter ingredientsAdapter;
    private AddPreparationStepsAdapter preparationStepsAdapter;

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

        editDishViewModel = new ViewModelProvider(requireActivity()).get(EditDishViewModel.class);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        ingredientsAdapter = editDishViewModel.ingredientsAdapter;
        preparationStepsAdapter = editDishViewModel.preparationStepsAdapter;

        editDishViewModel.ingredientsDescription = new ArrayList<>();
        editDishViewModel.preparationStepsDescription = new ArrayList<>();

        editDishViewModel.addDishCreator.setPreparationTime(sharedViewModel.getDish().getPreparationTime());
        editDishViewModel.addDishCreator.setUri(Uri.parse(sharedViewModel.getDish().getPhotoUriString()));

        editDishViewModel.getAllIngredientsFromDish(sharedViewModel.getDish().getDishId()).observe(getViewLifecycleOwner(), new Observer<List<Ingredient>>() {
            @Override
            public void onChanged(List<Ingredient> ingredients) {
                //getting list of ingredients and transforming to arrayList of strings containing ingredient description
                editDishViewModel.setUpListOfIngredients(ingredients);
                //setting this arrayList of strings as arrayList for adapter
                ingredientsAdapter.setIngredients(editDishViewModel.getIngredientsDescription());
            }
        });

        editDishViewModel.getAllPreparationStepsFromDish(sharedViewModel.getDish().getDishId()).observe(getViewLifecycleOwner(), new Observer<List<PreparationStep>>() {
            @Override
            public void onChanged(List<PreparationStep> preparationSteps) {
                editDishViewModel.setUpListOfPreparationSteps(preparationSteps);
                preparationStepsAdapter.setPreparationSteps(editDishViewModel.getPreparationStepsDescription());
            }
        });

        //navigate back to dish list
        backToDishListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(EditDishFragment.this)
                        .navigate(R.id.action_editDishFragment_to_dishesListFragment);
            }
        });

        //open photo picker
        choosePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermissions()) {
                    editDishViewModel.addDishCreator.pickDishPhoto(EditDishFragment.this);
                }
            }
        });

        //load dish image into image view
        GlideApp.with(requireContext())
                .load(Uri.parse(sharedViewModel.getDish().getPhotoUriString()))
                .override(dishPhotoImageView.getWidth(), dishPhotoImageView.getHeight())
                .fitCenter()
                .into(dishPhotoImageView);

        //set dish name
        dishNameEditText.setText(sharedViewModel.getDish().getDishName());

        //open number picker on clock image click
        clockImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editDishViewModel.picker.numberPicker(requireContext());
                editDishViewModel.picker.setListener(new PreparationTimeNumberPicker.OnPreparationTimeChoseListener() {
                    @Override
                    public void getPreparationTime(int time) {
                        editDishViewModel.addDishCreator.setPreparationTime(time);
                        preparationTimeTextView.setText(String.valueOf(editDishViewModel.addDishCreator.getPreparationTime()));
                    }
                });
            }
        });

        //set preparation time textView
        preparationTimeTextView.setText(String.valueOf(sharedViewModel.getDish().getPreparationTime()));


        //set up ingredients recycler view
        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ingredientsRecyclerView.setHasFixedSize(false);
        ingredientsRecyclerView.setAdapter(ingredientsAdapter);

        //set up preparation steps recycler view
        ItemTouchHelper.Callback callback = new PreparationStepMoveCallback(preparationStepsAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        preparationStepsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        preparationStepsRecyclerView.setHasFixedSize(false);
        touchHelper.attachToRecyclerView(preparationStepsRecyclerView);
        preparationStepsRecyclerView.setAdapter(preparationStepsAdapter);

        //adding new ingredient
        addIngredientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newIngredient = addIngredientEditText.getText().toString().trim();
                if(newIngredient.isEmpty()){
                    Toast.makeText(getContext(), "No ingredient entered!", Toast.LENGTH_SHORT).show();
                }
                else{
                    ingredientsAdapter.addIngredient(newIngredient);
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
                    preparationStepsAdapter.addPreparationStep(newPreparationStep);
                    addPreparationStepEditText.setText(null);
                }
            }
        });

        //deleting photo and setting uri to default image uri
        deletePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editDishViewModel.addDishCreator.setDefaultUri();
                GlideApp.with(requireContext())
                        .load(editDishViewModel.addDishCreator.getUri())
                        .override(dishPhotoImageView.getWidth(), dishPhotoImageView.getHeight())
                        .fitCenter()
                        .into(dishPhotoImageView);
            }
        });

        //updating dish, ingredients and steps
        addDishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editDishViewModel.addDishCreator.setDishName(dishNameEditText.getText().toString().trim());
                //update dish
                if (editDishViewModel.addDishCreator.checkNecessaryDataEntered(ingredientsAdapter.getIngredients(), preparationStepsAdapter.getPreparationSteps(), getContext())) {
                    int dishId = sharedViewModel.getDish().getDishId();
                    Dish updatedDish = new Dish(editDishViewModel.addDishCreator.getDishName(),
                            editDishViewModel.addDishCreator.getPreparationTime(), sharedViewModel.getDishCategory().getCategoryId(), String.valueOf(editDishViewModel.addDishCreator.getUri()));
                    updatedDish.setDishId(dishId);
                    editDishViewModel.update(updatedDish);
                    //delete all steps and ingredients
                    int i;
                    for(i=0; i<editDishViewModel.getIngredients().size(); i++){
                        editDishViewModel.delete(editDishViewModel.getIngredients().get(i));
                    }
                    for(i=0; i<editDishViewModel.getPreparationSteps().size(); i++){
                        editDishViewModel.delete(editDishViewModel.getPreparationSteps().get(i));
                    }
                    //insert steps and ingredients like in add dish fragment
                    for(i = 0; i < ingredientsAdapter.getIngredients().size(); i++) {
                        Ingredient ingredient = new Ingredient(ingredientsAdapter.getIngredients().get(i), dishId);
                        editDishViewModel.insert(ingredient);
                    }
                    for(i = 0; i < preparationStepsAdapter.getPreparationSteps().size(); i++) {
                        PreparationStep preparationStep = new PreparationStep(i + 1, preparationStepsAdapter.getPreparationSteps().get(i), dishId);
                        editDishViewModel.insert(preparationStep);
                    }
                    editDishViewModel.addDishCreator.setDefaultValues();
                    dishNameEditText.setText(null);
                    ingredientsAdapter.getIngredients().clear();
                    preparationStepsAdapter.getPreparationSteps().clear();
                    NavHostFragment.findNavController(EditDishFragment.this)
                            .navigate(R.id.action_editDishFragment_to_dishesListFragment);
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
                    editDishViewModel.addDishCreator.setUri(uri);
                    GlideApp.with(requireContext())
                            .load(editDishViewModel.addDishCreator.getUri())
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

    private boolean checkPermissions(){
        if(ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE}, RequestCode.PERMISSION_CODE);
            return false;
        }
        return true;
    }
}
