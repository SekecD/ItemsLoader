package ru.mastaa.qub.Math;

public class TaxCalculator {
    public double calculateTax(int[][] brackets, int income) {
        double totalTax = 0;

        for (int i = 0; i < brackets.length; i++) {
            int upper = brackets[i][0];
            int percent = brackets[i][1];

            if (income <= upper) {
                totalTax += income * percent / 100.0;
                break;
            } else {
                totalTax += upper * percent / 100.00;
                income -= upper;
            }
        }
        return totalTax;
    }
}
