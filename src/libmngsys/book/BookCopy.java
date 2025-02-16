package libmngsys.book;

import java.util.Date;
import java.util.List;

public class BookCopy{
    private final BookDetails bookDetails;
    private final int id;
    public BookCopy(BookDetails bookDetails, int id) {
        this.bookDetails = bookDetails;
        this.id = id;
    }
}
