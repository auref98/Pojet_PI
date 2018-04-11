/*
 * NamingException(Aurélien, Killian, Robin, Louis, Christophe)
 */

/**
 * @author ludovic
 */

package DAO;

import java.sql.*;
import java.util.*;

import Bean.*;

public class DAOSection extends DAO<Section>
{

	/**
	 * 
	 * @param	id correspond à l'id d'une Section
	 * @return 	si une ligne est trouvé dans la table
	 *  		section, elle est retournée sous forme d'objet
	 *  		sinon return null
	 *
	 */
	@Override
	public Section find(int id)
	{
		String query = "select * from section where id = ?";
		PreparedStatement ps;
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery(query);
			
			if(resultSet.next() == false) throw new SQLException();
			
			String nom = resultSet.getString("nom");
			Professeur relais = new Professeur();
			relais.setId(resultSet.getInt("refRelais"));
			ps.close();
			
			return new Section(id, nom, relais);
		}
		catch (SQLException ex)
		{
			System.out.println("Erreur: findSection failed !");
			return null;
		}
	}
	
	/**
	 * 
	 * @param	evenement est initialisé
	 * @return 	si une plusieurs lignes ont été trouvées dans la table
	 *  		Etudiant, elles seront retournées sous forme d'objet de type ArrayList
	 *  		sinon return null
	 *
	 */
	public ArrayList<Etudiant> findListeEtudiant(Section section)
	{
		String query = "select * from etudiant where refSect = ?";
		PreparedStatement ps;
		ArrayList<Etudiant> listeEtudiant = new ArrayList<Etudiant>();
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, section.getId());
			ResultSet resultSet = ps.executeQuery(query);
			
			if(resultSet.next() == false) throw new SQLException();
			
			do
			{
				int idEtudiant = resultSet.getInt("id");
				Etudiant etudiant = new DAOEtudiant.find(idEtudiant);
				etudiant.setSec(section);
				listeEtudiant.add(etudiant);
				
			} while(resultSet.next());
			
			return listeEtudiant;
		}
		catch (SQLException ex)
		{
			System.out.println("Erreur: findListeEtudiant failed !");
			return null;
		}
	}
	
	/**
	 * 
	 * @param	Contact est initialisé
	 * @return 	si une plusieurs lignes ont été trouvées dans la table
	 *  		contact, elles seront retournées sous forme d'objet de type ArrayList
	 *  		sinon return null
	 *
	 */
	public ArrayList<Contact> findListeContact(Section section)
	{
		String query = "select c.* from CONTACT c, SECTION s, INTERESSE i where c.id = i.REFCONTACT and s.id = i.REFSECT and s.id = ?";

		PreparedStatement ps;
		ArrayList<Contact> listeContact = new ArrayList<Contact>();
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, section.getId());
			ResultSet resultSet = ps.executeQuery(query);
			
			if(resultSet.next() == false) throw new SQLException();
			
			do
			{
				int idContact = resultSet.getInt("id");
				listeContact.add(new DAOContact().find(idContact));
				
			} while(resultSet.next());
			
			return listeContact;
		}
		catch (SQLException ex)
		{
			System.out.println("Erreur: findListeContact failed !");
			return null;
		}
	}
	
	/**
	 * 
	 * @param	Prof est initialisé
	 * @return 	si une plusieurs lignes ont été trouvées dans la table
	 *  		professeur, elles seront retournées sous forme d'objet de type ArrayList
	 *  		sinon return null
	 *
	 */
	public ArrayList<Professeur> findListeProfesseur(Section section)
	{
		String query = "select p.* from PROFESSEUR p, SECTION s, ENSEIGNE e where p.id = e.REFPROF and s.id = e.REFSECT and s.id = ?";

		PreparedStatement ps;
		ArrayList<Professeur> listeProf = new ArrayList<Professeur>();
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, section.getId());
			ResultSet resultSet = ps.executeQuery(query);
			
			if(resultSet.next() == false) throw new SQLException();
			
			do
			{
				int idProf = resultSet.getInt("id");
				listeProf.add(new DAOProfesseur().find(idProf));
				
			} while(resultSet.next());
			
			return listeProf;
		}
		catch (SQLException ex)
		{
			System.out.println("Erreur: findListeProf failed !");
			return null;
		}
	}

	/**
	 * 
	 * @param	section est initialisé
	 * @return 	true si l'objet section à bien été ajouté dans la table section
	 * 			false si un problème à été rencontré
	 *
	 */
	@Override
	public boolean create(Section section)
	{
		String query = "insert into evenement values(null, ?, ?)";
		PreparedStatement ps;
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setString(1, section.getNom());
			ps.setInt(2, section.getRelais().getId());
			
			if(ps.executeUpdate() == 0) throw new SQLException();
			
			ps.close();
			return true;
		}
		catch (SQLException e)
		{
			System.out.println("Erreur: createSection failed !");
			return false;
		}
	}
	
	/**
	 * 
	 * @param 	section est initialisé
	 * @return 	true si la ligne dans la table section à bien été mis à jour
	 * 			false si un problème à été rencontré
	 *
	 */
	@Override
	public boolean update(Section section)
	{
		String query = "update evenement set NOM = ?, refRelais = ? where id = ?";
		PreparedStatement ps;
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setString(1, section.getNom());
			ps.setInt(2, section.getRelais().getId());
			ps.setInt(3, section.getId());
			
			if(ps.executeUpdate() == 0) throw new SQLException();
			
			ps.close();
			return true;
		}
		catch (SQLException e)
		{
			System.out.println("Erreur: updateSection failed !");
			return false;
		}
	}

	/**
	 * 
	 * @param 	section est initialisé
	 * @return 	true si la ligne de la table section à bien été supprimé
	 * 			false si un problème à été rencontré
	 *
	 */
	@Override
	public boolean delete(Section section)
	{
		String query = "delete from section where id = ?";
		PreparedStatement ps;
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, section.getId());
			
			if(ps.executeUpdate() == 0) throw new SQLException();
			
			ps.close();
			return true;
		}
		catch (SQLException e)
		{
			System.out.println("Erreur: deleteSection failed !");
			return false;
		}
	}

}
