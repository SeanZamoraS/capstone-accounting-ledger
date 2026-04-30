package com.pluralsight;

import java.util.*;
import java.io.*;
import java.time.*;
import java.util.stream.Collectors;

//import static com.pluralsight.LedgerItem.createLedger;
//import static com.pluralsight.LedgerItem.createLedgerArrayList;

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
                amount = Math.abs(amount) * -1;
                currentItem = new LedgerItem(this.returnNextID(), itemTime, description, vendor, amount);
            }
            else if (choice == 2)
            {
                amount = Math.abs(amount) * 1;
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

    public void displayMoneyInOrOut(int choice)
    {
        this.completeLedger = LedgerItem.createLedgerArrayList();

        ArrayList<LedgerItem> searchedItems = null;

        if (choice == 1)
        {
            searchedItems = completeLedger.stream()
                    .filter(currentItem -> currentItem.getAmount() <= 0)
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        else if (choice == 2)
        {
            searchedItems = completeLedger.stream()
                    .filter(currentItem -> currentItem.getAmount() >= 0)
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        this.modifiedLedger = searchedItems;

        modifiedLedger.stream()
                .forEach(currentItem -> currentItem.displayItem());

    }

    public void displayVendorSearch(String vendor)
    {
       this.completeLedger = LedgerItem.createLedgerArrayList(); //update ledger

        //trying a stream instead of a loop because a loop here is a bit annoying
        ArrayList<LedgerItem> searchedItems = completeLedger.stream()
                .filter(currentItem -> currentItem.getVendor().equalsIgnoreCase(vendor))
                .collect(Collectors.toCollection(ArrayList::new));

        this.modifiedLedger = searchedItems; //not necessary due to stream but here just in case

        if (this.modifiedLedger.isEmpty())
        {
            System.out.println("\nNo results found.");
        }
        //trying another stream just for fun
        searchedItems.stream()
                .forEach(displayItem -> displayItem.displayItem());
        //just informed ArrayList have .forEach already :(

    }

    public void displayTimePurchase(String userChoice)
    {
        this.modifiedLedger = calculateRequestedTimeFrame(userChoice);

        if (this.modifiedLedger.isEmpty())
        {
            System.out.println("\nNo results found.");
        }

        modifiedLedger.stream()
                .forEach(displayItem -> displayItem.displayItem());


    }

    private static ArrayList<LedgerItem> calculateRequestedTimeFrame(String userChoice)
    {
        ArrayList<LedgerItem> currentLedger = new ArrayList<>();
        currentLedger = LedgerItem.createLedgerArrayList();

        switch (userChoice)
        {
            case "2": //month to date
                LocalDateTime startofMonth = LocalDate.now().withDayOfMonth(1).atStartOfDay();

                ArrayList<LedgerItem> mtdItems = currentLedger.stream()
                        .filter(item -> item.getCurrentTime().isAfter(startofMonth))
                        .collect(Collectors.toCollection(ArrayList::new));

                return mtdItems;
                //break;

            case "3": //year to date
                LocalDateTime startOfYear = LocalDate.now().withDayOfYear(1).atStartOfDay();

                ArrayList<LedgerItem> mtyItems = currentLedger.stream()
                        .filter(item -> item.getCurrentTime().isAfter(startOfYear))
                        .collect(Collectors.toCollection(ArrayList::new));

                return mtyItems;
                //break;

            case "4": //all last month
                LocalDateTime startOfLastMonth = LocalDate.now().minusMonths(1)
                        .withDayOfMonth(1).atStartOfDay();
                LocalDateTime endOfLastMonth = LocalDate.now().withDayOfMonth(1)
                        .minusDays(1).atTime(LocalTime.MAX);

                ArrayList<LedgerItem> lastMonthItems = currentLedger.stream()
                        .filter(item -> item.getCurrentTime().isAfter(startOfLastMonth)
                                && item.getCurrentTime().isBefore(endOfLastMonth))
                        .collect(Collectors.toCollection(ArrayList::new));

                return lastMonthItems;
                //break;

            case "5": //all last year
                LocalDateTime startOfLastYear = LocalDate.now().minusYears(1)
                        .withDayOfMonth(1).atStartOfDay();
                LocalDateTime endOfLastYear = LocalDate.now().withDayOfYear(1)
                        .minusDays(1).atTime(LocalTime.MAX);

                ArrayList<LedgerItem> lastYearItems = currentLedger.stream()
                        .filter(item -> item.getCurrentTime().isAfter(startOfLastYear)
                                && item.getCurrentTime().isBefore(endOfLastYear))
                        .collect(Collectors.toCollection(ArrayList::new));

                return lastYearItems;
                //break;

            default:
                System.out.println("This is a bug.");
                return null;
                //break;
        }
    }
}