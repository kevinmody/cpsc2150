package cpsc2150.banking.views;
//Author: Kevin Mody and Henry Mayo
//Class: CPSC 2151
//Sec: 006
//Date: 04/01/2021
import cpsc2150.banking.controllers.IMortgageController;

import java.util.Scanner;

public class MortgageView implements IMortgageView {

        Scanner s = new Scanner(System.in);

        IMortgageController imc;

    @Override
    public void setController(IMortgageController c) {
        imc = c;
    }

    @Override
    public double getHouseCost() {
        System.out.println("How much does the house cost");
        double cost = s.nextDouble();
        s.nextLine();

        return cost;
    }

    @Override
    public double getDownPayment() {
        System.out.println("How much is the down payment?");
        double dp = s.nextDouble();
        s.nextLine();

        return dp;
    }

    @Override
    public int getYears() {
        System.out.println("How many years ?");
        int y = s.nextInt();
        s.nextLine();

        return y;
    }

    @Override
    public double getMonthlyDebt() {
        System.out.println("How much are your monthly debt payments?");
        double md = s.nextDouble();
        s.nextLine();

        return md;
    }

    @Override
    public double getYearlyIncome() {
        System.out.println("How much is your yearly income?");
        double yi = s.nextDouble();
        s.nextLine();
        return yi;
    }

    @Override
    public int getCreditScore() {
        System.out.println("What is the credit score?");
        int cs = s.nextInt();
        s.nextLine();

        return cs;
    }

    @Override
    public String getName() {
        System.out.println("What's your name");
        return s.nextLine();
    }

    @Override
    public void printToUser(String s) {
        System.out.println(s);
    }

    @Override
    public void displayPayment(double p) {
        System.out.println("Principal Amount: $" + p);
    }

    @Override
    public void displayRate(double r) {
        System.out.println("Interest Rate: " + r + "%");
    }

    @Override
    public void displayApproved(boolean a) {
        System.out.println(a);
    }

    @Override
    public boolean getAnotherMortgage() {

        System.out.println("Would you like to apply for another mortgage? Y/N");
        char input = s.next().toUpperCase().charAt(0);
        s.nextLine();
        return input == 'Y';
    }

    @Override
    public boolean getAnotherCustomer() {
        System.out.println("Would you like to consider another customer Y/N");
        char input = s.next().toUpperCase().charAt(0);
        s.nextLine();
        return input == 'Y';
    }
}