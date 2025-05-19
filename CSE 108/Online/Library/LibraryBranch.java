import java.util.Scanner;

public class LibraryBranch
{
    private static int branchNum = 0;
    private static LibraryBranch branches[] = new LibraryBranch[10];

    private int id;
    private int bookNum;
    private Book books[] = new Book[20];

    LibraryBranch()
    {
        id = branchNum;

        Scanner sc = new Scanner(System.in);

        System.out.print("Branch " + id + ":\nBook Number: ");
        bookNum = sc.nextInt();
        if (bookNum > 20)
        {
            System.out.println("Cannot have more than 20 books!");
            bookNum = 20;
        }

        int bookId;
        String bookTitle;
        boolean availability;
        for (int i = 0; i < bookNum; i++)
        {
            System.out.print("Book ID: ");
            bookId = sc.nextInt();
            sc.nextLine();

            System.out.print("Book Title: ");
            bookTitle = sc.nextLine();

            System.out.print("Available: ");
            availability = sc.nextBoolean();

            books[i] = new Book(bookId, bookTitle, availability);
        }
    }

    public int getBookNum()
    {
        return bookNum;
    }

    public Book getBook(int idx)
    {
        return books[idx];
    }

    public static void addBranch()
    {
        if (branchNum < 10) branches[branchNum++] = new LibraryBranch();

        else System.out.println("Branch limit reached!");
    }

    public static void borrowBook(int branchID, int bookID)
    {
        if (branchID > branchNum)
        {
            System.out.println("Branch doesn't exist!");
            return;
        }

        for (int i = 0; i < branches[branchID-1].getBookNum(); i++)
        {
            if (branches[branchID-1].getBook(i).getID() == bookID)
            {
                if (branches[branchID-1].getBook(i).getAvailability())
                {
                    branches[branchID-1].getBook(i).setAvailability(false);
                    System.out.println("Book borrowed.");
                }

                else System.out.println("Book not available.");

                return;
            }
        }

        System.out.println("Book not found!");
    }

    public static void returnBook(int branchID, int bookID)
    {
        if (branchID > branchNum)
        {
            System.out.println("Branch doesn't exist!");
            return;
        }

        for (int i = 0; i < branches[branchID-1].getBookNum(); i++)
        {
            if (branches[branchID-1].getBook(i).getID() == bookID)
            {
                if (branches[branchID-1].getBook(i).getAvailability())
                {
                    System.out.println("Book already available.");
                }

                else
                {
                    branches[branchID-1].getBook(i).setAvailability(true);
                    System.out.println("Book marked as available.");
                }

                return;
            }
        }

        System.out.println("Book not found!");
    }

    public void display()
    {
        System.out.println("Branch ID: " + id);

        if (bookNum == 0) System.out.println("No books available.");

        else
        {
            for (int i = 0; i < bookNum; i++)
            {
                System.out.println(books[i].toString());
            }
        }
    }

    public static void displayLibrary()
    {
        for (int i = 0; i < branchNum; i++) branches[i].display();
    }
}