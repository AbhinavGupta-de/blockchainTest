package com.abhi.blockchain.services;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Getter
@Service
public class ChainService {

    private Blockchain blockchain;

    ChainService() {
        blockchain = new Blockchain();
    }

    public boolean addBlock() {
        int previousProof = blockchain.lastBlock().proof;
        int proof = blockchain.proofOfWork(previousProof);
        String previousHash = blockchain.hash(blockchain.lastBlock());
        blockchain.newBlock(proof, previousHash);
        return true;
    }


    public boolean isChainValid() {
        return blockchain.isChainValid(blockchain.chain);
    }

    public int getProofOfWork(int previousProof) {
        return blockchain.proofOfWork(previousProof);
    }

    public String getHash(Block block) {
        return blockchain.hash(block);
    }

    public Block getLastBlock() {
        return blockchain.lastBlock();
    }

    public LinkedList<Block> getChain() {
        return blockchain.chain;
    }
}
