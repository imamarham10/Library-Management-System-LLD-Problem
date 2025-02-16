package libmngsys.dataaccessor;

import libmngsys.book.BookCopy;
import libmngsys.user.Member;

import java.util.List;

public class DBAccessor {
    public Results getBooksWithName(String bookName){
        return null;
    }
    public Results getBooksWithAuthorNames(List<String> authors){
        return null;
    }
    public Results getMembersWithName(String name){
        return null;
    }
    public Results getBooksWithId(int bookId){
        return null;
    }
    public Results getMemberWithId(int memberId){
        return null;
    }
    public void insertBookCopy(BookCopy bookCopy){
    }
    public void deleteBookCopy(BookCopy bookCopy){

    }
    public void markAsBlocked(Member member){

    }
    public void issueBookCopyToMember(BookCopy bookcopy, Member member){

    }
    public void submitBookCopyFromMember(BookCopy bookcopy, Member member){

    }
    public boolean isBookCopyAvailable(BookCopy bookcopy){
        return true;
    }
    public Results getBorrower(BookCopy bookCopy){
        return null;
    }
    public Results getBorrowedBooksByMember(Member member){
        return null;
    }
}
