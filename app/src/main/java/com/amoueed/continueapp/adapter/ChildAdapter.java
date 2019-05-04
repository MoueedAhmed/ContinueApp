package com.amoueed.continueapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amoueed.continueapp.R;
import com.amoueed.continueapp.model.Child;


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
    class ViewHolder extends RecyclerView.ViewHolder {

        // Member Variables for the TextViews
        private TextView mChildNameText;
        private TextView mDateText;
        private TextView mGenderText;

        ViewHolder(View itemView) {
            super(itemView);

            // Initialize the views.
            mChildNameText = itemView.findViewById(R.id.name);
            mDateText = itemView.findViewById(R.id.date);
            mGenderText = itemView.findViewById(R.id.gender);
        }

        void bindTo(Child currentChild){
            // Populate the textviews with data.
            mChildNameText.setText(currentChild.getChildName());
            mDateText.setText(currentChild.getDateOfBirth());
            mGenderText.setText(currentChild.getGender());
        }
    }
}
