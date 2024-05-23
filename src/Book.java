

public class Book {
    private int id;
    private String author;
    private String title;
    private String price;
    private String date;
    private String volume;

    public Book(int id, String author, String title, String price, String date, String volume) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.price = price;
        this.date = date;
        this.volume = volume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", price='" + price + '\'' +
                ", date='" + date + '\'' +
                ", volume='" + volume + '\'' +
                '}';
    }
}