package com.revature.DAOs;

import com.revature.models.Book;

import java.util.ArrayList;

public interface BookDAOInterface {
    // A method to select all books
    ArrayList<Book> getBooks();

    // A method to insert a new book
    Book insertBook(Book book);

    // A method to get books by author ID
    ArrayList<Book> getBooksByAuthorId(int authorId);

    // A method to delete a book by ID
    void deleteBook(int bookId);
}
