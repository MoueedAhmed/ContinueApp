package com.amoueed.continueapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.amoueed.continueapp.adapter.ChildAdapter;
import com.amoueed.continueapp.adapter.VaccineAdapter;
import com.amoueed.continueapp.model.Child;
import com.amoueed.continueapp.model.Vaccine;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChildDetailActivity extends AppCompatActivity {

    private static final String TAG = "ChildDetailActivity";
    private String childID;

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    private RecyclerView mRecyclerView;
    private ArrayList<Vaccine> mVaccineData;
    private VaccineAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_detail);

        childID = getIntent().getStringExtra("id");


        mRecyclerView = findViewById(R.id.recyclerViewChildDetail);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mVaccineData = new ArrayList<>();
        mAdapter = new VaccineAdapter(this, mVaccineData);
        mRecyclerView.setAdapter(mAdapter);


        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String uId = user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Child").child(uId).child(childID).child("mVaccineData");
        databaseReference.keepSynced(true);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                Vaccine newVaccine = dataSnapshot.getValue(Vaccine.class);
                mVaccineData.add(newVaccine);
                // Notify the adapter of the change.
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }
}
