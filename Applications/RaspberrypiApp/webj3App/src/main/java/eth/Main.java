package eth;

import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.websocket.WebSocketService;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.gas.DefaultGasProvider;

import java.io.File;
import java.math.BigInteger;
import java.util.*;


public class Main {
    private Web3j web3;
    //private final String password = "walletPwd";
    private String password;
    private String    walletPath;
    private File walletDir;
    private Credentials credentials;
    private String wAddr;
    private Contract_sol_test smartContract;
    private String contractAddr = "0x2036CDa1eF0FECf329A12AA668DAe5031C6d8cDC"; //rpCtr:0xe62494f187a56969ecfeb56525b4505165499cf9  //kovanCtr: 0x2036CDa1eF0FECf329A12AA668DAe5031C6d8cDC
    private String filename;

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Main m = new Main();

        System.out.println(m.connect());
        //System.out.print(m.connectRpc());
        //m.createW();
        //System.out.println(m.createLightWallet());
        System.out.println(m.loadWallet());
        //System.out.print(m.getBalance());
        //System.out.print(m.deployContract());
        System.out.println(m.loadContract());
        m.websockets();
        //m.getHistory();
        //Map<String,List<String>> map = m.getHistory();
        //System.out.print(m.getHistory());
        /*Thread t = new Thread(){
            @Override
            public void run(){
                m.listen();
            }
        };
        t.start();*/

