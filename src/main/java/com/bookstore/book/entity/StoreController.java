package com.bookstore.book.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class StoreController {
    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping
    public ResponseEntity<List<Store>> getAllBooks() {
        List<Store> stores = storeService.getAllBooks();
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Store> getStoreById(@PathVariable("id") Long id) {
        Store store = storeService.findBookById(id);
        return new ResponseEntity<>(store, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Store> addBook(@RequestBody Store store) {
        Store newBook = storeService.addBook(store);
        return new ResponseEntity<>(newBook,HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}/{title}/{author}/{price}")
    public ResponseEntity<Store> updateBook(@PathVariable("id") Long id, @PathVariable("title") String title,@PathVariable("author") String author, @PathVariable("price") Double price, @RequestBody Store store) {
        Store updateBook = storeService.findBookById(id);
        updateBook.setTitle(title);
        updateBook.setAuthor(author);
        updateBook.setPrice(price);
        storeService.addBook(updateBook);
        return  new ResponseEntity<>(updateBook,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") Long id) {
        storeService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
