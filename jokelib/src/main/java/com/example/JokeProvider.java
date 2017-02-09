package com.example;

import java.util.Random;

public class JokeProvider {

    private static String[] jokes = {"I have no friends. Even the toilet cover attacks me from the back.",
            "Sometimes I drink water - just to surprise my liver",
    "Hearing voices in your head is normal. Listening to them is quite common. Arguing with them – acceptable. It is only when you lose that argument that you get in real trouble",
    "I was on the subway the other day, and the guy next to me was crying over a book. He was actually crying. So, I leaned over -- I go, 'You don't know how to read, either?'",
    "Lauren: Dad, do you know what the most commonly used letter in a girl’s name is?\n" +
            "\n" +
            "Me: Hmm, is it a consonant or a vowel? (Silence.) Please tell me you know what consonants and vowels are.\n" +
            "\n" +
            "Lauren: You’re no fun, Dad. Forget it.\n" +
            "\n" +
            "Me: What is a vowel?\n" +
            "\n" +
            "Lauren: OK, OK. A vowel is … ahh … eh … well, oh … uh …\n" +
            "\n" +
            "Me: Close enough."};

    public static String getRandomJoke(){

        Random random = new Random();

//        try {
//            Thread.sleep(5000); //Forcing some delay
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        return jokes[random.nextInt(jokes.length)];
    }

}
