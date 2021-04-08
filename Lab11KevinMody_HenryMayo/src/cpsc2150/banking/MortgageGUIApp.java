package cpsc2150.banking;
//Author: Kevin Mody and Henry Mayo
//Class: CPSC 2151
//Sec: 006
//Date: 04/06/2021
import cpsc2150.banking.views.*;
import cpsc2150.banking.controllers.*;


public class MortgageGUIApp {
    public static void main(String [] args)
    {
        IMortgageView view = new MortgageGUIView();
        IMortgageController controller = new MortgageGUIController(view);
        view.setController(controller);
        controller.submitApplication();
    }
}
