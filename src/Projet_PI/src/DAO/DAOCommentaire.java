/*
 * Haute �cole Robert Schuman - Libramont, annee scolaire 2017 - 2018
 * Informatique de geston, bloc 2	
 * 
 * Projet integr�: r�alisation d'un logiciel de gestion des inscriptions � des �v�nements
 * 
 * Groupe: NamingException {
 * 				Adam Ludovic;
 *				Arnould Killian;
 * 				De Bernardi Christophe;
 * 				Fockedey Aurelien;
 * 				Mathieu Robin;
 * 				Modave Louis;
 * 				}
 */

package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import Bean.Commentaire;
import Bean.Evenement;
import Bean.Representant;

/**
 * @author Aurelien
 */
public class DAOCommentaire extends DAO<Commentaire> {
	
	PreparedStatement prStat;
	ResultSet resSet;
	
	/**
	 * @author Aurelien.
	 * @param id
	 * @return le commentaire dont l'id correspond au commentaire 
	 */
	public Commentaire find(int id) {
		int idCommentaire = id;
		String query = "Select * From Commentaire where id=?";
		Commentaire com = null;
		try{
			prStat = connection.prepareStatement(query);
			prStat.setInt(1, idCommentaire);
			resSet = prStat.executeQuery();
			if(resSet.next()){
				Representant rep = new Representant();
				rep.setId(this.resSet.getInt("REFREPR"));
				Evenement eve = new Evenement();
				eve.setId(this.resSet.getInt("REFEVEN"));
				com = new Commentaire(id,resSet.getString("contenu"));
				com.setRep(rep);
				com.setEvenement(eve);
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
	
	/** POUR LUDOVIC
	 * Recherche dans la BD tout les commentaire relier a idEve.
	 * @param idEve
	 * @return LinkedList de commentaire relier a un evenement.
	 
	public LinkedList<Commentaire> findAllEve(int idEve){
		LinkedList<Commentaire> com = null;
		String sql = "SELECT * FROM commentaire WHERE refeven = ?";
		try {
			this.prStat = connection.prepareStatement(sql);
			this.prStat.setInt(1, idEve);
			this.resSet = this.prStat.executeQuery();
			while(this.resSet.next())
				com.add(new Commentaire(this.resSet.getInt("id"),this.resSet.getString("contenu")));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally{
			try {
				this.resSet.close();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
			try{
				this.prStat.close();
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		return com;
	}*/
	
	/**
	 * @author Aurelien
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
	public boolean update(Commentaire com) {
		String query = "UPDATE commentaire SET contenu = ? where id = ?";
		boolean change = false;
		try{
			this.prStat = connection.prepareStatement(query);
			this.prStat.setString(1, com.getContenu());
			this.prStat.setInt(2, com.getId());
			change = (this.prStat.executeUpdate()>0) ? true : false;
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
	public boolean delete(Commentaire com) {
		String query = "DELETE FROM commentaire WHERE id=?";
		boolean change = false;
		try{
			this.prStat = connection.prepareStatement(query);
			this.prStat.setInt(1, com.getId());
			change = (this.prStat.executeUpdate()>0)?true:false;
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			try{
				this.prStat.close();
			}catch(SQLException e){
				System.out.println(e.getMessage());
			}
		}
		return change;
	}
	
	
	
}
