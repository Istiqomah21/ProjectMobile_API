package com.example.simplemovieapps.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simplemovieapps.R;
import com.example.simplemovieapps.model.SaveModel;
import com.example.simplemovieapps.service.DatabaseHelper;

public class DetailSaveActivity extends AppCompatActivity {

    TextView tvAuthor, tvBody, tvTags;
    String author, body, tags, favorite;
    ImageView ivFavorite;
    Integer id, resource;
    Button btnSave,btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_save);

        tvAuthor = findViewById(R.id.tv_author);
        tvBody = findViewById(R.id.tv_body);
        tvTags = findViewById(R.id.tv_tags);
        btnDelete = findViewById(R.id.delete_favorite);
        btnSave = findViewById(R.id.save_favorite);
        ivFavorite = findViewById(R.id.iv_favorite);



        getIncomingExtra();
        setDataActivity();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper databasehelper = new DatabaseHelper(DetailSaveActivity.this);
                if(favorite.equals("tidak")){
                    favorite="favorite";
                    Toast.makeText(DetailSaveActivity.this, "Add to Favorite", Toast.LENGTH_SHORT).show();
                }else{
                    favorite="tidak";
                    Toast.makeText(DetailSaveActivity.this, "Remove to Favorite", Toast.LENGTH_SHORT).show();
                }
                databasehelper.updateQuote(id,favorite);
                Intent intent = new Intent(DetailSaveActivity.this, MainActivity.class);
                DetailSaveActivity.this.startActivity(intent);
                finish();

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper databasehelper = new DatabaseHelper(DetailSaveActivity.this);
                databasehelper.deleteQuote(id);
                Toast.makeText(DetailSaveActivity.this, "Quote Deleted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DetailSaveActivity.this, MainActivity.class);
                DetailSaveActivity.this.startActivity(intent);
                finish();

            }
        });


    }

    private void getIncomingExtra() {
        if (getIntent().hasExtra("author")) {
            author = getIntent().getStringExtra("author");
            body = getIntent().getStringExtra("body");
            tags = getIntent().getStringExtra("tags");
            favorite = getIntent().getStringExtra("favorite");
            id = getIntent().getIntExtra("id", 0);

            if (favorite.equals("favorite")){
                resource = getResources().getIdentifier("favorite_color", "drawable", this.getPackageName());
            }else{
                resource = getResources().getIdentifier("favorite_null", "drawable", this.getPackageName());
            }
        }

    }

    private void setDataActivity(){
        tvBody.setText(body);
        tvAuthor.setText(author);
        tvTags.setText(tags);
        ivFavorite.setImageResource(resource);


    }


//    private void getdata() {
//        Cursor cursor = helper.oneData(id_data);
//        if(cursor.moveToFirst()){
//            String title = cursor.getString(cursor.getColumnIndex(DatabaseHelper.AUTHOR));
//            String detail = cursor.getString(cursor.getColumnIndex(DatabaseHelper.BODY));
//            String pesanc = cursor.getString(cursor.getColumnIndex(DatabaseHelper.ID));
//
//            nama = title;
//            email = detail;
//            pesan =  pesanc;
//        }
//    }



//    int res = getResources().getIdentifier(imagename, "drawable", this.getPackageName());
//    imageview= (ImageView)findViewById(R.id.imageView);
//imageview.setImageResource(res);

//    String uri = "@drawable/myresource";  // where myresource (without the extension) is the file
//
//    int imageResource = getResources().getIdentifier(uri, null, getPackageName());
//
//    imageview= (ImageView)findViewById(R.id.imageView);
//    Drawable res = getResources().getDrawable(imageResource);
//imageView.setImageDrawable(res);

//    String imagename = "myImage";
//    int res = getResources().getIdentifier(imagename, "drawable", this.getPackageName());
//    imageview= (ImageView)findViewById(R.id.imageView);
//imageview.setImageResource(res);
}

