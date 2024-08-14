package com.revature.services;

import com.revature.DAOs.BookDAO;
import com.revature.models.Book;

import java.util.ArrayList;

public class BookService {
    BookDAO bookDAO = new BookDAO();

    public ArrayList<Book> getBooks() {
        return bookDAO.getBooks();
    }

    public ArrayList<Book> getBooksByAuthorId(int authorId) {
        return bookDAO.getBooksByAuthorId(authorId);
    }

    public Book insertBook(Book book) {
        return bookDAO.insertBook(book);
    }

    public void deleteBook(int bookId) {
        bookDAO.deleteBook(bookId);
    }
}
