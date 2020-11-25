package com.example.mobilehome;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<myviewholder> {

    ArrayList<Model> data1;
    Context context;
    Jsondatum[] data;
    String imageurl;

    public MyAdapter(Jsondatum[] data , Context context)
    {
        this.data = data;
        this.context=context;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item_listview,parent,false);
        return  new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
//            final  Model temp=data.get(position);
        final Jsondatum jsondatum=data[position];
        holder.tvmobilenamee.setText(jsondatum.getMobilename());
        holder.tvprice.setText(jsondatum.getPrice());
        // Glide can be used here
        Glide.with(holder.imgmobile.getContext()).load("http://192.168.18.8/MobileHome/images/"+ jsondatum.getImage()).into(holder.imgmobile);
//        holder.imgmobile.setImageResource("http://192.168.43.201/MobileHome/images/"+ jsondatum.getImage());
        holder.specification.setText(jsondatum.getMobileinfo());
        holder.contact.setText(jsondatum.getContact());
        holder.allcities.setText(jsondatum.getAllcities());

        holder.imgmobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,DetailActivity.class);
                imageurl="http://192.168.18.8/MobileHome/images/"+jsondatum.getImage();
                intent.putExtra("image",imageurl);
                intent.putExtra("Mobilename",jsondatum.getMobilename());
                intent.putExtra("Specification",jsondatum.getMobileinfo());
                intent.putExtra("Price",jsondatum.getPrice());
                intent.putExtra("Contect",jsondatum.getContact());
                intent.putExtra("allcities",jsondatum.getAllcities());



                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        holder.tvmobilenamee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,DetailActivity.class);
                intent.putExtra("image",imageurl);
                intent.putExtra("Mobilename",jsondatum.getMobilename());
                intent.putExtra("Specification",jsondatum.getMobileinfo());
                intent.putExtra("Price",jsondatum.getPrice());
                intent.putExtra("Contect",jsondatum.getContact());
                intent.putExtra("allcities",jsondatum.getAllcities());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return data.length;
    }
}
