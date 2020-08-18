package abandonedstudio.app.cookingapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import abandonedstudio.app.cookingapp.R;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ShoppingListHolder> {

    private List<String> ingredientsToBuy = new ArrayList<>();

    @NonNull
    @Override
    public ShoppingListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopping_list, parent, false);
        return new ShoppingListHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingListHolder holder, int position) {
        holder.ingredientTextView.setText(ingredientsToBuy.get(position));
    }

    @Override
    public int getItemCount() {
        return ingredientsToBuy.size();
    }

    public void setIngredientsToBuy(List<String> ingredientsToBuy) {
        this.ingredientsToBuy = ingredientsToBuy;
    }

    public static class ShoppingListHolder extends RecyclerView.ViewHolder{

        private TextView ingredientTextView;

        public ShoppingListHolder(@NonNull View itemView) {
            super(itemView);
            ingredientTextView = itemView.findViewById(R.id.ingredient_shopping_list_item_textView);
        }
    }
}
