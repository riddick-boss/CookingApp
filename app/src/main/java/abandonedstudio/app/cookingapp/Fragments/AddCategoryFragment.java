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
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import abandonedstudio.app.cookingapp.Database.DishCategory;
import abandonedstudio.app.cookingapp.R;
import abandonedstudio.app.cookingapp.ViewModel.AddCategoryViewModel;
import abandonedstudio.app.cookingapp.utils.CloseKeyboard;

public class AddCategoryFragment extends Fragment implements View.OnClickListener {

    private EditText newCategoryEditText;
    private AddCategoryViewModel addCategoryViewModel;
    private CloseKeyboard closeKeyboard;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.new_category_add_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageButton backButton = view.findViewById(R.id.back_to_all_categories_imageButton);
        Button addCategoryButton = view.findViewById(R.id.add_new_category_button);
        newCategoryEditText = view.findViewById(R.id.add_category_editText);

        closeKeyboard = new CloseKeyboard();

        addCategoryViewModel = new ViewModelProvider(requireActivity()).get(AddCategoryViewModel.class);

        backButton.setOnClickListener(this);
        addCategoryButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_to_all_categories_imageButton:
                NavHostFragment.findNavController(AddCategoryFragment.this)
                        .navigate(R.id.action_addCategoryFragment_to_dishCategoryFragment);
                newCategoryEditText.clearFocus();
                closeKeyboard.closeKeyboard(requireActivity());
                break;
            case R.id.add_new_category_button:
                String newCategory = newCategoryEditText.getText().toString().trim();
                if(!newCategory.isEmpty()) {
                    DishCategory newDishCategory = new DishCategory(newCategory);
                    addCategoryViewModel.insert(newDishCategory);
                    closeKeyboard.closeKeyboard(requireActivity());
                    newCategoryEditText.setText(null);
                    newCategoryEditText.clearFocus();
                    //showing on screen center
                    Toast.makeText(getContext(), "Category added", Toast.LENGTH_SHORT).show();
                    NavHostFragment.findNavController(AddCategoryFragment.this)
                            .navigate(R.id.action_addCategoryFragment_to_dishCategoryFragment);
                }
                else{
                    Toast.makeText(getContext(), "Enter category!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /* private void closeKeyboard() {
        View v = getActivity().getCurrentFocus();
        if (v != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    } */
}
