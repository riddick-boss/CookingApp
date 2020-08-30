package abandonedstudio.app.cookingapp.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import abandonedstudio.app.cookingapp.Adapters.DishIngredientAdapter;
import abandonedstudio.app.cookingapp.Adapters.DishPreparationStepsAdapter;
import abandonedstudio.app.cookingapp.Database.Dish;
import abandonedstudio.app.cookingapp.Database.Ingredient;
import abandonedstudio.app.cookingapp.Database.PreparationStep;
import abandonedstudio.app.cookingapp.GlideModule.GlideApp;
import abandonedstudio.app.cookingapp.R;
import abandonedstudio.app.cookingapp.ViewModel.DishViewModel;
import abandonedstudio.app.cookingapp.ViewModel.SharedViewModel;

public class DishFragment extends Fragment {

    private TextView dishNameTextView, preparationTimeTextView;
    private SharedViewModel sharedViewModel;
    private DishViewModel dishViewModel;
    private RecyclerView ingredientsRecyclerView, stepsRecyclerView;
    private ImageButton backToDishListButton;
    private Button goToShoppingListButton;
    private DishIngredientAdapter ingredientAdapter;
    private DishPreparationStepsAdapter stepsAdapter;
    private int dishId;
    private ImageView dishPhotoImageView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dish_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dishNameTextView = view.findViewById(R.id.dish_name_textView);
        preparationTimeTextView = view.findViewById(R.id.preparation_time_textView);
        backToDishListButton = view.findViewById(R.id.back_to_dish_list_from_dish_imageButton);
        ingredientsRecyclerView = view.findViewById(R.id.ingredients_recyclerView);
        stepsRecyclerView = view.findViewById(R.id.preparation_steps_recyclerView);
        goToShoppingListButton = view.findViewById(R.id.shopping_list_button);
        dishPhotoImageView = view.findViewById(R.id.dish_photo_imageView);

        ingredientAdapter = new DishIngredientAdapter();
        stepsAdapter = new DishPreparationStepsAdapter();

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        dishViewModel = new ViewModelProvider(requireActivity()).get(DishViewModel.class);

        Dish dish = sharedViewModel.getDish();
        dishId = dish.getDishId();


        dishNameTextView.setText(dish.getDishName());
        preparationTimeTextView.setText(String.valueOf(dish.getPreparationTime()));
        GlideApp.with(requireContext())
                .load(Uri.parse(dish.getPhotoUriString()))
                .override(dishPhotoImageView.getWidth(), dishPhotoImageView.getHeight())
                .fitCenter()
                .into(dishPhotoImageView);

        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ingredientsRecyclerView.setHasFixedSize(false);
        ingredientsRecyclerView.setAdapter(ingredientAdapter);

        dishViewModel.getAllIngredientsFromDish(dishId).observe(getViewLifecycleOwner(), new Observer<List<Ingredient>>() {
            @Override
            public void onChanged(List<Ingredient> ingredients) {
                ingredientAdapter.setIngredients(ingredients);
            }
        });

        stepsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        stepsRecyclerView.setHasFixedSize(false);
        stepsRecyclerView.setAdapter(stepsAdapter);

        dishViewModel.getAllPreparationStepsFromDish(dishId).observe(getViewLifecycleOwner(), new Observer<List<PreparationStep>>() {
            @Override
            public void onChanged(List<PreparationStep> preparationSteps) {
                stepsAdapter.setPreparationSteps(preparationSteps);
            }
        });

        backToDishListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(DishFragment.this)
                        .navigate(R.id.action_dishFragment_to_dishesListFragment);
            }
        });

        goToShoppingListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(DishFragment.this)
                        .navigate(R.id.action_dishFragment_to_createShoppingListFragment);
            }
        });
    }
}
