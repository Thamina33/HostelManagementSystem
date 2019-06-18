package com.example.hostelmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class studentListForFee extends AppCompatActivity {

    String newFee ;
    RecyclerView mrecyclerView ;
    LinearLayoutManager mLayoutManager ; // for lifo
    DatabaseReference mRef ;

    MaterialToolbar toolbar ;
    String Rexpence , Rrecieve , Rtotal  ;
    Double   mtotal , mexpense , mpayment  ;
    FirebaseRecyclerAdapter<modelForStudent  , viewHolderForFee> firebaseRecyclerAdapter;
    FirebaseRecyclerOptions<modelForStudent> options ;
    AlertDialog dialog ;
    DatabaseReference mref ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list_for_fee);




        // Find the toolbar view inside the activity layout
        toolbar =  findViewById(R.id.toolbar_for_fee);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Choose Student");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mrecyclerView = findViewById(R.id.reclyclerViewForAllStudentFEE) ;

        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        mrecyclerView.setHasFixedSize(true);

        mRef = FirebaseDatabase.getInstance().getReference("studentList");
        mref = FirebaseDatabase.getInstance().getReference("total");
        mRef.keepSynced(true);



    loadDataFromFireBase();








    }

    private void loadDataFromFireBase() {



        options = new FirebaseRecyclerOptions.Builder<modelForStudent>().setQuery(mRef ,modelForStudent.class )
                .build() ;


            firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<modelForStudent, viewHolderForFee>(options) {
                @Override
                protected void onBindViewHolder(@NonNull viewHolderForFee holder, final   int i, @NonNull modelForStudent model) {


                    holder.setDataToRow(getApplicationContext(),model.getPostId() , model.getName() , model.getRoll(), model.getAclass()
                            , model.getDoa(), model.getPaymonthList(), model.getSection(), model.getAddress(), model.getPh());


                }

                @NonNull
                @Override
                public viewHolderForFee onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                    //inflate the row

                    View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_fees__row, parent, false);

                    final viewHolderForFee viewHolderForFee = new viewHolderForFee(itemView) ;

                    viewHolderForFee.setsetOnClickListener(new viewHolderForFee.ClickListener() {
                        @Override
                        public Void OnItemClick(View view, int position) {


                            String postid = getItem(position).getPostId();
                            String monthlist = getItem(position).getPaymonthList();


                            getData(postid , monthlist);




                        return  null ;
                        }

                        @Override
                        public Void OnItemLongClick(View view, int position) {
                            return null;
                        }
                    });






                    return  viewHolderForFee;
                }
            };

            mrecyclerView.setLayoutManager(mLayoutManager) ;
            firebaseRecyclerAdapter.startListening();

            mrecyclerView.setAdapter(firebaseRecyclerAdapter) ;


    }

    public  void  startDialogue(final String postid, final String monthlist) {


     final AlertDialog.Builder mBuilder = new AlertDialog.Builder(studentListForFee.this) ;
     View mView =getLayoutInflater().inflate(R.layout.month_chooser_dialogue, null);
     mBuilder.setTitle("Fill Up The Form") ;




        final EditText mEmail = (EditText) mView.findViewById(R.id.etTitle_MONEY);
        final Spinner spinner = mView.findViewById(R.id.spinneRR);



        ArrayAdapter <String> adapter = new ArrayAdapter<String>(studentListForFee.this , android.R.layout.simple_spinner_item
                ,getResources().getStringArray(R.array.monthName));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);





        mBuilder.setPositiveButton("Upload", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialogInterface, int i) {

                String fee = mEmail.getText().toString();
                String month = spinner.getSelectedItem().toString() ;
                if (!month.contains("Please Select The Month") || !TextUtils.isEmpty(fee)){


                    Double feee = Double.valueOf(fee);

                     newFee =String.valueOf(feee + mpayment);
                    Toast.makeText(getApplicationContext(), "Error = "+newFee , Toast.LENGTH_LONG)
                            .show();
                    // first  increment The Data


                    mRef.child(postid).child("paymonthList").setValue(monthlist + " "+ month+ " ("+ fee +")")
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {


                                    mref.child("recieve").setValue(newFee).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {


                                              dialogInterface.dismiss();



                                        }
                                    })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(getApplicationContext(), "Error :  Could not added in the main list "+e.getMessage() , Toast.LENGTH_LONG)
                                                            .show();

                                                }
                                            });






                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(getApplicationContext(), "Error : "+e.getMessage() , Toast.LENGTH_LONG)
                                    .show();

                        }
                    });






                }

            }
        });

        mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();

            }
        });

        mBuilder.setView(mView);
        dialog = mBuilder.create() ;
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

    }





public  void    getData(final String id, final String mlist){
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                modelForSingleValues dwldData  = dataSnapshot.getValue(modelForSingleValues.class);





                try{

                    Rrecieve = dwldData.getRecieve();
                    mpayment = Double.valueOf(Rrecieve);

                    startDialogue(id , mlist);


                }
                catch (NullPointerException e ){

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

