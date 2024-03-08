package com.abhi.blockchain.services.agptcoin;

import java.util.LinkedList;

public class AGPTCoinFactory {

    public static GPTBlockchain<AGPTBlock> createBlockchain() {
        return new AGPTBlockchainImpl();
    }

    public static AGPTBlock createBlock(int index, String timestamp, int proof, String previousHash) {
        return new AGPTBlock(index, timestamp, proof, previousHash);
    }

    public static AGPTBlock createBlock() {
        return new AGPTBlock();
    }

    public static AGPTBlock createBlock(int index, String timestamp, int proof, String previousHash, LinkedList<Transaction> currentTransactions) {
        return new AGPTBlock(index, timestamp, proof, previousHash, currentTransactions);
    }

    public static Transaction createTransaction(String sender, String receiver, double amount) {
        return new Transaction(sender, receiver, amount);
    }

    public static LinkedList<Transaction> createTransactionList() {
        return new LinkedList<Transaction>();
    }

    public static LinkedList<AGPTBlock> createBlockList() {
        return new LinkedList<AGPTBlock>();
    }
}
