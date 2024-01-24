package com.example.lovelycoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.lovelycoffee.model.Restaurant;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ShowRes extends AppCompatActivity {

    public ListView listViewArtists;
    List<Restaurant> restaurants ;

    public FirebaseDatabase firebaseDatabase;
    public DatabaseReference rootDbRef;
    public DatabaseReference subrootDbRef_restaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_res);
        listViewArtists = (ListView) findViewById(R.id.listViewRes);
        restaurants = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        rootDbRef = firebaseDatabase.getReference();
        subrootDbRef_restaurant = rootDbRef.child("restaurants");

    }

    @Override
    public void onBackPressed() {
        // Show a confirmation dialog for back press
        super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("คุณต้องการออกหรือไม่?");
        builder.setCancelable(true);

        builder.setPositiveButton(
                "ใช่",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // If "Yes" is clicked, exit the app
                        finish();
                    }
                });

        builder.setNegativeButton(
                "ไม่",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // If "No" is clicked, dismiss the dialog
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }



    protected void onStart() {
        super.onStart();
        subrootDbRef_restaurant.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                restaurants.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String id = postSnapshot.getKey();
                    Restaurant artistshow = postSnapshot.getValue(Restaurant.class);
                    restaurants.add(artistshow);
                }
                Collections.sort(restaurants, new Comparator<Restaurant>() {
                    @Override
                    public int compare(Restaurant artist1, Restaurant artist2) {
                        // ในที่นี้เราเปรียบเทียบตามชื่อศิลปิน (สมมติว่าชื่ออยู่ใน property name)
                        return artist1.getName().compareTo(artist2.getName());
                    }
                });
                ListRestaurant placeAdapter = new ListRestaurant(ShowRes.this, restaurants);
                listViewArtists.setAdapter(placeAdapter);
            }
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void openExit(View view) {
        // Show a confirmation dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("คุณต้องการออกหรือไม่?");
        builder.setCancelable(true);

        builder.setPositiveButton(
                "ใช่",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // If "Yes" is clicked, exit the app
                        finish();
                    }
                });

        builder.setNegativeButton(
                "ไม่",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // If "No" is clicked, dismiss the dialog
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }
    public void openadd(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}