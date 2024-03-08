package com.abhi.blockchain.services.agptcoin;

import java.util.LinkedList;

// Transactions Record

public class AGPTBlockchainImpl implements GPTBlockchain<AGPTBlock>{

    private LinkedList<AGPTBlock> chain;

    public AGPTBlockchainImpl() {
        chain = new LinkedList<>();
        newBlock(100, "1");
    }

    @Override
    public AGPTBlock lastBlock() {
        return chain.getLast();
    }
    @Override
    public int proofOfWork(int previousProof) {
        int newProof = 1;
        boolean checkProof = false;
        while (!checkProof) {
            String hashOperation = hashOperation(previousProof, newProof);
            if (hashOperation.substring(0, 4).equals("0000")) {
                checkProof = true;
            } else {
                newProof++;
            }
        }
        return newProof;
    }

    @Override
    public String hash(Block block) {
        return hashOperation(block.getProof(), Integer.parseInt(block.getPreviousHash()));
    }

    private String hashOperation(int previousProof, int newProof) {
        return sha256(String.valueOf(newProof * newProof - previousProof * previousProof));
    }

    private String sha256(String input) {
        try {
            java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                hexString.append(String.format("%02x", b & 0xff));
            }
            return hexString.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void newBlock(int proof, String previousHash) {
        AGPTBlock block = new AGPTBlock(chain.size() + 1, String.valueOf(System.currentTimeMillis()), proof, previousHash);
        chain.add(block);
    }
    

    
    @Override
    public boolean isChainValid(LinkedList<Block> chain) {
        AGPTBlock previousBlock = (AGPTBlock) chain.get(0);
        int currentIndex = 1;
        while (currentIndex < chain.size()) {
            AGPTBlock block = (AGPTBlock) chain.get(currentIndex);
            if (!block.getPreviousHash().equals(hash(previousBlock))) {
                return false;
            }
            int previousProof = previousBlock.getProof();
            int proof = block.getProof();
            String hashOperation = hashOperation(previousProof, proof);
            if (!hashOperation.substring(0, 4).equals("0000")) {
                return false;
            }
            previousBlock = block;
            currentIndex++;
        }
        return true;
    }

    @Override
    public LinkedList<AGPTBlock> getChain() {
        return chain;
    }

}
