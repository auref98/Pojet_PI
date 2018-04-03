/*
 * NamingException(Aurélien, Killian, Robin, Louis, Christophe)
 */

/**
 * @author ludovic
 */

package DAO;

import java.sql.*;
import java.util.*;
import java.util.Date;

import Bean.Adresse;
import Bean.Etudiant;
import Bean.Evenement;

public class DAOAdresse extends DAO<Adresse>
{
	//Return null en cas d'érreur ou si aucune ligne n'a été trouvé
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
			System.out.println("Erreur: find failed !");
			return null;
		}
	}
	
	//Return null en cas d'érreur ou si aucune ligne n'a été trouvé
	public ArrayList<Evenement> findListeEvent(int idAddr)
	{
		ArrayList<Evenement> listeEvent = new ArrayList<Evenement>();
		String query = "select * from evenement where REFADDR = ?";
		PreparedStatement ps;
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, idAddr);
			ResultSet resultSet = ps.executeQuery(query);
			
			if(resultSet.next() == false) throw new SQLException();
			do
			{
				int id = resultSet.getInt("id");
				String nom = resultSet.getString("nom");
				int nbParticipantRequis = resultSet.getInt("nbParticipantRequis");
				String description = resultSet.getString("description");
				String image = resultSet.getString("image");
				
				listeEvent.add(new Evenement(id, nom, nbParticipantRequis, description, image));
				
			} while(resultSet.next());
			
			ps.close();
			return listeEvent;
		}
		catch (SQLException ex)
		{
			System.out.println("Erreur: findListeEvent failed !");
			return null;
		}
	}
	
	public ArrayList<Etudiant> findListeEtud(int idAddr)
	{
		ArrayList<Etudiant> listeEtud = new ArrayList<Etudiant>();
		String query = "select * from etudiant where REFADDR = ?";
		PreparedStatement ps;
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, idAddr);
			ResultSet resultSet = ps.executeQuery(query);
			
			if(resultSet.next() == false) throw new SQLException();
			do
			{
				int id = resultSet.getInt("id");
				java.util.Date dateNaissance = resultSet.getDate("dateNaissance");
				String paysNaissance = resultSet.getString("paysNaissance");
				String lieuNaissance = resultSet.getString("lieuNaissance");
				long numNational = resultSet.getLong("numNational");
				String nationalite = resultSet.getString("nationalite");
				String numBanque = resultSet.getString("numbanque");
				boolean soutienSocial = (resultSet.getInt("soutienSocial") == 1) ? true : false;
				String emplacementEcole = resultSet.getString("emplacementEcole");
				String image = resultSet.getString("image");
				
				listeEvent.add(new Evenement(id, nom, nbParticipantRequis, description, image));
				
			} while(resultSet.next());
			
			ps.close();
			return listeEvent;
		}
		catch (SQLException ex)
		{
			System.out.println("Erreur: findListeEvent failed !");
			return null;
		}
	}
	
	@Override
	public boolean create(Adresse object)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Adresse object)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Adresse object)
	{
		// TODO Auto-generated method stub
		return false;
	}
	
}
