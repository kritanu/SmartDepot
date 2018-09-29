package com.example.shivendra.barcodescanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //View Objects
    private Button buttonScan , blockButton;
    private TextView textViewName, textViewAddress, hideAddress, lastname, hidelastname,changeretail;

    //qr code scanner object
    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getSupportActionBar().hide();


        setContentView(R.layout.activity_main);

        //View objects
        buttonScan = (Button) findViewById(R.id.buttonScan);
        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewAddress = (TextView) findViewById(R.id.textViewAddress);
        hideAddress = (TextView) findViewById(R.id.addressText);
        hidelastname = findViewById(R.id.textLastname);
        lastname = findViewById(R.id.lastname);
        blockButton = (Button) findViewById(R.id.Blockbutton);
        changeretail = findViewById(R.id.retailer_change);


        blockButton.setVisibility(View.GONE);
        hidelastname.setVisibility(View.GONE);
        hideAddress.setVisibility(View.GONE);



        //intializing scan object
        qrScan = new IntentIntegrator(this);

        //attaching onclick listener
        buttonScan.setOnClickListener(this);
    }

    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                try {
                    //converting the data to json
                    final JSONObject obj = new JSONObject(result.getContents());
                    //setting values to textviews
                    changeretail.setText("Retailer Detail");
                    hidelastname.setVisibility(View.VISIBLE);
                    hideAddress.setVisibility(View.VISIBLE);
                    textViewName.setText(obj.getString("fname"));
                    textViewAddress.setText(obj.getString("address"));
                    lastname.setText(obj.getString("lname"));
                    blockButton.setVisibility(View.VISIBLE);

                    final Intent intent = new Intent(MainActivity.this,WebViewActivity.class);
                    final Bundle extras = new Bundle();

                    blockButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            try {
                                extras.putString("fName",obj.getString("fname"));
                                extras.putString("lName",obj.getString("lname"));
                                extras.putString("address",obj.getString("address"));
//                                Log.d("Testcheck",obj.getString("fname"));
//                                Log.d("Testcheck",obj.getString("lname"));
//                                Log.d("Testcheck",obj.getString("address"));

                                intent.putExtras(extras);
                                startActivity(intent);
                                finish();

                            } catch (JSONException e) {
                                Toast.makeText(MainActivity.this, "Values not found", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                    //if control comes here
                    //that means the encoded format not matches
                    //in this case you can display whatever data is available on the qrcode
                    //to a toast
                    //Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                    changeretail.setText("Contents: ");
                    textViewName.setText(result.getContents());
                    hideAddress.setText(null);
                    hidelastname.setText(null);

                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    @Override
    public void onClick(View view) {
        //initiating the qr code scan
        qrScan.initiateScan();
        qrScan.setOrientationLocked(true);
    }
}
