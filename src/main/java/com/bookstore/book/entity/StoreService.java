package com.bookstore.book.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class StoreService {
    private final StoreRepo storeRepo;

    @Autowired
    public StoreService(StoreRepo storeRepo) {
        this.storeRepo = storeRepo;
    }

    public Store addBook(Store store) {
        return  storeRepo.save(store);
    }

    public List<Store> getAllBooks() {
        return storeRepo.findAll();
    }

    public Store findBookById(Long id) {
        return storeRepo.findById(id).orElseThrow(() -> new BookNotFoundException("Book with id " + id + "not found!"));
    }

    public void deleteBook(Long id) {
        storeRepo.deleteById(id);
    }
}
