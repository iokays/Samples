package com.iokays.onjava.blockchain;


import org.apache.commons.codec.digest.DigestUtils;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class Block implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 前一个区块的hash
     */
    private final String previousHash;
    /**
     * 区块的数据
     */
    private final String data;
    /**
     * 时间戳
     */
    private final LocalDateTime timeStamp;
    /**
     * 区块的hash
     */
    private String hash;

    /**
     * 随机数
     */
    private int nonce;

    public Block(String previousHash, String data) {
        this.previousHash = previousHash;
        this.data = data;
        this.timeStamp = LocalDateTime.now();
        this.hash = this.calculateHash();
    }

    private String calculateHash() {
        return DigestUtils.sha256Hex(previousHash + Timestamp.valueOf(timeStamp).getTime() + String.valueOf(nonce) + data);
    }

    //挖矿
    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)) {//找到符合难度的hash
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!!" + hash);
    }

    public static void main(String[] args) {
        String target = new String(new char[5]).replace('\0', '0');
        System.out.println(target);
    }


    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }


    public static boolean isChainValid(final List<Block> blocks) {
        for (int i = 1; i < blocks.size(); i++) {
            final Block currentBlock = blocks.get(i);
            final Block previousBlock = blocks.get(i - 1);
            if (!isBlockValid(previousBlock, currentBlock)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isBlockValid(Block previousBlock, Block currentBlock) {
        if (!previousBlock.getHash().equals(currentBlock.getPreviousHash())) {
            return false;
        }
        return currentBlock.getHash().equals(currentBlock.calculateHash());
    }


    @Override
    public String toString() {
        return "Block{" +
                "hash='" + hash + '\'' +
                ", previousHash='" + previousHash + '\'' +
                ", data='" + data + '\'' +
                ", timeStamp=" + timeStamp +
                ", nonce=" + nonce +
                '}';
    }

}
