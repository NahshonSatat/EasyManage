package com.example.easymanage.Supplier;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.easymanage.LoginFlow.MainActivity;
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

public class OrderFragment extends Fragment {

    ListView listViewOrders;
    DatabaseReference DataRefOrders;
    List<Order> orders  = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order,container,false);
        listViewOrders = (ListView) view.findViewById(R.id.OrdersListView);
        OrderList adapter = new OrderList(getActivity(),orders);
        listViewOrders.setAdapter(adapter);
        DataRefOrders = FirebaseDatabase.getInstance().getReference("supplier_orders");
        return view ;

    }

    @Override
    public void onStart() {
        super.onStart();
        DataRefOrders.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                orders.clear();

                for(DataSnapshot orderSnapShot : dataSnapshot.getChildren())
                {
                    Order order = orderSnapShot.getValue(Order.class);
                    orders.add(order);
                }
                OrderList adapter = new OrderList(getActivity(),orders);
                listViewOrders.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public class OrderList extends ArrayAdapter<Order> {

        private Context context  ;
        private List<Order> OrdersList ;

        public OrderList(Context context , List<Order> OrdersList)
        {
            super(context, R.layout.order_list_item,OrdersList);
            this.context = context ;
            this.OrdersList = OrdersList ;

        }


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View listViewItem = layoutInflater.inflate(R.layout.order_list_item,parent,false);

            TextView UID =(TextView) listViewItem.findViewById(R.id.order_uid);
            final TextView ProductID =(TextView) listViewItem.findViewById(R.id.order_product_name);
            ImageView ProductImage =(ImageView) listViewItem.findViewById(R.id.order_product_image);


            final Order order = OrdersList.get(position);

            UID.setText(order.getUID());


            ProductImage.setImageResource(R.drawable.chair);
            DataRefOrders = FirebaseDatabase.getInstance().getReference("products").child(order.getProductID());
            DataRefOrders.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Product product = dataSnapshot.getValue(Product.class);
                    ProductID.setText( product.getProductName());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            return listViewItem;
        }

    }
}
