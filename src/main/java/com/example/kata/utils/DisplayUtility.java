package com.example.kata.utils;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.kata.consts.GameConsts;

/*
 * Class which provides methods to manage display messages on the console
 */
@Component
public class DisplayUtility {

    public void TerminateGame(char winner) {
        System.out.println("player " + winner + " wins the game");
    }

    public void displayScore(Map<Character, Integer> gameContext) {

        String message = "";
        String scoreValue = "";

        for (Character playerName : gameContext.keySet()) {
            if (gameContext.get(playerName) == GameConsts.ADVANTAGE_SCORE) {
                scoreValue = GameConsts.ADVANTAGE_MESSAGE;
            } else {
                scoreValue = String.valueOf(gameContext.get(playerName));
            }

            message += " | player " + playerName + ":" + scoreValue;
        }

        System.out.println(message);
    }
}
