package com.example.easymanage.Supplier;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.easymanage.LoginFlow.MainActivity;
import com.example.easymanage.Product;
import com.example.easymanage.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment {
    ListView listViewProducts;
    DatabaseReference DataRefOrders;
    List<Product> products  = new ArrayList<>();
    Context mActivity  = getActivity();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products,container,false);
        listViewProducts = (ListView) view.findViewById(R.id.OrdersListViewProduct);
        ProductList adapter = new ProductList(mActivity,products);
        listViewProducts.setAdapter(adapter);
        DataRefOrders = FirebaseDatabase.getInstance().getReference("supplier_products").child(MainActivity.user.getUid());
        listViewProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product product = products.get(position);
                Intent intent = new Intent(mActivity,ProductView.class);
                intent.putExtra("Product", product);
                startActivity(intent);
            }
        });
        return view ;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity){
            mActivity =(Activity) context;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        DataRefOrders.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                products.clear();

                for(DataSnapshot orderSnapShot : dataSnapshot.getChildren())
                {
                    Product product = orderSnapShot.getValue(Product.class);
                    products.add(product);
                }
                ProductList adapter = new ProductList(mActivity,products);
                listViewProducts.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public class ProductList extends ArrayAdapter<Product> {

        private Context context  ;
        private List<Product> ProductsList ;

        public ProductList(Context context , List<Product> ProductsList)
        {
            super(context, R.layout.order_list_item,ProductsList);
            this.context = context ;
            this.ProductsList = ProductsList ;

        }


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View listViewItem = layoutInflater.inflate(R.layout.order_list_item,parent,false);

            TextView Decripction =(TextView) listViewItem.findViewById(R.id.order_uid);
            final TextView ProductName =(TextView) listViewItem.findViewById(R.id.order_product_name);
            ImageView ProductImage =(ImageView) listViewItem.findViewById(R.id.order_product_image);


            Product product = ProductsList.get(position);

            Decripction.setText(product.getDescription());
            ProductName.setText(product.getProductName());
            ProductImage.setImageResource(R.drawable.chair);

            return listViewItem;
        }

    }
}
