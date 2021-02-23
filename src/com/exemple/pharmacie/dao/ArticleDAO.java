package com.exemple.pharmacie.dao;
import com.exemple.pharmacie.model.Article;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static DB.ConnectDb.getConnection;


public class ArticleDAO {
//	private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
//	private String jdbcUsername = "root";
//	private String jdbcPassword = "";
	

	
	
	private static final String INSERT_ARTICLE = "INSERT INTO article" + "  (name, prix, quantite) VALUES "
			+ " (?, ?, ?);";

	private static final String SELECT_ARTICLE_BY_ID = "select id,name,prix,quantite from article where id =?";
	private static final String SELECT_ALL_ARTICLE = "select * from article";
	private static final String DELETE_ARTICLE_SQL = "delete from article where id = ?;";
	private static final String UPDATE_ARTICLE_SQL = "update article set name = ?,prix= ?, quantite =? where id = ?;";

	public ArticleDAO() {
	}

//	protected Connection getConnection() {
//		Connection connection = null;
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return connection;
//	}

	public void insertArticle(Article article) throws SQLException {
		System.out.println(INSERT_ARTICLE);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ARTICLE)) {
			preparedStatement.setString(1, article.getName());
			preparedStatement.setFloat(2, article.getPrix());
			preparedStatement.setInt(3, article.getQuantite());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Article selectArticle(int id) {
		Article article = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ARTICLE_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				float prix = rs.getFloat("prix");
				int quantite = rs.getInt("quantite");
				article = new Article(id, name, prix, quantite);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return article;
	}

	public List<Article> selectAllArticles() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Article> articles = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ARTICLE);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				float prix = rs.getFloat("prix");
				int quantite = rs.getInt("quantite");
				articles.add(new Article(id, name, prix, quantite));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return articles;
	}

	public boolean deleteArticle(int id) throws SQLException {
		System.out.println(DELETE_ARTICLE_SQL);
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_ARTICLE_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateArticle(Article article) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_ARTICLE_SQL);) {
			statement.setString(1, article.getName());
			statement.setFloat(2, article.getPrix());
			statement.setInt(3, article.getQuantite());
			statement.setInt(4, article.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
