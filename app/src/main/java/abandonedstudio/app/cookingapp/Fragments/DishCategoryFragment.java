package abandonedstudio.app.cookingapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import abandonedstudio.app.cookingapp.Adapters.DishCategoryAdapter;
import abandonedstudio.app.cookingapp.CloseKeyboard;
import abandonedstudio.app.cookingapp.Database.DishCategory;
import abandonedstudio.app.cookingapp.R;
import abandonedstudio.app.cookingapp.ViewModel.DishCategoryViewModel;
import abandonedstudio.app.cookingapp.ViewModel.SharedViewModel;

public class DishCategoryFragment extends Fragment {

    private DishCategoryViewModel dishCategoryViewModel;
    private SharedViewModel sharedViewModel;
    private EditText categoryNameEditText;
    private Button deleteCategoryButton;
    private ImageButton changeCategoryNameButton;
    private View nestedLayout;
    private CloseKeyboard closeKeyboard;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dish_category_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton addNewCategoryButton = view.findViewById(R.id.add_dish_category_button);
        ImageButton closeNestedLayoutButton = view.findViewById(R.id.close_nested_layout_button);
        RecyclerView recyclerView = view.findViewById(R.id.dish_category_recyclerView);
        final DishCategoryAdapter adapter = new DishCategoryAdapter();
        categoryNameEditText = view.findViewById(R.id.nested_category_name_editText);
        changeCategoryNameButton = view.findViewById(R.id.nested_change_category_name_button);
        deleteCategoryButton = view.findViewById(R.id.nested_delete_category_button);
        nestedLayout = view.findViewById(R.id.nested_dish_category_layout);

        dishCategoryViewModel = new ViewModelProvider(requireActivity()).get(DishCategoryViewModel.class);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        /* new ViewModelProvider(this,
                new ViewModelProvider.AndroidViewModelFactory(this.getApplication()))
                .get(DishCategoryViewModel.class); */
        closeKeyboard = new CloseKeyboard();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        dishCategoryViewModel.getAllCategories().observe(getViewLifecycleOwner(), new Observer<List<DishCategory>>() {
            @Override
            public void onChanged(@Nullable List<DishCategory> notes) {
                adapter.setDishCategories(notes);
            }
        });

        addNewCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(DishCategoryFragment.this)
                       .navigate(R.id.action_dishCategoryFragment_to_addCategoryFragment);
            }
        });

        adapter.setOnItemClickListener(new DishCategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemLongClick(final DishCategory aDishCategory) {
                nestedLayout.setVisibility(View.VISIBLE);
                categoryNameEditText.setText(aDishCategory.getCategory());
                // DishCategory helpDishCategory = new DishCategory(aDishCategory.getCategory());
                final int currentId = aDishCategory.getCategoryId();

                changeCategoryNameButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String updatedDishCategoryName = categoryNameEditText.getText().toString().trim();
                        if(!updatedDishCategoryName.isEmpty() && !updatedDishCategoryName.equals(aDishCategory.getCategory())){
                            DishCategory currentDishCategory = new DishCategory(updatedDishCategoryName);
                            currentDishCategory.setCategoryId(currentId);
                            dishCategoryViewModel.update(currentDishCategory);
                            closeKeyboard.closeKeyboard(requireActivity());
                            categoryNameEditText.setText(null);
                            categoryNameEditText.clearFocus();
                            nestedLayout.setVisibility(View.INVISIBLE);
                            Toast.makeText(getContext(), "Category name updated", Toast.LENGTH_SHORT).show();
                        }
                        else if(updatedDishCategoryName.equals(aDishCategory.getCategory())){
                            Toast.makeText(getContext(), "Change category name before!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                deleteCategoryButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dishCategoryViewModel.delete(aDishCategory);
                        Snackbar.make(v, "Category deleted", Snackbar.LENGTH_SHORT).show();
                        nestedLayout.setVisibility(View.INVISIBLE);

                        /* Bundle result = new Bundle();
                        result.putString("bundleKey", "result");
                        getParentFragmentManager().setFragmentResult("requestKey", result); */

                    }
                });
            }

            @Override
            public void onItemClick(DishCategory bDishCategory) {
                sharedViewModel.setCategoryId(bDishCategory.getCategoryId());
                sharedViewModel.setCategoryName(bDishCategory.getCategory());
                sharedViewModel.setDishCategory(bDishCategory);
                NavHostFragment.findNavController(DishCategoryFragment.this)
                        .navigate(R.id.action_dishCategoryFragment_to_dishesListFragment);
            }
        });

        closeNestedLayoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nestedLayout.setVisibility(View.INVISIBLE);
            }
        });
    }


    /*
    private void openNewCategoryPicker(){
        //NewCategoryDialog newCategoryDialog = new NewCategoryDialog();
        // newCategoryDialog.show(getFragmentManager(), "new category dialog");
    }

    @Override
    public void passCategory(String category){
        DishCategory newDishCategory = new DishCategory(category);
        dishCategoryViewModel.insert(newDishCategory);
        Toast.makeText(getContext(), "Category added", Toast.LENGTH_SHORT).show();
    } */
}
