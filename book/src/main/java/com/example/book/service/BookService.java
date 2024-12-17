package com.example.book.service;

import com.example.book.domain.Book;
import com.example.book.repository.BookRepository;
import jakarta.transaction.Transactional;
import jdk.dynalink.linker.LinkerServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    //책을 등록
    public Book insertBook(Book book){
        return bookRepository.save(book); //가장 베이직한 방법
    }
    //책을 업데이트(put)
    public Book updateBook(Long id, Book book){
        Book b = bookRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 책입니다."));
        b. setTitle(book.getTitle());
        b.setSubtitle(book.getSubtitle());
        b.setAuthor(book.getAuthor());
        b.setPublisher(book.getPublisher());
        b.setStatus(book.getStatus());
        return bookRepository.save(b);
    }
    //책을 업데이트(patch)
    public Book updateBook(Long id, Book.Status status){
        Book b = bookRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 책입니다."));
        b.setStatus(status);
        return bookRepository.save(b);
    }
    //책을 삭제하고
    public void deleteBook(Long id){
        Book b = bookRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 책입니다."));
        if(b.getStatus() == Book.Status.BORROWED){
            throw new IllegalArgumentException("대출 중인 책은 삭제할 수 없습니다.");
        }
        //1번
        bookRepository.delete(b);
        //2번
       // bookRepository.deleteById(id);
    }
    //책을 조회(단건 조회)
    public Book findBook(Long id){
        return bookRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 책입니다."));
    }
    //책을 조회(다건 조회)

    public List<Book> findBook(){
        return bookRepository.findAll();
    }
}