        /*System.out.println(m.getBalance());
        //System.out.println(m.deployContract());
        System.out.println(m.loadContract());
        System.out.println(m.Action("cima","teste"));

        Map<String,List<String>> map = m.getHistory();
        Iterator<String> it = map.keySet().iterator();
        while(it.hasNext()){
            String user = it.next();
            List<String> l = map.get(user);
            System.out.println(user+": ");
            for(int i=0;i<l.size();i++){
                System.out.print(l.get(i)+"; ");
            }
        }
        */
        //
        /*
        try {
            t.join();
        }catch(Exception e){
            System.out.print("erro main: "+e);
        }
        */
        //m.listen();
        //m.websockets();
        m.exit();
        //System.out.println(m.deployContract());
        //m.exit();
    }

    public String executeScript(String dir){
        String s = "python /home/pi/Documentos/my_rpi/"+dir+".py";    //"scipt+cima; script+baixo; ..."
        //System.out.print(s);
        try {

            //Runtime.getRuntime().exec(path);
            Process p = Runtime.getRuntime().exec(s);
            //System.out.print("done well");
            return "works";
        }catch(Exception e){
            System.err.print(e);
            return "erro"+e;
        }
    }

    //private String privateKey = "9928CE3F5D15DDBA026B855B4D1017E680777B0C17314F66B39E64D391427401";

    public void exit(){
        web3.shutdown();
    }

    public boolean connect(){

        web3 = Web3j.build(new HttpService("https://kovan.infura.io/v3/b321b2057ccc412da1a6cfc01c158880")); //localhost:8545
        try {
            Web3ClientVersion clientVersion = web3.web3ClientVersion().sendAsync().get();
            if(!clientVersion.hasError()){
                //Connected

                System.out.println(web3.web3ClientVersion().getId());
                return true;
            }
            else {
                //Show Error

                return false;
            }


        }
        catch (Exception e) {
            //Show Error

            return false;
        }
    }

    public boolean connectRpc(){
        web3 = Web3j.build(new HttpService()); //localhost:8545
        try {
            Web3ClientVersion clientVersion = web3.web3ClientVersion().sendAsync().get();
            if(!clientVersion.hasError()){
                //Connected

                System.out.println(web3.web3ClientVersion().getId());
                return true;
            }
            else {
                //Show Error

                return false;
            }


        }
        catch (Exception e) {
            //Show Error

            return false;
        }
    }


    public String createW(){
        File dir = new File("/home/luis/Desktop/fika/");
        String walletFile;
        try {
            walletFile = WalletUtils.generateNewWalletFile("pwd", dir);
            credentials = WalletUtils.loadCredentials("pwd",new File(walletFile));
        }catch(Exception e){
            return "error gen: "+e;
        }
        return "done well "+credentials.getAddress();
    }

    public String createLightWallet(){
        File dir = new File("/home/pi/Documentos/my_rpi/");
        String walletFile;
        try {
            walletFile = WalletUtils.generateLightNewWalletFile("pwd", dir);
            credentials = WalletUtils.loadCredentials("pwd",new File(walletFile));
        }catch(Exception e){
            return "error gen: "+e;
        }
        return "done well "+credentials.getAddress();
    }

    public String loadWallet(){
        try {

            credentials = WalletUtils.loadCredentials("pwd","/home/pi/Documentos/my_rpi/UTC--2019-11-04T14-44-20.429000000Z--9c402fd296f4922eadebd405a5cfc33df0416a11.json"); ///home/luis/Desktop/fika/eth-net/db/keystore/UTC--2019-10-08T14-41-08.402000000Z--6c09f8473f6efa2016f46ace8e261c1331bc5f29.json
            wAddr=credentials.getAddress();
            return wAddr;
        }
        catch (Exception e){
            //Show Error

            return "erroAddr: "+e;
        }
    }

    public int getBalance(){
        BigInteger wei;
        try {
            EthGetBalance ethGetBalance = web3
                    .ethGetBalance(wAddr, DefaultBlockParameterName.LATEST).sendAsync().get();

            wei = ethGetBalance.getBalance();
            return wei.intValue();

        }catch(Exception e){
            return -1;
        }
    }
    public String addFunds(){

        //web3.ethGetBalance().
        return "";
    }

    public String deployContract(){
        try {

            smartContract = Contract_sol_test.deploy(web3, credentials, DefaultGasProvider.GAS_PRICE,DefaultGasProvider.GAS_LIMIT,"nome").send();
            contractAddr =smartContract.getContractAddress();

            return contractAddr;
        }catch(Exception e){

            return "Error "+e;
        }
    }

    public String getContractAddr(){
        return contractAddr;
    }

    public String loadContract(){
        try {

            smartContract = Contract_sol_test.load(contractAddr,web3,credentials, DefaultGasProvider.GAS_PRICE,DefaultGasProvider.GAS_LIMIT);
            contractAddr = smartContract.getContractAddress();
            return contractAddr;
        }catch(Exception e){

            return "Error";
        }
    }

    public static Bytes32 stringToBytes32(String string) {
        byte[] byteValue = string.getBytes();
        byte[] byteValueLen32 = new byte[32];
        System.arraycopy(byteValue, 0, byteValueLen32, 0, byteValue.length);
        return new Bytes32(byteValueLen32);
    }

    public String Action(String move,String username){
        try {
            //Bytes32 m = stringToBytes32(move);
            //Bytes32 u = stringToBytes32(username);
            TransactionReceipt transactionReceipt = smartContract.move(move, username).send();
            return transactionReceipt.getTransactionHash();
        }catch (Exception e){
            return "erro "+e;
        }
    }

    public void listen(){
        try{
            smartContract.movedEventFlowable(DefaultBlockParameterName.EARLIEST,DefaultBlockParameterName.LATEST).doOnError(error -> System.err.println("The error message is: " + error.getMessage())).subscribe(log ->{
                        String name = log.name;
                        String dir = log.dir;
                        String sender = log.sender;
                        String addr = log.log.getAddress();
                        System.out.println(name+", "+dir+", "+sender+", "+addr);
                        //executeScript(dir);
                    }
                    ,error ->System.err.print("erro: "+error));
        }catch(Exception e){
            System.out.println(e);
        }
    }

    private int sending=0;
    private String lastAddr="empty";

    public void websockets(){
        WebSocketService webSocketService = new WebSocketService("wss://kovan.infura.io/ws/v3/b321b2057ccc412da1a6cfc01c158880", true);
        try {
            webSocketService.connect();
            Web3j web3j = Web3j.build(webSocketService);

            smartContract = Contract_sol_test.load(contractAddr,web3j,credentials, DefaultGasProvider.GAS_PRICE,DefaultGasProvider.GAS_LIMIT);

            smartContract.movedEventFlowable(DefaultBlockParameterName.LATEST,DefaultBlockParameterName.LATEST).subscribe(log -> {
                        String name = log.name;
                        String dir = log.dir;
                        String sender = log.sender;
                        String addr = log.log.getAddress();

                        if (lastAddr != addr) {
                            synchronized (lastAddr) {
                                Thread.sleep(2000);
                                lastAddr = addr;
                            }
                        }
                        else {
                            /*synchronized ((Object) sending){
                                if (sending == 1) {
                                    Thread.sleep(3000);
                                }
                                sending = 1;
                            }*/


                            /*synchronized ((Object) sending) {
                                sending = 0;
                            }*/
                        }
                        System.out.println(name + ", " + dir + ", " + sender + ", " + addr);
                        executeScript(name);
                    }
            ,error ->System.err.print("erro: "+error));

        }catch(Exception e){
            System.err.print("erro socket: "+e);
        }
    }

    public void listen2() {

    }

    public Map<String,List<String>> getHistory(){
        Map<String,List<String>> map = new HashMap<>();
        System.out.println("cheguei");
        try {

            Tuple2<List<String>, List<String>> lista = smartContract.getHistory().send();
            //System.out.print(lista);
            if(lista.getSize()>0) {
                Iterator<String> it =lista.component1().iterator();
                while (it.hasNext()){
                    System.out.println(it.next());
                }
                //System.out.print(lista.component1().get(0));
                System.out.println(lista.component1().size());
            }

        }catch (Exception e){
            System.out.print("errooooooooooooooooo: "+e);
            List<String> x = new ArrayList<String>();
            x.add("erro "+e);
            map.put("erro",x);
            return map;
        }
        return map;
    }
}
