package com.example.easymanage;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Phone extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
    }
    public void onCall(View view) {
        EditText editText = (EditText) findViewById(R.id.PhoneNumber);
        Intent callIntent = new Intent(Intent.ACTION_CALL); //use ACTION_CALL class
        callIntent.setData(Uri.parse("tel:" + editText.getText().toString()));    //this is the phone number calling
        //check permission
        //If the device is running Android 6.0 (API level 23) and the app's targetSdkVersion is 23 or higher,
        //the system asks the user to grant approval.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            //request permission from user if the app hasn't got the required permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},   //request specific permission from user
                    10);
            return;
        } else {     //have got permission
            try {
                startActivity(callIntent);  //call activity and make phone call
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(getApplicationContext(), "yourActivity is not founded", Toast.LENGTH_SHORT).show();
            }
        }
        finish();
    }
}
