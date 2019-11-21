package eth;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.5.5.
 */
@SuppressWarnings("rawtypes")
public class ContractV2_sol_test extends Contract {
    private static final String BINARY = "608060405260006004553480156200001657600080fd5b5060405162000e4438038062000e44833981016040819052620000399162000169565b600080546001600160a01b0319163317905580516200006090600190602084019062000068565b50506200022b565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10620000ab57805160ff1916838001178555620000db565b82800160010185558215620000db579182015b82811115620000db578251825591602001919060010190620000be565b50620000e9929150620000ed565b5090565b6200010a91905b80821115620000e95760008155600101620000f4565b90565b600082601f8301126200011f57600080fd5b8151620001366200013082620001d0565b620001a9565b915080825260208301602083018583830111156200015357600080fd5b62000160838284620001f8565b50505092915050565b6000602082840312156200017c57600080fd5b81516001600160401b038111156200019357600080fd5b620001a1848285016200010d565b949350505050565b6040518181016001600160401b0381118282101715620001c857600080fd5b604052919050565b60006001600160401b03821115620001e757600080fd5b506020601f91909101601f19160190565b60005b8381101562000215578181015183820152602001620001fb565b8381111562000225576000848401525b50505050565b610c09806200023b6000396000f3fe608060405234801561001057600080fd5b50600436106100625760003560e01c80632ae86866146100675780633dc735da14610085578063aa15efc81461009a578063d56b2889146100b0578063f61b2d49146100b8578063fd9527c8146100cb575b600080fd5b61006f6100d3565b60405161007c9190610ada565b60405180910390f35b61009861009336600461088f565b610124565b005b6100a26102b7565b60405161007c929190610a61565b61009861057c565b61006f6100c6366004610869565b6105a5565b61006f6105c0565b600080805b6100e06105c0565b81101561011d57610111600382815481106100f757fe5b6000918252602090912001546001600160a01b03166105a5565b909101906001016100d8565b5090505b90565b61012c6105c6565b5060408051808201909152828152602081018290526004541561016a5760405162461bcd60e51b815260040161016190610aca565b60405180910390fd5b60016004556000805b6003548110156101b3576003818154811061018a57fe5b6000918252602090912001546001600160a01b03163314156101ab57600191505b600101610173565b50806101fc57600380546001810182556000919091527fc2575a0e9e593c00f959f8c92f12db2869c3395a3b0502d05e2516446f71f85b0180546001600160a01b031916331790555b33600090815260026020818152604083208054600181018083558286529483902087518051939695899593029091019261023992849201906105e0565b50602082810151805161025292600185019201906105e0565b505033600090815260026020526040902083546102749350909150839061065e565b507f4de756f107925b01c3b4d38b79fa03a10c7e7c055c17c0e55829125332dea70a8486336040516102a893929190610a86565b60405180910390a15050505050565b60608060006102c46100d3565b9050600080905060608260405190808252806020026020018201604052801561030157816020015b60608152602001906001900390816102ec5790505b50905060608360405190808252806020026020018201604052801561033a57816020015b60608152602001906001900390816103255790505b50905060005b6103486105c0565b8110156105705760005b610362600383815481106100f757fe5b81101561056757600260006003848154811061037a57fe5b60009182526020808320909101546001600160a01b0316835282019290925260400190208054829081106103aa57fe5b60009182526020918290206002918202018054604080516001831615610100026000190190921693909304601f81018590048502820185019093528281529290919083018282801561043d5780601f106104125761010080835404028352916020019161043d565b820191906000526020600020905b81548152906001019060200180831161042057829003601f168201915b505050505084868151811061044e57fe5b6020026020010181905250600260006003848154811061046a57fe5b60009182526020808320909101546001600160a01b03168352820192909252604001902080548290811061049a57fe5b90600052602060002090600202016001018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561053f5780601f106105145761010080835404028352916020019161053f565b820191906000526020600020905b81548152906001019060200180831161052257829003601f168201915b505050505083868151811061055057fe5b602090810291909101015260019485019401610352565b50600101610340565b50909450925050509091565b60045460011461059e5760405162461bcd60e51b815260040161016190610aba565b6000600455565b6001600160a01b031660009081526002602052604090205490565b60035490565b604051806040016040528060608152602001606081525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061062157805160ff191683800117855561064e565b8280016001018555821561064e579182015b8281111561064e578251825591602001919060010190610633565b5061065a9291506106fd565b5090565b8280548282559060005260206000209060020281019282156106f15760005260206000209160020282015b828111156106f1578254839083906106b7908290849060026000196101006001841615020190911604610717565b50600182018160010190805460018160011615610100020316600290046106df929190610717565b50505091600201919060020190610689565b5061065a92915061078c565b61012191905b8082111561065a5760008155600101610703565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610750578054855561064e565b8280016001018555821561064e57600052602060002091601f016020900482015b8281111561064e578254825591600101919060010190610771565b61012191905b8082111561065a5760006107a682826107bd565b6107b46001830160006107bd565b50600201610792565b50805460018160011615610100020316600290046000825580601f106107e35750610801565b601f01602090049060005260206000209081019061080191906106fd565b50565b803561080f81610bb2565b92915050565b600082601f83011261082657600080fd5b813561083961083482610b0f565b610ae8565b9150808252602083016020830185838301111561085557600080fd5b610860838284610b6c565b50505092915050565b60006020828403121561087b57600080fd5b60006108878484610804565b949350505050565b600080604083850312156108a257600080fd5b823567ffffffffffffffff8111156108b957600080fd5b6108c585828601610815565b925050602083013567ffffffffffffffff8111156108e257600080fd5b6108ee85828601610815565b9150509250929050565b60006109048383610988565b9392505050565b61091481610b5b565b82525050565b600061092582610b3d565b61092f8185610b41565b93508360208202850161094185610b37565b8060005b8581101561097b578484038952815161095e85826108f8565b945061096983610b37565b60209a909a0199925050600101610945565b5091979650505050505050565b600061099382610b3d565b61099d8185610b41565b93506109ad818560208601610b78565b6109b681610ba8565b9093019392505050565b60006109cd601083610b41565b6f526f626f74206e6f74206d6f76696e6760801b815260200192915050565b60006109f9604483610b41565b7fc39a6c74696d6f206d6f76696d656e746f2061696e646120612073657220707281527f6f6365737361646f2070656c6120426c6f636b636861696e2c206167756172646020820152633297171760e11b604082015260600192915050565b61091481610121565b60408082528101610a72818561091a565b90508181036020830152610887818461091a565b60608082528101610a978186610988565b90508181036020830152610aab8185610988565b9050610887604083018461090b565b6020808252810161080f816109c0565b6020808252810161080f816109ec565b6020810161080f8284610a58565b60405181810167ffffffffffffffff81118282101715610b0757600080fd5b604052919050565b600067ffffffffffffffff821115610b2657600080fd5b506020601f91909101601f19160190565b60200190565b5190565b90815260200190565b60006001600160a01b03821661080f565b600061080f82600061080f82610b4a565b82818337506000910152565b60005b83811015610b93578181015183820152602001610b7b565b83811115610ba2576000848401525b50505050565b601f01601f191690565b610bbb81610b4a565b811461080157600080fdfea365627a7a72315820fbf39a132a75f5294c0c3c108ef22db28219d0ed8c235539e22165d5c8a465466c6578706572696d656e74616cf564736f6c634300050c0040";

