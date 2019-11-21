pragma solidity >=0.4.22 <0.6.0;
pragma experimental ABIEncoderV2;

contract Contract {
    /* Define variable owner of the type address*/
    address owner;

    /* this function is executed at initialization and sets the owner of the contract */
    constructor () public { owner = msg.sender; }

    /* Function to recover the funds on the contract */
    //function kill() public { if (msg.sender == owner) selfdestruct(owner); }
}

contract test is Contract {
    /* define variable greeting of the type string */
    string contractName;
    struct Moves{
        string direction;
        string username;
    }
    mapping(address => Moves[])map;
    address[] addresses;
    uint isMoving=0;
    mapping(address => uint) vidas;
    /* this runs when the contract is executed */

    constructor (string memory cName) public {
        contractName = cName;
    }
    event Moved(
        string dir,
        string name,
        address sender
    );

    function getOwner() public returns(address){
        return this.owner;
    }

    function move(string memory direction, string memory name) public {
       Moves memory m = Moves(direction,name);
       require(isMoving==0,"Último movimento ainda a ser processado pela Blockchain, aguarde...");
       require(vidas[msg.sender]>0,"Sem vidas...");
       isMoving=1;
       uint existe=0;
       for(uint i=0;i<addresses.length;i++){
           if(msg.sender == addresses[i]){
               existe = 1;
           }
       }
       if(existe==0){
           addresses.push(msg.sender);
           vidas[msg.sender] = 3;
       }
       //vidas[msg.sender]--;
       Moves[] storage ms = map[msg.sender];
       ms.push(m);
       map[msg.sender] = ms;
       
       emit Moved(direction,name,msg.sender);
    }
    
    function finish() public{
        require(isMoving==1,"Robot not moving");
        isMoving=0;
    }

    function requestLive(address who) public{
        require(checkAddress(who)==1,"Not possible to request lives to inexistent address");
        vidas[who]=3;
    }

    function checkAddress(address addr) public returns(uint){
        for(uint=0;i<addresses.length;i++){
            if(msg.sender== addresses[i]){
                return 1;
            }
        }
        return 0;
    }
    
    
    function getHistory()public view returns(string[] memory,string[] memory){
        uint size = getsize();
        uint index =0;
        string[] memory d = new string[](size);
        string[] memory n = new string[](size);
        for(uint i=0;i<getCountAddr();i++){
            for(uint j=0;j<getCountStruct(addresses[i]);j++){
                d[index] = map[addresses[i]][j].direction;
                n[index] = map[addresses[i]][j].username;
                index++;
            }
        }
        return(d,n);
    }

    function getsize()public view returns(uint){
        uint size = 0;
        for(uint i = 0;i<getCountAddr();i++){
            size+=getCountStruct(addresses[i]);
        }
        return size;
    }
    
    function getCountAddr() public view returns(uint count) {
        return addresses.length;
    }
    function getCountStruct(address addr) public view returns(uint count) {
        return map[addr].length;
    }
}

