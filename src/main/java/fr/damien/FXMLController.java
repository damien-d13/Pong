package fr.damien;
/*
Put header here


 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import fr.damien.utils.PongUtils;
import javafx.animation.AnimationTimer;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class FXMLController implements Initializable {
 
    @FXML
    private Pane board;

    @FXML
    private Rectangle player;

    @FXML
    private Rectangle computer;

    @FXML
    private Circle ball;

    @FXML
    private Button reset;

    @FXML
    private Label score;

    private double playerVelocY = 0;

    private AnimationTimer timer;

    /**  CONSTRUCTOR */
    public FXMLController() {
        timer = new AnimationTimer(){
            @Override
            public void handle(long l) {
                PongUtils.handlePlayer(player, playerVelocY);
                PongUtils.updateGame(player, computer, ball);
                checkEndGame();
            }
        };
    };

    @FXML
    void onResetAction(ActionEvent event) {
        timer.stop();

        ball.setCenterX(550);
        ball.setCenterY(375);
        player.setY(325);
        computer.setY(ball.getCenterY() - 50);
        PongUtils.resetGame();

    }

    @FXML
    void onRunAction(ActionEvent event) {
        board.requestFocus();

        timer.start();
        computer.setY(ball.getCenterY() - 50);

    }

    @FXML
    public void onKeyPressed(KeyEvent keyEvent) throws IOException{

        if (ball.getCenterX() > 15) {
            switch (keyEvent.getCode()) {
                case UP:
                // System.out.println("pressed up");
                    if (player.getY() >= 0)  {
                        playerVelocY = -5;
                        player.setY(player.getY() + playerVelocY);
                        
                    }
                    break;
                 case DOWN:
                    //  System.out.println("pressed down");
                    if (player.getY() <= 650)  {
                        playerVelocY = 5;
                        player.setY(player.getY() + playerVelocY);
                        
                    }
                    break;
            
                default:
                    break;
            }
            
        }

    }

    public void onKeyReleased() {
        playerVelocY = 0;
    }
    public void checkEndGame() {
        if (ball.getCenterX() < 15) {
            timer.stop();
        }

    };
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        score.textProperty().bind(Bindings.convert(PongUtils.scoreProperty()));
    }    
}
