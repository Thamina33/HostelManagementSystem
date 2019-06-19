package com.example.hostelmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.DialogFragment;
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
import com.google.firebase.database.Query;

public class studentListPage extends AppCompatActivity {

    MaterialToolbar toolbar ;
    RecyclerView mrecyclerview ;

    LinearLayoutManager mLayoutManager; //for sorting
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    FirebaseRecyclerAdapter<modelForStudent, viewHolderForStudentStudentList> firebaseRecyclerAdapter ;
    FirebaseRecyclerOptions<modelForStudent> options ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list_page);


        // Find the toolbar view inside the activity layout
        toolbar =  findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        //init view

        mrecyclerview = findViewById(R.id.reclyclerViewForAllStudent) ;


        mLayoutManager = new LinearLayoutManager(this);
        //this will load the items from bottom means newest first
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        mrecyclerview.setHasFixedSize(true);

        //send Query to FirebaseDatabase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("studentList");
        mRef.keepSynced(true);
        loadData();

    }
    private  void loadData(){

        options = new FirebaseRecyclerOptions.Builder<modelForStudent>().setQuery(mRef , modelForStudent.class)
                .build() ;

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<modelForStudent, viewHolderForStudentStudentList>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final   viewHolderForStudentStudentList holder, final int position, @NonNull modelForStudent model) {
                holder.setDataToView(getApplicationContext(),model.getPostId() , model.getName() , model.getRoll(), model.getAclass()
                , model.getDoa(), model.getPaymonthList(), model.getSection(), model.getAddress(), model.getPh());
                String postId , name , roll  , aclass  ,doa  , paymonthList , section , address , ph  ;

            }

            @NonNull
            @Override
            public viewHolderForStudentStudentList onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                //INflate the row
                Context context;
                View itemVIew = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_fees__row, viewGroup, false);

                final viewHolderForStudentStudentList viewHolder = new viewHolderForStudentStudentList(itemVIew);

                //itemClicklistener
                viewHolder.setOnClickListener(new viewHolderForStudentStudentList.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        //Views
                        //   TextView mTitleTv = view.findViewById(R.id.rTitleTv);
                        //    TextView mDescTv = view.findViewById(R.id.rDescriptionTv);
                        //     ImageView mImageView = view.findViewById(R.id.rImageView);
                        String postId , name , roll  , aclass  ,doa  , paymonthList , section , address , ph  ;

                       postId   = getItem(position).getPostId() ;
                        name  = getItem(position).getName();
                        roll = getItem(position).getRoll();
                        aclass =  getItem(position).getAclass();
                        doa =  getItem(position).getDoa();
                        paymonthList =  getItem(position).getPaymonthList();
                        section =  getItem(position).getSection();
                        address = getItem(position).getAddress();
                        ph =  getItem(position).getPh();

                        //start dialogue

                        openDialog( postId ,name , roll , aclass , doa  , paymonthList, section , address , ph );






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

    private void openDialog(String postId, String nAme, String roll, String aclass, String doa, String paymonthList, String section, String address , String ph ) {

        ExampleDialog dialog = new ExampleDialog() ;
        Bundle args = new Bundle();
        args.putString("id",postId );
        args.putString("NAME",nAme );
        args.putString("ROLL",roll );
        args.putString("CLASS",aclass );
        args.putString("DOA",doa );
        args.putString("PAYMENTLIST",paymonthList );
        args.putString("ADDRESS",address );
        args.putString("SECTION",section );
        args.putString("PH",ph );




        dialog.setArguments(args);

        getSupportFragmentManager().beginTransaction();
        getSupportFragmentManager().findFragmentByTag("example_dialog");
        dialog.show(getSupportFragmentManager(), "example_dialog");




    }

    public  void  firebaseSearch(String searchText ){

        //convert string entered in SearchView to lowercase
        String query = searchText;

        Query firebaseSearchQuery = mRef.orderByChild("roll").startAt(query).endAt(query + "\uf8ff");


        options = new FirebaseRecyclerOptions.Builder<modelForStudent>().setQuery(firebaseSearchQuery , modelForStudent.class)
                .build() ;

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<modelForStudent, viewHolderForStudentStudentList>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final   viewHolderForStudentStudentList holder, final int position, @NonNull modelForStudent model) {
                holder.setDataToView(getApplicationContext(),model.getPostId() , model.getName() , model.getRoll(), model.getAclass()
                        , model.getDoa(), model.getPaymonthList(), model.getSection(), model.getAddress(), model.getPh());

            }

            @NonNull
            @Override
            public viewHolderForStudentStudentList onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                //INflate the row
                Context context;
                View itemVIew = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_fees__row, viewGroup, false);

                final viewHolderForStudentStudentList viewHolder = new viewHolderForStudentStudentList(itemVIew);

                //itemClicklistener
                viewHolder.setOnClickListener(new viewHolderForStudentStudentList.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        //Views
                        //   TextView mTitleTv = view.findViewById(R.id.rTitleTv);
                        //    TextView mDescTv = view.findViewById(R.id.rDescriptionTv);
                        //     ImageView mImageView = view.findViewById(R.id.rImageView);
                        String postId , name , roll  , aclass  ,doa  , paymonthList , section , address , ph  ;

                        postId   = getItem(position).getPostId() ;
                        name  = getItem(position).getName();
                        roll = getItem(position).getRoll();
                        aclass =  getItem(position).getAclass();
                        doa =  getItem(position).getDoa();
                        paymonthList =  getItem(position).getPaymonthList();
                        section =  getItem(position).getSection();
                        address = getItem(position).getAddress();
                        ph =  getItem(position).getPh();

                        //start dialogue

                        openDialog( postId ,name , roll , aclass , doa  , paymonthList, section , address , ph );






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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate the menu ; this adds items to the actionBar

        getMenuInflater().inflate(R.menu.menu , menu );
        MenuItem item = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                firebaseSearch(query);



                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
             firebaseSearch(newText); //filtering  as we Type

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

    //load data into recycler view onStart
    @Override
    protected void onStart() {
        super.onStart();
        if(firebaseRecyclerAdapter !=null){
            firebaseRecyclerAdapter.startListening();
        }
    }
}
