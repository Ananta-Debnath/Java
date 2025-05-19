import java.util.Scanner;


public class Main
{
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
            System.out.println(n);
        for (int i = 0; i < n; i++) LibraryBranch.addBranch();

        Scanner sc = new Scanner(System.in);
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