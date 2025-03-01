package Library.Model;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<Patron> patrons;
    
    public Library() {
        books = new ArrayList<>();
        patrons = new ArrayList<>();
    }
    
    public void addBook(Book book) {
        for (Book existingBook : books) {
            if (existingBook.getIsbn().equals(book.getIsbn())) {
                existingBook.addCopies(book.getTotalCopies());
                System.out.println("Added copies to existing book.");
                return;
            }
        }
        books.add(book);
    }
    
    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        
        System.out.println("\n================= Books in Library =================");
        for (Book book : books) {
            System.out.println("\n" + book);
            System.out.println("---------------------------------------------------");
        }
    }
    
    public void removeBook(String isbn, int copies) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                if (book.removeCopies(copies)) {
                    System.out.println("Removed " + copies + " copies of the book.");
                    
                    if (book.getTotalCopies() == 0) {
                        books.remove(book);
                        System.out.println("Book removed from library.");
                    }
                }
                return;
            }
        }
        System.out.println("Book with ISBN " + isbn + " not found.");
    }
    
    public void registerPatron(Patron patron) {
        for (Patron existingPatron : patrons) {
            if (existingPatron.getId().equals(patron.getId())) {
                System.out.println("Patron with ID " + patron.getId() + " already exists.");
                return;
            }
        }
        patrons.add(patron);
    }
    
    public void displayPatrons() {
        if (patrons.isEmpty()) {
            System.out.println("No patrons registered.");
            return;
        }
        
        System.out.println("\n=============== Registered Patrons ===============");
        for (Patron patron : patrons) {
            System.out.println("\n" + patron);
            
            List<String> borrowedIsbn = patron.getBorrowedBooks();
            if (!borrowedIsbn.isEmpty()) {
                System.out.println("\nBorrowed Books:");
                for (String isbn : borrowedIsbn) {
                    Book book = findBookByIsbn(isbn);
                    if (book != null) {
                        System.out.println("- " + book.getTitle() + " by " + book.getAuthor());
                    }
                }
            }
            System.out.println("---------------------------------------------------");
        }
    }
    
    public void borrowBook(String patronId, String isbn) {
        Patron patron = findPatronById(patronId);
        if (patron == null) {
            System.out.println("Patron with ID " + patronId + " not found.");
            return;
        }
        
        Book book = findBookByIsbn(isbn);
        if (book == null) {
            System.out.println("Book with ISBN " + isbn + " not found.");
            return;
        }
        
        if (!book.isAvailable()) {
            System.out.println("No copies of this book are currently available.");
            return;
        }
        
        if (patron.hasBorrowedBook(isbn)) {
            System.out.println("Patron already has a copy of this book.");
            return;
        }
        
        book.borrowCopy();
        patron.borrowBook(isbn);
        System.out.println("Book '" + book.getTitle() + "' has been borrowed by " + patron.getName() + ".");
    }
    
    public void returnBook(String patronId, String isbn) {
        Patron patron = findPatronById(patronId);
        if (patron == null) {
            System.out.println("Patron with ID " + patronId + " not found.");
            return;
        }
        
        Book book = findBookByIsbn(isbn);
        if (book == null) {
            System.out.println("Book with ISBN " + isbn + " not found in library catalog.");
            return;
        }
        
        if (!patron.hasBorrowedBook(isbn)) {
            System.out.println("This patron has not borrowed this book.");
            return;
        }

        patron.returnBook(isbn);
        book.returnCopy();
        System.out.println("Book '" + book.getTitle() + "' has been returned by " + patron.getName() + ".");
    }
    
    public void searchBooks(int searchOption, String searchTerm) {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        
        List<Book> results = new ArrayList<>();
        
        switch (searchOption) {
            case 1: // Search by title
                for (Book book : books) {
                    if (book.getTitle().toLowerCase().contains(searchTerm.toLowerCase())) {
                        results.add(book);
                    }
                }
                break;
                
            case 2: // Search by author
                for (Book book : books) {
                    if (book.getAuthor().toLowerCase().contains(searchTerm.toLowerCase())) {
                        results.add(book);
                    }
                }
                break;
                
            case 3: // Search by ISBN
                for (Book book : books) {
                    if (book.getIsbn().contains(searchTerm)) {
                        results.add(book);
                    }
                }
                break;
                
            default:
                System.out.println("Invalid search option.");
                return;
        }
        
        if (results.isEmpty()) {
            System.out.println("No books found matching your search criteria.");
        } else {
            System.out.println("\n============== Search Results ==============");
            for (Book book : results) {
                System.out.println("\n" + book);
                System.out.println("-------------------------------------------");
            }
            System.out.println(results.size() + " book(s) found.");
        }
    }
    
    private Book findBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }
    
    private Patron findPatronById(String id) {
        for (Patron patron : patrons) {
            if (patron.getId().equals(id)) {
                return patron;
            }
        }
        return null;
    }
} {
    
}
