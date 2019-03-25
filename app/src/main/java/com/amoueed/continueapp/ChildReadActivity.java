package com.amoueed.continueapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.amoueed.continueapp.Model.Child;
import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChildReadActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_read);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String uId = user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Child").child(uId);
        databaseReference.keepSynced(true);

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

        FirebaseRecyclerAdapter<Child, ChildViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Child, ChildViewHolder>(
                Child.class, R.layout.card_child_add, ChildViewHolder.class, databaseReference) {
            @Override
            protected void populateViewHolder(ChildViewHolder viewHolder, final Child model, int position) {
                viewHolder.setChildName(model.getChildName());
                viewHolder.setChildDOB(model.getDateOfBirth());
                viewHolder.myView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent myIntent = new Intent(ChildReadActivity.this, ChildUpdateDeleteActivity.class);
                        Child child = new Child();
                        child.setId(model.getId());
                        child.setChildName(model.getChildName());
                        child.setDateOfBirth(model.getDateOfBirth());
                        child.setGender(model.getGender());
                        child.setGuardianName(model.getGuardianName());
                        child.setPhone(model.getPhone());
                        child.setEmail(model.getEmail());
                        myIntent.putExtra("myChild", child);
                        startActivity(myIntent);
                    }
                });
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_child_read, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.item_logout:
                firebaseAuth.signOut();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
