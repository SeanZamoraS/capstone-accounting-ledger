package com.pluralsight;


import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;
import java.time.*;

public class LedgerItem
{
    private String id;
    private LocalDateTime currentTime;
    private String date;
    private String time;
    private String description;
    private String vendor;
    private double amount;

    public LedgerItem(String id, LocalDateTime currentTime, String description,
                      String vendor, double amount) //constructor from user
    {
        this.id = id;
        this.currentTime = currentTime;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;

        this.assignDateTimeFromLDTObject();
    }

    public LedgerItem(String id, String date, String time, String description,
                        String vendor, double amount) //constructor from file
    {
        this.id = id;
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;

        this.assignLDTObjectFromString();
    }

    //getters, setters
    public String getID() {return this.id;}
    public LocalDateTime getCurrentTime() {return this.currentTime;}
    public String getDate() {return this.date;}
    public String getTime() {return this.time;}
    public String getDescription() {return this.description;}
    public String getVendor() {return this.vendor;}
    public double getAmount() {return this.amount;}

    public void setID(String id) {this.id =id;}
    public void setCurrentTime(LocalDateTime currentTime) {this.currentTime = currentTime;}
    public void setDate(String date) {this.date = date;}
    public void setTime(String time) {this.time = time;}
    public void setDescription(String description) {this.description = description;}
    public void setVendor(String vendor) {this.vendor = vendor;}
    public void setAmount(double amount) {this.amount = amount;}

    //methods:

    public static LedgerManagement createLedger()
    {

        ArrayList<LedgerItem> currentLedger = new ArrayList<>();

        try
        {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();
            line = bufferedReader.readLine();

            while(line != null)
            {
                //line = bufferedReader.readLine();
                String[] splitLine = line.split("\\|");

                String id = splitLine[0];
                String date = splitLine[1];
                String time = splitLine[2];
                String description = splitLine[3];
                String vendor = splitLine[4];
                double amount = Double.parseDouble(splitLine[5]);

                LedgerItem currentItem = new LedgerItem(id, date, time, description, vendor, amount);
                currentLedger.add(currentItem);

                line = bufferedReader.readLine(); //does this go here or at the top?
            }
            bufferedReader.close();
            fileReader.close();
        }


        catch (Exception ex)
        {
            System.out.println("Something went wrong in getting the ledger.");
            return null;
        }
        LedgerManagement currentLedgerComplete = new LedgerManagement(currentLedger, false);
        return currentLedgerComplete;
    }

    public static ArrayList<LedgerItem> createLedgerArrayList()
    {

        ArrayList<LedgerItem> currentLedger = new ArrayList<>();

        try
        {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();
            line = bufferedReader.readLine();

            while(line != null)
            {
                //line = bufferedReader.readLine();
                String[] splitLine = line.split("\\|");

                String id = splitLine[0];
                String date = splitLine[1];
                String time = splitLine[2];
                String description = splitLine[3];
                String vendor = splitLine[4];
                double amount = Double.parseDouble(splitLine[5]);

                LedgerItem currentItem = new LedgerItem(id, date, time, description, vendor, amount);
                currentLedger.add(currentItem);

                line = bufferedReader.readLine(); //does this go here or at the top?
            }
            bufferedReader.close();
            fileReader.close();
        }

        catch (Exception ex)
        {
            System.out.println("Something went wrong in getting the ledger.");
            return null;
        }
        return currentLedger;
    }

    public static LedgerManagement createLedgerObjectFromFile()
    {
        return null;
    }

    public void displayItem()
    {
        String id = this.id;
        String date = this.date;
        String time = this.time;
        String description = this.description;
        String vendor = this.vendor;
        double amount = this.amount;

        System.out.printf("\n%s|%s|%s|%s|%s|%.2f\n", id, date, time, description, vendor, amount);
    }

    private void assignDateTimeFromLDTObject()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime itemTime = this.getCurrentTime();

        String sItemTime = itemTime.format(formatter);

        String[] dateAndTime = sItemTime.split(" ");

        this.date = dateAndTime[0];
        this.time = dateAndTime[1];
    }

    private void assignLDTObjectFromString()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String completeTime = this.getDate() + " " + this.getTime();
        LocalDateTime time = LocalDateTime.parse(completeTime, formatter);

        this.currentTime = time;
    }

}
