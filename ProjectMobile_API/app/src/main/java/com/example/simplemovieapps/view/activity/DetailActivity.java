package com.example.simplemovieapps.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.simplemovieapps.R;

import com.example.simplemovieapps.model.SaveModel;
import com.example.simplemovieapps.service.DatabaseHelper;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    TextView tvAuthor, tvBody, tvFavorite, tvUpvote,tvDownvote, tvTags;
    String author, body, favorite, upvote, downvote, tags;
    Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvAuthor = findViewById(R.id.tv_author);
        tvBody = findViewById(R.id.tv_body);
        tvTags = findViewById(R.id.tv_tags);
        tvFavorite = findViewById(R.id.tv_favorite);
        tvUpvote = findViewById(R.id.tv_upvote);
        tvDownvote = findViewById(R.id.tv_downvote);

        btnSave = findViewById(R.id.btn_save);

        getIncomingExtra();

        setDataActivity();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences getPreferences = getSharedPreferences("SAVED_LOGIN", MODE_PRIVATE);
                Boolean check = getPreferences.getBoolean("LOGGED",false);

                Integer kunci = getPreferences.getInt("ID", 0);

                DatabaseHelper databasehelper = new DatabaseHelper(DetailActivity.this);
                SaveModel saveModel = new SaveModel(author,body,tags,"tidak",kunci);
                databasehelper.addQuote(saveModel);
                Toast.makeText(DetailActivity.this, "Add Quote Successfully", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(getIntent());

            }
        });


    }

    private void getIncomingExtra() {
        if (getIntent().hasExtra("author")) {
            author = getIntent().getStringExtra("author");
            body = getIntent().getStringExtra("body");
            tags = getIntent().getStringExtra("tags");
            favorite = String.valueOf(getIntent().getIntExtra("favorite", 0));
            upvote = String.valueOf(getIntent().getIntExtra("upvote", 0));
            downvote = String.valueOf(getIntent().getIntExtra("downvote", 0));

        }

    }

    private void setDataActivity(){
        tvBody.setText(body);
        tvAuthor.setText(author);
        tvTags.setText(tags);;
        tvFavorite.setText(favorite);
        tvDownvote.setText(downvote);
        tvUpvote.setText(upvote);
    }


}

