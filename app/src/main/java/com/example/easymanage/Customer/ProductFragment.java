package com.example.easymanage.Customer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.easymanage.Order;
import com.example.easymanage.Product;
import com.example.easymanage.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment {
    ListView listViewProducts;
    DatabaseReference DataRefProducts;
    List<Product> products  = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products,container,false);
        listViewProducts = (ListView) view.findViewById(R.id.ProductListView);
        ProductFragment.ProductList adapter = new ProductFragment.ProductList(getActivity(),products);
        listViewProducts.setAdapter(adapter);
        DataRefProducts = FirebaseDatabase.getInstance().getReference("all_product");
        return view ;

    }

    @Override
    public void onStart() {
        super.onStart();
        DataRefProducts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                products.clear();

                for(DataSnapshot productSnapShot : dataSnapshot.getChildren())
                {
                    Product product = productSnapShot.getValue(Product.class);
                    products.add(product);
                }
                ProductFragment.ProductList adapter = new ProductFragment.ProductList(getActivity(),products);
                listViewProducts.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public class ProductList extends ArrayAdapter<Product> {

        private Context context  ;
        private List<Product> ProductList ;

        public ProductList(Context context , List<Product> ProductList)
        {
            super(context, R.layout.product_list_item,ProductList);
            this.context = context ;
            this.ProductList = ProductList ;

        }


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View listViewItem = layoutInflater.inflate(R.layout.product_list_item,parent,false);

            TextView UID =(TextView) listViewItem.findViewById(R.id.product_uid);
            TextView ProductID =(TextView) listViewItem.findViewById(R.id.product_product_name);
            ImageView ProductImage =(ImageView) listViewItem.findViewById(R.id.product_product_image);


            Product product = ProductList.get(position);

            UID.setText(product.getUID());
            ProductID.setText(product.getProductID());
            ProductImage.setImageResource(R.drawable.chair);

            return listViewItem;
        }

    }
}
