package com.example.obs.controller;


import com.example.obs.domain.Author;
import com.example.obs.domain.Book;
import com.example.obs.service.AuthorService;
import com.example.obs.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/authors")
public class AuthorRestController {
    private final AuthorService authorService;

    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    //GET all
    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthor(){
        try {
            List<Author> authors = authorService.getAllAuthors();
            return ResponseEntity.ok(authors);
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
    // GET book by id
    @GetMapping("/{authorId}")
    public ResponseEntity<Author> getAuthorId(@PathVariable Long authorId) {
        Optional<Author> author = authorService.getAuthorById(authorId);

        return author.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST mapping only available for admin
    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author){
        Author newAuthor = authorService.addAuthor(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAuthor);
    }

    @PutMapping("/{authorId}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long authorId, @RequestBody Author authorDetails) {
        try {
            Author updateAuthor = authorService.updateAuthor(authorId, authorDetails);
            return ResponseEntity.ok(updateAuthor);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity<Author> deleteAuthor(@PathVariable Long authorId){
        try{
            authorService.deleteAuthor(authorId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


}


