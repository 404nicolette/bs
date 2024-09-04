package com.example.obs;

import com.example.obs.domain.Author;
import com.example.obs.domain.Book;
import com.example.obs.domain.Order;
import com.example.obs.repo.AuthorRepository;
import com.example.obs.repo.BookRepository;
import com.example.obs.repo.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ObsApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ObsApplication.class, args);

    }
    @Override
    public void run(String... args) throws Exception {

    }
}



