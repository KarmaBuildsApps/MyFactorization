package myapp.tae.ac.uk.myfactorizerapp.presenter;

import java.util.ArrayList;

/**
 * Created by Karma on 10/03/16.
 */
public class FactorizerService {

    public boolean isPrimeNumber(double number) {
        boolean isPrime = true;
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                isPrime = false;
            }
        }
        return isPrime;
    }

    public String convertToPrettyString(ArrayList<Double> factors) {
        StringBuilder sb = new StringBuilder();
        int factor = 0;
        for (int i = 0; i < factors.size(); i++) {
            sb.append(factors.get(i).intValue());
            if (i != factors.size() - 1) {
                sb.append("x");
            }
        }
        return sb.toString();
    }

    public void updateResult(String string) {

    }
}
