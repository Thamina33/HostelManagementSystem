package com.example.hostelmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CreateNewStudent extends AppCompatActivity {

    EditText  nameIN  , rollIN , secTionIN , doaIN , adressIN , phoneIN  , classIN;
    String name , roll  ,section , doa , adress , phone , classs ;
    DatabaseReference  userRef ;
    Button submitBtn ;
    String DATE  , Time ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_student);

        userRef = FirebaseDatabase.getInstance().getReference("studentList");

        //init Views
        nameIN = findViewById(R.id.nameOfReg);
        rollIN = findViewById(R.id.rollOfReg);
        secTionIN = findViewById(R.id.secOfRow);
        doaIN = findViewById(R.id.doaOfReg);
        adressIN= findViewById(R.id.adressOfReg);
        phoneIN = findViewById(R.id.phOfREg);
        classIN = findViewById(R.id.calssOfReg);
        submitBtn = findViewById(R.id.createStudentBtn) ;


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


 // getting the data
                DATE = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

                name = nameIN.getText().toString();
                roll = rollIN.getText().toString();
                section = secTionIN.getText().toString();
                adress = adressIN.getText().toString();
                phone = phoneIN.getText().toString();
                classs = classIN.getText().toString();


                if(!TextUtils.isEmpty(name) || !TextUtils.isEmpty(roll)||!TextUtils.isEmpty(section)||!TextUtils.isEmpty(adress)
                || !TextUtils.isEmpty(phone)||!TextUtils.isEmpty(classs)){



                       initUploadData(name, roll , section , adress , phone , classs) ;




                }
                else {

                    Toast.makeText(getApplicationContext() , "Error: Enter The Field Properly ", Toast.LENGTH_LONG)
                            .show();
                }




            }
        });




    }



    private void initUploadData(String name, String roll, String section, String adress, String phone, String classs) {


        String postId = userRef.push().getKey();
       // String postId , name , roll  , aclass  ,doa  , paymonthList , section , address , ph

        modelForStudent uploadModel = new modelForStudent(postId  , name , roll , classs , DATE, "null", section, adress , phone );

        userRef.child(postId).setValue(uploadModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                Toast.makeText(getApplicationContext() , " Student Updated", Toast.LENGTH_LONG)
                        .show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {


                Toast.makeText(getApplicationContext() , "Error: " + e.getMessage(), Toast.LENGTH_LONG)
                        .show();
            }
        });




    }
}
