package com.pluralsight;

import java.util.*;
import java.io.*;
import java.time.*;

//import static com.pluralsight.LedgerItem.createLedger;

public class MenuLogic
{
    static Scanner input = new Scanner(System.in);

    static LedgerManagement currentLedger = new LedgerManagement(LedgerItem.createLedger().getCompleteLedger(), false);

    public static void main()
    {
        homeScreen();
    }

    public static void homeScreen()
    { //using recursion for menus for now for MVP purposes, change to while later(?)
        System.out.println("""
                
                LEDGER MANAGEMENT APP HOME:
                
                Please enter one of the following options 
                (1, 2, 3, or 0):
                
                1) Add a purchase to the ledger
                2) Add a payment/invoice to the ledger
                3) Check ledger
                
                0) Exit program\n""");

        String userInput = input.nextLine().strip();

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
                System.out.println("How much did you spend? (positive number)\n");
                amount = TextManagement.receiveValidDouble();
                break;

            case 2:
                System.out.println("How much were you paid?\n");
                amount = TextManagement.receiveValidDouble();
                break;

            default:
                System.out.println("This is not supposed to happen. This is a bug.");
                break;
        }

        System.out.println("\nWhat vendor/person did you have the transaction with?\n");
        input.nextLine(); //to eat line after double?
        String vendor = TextManagement.receiveValidString("vendor");

        System.out.println("\nPlease enter a description for the transaction: \n");
        String description = TextManagement.receiveValidString("description");

        System.out.printf("\nCreating an entry for $%.2f from %s with a description of: %s\n", amount, vendor, description);


        System.out.println("""
                \nWould you like to confirm or retry entry?
                
                Please enter 1 or 2.
                
                1) Confirm entry
                2) Make a correction\n""");

        String confirm = input.nextLine();

        switch (confirm)
        {
            case "1":
                try
                {
                    currentLedger.writeToLedger(choice, description, vendor, amount);

                    System.out.println("Successfully added entry to ledger. Returning to home menu.");
                    TextManagement.cleanCSV();
                    homeScreen();
                    break;
                }
                catch (Exception e)
                {
                    addToLedgerScreen(choice);
                    break;
                }

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
                
                6) Transactions: All expenses
                7) Transactions: All income
                
                9) Search by vendor
                
                0) Return to home screen\n""");

        String userChoice = input.nextLine();

        switch(userChoice)
        {
            case "1":
                LedgerManagement currentLedger = new LedgerManagement(LedgerItem.createLedger().getCompleteLedger(), false);
                System.out.println();

                currentLedger.displayCompleteLedger();
                System.out.println("\nReturning to selections...\n");
                ledgerSearchScreen();
                //press enter to continue would be a good quality of life feature
                break;

            case "2":
                LedgerManagement monthToDay = new LedgerManagement(LedgerItem.createLedger().getCompleteLedger(), false);
                System.out.println();

                monthToDay.displayTimePurchase("2");
                System.out.println("\nReturning to selections...\n");
                ledgerSearchScreen();
                //""
                break;

            case "3":
                LedgerManagement yearToDay = new LedgerManagement(LedgerItem.createLedger().getCompleteLedger(), false);
                System.out.println();

                yearToDay.displayTimePurchase("3");
                System.out.println("\nReturning to selections...\n");
                ledgerSearchScreen();
                //""
                break;

            case "4":
                LedgerManagement lastMonth = new LedgerManagement(LedgerItem.createLedger().getCompleteLedger(), false);
                System.out.println();

                lastMonth.displayTimePurchase("4");
                System.out.println("\nReturning to selections...\n");
                ledgerSearchScreen();
                //""
                break;

            case "5":
                LedgerManagement lastYear = new LedgerManagement(LedgerItem.createLedger().getCompleteLedger(), false);
                System.out.println();

                lastYear.displayTimePurchase("5");
                System.out.println("\nReturning to selections...\n");
                ledgerSearchScreen();
                //""
                break;

            case "6":
                LedgerManagement expenses = new LedgerManagement(LedgerItem.createLedger().getCompleteLedger(), false);
                System.out.println();

                expenses.displayMoneyInOrOut(1);
                System.out.println("\nReturning to selections...\n");
                ledgerSearchScreen();
                //""
                break;

            case "7":
                LedgerManagement income = new LedgerManagement(LedgerItem.createLedger().getCompleteLedger(), false);
                System.out.println();

                income.displayMoneyInOrOut(2);
                System.out.println("\nReturning to selections...\n");
                ledgerSearchScreen();
                //""
                break;

            case "9":
                System.out.println("\nWhat is the name of the vendor you would like to search for?\n");
                LedgerManagement searchLedger = new LedgerManagement(LedgerItem.createLedger().getCompleteLedger(), false);

                String userVendor = input.nextLine();
                System.out.println("""
                        
                        Found these entries:""");
                searchLedger.displayVendorSearch(userVendor);
                System.out.println("\nReturning to selections.\n");
                //""
                ledgerSearchScreen();
                break;

            case "0":
                homeScreen();
                break;

            default:
                ledgerSearchScreen();
                break;
        }
    }

    public static void selectAFile()
    {
        System.out.println("""
                Starting up the financial ledger program...
                
                In order to proceed, select an option to create a ledger.
                
                1) Load from file
                2) Create a new blank ledger
                3) Use the example template ledger
                
                """);

        String userChoice = input.nextLine();

        switch (userChoice)
        {
            case "1":
                break;

            case "2":
                break;

            case "3":
                break;

            default:
                break;
        }

    }
}
