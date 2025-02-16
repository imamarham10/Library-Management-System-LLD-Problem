package libmngsys.searcher;

import libmngsys.book.BookCopy;

import java.util.List;

public class AuthorBasedBookSearcher implements BookSearcher {
    private final List<String> authorNames;
    public AuthorBasedBookSearcher(List<String> authorNames) {
        this.authorNames = authorNames;
    }
    @Override
    public List<BookCopy> search() {
        return null;
    }
}
