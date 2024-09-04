package com.example.obs.service;

import com.example.obs.domain.Book;
import com.example.obs.repo.BookRepository;
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
    public Optional<Book> getBookById(Long bookId) {
        return bookRepository.findById(bookId);
    }

    // create - only for admin
    // later on ensure to create an authentication process that separates user from admin
    // user cannot add or put the book only admin
    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    public Book updateBook(Long bookId, Book bookDetails){
        Book book = bookRepository.findById(bookId).orElseThrow();
        book.setISBN(bookDetails.getISBN());
        book.setTitle(bookDetails.getTitle());
        book.setPrice(bookDetails.getPrice());
        book.setAuthors(bookDetails.getAuthors());
        book.setOrders(bookDetails.getOrders());
        book.setPublicationYear(bookDetails.getPublicationYear());
        return bookRepository.save(book);
    }

    public void deleteBook(Long bookId){
        if (!bookRepository.existsById(bookId)){
            throw new RuntimeException("Book not found!");
        } else{
            bookRepository.deleteById(bookId);
        }
    }
}
