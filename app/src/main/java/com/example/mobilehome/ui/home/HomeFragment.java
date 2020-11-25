package com.example.mobilehome.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mobilehome.Jsondatum;
import com.example.mobilehome.MyAdapter;
import com.example.mobilehome.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class HomeFragment extends Fragment {

 private String url="http://192.168.18.8/MobileHome/fetchdata.php";

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);


        final RecyclerView recyclerView=root.findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

//        MyAdapter adapter=new MyAdapter(Jsondatum[], getActivity());
//        recyclerView.setAdapter(adapter);

        StringRequest request=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                GsonBuilder gsonBuilder=new GsonBuilder();
                Gson gson=gsonBuilder.create();
               Jsondatum[] data= gson.fromJson(response, Jsondatum[].class);
               //set data in Recycle view
               recyclerView.setAdapter(new MyAdapter(data,getContext()));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue queue= Volley.newRequestQueue(getContext());
        queue.add(request);

        return root;
    }

    /*---------------getting data for Recycle View--------------------*/
//    public ArrayList<Model> dataqueue()
//    {
//        ArrayList<Model> holder=new ArrayList<>();
//        Model obj1=new Model();
//        obj1.setMobilename("Sumsung");
//        obj1.setMobileprice("1000");
//        obj1.setImagename(R.drawable.smartphone);
//        holder.add(obj1);
//
//        Model obj2=new Model();
//        obj2.setMobilename("IPhone");
//        obj2.setMobileprice("1000");
//        obj2.setImagename(R.drawable.smartphone);
//        holder.add(obj2);
//
//        Model obj3=new Model();
//        obj3.setMobilename("LG");
//        obj3.setMobileprice("1000");
//        obj3.setImagename(R.drawable.smartphone);
//        holder.add(obj3);
//
//        Model obj4=new Model();
//        obj4.setMobilename("Huawei");
//        obj4.setMobileprice("1000");
//        obj4.setImagename(R.drawable.smartphone);
//        holder.add(obj4);
//
//        Model obj5=new Model();
//        obj5.setMobilename("Nokia");
//        obj5.setMobileprice("1000");
//        obj5.setImagename(R.drawable.smartphone);
//        holder.add(obj5);
//
//        Model obj6=new Model();
//        obj6.setMobilename("Hrc");
//        obj6.setMobileprice("1000");
//        obj6.setImagename(R.drawable.smartphone);
//        holder.add(obj6);
//
//        Model obj7=new Model();
//        obj7.setMobilename("Oppo");
//        obj7.setMobileprice("1000");
//        obj7.setImagename(R.drawable.smartphone);
//        holder.add(obj7);
//
//        Model obj8=new Model();
//        obj8.setMobilename("Lenevo");
//        obj8.setMobileprice("1000");
//        obj8.setImagename(R.drawable.smartphone);
//        holder.add(obj8);
//
//        Model obj9=new Model();
//        obj9.setMobilename("Techno");
//        obj9.setMobileprice("1000");
//        obj9.setImagename(R.drawable.smartphone);
//        holder.add(obj9);
//
//        Model obj10=new Model();
//        obj10.setMobilename("Infinix");
//        obj10.setMobileprice("1000");
//        obj10.setImagename(R.drawable.smartphone);
//        holder.add(obj10);
//
//
//        return  holder;
//    }
}
