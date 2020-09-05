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


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ready_shopping_list_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView dishNameTextView = view.findViewById(R.id.dish_name_shopping_list_textView);
        RecyclerView recyclerView = view.findViewById(R.id.shopping_list_recyclerView);
        ImageButton goToChooseShoppingList = view.findViewById(R.id.back_to_choose_shopping_list_imageButton);
        Button cookButton = view.findViewById(R.id.go_to_dish_button);

        ShoppingListAdapter adapter = new ShoppingListAdapter();

        SharedViewModel sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        dishNameTextView.setText(sharedViewModel.getDish().getDishName());

        adapter.setIngredientsToBuy(sharedViewModel.getIngredientsToBuy());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        goToChooseShoppingList.setOnClickListener(v -> NavHostFragment.findNavController(ShoppingListFragment.this)
                .navigate(R.id.action_shoppingListFragment_to_createShoppingListFragment));

        cookButton.setOnClickListener(v -> {
            //stop notification service
            NavHostFragment.findNavController(ShoppingListFragment.this)
                    .navigate(R.id.action_shoppingListFragment_to_dishFragment);
        });
    }
}
