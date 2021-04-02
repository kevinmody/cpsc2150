package cpsc2150.banking.models;
//Author: Kevin Mody and Henry Mayo
//Class: CPSC 2151
//Sec: 006
//Date: 04/01/2021
public abstract class AbsMortgage implements IMortgage {

    /**
     *
     * @return the string representation of this loan details, or a notice that the loan was not approved
     * @requires this != null and [interestRate and payment have been calculated]
     * @ensures [toString = The string value of the loan, or "Loan was not approved"]
     */
    @Override
    public String toString()
    {
        String str = "";
        if(loanApproved())
        {
            str += "Mortgage Info:\n";
            str += "Principal Amount: $" + getPrincipal() + "\n";
            str += "Interest Rate: " + (getRate() * 100) + "%\n";
            str += "Term: " + getYears() + " years\n";
            str += "Monthly Payment: $" + getPayment() + "\n";
        }
        else
        {
            str += "Loan was not approved";
        }
        return str;
    }
}
