package com.example.hostelmanagementsystem;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

public class ExampleDialog extends DialogFragment {

    public static final String TAG = "example_dialog";

    private Toolbar toolbar;



    public static ExampleDialog display(FragmentManager fragmentManager) {
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(fragmentManager, TAG);
        return exampleDialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_FullScreenDialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
            dialog.getWindow().setWindowAnimations(R.style.AppTheme_Slide);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.student_information_dialogue, container, false);

        toolbar = view.findViewById(R.id.toolbar);






        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setTitle("SStudent Information");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        TextView name , Class ,roll , section , adress , ph , paymentMonth , doa , postID  ;

        name = view.findViewById(R.id.nameOfdialouge);
        Class = view.findViewById(R.id.calssOfdialouge);
        roll= view.findViewById(R.id.rollOfDialogue);
        section= view.findViewById(R.id.secOfdialouge);
        adress= view.findViewById(R.id.adressOfdialouge);
        ph = view.findViewById(R.id.phOfdialouge);
        paymentMonth= view.findViewById(R.id.paymentDialogue);
        doa = view.findViewById(R.id.doaOfdialouge);
        postID= view.findViewById(R.id.postIDOfdialouge);

        String id = getArguments().getString("id");
        doa.setText(getArguments().getString("DOA"));
        name.setText(getArguments().getString("NAME"));
        Class.setText(getArguments().getString("CLASS"));
        roll.setText(getArguments().getString("ROLL"));
        section.setText(getArguments().getString("SECTION"));
        adress.setText(getArguments().getString("ADDRESS"));
        paymentMonth.setText(getArguments().getString("PAYMENTLIST"));
        ph.setText(getArguments().getString("PH"));



    }








}

