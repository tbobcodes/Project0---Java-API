package com.revature.controllers;

import com.revature.DAOs.AuthorDAO;
import com.revature.models.Author;
import com.revature.models.Book;
import com.revature.services.AuthorService;
import com.revature.services.BookService;
import io.javalin.http.Handler;

public class AuthorController {
    AuthorService authorService = new AuthorService();
    BookService bookService = new BookService();

    //We need an EmployeeDAO to use its employee data methods
    AuthorDAO aDAO = new AuthorDAO();

    //OLD^ we're using services now:


    public Handler getAllAuthors = ctx -> {
        ctx.json(authorService.getAuthors());
        ctx.status(200);
    };

    public Handler insertAuthor = ctx -> {
        Author author = ctx.bodyAsClass(Author.class);
        authorService.insertAuthor(author);
        ctx.status(201);
        ctx.json(author);
    };

    public Handler updateAuthor = ctx -> {
        int authorId = Integer.parseInt(ctx.pathParam("id"));
        Author author = ctx.bodyAsClass(Author.class);
        authorService.updateAuthor(authorId, author);
        ctx.status(200);
        ctx.json(author);
    };

    public Handler getAuthorById = ctx -> {
        int authorId = Integer.parseInt(ctx.pathParam("id"));
        Author author = authorService.getAuthorById(authorId);
        if (author != null) {
            ctx.json(author);
        } else {
            ctx.status(404).result("Author not found");
        }
    };

    public Handler getBooksByAuthorId = ctx -> {
        int authorId = Integer.parseInt(ctx.pathParam("id"));
        ctx.json(bookService.getBooksByAuthorId(authorId));
    };

    public Handler insertBook = ctx -> {
        Book book = ctx.bodyAsClass(Book.class);
        bookService.insertBook(book);
        ctx.status(201);
        ctx.json(book);
    };

    public Handler deleteBook = ctx -> {
        int bookId = Integer.parseInt(ctx.pathParam("id"));
        bookService.deleteBook(bookId);
        ctx.status(204);
    };
}
