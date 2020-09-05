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
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import abandonedstudio.app.cookingapp.Adapters.DishIngredientAdapter;
import abandonedstudio.app.cookingapp.Adapters.DishPreparationStepsAdapter;
import abandonedstudio.app.cookingapp.Database.Dish;
import abandonedstudio.app.cookingapp.GlideModule.GlideApp;
import abandonedstudio.app.cookingapp.R;
import abandonedstudio.app.cookingapp.ViewModel.DishViewModel;
import abandonedstudio.app.cookingapp.ViewModel.SharedViewModel;

public class DishFragment extends Fragment {

    private DishIngredientAdapter ingredientAdapter;
    private DishPreparationStepsAdapter stepsAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dish_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView dishNameTextView = view.findViewById(R.id.dish_name_textView);
        TextView preparationTimeTextView = view.findViewById(R.id.preparation_time_textView);
        ImageButton backToDishListButton = view.findViewById(R.id.back_to_dish_list_from_dish_imageButton);
        RecyclerView ingredientsRecyclerView = view.findViewById(R.id.ingredients_recyclerView);
        RecyclerView stepsRecyclerView = view.findViewById(R.id.preparation_steps_recyclerView);
        Button goToShoppingListButton = view.findViewById(R.id.shopping_list_button);
        ImageView dishPhotoImageView = view.findViewById(R.id.dish_photo_imageView);

        ingredientAdapter = new DishIngredientAdapter();
        stepsAdapter = new DishPreparationStepsAdapter();

        SharedViewModel sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        DishViewModel dishViewModel = new ViewModelProvider(requireActivity()).get(DishViewModel.class);

        Dish dish = sharedViewModel.getDish();
        int dishId = dish.getDishId();


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

        dishViewModel.getAllIngredientsFromDish(dishId).observe(getViewLifecycleOwner(), ingredients -> ingredientAdapter.setIngredients(ingredients));

        stepsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        stepsRecyclerView.setHasFixedSize(false);
        stepsRecyclerView.setAdapter(stepsAdapter);

        dishViewModel.getAllPreparationStepsFromDish(dishId).observe(getViewLifecycleOwner(), preparationSteps -> stepsAdapter.setPreparationSteps(preparationSteps));

        backToDishListButton.setOnClickListener(v -> NavHostFragment.findNavController(DishFragment.this)
                .navigate(R.id.action_dishFragment_to_dishesListFragment));

        goToShoppingListButton.setOnClickListener(v -> NavHostFragment.findNavController(DishFragment.this)
                .navigate(R.id.action_dishFragment_to_createShoppingListFragment));
    }
}
