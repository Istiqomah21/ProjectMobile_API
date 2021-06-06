package com.example.simplemovieapps.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplemovieapps.R;
import com.example.simplemovieapps.model.QuoteResponse;
import com.example.simplemovieapps.model.QuoteResultItem;
import com.example.simplemovieapps.view.activity.DetailActivity;
import com.example.simplemovieapps.view.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class QotdDiscoverAdapter extends RecyclerView.Adapter<QotdDiscoverAdapter.ViewHolder> {

    private ArrayList<QuoteResultItem> qotdDiscoverItems = new ArrayList<>();
    private Context context;


    public QotdDiscoverAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<QuoteResultItem> items){
        qotdDiscoverItems.clear();
        qotdDiscoverItems.addAll(items);
//        quoteResponses.clear();
//        quoteResponses.addAll(date);
        notifyDataSetChanged();
    }


    @NonNull
    @Override // inisialisasi item list.xml
    public QotdDiscoverAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new QotdDiscoverAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QotdDiscoverAdapter.ViewHolder holder, int position) {
    // menampilkan data yang berbeda beda
        holder.tvAuthor.setText(qotdDiscoverItems.get(position).getAuthor());
        holder.tvBody.setText(qotdDiscoverItems.get(position).getBody());
        holder.tvFavorite.setText(String.valueOf(qotdDiscoverItems.get(position).getFavoritesCount()));
        holder.tvUpvote.setText(String.valueOf(qotdDiscoverItems.get(position).getUpvotesCount()));
        holder.tvDownvote.setText(String.valueOf(qotdDiscoverItems.get(position).getDownvotesCount()));
        Integer count = qotdDiscoverItems.get(position).getTags().size();
        String tags = "";
        if(count > 0){
            for(int c=0; c<count; c++){
                tags = tags +"["+ String.valueOf(qotdDiscoverItems.get(position).getTags().get(c))+"] ";
            }
        }
        holder.tvTags.setText(tags);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                               // variabel ke detail activity
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("author", qotdDiscoverItems.get(position).getAuthor());
                intent.putExtra("body", qotdDiscoverItems.get(position).getBody());
                String tags = "";
                if(count > 0){
                    for(int c=0; c<count; c++){
                        tags = tags +"["+ String.valueOf(qotdDiscoverItems.get(position).getTags().get(c))+"] ";
                    }
                }
                intent.putExtra("tags", tags);
                intent.putExtra("favorite", qotdDiscoverItems.get(position).getFavoritesCount());
                intent.putExtra("upvote", qotdDiscoverItems.get(position).getUpvotesCount());
                intent.putExtra("downvote", qotdDiscoverItems.get(position).getDownvotesCount());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return qotdDiscoverItems.size();
    }


    // menghubungkan resycklerview dengan viewholder
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvAuthor,tvBody,tvTags,tvFavorite,tvUpvote,tvDownvote;
        CardView layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.itemlist_cv);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            tvBody = itemView.findViewById(R.id.tv_body);
            tvTags = itemView.findViewById(R.id.tv_tags);

            tvFavorite = itemView.findViewById(R.id.tv_favorite);
            tvDownvote = itemView.findViewById(R.id.tv_downvote);
            tvUpvote = itemView.findViewById(R.id.tv_upvote);

        }
    }
}
