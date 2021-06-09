package highscore;


import java.io.*;
import java.util.ArrayList;


public class Score  {
    private static File scoreFile = new File("scores.txt");
    public static  ArrayList<Score> sortedScores = new ArrayList<>();
    ArrayList<Score> scoresList = new ArrayList<>();
    private int result;
    private String nick;
    private String resultState;




    public Score(String nick,int result,String resultState) {
        this.nick = nick;
        this.result = result;
        this.resultState = resultState;



    }



    public  String toString()
    {
        return nick+" "+result+" "+resultState;
    }


    public void addScore()
    {
        try {

            BufferedWriter bw = new BufferedWriter(new FileWriter(scoreFile,true));
            bw.write(nick+"_____"+result+"_____"+resultState+"\n");
            bw.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void sortScores()
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader(scoreFile));
            ArrayList<Score> scoresList = new ArrayList<>();
            String currentLine = br.readLine();
            while(currentLine!=null)
            {
                String[]scoresLine = currentLine.split("_____");
                String nick = scoresLine[0];
                int score = Integer.parseInt(scoresLine[1]);
                String resultState = scoresLine[2];
                scoresList.add(new Score(nick,score,resultState));
                currentLine = br.readLine();

            }

            scoresList.sort((Score o1, Score o2) -> o2.result - o1.result);

            BufferedWriter bw = new BufferedWriter(new FileWriter(scoreFile));
            for(Score s : scoresList)
            {
                bw.write(s.nick+"_____"+s.result+"_____"+s.resultState);
                sortedScores.add(s);
                bw.newLine();
            }
            bw.close();
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}

