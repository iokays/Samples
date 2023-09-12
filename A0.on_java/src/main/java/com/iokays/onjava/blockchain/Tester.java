package com.iokays.onjava.blockchain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Stream;

public class Tester implements Serializable {

    @Test
    public void testBlock() {

        final Block firstBlock = new Block("0", "first block");
        final var difficulty = 5;

        firstBlock.mineBlock(difficulty);
        System.out.println("first block hash : " + firstBlock.getHash());

        final Block secondBlock = new Block(firstBlock.getHash(), "second block");
        secondBlock.mineBlock(difficulty);
        System.out.println("second block hash : " + secondBlock.getHash());

        final Block thirdBlock = new Block(secondBlock.getHash(), "third block");
        thirdBlock.mineBlock(difficulty);
        System.out.println("third block hash : " + thirdBlock.getHash());

        final var list = List.of(firstBlock, secondBlock, thirdBlock);

        Assertions.assertTrue(Block.isChainValid(list));

        list.forEach(System.out::println);
    }

}
