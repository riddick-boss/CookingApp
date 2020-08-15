package abandonedstudio.app.cookingapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import abandonedstudio.app.cookingapp.Database.Dish;
import abandonedstudio.app.cookingapp.R;
import abandonedstudio.app.cookingapp.ViewModel.SharedViewModel;

public class DishFragment extends Fragment {

    private TextView dishNameTextView, preparationTimeTextView;
    private SharedViewModel sharedViewModel;

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

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        dishNameTextView.setText(sharedViewModel.getDishName());

    }
}
