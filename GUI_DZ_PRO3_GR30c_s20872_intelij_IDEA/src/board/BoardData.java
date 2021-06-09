package board;

import enums.GameStateEnum;
import enums.KeyEnum;

public class BoardData {

    public static int colCounter;
    public static  int rowCounter;
    public static int squareSize = 20;
    public static int finalScore;





   static int boardWidth;
    static  int boardHeight;

   static int PointCol;
     static int PointRow;
    static int pointsForField = 10;
    static KeyEnum key = KeyEnum.RIGHT;
    static int score;
    static int timeCounter=0;
    public static GameStateEnum gameState = GameStateEnum.STOPPED;

    public static boolean isShiftDown,isCtrlDown,isQDown;

    public static int getScore() {
        return score;
    }

    public static void setScore(int newScore)
    {
       score = newScore;
    }

    public static void setTimeCounter(int timeCounter) {
        BoardData.timeCounter = timeCounter;
    }

    public static int getTimeCounter() {
        return timeCounter;
    }

    public static void countDimensions()
    {
         boardWidth = colCounter*squareSize;
         boardHeight = rowCounter*squareSize+40;
    }

    public static void setFinalScore()
    {

        finalScore = score+(colCounter*rowCounter)-timeCounter;
        if(finalScore<0)finalScore=0;
    }
}
