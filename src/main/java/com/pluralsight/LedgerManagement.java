package com.pluralsight;

import java.util.*;
import java.io.*;
import java.time.*;

public class LedgerManagement
{
    private ArrayList<LedgerItem> completeLedger;
    private ArrayList<LedgerItem> modifiedLedger; //for use in methods?

    public LedgerManagement(ArrayList<LedgerItem> completeLedger) //constructor
    {
        this.completeLedger = completeLedger;
    }

    //getter(used) + setter(should be unused?)
    public ArrayList<LedgerItem> getCompleteLedger() {return this.completeLedger;}
    public void setCompleteLedger(ArrayList<LedgerItem> completeLedger) {this.completeLedger = completeLedger;}
}