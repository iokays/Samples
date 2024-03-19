package com.iokays.extend.exception;

import io.vavr.control.Try;

public class ExceptionSample {

    public static void main(String[] args) {
        final var value = Try.of(() -> Integer.valueOf("1.1")).getOrElse(0);
        System.out.println(value);
    }

}
