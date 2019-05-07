package com.example.new_rss.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.new_rss.Interface.ItemClickListener;
import com.example.new_rss.Model.RSSObject;
import com.example.new_rss.R;

class FeedViewHolder extends RecyclerView.ViewHolder implements OnClickListener, View.OnLongClickListener {

    //instance variables
    public TextView textTitle, textPubDate, textContent;
    private ItemClickListener itemClickListener;

    //Constructor
    public FeedViewHolder( View itemView) {
        super(itemView);

        //defining the textviews
        textTitle = (TextView)itemView.findViewById(R.id.textTitle);
        textPubDate = (TextView)itemView.findViewById(R.id.pubDate);
        textContent = (TextView)itemView.findViewById(R.id.textContent);

        //Set event
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {

        itemClickListener.onClick(view, getAdapterPosition(),false);

    }

    @Override
    public boolean onLongClick(View view) {

        itemClickListener.onClick(view, getAdapterPosition(),true);

        return true;
    }
}



public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder> {

    //instance variables
    private RSSObject rssObject;
    private Context mContext;
    private LayoutInflater inflater;

    //Constructor
    public FeedAdapter(RSSObject rssObject, Context mContext) {
        this.rssObject = rssObject;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public FeedViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.row, viewGroup, false);
        return new FeedViewHolder(itemView);
    }

    //Bind the data to the views
    @Override
    public void onBindViewHolder( FeedViewHolder feedViewHolder, int i) {
        feedViewHolder.textTitle.setText(rssObject.getItems().get(i).getTitle());
        feedViewHolder.textPubDate.setText(rssObject.getItems().get(i).getPubDate());
        feedViewHolder.textContent.setText(rssObject.getItems().get(i).getContent());

        //set the item click listener
        feedViewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {

                //if its not a long click then open the news url
                if(!isLongClick) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(rssObject.getItems().get(position).getLink()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return rssObject.getItems().size();
    }
}
