package cpsc2150.banking.models;

//Author: Kevin Mody and Henry Mayo
//Class: CPSC 2151
//Sec: 006
//Date: 02/22/2021
public class MortgageApp {

    public static void main(String [] args)
    {
        // House-related information
        double cost = 150000;
        double dp = cost * .1;
        int y = 15;

        // Billy Bob's information
        double debt = 100;
        double inc = 50000;
        int score = 850;
        String name = "Billy Bob";

        // Call customer constructor and apply for a loan
        ICustomer c = new Customer(debt, inc, score, name);
        c.applyForLoan(dp, cost, y);

        // Print the results of mortgage application
        System.out.println(c.toString());


    }
}
