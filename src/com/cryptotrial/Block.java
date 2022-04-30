package com.cryptotrial;

import java.util.ArrayList;
import java.util.Date;

public class Block {

    public String hash;
    private String previousHash;
    public String merkleRoot;
    public ArrayList<Transaction> transactions = new ArrayList<Transaction>(); //our data will be a simple message
    private long timeStamp; //as a number of milliseconds since 1/1/1970
    private int nonce;

    //block constructor
    public Block(String previousHash){

        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash(); //must come after previous values are set
    }

    //returns PreviousHash
    public String getPreviousHash(){
        return previousHash;
    }

    //calculates hash for block
    public String calculateHash(){
        String calculatedHash = StringUtil.applySha256(previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + merkleRoot); //sends important data that can't be tampered with to hash maker
        return calculatedHash;
    }

    //mine a hash
    public void mineBlock(int difficulty){
        merkleRoot = StringUtil.getMerkleRoot(transactions);
        String target = StringUtil.getDifficultyString(difficulty); //create a string with the difficulty integer

        while(!hash.substring(0, difficulty).equals(target)){
            nonce++;
            hash =  calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }

    //add a transaction to this block
    public boolean addTransaction(Transaction transaction){
        //process transaction and check if valid, unless block is genesis block then ignore
        if(transaction == null ) return false;
        if((previousHash != "0")){
            if(!transaction.processTransaction()){
                System.out.print("Transaction failed to process. Discarded. ");
                return false;
            }
        }
        transactions.add(transaction);
        System.out.println("Transaction Successfully added to Block");
        return true;
    }

}
