package abandonedstudio.app.cookingapp.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class PreparationStepMoveCallback extends ItemTouchHelper.Callback {

    private ItemTouchHelperListener myAdapter;

    public PreparationStepMoveCallback(ItemTouchHelperListener myAdapter) {
        this.myAdapter = myAdapter;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE){
            if (viewHolder instanceof AddPreparationStepsAdapter.AddStepHolder){
                AddPreparationStepsAdapter.AddStepHolder myViewHolder =
                        (AddPreparationStepsAdapter.AddStepHolder) viewHolder;
                myAdapter.onRowSelected(myViewHolder);
            }
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        return makeMovementFlags(dragFlags, 0);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        myAdapter.onRowMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        if (viewHolder instanceof AddPreparationStepsAdapter.AddStepHolder){
            AddPreparationStepsAdapter.AddStepHolder myViewHolder =
                    (AddPreparationStepsAdapter.AddStepHolder) viewHolder;
            myAdapter.onRowClear(myViewHolder);
        }
    }

    public interface ItemTouchHelperListener{
        void onRowMoved(int fromPosition, int toPosition);
        void onRowSelected(AddPreparationStepsAdapter.AddStepHolder myViewHolder);
        void onRowClear(AddPreparationStepsAdapter.AddStepHolder myViewHolder);
    }
}
