package com.amoueed.continueapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amoueed.continueapp.R;
import com.amoueed.continueapp.model.Vaccine;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class VaccineAdapter extends RecyclerView.Adapter<VaccineAdapter.ViewHolder>{

    private ArrayList<Vaccine> mVaccineData;
    private Context mContext;

    public VaccineAdapter(Context context, ArrayList<Vaccine> vaccineData) {
        this.mVaccineData = vaccineData;
        this.mContext = context;
    }

    @Override
    public VaccineAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.list_item_child_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(VaccineAdapter.ViewHolder holder, int position) {
        // Get current vaccine.
        Vaccine currentVaccine = mVaccineData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentVaccine);
    }

    @Override
    public int getItemCount() {
        return mVaccineData.size();
    }

    /**
     * ViewHolder class that represents each row of data in the RecyclerView.
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        // Member Variables for the TextViews
        private TextView mVaccineNameText;
        private TextView mDueDateText;
        private TextView mGivenDateText;
        private TextView mVaccineStatusText;
        private TextView mReminderStatusText;
        private ImageView mVaccineImage;

        ViewHolder(View itemView) {
            super(itemView);

            // Initialize the views.
            mVaccineNameText = itemView.findViewById(R.id.vaccine_name);
            mDueDateText = itemView.findViewById(R.id.due_date);
            mGivenDateText = itemView.findViewById(R.id.given_date);
            mVaccineStatusText = itemView.findViewById(R.id.vaccine_status);
            mReminderStatusText = itemView.findViewById(R.id.vaccine_reminder_status);
            mVaccineImage = itemView.findViewById(R.id.vaccineImage);
        }

        void bindTo(Vaccine currentVaccine){
            // Populate the textviews with data.
            mVaccineNameText.setText(currentVaccine.getName());
            mDueDateText.setText(currentVaccine.getDueDate());
            mGivenDateText.setText(currentVaccine.getGivenDate());
            mVaccineStatusText.setText(currentVaccine.getStatus());
            mReminderStatusText.setText(currentVaccine.getReminderStatus());
            Glide.with(mContext).load(R.drawable.pink).into(mVaccineImage);
        }
    }

}
