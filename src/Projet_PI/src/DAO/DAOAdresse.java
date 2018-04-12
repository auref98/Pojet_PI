/*
 * Haute �cole Robert Schuman - Libramont, annee scolaire 2017 - 2018
 * Informatique de geston, bloc 2	
 * 
 * Projet integre: realisation d'un logiciel de gestion des inscriptions � des evenements
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

/**
 * @author ludovic
 */

package DAO;

import java.sql.*;
import java.util.*;

import Bean.Adresse;
import Bean.Etudiant;
import Bean.Evenement;

public class DAOAdresse extends DAO<Adresse>
{
	/**
	 * 
	 * @param	id correspond � l'id d'une adresse
	 * @return 	si une ligne est trouv� dans la table
	 *  		adresse, elle est retourn�e sous forme d'objet
	 *  		sinon return null
	 *
	 */
	@Override
	public Adresse find(int id)
	{
		String query = "select * from adresse where id = ?";
		PreparedStatement ps;
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery(query);
			
			if(resultSet.next() == false) throw new SQLException();
			
			String localite = resultSet.getString("localite");
			int codePostal = resultSet.getInt("codePostal");
			String rue = resultSet.getString("rue");
			int numero = resultSet.getInt("numero");
			String boite = resultSet.getString("Boite");
			String pays = resultSet.getString("pays");
			ps.close();
			
			return new Adresse(id, localite, codePostal, rue, numero, boite, pays);
		}
		catch (SQLException ex)
		{
			System.out.println("Erreur: findAdr failed !");
			return null;
		}
	}
	
	/**
	 * 
	 * @param 	adr est initialis�
	 * @return 	true si l'objet adr � bien �t� ajout� dans la table adresse
	 * 			false si un probl�me � �t� rencontr�
	 *
	 */
	@Override
	public boolean create(Adresse adr)
	{
		String query = "insert into adresse values(null, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps;
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, adr.getCodePostal());
			ps.setString(2, adr.getLocalite());
			ps.setString(3, adr.getRue());
			ps.setInt(4, adr.getNumero());
			ps.setString(5, adr.getBoite());
			ps.setString(6, adr.getPays());
			
			if(ps.executeUpdate() == 0) throw new SQLException();
			
			ps.close();
			return true;
		}
		catch (SQLException e)
		{
			System.out.println("Erreur: createAdr failed !");
			return false;
		}
	}

	/**
	 * 
	 * @param 	adr est initialis�
	 * @return 	true si la ligne dans la table adresse � bien �t� mis � jour
	 * 			false si un probl�me � �t� rencontr�
	 *
	 */
	@Override
	public boolean update(Adresse adr)
	{
		String query = "update adresse set CODEPOSTAL = ?, LOCALITE = ?, RUE = ?, NUMERO = ?, BOITE = ?, PAYS = ? where id = ?";
		PreparedStatement ps;
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, adr.getCodePostal());
			ps.setString(2, adr.getLocalite());
			ps.setString(3, adr.getRue());
			ps.setInt(4, adr.getNumero());
			ps.setString(5, adr.getBoite());
			ps.setString(6, adr.getPays());
			ps.setInt(7, adr.getId());
			
			if(ps.executeUpdate() == 0) throw new SQLException();
			
			ps.close();
			return true;
		}
		catch (SQLException e)
		{
			System.out.println("Erreur: UpdateAdr failed !");
			return false;
		}
	}
	
	/**
	 * 
	 * @param 	adr est initialis�
	 * @return 	true si la ligne de la table adresse � bien �t� supprim�
	 * 			false si un probl�me � �t� rencontr�
	 *
	 */
	@Override
	public boolean delete(Adresse adr)
	{
		String query = "delete from adresse where id = ?";
		PreparedStatement ps;
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, adr.getId());
			
			if(ps.executeUpdate() == 0) throw new SQLException();
			
			ps.close();
			return true;
		}
		catch (SQLException e)
		{
			System.out.println("Erreur: deleteAdr failed !");
			return false;
		}
	}
}