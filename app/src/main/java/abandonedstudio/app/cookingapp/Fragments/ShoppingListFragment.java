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
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import abandonedstudio.app.cookingapp.Adapters.ShoppingListAdapter;
import abandonedstudio.app.cookingapp.R;
import abandonedstudio.app.cookingapp.ViewModel.SharedViewModel;

public class ShoppingListFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView dishNameTextView;
    private ImageButton goToChooseShoppingList;
    private Button cookButton;
    private SharedViewModel sharedViewModel;
    private ShoppingListAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ready_shopping_list_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dishNameTextView = view.findViewById(R.id.dish_name_shopping_list_textView);
        recyclerView = view.findViewById(R.id.shopping_list_recyclerView);
        goToChooseShoppingList = view.findViewById(R.id.back_to_choose_shopping_list_imageButton);
        cookButton = view.findViewById(R.id.go_to_dish_button);

        adapter = new ShoppingListAdapter();

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        dishNameTextView.setText(sharedViewModel.getDishName());

        adapter.setIngredientsToBuy(sharedViewModel.getIngredientsToBuy());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        goToChooseShoppingList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(ShoppingListFragment.this)
                        .navigate(R.id.action_shoppingListFragment_to_createShoppingListFragment);
            }
        });

        cookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //stop notification service
                NavHostFragment.findNavController(ShoppingListFragment.this)
                        .navigate(R.id.action_shoppingListFragment_to_dishFragment);
            }
        });
    }
}
