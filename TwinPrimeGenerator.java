/**
 * This class that generates twin primes with a given range of numbers.
 * 
 * @author Brian Wu
 */
public class TwinPrimeGenerator{

    /**
     * This method generates twin prime numbers within a given range.
     * @param min Minimum.
     * @param max Maximum.
     * @return A prime integer m that its twin prime m - 2 are both in the range given.
     */
    public static int generateTwinPrime(int min, int max){
        int minimum, maximum;
        minimum = min;
        maximum = max;

        for(int i = minimum + 2; i <= maximum; i++){ //check all numbers in range
            if(isPrime(i) && isPrime(i - 2)){ //if m and m-2 are both a prime (twin prime checker)
                return i;
            }   
        }

        return -1; //unsure what to do when it cannot find a twin prime in the set
    }

    /**
     * This method is a helper for the generateTwinPrime() method that declares if a number
     * is prime or not using the prime number rule.
     * @param number The number being tested.
     * @return Whether the number is price or not.
     */
    private static boolean isPrime(int number){
        boolean isIt = true;
        if(number < 2){ //checker for 0 and 1
            return !isIt;
        }
        for(int i = 2; i < number; i++){
            
            if(number % i == 0){ //if the number isn't a prime
                isIt = false;
            }
        }
        return isIt;
    }

    /**
     * A simple smoke test class that verifies the class.
     * @param args
     */
    public static void main(String[] args){
        // int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
        // int[] composities = {4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22, 24, 25, 26, 27, 28, 30, 32, 33, 34, 35, 36, 38, 39, 40, 42, 44, 45, 46, 48, 49, 50, 51, 52, 54, 55, 56, 57, 58, 60, 62, 63, 64, 65, 66, 68, 69, 70, 72, 74, 75, 76, 77, 78, 80, 81, 82, 84, 85, 86, 87, 88, 90, 91, 92, 93, 94, 95, 96, 98, 99, 100};
        
        // System.out.println(generateTwinPrime(95500, 96000));

        // for(int i = 0; i < primes.length; i++){ //should return nothing but true
        //     System.out.println(isPrime(primes[i]));
        // }

        // for(int i = 0; i < composities.length; i++){ //should return nothing but false
        //     System.out.println(isPrime(composities[i]));
        // }
    }

}

