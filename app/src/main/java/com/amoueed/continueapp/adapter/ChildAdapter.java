package com.amoueed.continueapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amoueed.continueapp.ChildDetailActivity;
import com.amoueed.continueapp.R;
import com.amoueed.continueapp.model.Child;
import com.bumptech.glide.Glide;


import java.util.ArrayList;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ViewHolder>{

    private ArrayList<Child> mChildData;
    private Context mContext;

    public ChildAdapter(Context context, ArrayList<Child> childData) {
        this.mChildData = childData;
        this.mContext = context;
    }

    @Override
    public ChildAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.card_child_add, parent, false));
    }

    @Override
    public void onBindViewHolder(ChildAdapter.ViewHolder holder, int position) {
        Child currentChild = mChildData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentChild);
    }

    @Override
    public int getItemCount() {
        return mChildData.size();
    }

    /**
     * ViewHolder class that represents each row of data in the RecyclerView.
     */
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        // Member Variables for the TextViews
        private TextView mChildNameText;
        private TextView mDateText;
        private TextView mGenderText;
        private ImageView mImage;

        ViewHolder(View itemView) {
            super(itemView);

            // Initialize the views.
            mChildNameText = itemView.findViewById(R.id.name);
            mDateText = itemView.findViewById(R.id.date);
            mGenderText = itemView.findViewById(R.id.gender);
            mImage = itemView.findViewById(R.id.childImage);

            // Set the OnClickListener to the entire view.
            itemView.setOnClickListener(this);
        }

        void bindTo(Child currentChild){
            // Populate the textviews with data.
            mChildNameText.setText(currentChild.getChildName());
            mDateText.setText(currentChild.getDateOfBirth());
            mGenderText.setText(currentChild.getGender());
            Glide.with(mContext).load(R.drawable.child).into(mImage);
        }

        @Override
        public void onClick(View view) {
            Child currentChild = mChildData.get(getAdapterPosition());
            Intent childDetailIntent = new Intent(mContext, ChildDetailActivity.class);
            childDetailIntent.putExtra("id", currentChild.getId());
            mContext.startActivity(childDetailIntent);
        }
    }
}
