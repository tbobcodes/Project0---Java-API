package com.revature.services;

import com.revature.DAOs.AuthorDAO;
import com.revature.models.Author;
import com.revature.models.Book;

import java.util.ArrayList;

public class AuthorService {
    AuthorDAO authorDAO = new AuthorDAO();

    public ArrayList<Author> getAuthors() {
        return authorDAO.getAuthors();
    }

    public Author insertAuthor(Author author) {
        return authorDAO.insertAuthor(author);
    }

    public void updateAuthor(int authorId, Author updatedAuthor) {
        authorDAO.updateAuthor(authorId, updatedAuthor);
    }

    public Author getAuthorById(int authorId) {
        return authorDAO.getAuthorById(authorId);
    }

    public ArrayList<Book> getBooksByAuthorId(int authorId) {
        return authorDAO.getBooksByAuthorId(authorId);
    }

    public Book insertBook(Book book) {
        return authorDAO.insertBook(book);
    }

    public void deleteBook(int bookId) {
        authorDAO.deleteBook(bookId);
    }
}
