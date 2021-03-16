public class fuckingrundt
{


    public static void main(String[] args)
    {
        for (int i = 0; 100 > i ; i++)
        {
            if (isPrime(i)) System.out.println(i);


        }
    }


    public static boolean isPrime(long number)
    {
        if(1 == number) return true;
        else if(2 == number) return true;
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
}
