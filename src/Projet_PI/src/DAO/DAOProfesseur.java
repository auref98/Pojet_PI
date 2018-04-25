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
import java.util.ArrayList;


import Bean.Professeur;
import Bean.Representant;
import Bean.Section;

public class DAOProfesseur extends DAO<Professeur>{

	PreparedStatement prStat;
	ResultSet resSet;
	
	@Override
	public Professeur find(int id) {
		// TODO Auto-generated method stub
		Professeur prof = null;
		String sql = "SELECT * FROM Professeur WHERE id = ?";
		try {
			this.prStat = connection.prepareStatement(sql);
			this.prStat.setInt(1, id);
			this.resSet = this.prStat.executeQuery();
			if(this.resSet.next()){
				Representant rep = new DAORepresentant().find(id);
				prof = new Professeur(rep.getFirstName(),rep.getLastName(),rep.getPhone(),rep.getMail(),rep.getMatricule(),id,this.resSet.getInt("nbParticipations"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally{
			try {
				this.resSet.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			try{
				this.prStat.close();
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		return prof;
	}
	
	public Professeur findProf(String mail,String password){
		Representant repr = new DAORepresentant().findRepr(mail, password);
		if(repr != null)return this.find(repr.getId());
		return null;
	}
	
	public ArrayList<Section> findRelais(int id){
		ArrayList<Section> sect = new ArrayList<Section>();
		String sql = "SELECT id FROM section WHERE refrelais = ?";
		try {
			this.prStat = connection.prepareStatement(sql);
			this.prStat.setInt(1, id);
			this.resSet = this.prStat.executeQuery();
			while(this.resSet.next()){
				sect.add(new DAOSection().find(this.resSet.getInt("id")));
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
		return sect;
	}
	
	public ArrayList<Section> findEnseigne(int id){
		ArrayList<Section> sect = new ArrayList<Section>();
		String sql = "SELECT * FROM enseigne WHERE refprof = ?";
		try {
			this.prStat = connection.prepareStatement(sql);
			this.prStat.setInt(1, id);
			this.resSet = this.prStat.executeQuery();
			while(this.resSet.next()){
				sect.add( new DAOSection().find(this.resSet.getInt("refsect")));
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
		return sect;
	}

	@Override
	public boolean create(Professeur prof) {
		// TODO Auto-generated method stub
		Representant repr = new Representant(prof.getId(),
											prof.getLastName(),
											prof.getFirstName(),
											prof.getPhone(),
											prof.getMail(),
											prof.getMatricule());
		if(!new DAORepresentant().create(repr))return false;
		boolean change = false;
		String sql = "INSERT INTO professeur (id,nbParticipations) VALUES (?,?)";
		try {
			this.prStat = connection.prepareStatement(sql);
			this.prStat.setInt(1, repr.getId());
			this.prStat.setInt(2, prof.getNbParticipations());
			change = (this.prStat.executeUpdate()>0)?true: false;
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
		
		sql = "INSERT INTO enseigne (refprof,refsect) VALUES(?,?)";
		if(prof.getEnseigne() != null){
			try{
				this.prStat = connection.prepareStatement(sql);
				for(Section enseigne : prof.getEnseigne()){
					try{
						this.prStat.setInt(1, repr.getId());
						this.prStat.setInt(2, enseigne.getId());
						this.prStat.executeUpdate();
					}catch(Exception e){
						System.out.println(e.getMessage());
					}
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				try {
					this.prStat.close();
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
			}
		}
		return change;
	}
	
	public boolean addEnseigne(int idprof,int idSection){
		boolean change = false;
		String sql = "INSERT INTO enseigne (refprof,refsect) VALUES(?,?)";
		try {
			this.prStat = connection.prepareStatement(sql);
			this.prStat.setInt(1, idprof);
			this.prStat.setInt(2, idSection);
			change = (this.prStat.executeUpdate()>0)?true: false;
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

	@Override
	public boolean update(Professeur prof) {
		// TODO Auto-generated method stub
		boolean change = false;
		String sql = "UPDATE professeur SET nbParticipant = ? WHERE id = ?";
		try {
			this.prStat = connection.prepareStatement(sql);
			this.prStat.setInt(1, prof.getNbParticipations());
			this.prStat.setInt(2, prof.getId());
			change = (this.prStat.executeUpdate()>0)?true: false;
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
	
	@Override
	public boolean delete(Professeur prof) {
		// TODO Auto-generated method stub
		String query = "DELETE FROM professeur WHERE id=?";
		boolean change = false;
		try{
			this.prStat = connection.prepareStatement(query);
			this.prStat.setInt(1, prof.getId());
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
	
	public boolean removeEnseigne(int idProf,int idSection){
		boolean change = false;
		String sql = "DELETE FROM enseigne WHERE refsect = ? && refprof = ?";
		try{
			this.prStat = connection.prepareStatement(sql);
			this.prStat.setInt(1, idSection);
			this.prStat.setInt(2, idProf);
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
