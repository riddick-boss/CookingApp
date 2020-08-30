package abandonedstudio.app.cookingapp.Dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.NumberPicker;

import androidx.appcompat.app.AlertDialog;

import abandonedstudio.app.cookingapp.R;

public class PreparationTimeNumberPicker {

    private OnPreparationTimeChoseListener listener;

    public PreparationTimeNumberPicker() {
    }

    public void numberPicker(Context context){
        final NumberPicker numberPicker = new NumberPicker(context);
        numberPicker.setMinValue(0); //Minimum value to select
        numberPicker.setMaxValue(500);


        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(numberPicker);
        builder.setTitle(R.string.choose_preparation_time_dialog_title);
        builder.setMessage(R.string.choose_preparation_time_dialog_message);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //getting int from picker
                listener.getPreparationTime(numberPicker.getValue());
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public interface OnPreparationTimeChoseListener{
        void getPreparationTime(int time);
    }

    public void setListener(OnPreparationTimeChoseListener listener){
        this.listener = listener;
    }
}