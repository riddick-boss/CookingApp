package abandonedstudio.app.cookingapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import abandonedstudio.app.cookingapp.R;

public class AddDishIngredientsAdapter extends RecyclerView.Adapter<AddDishIngredientsAdapter.AddIngredientHolder> {

    private ArrayList<String> ingredients = new ArrayList<>();
    private IngredientsArrayPasser listener;

    @NonNull
    @Override
    public AddIngredientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dish_ingredients, parent, false);
        return new AddIngredientHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AddIngredientHolder holder, int position) {
        String currentIngredient = ingredients.get(position);
        holder.ingredientTextView.setText(currentIngredient);
        holder.nestedChangeIngredientEditText.setText(currentIngredient);
    }

    @Override
    public int getItemCount() {
        if(ingredients == null || ingredients.size() == 0){
            return 0;
        }
        return ingredients.size();
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
        notifyDataSetChanged();
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void addIngredient(String ingredient){
        ingredients.add(ingredient);
        notifyDataSetChanged();
    }

    public class AddIngredientHolder extends RecyclerView.ViewHolder {

        private TextView ingredientTextView;
        private EditText nestedChangeIngredientEditText;
        private View nestedLayout;

        public AddIngredientHolder(@NonNull final View itemView) {
            super(itemView);
            ingredientTextView = itemView.findViewById(R.id.ingredient_list_dish_textView);
            nestedChangeIngredientEditText = itemView.findViewById(R.id.nested_item_ingredient_add_ingredient_editText);
            ImageButton changeIngredientButton = itemView.findViewById(R.id.nested_item_ingredient_add_ingredient_button);
            nestedLayout = itemView.findViewById(R.id.nested_item_ingredient_layout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nestedLayout.setVisibility(View.VISIBLE);
                    nestedChangeIngredientEditText.setText(ingredientTextView.getText());
                }
            });

            changeIngredientButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String newIngredient = nestedChangeIngredientEditText.getText().toString().trim();
                    //updating ingredients array
                    //ingredients.remove(getAdapterPosition());
                    //ingredients.add(newIngredient);
                    //updating ingredient at specified index
                    if(!newIngredient.isEmpty()){
                        ingredients.set(getAdapterPosition(), newIngredient);
                        notifyDataSetChanged();
                    }
                    nestedLayout.setVisibility(View.INVISIBLE);
                }
            });
        }
    }

    public interface IngredientsArrayPasser{
        void PassIngredients(ArrayList<String> arrayList);
    }

    public void setIngredientsArrayPasserListener(IngredientsArrayPasser listener){
        this.listener = listener;
    }
}
