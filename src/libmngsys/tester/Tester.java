package libmngsys.tester;

import libmngsys.auth.UserAuthenticator;
import libmngsys.book.BookCopy;
import libmngsys.book.BookDetails;
import libmngsys.dataaccessor.DBAccessor;
import libmngsys.library.Library;
import libmngsys.searcher.*;
import libmngsys.user.Member;
import libmngsys.util.IdGenerator;

import java.util.Date;
import java.util.List;

public class Tester {
    private final Library library = new Library(new DBAccessor());
    public List<BookCopy> searchBooksByBookName(String bookName) {
        if(bookName == null){
            throw new IllegalArgumentException("Book name cannot be null.");
        }
        BookSearcher bookSearcher = new NameBasedBookSearcher(bookName);
        return bookSearcher.search();
    }
    public List<BookCopy> searchBooksByAuthorNames(List<String>authorNames){
        if(authorNames == null || authorNames.size() == 0){
            throw new IllegalArgumentException("Author names cannot be null.");
        }
        BookSearcher bookSearcher = new AuthorBasedBookSearcher(authorNames);
        return bookSearcher.search();
    }
    public List<Member> searchMembersByMemberName(String memberName, String adminToken){
        if(adminToken == null || !UserAuthenticator.isAdmin(adminToken)){
            throw new IllegalArgumentException("Operation forbidden");
        }
        if(memberName == null) {
            throw new IllegalArgumentException("Member name cannot be null.");
        }
        MemberSearcher memberSearcher = new NameBasedMemberSearcher(memberName);
        return memberSearcher.search();
    }
    public void addBook(String bookName, Date publicationDate, List<String> authorNames, String adminToken){
        if(adminToken == null || !UserAuthenticator.isAdmin(adminToken)){
            throw new IllegalArgumentException("Operation forbidden");
        }
        if(bookName == null || publicationDate == null || authorNames == null || authorNames.size() == 0){
            throw new IllegalArgumentException("Invalid input.");
        }
        BookCopy bookCopy = new BookCopy(new BookDetails(bookName,publicationDate, authorNames), IdGenerator.getUniqueId());
        library.addBookCopy(bookCopy);
    }
    public void deleteBook(int bookId, String adminToken){
        if(adminToken == null || !UserAuthenticator.isAdmin(adminToken)){
            throw new IllegalArgumentException("Operation forbidden");
        }
        if(bookId <= 0){
            throw new IllegalArgumentException("Book ID cannot be negative number.");
        }
        BookSearcher bookSearcher = new IdBasedBookSearcher(bookId);
        List<BookCopy> bookCopy = bookSearcher.search();
        if(bookCopy.size() == 0 || bookCopy == null){
            throw new RuntimeException(("No book copies retrieved for book " + bookId));
        }
        library.deleteBookCopy(bookCopy.get(0));
    }
    public void blockMember(int memberId, String adminToken) {
        if (adminToken == null || !UserAuthenticator.isAdmin(adminToken)) {
            throw new IllegalArgumentException("Operation forbidden");
        }
        if (memberId <= 0) {
            throw new IllegalArgumentException("Member ID cannot be negative number.");
        }
        MemberSearcher memberSearcher = new IdBasedMemberSearcher(memberId);
        List<Member> member = memberSearcher.search();
        if(member == null || member.isEmpty()) {
            throw new RuntimeException("No member retrieved for member id: " + memberId);
        }
        library.blockMember(member.get(0));
    }
    public void issueBook(int memberId, int bookCopyId, String adminToken){
        if(adminToken == null || !UserAuthenticator.isAdmin(adminToken)){
            throw new IllegalArgumentException("Operation forbidden");
        }
        if(memberId <= 0 || bookCopyId <= 0){
            throw new IllegalArgumentException("Member ID or Book copy ID cannot be negative number.");
        }
        MemberSearcher memberSearcher = new IdBasedMemberSearcher(memberId);
        List<Member> member = memberSearcher.search();
        if(member == null || member.isEmpty()) {
            throw new RuntimeException("No member retrieved for member id: " + memberId);
        }
        BookSearcher bookSearcher = new IdBasedBookSearcher(bookCopyId);
        List<BookCopy> bookCopy = bookSearcher.search();
        if(bookCopy == null || bookCopy.isEmpty()) {
            throw new RuntimeException("No book copies retrieved for book copy id: " + bookCopyId);
        }
        library.issueBookCopy(bookCopy.getFirst(), member.getFirst());
    }
    public void submitBook(int memberId, int bookCopyId, String adminToken){}
    public Member getBorrowerOfBook(int bookCopyId, String adminToken){
        if(adminToken == null || !UserAuthenticator.isAdmin(adminToken)){
            throw new IllegalArgumentException("Operation forbidden");
        }
        if(bookCopyId <= 0){
            throw new IllegalArgumentException("Book copy ID cannot be negative number.");
        }
        BookSearcher bookSearcher = new IdBasedBookSearcher(bookCopyId);
        List<BookCopy> bookCopy = bookSearcher.search();
        if(bookCopy == null || bookCopy.isEmpty()) {
            throw new RuntimeException("No book copies retrieved for book copy id: " + bookCopyId);
        }
        return library.getBorrower(bookCopy.getFirst());
    }
    public List<BookCopy> getBookesBorrowedByMember(int memberId, String adminToken){
        if(adminToken == null || !UserAuthenticator.isAdmin(adminToken)){
            throw new IllegalArgumentException("Operation forbidden");
        }
        if(memberId <= 0){
            throw new IllegalArgumentException("Member ID cannot be negative number.");
        }
        MemberSearcher memberSearcher = new IdBasedMemberSearcher(memberId);
        List<Member> member = memberSearcher.search();
        if(member == null || member.isEmpty()) {
            throw new RuntimeException("No member retrieved for member id: " + memberId);
        }
        return library.getBorrowedBooks(member.getFirst());
    }


}
