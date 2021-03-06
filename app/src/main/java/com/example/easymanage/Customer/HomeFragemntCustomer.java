package com.example.easymanage.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.easymanage.LoginFlow.MainActivity;
import com.example.easymanage.Phone;
import com.example.easymanage.R;
import com.example.easymanage.Supplier.MakeNewProduct;

public class HomeFragemntCustomer extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_customer, container, false);
        ImageView button = (ImageView) view.findViewById(R.id.makeNewProductbtnCustomer);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomePageCustomer.class);
                startActivity(intent);
            }
        });
        ImageView logOutBtn = (ImageView) view.findViewById(R.id.logOutBtnHomeCustomer);
        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        ImageView phone = (ImageView) view.findViewById(R.id.PhoneCustomer);
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
