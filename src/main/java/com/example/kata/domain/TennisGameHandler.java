package com.example.kata.domain;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.kata.consts.GameConsts;
import com.example.kata.utils.DisplayUtility;

/*
 * Class manage tennis game calculating score
 */
@Component
public class TennisGameHandler {

    private boolean isEndGame = false;

    @Autowired
    DisplayUtility displayUtility;

    public boolean getIsEndGame() {
        return this.isEndGame;
    }

    /*
     * Method that returns the player name who loses the point
     * 
     * @param scorerName: the player who scored the goal
     * 
     * @return the name of the second player
     */
    public char getSecondPlayerName(char scorerName) {
        if (GameConsts.PLAYER1_NAME == scorerName) {
            return GameConsts.PLAYER2_NAME;
        } else {
            return GameConsts.PLAYER1_NAME;
        }
    }

    /*
     * Method used to update tennis game score based on the privious score of the
     * player
     * 
     * @param scorerName: the name of the player who scored the goal
     * 
     * @param gameContext: a hashMap stores the context of the game, players and
     * their scores
     * 
     * @return void
     */
    public void updateScore(char scorerName, Map<Character, Integer> gameContext) {

        // Name of the player who loses the point
        char SecondPlayerName = getSecondPlayerName(scorerName);

        // Previous score of the scorer
        Integer previousScore = gameContext.get(scorerName);

        if ((previousScore == GameConsts.ZERO_SCORE) || (previousScore == GameConsts.FIFTEEN_SCORE)) {
            // Increment score with 15 points
            gameContext.put(scorerName, previousScore + GameConsts.FIFTEEN_POINT);

        } else if (previousScore == GameConsts.THIRTY_SCORE) {
            // Increment score with 10 points
            gameContext.put(scorerName, previousScore + GameConsts.TEN_POINT);

        } else {
            // Check the case of ending game before deuce
            if ((previousScore == GameConsts.FORTY_SCORE) && (gameContext.get(SecondPlayerName) < GameConsts.FORTY_SCORE)) {
                this.isEndGame = true;

            } else {
                // deuce case
                if (previousScore == gameContext.get(SecondPlayerName)) {
                    gameContext.put(scorerName, GameConsts.ADVANTAGE_SCORE);
                }

                // Check the case of ending game after deuce
                if ((previousScore == GameConsts.ADVANTAGE_SCORE)
                        && (gameContext.get(SecondPlayerName)) == GameConsts.FORTY_SCORE) {
                    this.isEndGame = true;
                }

                // Check the case of returning score to deuce case
                if ((previousScore == GameConsts.FORTY_SCORE) && (gameContext.get(SecondPlayerName)) > GameConsts.FORTY_SCORE) {
                    gameContext.put(scorerName, GameConsts.FORTY_SCORE);
                    gameContext.put(SecondPlayerName, GameConsts.FORTY_SCORE);
                }
            }
        }
    }

    /*
     * Method used to simulate a tennis game
     * 
     * @param playingSequence: the sequence of goals in a round
     * 
     * @param gameContext: a hashMap stores the context of the game, players and
     * theirs score
     * 
     * @return void
     */
    public void play(String playingSequence, Map<Character, Integer> gameContext) {

        for (int index = 0; index < playingSequence.length(); index++) {

            // update score
            updateScore(playingSequence.charAt(index), gameContext);

            // display score or terminate game
            if (!this.isEndGame) {
                displayUtility.displayScore(gameContext);
            } else {
                displayUtility.TerminateGame(playingSequence.charAt(index));
            }
        }
    }

}
