package com.cryptotrial;

public class TransactionInput {
    public String transactionOutputId; //reference to TransactionOutputs -> transactionId
    public TransactionOutput UTXO; //contains the urgent transaction output

    public TransactionInput(String transactionOutputId){
        this.transactionOutputId = transactionOutputId;
    }

}
