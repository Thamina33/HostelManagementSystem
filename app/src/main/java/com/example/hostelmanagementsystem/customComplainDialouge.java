package com.example.hostelmanagementsystem;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class customComplainDialouge extends DialogFragment {

    DatabaseReference mref ;

    public static final String TAG = "add_complain_dialog";

    private Toolbar toolbar;

    String id ;

    String  name , roll , Class , details , complain , DATE ;



    public static customComplainDialouge display(FragmentManager fragmentManager) {
        customComplainDialouge exampleDialog = new customComplainDialouge();
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
        View view = inflater.inflate(R.layout.activity_add_complain, container, false);

        toolbar = view.findViewById(R.id.toolbarComplain);





        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setTitle("Add Complain");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        mref = FirebaseDatabase.getInstance().getReference("complainList");
        final EditText nameIn = view.findViewById(R.id.nameEditText);
        final EditText rollIn = view.findViewById(R.id.rollEditText);
        final EditText classIn = view.findViewById(R.id.classEditText);
        final EditText detailsIN = view.findViewById(R.id.complainEditText) ;
        final MaterialButton submitBtn = view.findViewById(R.id.submitComplain);

        final Spinner spinner = view.findViewById(R.id.spinner);



        ArrayAdapter <String> adapter = new ArrayAdapter<String>(getContext() , android.R.layout.simple_spinner_item
                ,getResources().getStringArray(R.array.Complain));
        adapter.setDropDownViewResource(android.R.layout.simple_selectable_list_item);
        spinner.setAdapter(adapter);


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name , roll , clasS , details  , complain ;

                name = nameIn.getText().toString();
                roll = rollIn.getText().toString();
                clasS=classIn.getText().toString();
                details = detailsIN.getText().toString();
                complain = spinner.getSelectedItem().toString();

                DATE = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

                if(!TextUtils.isEmpty(name) || !TextUtils.isEmpty(roll) || !TextUtils.isEmpty(clasS)
                || !complain.contains("Please Select Complain Type"))
                {
                    // upload complain here



                    String postId  = mref.push().getKey();

                    modelForcomplainList uploadModel =  new modelForcomplainList(postId , name , roll , clasS , "",DATE , complain , details ) ;

                    mref.child(postId).setValue(uploadModel)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {


                            Toast.makeText(getContext() , "Complain Added Successfully !!!" , Toast.LENGTH_LONG)
                                    .show();

                            dismiss();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext() , "Error : "+ e.getMessage() , Toast.LENGTH_LONG)
                                    .show();
                        }
                    });


                }
                else {

                    Toast.makeText(getContext() , "Fill Up the Form Properly" , Toast.LENGTH_LONG)
                            .show();


                }



            }
        });





    }






}
