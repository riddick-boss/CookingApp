package abandonedstudio.app.cookingapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import abandonedstudio.app.cookingapp.Database.PreparationStep;
import abandonedstudio.app.cookingapp.R;

public class DishPreparationStepsAdapter extends RecyclerView.Adapter<DishPreparationStepsAdapter.DishStepsHolder> {

    List<PreparationStep> preparationSteps = new ArrayList<>();

    @NonNull
    @Override
    public DishStepsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dish_steps, parent, false);
        return new DishStepsHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DishStepsHolder holder, int position) {
        PreparationStep currentStep = preparationSteps.get(position);
        holder.stepNumberTextView.setText(String.valueOf(currentStep.getStepNumber()));
        holder.stepDescriptionTextView.setText(currentStep.getStepDescription());
    }

    @Override
    public int getItemCount() {
        return preparationSteps.size();
    }

    public void setPreparationSteps(List<PreparationStep> preparationSteps) {
        this.preparationSteps = preparationSteps;
        notifyDataSetChanged();
    }

    public static class DishStepsHolder extends RecyclerView.ViewHolder{

        private TextView stepNumberTextView, stepDescriptionTextView;

        public DishStepsHolder(@NonNull View itemView) {
            super(itemView);
            stepNumberTextView = itemView.findViewById(R.id.step_number_dish_textView);
            stepDescriptionTextView = itemView.findViewById(R.id.step_description_dish_textView);
        }
    }
}
