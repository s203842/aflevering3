import java.awt.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class RaceTrack {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int str = 10;

        StdDraw.setScale(-(str+1),(str+1));

        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);

        StdDraw.filledSquare(0,0,str);
        StdDraw.setPenColor();


        StdDraw.setPenRadius(0.005);

        StdDraw.square(0,0,str);

        StdDraw.setPenRadius();

        for (int i = -str; i < str; i ++ ) {

            StdDraw.line(i,-str,i,str);
            StdDraw.line(-str,i,str,i);

        }

        StdDraw.setPenColor(StdDraw.WHITE);

        StdDraw.filledSquare(0,0,str/2.0);

        StdDraw.setPenRadius(0.005);

        StdDraw.setPenColor(StdDraw.BLACK);

        StdDraw.square(0,0,str/2.0);

        StdDraw.setPenRadius(0.015);


        // get number of players, n
        int n= 10;
        while(n < 1  || n > 4)
        {
            n = getnum(input);
        }

         // vi fucker rundt rundt med arrays istedet, vi tænker max 4 spillere.
        Player[] players = new Player[n];

        for (int i = 0; n > i ; i++)
        {
            players[i] = new Player();
            players[i].setCord(0,6+i);
            players[i].farve = new Color(50+ThreadLocalRandom.current().nextInt(206),50+ThreadLocalRandom.current().nextInt(206),50+ThreadLocalRandom.current().nextInt(206));

        }



        for (Player player : players)
        {
            StdDraw.setPenColor(player.farve);
            StdDraw.point(player.x,player.y);

        }



        /*
        switch (n) {

            case 1:
                Player player1 = new Player();

                break;

            case 2:

                Player player1 = new Player();
                Player player2 = new Player();

                break;

            case 3:

                Player player1 = new Player();
                Player player2 = new Player();
                Player player3 = new Player();

        }*/


    }


    public static int getnum(Scanner console)
    {
        System.out.print("Giz æ tal over 0: ");
        while (!console.hasNextInt())
        {
            console.nextLine();
            System.out.println("a sa æ tal");
            System.out.print("Giz æ ny tal: ");
        }
        return console.nextInt();
    }


}