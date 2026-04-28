package com.pluralsight;

import java.util.*;
import java.io.*;
import java.time.*;

public class LedgerManagement
{
    private ArrayList<LedgerItem> completeLedger;
    private ArrayList<LedgerItem> modifiedLedger;//for use in methods?
    private boolean isModified;

    public LedgerManagement(ArrayList<LedgerItem> ledger, boolean isModified) //constructor, boolean to avoid same signature
    {
        if (isModified == false)
        {
            this.completeLedger = ledger;
        }
        else if (isModified == true)
        {
            this.modifiedLedger = ledger;
        }
    }


    //getter(used) + setter(should be unused?)
    public ArrayList<LedgerItem> getCompleteLedger() {return this.completeLedger;}
    public ArrayList<LedgerItem> getModifiedLedger() {return this.modifiedLedger;}
    public void setCompleteLedger(ArrayList<LedgerItem>
                                          completeLedger) {this.completeLedger = completeLedger;}

    //methods
    public void displayCompleteLedger()
    {
        for(int i = 0; i < this.getCompleteLedger().size(); i++)
        {
            completeLedger.get(i).displayItem();
        }
    }

    public String returnNextID()
    {
        int size = completeLedger.size() - 1;
        String lastID = completeLedger.get(size).getID();

        int iLastID = Integer.parseInt(lastID) + 1; //I don't know if you need the +1/-1 technically but it makes me feel better
        return Integer.toString(iLastID);
    }

    public void writeToLedger(int choice, String description, String vendor, double amount)
    {
        try
        {
            FileOutputStream fileWriter = new FileOutputStream("transactions.csv", true);
            PrintStream printer = new PrintStream(fileWriter);

            LocalDateTime itemTime = LocalDateTime.now();


            LedgerItem currentItem = null;
            if (choice == 1)
            {
                amount = amount * -1;
                currentItem = new LedgerItem(this.returnNextID(), itemTime, description, vendor, amount);
            }
            else if (choice == 2)
            {
                currentItem = new LedgerItem(this.returnNextID(), itemTime, description, vendor, amount);
            }
            this.getCompleteLedger().add(currentItem); //necessary or not? updating .csv + createLedger() alternatively

            printer.printf("\n%s|%s|%s|%s|%s|%.2f", currentItem.getID(), currentItem.getDate(),
                    currentItem.getTime(), currentItem.getDescription(), currentItem.getVendor(),
                    currentItem.getAmount());

            int element = this.getCompleteLedger().size();
            System.out.println("\nHere is the new entry:");
            this.getCompleteLedger().get(element - 1).displayItem();;
        }

        catch (Exception e)
        {
            System.out.println("Something went wrong. Try again.");
            e.printStackTrace();
        }

    }
}