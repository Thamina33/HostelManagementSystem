package com.example.hostelmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class complainList extends AppCompatActivity {


    MaterialToolbar toolbar;
    RecyclerView mrecyclerview;

    LinearLayoutManager mLayoutManager; //for sorting
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    FirebaseRecyclerAdapter<modelForcomplainList, viewHolderForComplainList> firebaseRecyclerAdapter;
    FirebaseRecyclerOptions<modelForcomplainList> options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain_list);


        // Find the toolbar view inside the activity layout
        toolbar = findViewById(R.id.toolbarInComplainList);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Complain List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        //init view

        mrecyclerview = findViewById(R.id.reclyclerViewForComplainList);


        mLayoutManager = new LinearLayoutManager(this);
        //this will load the items from bottom means newest first
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        mrecyclerview.setHasFixedSize(true);

        //send Query to FirebaseDatabase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("complainList");
        mRef.keepSynced(true);
        loadData();

    }

    private void loadData() {

        options = new FirebaseRecyclerOptions.Builder<modelForcomplainList>().setQuery(mRef, modelForcomplainList.class)
                .build();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<modelForcomplainList, viewHolderForComplainList>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final viewHolderForComplainList holder, final int position, @NonNull modelForcomplainList model) {
                holder.setDataToView(getApplicationContext(), model.getPostID(), model.getName(), model.getRoll(), model.getaClass()
                        , model.getSection(), model.getDate(), model.getReason(), model.getDetails());
            }

            @NonNull
            @Override
            public viewHolderForComplainList onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                //INflate the row
                Context context;
                View itemVIew = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_complain__row, viewGroup, false);

                final viewHolderForComplainList viewHolder = new viewHolderForComplainList(itemVIew);
                //itemClicklistener
                viewHolder.setOnClickListener(new viewHolderForComplainList.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        //Views
                        //   TextView mTitleTv = view.findViewById(R.id.rTitleTv);
                        //    TextView mDescTv = view.findViewById(R.id.rDescriptionTv);
                        //     ImageView mImageView = view.findViewById(R.id.rImageView);
                        String postId, name, roll, aclass, doa, section, detailss, reasonn;

                        postId = getItem(position).getPostID();
                        name = getItem(position).getName();
                        roll = getItem(position).getRoll();
                        aclass = getItem(position).getaClass();
                        doa = getItem(position).getDate();
                        reasonn = getItem(position).getReason();
                        section = getItem(position).getSection();
                        detailss = getItem(position).getDetails();


                        //start dialogue

                        openDialog(postId, name, roll, aclass, doa , section, detailss, reasonn);


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

    private void openDialog(String postId, String name,String roll,String aclass,String doa ,String section,String detailss,String reasonn) {

        complainDialogue dialog = new complainDialogue();
        Bundle args = new Bundle();
        args.putString("id", postId);
        args.putString("NAME", name);
        args.putString("ROLL", roll);
        args.putString("CLASS", aclass);
        args.putString("DOA", doa);
        args.putString("reason", reasonn);
        args.putString("details", detailss);
        args.putString("SECTION", section);

        dialog.setArguments(args);

        getSupportFragmentManager().beginTransaction();
        getSupportFragmentManager().findFragmentByTag("complain_dialog");
        dialog.show(getSupportFragmentManager(), "complain_dialog");


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate the menu ; this adds items to the actionBar

        getMenuInflater().inflate(R.menu.menu_complain, menu);
        MenuItem item = menu.findItem(R.id.addComplain);

        /*

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

*/
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//  int id =item.getItemId();

        switch (item.getItemId()) {
            case R.id.addComplain:
                // Not implemented here

               // openDialog();


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
}