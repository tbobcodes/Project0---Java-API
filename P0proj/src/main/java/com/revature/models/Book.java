package com.revature.models;

public class Book {

    private int book_id;
    private String title;
    private String description;
    private int publication_year;
    private Author author; // Reference to Author object
    private int author_id_fk; // Foreign key for inserts

    // No-args constructor
    public Book() {
    }

    // All-args constructor
    public Book(int book_id, String title, String description, int publication_year, Author author) {
        this.book_id = book_id;
        this.title = title;
        this.description = description;
        this.publication_year = publication_year;
        this.author = author;
    }

    // Constructor to be used with inserts (no id, and author_id_fk instead of Author)
    public Book(String title, String description, int publication_year, int author_id_fk) {
        this.title = title;
        this.description = description;
        this.publication_year = publication_year;
        this.author_id_fk = author_id_fk;
    }

    // Getters and setters
    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPublication_year() {
        return publication_year;
    }

    public void setPublication_year(int publication_year) {
        this.publication_year = publication_year;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getAuthor_id_fk() {
        return author_id_fk;
    }

    public void setAuthor_id_fk(int author_id_fk) {
        this.author_id_fk = author_id_fk;
    }

    // toString
    @Override
    public String toString() {
        return "Book{" +
                "book_id=" + book_id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", publication_year=" + publication_year +
                ", author=" + author +
                '}';
    }
}
