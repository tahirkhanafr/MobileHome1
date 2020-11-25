package com.example.mobilehome;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myviewholder extends RecyclerView.ViewHolder {
    ImageView imgmobile;
    TextView tvmobilenamee, tvprice, specification,contact,allcities;

    public myviewholder(@NonNull View itemView) {
        super(itemView);
        imgmobile=itemView.findViewById(R.id.mobileicon);
        tvmobilenamee=itemView.findViewById(R.id.mobilename);
        tvprice=itemView.findViewById(R.id.price);
        specification=itemView.findViewById(R.id.specification);
        contact=itemView.findViewById(R.id.contact);
        allcities=itemView.findViewById(R.id.allcities);

    }
}
