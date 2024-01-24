package com.example.lovelycoffee;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lovelycoffee.model.Restaurant;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ListRestaurant extends ArrayAdapter<Restaurant> {
    private Activity context;
    List<Restaurant> restaurants;
    String id;
    public ListRestaurant(Activity context, List<Restaurant> restaurants) {
        super(context, R.layout.activity_list_restaurant, restaurants);
        this.context = context;
        this.restaurants = restaurants;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_list_restaurant, null, true);


        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewLocation = (TextView) listViewItem.findViewById(R.id.textViewLocation);
        TextView textViewTime = (TextView) listViewItem.findViewById(R.id.textViewTime);
        TextView textViewDetail = (TextView) listViewItem.findViewById(R.id.textViewDetail);



        final Restaurant restaurantShow = restaurants.get(position);
        id = restaurantShow.getId(); //เก็บ PK ไว้ตัวแปร id
        textViewName.setText("ชื่อร้าน" + " " + restaurantShow.getName());
        textViewLocation.setText("สถานที่ตั้ง" + " " + restaurantShow.getLocation());
        textViewTime.setText("เวลาเปิด-ปิด" + " " + restaurantShow.getOpenCloseTime());
        textViewDetail.setText("รายละเอียดเพิ่มเติม" + " " + restaurantShow.getAdditionalDetails());


        listViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder pictureDialog = new AlertDialog.Builder(context);
                pictureDialog.setTitle("เลือกรายการ");
                String[] pictureDialogItems = {"รายละเอียดร้านกาเเฟ", "ลบข้อมูลร้านกาเเฟ"};
                pictureDialog.setItems(pictureDialogItems,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        Intent openUpdate = new Intent(context, ShowResById.class);
                                        openUpdate.putExtra("resId",restaurantShow.getName());
                                        openUpdate.putExtra("resName",restaurantShow.getName());
                                        openUpdate.putExtra("resLocation",restaurantShow.getLocation());
                                        openUpdate.putExtra("resTime",restaurantShow.getOpenCloseTime());
                                        openUpdate.putExtra("resDetail",restaurantShow.getAdditionalDetails());
                                        context.startActivity(openUpdate);
                                        break;
                                    case 1:
                                        deleteArtistConfirmation(restaurantShow.getId());
                                        break;
                                    case 2:
                                        break;
                                }
                            }
                        });
                pictureDialog.show();


            }
        });


        return listViewItem;
    }

    private boolean deleteArtist(String id) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("restaurants").child(id);
        dR.removeValue();
        Toast.makeText(getContext(), "ลบร้านกาเเฟเรียบร้อย", Toast.LENGTH_LONG).show();
        return true;
    }

    private void deleteArtistConfirmation(final String artistId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("ยืนยันการลบ");
        builder.setMessage("คุณต้องการลบร้านกาเเฟนี้ใช่หรือไม่?");

        // Add the buttons
        builder.setPositiveButton("ใช่", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // User clicked OK button
                deleteArtist(artistId);
            }
        });
        builder.setNegativeButton("ไม่", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // User clicked Cancel button
                dialog.dismiss();
            }
        });

        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}