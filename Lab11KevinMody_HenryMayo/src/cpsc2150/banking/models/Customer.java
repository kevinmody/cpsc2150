package cpsc2150.banking.models;
//Author: Kevin Mody and Henry Mayo
//Class: CPSC 2151
//Sec: 006
//Date: 04/06/2021
/**
 *
 * @Invariants name != "" and
 *              monthlyDeptPayments >= 0 and
 *              income >= 0 and
 *              0<= creditScore <= 850
 */
public class Customer extends AbsCustomer {
    private final String name;
    private final double monthlyDebtPayments;
    private final double income;
    private final int creditScore;

    /**
     *
     * @param debt the customer's monthly debt payments
     * @param inc the customer's yearly income
     * @param score the customers credit score
     * @param n the customers name
     * @requires debt >= 0 and inc >= 0 and 0 <= score <= MAX_CREDIT_SCORE and n != ""
     * @ensures monthlyDebtPayments = debt and income = inc and creditScore = score and name = n
     */
    public Customer(double debt, double inc, int score, String n)
    {

        monthlyDebtPayments = debt;
        income = inc;
        creditScore = score;
        name = n;


    }


    /**
     *
     * @param downPayment the down payment on the loan
     * @param houseCost the cost of the house they want to buy
     * @param years the number of years they will take to repay the loan
     * @return true if the loan was approved
     * @requires 0 <= downPayment < houseCost and houseCost > 0 and y in (15, 20, 25, 30)
     * @ensures [applyForLoan iff the loan was approved with those terms]
     */
    @Override
    public boolean applyForLoan(double downPayment, double houseCost, int years)
    {
        loan = new Mortgage(houseCost, downPayment, years, this);
        return loan.loanApproved();
    }

    /**
     * @requires [the customer has applied for and received a loan]
     * @ensures [getRate = interest rate of the approved loan] and 0 <= getRate <= 1
     * @return the interest rate on the customer's loan
     */
    @Override
    public double getRate()
    {
        return loan.getRate();
    }

    /**
     *
     * @return the monthly payment on the customer's loan
     * @requires [the customer has applied for and received a loan]
     * @ensures [getMonthlyPay = the monthly payment on the customer's loan] and 0<getMonthlyPay
     */
    @Override
    public double getMonthlyPay()
    {
        return loan.getPayment();
    }

    /**
     *
     * @return the customer's monthly debt payments not including the loan
     * @requires this != null
     * @ensures getMonthlyDebtPayments = monthlyDebtPayments
     */
    @Override
    public double getMonthlyDebtPayments()
    {
        return monthlyDebtPayments;
    }

    /**
     *
     * @return the customer's yearly income
     * @requires this != null
     * @ensures getIncome = income
     */
    @Override
    public double getIncome()
    {
        return income;
    }

    /**
     *
     * @return the customer's credit score
     * @requires this != null
     * @ensures getCreditScore = creditScore
     */
    @Override
    public int getCreditScore()
    {
        return creditScore;
    }


    /**
     *
     * @return a string representation of the customer including loan details
     * @requires this != null
     * @ensures [toString is a string representation of the customer]
     */
    @Override
    public String getName(){
        return name;
    }
    @Override
    public boolean appliedForLoan()
    {
        return loan != null;
    }


}
