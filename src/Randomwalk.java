import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;


public class Randomwalk
{

    public static void main(String[] args)
    {
        Scanner consol = new Scanner(System.in);

        int n = -1, x=0, y=0, cnt = 0;
        while(n < 1)
        {
            n=getnum(consol);
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        long then = System.currentTimeMillis();
        System.out.println("Start: "+ dtf.format(LocalDateTime.now()));


        StdDraw.setScale(-n, n);
        StdDraw.setPenRadius(0.5/n);


        while(Math.abs(x) < n && Math.abs(y) < n )
        {
            System.out.println("Position = ("+x+","+y+") "); //print location
            StdDraw.point(x,y);

            //take step
            switch (ThreadLocalRandom.current().nextInt(4))
            {
                //east / right
                case 0 -> x++;

                //north / up
                case 1 -> y++;

                //west / left
                case 2 -> x--;

                //south / down
                default -> y--;
            }
            cnt++;
        }
        System.out.println("RandomWalk for n = " + n);
        System.out.println("Walked: " + cnt + " steps.");

        System.out.println("Slut: " + dtf.format(LocalDateTime.now()));
        long now = System.currentTimeMillis();
        System.out.println("it took");
        System.out.println(((now - then) / 1000F) + " seconds");


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
