package cpsc2150.banking.controllers;
//Author: Kevin Mody and Henry Mayo
//Class: CPSC 2151
//Sec: 006
//Date: 04/01/2021
import cpsc2150.banking.models.Customer;
import cpsc2150.banking.models.Mortgage;
import cpsc2150.banking.views.IMortgageView;

import java.util.Scanner;

public class MortgageController implements IMortgageController {
    IMortgageView v;
    double cost, dp, md, ic;
    int years, cs;
    int MAX_CREDIT_SCORE = 850;
    String name;
    Scanner s = new Scanner(System.in);

public MortgageController(IMortgageView view){
    v = view;
}

@Override
public void submitApplication() {

    while (true) {
        boolean t = false;
        while (!t) {
            name = v.getName();
            if (name != null) {
                t = true;
            }
        }

        t = false;
        while (!t) {
            ic = v.getYearlyIncome();

            if (ic >= 0) {
                t = true;

            }else{
                v.printToUser("Income must be greater than 0.");
            }
        }

        t = false;
        while (!t) {
            md = v.getMonthlyDebt();

            if (md >= 0) {
                t = true;
            }else{
                v.printToUser("Debt must be greater than or equal to 0.");
            }
        }

        t = false;
        while (!t) {
            cs = v.getCreditScore();

            if (cs >= 0 && cs <= MAX_CREDIT_SCORE) {
                t = true;
            }else{
                v.printToUser("Credit Score must be greater than 0 and less than 850");
            }
        }


        boolean fuck = true;

        while(fuck) {
            t = false;
            while (!t) {
                cost = v.getHouseCost();

                if (cost >= 0) {
                    t = true;
                }else{
                    v.printToUser("Cost must be greater than 0.");
                }
            }
            t = false;
            while (!t) {
                dp = v.getDownPayment();
                if (dp >= 0 && dp <= cost) {
                    t = true;
                }else{
                    v.printToUser("Down Payment must be greater than 0 and less than the cost of the house.");

                }
            }
            t = false;
            while (!t) {
                years = v.getYears();
                v.printToUser("Years must be greater than 0.");
                if (years >= 1) {
                    t = true;
                }
            }
            Customer u = new Customer(md, ic, cs, name);
            u.applyForLoan(dp,cost,years);
            v.printToUser(u.toString());
            fuck = v.getAnotherMortgage();
        }
        if (!v.getAnotherCustomer()) {
            break;
        }
    }
}
}

