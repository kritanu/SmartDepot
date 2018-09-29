pragma solidity ^0.4.18;

contract Owned {
    address owner;
    
    constructor() Owned() public {     
        owner = msg.sender;
    }
    
    modifier onlyOwner{
        require(msg.sender == owner);
        _;
    }
}

contract Warehouse is Owned{
    
    struct Item{
        uint serial;
        bytes16 fName;
        bytes16 lName;
    }
    
    mapping (address => Item) items;
    address[] public ItemAddrs;
    
    event itemInfo(
        bytes16 fName,
        bytes16 lName,
        uint serial
        );
    
    function setItem(address _address,uint _serial, bytes16 _fName, bytes16 _lName) onlyOwner public{
        var item = items[_address];
        item.serial = _serial;
        item.fName = _fName;
        item.lName = _lName;
    
        ItemAddrs.push(_address) -1;
       emit itemInfo(_fName,_lName,_serial);
    } 
    
    function getItems() view public returns(address[]){
        return ItemAddrs;
    }
    
    function getItem(address _address) view public returns (uint,bytes16,bytes16){
        return (items[_address].serial,items[_address].fName,items[_address].lName);
    }
    
    function countItems() view public returns (uint){
        return ItemAddrs.length;
    }
    
    
}