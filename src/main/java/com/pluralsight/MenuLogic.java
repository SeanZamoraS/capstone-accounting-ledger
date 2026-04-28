package com.pluralsight;

import java.util.*;
import java.io.*;
import java.time.*;

import static com.pluralsight.LedgerItem.createLedger;

public class MenuLogic
{
    static Scanner input = new Scanner(System.in);

    //static ArrayList<LedgerItem> startLedger = new ArrayList<>();
    static LedgerManagement currentLedger = new LedgerManagement(createLedger().getCompleteLedger(), false);

    public static void main()
    {
        //add creating arraylist function to update
        homeScreen();
    }

    public static void homeScreen()
    { //using recursion for menus for now for MVP purposes, change to while later(?)
        System.out.println("""
                LEDGER MANAGEMENT APP:
                
                Please enter one of the following options 
                (1, 2, 3, or 0):
                
                1) Add a purchase to the ledger
                2) Add a payment/invoice to the ledger
                3) Check ledger
                
                0) Exit program\n\n""");

        String userInput = input.nextLine();

        switch (userInput)
        {
            case "1":
                addToLedgerScreen(1);
                break;

            case "2":
                addToLedgerScreen(2);
                break;

            case "3":
                ledgerSearchScreen();
                break;

            case "0":
                System.out.println("\nThank you for using the ledger management app. Goodbye!");
                System.exit(0);
                break;

            default:
                System.out.println("Please select a valid option.");
                homeScreen();
        }

    }

    public static void addToLedgerScreen(int choice)
    {
        System.out.println("\nLet's add your transaction to the ledger...");

        double amount = 0.0;
        switch (choice)
        {
            case 1:
                System.out.println("How much did you spend?\n");
                //double input exception handler goes here
                amount = input.nextDouble();
                break;

            case 2:
                System.out.println("How much did were you paid?\n");
                //double input exception handler goes here
                amount = input.nextDouble();
                break;

            default:
                System.out.println("This is not supposed to happen. This is a bug.");
                break;
        }

        System.out.println("\nWhat vendor/person did you have the transaction with?\n");
        input.nextLine();
        String vendor = input.nextLine();

        System.out.println("\nPlease enter a description for the transaction: \n");
        String description = input.nextLine();

        LocalDateTime currentTime = LocalDateTime.now();

        //soutf("\nCreating an entry for $%.2f from $s with a description of: $s", amount, description);

        //I want the date to be current in case user spends time lingering on this option, log
        //the time of confirmation, will have to add a loop for multiple additions later, currentItem
        //will be in scope due to recursion(?)

        System.out.println("""
                \nWould you like to confirm or retry entry?
                
                Please enter 1 or 2.
                
                1) Confirm entry
                2) Make a correction\n""");

        String confirm = input.nextLine();

        switch (confirm)
        {
            case "1":
                //LocalDateTime currentTime = LocalDateTime.now();
                //LedgerItem currentItem = new LedgerItem(currentTime, description, vendor, amount);
                //currentItem.setDateTime();
                //startLedger.add(currentItem); //currentLedger.getLedger.add(currentItem);
                //shall i write to .csv or save until before exiting?

                System.out.println("Successfully added entry to ledger. Returning to home menu.");
                homeScreen();
                break;

            case "2":
                System.out.println("Trying again...");
                addToLedgerScreen(choice);
                break;

            default:
                //when adding loop you can ask for valid input but not necessary for MVP
                System.out.println("Trying again...");
                addToLedgerScreen(choice);
                break;
        }

    }

    public static void ledgerSearchScreen()
    {
        System.out.println("""
                Which part of the ledger would you like to view?
                
                Please enter one of the following options
                (1, 2, 3, 4, 5, 9, or 0):
                
                1) Display entire ledger
                2) Transactions: Month to Today's Date
                3) Transactions: Year to Today's Date
                4) Transactions: Last Month
                5) Transactions: Last Year
                
                9) Search by vendor
                
                0) Return to home screen\n""");

        String userChoice = input.nextLine();

        switch(userChoice)
        {
            case "1":
                LedgerManagement currentLedger = new LedgerManagement(createLedger().getCompleteLedger(), false);
                System.out.println();

                currentLedger.displayCompleteLedger();
                System.out.println("\nReturning to selections...\n");
                ledgerSearchScreen();
                //press enter to continue would be a good quality of life feature
                break;

            case "2":
                //currentLedger.displayTimePurchase(2);
                ledgerSearchScreen();
                //""
                break;

            case "3":
                //currentLedger.displayTimePurchase(3);
                ledgerSearchScreen();
                //""
                break;

            case "4":
                //currentLedger.displayTimePurchase(4);
                ledgerSearchScreen();
                //""
                break;

            case "5":
                //currentLedger.displayTimePurchase(5);
                ledgerSearchScreen();
                //""
                break;

            case "9":
                System.out.println("\nWhat is the name of the vendor you would like to search for?\n");

                String userVendor = input.nextLine();
                //displayVendorSearch(userVendor);
                //""
                ledgerSearchScreen();
                break;

            case "0":
                homeScreen();
                break;
        }
    }
}
