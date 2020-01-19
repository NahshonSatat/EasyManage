package com.example.easymanage.Supplier;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.easymanage.LoginFlow.MainActivity;
import com.example.easymanage.Phone;
import com.example.easymanage.R;

public class HomeFragemnt extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ImageView button = (ImageView) view.findViewById(R.id.makeNewProductbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MakeNewProduct.class);
                startActivity(intent);
            }
        });
        ImageView logOutBtn = (ImageView) view.findViewById(R.id.logOutBtnHomeSupplier);
        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        ImageView phone = (ImageView) view.findViewById(R.id.PhoneSupplier);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Phone.class);
                startActivity(intent);
            }
        });

        return view;
    }


}
