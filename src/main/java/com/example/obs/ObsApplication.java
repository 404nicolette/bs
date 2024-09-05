package com.example.obs;

import com.example.obs.domain.Author;
import com.example.obs.domain.Book;
import com.example.obs.domain.Order;
import com.example.obs.repo.AuthorRepository;
import com.example.obs.repo.BookRepository;
import com.example.obs.repo.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Arrays;

@Component
public class ObsApplication implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;
    private final AuthorRepository authorRepository;

    public ObsApplication(BookRepository bookRepository, OrderRepository orderRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.orderRepository = orderRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author author1 = new Author();
        author1.setAuthorName("Agatha Christie");

        Author author2 = new Author();
        author2.setAuthorName("George R.R. Martin");

        Author author3 = new Author();
        author3.setAuthorName("J.R.R. Tolkien");

        Author author4 = new Author();
        author4.setAuthorName("Isaac Asimov");

        Book book1 = new Book();
        book1.setISBN("978-0062073488");
        book1.setTitle("Murder on the Orient Express");
        book1.setPublicationYear(1934);
        book1.setPrice(14.99);

        Book book2 = new Book();
        book2.setISBN("978-0553103540");
        book2.setTitle("A Game of Thrones");
        book2.setPublicationYear(1996);
        book2.setPrice(25.99);

        Book book3 = new Book();
        book3.setISBN("978-0618260300");
        book3.setTitle("The Lord of the Rings");
        book3.setPublicationYear(1954);
        book3.setPrice(29.99);

        Book book4 = new Book();
        book4.setISBN("978-0307292063");
        book4.setTitle("Good Omens");
        book4.setPublicationYear(1990);
        book4.setPrice(19.99);

        author1.setBooks(Arrays.asList(book1));
        author2.setBooks(Arrays.asList(book2, book4));
        author3.setBooks(Arrays.asList(book3));
        author4.setBooks(Arrays.asList(book4));

        book1.setAuthors(Arrays.asList(author1));
        book2.setAuthors(Arrays.asList(author2));
        book3.setAuthors(Arrays.asList(author3));
        book4.setAuthors(Arrays.asList(author2, author4));

        authorRepository.saveAll(Arrays.asList(author1, author2, author3, author4));
        bookRepository.saveAll(Arrays.asList(book1, book2, book3, book4));

        Order order1 = new Order();
        order1.setDateTime(Timestamp.valueOf("2024-09-01 10:30:00"));
        order1.setCustomerName("Alice Johnson");
        order1.setBooks(Arrays.asList(book1, book2));

        Order order2 = new Order();
        order2.setDateTime(Timestamp.valueOf("2024-09-02 15:45:00"));
        order2.setCustomerName("Bob Smith");
        order2.setBooks(Arrays.asList(book3));

        Order order3 = new Order();
        order3.setDateTime(Timestamp.valueOf("2024-09-03 12:00:00"));
        order3.setCustomerName("Charlie Brown");
        order3.setBooks(Arrays.asList(book1, book3, book4));

        orderRepository.saveAll(Arrays.asList(order1, order2, order3));
    }
}

