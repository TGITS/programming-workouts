import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Sieve {
    private List<Integer> primesUpToMaxPrime;

    Sieve(final int maxPrime) {
        if (maxPrime <= 1) {
            primesUpToMaxPrime = Collections.emptyList();
        } else {
            List<Integer> twoToMaxPrimeRange = IntStream.rangeClosed(2, maxPrime).boxed().collect(Collectors.toList());
            int prime = twoToMaxPrimeRange.remove(0);
            primesUpToMaxPrime = new ArrayList<>();
            boolean loop = true;
            while (loop) {
                primesUpToMaxPrime.add(prime);
                twoToMaxPrimeRange.removeAll(getMultiplesUpToMaxPrime(prime, maxPrime));
                if (twoToMaxPrimeRange.isEmpty() || prime >= maxPrime) {
                    loop = false;
                } else {
                    prime = twoToMaxPrimeRange.remove(0);
                }
            }
        }
    }

    private List<Integer> getMultiplesUpToMaxPrime(int prime, int maxPrime) {
        int number = prime;
        int multiple = number * prime;
        List<Integer> multiples = new ArrayList<>();
        while (multiple <= maxPrime) {
            multiples.add(multiple);
            number++;
            multiple = number * prime;
        }
        return multiples;
    }

    List<Integer> getPrimes() {
        return primesUpToMaxPrime;
    }
}
