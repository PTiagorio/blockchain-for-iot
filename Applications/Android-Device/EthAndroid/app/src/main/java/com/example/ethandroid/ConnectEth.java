package com.example.ethandroid;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.JsonReader;
import android.util.JsonWriter;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.util.JSONPObject;

import org.bouncycastle.asn1.smime.SMIMEEncryptionKeyPreferenceAttribute;
import org.json.JSONObject;
import org.reactivestreams.Subscription;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.rx.Web3jRx;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.Contract;
import org.web3j.tx.Transfer;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.web3j.tx.gas.DefaultGasProvider.GAS_LIMIT;
import static org.web3j.tx.gas.DefaultGasProvider.GAS_PRICE;

public class ConnectEth implements Serializable {

    private static final long serialVersionUID = 1L;
    private Web3j web3;
    //private final String password = "walletPwd";
    private String password;
    private String walletPath;
    private String username;
    private File walletDir;
    private Context context;
    private Credentials credentials;
    private String wAddr;
    private Contract_sol_test smartContract;
    private String contractAddr = "0x2036cda1ef0fecf329a12aa668dae5031c6d8cdc";//"0x21eddf45C64aD7643D3adbF2042E917a60b82beB";
    private String filename;
    private String pk= "9928CE3F5D15DDBA026B855B4D1017E680777B0C17314F66B39E64D391427401";
    //private String privateKey = "9928CE3F5D15DDBA026B855B4D1017E680777B0C17314F66B39E64D391427401";

