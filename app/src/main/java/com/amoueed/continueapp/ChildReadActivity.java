package com.amoueed.continueapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ChildReadActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_read);

        recyclerView = findViewById(R.id.recyclerViewChildRead);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void addChildfloatingActionButtonChildRead(View view) {
        startActivity(new Intent(ChildReadActivity.this, ChildAddActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public static class ChildViewHolder extends RecyclerView.ViewHolder{
        View myView;

        public ChildViewHolder(View itemView){
            super(itemView);
            myView = itemView;
        }

        public void setChildName(String childName){
            TextView name = myView.findViewById(R.id.name);
            name.setText(childName);
        }

        public void setChildDOB(String childName){
            TextView date = myView.findViewById(R.id.date);
            date.setText(childName);
        }
    }
}
