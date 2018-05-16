/*
 * Haute école Robert Schuman - Libramont, année scolaire 2017 - 2018
 * Bachelier en informatique de gestion, bloc 2
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
import java.sql.SQLException;
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
										this.resSet.getString("PHONE"),
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
	
	public Representant find(String mail){
		String sql = "SELECT * FROM representant WHERE mail = ?";
		Representant rep = null;
		
		try{
			this.prStat = connection.prepareStatement(sql);
			this.prStat.setString(1, mail);
			this.resSet = this.prStat.executeQuery();
			if(this.resSet.next()){
				rep = new Representant(this.resSet.getInt("ID"),
						this.resSet.getString("LASTNAME"),
						this.resSet.getString("FIRSTNAME"),
						this.resSet.getString("PHONE"),
						this.resSet.getString("MAIL"),
						this.resSet.getString("MATRICULE"));
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			try {
				this.resSet.close();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			try {
				this.prStat.close();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		return rep;
	}
	
	public Representant find(String mail, String password){
		String sql = "SELECT * FROM representant WHERE password = STANDARD_HASH(? || (SELECT salt FROM representant WHERE mail = ?),'SHA256') and mail = ?";
		Representant rep = null;
		try {
			this.prStat = connection.prepareStatement(sql);
			this.prStat.setString(1, password);
			this.prStat.setString(2, mail);
			this.prStat.setString(3, mail);
			this.resSet = this.prStat.executeQuery();
			if(this.resSet.next()){
				rep = new Representant(this.resSet.getInt("ID"),
										this.resSet.getString("LASTNAME"),
										this.resSet.getString("FIRSTNAME"),
										this.resSet.getString("PHONE"),
										this.resSet.getString("MAIL"),
										this.resSet.getString("MATRICULE"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				this.resSet.close();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			try {
				this.prStat.close();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		return rep;
	}
	
	public LinkedList<Inscription> findInscription(int id){
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
		String sql = "INSERT INTO representant(lastName, firstName, phone, mail, matricule,password,salt) VALUES (?,?,?,?,?,STANDARD_HASH(?,'SHA256'),?)";
		try {
			this.prStat = connection.prepareStatement(sql,new String[] {"id"});
			this.prStat.setString(1, rep.getLastName());
			this.prStat.setString(2, rep.getFirstName());
			this.prStat.setString(3, rep.getPhone());
			this.prStat.setString(4, rep.getMail());
			this.prStat.setString(5, rep.getMatricule());
			String pass = this.RandomString(15);
			rep.setPassword(pass);

			String salt = this.RandomString(20);
			this.prStat.setString(6, pass+salt);//------------------pass+salt
			this.prStat.setString(7, salt);
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
	
	public boolean update(String mail, String password){
		boolean change = false;
		String sql = "UPDATE representant set password = STANDARD_HASH( ? || (SELECT salt FROM representant WHERE mail = ?),'SHA256') WHERE mail = ?";
		try {
			this.prStat = connection.prepareStatement(sql);
			this.prStat.setString(1, password);
			this.prStat.setString(2, mail);
			this.prStat.setString(3, mail);
			change = (this.prStat.executeUpdate()>0)?true:false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				this.prStat.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return change;
	}
	
	@Override
	public boolean update(Representant rep) {
		// TODO Auto-generated method stub
		if(rep.getPassword() != null && rep.getPassword() != "")this.update(rep.getMail(), rep.getPassword());
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
			this.prStat.setString(3, rep.getPhone());
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
	private String RandomString(int nbChar){
		String allChar = "AZERTYUIOPQSDFGHJKLMWXCVBN123456789azertyuiopqsdfghjklmwxcvbn";
		String random = "";
		for(int i = 0; i < nbChar;i++){
			int charAleatoire =(int)(Math.random() * (allChar.length() - 0));
			random+=allChar.charAt(charAleatoire);
		}
		return random;
	}
}
