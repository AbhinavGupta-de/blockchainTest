package com.abhi.blockchain.controllers;

import com.abhi.blockchain.services.blockchain.Block;
import com.abhi.blockchain.services.ChainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;

@RestController
@RequestMapping("/api/chain")
public class ChainController {

    @Autowired
    private ChainService chainService;

    @PostMapping("/addBlock")
    public String addBlock() {
        if (chainService.addBlock()) {
            return "Block added successfully";
        } else {
            return "Block not added";
        }
    }

    @GetMapping("/isChainValid")
    public String isChainValid() {
        if (chainService.isChainValid()) {
            return "Chain is valid";
        } else {
            return "Chain is not valid";
        }
    }

    @GetMapping("/getProofOfWork")
    public int getProofOfWork(int previousProof) {
        return chainService.getProofOfWork(previousProof);
    }

    @GetMapping("/getHash")
    public String getHash(Block block) {
        return chainService.getHash(block);
    }

    @GetMapping("/getLastBlock")
    public Block getLastBlock() {
        return chainService.getLastBlock();
    }

    @GetMapping("/getChain")
    public String getChain() {
        LinkedList<Block> list = chainService.getChain();
        for (Block block : list) {
            System.out.println(block.toString());
        }
        return chainService.getChain().toString();
    }


}
