package abandonedstudio.app.cookingapp.Adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import abandonedstudio.app.cookingapp.Database.Dish;
import abandonedstudio.app.cookingapp.GlideModule.GlideApp;
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
        //set image in list properly
        GlideApp.with(holder.itemView.getContext())
                .load(Uri.parse(currentDish.getPhotoUriString()))
                .override(holder.dishPhotoImageView.getWidth(), holder.dishPhotoImageView.getHeight())
                .fitCenter()
                .into(holder.dishPhotoImageView);
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

        private TextView dishNameTextView, preparationTimeTextView;
        private ImageView dishPhotoImageView;

        public DishListHolder(@NonNull View itemView) {
            super(itemView);
            dishNameTextView = itemView.findViewById(R.id.dish_name_textView);
            preparationTimeTextView = itemView.findViewById(R.id.preparation_time_list_textView);
            dishPhotoImageView = itemView.findViewById(R.id.item_dish_photo_imageView);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if(listener != null && position != RecyclerView.NO_POSITION){
                    listener.onDishClicked(dishes.get(position));
                }
            });

            itemView.setOnLongClickListener(v -> {
                int position = getAdapterPosition();
                if(listener != null && position != RecyclerView.NO_POSITION){
                    listener.onDishLongCLicked(dishes.get(position));
                }
                return true;
            });
        }
    }

    public interface OnDishClickListener{
        void onDishClicked(Dish dish);
        void onDishLongCLicked(Dish dish);
    }
    public void setOnDishClickListener(OnDishClickListener listener){
        this.listener = listener;
    }
}
