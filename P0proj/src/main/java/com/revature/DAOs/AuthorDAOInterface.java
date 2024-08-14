package com.revature.DAOs;

import com.revature.models.Author;

import java.util.ArrayList;

public interface AuthorDAOInterface {
    // A method to select all authors
    ArrayList<Author> getAuthors();

    // A method to insert a new author
    Author insertAuthor(Author author);

    // A method to update an existing author
    void updateAuthor(int authorId, Author updatedAuthor);

    // A method to get an author by ID
    Author getAuthorById(int authorId);

]
