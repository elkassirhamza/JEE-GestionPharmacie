//import com.exemple.pharmacie.dao.ArticleDAO;

import DB.ConnectDb;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(ConnectDb.getConnection()); 
		
//		ArticleDAO dao=new  ArticleDAO();
//		System.out.println(dao.selectAllArticles());

	}

}
