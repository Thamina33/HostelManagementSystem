package com.example.hostelmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.google.android.material.appbar.MaterialToolbar;

public class HomePage extends AppCompatActivity {

    CardView studentIdbtn;
    TextView add_stdn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);



        studentIdbtn = (CardView) findViewById(R.id.studentIdBTN);
        studentIdbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intent = new Intent (HomePage.this, studentListPage.class);
                startActivity(intent);

            }
        });




    }

}
