package cpsc2150.banking.controllers;
//Author: Kevin Mody and Henry Mayo
//Class: CPSC 2151
//Sec: 006
//Date: 04/06/2021
/**
 * This interface is the Controller that partners with IMortgageView
 *
 * Defines: 
 *		View: The IMortgageView
 * Initialization ensures: View != NULL
 */

public interface IMortgageController {

    /**
     * This will handle the processing of a mortgage application
     * 
     * @pre: none
     * @post: none
     */
    void submitApplication();

}