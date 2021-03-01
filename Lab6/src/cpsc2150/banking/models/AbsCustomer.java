package cpsc2150.banking.models;
//Author: Kevin Mody and Henry Mayo
//Class: CPSC 2151
//Sec: 006
//Date: 02/22/2021
public abstract class AbsCustomer implements ICustomer {

    //Every customer will have an IMortgage Loan
    //Protected so the child classes can access it
    protected IMortgage loan;

    @Override
    public String toString()
    {
        String str = "";
        str += "Name: " + getName() + "\n";
        str += "Income: $" + getIncome() + "\n";
        str += "Credit Score: " + getCreditScore() + "\n";
        str += "Monthly Debt: $" + getMonthlyDebtPayments() + "\n";
        str += "Mortgage info: \n";
        if( appliedForLoan()) {
            str += loan.toString();
        }

        return str;

    }
}
