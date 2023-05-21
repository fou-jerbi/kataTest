package com.example.kata.validator;

import com.example.kata.consts.GameConsts;

public class InputValidator {

    public static boolean isInputValid(String input) {

        // check the size of the input
        if (input.length() < GameConsts.MIN_INPUT_SIZE) {
            return false;
        }

        // check the elements of the input
        for (int index = 0; index < input.length(); index++) {

            char element = input.charAt(index);

            if ((element != GameConsts.PLAYER1_NAME) && (element != GameConsts.PLAYER2_NAME)) {
                return false;
            }
        }

        return true;
    }

}
