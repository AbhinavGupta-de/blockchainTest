package com.abhi.blockchain.services.agptcoin;

public interface Block {
    int getIndex();
    String getTimestamp();
    int getProof();
    String getPreviousHash();
    String toString();

    int addTransactions(String sender, String receiver, double amount);

}
