package com.example.obs.service;

import com.example.obs.domain.Author;
import com.example.obs.domain.Book;
import com.example.obs.repo.AuthorRepository;
import com.example.obs.repo.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    // GET all
    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    // GET by id
    public Optional<Author> getAuthorById(Long authorId) {
        return authorRepository.findById(authorId);
    }

    // POST - only for admin
    // later on ensure to create an authentication process that separates user from admin
    // user cannot add or put the book only admin
    public Author addAuthor(Author author){
        return authorRepository.save(author);
    }

    //PUT
    public Author updateAuthor(Long authorId, Author authorDetails){
        Author author = authorRepository.findById(authorId).orElseThrow();
        author.setAuthorName(authorDetails.getAuthorName());
        author.setBooks(authorDetails.getBooks());
        return authorRepository.save(author);
    }

    //DELETE
    public void deleteAuthor(Long authorId){
        if (!authorRepository.existsById(authorId)){
            throw new RuntimeException("Author not found!");
        } else{
            authorRepository.deleteById(authorId);
        }
    }

    //GET-> list all books written by the author
    public List<Book> getAllBooksByAuthor(long authorId){
        Author author = authorRepository.findById(authorId).orElseThrow();
        return author.getBooks();
    }
}
