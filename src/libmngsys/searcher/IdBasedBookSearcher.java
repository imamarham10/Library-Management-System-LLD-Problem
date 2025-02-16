package libmngsys.searcher;

import libmngsys.book.BookCopy;
import libmngsys.dataaccessor.DBAccessor;
import libmngsys.dataaccessor.Results;
import libmngsys.dataaccessor.ResultsConverter;

import java.util.List;

public class IdBasedBookSearcher implements  BookSearcher{
    private final int id;
    private final DBAccessor dbAccessor;
    public IdBasedBookSearcher(int id) {
        this.id = id;
        this.dbAccessor = new DBAccessor();
    }
    @Override
    public List<BookCopy> search() {
        Results results = dbAccessor.getBooksWithId(id);
        return ResultsConverter.convertToBookCopies(results);
    }
}
