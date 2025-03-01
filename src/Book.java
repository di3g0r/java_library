package Library.Model;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private int totalCopies;
    private int availableCopies;
    
    public Book(String title, String author, String isbn, int copies) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.totalCopies = copies;
        this.availableCopies = copies;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public int getTotalCopies() {
        return totalCopies;
    }
    
    public int getAvailableCopies() {
        return availableCopies;
    }
    
    public boolean isAvailable() {
        return availableCopies > 0;
    }
    
    public boolean borrowCopy() {
        if (availableCopies > 0) {
            availableCopies--;
            return true;
        }
        return false;
    }
    
    public void returnCopy() {
        if (availableCopies < totalCopies) {
            availableCopies++;
        }
    }
    
    public void addCopies(int copies) {
        totalCopies += copies;
        availableCopies += copies;
    }
    
    public boolean removeCopies(int copies) {
        if (availableCopies >= copies) {
            totalCopies -= copies;
            availableCopies -= copies;
            return true;
        } else {
            System.out.println("Cannot remove copies: Some copies are currently borrowed.");
            return false;
        }
    }
    
    @Override
    public String toString() {
        return "Title: " + title + 
               "\nAuthor: " + author + 
               "\nISBN: " + isbn + 
               "\nAvailable Copies: " + availableCopies + 
               "/" + totalCopies;
    }
}
