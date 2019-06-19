package com.example.hostelmanagementsystem;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class addPaymentList  extends AppCompatActivity {


    MaterialButton toolbarBtn ;
    Dialog dialog ;
    String DATE  , time  ;
    String db;
    String amount , reason , date ;
    DatabaseReference mRef ;

    MaterialToolbar toolbar ;
    RecyclerView mrecyclerview ;
    String Total ;

    LinearLayoutManager mLayoutManager; //for sorting
    FirebaseDatabase mFirebaseDatabase;
    FirebaseRecyclerAdapter<modelForExpences, viewHoderForExpenceList> firebaseRecyclerAdapter ;
    FirebaseRecyclerOptions<modelForExpences> options ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expences_list);
        getSupportActionBar().hide();



        Intent i = getIntent() ;
        db =   i.getStringExtra("DB") ;




        mRef = FirebaseDatabase.getInstance().getReference(db);




        toolbarBtn = findViewById(R.id.addExpBtn);

        if(db.contains("recivePaymnetList")){

            toolbarBtn.setText("Add Payment");
        }

        toolbarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDialogue();
                //   Toast.makeText(getApplicationContext() , "TTT", Toast.LENGTH_LONG).show();
            }
        });
        //init view

        mrecyclerview = findViewById(R.id.reclyclerViewForExpenceList) ;


        mLayoutManager = new LinearLayoutManager(this);
        //this will load the items from bottom means newest first
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        mrecyclerview.setHasFixedSize(true);

        //send Query to FirebaseDatabase
        mRef.keepSynced(true);
        loadData();

    }
    private  void loadData(){

        options = new FirebaseRecyclerOptions.Builder<modelForExpences>().setQuery(mRef , modelForExpences.class)
                .build() ;

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<modelForExpences, viewHoderForExpenceList>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final   viewHoderForExpenceList holder, final int position, @NonNull modelForExpences model) {
                holder.setDataToView(getApplicationContext(), model.getReason()  , model.getAmount() , model.getDate());

                //     String postId , name , roll  , aclass  ,doa  , paymonthList , section , address , ph  ;

            }

            @NonNull
            @Override
            public viewHoderForExpenceList onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                //INflate the row
                Context context;
                View itemVIew = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_expense__row, viewGroup, false);

                final viewHoderForExpenceList viewHolder = new viewHoderForExpenceList(itemVIew);
                //itemClicklistener
                viewHolder.setOnClickListener(new viewHoderForExpenceList.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        //Views
                        //   TextView mTitleTv = view.findViewById(R.id.rTitleTv);
                        //    TextView mDescTv = view.findViewById(R.id.rDescriptionTv);
                        //     ImageView mImageView = view.findViewById(R.id.rImageView);
                        String  reason , amount ,date   ;

                        reason   = getItem(position).getReason() ;
                        amount  = getItem(position).getAmount();
                        date = getItem(position).getDate();


                        //start dialogue








                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                    }
                });


                return viewHolder;
            }

        };

        mrecyclerview.setLayoutManager(mLayoutManager);
        firebaseRecyclerAdapter.startListening();
        //setting adapter

        mrecyclerview.setAdapter(firebaseRecyclerAdapter);
    }

    public  void  startDialogue() {

        dialog = new Dialog(addPaymentList.this);
        dialog.setContentView(R.layout.custom_dialogue);
        final EditText mEmail = (EditText) dialog.findViewById(R.id.etTitle);
        final EditText mPassword = (EditText) dialog.findViewById(R.id.etDesc);
        Button mLogin = (Button) dialog.findViewById(R.id.btnSubmit);


        dialog.show();


        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reason = mEmail.getText().toString();
                amount = mPassword.getText().toString();

                if(!TextUtils.isEmpty(reason) || !TextUtils.isEmpty(amount)){


                    dwldTotalFromFirebase(reason , amount);



                }







            }
        });


    }

    private void dwldTotalFromFirebase(final String reason, final String amount) {

        DatabaseReference mrr  = FirebaseDatabase.getInstance().getReference("total");
        mrr.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                modelForSingleValues dwldData  = dataSnapshot.getValue(modelForSingleValues.class);


                try {
                    String Rrecieve = dwldData.getRecieve();
                    String REASON  = reason ;
                    String Amount = amount ;



                    Double   mpayment = Double.valueOf(Rrecieve);

                    double nowPay = Double.valueOf(amount);

                    String total = String.valueOf(mpayment+ nowPay);

                    uploadExpenceTofireBase(REASON ,Amount , total  );

                }
                catch (NullPointerException e ){





                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate the menu ; this adds items to the actionBar

        getMenuInflater().inflate(R.menu.menu , menu );
        MenuItem item = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //   firebaseSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //  firebaseSearch(newText); //filtering  as we Type
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//  int id =item.getItemId();

        switch (item.getItemId()) {
            case R.id.createStudent:
                // Not implemented here
                Intent o = new Intent(getApplicationContext(), CreateNewStudent.class);
                startActivity(o);


                return false;
            default:
                break;
        }


        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }




    public void uploadExpenceTofireBase(String reason, String amount, String total){

        String delegate = "hh:mm aaa";
        time = String.valueOf(DateFormat.format(delegate, Calendar.getInstance().getTime()));

        DATE = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        time = time + " "+DATE ;
final String TOTAL = total ;



        String postid = mRef.push().getKey();


        modelForExpences model = new modelForExpences(this.reason, this.amount, time) ;


        mRef.child(postid).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {


                // uploiaded
                DatabaseReference mre = FirebaseDatabase.getInstance().getReference();
                mre.child("total").child("recieve").setValue(TOTAL).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        dialog.dismiss();


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });


                Toast.makeText(getApplicationContext() , "Data Uploaded Sent !! "  , Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {


                Toast.makeText(getApplicationContext() , "Error : " + e.getMessage() , Toast.LENGTH_LONG).show();

            }
        }) ;

    }




}
