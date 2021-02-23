package com.exemple.pharmacie.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exemple.pharmacie.dao.ArticleDAO;
import com.exemple.pharmacie.model.Article;


/**
 * Servlet implementation class ArticleServlet
 */
@SuppressWarnings("unused")
@WebServlet("/")
public class ArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleDAO articleDAO;
	
	public void init() {
		articleDAO = new ArticleDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertArticle(request, response);
				break;
			case "/delete":
				deleteArticle(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateArticle(request, response);
				break;
			default:
				listArticle(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listArticle(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Article> listArticle = articleDAO.selectAllArticles();
		request.setAttribute("listArticle", listArticle);
		RequestDispatcher dispatcher = request.getRequestDispatcher("article-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("article-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Article existingArticle = articleDAO.selectArticle(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("article-form.jsp");
		request.setAttribute("article", existingArticle);
		dispatcher.forward(request, response);

	}

	private void insertArticle(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String name = request.getParameter("name");
		float prix = Float.parseFloat(request.getParameter("prix"));
		int quantite = Integer.parseInt(request.getParameter("quantite"));
		Article newArticle = new Article(name, prix, quantite);
		articleDAO.insertArticle(newArticle);
		response.sendRedirect("list");
	}

	private void updateArticle(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		float prix = Float.parseFloat(request.getParameter("prix"));
		int quantite = Integer.parseInt(request.getParameter("quantite"));

		Article article = new Article(id, name, prix, quantite);
		articleDAO.updateArticle(article);
		response.sendRedirect("list");
	}

	private void deleteArticle(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		articleDAO.deleteArticle(id);
		response.sendRedirect("list");

	}

}
