package abandonedstudio.app.cookingapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import abandonedstudio.app.cookingapp.Adapters.DishListAdapter;
import abandonedstudio.app.cookingapp.Database.Dish;
import abandonedstudio.app.cookingapp.R;
import abandonedstudio.app.cookingapp.ViewModel.DishListViewModel;
import abandonedstudio.app.cookingapp.ViewModel.SharedViewModel;

public class DishesListFragment extends Fragment {

    private TextView categoryTextView, dishNameNestedTextView;
    private ImageButton backToAllCategoriesButton, closeNestedLayoutButton;
    private FloatingActionButton addNewDishButton;
    private RecyclerView recyclerView;
    private DishListAdapter adapter;
    private SharedViewModel sharedViewModel;
    private DishListViewModel dishListViewModel;
    private int currentCategoryId;
    private String currentCategoryName;
    private View nestedLayout;
    private Button editDishNestedButton, deleteDishNestedButton;

    /*
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            getParentFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
                @Override
                public void onFragmentResult(@NonNull String key, @NonNull Bundle bundle) {
                    setCategoryPassed(bundle.getString("bundleKey"));
                }
            });
        } */

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dishes_list__fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        categoryTextView = view.findViewById(R.id.header_dish_category_textView);
        backToAllCategoriesButton = view.findViewById(R.id.back_to_categories_from_dish_list_imageButton);
        addNewDishButton = view.findViewById(R.id.add_dish_button);
        recyclerView = view.findViewById(R.id.dishes_list_recyclerView);
        nestedLayout = view.findViewById(R.id.nested_dish_list_layout);
        dishNameNestedTextView = view.findViewById(R.id.dish_name_nested_dish_list_layout_textView);
        editDishNestedButton = view.findViewById(R.id.edit_dish_nested_dish_list_button);
        deleteDishNestedButton = view.findViewById(R.id.delete_dish_nested_dish_list_button);
        closeNestedLayoutButton = view.findViewById(R.id.close_nested_dish_list_layout_button);

        adapter = new DishListAdapter();

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        dishListViewModel = new ViewModelProvider(requireActivity()).get(DishListViewModel.class);

        currentCategoryId = sharedViewModel.getDishCategory().getCategoryId();
        currentCategoryName = sharedViewModel.getDishCategory().getCategory();

        categoryTextView.setText(String.valueOf(currentCategoryName));

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        dishListViewModel.getAllDishesFromCategory(currentCategoryId).observe(getViewLifecycleOwner(), new Observer<List<Dish>>() {
            @Override
            public void onChanged(@Nullable List<Dish> dishes) {
                adapter.setDishes(dishes);
            }
        });

        backToAllCategoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(DishesListFragment.this)
                        .navigate(R.id.action_dishesListFragment_to_dishCategoryFragment);
            }
        });

        addNewDishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //navigate to create new dish fragment
                NavHostFragment.findNavController(DishesListFragment.this)
                        .navigate(R.id.action_dishesListFragment_to_addDishFragment);
            }
        });

        adapter.setOnDishClickListener(new DishListAdapter.OnDishClickListener() {
            @Override
            public void onDishClicked(Dish dish) {
                sharedViewModel.setDish(dish);
                NavHostFragment.findNavController(DishesListFragment.this)
                        .navigate(R.id.action_dishesListFragment_to_dishFragment);
            }

            @Override
            public void onDishLongCLicked(final Dish dish) {
                nestedLayout.setVisibility(View.VISIBLE);
                dishNameNestedTextView.setText(dish.getDishName());

                editDishNestedButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sharedViewModel.setDish(dish);
                        //navigate to edit dish fragment
                        NavHostFragment.findNavController(DishesListFragment.this)
                                .navigate(R.id.action_dishesListFragment_to_editDishFragment);
                    }
                });

                deleteDishNestedButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dishListViewModel.delete(dish);
                        Snackbar.make(v, "Dish deleted", Snackbar.LENGTH_SHORT).show();
                        nestedLayout.setVisibility(View.INVISIBLE);
                    }
                });

                closeNestedLayoutButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nestedLayout.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });
    }
}