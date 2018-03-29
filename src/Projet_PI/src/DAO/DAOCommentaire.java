
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Bean.Commentaire;

/**
 * @author Aurelien
 */
public class DAOCommentaire extends DAO<Commentaire> {
	
	PreparedStatement prStat;
	ResultSet resSet;
	
	/**
	 * @author Aurelien
	 * @param id
	 * @return le commentaire dont l'id correspond au commentaire 
	 */
	public Commentaire find(String id) {
		int idCommentaire = Integer.parseInt(id);
		String query = "Select contenu From Commentaire where id=?";
		Commentaire com = null;
		try{
			prStat = connection.prepareStatement(query);
			prStat.setInt(1, idCommentaire);
			resSet = prStat.executeQuery();
			if(resSet.next()){
				com = new Commentaire(resSet.getString("contenu"));
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally{
			try {
				resSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			try {
				prStat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
		return com;
	}
	@Override
	public boolean create(Commentaire com) {
		String query = "INSERT INTO commentaire (contenu) VALUES (?)";
		return false;
	}
	@Override
	public boolean update(Commentaire object) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean delete(Commentaire object) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}
