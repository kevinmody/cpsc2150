package cpsc2150.banking.controllers;
//Author: Kevin Mody and Henry Mayo
//Class: CPSC 2151
//Sec: 006
//Date: 04/06/2021

import cpsc2150.banking.models.Customer;
import cpsc2150.banking.models.ICustomer;
import cpsc2150.banking.models.IMortgage;
import cpsc2150.banking.models.Mortgage;
import cpsc2150.banking.views.IMortgageView;


public class MortgageGUIController implements IMortgageController {
    private IMortgageView view;
    int MAX_CREDIT_SCORE = 850;
    public MortgageGUIController(IMortgageView v){
        view = v;
    }
    public void submitApplication() {
        boolean noneError=true;
        try{
            view.getYearlyIncome();
            view.getMonthlyDebt();
            view.getCreditScore();
            view.getHouseCost();
            view.getDownPayment();
        }
        catch(Exception e){
            view.printToUser("Please fill out all the boxes!");
            noneError= false;
        }

        if(noneError) {
            if (view.getName().isEmpty())
                view.printToUser("Please enter a name.");
            else if (view.getYearlyIncome() <= 0)
                view.printToUser("Income must be greater than 0");
            else if (view.getMonthlyDebt() < 0)
                view.printToUser("Debt must be greater than or equal to 0.");
            else if (view.getCreditScore() <= 0 || view.getCreditScore() >= MAX_CREDIT_SCORE)
                view.printToUser("Credit Score must be greater than 0 and less than 850");
            else if (view.getHouseCost() <= 0)
                view.printToUser("Cost must be greater than 0.");
            else if (view.getDownPayment() <= 0 || view.getDownPayment() >= view.getHouseCost())
                view.printToUser("Down Payment must be greater than 0 and less than the cost of the house.");
            else {
                view.printToUser("Provide Customer and Mortgage information");

                ICustomer customer = new Customer(view.getMonthlyDebt(), view.getYearlyIncome(), view.getCreditScore(),
                        view.getName());
                IMortgage mortgage = new Mortgage(view.getHouseCost(), view.getDownPayment(), view.getYears(),
                        customer);

                boolean approved = mortgage.loanApproved();
                if (approved) {
                    view.displayApproved(true);
                    view.displayRate(mortgage.getRate());
                    view.displayPayment(mortgage.getPayment());
                } else {
                    view.displayApproved(false);
                    view.displayRate(0);
                    view.displayPayment(0);
                }
            }
        }
    }
}

