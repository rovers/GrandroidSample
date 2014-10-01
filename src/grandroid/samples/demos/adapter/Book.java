package grandroid.samples.demos.adapter;

import grandroid.database.Identifiable;
import grandroid.database.Table;

@Table("Book")
public class Book implements Identifiable {

    private Integer _id;
    private String bookName;
    private String bookAuthor;

    public Book() {

    }

    public Book(String bookName, String bookAuthor) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

}
