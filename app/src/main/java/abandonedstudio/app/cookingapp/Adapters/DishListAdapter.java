package abandonedstudio.app.cookingapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import abandonedstudio.app.cookingapp.Database.Dish;
import abandonedstudio.app.cookingapp.R;

public class DishListAdapter extends RecyclerView.Adapter<DishListAdapter.DishListHolder> {

    private List<Dish> dishes = new ArrayList<>();
    private OnDishClickListener listener;

    @NonNull
    @Override
    public DishListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dish_list, parent, false);
        return new DishListHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DishListHolder holder, int position) {
        Dish currentDish = dishes.get(position);
        holder.dishNameTextView.setText(currentDish.getDishName());
        holder.preparationTimeTextView.setText(String.valueOf(currentDish.getPreparationTime()));
    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
        notifyDataSetChanged();
    }

    class DishListHolder extends RecyclerView.ViewHolder{

        private TextView dishNameTextView;
        private TextView preparationTimeTextView;

        public DishListHolder(@NonNull View itemView) {
            super(itemView);
            dishNameTextView = itemView.findViewById(R.id.dish_name_textView);
            preparationTimeTextView = itemView.findViewById(R.id.preparation_time_list_textView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION){
                        listener.onDishClicked(dishes.get(position));
                    }
                }
            });
        }
    }

    public interface OnDishClickListener{
        void onDishClicked(Dish dish);
    }
    public void setOnDishClickListener(OnDishClickListener listener){
        this.listener = listener;
    }
}
