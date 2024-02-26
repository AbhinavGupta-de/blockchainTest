package com.abhi.blockchain.services;

public class Block{

    int index;
    // Timestamp
    String timestamp;
    int proof;
    String previousHash;

    public Block(int index, String timestamp, int proof, String previousHash) {
        this.index = index;
        this.timestamp = timestamp;
        this.proof = proof;
        this.previousHash = previousHash;
    }

    public String toString() {
        return "Block{" +
                "index=" + index +
                ", timestamp='" + timestamp + '\'' +
                ", proof=" + proof +
                ", previousHash='" + previousHash + '\'' +
                '}';
    }

}
