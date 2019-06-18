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

    CardView studentIdbtn , finaceBtn  , comaplainbtn ;
    TextView add_stdn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);



        studentIdbtn = (CardView) findViewById(R.id.studentIdBTN);
        finaceBtn = findViewById(R.id.finaceBtn)  ;
        comaplainbtn = findViewById(R.id.complainID) ;

        comaplainbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent o = new Intent(getApplicationContext(), complainList.class);
                startActivity(o);

            }
        });

        finaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext() , hisabPage.class);
                startActivity(i);

            }
        });


        studentIdbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intent = new Intent (HomePage.this, studentListPage.class);
                startActivity(intent);

            }
        });




    }

}
