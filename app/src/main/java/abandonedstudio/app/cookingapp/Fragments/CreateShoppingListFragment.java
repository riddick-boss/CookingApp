package abandonedstudio.app.cookingapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import abandonedstudio.app.cookingapp.Adapters.CreateShoppingListAdapter;
import abandonedstudio.app.cookingapp.Database.Ingredient;
import abandonedstudio.app.cookingapp.R;
import abandonedstudio.app.cookingapp.ViewModel.CreateShoppingListViewModel;
import abandonedstudio.app.cookingapp.ViewModel.SharedViewModel;

public class CreateShoppingListFragment extends Fragment {

    private CreateShoppingListViewModel createShoppingListViewModel;
    private SharedViewModel sharedViewModel;
    private RecyclerView recyclerView;
    private TextView dishNameTextView;
    private CreateShoppingListAdapter adapter;
    private Button okButton;
    private ImageButton goToDishButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.choose_shopping_list_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dishNameTextView = view.findViewById(R.id.dish_name_shopping_list_textView);
        recyclerView = view.findViewById(R.id.choose_shopping_list_recyclerView);
        okButton = view.findViewById(R.id.ok_shopping_list_button);
        goToDishButton = view.findViewById(R.id.back_to_dish_imageButton);

        createShoppingListViewModel = new ViewModelProvider(requireActivity()).get(CreateShoppingListViewModel.class);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        adapter = new CreateShoppingListAdapter();

        dishNameTextView.setText(sharedViewModel.getDishName());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        createShoppingListViewModel.getAllIngredientsFromDish(sharedViewModel.getDishId()).observe(getViewLifecycleOwner(), new Observer<List<Ingredient>>() {
            @Override
            public void onChanged(List<Ingredient> ingredients) {
                adapter.setIngredients(ingredients);
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!adapter.getIngredientsToBuy().isEmpty()){
                    sharedViewModel.setIngredientsToBuy(adapter.getIngredientsToBuy());
                    //start notification service
                    NavHostFragment.findNavController(CreateShoppingListFragment.this)
                            .navigate(R.id.action_createShoppingListFragment_to_shoppingListFragment);
                }
                else {
                    Toast.makeText(getContext(), "Shopping list is empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        goToDishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(CreateShoppingListFragment.this)
                        .navigate(R.id.action_createShoppingListFragment_to_dishFragment);
            }
        });
    }
}
