
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
	public Commentaire find(int id) {
		int idCommentaire = id;
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
	
	/**
	 * 
	 * @param com
	 * @param refRepr
	 * @param regEvent
	 * @return true si le commentaire est ajouter
	 */
	public boolean create(Commentaire com) {
		int refRepr = com.getRep().getId();
		int refEvent = com.getEvenement().getId();
		boolean change = false;
		String query = "INSERT INTO commentaire (contenu,refrepr,refeven) VALUES (?,?,?)";
		try{
			this.prStat = connection.prepareStatement(query);
			this.prStat.setString(0, com.getContenu());
			this.prStat.setInt(2, refRepr);
			this.prStat.setInt(3, refEvent);
			int nbChange = this.prStat.executeUpdate();
			change = (nbChange > 0) ? true : false;
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			try {
				prStat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
		return change;
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
