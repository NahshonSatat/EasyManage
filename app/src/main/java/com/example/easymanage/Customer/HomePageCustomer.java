package com.example.easymanage.Customer;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.easymanage.R;
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
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutCustomer,new HomeFragemntCustomer()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null ;
            switch (menuItem.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment = new HomeFragemntCustomer();
                    break;
                case R.id.navigation_orders:
                    selectedFragment = new OrderFragmentCustomer();
                    break;
                case R.id.navigation_products:
                    selectedFragment = new ProductFragmentCustomer();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutCustomer,selectedFragment).commit();
            return true;
        }
    };
}
