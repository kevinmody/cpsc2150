package cpsc2150.banking.models;
//Author: Kevin Mody and Henry Mayo
//Class: CPSC 2151
//Sec: 006
//Date: 02/22/2021

/**
 * @invariants cost >= 0 and dp >= 0 and years >= 1
 * Correspondences: Payment = cost
 *                  Customer = customer
 *
 *
 */
public class Mortgage extends AbsMortgage implements IMortgage {
    private final double cost, dp;
    private final int y;
    private final ICustomer c;
    //months in year constant
    public static final int miy = 12;

    /**
     *
     * @pre cost >= 0 and dp >= 0 and years >= 1
     * @post
     * @param hc is the home cost
     * @param down is the down payment
     * @param years is how long the loan is for
     * @param customer is the client
     */
    Mortgage(double hc, double down, int years, ICustomer customer){
        cost = hc;
        dp = down;
        y = years;
        c = customer;
    }

    @Override
    public double getPrincipal() {
        return cost-dp;
    }

    @Override
    public int getYears() {
        return y;
    }

    @Override
    public double getPayment() {
        //monthly payment
        double num = (getRate()/miy)*getPrincipal();
        double den = 1-Math.pow(1+getRate()/miy,(-miy*y));
        return num/den;
    }

    @Override
    public double getRate() {
        //base
        double rate = BASERATE;

        //duration
        if(y < MAX_YEARS){
            rate+=GOODRATEADD;
        }
        else{
            rate+=NORMALRATEADD;
        }

        //down payment
        if(dp/cost < PREFERRED_PERCENT_DOWN){
            rate+=GOODRATEADD;
        }

        //credit score
        if(c.getCreditScore() < BADCREDIT){
            rate+=VERYBADRATEADD;
        }
        if(c.getCreditScore() < FAIRCREDIT && c.getCreditScore() >= BADCREDIT){
            rate+=BADRATEADD;
        }
        if(c.getCreditScore() < GOODCREDIT && c.getCreditScore() >= FAIRCREDIT){
            rate+=NORMALRATEADD;
        }
        if(c.getCreditScore() < GREATCREDIT && c.getCreditScore() >= GOODCREDIT){
            rate+=GOODRATEADD;
        }

        return rate;
    }

    @Override
    public boolean loanApproved() {
        double debt = c.getMonthlyDebtPayments()+getPayment();
        double income = c.getIncome()/miy;
        if(getRate() < RATETOOHIGH && (dp/cost) >= MIN_PERCENT_DOWN && debt/income <= DTOITOOHIGH){
            return true;
        }
        else{
            return false;
        }
    }
}
