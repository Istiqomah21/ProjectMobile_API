package com.example.simplemovieapps.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.simplemovieapps.R;
import com.example.simplemovieapps.view.fragment.QotdFragment;
import com.example.simplemovieapps.view.fragment.SaveFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private Fragment selectedFragment = new QotdFragment(MainActivity.this);
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.activitymain_bottomnav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        loadFragment(selectedFragment);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.menu_bottomnav_qotd:
                selectedFragment = new QotdFragment(MainActivity.this);
                loadFragment(selectedFragment);
                break;

            case R.id.menu_bottomnav_save:
                SharedPreferences getPreferences = getSharedPreferences("SAVED_LOGIN", MODE_PRIVATE);
                Boolean check = getPreferences.getBoolean("LOGGED",false);

                Integer user = getPreferences.getInt("ID", 0);


                int res = getResources().getIdentifier("favorite_color", "drawable", this.getPackageName());
                int res_null = getResources().getIdentifier("favorite_null", "drawable", this.getPackageName());

                selectedFragment = new SaveFragment(MainActivity.this, user, res, res_null);
                loadFragment(selectedFragment);

                break;

        }

        return loadFragment(selectedFragment);
    }

    private boolean loadFragment(Fragment selectedFragment) {
        if (selectedFragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activitymain_fragmentcontainer,selectedFragment)
                    .commit();
            return true;
        }
        return false;
    }
//    public void detail(){
//
//                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
//                startActivity(intent);
//    }
public boolean onCreateOptionsMenu (Menu menu){
    getMenuInflater().inflate(R.menu.menu_main, menu);


    return true;
}

    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_logout:
                SharedPreferences preferences = getSharedPreferences("SAVED_LOGIN", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
