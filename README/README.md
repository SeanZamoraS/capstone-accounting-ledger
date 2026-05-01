# **LEDGER MANAGEMENT APP CAPSTONE**

### About this project:

The creation of this app was initiated by following the requirements
of the accounting ledger capstone project for the Year Up United software track.
It is a very simple app intended to run in a terminal (although I have not made it usable yet outside an IDE terminal).

It can be used to display information in a financial ledger and add information to that ledger.
I've included some pre-generated information in the ledger to show how the application works. 

I used Claude Sonnet AI by Anthropic to generate some of the entries in the example ledger. 

### How to use ledger management app:

At present, in order to run this app it is necessary to pull the project from GitHub
and run it from an IDE. 

Once that has been completed and the app is running, there are a series of prompts that can
be used, each one will give directions to type a number in the terminal in order to select
an option. The application is designed such that if it does not receive a number it was expecting
that it will prompt the user again. Each number should be typed in alone with no letters, spaces or
special characters with the exception of entering in the amount of money used in the transaction, where a
period (".") character is allowed to represent number of cents. 

On the home screen there are 4 options. Entering 1 or 2 will begin the prompt to enter
information to the ledger, with 1 being for recording purchases and 2 being for recording
being paid. Once all the questions in that prompt are answered and confirmed, the information should be available
on the ledger. 

In addition, entering 0 will end the program. 

Entering 3 on the home screen will bring up a new menu that has options to display the whole ledger
or parts of the ledger. The app can display certain parts of the ledger, such as entries from 
last year or last month or even display based on whether the transaction was an expense or a payment. 
There is also a search by vendor option on this screen. 

### Design notes:
In no particular order, here are some of the quirks of the application: 

- Searching by vendor matches exact names only at this point.
- Time stamps are based on the time a transaction is entered into the ledger, not on the time of purchase. I feel this would be useful accountability in creating transactions on the ledger. In the future, I would also think about adding a purchase date user input though, I just didn't think about it at the time.  
- In order to resolve line spacing issues in reading from a file, each time the ledger is modified, the ledger file is destroyed and rewritten without spaces. (During development, I would often open the file and accidentally put spaces in. I also had difficulty with coming up with a way to not leave blank space at the end of the file when writing to it.) 
- I figured out how to use streams halfway through this project so there are some included. 
- I left many original comments throughout the code to show areas where I am uncertain or working through an issue.