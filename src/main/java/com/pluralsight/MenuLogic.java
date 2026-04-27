package com.pluralsight;

import java.util.*;
import java.io.*;
import java.time.*;

public class MenuLogic
{
    static Scanner input = new Scanner(System.in);
    //static ArrayList<LedgerItem> currentLedger = new ArrayList<>();

    public static void main()
    {
        //add creating arraylist function to update
        homeScreen();
    }

    public static void homeScreen()
    { //using recursion for menus for now for MVP purposes, change to while later(?)
        System.out.println("""
                LEDGER MANAGEMENT APP:
                
                Please enter one of the following options (1, 2, 3, or 0: 
                
                1) Add a purchase to the ledger
                2) Add a payment/invoice to the ledger
                3) Check ledger
                
                0) Exit program\n\n""");

        String userInput = input.nextLine();

        switch (userInput)
        {
            case "1":
                //addToLedgerScreen(1)
                break;

            case "2":
                //addToLedgerScreen(2)
                break;

            case "3":
                //ledgerSearchScreen()
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
                System.out.println("How much did you spend?");
                //double input exception handler goes here
                amount = input.nextDouble();
                break;

            case 2:
                System.out.println("How much did were you paid?");
                //double input exception handler goes here
                amount = input.nextDouble();
                break;

            default:
                System.out.println("This is not supposed to happen. This is a bug.");
                break;
        }

        System.out.println("\nWhat vendor/person did you have the transaction with?\n");
        String vendor = input.nextLine();

        System.out.println("\nPlease enter a description for the transaction: \n");
        String description = input.nextLine();

        LocalDateTime currentTime = LocalDateTime.now();

        //LedgerItem currentItem = new LedgerItem(currentTime, description, vendor, amount);
        //currentItem.setDateTime();


    }
}
