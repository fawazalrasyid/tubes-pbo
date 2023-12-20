package com.fawazalrasyid.codelearn.Adapter;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fawazalrasyid.codelearn.Models.Course;
import com.fawazalrasyid.codelearn.R;
import com.squareup.picasso.Picasso;

public class CourseAdapter extends RecyclerView.ViewHolder {

    private TextView tvName;
    private ImageView ivImage;
    private FrameLayout rv;

    public CourseAdapter(View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.name);
        ivImage = itemView.findViewById(R.id.image);
        rv = itemView.findViewById(R.id.item_course);

    }

    public void bindToCard(Course course, View.OnClickListener onClickListener){
        tvName.setText(course.getName());
        Picasso.get()
                .load(course.getImage())
                .into(ivImage);
        rv.setOnClickListener(onClickListener);
    }

}
