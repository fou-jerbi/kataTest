package com.example.kata;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.kata.consts.GameConsts;
import com.example.kata.domain.TennisGameHandler;
import com.example.kata.validator.InputValidator;

@SpringBootApplication
public class KataApplication implements CommandLineRunner {

	// Change this variable for new score sequence
	private static final String SCORE_SEQUENCE = "AAABBBABAA";

	@Autowired
	private ApplicationContext context;

	public static void main(String[] args) {

		SpringApplication.run(KataApplication.class, args);
	}

	@Override
	public void run(String... args) {

		TennisGameHandler tennisGame = context.getBean(TennisGameHandler.class);

		Map<Character, Integer> gameContext = new HashMap<Character, Integer>();
		gameContext.put(GameConsts.PLAYER1_NAME, 0);
		gameContext.put(GameConsts.PLAYER2_NAME, 0);

		// check the input validity
		if (InputValidator.isInputValid(SCORE_SEQUENCE)) {
			tennisGame.play(SCORE_SEQUENCE, gameContext);
		} else {
			System.out.println("Input sequence is not valid");
		}
	}
}
