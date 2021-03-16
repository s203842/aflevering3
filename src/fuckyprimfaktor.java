import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

public class fuckyprimfaktor
{


    public static void main(String[] args)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        Scanner consol = new Scanner(System.in);
        long number = getnum(consol);
        long then = System.currentTimeMillis();
        System.out.println("Start: "+ dtf.format(LocalDateTime.now()));
        long onumber = number;



        ArrayList<Long> factors = new ArrayList<Long>();

        System.out.println("prime faktorisering af: " + number);

        while (!isPrime(number) && !(number<=1))
        {
            long prim = ThreadLocalRandom.current().nextLong(number-2)+2;
                if(number % prim ==0 && isPrime(prim))
                {
                    System.out.println("factor: " + prim);
                    number = number / prim;
                    factors.add(prim);
                }
        }
        factors.add(number);
        Collections.sort(factors);
        System.out.print("number : " + onumber +" has the following primefactors");
        factors.forEach((factor ) ->
                System.out.print(", " +factor));
        System.out.println();
        System.out.println("Slut: " + dtf.format(LocalDateTime.now()));
        long now = System.currentTimeMillis();
        System.out.println("it took");
        float sec = (now - then) / 1000F; System.out.println(sec + " seconds");
    }

    public static boolean isPrime(long number)
    {
        for (long i = 2;  number > i; i++)
        {
            if (number % i == 0)
            {
                return false;
            }
        }
        return true;
    }

    public static long getnum(Scanner console)
    {
        System.out.print("Giz tal: ");
        while (!console.hasNextLong())
        {
            console.next();
            System.out.println("tal tak.");
            System.out.print("Giz nyt tal: ");
        }
        return console.nextLong();
    }

}
