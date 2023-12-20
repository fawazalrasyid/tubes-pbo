package com.fawazalrasyid.codelearn.Adapter;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fawazalrasyid.codelearn.Models.Course;
import com.fawazalrasyid.codelearn.Models.Module;
import com.fawazalrasyid.codelearn.R;
import com.squareup.picasso.Picasso;

public class ModuleAdapter extends RecyclerView.ViewHolder {

    private TextView tvName;
    private ImageView ivImage;
    private FrameLayout rv;

    public ModuleAdapter(View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.name);
        ivImage = itemView.findViewById(R.id.logo);
        rv = itemView.findViewById(R.id.item_module);

    }

    public void bindToCard(Module module, View.OnClickListener onClickListener){
        tvName.setText(module.getName());
        Picasso.get()
                .load(module.getImage())
                .into(ivImage);
        rv.setOnClickListener(onClickListener);

    }

}