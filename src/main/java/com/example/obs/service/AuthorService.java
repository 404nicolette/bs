package com.example.obs.service;

import com.example.obs.domain.Author;
import com.example.obs.domain.Book;
import com.example.obs.repo.AuthorRepository;
import com.example.obs.repo.BookRepository;

import java.util.List;
import java.util.Optional;

public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    // get all
    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    // get by id
    public Optional<Author> getAuthorById(Long authorId) {
        return authorRepository.findById(authorId);
    }

    // create - only for admin
    // later on ensure to create an authentication process that separates user from admin
    // user cannot add or put the book only admin
    public Author addAuthor(Author author){
        return authorRepository.save(author);
    }

    public Author updateAuthor(Long authorId, Author authorDetails){
        Author author = authorRepository.findById(authorId).orElseThrow();
        author.setAuthorName(authorDetails.getAuthorName());
        author.setBooks(authorDetails.getBooks());
        return authorRepository.save(author);
    }

    public void deleteAuthor(Long authorId){
        if (!authorRepository.existsById(authorId)){
            throw new RuntimeException("Author not found!");
        } else{
            authorRepository.deleteById(authorId);
        }
    }
}
