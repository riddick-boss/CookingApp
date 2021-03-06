package abandonedstudio.app.cookingapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import abandonedstudio.app.cookingapp.Adapters.CreateShoppingListAdapter;
import abandonedstudio.app.cookingapp.R;
import abandonedstudio.app.cookingapp.ViewModel.CreateShoppingListViewModel;
import abandonedstudio.app.cookingapp.ViewModel.SharedViewModel;
import abandonedstudio.app.cookingapp.notifications.ShoppingListNotification;

public class CreateShoppingListFragment extends Fragment {

    private SharedViewModel sharedViewModel;
    private CreateShoppingListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.choose_shopping_list_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView dishNameTextView = view.findViewById(R.id.dish_name_shopping_list_textView);
        RecyclerView recyclerView = view.findViewById(R.id.choose_shopping_list_recyclerView);
        Button okButton = view.findViewById(R.id.ok_shopping_list_button);
        ImageButton goToDishButton = view.findViewById(R.id.back_to_dish_imageButton);
        CheckBox checkAllCheckBox = view.findViewById(R.id.choose_all_checkBox);

        CreateShoppingListViewModel createShoppingListViewModel = new ViewModelProvider(requireActivity()).get(CreateShoppingListViewModel.class);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        adapter = new CreateShoppingListAdapter();

        dishNameTextView.setText(sharedViewModel.getDish().getDishName());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        createShoppingListViewModel.getAllIngredientsFromDish(sharedViewModel.getDish().getDishId()).observe(getViewLifecycleOwner(), ingredients -> adapter.setIngredients(ingredients));

        okButton.setOnClickListener(v -> {
            if(!adapter.getIngredientsToBuy().isEmpty()){
                sharedViewModel.setIngredientsToBuy(adapter.getIngredientsToBuy());
                //start notification service
                ShoppingListNotification notification = new ShoppingListNotification();
                int notificationId= (int) System.currentTimeMillis();
                StringBuilder content = new StringBuilder();
                for (int i=0; i<adapter.getIngredientsToBuy().size(); i++){
                    content.append(adapter.getIngredientsToBuy().get(i));
                    if (i<adapter.getIngredientsToBuy().size()-1){
                        content.append(",").append(System.lineSeparator());
                    }
                }
                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(requireContext());
                notificationManagerCompat.notify(notificationId, notification.buildNotification(requireContext(), sharedViewModel.getDish().getDishName(), content.toString()));
                NavHostFragment.findNavController(CreateShoppingListFragment.this)
                        .navigate(R.id.action_createShoppingListFragment_to_shoppingListFragment);
            }
            else {
                Toast.makeText(getContext(), "Shopping list is empty!", Toast.LENGTH_SHORT).show();
            }
        });

        goToDishButton.setOnClickListener(v -> NavHostFragment.findNavController(CreateShoppingListFragment.this)
                .navigate(R.id.action_createShoppingListFragment_to_dishFragment));

        checkAllCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) adapter.selectAllIngredients();
            else adapter.unselectAllIngredients();
        });
    }
}
