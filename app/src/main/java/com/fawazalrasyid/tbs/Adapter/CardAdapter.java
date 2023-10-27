package com.fawazalrasyid.tbs.Adapter;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fawazalrasyid.tbs.Models.Card;
import com.fawazalrasyid.tbs.R;
import com.squareup.picasso.Picasso;

public class CardAdapter extends RecyclerView.ViewHolder {

    private TextView tvName;
    private ImageView ivImg;
    private FrameLayout rv;

    public CardAdapter (View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.name);
        ivImg = itemView.findViewById(R.id.logo);
        rv = itemView.findViewById(R.id.itemmenu);

    }

    public void bindToCard(Card card, View.OnClickListener onClickListener){
        tvName.setText(card.cardname);
        Picasso.get()
                .load(card.cardlogo)
                .into(ivImg);
        rv.setOnClickListener(onClickListener);

    }

}