    public static final String FUNC_FINISH = "finish";

    public static final String FUNC_GETCOUNTADDR = "getCountAddr";

    public static final String FUNC_GETCOUNTSTRUCT = "getCountStruct";

    public static final String FUNC_GETHISTORY = "getHistory";

    public static final String FUNC_GETSIZE = "getsize";

    public static final String FUNC_MOVE = "move";

    public static final Event MOVED_EVENT = new Event("Moved", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}));
    ;

    @Deprecated
    protected ContractV2_sol_test(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ContractV2_sol_test(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ContractV2_sol_test(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ContractV2_sol_test(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<MovedEventResponse> getMovedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(MOVED_EVENT, transactionReceipt);
        ArrayList<MovedEventResponse> responses = new ArrayList<MovedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            MovedEventResponse typedResponse = new MovedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.dir = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.name = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.sender = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<MovedEventResponse> movedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, MovedEventResponse>() {
            @Override
            public MovedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(MOVED_EVENT, log);
                MovedEventResponse typedResponse = new MovedEventResponse();
                typedResponse.log = log;
                typedResponse.dir = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.name = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.sender = (String) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<MovedEventResponse> movedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(MOVED_EVENT));
        return movedEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> finish() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_FINISH, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> getCountAddr() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETCOUNTADDR, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getCountStruct(String addr) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETCOUNTSTRUCT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, addr)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Tuple2<List<String>, List<String>>> getHistory() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETHISTORY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Utf8String>>() {}, new TypeReference<DynamicArray<Utf8String>>() {}));
        return new RemoteFunctionCall<Tuple2<List<String>, List<String>>>(function,
                new Callable<Tuple2<List<String>, List<String>>>() {
                    @Override
                    public Tuple2<List<String>, List<String>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<List<String>, List<String>>(
                                convertToNative((List<Utf8String>) results.get(0).getValue()), 
                                convertToNative((List<Utf8String>) results.get(1).getValue()));
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> getsize() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETSIZE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> move(String direction, String name) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_MOVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(direction), 
                new org.web3j.abi.datatypes.Utf8String(name)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static ContractV2_sol_test load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ContractV2_sol_test(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ContractV2_sol_test load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ContractV2_sol_test(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ContractV2_sol_test load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ContractV2_sol_test(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ContractV2_sol_test load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ContractV2_sol_test(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ContractV2_sol_test> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String cName) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(cName)));
        return deployRemoteCall(ContractV2_sol_test.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<ContractV2_sol_test> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String cName) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(cName)));
        return deployRemoteCall(ContractV2_sol_test.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ContractV2_sol_test> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String cName) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(cName)));
        return deployRemoteCall(ContractV2_sol_test.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ContractV2_sol_test> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String cName) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(cName)));
        return deployRemoteCall(ContractV2_sol_test.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class MovedEventResponse extends BaseEventResponse {
        public String dir;

        public String name;

        public String sender;
    }
}
