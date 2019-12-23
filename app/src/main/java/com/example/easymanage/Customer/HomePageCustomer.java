package com.example.easymanage.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.easymanage.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import com.example.easymanage.Supplier.HomeFragemnt;
import com.example.easymanage.Supplier.OrderFragment;
import com.example.easymanage.Supplier.ProductFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePageCustomer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_customer);

        BottomNavigationView bottomNav = findViewById(R.id.navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new HomeFragemnt()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null ;
            switch (menuItem.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment = new HomeFragemnt();
                    break;
                case R.id.navigation_orders:
                    selectedFragment = new OrderFragment();
                    break;
                case R.id.navigation_products:
                    selectedFragment = new ProductFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,selectedFragment).commit();
            return true;
        }
    };
}
