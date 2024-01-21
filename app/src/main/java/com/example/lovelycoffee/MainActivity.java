package com.example.lovelycoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName;
    private Spinner spinnerLocation;
    private EditText editTextOpenCloseTime;
    private EditText editTextAdditionalDetails;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("restaurants"); // Change "restaurants" to your desired database path


        // Initialize your views
        editTextName = findViewById(R.id.editTextName);
        spinnerLocation = findViewById(R.id.spinnerLocation);
        editTextOpenCloseTime = findViewById(R.id.editTextOpenCloseTime);
        editTextAdditionalDetails = findViewById(R.id.editTextAdditionalDetails);

        Button buttonAddRestaurant = findViewById(R.id.buttonAddRestaurant);
        buttonAddRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateFields()) {
                    addRestaurant();
                }
            }
        });

        // Set OnClickListener for the Clear button
        Button buttonClearFields = findViewById(R.id.buttonClearFields);
        buttonClearFields.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearFields();
            }
        });
    }

    private void addRestaurant() {
        // Get data from fields
        String name = editTextName.getText().toString();
        String location = spinnerLocation.getSelectedItem().toString();
        String openCloseTime = editTextOpenCloseTime.getText().toString();
        String additionalDetails = editTextAdditionalDetails.getText().toString();

        // Push data to Firebase
        String key = databaseReference.push().getKey();

        // Create a Restaurant object or a Map to represent your data
        Restaurant restaurant = new Restaurant(key,name, location, openCloseTime, additionalDetails);


        databaseReference.child(key).setValue(restaurant);

        // Clear the fields after adding the restaurant
        Toast.makeText(this, "เพิ่มร้านสำเร็จ", Toast.LENGTH_SHORT).show();
        clearFields();
    }

    private void clearFields() {
        // Clear the content of all EditText and Spinner fields
        editTextName.getText().clear();
        spinnerLocation.setSelection(0); // Set the selection to the first item or adjust as needed
        editTextOpenCloseTime.getText().clear();
        editTextAdditionalDetails.getText().clear();
    }

    private boolean validateFields() {
        if (editTextName.getText().toString().trim().isEmpty()) {
            showToast("Please enter a restaurant name");
            return false;
        }

        if (editTextOpenCloseTime.getText().toString().trim().isEmpty()) {
            showToast("Please enter open-close time");
            return false;
        }

        if (editTextAdditionalDetails.getText().toString().trim().isEmpty()) {
            showToast("Please enter additional details");
            return false;
        }

        return true;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}