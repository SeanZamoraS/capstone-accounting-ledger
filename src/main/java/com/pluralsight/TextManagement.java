package com.pluralsight;
import java.util.*;
import java.io.*;
import java.time.*;
import static com.pluralsight.MenuLogic.input;

public class TextManagement //just a class for organization purposes
{
    //static Scanner enterScanner = new Scanner(System.in);

    public static void cleanCSV() //tested and seems to work as intended, be careful though
    {
        File originalFile = new File("transactions.csv");
        File cleanedFile = new File("transactionsTEMP.csv");

        try
        {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            FileOutputStream fileWriter = new FileOutputStream("transactionsTEMP.csv", false);
            PrintStream printer = new PrintStream(fileWriter);

            String line = " ";
            line = bufferedReader.readLine();

            while(line != null)
            {
                if (!line.isBlank())
                {
                    printer.println(line);
                }
                line = bufferedReader.readLine();
            }
            fileReader.close();
            bufferedReader.close();
            fileWriter.close();
            printer.close();
        }

        catch (Exception e)
        {
            System.out.println("Something went wrong cleaning the file.");
        }

        originalFile.delete();
        boolean success = cleanedFile.renameTo(originalFile);

        if (success == false)
        {
            System.out.println("File unable to be renamed during file cleaning. Look for transactionsTEMP.csv");
        }
    }

    public static double receiveValidDouble() //ripped from theater-reservations project
    {
        while (true)
        {
            try
            {
                double amountDouble = input.nextDouble();
                return amountDouble;
            }

            catch (Exception e)
            {
                System.out.println("\nPlease enter a valid number(no letters or symbols aside from '.'):\n");
                input.nextLine();
            }
        }
    }

    public static String receiveValidString(String word)
    {
        String userInput = input.nextLine();

        while (true)
        {
            if ((userInput.isEmpty() || userInput.isBlank()))
            {
                System.out.printf("\nPlease enter a %s.\n\n", word);
                userInput = input.nextLine();
            }

            else
            {
                return userInput;
            }
        }
    }

    public static void pressEnterToContinue()
    {
        System.out.println("\nPress enter to continue...");
        input.nextLine();
    }
}