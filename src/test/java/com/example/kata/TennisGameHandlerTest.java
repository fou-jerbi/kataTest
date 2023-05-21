package com.example.kata;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.kata.consts.GameConsts;
import com.example.kata.domain.TennisGameHandler;

@SpringBootTest
public class TennisGameHandlerTest {

    @Autowired
    TennisGameHandler tennisGame;

    @Test
    void getPlayerTwoShouldGetPlayerTwo(){
        assertTrue(tennisGame.getSecondPlayerName(GameConsts.PLAYER1_NAME) == GameConsts.PLAYER2_NAME);
        assertTrue(tennisGame.getSecondPlayerName(GameConsts.PLAYER2_NAME) == GameConsts.PLAYER1_NAME);

    }
    
    @Test
    void updateScoreShouldUpdateScore(){
        Map<Character, Integer> gameContext = new HashMap<Character, Integer>();
		gameContext.put(GameConsts.PLAYER1_NAME, GameConsts.ZERO_SCORE);
		gameContext.put(GameConsts.PLAYER2_NAME, GameConsts.ZERO_SCORE);

        tennisGame.updateScore(GameConsts.PLAYER1_NAME, gameContext);
        assertTrue(gameContext.get(GameConsts.PLAYER1_NAME) == GameConsts.FIFTEEN_SCORE);
        assertTrue(gameContext.get(GameConsts.PLAYER2_NAME) == GameConsts.ZERO_SCORE);

        tennisGame.updateScore(GameConsts.PLAYER2_NAME, gameContext);
        assertTrue(gameContext.get(GameConsts.PLAYER1_NAME) == GameConsts.FIFTEEN_SCORE);
        assertTrue(gameContext.get(GameConsts.PLAYER2_NAME) == GameConsts.FIFTEEN_SCORE);

        tennisGame.updateScore(GameConsts.PLAYER1_NAME, gameContext);
        tennisGame.updateScore(GameConsts.PLAYER1_NAME, gameContext);
        assertTrue(gameContext.get(GameConsts.PLAYER1_NAME) == GameConsts.FORTY_SCORE);
        assertTrue(gameContext.get(GameConsts.PLAYER2_NAME) == GameConsts.FIFTEEN_SCORE);

        tennisGame.updateScore(GameConsts.PLAYER1_NAME, gameContext);
        assertTrue(tennisGame.getIsEndGame() == true);
    }

    @Test
    void updateScoreShouldReturnScoreToDeuce(){
        Map<Character, Integer> gameContext = new HashMap<Character, Integer>();
		gameContext.put(GameConsts.PLAYER1_NAME, GameConsts.ADVANTAGE_SCORE);
		gameContext.put(GameConsts.PLAYER2_NAME, GameConsts.FORTY_SCORE);

        tennisGame.updateScore(GameConsts.PLAYER2_NAME, gameContext);
        assertTrue(gameContext.get(GameConsts.PLAYER1_NAME) == GameConsts.FORTY_SCORE);
        assertTrue(gameContext.get(GameConsts.PLAYER2_NAME) == GameConsts.FORTY_SCORE);
    }

    @Test
    void updateScoreShouldEndGameAfterDeuce(){
        Map<Character, Integer> gameContext = new HashMap<Character, Integer>();
		gameContext.put(GameConsts.PLAYER1_NAME, GameConsts.ADVANTAGE_SCORE);
		gameContext.put(GameConsts.PLAYER2_NAME, GameConsts.FORTY_SCORE);

        tennisGame.updateScore(GameConsts.PLAYER1_NAME, gameContext);
        assertTrue(tennisGame.getIsEndGame() == true);
        assertTrue(gameContext.get(GameConsts.PLAYER2_NAME) == GameConsts.FORTY_SCORE);
    }

}
