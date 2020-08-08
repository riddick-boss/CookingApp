package abandonedstudio.app.cookingapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import abandonedstudio.app.cookingapp.Database.DishCategory;
import abandonedstudio.app.cookingapp.R;

public class DishCategoryAdapter extends RecyclerView.Adapter<DishCategoryAdapter.DishCategoryHolder> {

    private List<DishCategory> dishCategories = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public DishCategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dish_category, parent, false);
        return new DishCategoryHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DishCategoryHolder holder, int position) {
        DishCategory currentCategory = dishCategories.get(position);
        holder.dishCategoryTextView.setText(currentCategory.getCategory());
    }

    @Override
    public int getItemCount() {
        return dishCategories.size();
    }

    public void setDishCategories(List<DishCategory> dishCategories){
        this.dishCategories = dishCategories;
        notifyDataSetChanged();
    }

    class DishCategoryHolder extends RecyclerView.ViewHolder{

            private TextView dishCategoryTextView;

            public DishCategoryHolder(@NonNull View itemView) {
                super(itemView);
                dishCategoryTextView = itemView.findViewById(R.id.dish_category_textView);

                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int position = getAdapterPosition();
                        if (listener != null && position != RecyclerView.NO_POSITION) {
                            listener.onItemLongClick(dishCategories.get(position));
                        }
                        return true;
                    }
                });

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        if (listener != null && position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(dishCategories.get(position));
                        }
                    }
                });

                /* itemView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        Handler handler = new Handler();
                        if(event.getAction() == MotionEvent.ACTION_DOWN){
                            isLongPress=true;
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if(isLongPress) {
                                        if (listener != null && position != RecyclerView.NO_POSITION) {
                                            position = getAdapterPosition();
                                            listener.onItemClick(dishCategories.get(position));
                                            position = RecyclerView.NO_POSITION;
                                        }
                                    }
                                }
                            }, 5000);
                        }
                        else if(event.getAction() == MotionEvent.ACTION_UP){
                            handler.removeCallbacksAndMessages(null);
                            position = RecyclerView.NO_POSITION;
                            isLongPress=false;
                        }
                        return true;
                    } */

                    /* @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        if (listener != null && position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(dishCategories.get(position));
                        }
                    }
                }); */
            }
        }

    public interface OnItemClickListener {
        void onItemLongClick(DishCategory dishCategory);
        void onItemClick(DishCategory dishCategory);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
