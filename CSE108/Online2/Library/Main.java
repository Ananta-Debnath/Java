import java.util.Scanner;


public class Main
{
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < n; i++)
        {
            System.out.print("Branch " + (i+1) + ":\nBook Number: ");
            int bookNum = sc.nextInt();
            if (bookNum > 20)
            {
                System.out.println("Cannot have more than 20 books!");
                bookNum = 20;
            }
            LibraryBranch branch = LibraryBranch.addBranch(bookNum);

            if (branch != null)
            {
                int bookId;
                String bookTitle;
                boolean availability;
                for (int j = 0; j < bookNum; j++)
                {
                    System.out.print("Book ID: ");
                    bookId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Book Title: ");
                    bookTitle = sc.nextLine();

                    System.out.print("Available: ");
                    availability = sc.nextBoolean();

                    branch.addBook(bookId, bookTitle, availability);
                }
            }
        }

        
        while (true)
        { 
            System.out.println("Menu:");
            System.out.println("1. Borrow a Book");
            System.out.println("2. Return a Book");
            System.out.println("3. Display All Branch Info");
            System.out.println("4. Exit");

            int branchID, bookID;
            switch (sc.nextInt()) {
                case 1 -> {
                    System.out.print("Branch ID: ");
                    branchID = sc.nextInt();

                    System.out.print("Book ID: ");
                    bookID = sc.nextInt();

                    LibraryBranch.borrowBook(branchID, bookID);
                }

                case 2 -> {
                    System.out.print("Branch ID: ");
                    branchID = sc.nextInt();

                    System.out.print("Book ID: ");
                    bookID = sc.nextInt();

                    LibraryBranch.returnBook(branchID, bookID);
                }

                case 3 -> LibraryBranch.displayLibrary();
                    
                case 4 -> {
                    sc.close();
                    return;
                }

                default -> System.out.println("Invalid input!");
            }
        }
    }
}


/*
Branch a = new Branch();
Branch b = a;
a = new Branch();


*/