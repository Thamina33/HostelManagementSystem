package com.example.hostelmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class hisabPage extends AppCompatActivity {
    MaterialButton addPayMentBtn , addExpenceListBtn  ;
    TextView expense ,recieve , total ;
    String Rexpence , Rrecieve , Rtotal  ;
    DatabaseReference mref  ;
    Double   mtotal , mexpense , mpayment  ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hisab_page);

        mref = FirebaseDatabase.getInstance().getReference("total");


        addExpenceListBtn = findViewById(R.id.expencesList) ;
        addPayMentBtn = findViewById(R.id.addPayment);
        expense = findViewById(R.id.expencesTEXT);
        recieve = findViewById(R.id.recicveTExt);
        total = findViewById(R.id.totlaTEXt);



        addPayMentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  = new Intent(getApplicationContext(), addPaymentList.class);
                i.putExtra("DB", "recivePaymnetList") ;
                i.putExtra("total",Rrecieve ) ;

                startActivity(i);

            }
        });

        addExpenceListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i  = new Intent(getApplicationContext(), addExpencesList.class);
                i.putExtra("DB" , "expensesList") ;
                i.putExtra("total",Rexpence ) ;
                startActivity(i);

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        loadDataFromFirebase();

    }

    private void loadDataFromFirebase() {




        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                modelForSingleValues dwldData  = dataSnapshot.getValue(modelForSingleValues.class);

                try{
                    Rexpence = dwldData.getExpense() ;
                    Rrecieve = dwldData.getRecieve();

                    expense.setText("$"+ Rexpence);
                    recieve.setText("$"+ Rrecieve);



                    mpayment = Double.valueOf(Rrecieve);

                    mexpense = Double.valueOf(Rexpence);

                    mtotal =  mpayment - mexpense ;
                    total.setText("$"+String.format("%.2f", mtotal) );

                }
                catch (NullPointerException e ){

                    expense.setText("$00");
                    recieve.setText("$00");
                    total.setText("$00.00");

                    Toast.makeText(getApplicationContext()  , "Network Error", Toast.LENGTH_LONG)
                            .show();
                }





            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }) ;





    }
}
