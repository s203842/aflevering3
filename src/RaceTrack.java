import java.util.*;


public class RaceTrack {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int n = getnum(input);

        int speed;

        int str = 10;

        StdDraw.setScale(-(str+1),(str+1));

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

        }


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