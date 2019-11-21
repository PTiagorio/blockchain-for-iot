package com.example.ethandroid;

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
public class Contract_sol_test extends Contract {
    private static final String BINARY = "60806040523480156200001157600080fd5b5060405162000d1d38038062000d1d833981016040819052620000349162000164565b600080546001600160a01b0319163317905580516200005b90600190602084019062000063565b505062000226565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10620000a657805160ff1916838001178555620000d6565b82800160010185558215620000d6579182015b82811115620000d6578251825591602001919060010190620000b9565b50620000e4929150620000e8565b5090565b6200010591905b80821115620000e45760008155600101620000ef565b90565b600082601f8301126200011a57600080fd5b8151620001316200012b82620001cb565b620001a4565b915080825260208301602083018583830111156200014e57600080fd5b6200015b838284620001f3565b50505092915050565b6000602082840312156200017757600080fd5b81516001600160401b038111156200018e57600080fd5b6200019c8482850162000108565b949350505050565b6040518181016001600160401b0381118282101715620001c357600080fd5b604052919050565b60006001600160401b03821115620001e257600080fd5b506020601f91909101601f19160190565b60005b8381101562000210578181015183820152602001620001f6565b8381111562000220576000848401525b50505050565b610ae780620002366000396000f3fe608060405234801561001057600080fd5b50600436106100575760003560e01c80632ae868661461005c5780633dc735da1461007a578063aa15efc81461008f578063f61b2d49146100a5578063fd9527c8146100b8575b600080fd5b6100646100c0565b60405161007191906109b8565b60405180910390f35b61008d610088366004610825565b610111565b005b610097610276565b60405161007192919061095f565b6100646100b33660046107ff565b61053b565b610064610556565b600080805b6100cd610556565b81101561010a576100fe600382815481106100e457fe5b6000918252602090912001546001600160a01b031661053b565b909101906001016100c5565b5090505b90565b61011961055c565b5060408051808201909152828152602081018290526000805b600354811015610172576003818154811061014957fe5b6000918252602090912001546001600160a01b031633141561016a57600191505b600101610132565b50806101bb57600380546001810182556000919091527fc2575a0e9e593c00f959f8c92f12db2869c3395a3b0502d05e2516446f71f85b0180546001600160a01b031916331790555b3360009081526002602081815260408320805460018101808355828652948390208751805193969589959302909101926101f89284920190610576565b5060208281015180516102119260018501920190610576565b50503360009081526002602052604090208354610233935090915083906105f4565b507f4de756f107925b01c3b4d38b79fa03a10c7e7c055c17c0e55829125332dea70a84863360405161026793929190610984565b60405180910390a15050505050565b60608060006102836100c0565b905060008090506060826040519080825280602002602001820160405280156102c057816020015b60608152602001906001900390816102ab5790505b5090506060836040519080825280602002602001820160405280156102f957816020015b60608152602001906001900390816102e45790505b50905060005b610307610556565b81101561052f5760005b610321600383815481106100e457fe5b81101561052657600260006003848154811061033957fe5b60009182526020808320909101546001600160a01b03168352820192909252604001902080548290811061036957fe5b60009182526020918290206002918202018054604080516001831615610100026000190190921693909304601f8101859004850282018501909352828152929091908301828280156103fc5780601f106103d1576101008083540402835291602001916103fc565b820191906000526020600020905b8154815290600101906020018083116103df57829003601f168201915b505050505084868151811061040d57fe5b6020026020010181905250600260006003848154811061042957fe5b60009182526020808320909101546001600160a01b03168352820192909252604001902080548290811061045957fe5b90600052602060002090600202016001018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156104fe5780601f106104d3576101008083540402835291602001916104fe565b820191906000526020600020905b8154815290600101906020018083116104e157829003601f168201915b505050505083868151811061050f57fe5b602090810291909101015260019485019401610311565b506001016102ff565b50909450925050509091565b6001600160a01b031660009081526002602052604090205490565b60035490565b604051806040016040528060608152602001606081525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106105b757805160ff19168380011785556105e4565b828001600101855582156105e4579182015b828111156105e45782518255916020019190600101906105c9565b506105f0929150610693565b5090565b8280548282559060005260206000209060020281019282156106875760005260206000209160020282015b828111156106875782548390839061064d9082908490600260001961010060018416150201909116046106ad565b50600182018160010190805460018160011615610100020316600290046106759291906106ad565b5050509160020191906002019061061f565b506105f0929150610722565b61010e91905b808211156105f05760008155600101610699565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106106e657805485556105e4565b828001600101855582156105e457600052602060002091601f016020900482015b828111156105e4578254825591600101919060010190610707565b61010e91905b808211156105f057600061073c8282610753565b61074a600183016000610753565b50600201610728565b50805460018160011615610100020316600290046000825580601f106107795750610797565b601f0160209004906000526020600020908101906107979190610693565b50565b80356107a581610a90565b92915050565b600082601f8301126107bc57600080fd5b81356107cf6107ca826109ed565b6109c6565b915080825260208301602083018583830111156107eb57600080fd5b6107f6838284610a4a565b50505092915050565b60006020828403121561081157600080fd5b600061081d848461079a565b949350505050565b6000806040838503121561083857600080fd5b823567ffffffffffffffff81111561084f57600080fd5b61085b858286016107ab565b925050602083013567ffffffffffffffff81111561087857600080fd5b610884858286016107ab565b9150509250929050565b600061089a838361091e565b9392505050565b6108aa81610a39565b82525050565b60006108bb82610a1b565b6108c58185610a1f565b9350836020820285016108d785610a15565b8060005b8581101561091157848403895281516108f4858261088e565b94506108ff83610a15565b60209a909a01999250506001016108db565b5091979650505050505050565b600061092982610a1b565b6109338185610a1f565b9350610943818560208601610a56565b61094c81610a86565b9093019392505050565b6108aa8161010e565b6040808252810161097081856108b0565b9050818103602083015261081d81846108b0565b60608082528101610995818661091e565b905081810360208301526109a9818561091e565b905061081d60408301846108a1565b602081016107a58284610956565b60405181810167ffffffffffffffff811182821017156109e557600080fd5b604052919050565b600067ffffffffffffffff821115610a0457600080fd5b506020601f91909101601f19160190565b60200190565b5190565b90815260200190565b60006001600160a01b0382166107a5565b60006107a58260006107a582610a28565b82818337506000910152565b60005b83811015610a71578181015183820152602001610a59565b83811115610a80576000848401525b50505050565b601f01601f191690565b610a9981610a28565b811461079757600080fdfea365627a7a723158208028ab011388a2807c3639d68cb2ac9b4be44582e4bc0a99600120e55a1c4ddb6c6578706572696d656e74616cf564736f6c634300050c0040";

