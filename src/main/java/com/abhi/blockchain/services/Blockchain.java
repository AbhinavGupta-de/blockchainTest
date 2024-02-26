package com.abhi.blockchain.services;

import org.springframework.context.annotation.Bean;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.LinkedList;

/**
 * Blockchain
 */
public class Blockchain {


        LinkedList<Block> chain;

        public Blockchain() {
            chain = new LinkedList<Block>();
            chain.add(new Block(1, "01/01/2018", 100, "0"));
        }

        public Block lastBlock() {
            return chain.getLast();
        }

        public Block newBlock(int proof, String previousHash) {
            int index = chain.size() + 1;
            String timestamp = String.valueOf(new Date());
            Block block = new Block(index, timestamp, proof, previousHash);
            chain.add(block);
            return block;
        }

        public int proofOfWork(int previousProof) {
            int newProof = 1;
            boolean checkProof = false;
            while (!checkProof) {
                String hashOperation = String.valueOf(newProof * newProof - previousProof * previousProof);
                if (hashOperation.length() >= 4 && hashOperation.endsWith("0000")) {
                    checkProof = true;
                } else {
                    newProof += 1;
                }
            }
            return newProof;
        }

        public String hash(Block block) {
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] encodedBlock = digest.digest((block.index + block.timestamp +
                        block.proof + block.previousHash).getBytes());
                StringBuilder hexString = new StringBuilder(2 * encodedBlock.length);
                for (byte b : encodedBlock) {
                    hexString.append(String.format("%02x", b & 0xff));
                }
                return hexString.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return null;
        }

        public boolean isChainValid(LinkedList<Block> chain) {
            Block previousBlock = chain.get(0);
            int blockIndex = 1;
            while (blockIndex < chain.size()) {
                Block block = chain.get(blockIndex);
                if (!block.previousHash.equals(hash(previousBlock))) {
                    return false;
                }
                int previousProof = previousBlock.proof;
                int proof = block.proof;
                String hashOperation = String.valueOf(proof * proof - previousProof * previousProof);
                if (hashOperation.length() >= 4 && !hashOperation.endsWith("0000")) {
                    return false;
                }
                previousBlock = block;
                blockIndex += 1;
            }
            return true;
        }
}