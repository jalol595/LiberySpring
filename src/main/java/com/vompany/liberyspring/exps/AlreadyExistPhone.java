package com.vompany.liberyspring.exps;

public class AlreadyExistPhone extends RuntimeException {
    public AlreadyExistPhone(String massage) {
        super(massage);
    }
}
