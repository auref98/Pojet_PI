/*
 * Haute école Robert Schuman - Libramont, annee scolaire 2017 - 2018
 * Informatique de geston, bloc 2	
 * 
 * Projet integré: réalisation d'un logiciel de gestion des inscriptions à des événements
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
import java.util.LinkedList;

import Bean.Inscription;
import Bean.Plage;
import Bean.Representant;

/**
 * @author Aurelien
 *
 */
public class DAORepresentant extends DAO<Representant>{
	PreparedStatement prStat;
	ResultSet resSet;
	
	@Override
	public Representant find(int id) {
		// TODO Auto-generated method stub
		Representant repr = null;
		String sql = "SELECT * FROM representant WHERE id = ?";
		try {
			this.prStat = connection.prepareStatement(sql);
			this.prStat.setInt(1, id);
			this.resSet = this.prStat.executeQuery();
			if(this.resSet.next()){
				repr = new Representant(id,this.resSet.getString("LASTNAME"),
										this.resSet.getString("FIRSTNAME"),
										this.resSet.getInt("PHONE"),
										this.resSet.getString("MAIL"),
										this.resSet.getString("MATRICULE"));
			}
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
			try {
				this.prStat.close();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
		}
		return repr;
	}
	
	public LinkedList<Inscription> findCommentaire(int id){
		LinkedList<Inscription> ins = new LinkedList<Inscription>();
		String sql = "SELECT * FROM inscription WHERE refrepr = ?";
		Representant rep = this.find(id);
		try {
			this.prStat = connection.prepareStatement(sql);
			this.prStat.setInt(1, id);
			this.resSet = this.prStat.executeQuery();
			while(this.resSet.next()){
				Plage p = new Plage();
				p.setId(this.resSet.getInt("REFPLAGE"));
				ins.add(new Inscription(this.resSet.getInt("ID"),(this.resSet.getInt("VALIDE") == 1)?true:false,rep,p));
			}
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
			try {
				this.prStat.close();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
		}
		return ins;
	}

	@Override
	public boolean create(Representant rep) {
		boolean change = false;
		String sql = "INSERT INTO representant(lastName, firstName, phone, mail, matricule) VALUES (?,?,?,?,?)";
		try {
			this.prStat = connection.prepareStatement(sql);
			this.prStat.setString(1, rep.getLastName());
			this.prStat.setString(2, rep.getFirstName());
			this.prStat.setInt(3, rep.getPhone());
			this.prStat.setString(4, rep.getMail());
			this.prStat.setString(5, rep.getMatricule());
			change = (this.prStat.executeUpdate()>0)?true:false;
			
			this.resSet = this.prStat.getGeneratedKeys();
			if(this.resSet.next()){
				rep.setId(this.resSet.getInt(1));
			}
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
			try {
				this.prStat.close();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
		}
		return change;
	}
	
	@Override
	public boolean update(Representant rep) {
		// TODO Auto-generated method stub
		boolean change = false;
		String sql = "UPDATE representant SET lastname = ?,"
											+ "firstname = ?,"
											+ "phone = ?,"
											+ "mail = ?,"
											+ "matricule = ? WHERE id = ?";
		try {
			this.prStat = connection.prepareStatement(sql);
			this.prStat.setString(1, rep.getLastName());
			this.prStat.setString(2, rep.getFirstName());
			this.prStat.setInt(3, rep.getPhone());
			this.prStat.setString(4, rep.getMail());
			this.prStat.setString(5, rep.getMatricule());
			this.prStat.setInt(6, rep.getId());
			change = (this.prStat.executeUpdate()>0)?true:false;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally{
			try {
				this.prStat.close();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
		}
		return change;
	}

	//? Doit ton supprimer tout les commentaire lié ainsi que toute les inscriptions?
	@Override
	public boolean delete(Representant rep) {
		// TODO Auto-generated method stub
		boolean change = false;
		String sql = "DELETE FROM representant WHERE idr = ?";
		try {
			this.prStat = connection.prepareStatement(sql);
			this.prStat.setInt(1, rep.getId());
			change = (this.prStat.executeUpdate()>0)?true:false;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally{
			try{
				this.prStat.close();
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		return change;
	}
}
