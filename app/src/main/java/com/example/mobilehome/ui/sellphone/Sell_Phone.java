package com.example.mobilehome.ui.sellphone;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mobilehome.R;

public class Sell_Phone extends Fragment implements View.OnClickListener {

    EditText  name, username, password;
    Button login, signup;
    TextView skip;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.sellphone, container, false);


        username=root.findViewById(R.id.edusername);
        password=root.findViewById(R.id.edpassword);
        skip=root.findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(), CellData.class);
                startActivity(i);
            }
        });



        login=root.findViewById(R.id.login);
        login.setOnClickListener(this);
        signup=root.findViewById(R.id.signup);
        signup.setOnClickListener(this);

        return root;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.signup:
                Toast.makeText(getContext(), "Signup button is pressed", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getContext(), Singup.class);
                startActivity(i);
                break;
            case R.id.login:
                Toast.makeText(getActivity(), "Logn is Pressed", Toast.LENGTH_SHORT).show();

        }

    }
}
