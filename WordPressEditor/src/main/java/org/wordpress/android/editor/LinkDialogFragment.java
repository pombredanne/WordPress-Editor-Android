package org.wordpress.android.editor;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class LinkDialogFragment extends DialogFragment {

    public static final int LINK_DIALOG_REQUEST_CODE = 1;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.link_dialog, null);

        final EditText urlEditText = (EditText) view.findViewById(R.id.linkURL);
        final EditText linkEditText = (EditText) view.findViewById(R.id.linkText);

        builder.setView(view)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent();
                        intent.putExtra("linkURL", urlEditText.getText().toString());
                        intent.putExtra("linkText", linkEditText.getText().toString());
                        getTargetFragment().onActivityResult(getTargetRequestCode(), LINK_DIALOG_REQUEST_CODE, intent);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        LinkDialogFragment.this.getDialog().cancel();
                    }
                });

        // Prepare initial state of EditTexts
        urlEditText.setSelection(7);

        Bundle bundle = getArguments();
        if (bundle != null) {
            linkEditText.setText(bundle.getString("linkText"));
        }

        return builder.create();
    }
}
