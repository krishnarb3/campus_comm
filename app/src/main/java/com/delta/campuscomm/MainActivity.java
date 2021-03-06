package com.delta.campuscomm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.delta.campuscomm.CommonUtilities.*;

public class MainActivity extends AppCompatActivity {

    Button butonEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        butonEnter = (Button)findViewById(R.id.button_enter_main);

        SharedPreferences store = getSharedPreferences("campuscomm", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = store.edit();
        editor.apply();

        //to verify that app is running
        isAppRun = true;

        myDBHandler = new MyDBHandler(getApplicationContext(), null, null, 1);

        //Check if user already logged in and  if yes, go to posts activity
        if (store.getString("userName", null) != null) {
            Intent i = new Intent(this, AllFunctionsActivity.class);
            finish();
            startActivity(i);
            return;
        }

        butonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                butonEnter.setBackgroundColor(getResources().getColor(R.color.primarycolor));
                Intent intent = new Intent(MainActivity.this, AuthorActivity.class);
                startActivity(intent);
                butonEnter.setBackgroundColor(getResources().getColor(R.color.enterButtonColor));
                finish();
            }
        });
    }

}