    public static final String FUNC_GETCOUNTADDR = "getCountAddr";

    public static final String FUNC_GETCOUNTSTRUCT = "getCountStruct";

    public static final String FUNC_GETHISTORY = "getHistory";

    public static final String FUNC_GETSIZE = "getsize";

    public static final String FUNC_MOVE = "move";

    public static final Event MOVED_EVENT = new Event("Moved", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}));
    ;

    @Deprecated
    protected Contract_sol_test(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Contract_sol_test(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Contract_sol_test(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Contract_sol_test(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
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
    public static Contract_sol_test load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Contract_sol_test(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Contract_sol_test load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Contract_sol_test(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Contract_sol_test load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Contract_sol_test(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Contract_sol_test load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Contract_sol_test(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Contract_sol_test> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String cName) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(cName)));
        return deployRemoteCall(Contract_sol_test.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Contract_sol_test> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String cName) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(cName)));
        return deployRemoteCall(Contract_sol_test.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Contract_sol_test> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String cName) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(cName)));
        return deployRemoteCall(Contract_sol_test.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Contract_sol_test> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String cName) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(cName)));
        return deployRemoteCall(Contract_sol_test.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class MovedEventResponse extends BaseEventResponse {
        public String dir;

        public String name;

        public String sender;
    }
}
