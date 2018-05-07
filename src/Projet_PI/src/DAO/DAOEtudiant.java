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
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Bean.Adresse;
import Bean.Etudiant;
import Bean.Representant;
import Bean.Section;

/**
 * @author Aurelien
 *
 */
public class DAOEtudiant extends DAO<Etudiant>{

	PreparedStatement prStat;
	ResultSet resSet;
	
	@Override
	public Etudiant find(int id) {
		// TODO Auto-generated method stub
		Etudiant etu = null;
		String sql = "SELECT * FROM etudiant WHERE id = ?";
		try {
			this.prStat = connection.prepareStatement(sql);
			this.prStat.setInt(1, id);
			this.resSet = this.prStat.executeQuery();
			if(this.resSet.next()){
				//Section sect = new DAOSection().find(this.resSet.getInt("REFSECT"));
				//Adresse adr = new DAOAdresse().find(this.resSet.getInt("REFADDR"));
				Representant rep = new DAORepresentant().find(this.resSet.getInt("ID"));
				LocalDate date = null;
				if(this.resSet.getString("DATENAISSANCE") != null)date = LocalDate.parse(this.resSet.getString("DATENAISSANCE").split(" ")[0],DateTimeFormatter.ISO_DATE);
				etu = new Etudiant(rep.getLastName(),
									rep.getFirstName(),
									rep.getPhone(),
									rep.getMail(),
									rep.getMatricule(),
									this.resSet.getInt("ID"),
									date,
									this.resSet.getString("PAYSNAISSANCE"),
									this.resSet.getString("LIEUNAISSANCE"),
									this.resSet.getString("NUMNATIONAL"),
									this.resSet.getString("NATIONALITE"),
									this.resSet.getString("NUMBANQUE"),
									(this.resSet.getInt("SOUTIENSOCIAL")==1)?true:false,
									this.resSet.getString("EMPLACEMENTECOLE"),
									this.resSet.getString("ROLE"),
									null,
									null);
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
			try{
				this.prStat.close();
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		return etu;
	}
	
	public Section findSect(int id){
		Section sec = null;
		String sql = "SELECT refsect FROM section WHERE id = ?";
		try {
			this.prStat = connection.prepareStatement(sql);
			this.prStat.setInt(1, id);
			this.resSet = this.prStat.executeQuery();
			if(this.resSet.next()){
				sec = new DAOSection().find(this.resSet.getInt("REFSECT"));
			}
		}
		catch(Exception e){
			e.printStackTrace();
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
		return sec;
	}
	
	public Adresse findAddr(int id){
		Adresse addr = null;
		String sql = "SELECT refsect FROM section WHERE id = ?";
		try {
			this.prStat = connection.prepareStatement(sql);
			this.prStat.setInt(1, id);
			this.resSet = this.prStat.executeQuery();
			if(this.resSet.next()){
				addr = new DAOAdresse().find(this.resSet.getInt("REFADDR"));
			}
		}
		catch(Exception e){
			e.printStackTrace();
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
		return addr;
	}
	
	public Etudiant find(String mail, String password){
		Representant rep = new DAORepresentant().find(mail, password);
		if(rep != null)return this.find(rep.getId());
		return null;
	}

	@Override
	public boolean create(Etudiant etu) {
		// TODO Auto-generated method stub
		Representant repr = new Representant(etu.getId(),
											etu.getLastName(),
											etu.getFirstName(),
											etu.getPhone(),
											etu.getMail(),
											etu.getMatricule());
		boolean change = false;
		String sql = "INSERT INTO etudiant ( id,"
											+ "datenaissance,"
											+ "paysnaissance,"
											+ "lieunaissance,"
											+ "numnational,"
											+ "nationalite,"
											+ "numbanque,"
											+ "soutiensocial,"
											+ "emplacementecole,"
											+ "role,"
											+ "refsect,"
											+ "refaddr)"
											+ "VALUES(?, to_date(?,'yyyy-mm-dd'),?,?,?,?,?,?,?,?,?,?)";
		try {
			connection.setAutoCommit(false);
			if(!new DAORepresentant().create(repr))throw new SQLException();
			this.prStat = connection.prepareStatement(sql);
			this.prStat.setInt(1, repr.getId());
			if(etu.getDateNaissance() != null)this.prStat.setString(2, etu.getDateNaissance().toString());
			else this.prStat.setNull(2, 0);
			this.prStat.setString(3, etu.getPaysNaissance());
			this.prStat.setString(4, etu.getLieuNaissance());
			this.prStat.setString(5, etu.getNumNational());
			this.prStat.setString(6, etu.getNationalite());
			this.prStat.setString(7, etu.getNumBanque());
			this.prStat.setInt(8, (etu.isSoutienSocial())?1:0);
			this.prStat.setString(9, etu.getEmplacementEcole());
			this.prStat.setString(10, etu.getRole());
			if(etu.getSec() != null)this.prStat.setInt(11, etu.getSec().getId());
			else this.prStat.setNull(11, 0);
			if(etu.getAdr() != null)this.prStat.setInt(12, etu.getAdr().getId());
			else this.prStat.setNull(12, 0);
			change = (this.prStat.executeUpdate() > 0)?true:false;
			connection.commit();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				connection.setAutoCommit(true);
			} catch (Exception e) {
				// TODO: handle exception
				System.err.println("Erreur, auto comit(Etudiant)");
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
	public boolean update(Etudiant etu) {
		// TODO Auto-generated method stub
		boolean change = false;
		String sql = "UPDATE etudiant SET datenaissance = to_date(?,'yyyy-mm-dd'),"
											+ "paysnaissance = ?,"
											+ "lieunaissance = ?,"
											+ "numnational = ?,"
											+ "nationalite = ?,"
											+ "numbanque = ?,"
											+ "soutiensocial = ?,"
											+ "emplacementecole = ?,"
											+ "role = ?,"
											+ "refsect = ?,"
											+ "refaddr = ? WHERE id = ?";
		try {
			connection.setAutoCommit(false);
			if(!new DAORepresentant().update((Representant)etu))throw new SQLException();
			this.prStat = connection.prepareStatement(sql);
			if(etu.getDateNaissance() != null)this.prStat.setString(1, etu.getDateNaissance().toString().split(" ")[0]);
			else this.prStat.setNull(1, 0);
			this.prStat.setString(2, etu.getPaysNaissance());
			this.prStat.setString(3, etu.getLieuNaissance());
			this.prStat.setString(4, etu.getNumNational());
			this.prStat.setString(5, etu.getNationalite());
			this.prStat.setString(6, etu.getNumBanque());
			this.prStat.setInt(7, (etu.isSoutienSocial())?1:0);
			this.prStat.setString(8, etu.getEmplacementEcole());
			this.prStat.setString(9, etu.getRole());
			if(etu.getSec() != null)this.prStat.setInt(10, etu.getSec().getId());
			else this.prStat.setNull(10, 0);
			if(etu.getAdr() != null)this.prStat.setInt(11, etu.getAdr().getId());
			else this.prStat.setNull(11, 0);
			this.prStat.setInt(12, etu.getId());
			change = (this.prStat.executeUpdate() > 0)?true:false;
			connection.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e.getMessage());
		}finally{
			try{
				connection.setAutoCommit(true);
			}catch(Exception e){
				System.out.println("Erreur, auto comit (Etudiant)");
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
	public boolean delete(Etudiant etu) {
		String query = "DELETE FROM etudiant WHERE id=?";
		boolean change = false;
		try{
			this.prStat = connection.prepareStatement(query);
			this.prStat.setInt(1, etu.getId());
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
