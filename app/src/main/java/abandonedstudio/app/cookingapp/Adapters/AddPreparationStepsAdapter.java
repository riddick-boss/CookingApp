package abandonedstudio.app.cookingapp.Adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

import abandonedstudio.app.cookingapp.R;

public class AddPreparationStepsAdapter extends RecyclerView.Adapter<AddPreparationStepsAdapter.AddStepHolder> implements PreparationStepMoveCallback.ItemTouchHelperListener{

    private ArrayList<String> preparationSteps = new ArrayList<>();

    @NonNull
    @Override
    public AddStepHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dish_steps, parent, false);
        return new AddStepHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AddStepHolder holder, int position) {
        String currentStep = preparationSteps.get(position);
        holder.stepDescriptionTextView.setText(currentStep);
        holder.stepNumberTextView.setText(String.valueOf(position+1));
    }

    @Override
    public int getItemCount() {
        if(preparationSteps == null || preparationSteps.size() == 0){
            return 0;
        }
        return preparationSteps.size();
    }



    public ArrayList<String> getPreparationSteps() {
        return preparationSteps;
    }

    public void setPreparationSteps(ArrayList<String> preparationSteps) {
        this.preparationSteps = preparationSteps;
        notifyDataSetChanged();
    }

    public void addPreparationStep(String preparationStep){
        preparationSteps.add(preparationStep);
        notifyDataSetChanged();
    }

    @Override
    public void onRowMoved(int fromPosition, int toPosition) {
        int i;
        if (fromPosition < toPosition){
            for (i = fromPosition; i<toPosition; i++){
                Collections.swap(preparationSteps, i, i+1);
            }
        }
        else {
            for (i = fromPosition; i > toPosition; i--) {
                Collections.swap(preparationSteps, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onRowSelected(AddStepHolder myViewHolder) {
        myViewHolder.itemView.setBackgroundColor(Color.GRAY);
    }

    @Override
    public void onRowClear(AddStepHolder myViewHolder) {
        myViewHolder.itemView.setBackgroundColor(Color.TRANSPARENT);
        notifyDataSetChanged();
    }

    public class AddStepHolder extends RecyclerView.ViewHolder {

        private TextView stepDescriptionTextView, stepNumberTextView;
        private EditText nestedStepEditText;
        private View nestedLayout;

        public AddStepHolder(@NonNull View itemView) {
            super(itemView);
            stepDescriptionTextView = itemView.findViewById(R.id.step_description_dish_textView);
            stepNumberTextView = itemView.findViewById(R.id.step_number_dish_textView);
            nestedStepEditText = itemView.findViewById(R.id.nested_preparation_step_editText);
            nestedLayout = itemView.findViewById(R.id.nested_item_preparation_step_layout);
            ImageButton changeStepButton = itemView.findViewById(R.id.nested_item_preparation_step_add_button);
            ImageButton removeStepButton = itemView.findViewById(R.id.nested_item_preparation_step_remove_button);

            itemView.setOnClickListener(v -> {
                nestedLayout.setVisibility(View.VISIBLE);
                nestedStepEditText.setText(stepDescriptionTextView.getText());
            });

            changeStepButton.setOnClickListener(v -> {
                String newStep = nestedStepEditText.getText().toString().trim();

                //updating step at specified index
                if(!newStep.isEmpty()){
                    preparationSteps.set(getAdapterPosition(), newStep);
                    notifyDataSetChanged();
                }
                nestedLayout.setVisibility(View.INVISIBLE);
            });

            //removing step with animation
            removeStepButton.setOnClickListener(view -> {
                removeStep(getAdapterPosition());
                nestedLayout.setVisibility(View.INVISIBLE);
            });
        }

        private void removeStep(int position) {
            preparationSteps.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, preparationSteps.size());
        }

    }
}
