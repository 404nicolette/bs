package com.example.obs.service;

import com.example.obs.domain.Book;
import com.example.obs.domain.Order;
import com.example.obs.repo.OrderRepository;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    //GET-> list all orders
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    //GET-> list order by Id
    public Optional<Order> getOrderById(long orderId){
        return orderRepository.findById(orderId);
    }

    //POST-> create new order
    public Order createOrder(Order order){
        return orderRepository.save(order);
    }

    //PUT-> edit order
    public Order updateOrder(long orderId, Order orderDetails){
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setBooks(orderDetails.getBooks());
        return orderRepository.save(order);
    }

    //DELETE
    public void deleteOrder(long orderId){
        if (!orderRepository.existsById(orderId)){
            throw new RuntimeException("Order not found!");
        } else{
            orderRepository.deleteById(orderId);
        }
    }

    // GET-> list all books in an order
    public List<Book> getAllBooksinOrder(long orderId){
        Order order = orderRepository.findById(orderId).orElseThrow();
        return order.getBooks();
    }

    //PUT-> add new book to an existing order
    public Order addNewBookInOrder(long orderId, Book newBook){
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.getBooks().add(newBook);
        orderRepository.save(order);
        return order;
    }

    //DELETE-> remove a book from an existing order
    public Order deleteABookInOrder(long bookId, long orderId){
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.getBooks().remove(bookId);
        orderRepository.save(order);
        return order;
    }






}
