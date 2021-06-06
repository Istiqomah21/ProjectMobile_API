package com.example.simplemovieapps.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplemovieapps.R;
import com.example.simplemovieapps.model.QuoteResultItem;
import com.example.simplemovieapps.model.SaveModel;
import com.example.simplemovieapps.service.DatabaseHelper;
import com.example.simplemovieapps.view.activity.DetailActivity;
import com.example.simplemovieapps.view.activity.DetailSaveActivity;

import java.util.ArrayList;
import java.util.List;

public class SaveAdapter extends RecyclerView.Adapter<SaveAdapter.ViewHolder> {

    private ArrayList<SaveModel> saveItems = new ArrayList<>();
    private Integer resource, resource_null;

    Context context;

    public SaveAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<SaveModel> items, Integer res, Integer res_null){
        saveItems.clear();
        saveItems.addAll(items);
        this.resource=res;
        this.resource_null=res_null;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SaveAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_save_list,parent,false);
        SaveAdapter.ViewHolder viewHolder = new SaveAdapter.ViewHolder(view);
        return viewHolder;
    }




    @Override
    public void onBindViewHolder(@NonNull final SaveAdapter.ViewHolder holder, final int position) {
        final SaveModel saveModel = saveItems.get(position);
        holder.tvAuthor.setText(saveModel.getAuthor());
        holder.tvBody.setText(saveModel.getBody());
        holder.tvTags.setText(saveModel.getTags());
        if (saveModel.getFavorite().equals("favorite")) {
            holder.ivFavorite.setImageResource(resource);
        }else{
            holder.ivFavorite.setImageResource(resource_null);
        }

        holder.cardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                               // variabel ke detail activity

                Intent intent = new Intent(context, DetailSaveActivity.class);
                intent.putExtra("id", saveModel.getId());
                intent.putExtra("author", saveModel.getAuthor());
                intent.putExtra("body", saveModel.getBody());
                intent.putExtra("tags", saveModel.getTags());
                intent.putExtra("favorite", saveModel.getFavorite());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return saveItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvAuthor, tvBody, tvTags;
        CardView cardLayout;
        ImageView ivFavorite;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvAuthor = itemView.findViewById(R.id.tv_author);
            tvBody = itemView.findViewById(R.id.tv_body);
            tvTags = itemView.findViewById(R.id.tv_tags);
            cardLayout = itemView.findViewById(R.id.itemsavelist_cv);
            ivFavorite = itemView.findViewById(R.id.iv_favorite);



        }
    }
}
