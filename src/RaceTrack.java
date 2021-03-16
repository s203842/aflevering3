import java.awt.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class RaceTrack {


    public static int str=10;

    public static void main(String[] args) {

        StdDraw.setScale(-(str+1),(str+1));

        boolean vundet;

        int vindeSpiller = 0;
        boolean gamewon=false;

        Scanner input = new Scanner(System.in);
        drawmap();

        // noget med guide

        System.out.print("Vil du have have hjælpelinjer (y/n)?");
        boolean guideon= "y".equals(input.next());


        // get number of players, n
        int n= getnum(input, "Hvor mange spillere (1-4): ");
        while(n < 1  || n > 4) // limit players to 1-4
        {
            System.out.println("Et tal mellem 1 og 4. ");
            n = getnum(input, "Hvor mange spillere (1-4): ");
        }

         // vi fucker rundt rundt med arrays istedet, vi tænker max 4 spillere.
        Player[] players = new Player[n];


        //initialize each player
        for (int i = 0; n > i ; i++)
        {
            players[i] = new Player();
            players[i].playernumber=1+i;
            players[i].setCord(0,6+i);
            players[i].farve = new Color(50+ThreadLocalRandom.current().nextInt(206),50+ThreadLocalRandom.current().nextInt(206),50+ThreadLocalRandom.current().nextInt(206));
            players[i].coordhis.add(new int[]{players[i].x,players[i].y});
            players[i].coordhis.add(new int[]{players[i].x,players[i].y});
        }



        //vi prøver at bevæge
        while(anyalive(players) && !gamewon)
        {

            for (Player player : players)
            {
                drawmap();
                drawplayers(players);
                if (!player.dead)
                {

                    player.coordhis.add(new int[]{player.x,player.y});

                    System.out.println("Det er spiller " + player.playernumber + "'s tur");
                    if(guideon) drawguide(player);

                    turn(player, input);

                    player.coordhis.add(new int[]{player.x,player.y});

                    gamewon = playerWon(player);

                    if (gamewon)
                    {

                        vindeSpiller = player.playernumber;

                        System.out.println("Tilykke til spiller nr. " + vindeSpiller + " for at have vundet spillet! Det tog " + player.turnnumber + " ture");
                     break;}

                    player.dead = crash(player);


                }


            }

        }
        drawmap();
        drawplayers(players);

        System.out.println(" \n Spil slut");





    }


    public static int getnum(Scanner console,String prompt)
    {
        System.out.print(prompt);
        while (!console.hasNextInt())
        {
            console.nextLine();
            System.out.println("a sa æ tal");
            System.out.print(prompt);
        }
        return console.nextInt();
    }

    public static int getdir(Scanner console)
    {
        int n;
        while (true)
        {
            n=getnum(console,"Hvilken retning (Brug numpad): ");
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
    }

    public static boolean crash(Player player)
    {
        //if inner square
        if((player.y >= -(str/2) && player.y <= (str/2)  ) &&  (player.x >= -(str/2) && player.x <= (str/2)  ))
        {
            System.out.println("Spiller " + player.playernumber + " er kørt galt.");
            return true;

        }

        //if outer square
        else if((player.y <= -str || player.y >= str ) || (player.x <= -str || player.x >= str))
        {
            System.out.println("Spiller " + player.playernumber + " er kørt galt.");
            return true;
        }

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

    public static boolean playerWon(Player spiller)
    {

        if (spiller.giveCord()[0] >= 0 && spiller.dx > 0 && spiller.turnnumber > 10  )
        {
            return true;
        }

        else
        {
            return false;

        }

    }



    //draw stuff

    public static void drawmap()
    {
        //clear map
        StdDraw.clear();


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

    public static void drawplayers(Player[] players)
    {
        int x0,x1,y0,y1;
        for (Player player: players)
        {
            StdDraw.setPenColor(player.farve);



            for (int i = 0; i < player.coordhis.size() ; i+=2)
            {
                x0=player.coordhis.get(i)[0];
                x1=player.coordhis.get(i+1)[0];
                y0=player.coordhis.get(i)[1];
                y1=player.coordhis.get(i+1)[1];
                StdDraw.setPenRadius(0.015);
                StdDraw.point(x0,y0);

                StdDraw.setPenRadius(0.005);

                StdDraw.line(x0,y0,x1,y1);

                StdDraw.setPenRadius(0.015);
                StdDraw.point(x1,y1);

            }

        }

    }

    public static void drawguide(Player player)
    {
        int x0=player.x+player.dx, y0 = player.y+ player.dy;

        StdDraw.setPenColor(Color.YELLOW);

        //draw 5
        StdDraw.setPenRadius(0.01);
        StdDraw.point(x0,y0);

        //draw other 8
        StdDraw.setPenRadius(0.003);

        //from player to guide
        StdDraw.line(player.x,player.y,x0,y0);


        //directions

        for (int i = -1; i <2 ; i++)
        {
            for (int j = -1; j <2 ; j++)
            {
                StdDraw.line(x0,y0,x0+i,y0+j);
            }

        }

        /*
        StdDraw.line(x0,y0,x0,y0-1);
        StdDraw.line(x0,y0,x0,y0+1);
        StdDraw.line(x0,y0,x0-1,y0-1);
        StdDraw.line(x0,y0,x0-1,y0);
        StdDraw.line(x0,y0,x0-1,y0+1);
        StdDraw.line(x0,y0,x0+1,y0-1);
        StdDraw.line(x0,y0,x0+1,y0);
        StdDraw.line(x0,y0,x0+1,y0+1);
        */
    }






}