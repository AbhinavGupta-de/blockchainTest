package com.abhi.blockchain.services.agptcoin;

import java.util.LinkedList;

record Transaction(String sender, String receiver, double amount) {}

public class AGPTBlock implements  Block{

    private int index;
    private String timestamp;
    private int proof;
    private String previousHash;

    private LinkedList<Transaction> currentTransactions;


    public AGPTBlock(int index, String timestamp, int proof, String previousHash) {
        this.index = index;
        this.timestamp = timestamp;
        this.proof = proof;
        this.previousHash = previousHash;
    }


    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public int getProof() {
        return proof;
    }

    @Override
    public String getPreviousHash() {
        return previousHash;
    }

    private void newTransaction(String sender, String receiver, double amount) {
        currentTransactions.add(new Transaction(sender, receiver, amount));
    }

    @Override
    public int addTransactions(String sender, String receiver, double amount) {
        newTransaction(sender, receiver, amount);
        return getIndex() + 1;
    }
}
