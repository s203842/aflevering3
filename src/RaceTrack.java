import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;


public class RaceTrack {


    public static int str=10;
    public static int str2 = str/2;

    public static void main(String[] args) {

        ArrayList<Line2D> wallslist = new ArrayList<>(); //an arraylist with the walls of our track

        //horizontal outer
        wallslist.add(new Line2D.Double(-str,str,str,str));
        wallslist.add(new Line2D.Double(-str,-str,str,-str));

        //vertical outer
        wallslist.add(new Line2D.Double(-str,-str,-str,str));
        wallslist.add(new Line2D.Double(str,-str,str,str));

        //horizontal inner
        wallslist.add(new Line2D.Double(-str2,str2,str2,str2));
        wallslist.add(new Line2D.Double(-str2,-str2,str2,-str2));

        //vertical inner
        wallslist.add(new Line2D.Double(-str2,-str2,-str2,str2));
        wallslist.add(new Line2D.Double(str2,-str2,str2,str2));

        // final checkline is the goalpost
        Line2D[] checklines = new Line2D[4];
        checklines[0] = new Line2D.Double(str2,0,str,0);
        checklines[1] = new Line2D.Double(0,-str2,0,-str);
        checklines[2] = new Line2D.Double(-str2,0,-str,0);
        checklines[3] = new Line2D.Double(0,str2,0,str);





        StdDraw.setScale(-(str+1),(str+1));

        boolean vundet;

        int vindeSpiller = 0;
        boolean gamewon=false;

        Scanner input = new Scanner(System.in);
        drawmap();

        // noget med guide

        System.out.print("Vil du have have hjælpelinjer (y/n) ");
        boolean guideon= "y".equals(input.next());
        input.nextLine();

        System.out.print("genopliv (y/n) ");
        boolean jesus = "y".equals(input.next());

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
        while(anyplaying(players))
        {

            for (Player player : players)
            {
                if(player.playing)
                {
                    if (!player.dead)
                    {

                        player.coordhis.add(new int[]{player.x,player.y});

                        System.out.println("Det er spiller " + player.playernumber + "'s tur");
                        if(guideon) drawguide(player);

                        turn(player, input,wallslist,checklines);

                        player.coordhis.add(new int[]{player.x,player.y});

                        if(!jesus && player.dead) player.playing=false;

                        //gamewon = playerWon(player);

                       /* if (gamewon)
                        {

                            vindeSpiller = player.playernumber;

                           System.out.println("Tilykke til spiller nr. " + vindeSpiller + " for at have vundet spillet! Det tog " + player.turnnumber + " ture");
                        break;
                        }*/

                        drawmap();
                        drawplayers(players);


                    }
                    else if(jesus)
                    {
                        revive(player);
                        player.turnnumber++;

                    }



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

    public static void turn(Player player, Scanner console,ArrayList<Line2D> walls,Line2D[] checklines)
    {
        System.out.println("kører tur for spiller: " + player.playernumber);

        player.setVec(getdir(console));
        Line2D drive = new Line2D.Double(player.x,player.y,player.x+player.dx,player.y+player.dy);

        player.turnnumber++;

        player.dead = crash(player,walls,drive);
        checkpoint(player,drive,checklines);


        StdDraw.setPenColor(player.farve);
        StdDraw.setPenRadius(0.005);
        player.setPos();
        StdDraw.setPenRadius(0.015);
        StdDraw.point(player.x,player.y);
    }

    public static boolean crash(Player player,  ArrayList<Line2D> walls, Line2D drive)
    {
        AtomicBoolean crash = new AtomicBoolean(false);

        //We multithrading bois
        walls.forEach((wall) ->
        {
            if(drive.intersectsLine(wall))
            {
                if(!crash.get())
                {
                    System.out.println("Spiller " + player.playernumber + " er kørt galt.");
                }
                crash.set(true);
            }
        });
        return crash.get();

        /* for (Line2D wall:walls)
        {
            if(drive.intersectsLine(wall))
            {
                System.out.println("Spiller " + player.playernumber + " er kørt galt.");

                return true;
            }
        }
        return false; */

            /*
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
        */

    }

    public static boolean anyplaying(Player[] players)
    {
        for (Player player: players)
        {
            if(player.playing) return true;

        }
        return false;
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
        if (spiller.giveCord()[0] >= 0 && spiller.dx > 0 && spiller.turnnumber > 10) return true;
        else return false;
    }

    public static void revive(Player player)
    {
        int x=0,y=str2+player.playernumber;

        switch (player.checkpoint)
        {
            case 1 ->
            {
                x = y;
                y = 0;
            }

            case 2 -> y = -y;

            case 3 ->
            {
                x = -y;
                y = 0;
            }
        }

        player.revive(x,y,0,0);

    }

    public static void checkpoint(Player player, Line2D drive, Line2D[] lines)
    {
        /*if(lines.length-1 == player.checkpoint && drive.intersectsLine(lines[player.checkpoint])) player.playing = false;*/

         if(drive.intersectsLine(lines[player.checkpoint])) player.checkpoint++;
         if(player.checkpoint==lines.length)
         {
             player.playing = false;
             System.out.println("Spiller " + player.playernumber + " har koert en runde paa " + player.turnnumber + " ture!");
         }
    }



    //draw stuff


    //ændrer drawmap til at tegne linjer istedet? siden det gør det mere modulært?
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