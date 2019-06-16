package com.example.hostelmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.material.appbar.MaterialToolbar;

public class studentListPage extends AppCompatActivity {

    MaterialToolbar toolbar ;

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
}
