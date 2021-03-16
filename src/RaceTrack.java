import java.awt.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class RaceTrack {


    public static int str=10;

    public static void main(String[] args) {

        boolean vundet;

        int vindeSpiller = 0;

        Scanner input = new Scanner(System.in);

        drawmap();







        // get number of players, n
        int n= 10;
        while(n < 1  || n > 4) // limit players to 1-4
        {
            n = getnum(input);
        }

         // vi fucker rundt rundt med arrays istedet, vi tænker max 4 spillere.
        Player[] players = new Player[n];

        for (int i = 0; n > i ; i++)
        {
            players[i] = new Player();
            players[i].playernumber=1+i;
            players[i].setCord(0,6+i);
            players[i].farve = new Color(50+ThreadLocalRandom.current().nextInt(206),50+ThreadLocalRandom.current().nextInt(206),50+ThreadLocalRandom.current().nextInt(206));

        }

        //draw each player
        for (Player player : players)
        {
            StdDraw.setPenColor(player.farve);
            StdDraw.point(player.x,player.y);

        }


        //vi prøver at bevæge
        while(anyalive(players))
        {

            for (Player player : players)
            {
                if (!player.dead && !playerWon(player))
                {
                    System.out.println("Det er spiller " + player.playernumber + "'s tur");
                    turn(player, input);

                    vundet = playerWon(player);

                    if (vundet) {

                        vindeSpiller = player.playernumber;

                        System.out.println("Tilykke til spiller nr. " + vindeSpiller + " for at have vundet spillet!");

                        break; }

                    player.dead = crash(player);


                }


            }

        }
        System.out.println("program færdig");





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

    public static int getdir(Scanner console)
    {
        System.out.print("Hvilken retning (Brug numpad): ");
        int n;
        while (true)
        {
            n=getnum(console);
            if(0 < n && 10 > n) return n;
            System.out.print("En retning er et tal mellem 1 og 9 (inklusiv), proev igen: ");
        }
    }

    public static void turn(Player player, Scanner console)
    {
        System.out.println("kører tur for spiller: " + player.playernumber);

            StdDraw.setPenColor(player.farve);
            player.setVec(getdir(console));
            StdDraw.setPenRadius(0.005);
            player.setPos();
            StdDraw.setPenRadius(0.015);
            StdDraw.point(player.x,player.y);
            player.turnnumber += 1;

        System.out.println(player.turnnumber);
    }

    public static boolean crash(Player player)
    {
        //if inner square
        if((player.y >= -(str/2) && player.y <= (str/2)  ) &&  (player.x >= -(str/2) && player.x <= (str/2)  )) return true;

        //if outer square
        else if((player.y <= -str || player.y >= str ) || (player.x <= -str || player.x >= str)) return true;

        else return false;
    }


    public static boolean anyalive(Player[] players)
    {
        for (Player player: players)
        {
            if(!player.dead) return true;

        }

        return false;

    }



    //draw stuff

    public static void drawmap()
    {
        StdDraw.setScale(-(str+1),(str+1));


        // draw grey part of map
        StdDraw.setPenColor(Color.LIGHT_GRAY);
        StdDraw.filledSquare(0,0,str);


        //draw gridlines
        StdDraw.setPenRadius();
        StdDraw.setPenColor(Color.DARK_GRAY);
        for (int i = -str; i < str; i ++ ) {

            StdDraw.line(i,-str,i,str);
            StdDraw.line(-str,i,str,i);

        }


        //draw goal line
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(Color.ORANGE);
        StdDraw.line(0,0,0,str);


        // draw outer square
        StdDraw.setPenColor();

        StdDraw.setPenRadius(0.005);

        StdDraw.square(0,0,str);


        //fill inner square
        StdDraw.setPenColor(Color.WHITE);

        StdDraw.filledSquare(0,0,str/2.0);

        StdDraw.setPenRadius(0.005);

        StdDraw.setPenColor(Color.BLACK);

        StdDraw.square(0,0,str/2.0);

        StdDraw.setPenRadius(0.015);



    }

    public static boolean playerWon(Player spiller) {

        if (spiller.giveCord()[0] >= 0 && spiller.dx > 0 && spiller.turnnumber > 10  ) {

            return true;

        }

        else {
            return false;

        }

    }



}