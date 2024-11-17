package baseball.game.controller;

import baseball.baseNumber.BaseNumber;
import baseball.game.controller.constant.InputMessage;
import baseball.game.controller.constant.OutputMessage;
import baseball.game.controller.constant.Rules;
import baseball.guessNumber.GuessNumber;
import baseball.guessResult.GuessResult;
import baseball.view.Inputview;
import baseball.view.OutputView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameController {

    private final OutputView outputView;
    private final Inputview inputview;

    public GameController(OutputView outputView, Inputview inputview) {
        this.outputView = outputView;
        this.inputview = inputview;
    }

    public void play () {
        do {
            playSingle();
        } while(UserWantsToPlayMore());
    }

    private void playSingle() {
        outputView.print(OutputMessage.GAME_START);
        BaseNumber number = BaseNumber.create();
        GuessUntilFullStrike(number);
        outputView.print(OutputMessage.GAME_END);
    }

    private void GuessUntilFullStrike(BaseNumber number) {
        GuessResult guessResult;
        do {
            String input = inputview.promptUserInput(InputMessage.GUESS_BASE_NUMBER);
            List<Integer> inputNumbers = validateGuessNumber(input);
            GuessNumber guessNumber = GuessNumber.from(inputNumbers);
            guessResult = number.match(guessNumber);
            outputView.print(guessResult);
        } while(!guessResult.fullStrike());
    }

    private List<Integer> validateGuessNumber(String input) {
        List<Integer> numbers = new ArrayList<>();
        validateLength(input);
        addNumbers(numbers, input);
        validateDistinct(numbers);
        return numbers;
    }

    private void validateDistinct(List<Integer> numbers) {
        Set<Integer> distictNumber = new HashSet<>(numbers);
        if (numbers.size() != distictNumber.size()) {
            throw new IllegalArgumentException();
        }
    }

    private void addNumbers(List<Integer> numbers, String input) {
        for (int i = 0; i < Rules.BASE_NUMBER_COUNT; ++i) {
            try {
                int number = Integer.parseInt(input.substring(i, i+1));
                numbers.add(number);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException();
            }
        }
    }

    private void validateLength(String input) {
        if (input.length() != 3) {
            throw new IllegalArgumentException();
        }
    }

    private boolean UserWantsToPlayMore() {
        String input = inputview.promptUserInput(InputMessage.GAME_CONTINUE);
        boolean gameContinue = validateGameContinue(input);
        return gameContinue;
    }

    private boolean validateGameContinue(String input) {
        return input.equals("1");
    }

}
