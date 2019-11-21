package com.example.ethandroid;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HelpActivity extends Activity {

    TextView tv;
    EditText et;
    TextView tv2;
    //ConnectEth con = null;
    String wAddr = null; // or other values
    String username = null;
    String balance  = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        et = findViewById(R.id.usernameET);
        Bundle b = getIntent().getExtras();

        if(b != null) {
            wAddr = b.getString("wAddr");
            username = b.getString("username");
            balance = b.getString("balance");
        }
        else
            Log.d("log","Erro");
        if(wAddr != null) {
            tv = findViewById(R.id.texto2);
            tv.setText("Wallet Address: " + wAddr);
        }
        if(username != null)
        {
            et = findViewById(R.id.usernameET);
            et.setText(username);
        }
        if(balance!=null){
            tv2 = findViewById(R.id.texto3);
            tv2.setText("Wallet balance: "+balance);
        }

    }

    public void onRequest(View view){
        Toast.makeText(this,"Requesting Ether. This may take a while",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        if(username != null) {
            username = et.getText().toString();
            intent.putExtra("username", username);
        }
        intent.putExtra("request",1);
        setResult(RESULT_OK, intent);

        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        if(username != null) {
            username = et.getText().toString();
            intent.putExtra("username", username);
        }
        setResult(RESULT_OK, intent);
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onStop() {
        Intent intent = new Intent();
        if(username != null) {
            username = et.getText().toString();
            intent.putExtra("username", username);
        }
        setResult(RESULT_OK, intent);
        super.onStop();
    }
}
