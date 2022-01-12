package com.example.domain.common;

import java.util.UUID;

public class IdStringGenerator {
    private static NextStringGenerator nextStringGenerator = new NextStringGeneratorRandomUUID();

    public static Id<String> nextIdString() {
        return new Id<>(nextStringGenerator.nextString());
    }

    public static String nextString(){
        return nextStringGenerator.nextString();
    }

    public static void setNextStringGenerator(NextStringGenerator nextStringGenerator) {
        IdStringGenerator.nextStringGenerator = nextStringGenerator;
    }

    @FunctionalInterface
    public interface NextStringGenerator {
        String nextString();
    }

    public static class NextStringGeneratorRandomUUID implements NextStringGenerator {
        @Override
        public String nextString() {
            return UUID.randomUUID().toString();
        }
    }
}
