package fr.damien.utils;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import static java.lang.Math.*;

public class PongUtils {


    private static final double UP_LIMIT = 15;
    private static final double BOTTOM_LIMIT = 735;
    private static final double INITIAL_VX = 5.0;
    private static final double INITIAL_VY = 1.0;
    private static final double SPEED_LIMIT = 30;
    private static final double ACCELERATION = 1.1;

    private static double angle = atan2(INITIAL_VY, INITIAL_VX);
    private static double magnitude = sqrt(5);

    private static double deltaX = magnitude * cos(angle);
    private static double deltaY = magnitude * sin(angle);

    private static SimpleIntegerProperty score = new SimpleIntegerProperty(0);


    public static void handlePlayer(Rectangle player, double playerVelocY ) {
        player.setY(player.getY() >= 0 && player.getY() <= 650 ? player.getY()  + playerVelocY : player.getY() );
    }
    public static void updateGame(Rectangle player, Rectangle computer, Circle ball) {
        ball.setCenterX(ball.getCenterX() + deltaX);
        ball.setCenterY(ball.getCenterY() + deltaY);
        computer.setY(ball.getCenterY() - 50);

        if (player.getBoundsInParent().intersects(ball.getBoundsInParent())) {

            magnitude *= (magnitude < SPEED_LIMIT) ? ACCELERATION : 1;

            angle = (PI / 4 ) * abs( (player.getY( ) + 50 + ball.getCenterY() - 15) / 50);
            deltaX = abs(magnitude * cos(angle));

            deltaY = (ball.getCenterY() <= player.getY() + 75 ? -abs(magnitude * sin(angle)) : abs(magnitude * sin(angle)) );

            score.set(score.get() + 1);

        } else if (computer.getBoundsInParent().intersects(ball.getBoundsInParent())) {
            magnitude *= (magnitude < SPEED_LIMIT) ? ACCELERATION : 1;
            deltaX = -deltaX;
        } else if (ball.getCenterY() > BOTTOM_LIMIT) {
            deltaY = -deltaY;
        } else if (ball.getCenterY() < UP_LIMIT) {
            deltaY = -deltaY;
        }

    }
    
    public static void resetGame() {
        angle = atan2(INITIAL_VY, INITIAL_VX);
        magnitude = sqrt(5);
        deltaX = magnitude * cos(angle);
        deltaY = magnitude * sin(angle);
        score.set(0);
    }


    public static SimpleIntegerProperty scoreProperty() {
       
        return score;
    }
}
