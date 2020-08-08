package abandonedstudio.app.cookingapp.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

import abandonedstudio.app.cookingapp.R;
/*                                                                  TO USE IN FUTURE    !
public class NewCategoryDialog extends DialogFragment {

    private EditText categoryEditText;
    private NewCategoryDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View v = getActivity().getLayoutInflater().inflate(R.layout.new_category_dialog, null);



        builder.setView(v)
                .setTitle(R.string.add_new_category)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String category = categoryEditText.getText().toString();
                        passCategory(category);
                    }
                });

        categoryEditText = v.findViewById(R.id.new_category_editText);

        return builder.create();
    }


} */
