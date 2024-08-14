package com.revature.DAOs;

import com.revature.models.Book;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class BookDAO implements BookDAOInterface {

    @Override
    public ArrayList<Book> getBooks() {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM books";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            ArrayList<Book> books = new ArrayList<>();

            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("author_id"),
                        rs.getDouble("price")
                );
                books.add(book);
            }

            return books;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Book insertBook(Book book) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO books (title, author, author_id, price) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setInt(3, book.getAuthorId());
            pstmt.setDouble(4, book.getPrice());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                book.setBookId(rs.getInt(1));
            }

            return book;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ArrayList<Book> getBooksByAuthorId(int authorId) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM books WHERE author_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, authorId);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Book> books = new ArrayList<>();

            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("author_id"),
                        rs.getDouble("price")
                );
                books.add(book);
            }

            return books;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void deleteBook(int bookId) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "DELETE FROM books WHERE book_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bookId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
