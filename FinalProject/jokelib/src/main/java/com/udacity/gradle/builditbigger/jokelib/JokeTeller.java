package com.udacity.gradle.builditbigger.jokelib;

import java.util.Random;

public class JokeTeller {

    String[] jokes = new String[] {
        "What is the biggest lie in the entire universe? I have read and agree to the Terms & Conditions.",
        "What should you do after your Nintendo game ends in a tie? Ask for a Wii-match!",
        "Patient: Doctor, I need your help. I’m addicted to checking my Twitter! Doctor: I’m so sorry, I don’t follow."
    };
    private final Random mRand;

    public JokeTeller() {
        mRand = new Random();
    }

    public String tellMeAJoke() {
        int jokePosition = mRand.nextInt(jokes.length);
        return jokes[jokePosition];
    }

}
