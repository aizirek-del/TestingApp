package com.example.testingapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    private List<MyList> mList;
    private PostClickListener mClickListener;
    private MyViewHolder holder;
    private int position;


    public ListAdapter(Context ctx, List<MyList> items) {
        inflater = LayoutInflater.from(ctx);
        this.mList = items;
    }

    @Override
    public ListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_list, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    public void onBindViewHolder(@NonNull ListAdapter.MyViewHolder holder, int pos) {
        this.holder = holder;
        this.position = pos;

        MyList item = mList.get(position);

        holder.mTextTitle.setText(item.title);
        holder.mTextBody.setText(item.body);
       holder.mCardView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
    mClickListener.OnClick(position);

           }
       });



    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setOnItemClickListener(PostClickListener listener) {

        mClickListener = listener;
    }


static  class MyViewHolder extends RecyclerView.ViewHolder {
    private MaterialCardView mCardView;
    private TextView mTextId;
    private TextView mTextTitle;
    private TextView mTextBody;

    MyViewHolder(View itemView) {
        super(itemView);
        mCardView = itemView.findViewById(R.id.card_view);
        mTextId = itemView.findViewById(R.id.text_id);
        mTextTitle = itemView.findViewById(R.id.text_title);
        mTextBody = itemView.findViewById(R.id.text_body);

    }

}
}
