package com.example.mobilehome.ui.sellphone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mobilehome.R;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Singup extends AppCompatActivity  implements View.OnClickListener {

    EditText edname, edusername, edpassword, edrepassword, edcnic;
    Button login, msignup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        edname=findViewById(R.id.edname);
        edusername=findViewById(R.id.edusername);
        edpassword=findViewById(R.id.edpassword);
        edrepassword=findViewById(R.id.edrepassword);
        edcnic=findViewById(R.id.edCNIC);

        msignup=findViewById(R.id.signup);
        msignup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.signup:
               final String name= edname.getText().toString();
               final String username= edusername.getText().toString();
               final String password= edpassword.getText().toString();
                String repassword= edrepassword.getText().toString();
                final String cnic=edcnic.getText().toString();

                if (password.length()<6){
                    Toast.makeText(this, "Password is too short, at least 6 character required", Toast.LENGTH_SHORT).show();
                    return; }
                if (!password.equals(repassword)){
                    Toast.makeText(this, "Password doesn't match", Toast.LENGTH_SHORT).show();
                    return; }

//                String url="http://localhost/MobileHome/register.php";
                String url="http://192.168.18.8/MobileHome/register.php";
//                String url="http://192.168.43.201/MobileHome/register.php";
                RequestQueue queue = Volley.newRequestQueue(this);
                StringRequest request=new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equals("fialed"))
                                {
                                    Toast.makeText(Singup.this, "Something wrong", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(Singup.this, "your are registered now", Toast.LENGTH_SHORT).show();
                                }

                            }
                        },

                        new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("Error", "onErrorResponse: "+error.toString() );

                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map=new HashMap<>();
                        map.put("name",name);
                        map.put("username",username);
                        map.put("password",password);
                        map.put("cnic",cnic);
                        return map;
                    }
                };
                queue.add(request);

                break;
        }

    }
}
