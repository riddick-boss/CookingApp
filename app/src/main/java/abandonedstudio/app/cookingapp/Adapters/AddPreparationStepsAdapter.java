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

public class AddPreparationStepsAdapter extends RecyclerView.Adapter<AddPreparationStepsAdapter.AddStepHolder> {

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
        if(preparationSteps.size() == 0){
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

    public class AddStepHolder extends RecyclerView.ViewHolder {

        private TextView stepDescriptionTextView, stepNumberTextView;
        private EditText nestedStepEditText;
        private View nestedLayout;
        private ImageButton changeStepButton;

        public AddStepHolder(@NonNull View itemView) {
            super(itemView);
            stepDescriptionTextView = itemView.findViewById(R.id.step_description_dish_textView);
            stepNumberTextView = itemView.findViewById(R.id.step_number_dish_textView);
            nestedStepEditText = itemView.findViewById(R.id.nested_preparation_step_editText);
            nestedLayout = itemView.findViewById(R.id.nested_item_preparation_step_layout);
            changeStepButton = itemView.findViewById(R.id.nested_item_preparation_step_add_button);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nestedLayout.setVisibility(View.VISIBLE);
                    nestedStepEditText.setText(stepDescriptionTextView.getText());
                }
            });

            changeStepButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String newStep = nestedStepEditText.getText().toString().trim();

                    //updating step at specified index
                    if(!newStep.isEmpty()){
                        preparationSteps.set(getAdapterPosition(), newStep);
                        notifyDataSetChanged();
                    }
                    nestedLayout.setVisibility(View.VISIBLE);
                }
            });
        }
    }
}
