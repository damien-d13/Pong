package fr.damien.utils;

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
    private static double magnitude = sqrt(26);

    private static double deltaX = magnitude * cos(angle);
    private static double deltaY = magnitude *sin(angle);


    public static void handlePlayer(Rectangle player, double playerVelocY ) {
        player.setY(player.getY() >= 0 && player.getY() <= 600 ? player.getY()  + playerVelocY : player.getY() );
    }
    public static void updateGame(Rectangle player, Rectangle computer, Circle ball) {
        ball.setCenterX(ball.getCenterX() - 4);
    }
    
    public static void resetGame() {

    }
}
