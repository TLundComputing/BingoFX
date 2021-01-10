package org.tjl.bingo;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BingoGame {

    private ArrayList<Integer> numbersToCall = new ArrayList<>();
    private ArrayList<Integer> calledNums = new ArrayList<>();
    private final String[] games = {"One Line", "Two Lines", "Full House"};
    private float[] prizes = new float[3];
    private float kitty;
    private int pointer;
    private int gamePointer;
    private int prizePointer;
    private DecimalFormat df = new DecimalFormat("0.00");

    public BingoGame(){
        for(int i = 0; i < 90; i++){
            numbersToCall.add(i+1);
        }
        Collections.shuffle(numbersToCall);
        pointer = 0;
        gamePointer = 0;
        prizePointer = 0;
    }

    public BingoGame(float theKitty){
        for(int i = 0; i < 90; i++){
            numbersToCall.add(i+1);
        }
        Collections.shuffle(numbersToCall);
        pointer = 0;
        kitty = theKitty;
        gamePointer = 0;
        prizePointer = 0;
        generatePrizes();
    }

    public void generatePrizes(){
        prizes[0] = kitty * 0.2f;
        prizes[1] = kitty * 0.3f;
        prizes[2] = kitty * 0.5f;
    }

    public String getGame(){
        if(gamePointer >= 3){
            return "Game Has Ended";
        }
        return games[gamePointer];
    }

    public String bingoWinner(){
        if(gamePointer >= 3){
            return "Game Has Already Ended";
        }
        String temp = df.format(prizes[prizePointer]);
        prizePointer += 1;
        gamePointer += 1;
        return temp;
    }

    public int[] getCalledNums(){
        Collections.sort(calledNums);
        int[] called = new int[calledNums.size()];
        for(int i = 0; i < called.length; i++){
            called[i] = calledNums.get(i);
        }
        return called;
    }

    public int nextNumber(){
        if(pointer < 90){
            int temp = numbersToCall.get(pointer);
            calledNums.add(temp);
            pointer += 1;
            return temp;
        }
        return -1;
    }

}
