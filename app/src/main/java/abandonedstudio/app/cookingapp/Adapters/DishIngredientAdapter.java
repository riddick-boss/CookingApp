package abandonedstudio.app.cookingapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import abandonedstudio.app.cookingapp.Database.Ingredient;
import abandonedstudio.app.cookingapp.R;

public class DishIngredientAdapter extends RecyclerView.Adapter<DishIngredientAdapter.DishIngredientHolder> {

    private List<Ingredient> ingredients = new ArrayList<>();

    @NonNull
    @Override
    public DishIngredientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dish_ingredients, parent, false);
        return new DishIngredientHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DishIngredientHolder holder, int position) {
        Ingredient currentIngredient = ingredients.get(position);
        holder.ingredientTextView.setText(currentIngredient.getIngredient());
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        notifyDataSetChanged();
    }

    public static class DishIngredientHolder extends RecyclerView.ViewHolder{

        private TextView ingredientTextView;

        public DishIngredientHolder(@NonNull View itemView) {
            super(itemView);
            ingredientTextView = itemView.findViewById(R.id.ingredient_list_dish_textView);
        }
    }
}
