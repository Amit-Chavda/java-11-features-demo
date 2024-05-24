package com.java_11_features.dto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Cycle {

    private Cycle() {
        log.info("Cycle class private constructor");
    }

    private static void privateMethod() {
        log.info("Cycle class private method");
    }

    public static class NanoCycle {
        public void testMethod() {
            privateMethod();
            log.info("NanoCycle class method ");
        }
    }
}

