package abandonedstudio.app.cookingapp.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "preparation_steps", foreignKeys = @ForeignKey(entity = Dish.class, parentColumns = "dishId", childColumns = "dish_id", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE))
public class PreparationStep {

    public PreparationStep(int stepNumber, String stepDescription, int dishId) {
        this.stepNumber = stepNumber;
        this.stepDescription = stepDescription;
        this.dishId = dishId;
    }

    @PrimaryKey(autoGenerate = true)
    private int stepId;

    @ColumnInfo(name = "step_number")
    private int stepNumber;

    @ColumnInfo(name = "step_description")
    private String stepDescription;

    @ColumnInfo(name = "dish_id")
    private int dishId;

    public int getStepId() {
        return stepId;
    }

    public void setStepId(int stepId) {
        this.stepId = stepId;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public String getStepDescription() {
        return stepDescription;
    }

    public int getDishId() {
        return dishId;
    }
}
