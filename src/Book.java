public class Book {
    private String id;
    private String title;
    private int stock;
    private Book next;

    public Book(String id, String title, int stock) {
        this.id = id;
        this.title = title;
        this.stock = stock;
        this.next = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Book getNext() {
        return next;
    }

    public void setNext(Book next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Judul: " + title + ", Stok: " + stock;
    }
}
