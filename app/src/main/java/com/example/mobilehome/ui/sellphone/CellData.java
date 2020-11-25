package com.example.mobilehome.ui.sellphone;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mobilehome.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class CellData extends AppCompatActivity {

    Button btnselectphoto, btnuploadphoto;
    ImageView imageView;
    Bitmap bitmap;
    String encodeImage;
    Spinner spinnerallcities;
    EditText edmobilename, edmobileinfo,edprice,edcontact;
    Button btnsubmait;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cell_data);

        btnselectphoto=findViewById(R.id.selectphoto);
        btnuploadphoto=findViewById(R.id.uploadphoto);
        imageView=findViewById(R.id.imageView);
        spinnerallcities = (Spinner)findViewById(R.id.spinnerCities);
        edmobilename=findViewById(R.id.mobilename);
        edmobileinfo=findViewById(R.id.mobileinfo);
        edprice=findViewById(R.id.price);
        edcontact=findViewById(R.id.contact);
        btnsubmait=findViewById(R.id.submait);

        btnselectphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isStoragePermissionGranted();
                if (!isStoragePermissionGranted()){
                    Toast.makeText(CellData.this, "grant storage permission and retry", Toast.LENGTH_SHORT).show();
                }
               else  {
                    Intent intent=new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent,"Select Image"),39);
               }

            }
        });

        btnuploadphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(CellData.this, "Provide the contact number ", Toast.LENGTH_SHORT).show();
            }
        });
        btnsubmait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="http://192.168.18.8/MobileHome/upload.php";

               final String spinner=spinnerallcities.getSelectedItem().toString();
                final String mobilename= edmobilename.getText().toString();
                final String mobileinfo= edmobileinfo.getText().toString();
                final String price= edprice.getText().toString();
                final String contact= edcontact.getText().toString();


                RequestQueue requestQueue = Volley.newRequestQueue(CellData.this);
                StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(CellData.this, response, Toast.LENGTH_SHORT).show();
                        Log.d("TAGGG", "onResponse: "+ response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CellData.this, "failed"+error.toString(), Toast.LENGTH_SHORT).show();

                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parms=new HashMap<>();
                        parms.put("allcities",spinner);
                        parms.put("mobilename",mobilename);
                        parms.put("mobileinfo",mobileinfo);
                        parms.put("price", price);
                        parms.put("contact", contact);
                        parms.put("image",encodeImage);
                        return parms;
                    }
                };
                requestQueue.add(request);
            }
        });
    }

    private boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(CellData.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 39);
                return false;
            }
        } else {
            return true;
        } }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==39 && resultCode== RESULT_OK && data!=null){
            Uri filePath=data.getData();
            System.out.println("Filepth"+ filePath);
            try {
                InputStream inputStream=getContentResolver().openInputStream(filePath);
                bitmap= BitmapFactory.decodeStream(inputStream);
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageBitmap(bitmap);
                imageStore(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void imageStore(Bitmap bitmap) {
        ByteArrayOutputStream stream= new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
        byte[] imageBytes=stream.toByteArray();
        encodeImage= android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);

    }
}
