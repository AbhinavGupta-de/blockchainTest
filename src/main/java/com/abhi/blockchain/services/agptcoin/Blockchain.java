package com.abhi.blockchain.services.agptcoin;

import java.util.LinkedList;

public interface Blockchain<T extends Block>{
    Block lastBlock();
    int proofOfWork(int previousProof);
    String hash(Block block);
    void newBlock(int proof, String previousHash);
    boolean isChainValid(LinkedList<Block> chain);
    LinkedList<T> getChain();
}
