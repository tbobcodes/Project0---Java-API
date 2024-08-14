package com.revature.DAOs;

import com.revature.models.Author;
import com.revature.models.Book;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class AuthorDAO implements AuthorDAOInterface {

    @Override
    public ArrayList<Author> getAuthors() {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM authors";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            ArrayList<Author> authors = new ArrayList<>();
            while (rs.next()) {
                Author author = new Author(
                        rs.getInt("author_id"),
                        rs.getString("name"),
                        rs.getString("birth_date"),
                        rs.getString("nationality"),
                        null // This would be a list of books
                );
                authors.add(author);
            }
            return authors;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Author insertAuthor(Author author) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO authors (name, birth_date, nationality) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, author.getName());
            ps.setString(2, author.getBirth_date());
            ps.setString(3, author.getNationality());
            ps.executeUpdate();
            return author;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to insert author!");
        }
        return null;
    }

    @Override
    public void updateAuthor(int authorId, Author updatedAuthor) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "UPDATE authors SET name = ?, birth_date = ?, nationality = ? WHERE author_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, updatedAuthor.getName());
            ps.setString(2, updatedAuthor.getBirth_date());
            ps.setString(3, updatedAuthor.getNationality());
            ps.setInt(4, authorId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to update author!");
        }
    }

    @Override
    public Author getAuthorById(int authorId) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM authors WHERE author_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, authorId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Author(
                        rs.getInt("author_id"),
                        rs.getString("name"),
                        rs.getString("birth_date"),
                        rs.getString("nationality"),
                        null // This would be a list of books
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
