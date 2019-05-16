package com.example.calculator;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.widget.Toast;

/*Dialog box that pops up when the user clicks "Add Person"
Contains two buttons for the option to use the camera or the image gallery
*/
public class CameraDialog extends AppCompatDialogFragment {

    public CameraDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Add A Person")
                .setMessage("How do you want to take the picture?")
                .setNeutralButton("From Camera", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onCameraClicked();
                    }
                })
                .setPositiveButton("From Gallery", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast toast = Toast.makeText(getContext(),
                                "Make sure to choose 'Gallery' and not 'Photos'",
                                Toast.LENGTH_LONG);
                        toast.show();

                        listener.onGalleryClicked();
                    }
                });
        return builder.create();
    }

    public interface CameraDialogListener{
        void onCameraClicked();
        void onGalleryClicked();
    }

    @Override
    public void onAttach (Context context){
        super.onAttach(context);

        try {
            listener = (CameraDialogListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "Must implement listener");
        }
    }
}
