package com.example.simplemovieapps.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simplemovieapps.R;
import com.example.simplemovieapps.model.SaveModel;
import com.example.simplemovieapps.model.UserModel;
import com.example.simplemovieapps.service.DatabaseHelper;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    EditText nama,username,password;
    Button login,daftar;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences getPreferences = getSharedPreferences("SAVED_LOGIN", MODE_PRIVATE);
        Boolean check = getPreferences.getBoolean("LOGGED",false);
        String orang = getPreferences.getString("NAMA", "false");
        Integer kunci = getPreferences.getInt("ID", 0);

        if (check){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            LoginActivity.this.startActivity(intent);
            finish();
        }

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.login_activity);

        nama = findViewById(R.id.et_nama);
        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
        login = findViewById(R.id.btn_login);
        daftar = findViewById(R.id.btn_daftar);


        sharedPreferences = getSharedPreferences("SAVED_LOGIN", MODE_PRIVATE);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getUsername = username.getText().toString();
                String getPassword = password.getText().toString();

                if (getPassword.length()>0 && getUsername.length()>0){

                    DatabaseHelper databaseHelper = new DatabaseHelper(LoginActivity.this);
                    ArrayList<UserModel> userModels = databaseHelper.getUser();
                    boolean cek = false;
                    for (int i=0;i<userModels.size();i++){

                        if(getUsername.equals(userModels.get(i).getUsername()) && getPassword.equals(userModels.get(i).getPassword())){
                            cek = true;
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("LOGGED", true);
                            editor.putString("NAMA", userModels.get(i).getNama());
                            editor.putInt("ID", userModels.get(i).getId());
                            editor.apply();

                        }
                    }

                    if (cek == true){


                        Toast.makeText(getApplicationContext(),"Login Berhasil",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        LoginActivity.this.startActivity(intent);
                        finish();



                    }else{

                        Toast.makeText(getApplicationContext(),"Username password salah",Toast.LENGTH_SHORT).show();
                    }


                }else {
                    Toast.makeText(getApplicationContext(),"Wajib diisi",Toast.LENGTH_SHORT).show();
                }
            }
        });
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getNama = nama.getText().toString();
                String getUsername = username.getText().toString();
                String getPassword = password.getText().toString();

                if (getNama.length()>0 && getPassword.length()>0 && getUsername.length()>0){

                    DatabaseHelper databasehelper = new DatabaseHelper(LoginActivity.this);
                    UserModel userModel = new UserModel(getNama, getUsername, getPassword);
                    databasehelper.addUser(userModel);
                    Toast.makeText(getApplicationContext(),"Create Berhasil",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Create Gagal",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}