    public ConnectEth(Context context){
        //walletPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        this.context = context;
        username = "hub";
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        walletDir=path;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean connect(){

        //web3 = Web3j.build(new HttpService()); //default http:localhost:8545
        web3 = Web3j.build(new HttpService("https://kovan.infura.io/v3/b321b2057ccc412da1a6cfc01c158880"));//kovan: kovan.infura.io/v3/b321b2057ccc412da1a6cfc01c158880
        //ropsten: ropsten.infura.io/v3/b321b2057ccc412da1a6cfc01c158880
        try {
            Web3ClientVersion clientVersion = web3.web3ClientVersion().sendAsync().get();
            if(!clientVersion.hasError()){
                //Connected

                Log.d("log","connect working");
                return true;
            }
            else {
                //Show Error
                Log.d("log","erro clientVersion: "+clientVersion.getError());
                return false;
            }


        }
        catch (Exception e) {
            //Show Error
            Log.d("log","erro connect: "+e);
            return false;
        }
    }


    /*public void save(File f){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Downloads.TITLE, f.getName());
        contentValues.put(MediaStore.Downloads.DISPLAY_NAME, f.getName());
        contentValues.put(MediaStore.Downloads.MIME_TYPE, MimeTypeMap.getFileExtensionFromUrl(f.getAbsolutePath()));
        contentValues.put(MediaStore.Downloads.SIZE, f.getTotalSpace());
        contentValues.put(MediaStore.Downloads.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS + File.separator + "Fika");

        ContentResolver database = context.getContentResolver();
        database.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues);
    }*/

    /*public File saveTest(String content){
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File myFile = new File(path, "meta.json");
        try {
            Log.d("log","started json");
            FileOutputStream fOut = new FileOutputStream(myFile, true);

            //OutputStreamWriter osw = new OutputStreamWriter(fOut);
            JSONObject json = new JSONObject(content);
            //osw.write(json.toString());
            //osw.close();
            ObjectOutputStream oos= new ObjectOutputStream(fOut);
            oos.writeObject(json.toString());
            Log.d("log",""+json.toString());
            oos.close();

            fOut.close();
        }catch(Exception e){
            Log.d("log"," erro no testsave "+e);
        }

        return myFile;
    }*/

    private Credentials  importW() {
        if (!WalletUtils.isValidPrivateKey(pk)) {
            Log.d("log","erro na pk");
        }

        Credentials credentials = Credentials.create(pk);


        try {
            String file = WalletUtils.generateWalletFile(
                    "pwd", credentials.getEcKeyPair(), walletDir, false);
            Log.d("log","Wallet file " + file
                    + " successfully created in: " + walletDir + "\n");
            File w = new File(walletDir+"/"+file);
            w.renameTo(new File(walletDir+"/metamaskW.json"));
            File newf = new File(walletDir+"/metamaskW.json");
            Log.d("log","file: "+newf);
            Credentials cred = WalletUtils.loadCredentials("pwd",newf);
            Log.d("log","cred: "+ cred.getAddress());
            return cred;
        } catch (Exception e) {
            Log.d("log"," erro no walletgen: "+e);
            return null;
        }
    }

    public void delMetaMask(){
        File f = new File(walletDir+"/"+"metamaskW.json");
        if(f.exists()){
            if(f.delete())
                Log.d("log","metamaskwalletDeleted");
            else
                Log.d("log","cant delete");
        }
        else {
            Log.d("log", "metamask wallet.json doesnt exist");
        }
    }

    public String requestEther(){

        //String content="{\"address\":\"d68e1912f210296e2b76210d258d4149d20c0f66\",\"id\":\"212abf0f-bb2d-4422-a20c-1d14501b3d88\",\"version\":3,\"crypto\":{\"cipher\":\"aes-128-ctr\",\"ciphertext\":\"5cde8f8fb92eec6e5c94132fc259bec90a0605bb189a5ca7c6b8b46f426831ce\",\"cipherparams\":{\"iv\":\"0ed53ca05898f1e2e653793ca06fb97c\"},\"kdf\":\"scrypt\",\"kdfparams\":{\"dklen\":32,\"n\":262144,\"p\":1,\"r\":8,\"salt\":\"b053a52c860be2406b2d86c4f70b03f6091888e514f0a9e84fda6609053ef49d\"},\"mac\":\"7da3ba406c1b57eabc0795648ecec8340d6da4bff7dc359cb6542421802d7788\"}}";
        try {
            //File x=saveTest(content);
            //Credentials metaCredentials = WalletUtils.loadCredentials("pwd",x);
            //Credentials metaCredentials =WalletUtils.loadJsonCredentials("pwd", content);
            //WalletUtils.loadCredentials()
            Credentials metaCredentials = importW();
            TransactionReceipt transactionReceipt = Transfer.sendFunds(
                    web3, metaCredentials, wAddr,
                    BigDecimal.valueOf(0.1), Convert.Unit.ETHER)
                    .send();
            Log.d("log", "request well: " + transactionReceipt.getStatus());


            delMetaMask();
            return transactionReceipt.getTransactionHash();
        }catch(Exception e){
            Log.d("log","erro no request: "+e);
            return "Request error: "+e;
        }
        //web3.ethSendTransaction(transaction).send();
    }

    public void wallet(String password){


        try{
            File temp = new File(walletDir+"/wallet.json");
            if(!temp.exists()){
                filename = WalletUtils.generateLightNewWalletFile(password,walletDir);
                File w = new File(walletDir+"/"+filename);
                w.renameTo(new File(walletDir+"/wallet.json"));
            }

            credentials = WalletUtils.loadCredentials(password,temp);
            wAddr=credentials.getAddress();
            Log.d("log","well2"+credentials.getAddress());
        }catch(Exception e){
            Log.d("log","erro: "+e);
        }
        /*try{
            this.password=password;
            File[] xx = walletDir.listFiles();

            if(xx.length>1){
                credentials = WalletUtils.loadCredentials(password,xx[0].getAbsoluteFile());
            }
            else {
                WalletUtils.generateLightNewWalletFile(password, walletDir);
                xx = walletDir.listFiles();
                credentials = WalletUtils.loadCredentials(password, xx[0].getAbsoluteFile());
            }
            filename = xx[0].getName();
            wAddr = credentials.getAddress();
            //WalletUtils.generateNewWalletFile(password   , walletDir);

        }
        catch (Exception e){
            //Display an Error
            Log.d("log","erroWallet: "+e);
        }
        */
    }

    /*public String getAddr(String password){
        try {
            credentials = WalletUtils.loadCredentials(password,walletDir+"/"+"UTC--2019-10-05T05-40-11.9Z--1dd2c69132c709f69b646ec446b06661d0bcddec.json");

            Log.d("log","addr well"+", "+credentials.getAddress());

            return credentials.getAddress();
        }
        catch (Exception e){
            //Show Error
            Log.d("log","erroAddr: "+e);
            return "erroAddr: "+e;
        }
    }*/

    public Double getBalance(){
        BigInteger wei;
        BigDecimal balanceInEther;
        try {
            EthGetBalance ethGetBalance = web3
                    .ethGetBalance(this.wAddr, DefaultBlockParameterName.LATEST)
                    .sendAsync()
                    .get();

            wei = ethGetBalance.getBalance();
            balanceInEther = Convert.fromWei(wei.toString(), Convert.Unit.ETHER);
            Log.d("log","balance: "+balanceInEther);
        }catch(Exception e){
            Log.d("log","erro no balance: "+e);
            return 0.0;
        }
        return balanceInEther.doubleValue();
    }

    public String deployContract(){
        try {
            byte[] array= new byte[32];
            array[0] = 'C';
            smartContract = Contract_sol_test.deploy(web3, credentials, DefaultGasProvider.GAS_PRICE,DefaultGasProvider.GAS_LIMIT,"nome").send();
            contractAddr =smartContract.getContractAddress();
            Log.d("log","deploy bom");
            return contractAddr;
        }catch(Exception e){
            Log.d("log","deploy: "+e);
            return "Error";
        }
    }

    public String getContractAddr(){
        return contractAddr;
    }
    public String getwAddr(){
        return wAddr;
    }

    public String loadContract(){
        try {

            smartContract = Contract_sol_test.load(contractAddr,web3,credentials, GAS_PRICE, GAS_LIMIT);
            contractAddr = smartContract.getContractAddress();
            return contractAddr;
        }catch(Exception e){

            return "Error"+e;
        }
    }

    public String Action(String move,String username){
        try {
            Log.d("log","tried.");
            TransactionReceipt transactionReceipt = smartContract.move(move, username).send();
            Log.d("log",transactionReceipt.getStatus());
            return transactionReceipt.getTransactionHash();
        }catch (Exception e){
            Log.d("log","erro: "+e);
            return "erro: "+e;
        }
    }

    public void listen(){
        try{
            smartContract.movedEventFlowable(DefaultBlockParameterName.EARLIEST,DefaultBlockParameterName.LATEST).subscribe(log ->{
                String name = new String(log.name);
                String dir = new String(log.dir);
                String sender = log.sender;
                String addr = log.log.getAddress();

                //executeScript(dir);
            });
        }catch(Exception e){

        }
    }



    /*public Map<String,List<String>> getHistory(){
        Map<String,List<String>> map = new HashMap<>();
        try {
            Tuple2<List<byte[]>, List<byte[]>> lista = smartContract.getHistory().send();
            List<String> values = new ArrayList<>();
            List<String> keys = new ArrayList<>();
            for(int i = 0;i<lista.getValue1().size();i++){
                String value = new String(lista.getValue1().get(i));
                String key = new String(lista.getValue2().get(i));
                if(map.containsKey(key)){
                    List<String> temp = map.get(key);
                    temp.add(value);
                    map.replace(key,temp);
                }
                else{
                    List<String> temp2 = new ArrayList<>();
                    temp2.add(value);
                    map.put(key,temp2);
                }
            }

        }catch (Exception e){
            return map;
        }
        return map;
    }*/
}
