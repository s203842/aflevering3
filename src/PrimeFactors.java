import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;


public class PrimeFactors
{

    public static void main(String[] args)
    {
        Scanner consol = new Scanner(System.in);
        long number = getnum(consol);

        while(!(0 == number))
        {
            System.out.println("The number: " + number + " has the following primefactors: " + goodfactor(number));
        System.out.println( "\n");

        System.out.println("Type 1 for bad factor as well.");
        if(1==getnum(consol))
       {
            System.out.println("The number: " + number + " has the following primefactors: " + badfactor(number));
            System.out.println("\n \n \n");
        }

        number = getnum(consol);
        }



    }

    public static String goodfactor(long number)
    {
        long prim = 2;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        long then = System.currentTimeMillis();
        System.out.println("Start: "+ dtf.format(LocalDateTime.now()));
        ArrayList<Long> factors = new ArrayList<>();
        System.out.println("Prime factorisation of: " + number);

        while( number % prim ==0)
        {
            factors.add(prim);
            number /= prim;
        }
        prim++;

        while(Math.sqrt(number)+1 > prim )
        {
            if(number % prim ==0)
            {
               //System.out.println("factor: " + prim);
                number = number / prim;
                factors.add(prim);

                //we only want to test if we actually change the number.
                if(isPrime(number)) break;
            }
            else prim +=2;
        }
        factors.add(number);

        System.out.println("Slut: " + dtf.format(LocalDateTime.now()));
        long now = System.currentTimeMillis();
        System.out.println("it took");
        float sec = (now - then) / 1000F; System.out.println(sec + " seconds");
        return ( Arrays.toString(factors.toArray()) .replaceAll("[\\[\\]]", ""));
    }

    public static String badfactor(long number)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        long then = System.currentTimeMillis();
        System.out.println("Start: "+ dtf.format(LocalDateTime.now()));
        ArrayList<Long> factors = new ArrayList<>();
        System.out.println("prime faktorisering af: " + number);

        if(!isPrime(number))  while (!(number<=3))
        {
            long prim = ThreadLocalRandom.current().nextLong(number-2)+2;
            if(number % prim ==0 && isPrime(prim))
            {
               // System.out.println("factor: " + prim);
                number = number / prim;
                factors.add(prim);

                //we only want to test if we actually change the number.
                if(isPrime(number)) break;
            }

        }
        factors.add(number);
        Collections.sort(factors);
        String factorss = ( Arrays.toString(factors.toArray()) .replaceAll("[\\[\\]]", ""));
        System.out.println("Slut: " + dtf.format(LocalDateTime.now()));
        long now = System.currentTimeMillis();
        System.out.println("it took");
        float sec = (now - then) / 1000F; System.out.println(sec + " seconds");

        return factorss;

    }

    public static boolean isPrime(long number)
    {
        if(number == 2) return true;
        else if (number % 2 == 0) return false;

        for (long i = 3;  Math.sqrt(number)+1 > i; i+=2)
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
        System.out.print("Giz æ tal over 0, (0 for at afslutte): ");
        while (!console.hasNextLong())
        {
            console.nextLine();
            System.out.println("a sa æ tal");
            System.out.print("Giz æ ny tal: ");
        }
        return console.nextLong();
    }

    public static long nxtprime(long oldprime, long maxn)
    {
         if(2 == oldprime ) return 3;
        for (long  i = oldprime+2;  maxn > i  ; i+=2)
        {
            if(isPrime((i))) return i;
        }
        return 10;
    }

}
