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
}