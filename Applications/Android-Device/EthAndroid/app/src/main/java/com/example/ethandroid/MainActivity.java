package com.example.ethandroid;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.net.Socket;
import java.security.Provider;
import java.security.Security;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity  {

    private ConnectEth con;
    private String name="ola";
    private Map<String, List<String>> log;
    private boolean state=false,erro=false;
    private int nvidas=3;
    View textview2,hearts;
    View[] heart = {null,null,null};
    public MainActivity() {
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat
                .checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            //if (ActivityCompat.shouldShowRequestPermissionRationale(this,
            //        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
           // } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            //}
        }
        else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        try {
            setupBouncyCastle();

        }catch(Exception e) {
            Log.d("log", "erro main: " + e);
        }

        hearts = findViewById(R.id.hearts);
        heart[0] = findViewById(R.id.heart1);
        heart[1] = findViewById(R.id.heart2);
        heart[2] = findViewById(R.id.heart3);
        //((ViewGroup) hearts.getParent()).removeView(hearts);
        //((ViewGroup) hearts.getParent()).removeView(hearts);
        hearts.setVisibility(View.GONE);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
            {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    connect();
                    wallet();
                    Log.d("log","b: "+con.getBalance());
                    if(con.getBalance()<=0.0){
                        new RequestThread().execute();
                    }
                    load();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this,"Permissions not granted, cant't load or create Wallet.json",Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    /*@Override
    protected void onStart() {
        super.onStart();
        TextView tv;
        try{
            //Thread.sleep(5000);
        }catch (Exception e){
            Log.d("log","erro main: "+e);
        }
        tv = findViewById(R.id.texto);
        tv.setText("Wallet: " + con.getwAddr() + "\nSmart Contract Adress: " + con.getContractAddr());
    }*/

   /* public void onCima(View view){
        Toast.makeText(this,"Teste",Toast.LENGTH_LONG).show();
    }*/


    public void connect(){
        try{
             con = new ConnectEth(this);
            if (con.connect()) {
                Toast.makeText(this, "Connnected ", Toast.LENGTH_LONG).show();
            }
        }catch(Exception e){
            Toast.makeText(this,"Error connecting to Ethereum node, "+e,Toast.LENGTH_LONG).show();
        }
    }

    public void wallet(){
        try{
            con.wallet("pwd");
            Toast.makeText(this, "wallet imported: "+con.getwAddr(), Toast.LENGTH_SHORT).show();
            Log.d("log","addr: "+con.getwAddr());
        }catch(Exception e){
            Log.d("log","erro: "+e);
            Toast.makeText(this,"Error loading wallet, "+e,Toast.LENGTH_LONG).show();
        }

    }

    public String getWalletAddr(){
        return con.getwAddr();
    }

    public String getContractAddr(){
        return con.getContractAddr();
    }

    /*public void onAddr(View view){
        try{
            con.getAddr("pwd");
            Toast.makeText(this, "Your address is " + con.getAddr("pwd"), Toast.LENGTH_LONG).show();
        }catch(Exception e){
            Toast.makeText(this,"erro connect, "+e,Toast.LENGTH_LONG).show();
        }
    }*/

    /*public void onDeploy(View view){

        try{
            //con.deployContract();
            Background back = new Background(con);
            back.start();
            Toast.makeText(this, con.getContractAddr(), Toast.LENGTH_LONG).show();
        }catch(Exception e){
            Toast.makeText(this,"erro deploy, "+e,Toast.LENGTH_LONG).show();
        }
    }*/

    public void load(){
        try{
            String t = con.loadContract();
            Toast.makeText(this, t, Toast.LENGTH_LONG).show();
        }catch(Exception e){
            Toast.makeText(this,"Error loadingSmart Contract, "+e,Toast.LENGTH_LONG).show();
        }
    }


    /*public void onHistory(View view){
        Toast.makeText(this,""+con.getHistory(), Toast.LENGTH_LONG).show();
    }*/
    public void lose(){
        Log.d("log","Game Over");
        Toast.makeText(this,"Game Over",Toast.LENGTH_LONG).show();
    }

    public void updateHearts(){
        if(!state){
            //textview = findViewById(R.id.genView);
            textview2 = findViewById(R.id.texto);
            //textview2.getParent()).removeView(textview2);
            textview2.setVisibility(View.GONE);
            hearts.setVisibility(View.VISIBLE);
            state = true;
        }
        else
        if(erro){
            switch(nvidas){
                case 0:
                    break;
                case 1:
                    heart[nvidas-1].setVisibility(View.GONE);
                    nvidas--;
                    lose();
                    break;
                case 2: case 3:
                    heart[nvidas-1].setVisibility(View.GONE);
                    nvidas--;
                    break;
                default:
                    Log.d("log","error");
                    break;

            }
        }
    }
    public void onHelp(View view) {
        Intent intent = new Intent(this, HelpActivity.class);
        if (con != null){
            intent.putExtra("wAddr", con.getwAddr());
            intent.putExtra("username", con.getUsername());
            intent.putExtra("balance",con.getBalance().toString()                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       );
            //intent.putExtra("connectEth", con);
        }
        //intent.putExtra("wAddr","invalid wallet address");
        startActivityForResult(intent,0);
        //con.setUsername();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (data.hasExtra("username")) {
                con.setUsername(data.getExtras().getString("username"));
            }
            if(data.hasExtra("request"))
            {
                if(con.getBalance()<1.1) {
                    new RequestThread().execute();
                    Toast.makeText(this,"requested 0.1 ether",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this,"Wallet balance is too high to request ether",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    public void onUp(View view){
        Toast.makeText(this, "Move forward, Username: "+con.getUsername(), Toast.LENGTH_SHORT).show();
        //updateHearts();
        callAction("cima");
    }

    public void onDown(View view){
        Toast.makeText(this, "Move backward, Username: "+con.getUsername(), Toast.LENGTH_SHORT).show();
        callAction("baixo");
    }
    public void onRight(View view){
        Toast.makeText(this, "Turn right, Username: "+con.getUsername(), Toast.LENGTH_SHORT).show();
        callAction("direita");
    }
    public void onLetf(View view){
        Toast.makeText(this, "Turn left, Username: "+con.getUsername(), Toast.LENGTH_SHORT).show();
        callAction("esquerda");
    }


    public void callAction(String dir){
        try {
            new ActionThread().execute(dir);
        }catch(Exception e){
            Toast.makeText(this,"Error ActionThread: "+e,Toast.LENGTH_SHORT).show();
        }
    }
    /*
    public void getHistory(){
        log = con.getHistory();
    }*/


    private void setupBouncyCastle() {
        final Provider provider = Security.getProvider(BouncyCastleProvider.PROVIDER_NAME);
        if (provider == null) {
            // Web3j will set up the provider lazily when it's first used.
            return;
        }
        if (provider.getClass().equals(BouncyCastleProvider.class)) {
            // BC with same package name, shouldn't happen in real life.
            return;
        }
        // Android registers its own BC provider. As it might be outdated and might not include
        // all needed ciphers, we substitute it with a known BC bundled in the app.
        // Android's BC has its package rewritten to "com.android.org.bouncycastle" and because
        // of that it's possible to have another BC implementation loaded in VM.
        Security.removeProvider(BouncyCastleProvider.PROVIDER_NAME);
        Security.insertProviderAt(new BouncyCastleProvider(), 1);
    }

    public class ActionThread extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... strings) {
            String str;
            Log.d("log","started");
            str=con.Action(strings[0],con.getUsername());
            Log.d("log","ended...");
            return str;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getApplicationContext(),"Transaction hash: "+s, Toast.LENGTH_LONG).show();
            super.onPostExecute(s);
        }
    }

    public class RequestThread extends AsyncTask<Void,String,String>{
        @Override
        protected String doInBackground(Void... voids) {
            String str;
            Log.d("log","started");
            str = con.requestEther();
            Log.d("log","ended...");
            return str;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getApplicationContext(),"transaction hash: "+s,Toast.LENGTH_SHORT).show();
            super.onPostExecute(s);
        }
    }
}

