package abandonedstudio.app.cookingapp.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.List;

import abandonedstudio.app.cookingapp.Adapters.DishListAdapter;
import abandonedstudio.app.cookingapp.Database.Dish;
import abandonedstudio.app.cookingapp.R;
import abandonedstudio.app.cookingapp.ViewModel.DishListViewModel;
import abandonedstudio.app.cookingapp.ViewModel.SharedViewModel;

public class DishesListFragment extends Fragment {

    private TextView categoryTextView;
    private ImageButton backToAllCategoriesButton;
    private FloatingActionButton addNewDishButton;
    private RecyclerView recyclerView;
    private DishListAdapter adapter;
    private SharedViewModel sharedViewModel;
    private DishListViewModel dishListViewModel;
    private int currentCategoryId;
    private String currentCategoryName;

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
        adapter = new DishListAdapter();

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        dishListViewModel = new ViewModelProvider(requireActivity()).get(DishListViewModel.class);

        currentCategoryId = sharedViewModel.getDishCategory().getCategoryId();
        currentCategoryName = sharedViewModel.getCategoryName();

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

        adapter.setOnDishClickListener(new DishListAdapter.OnDishClickListener() {
            @Override
            public void onDishClicked(Dish dish) {
                sharedViewModel.setDishId(dish.getDishId());
                sharedViewModel.setDishName(dish.getDishName());
                sharedViewModel.setDish(dish);
                // Log.d("TAG", String.valueOf(sharedViewModel.getDish().getDishId()));
                NavHostFragment.findNavController(DishesListFragment.this)
                        .navigate(R.id.action_dishesListFragment_to_dishFragment);
            }
        });
    }
}