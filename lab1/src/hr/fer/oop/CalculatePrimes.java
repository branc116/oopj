package hr.fer.oop;

public class CalculatePrimes {
    public static Integer getToNumber(Integer nPrimes)
    {
        Double toNumber = nPrimes *Math.E;
        while (toNumber/(Math.log(toNumber) - 1) < nPrimes)
        {
            toNumber*=Math.E;
        }
        return toNumber.intValue() + 10;
    }
    
    public static int[] sieveOfEratosthenesWithPrimesToN(Integer toNumber)
    {
        Double nDouble = (toNumber/(Math.log(toNumber) - 1.0));
        Integer n = nDouble.intValue();
        System.out.printf("Calculating %d primes to number %d%n", n, toNumber);
        int[] primes = new int[n];
        boolean[] isNotPrime = new boolean[toNumber];
        isNotPrime[0] = true;
        isNotPrime[1] = true;

        Integer j = 0;
        for(int i = 2;i<toNumber;i++){
            if (!isNotPrime[i]){
                primes[j] = i;
                j++;
                if (primes.length <= j)
                    return primes;
                for (int k = i + i;k<toNumber;k+=i)
                {
                    isNotPrime[k] = true;
                }
            }
        }
        return primes;
    }

    /**
     * Get first n or more primes 
     */
    public static int[] sieveOfEratosthenesWithNPrimeNumbers(Integer n)
    {
        Integer toNumber = getToNumber(n);
        return sieveOfEratosthenesWithPrimesToN(toNumber);
    }
}