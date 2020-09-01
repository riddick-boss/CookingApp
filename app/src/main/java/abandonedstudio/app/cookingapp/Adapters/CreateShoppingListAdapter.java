package abandonedstudio.app.cookingapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import abandonedstudio.app.cookingapp.Database.Ingredient;
import abandonedstudio.app.cookingapp.R;

public class CreateShoppingListAdapter extends RecyclerView.Adapter<CreateShoppingListAdapter.CreateShoppingListHolder> {

    private List<Ingredient> ingredients = new ArrayList<>();
    private List<String> ingredientsToBuy = new ArrayList<>();
    private boolean isCheckedAll = false;

    @NonNull
    @Override
    public CreateShoppingListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_choose_shopping_list, parent, false);
        return new CreateShoppingListHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CreateShoppingListHolder holder, int position) {
        final Ingredient currentIngredient = ingredients.get(position);
        holder.ingredientTextView.setText(currentIngredient.getIngredient());
        //create shopping list from ingredients which are checked to buy
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    ingredientsToBuy.add(currentIngredient.getIngredient());
                }
                else {
                    ingredientsToBuy.remove(currentIngredient.getIngredient());
                }
            }
        });
        if(isCheckedAll){
            holder.checkBox.setChecked(true);
        }
        if (!isCheckedAll){
            holder.checkBox.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public void selectAllIngredients(){
        isCheckedAll = true;
        notifyDataSetChanged();
    }

    public void unselectAllIngredients(){
        isCheckedAll = false;
        notifyDataSetChanged();
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        notifyDataSetChanged();
    }

    public List<String> getIngredientsToBuy() {
        return ingredientsToBuy;
    }

    public static class CreateShoppingListHolder extends RecyclerView.ViewHolder {

        private TextView ingredientTextView;
        private CheckBox checkBox;

        public CreateShoppingListHolder(@NonNull View itemView) {
            super(itemView);
            ingredientTextView = itemView.findViewById(R.id.ingredient_choose_shopping_list_item_textView);
            checkBox = itemView.findViewById(R.id.shopping_list_checkBox);
        }
    }
}
