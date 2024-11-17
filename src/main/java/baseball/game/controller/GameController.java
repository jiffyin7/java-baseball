package baseball.game.controller;

import baseball.game.controller.constant.OutputMessage;
import baseball.view.Inputview;
import baseball.view.OutputView;

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
    }

    private boolean UserWantsToPlayMore() {
        return false;
    }

}