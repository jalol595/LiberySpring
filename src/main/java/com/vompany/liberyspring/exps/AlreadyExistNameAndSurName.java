package com.vompany.liberyspring.exps;

public class AlreadyExistNameAndSurName extends RuntimeException {
    public AlreadyExistNameAndSurName(String massage) {
        super(massage);
    }
}
