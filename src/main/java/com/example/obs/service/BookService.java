package com.example.obs.service;

import com.example.obs.domain.Book;
import com.example.obs.repo.BookRepository;
import org.antlr.v4.runtime.atn.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // get all
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    // get by id
    public Optional<Book> getBookbyId(Long bookId){
        return bookRepository.findAllById(bookId);
    }

    // create - only for admin
    // later on ensure to create an authentication process that separates user from admin
    // user cannot add or put the book only admin
    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    public Book updateBook(Long bookId, Book bookDetails){
        Book book = bookRepository.findAllById(bookId).orElseThrow(() -> new RuntimeException("Book not found!"));
        book.setAuthors(bookDetails.getAuthors());
        book.setISBN(bookDetails.getISBN());
        book.setOrders(bookDetails.getOrders());
        book.setPrice(bookDetails.getPrice());
        book.setTitle(bookDetails.getTitle());
        book.setPublicationYear(bookDetails.getPublicationYear());
        return bookRepository.save(book);
    }

    public void deleteBook(Long bookId){
        if(!bookRepository.existsById(bookId)){
            throw new RuntimeException("Book not found!");
        }
        bookRepository.deleteById(bookId);
    }
}
