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

/**
 * @author ludovic
 */

package DAO;

import java.lang.reflect.Array;
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
		PreparedStatement ps = null;
		Section section = null;
		ResultSet resultSet = null;
		try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			resultSet = ps.executeQuery();
			
			if(resultSet.next() == false) throw new SQLException();
			
			String nom = resultSet.getString("nom");
			Professeur relais = new Professeur();
			relais.setId(resultSet.getInt("refRelais"));
			
			section = new Section(id, nom, relais);
		}
		catch (SQLException ex)
		{
			System.out.println("Erreur: findSection failed !");
			ex.printStackTrace();
		}
		finally
		{
			try{
				resultSet.close();
			}catch(Exception e){
				System.out.println("Erreur: fermeture de resultSet");
			}
			try
			{
				ps.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return section;
	}
	
	public ArrayList<Section> findAll(){
		ArrayList <Section> sects = new ArrayList<Section>();
		String sql = "SELECT * FROM section";
		PreparedStatement prStat = null;
		ResultSet resSet = null;
		try {
			prStat = connection.prepareStatement(sql);
			resSet = prStat.executeQuery();
			while(resSet.next()){
				sects.add(new Section(resSet.getInt("id"),resSet.getString("nom"),new DAOProfesseur().find(resSet.getInt("refRelais"))));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Erreur : findAll section");
		}finally{
			try {
				resSet.close();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Erreur : resSet impossible a fermer (findAll section)");
			}
			try {
				prStat.close();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Erreur : prStat impossible a fermer (findAll section)");
			}
		}
		return sects;
	}
	
	/**
	 * 
	 * @param	section est initialisé
	 * @return 	si une plusieurs lignes ont été trouvées dans la table
	 *  		Etudiant, elles seront retournées sous forme d'objet de type ArrayList
	 *  		sinon return null
	 *
	 */
	public ArrayList<Etudiant> findListeEtudiant(Section section)
	{
		String query = "select * from etudiant where refSect = ?";
		PreparedStatement ps = null;
		ArrayList<Etudiant> listeEtudiant = new ArrayList<Etudiant>();
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, section.getId());
			ResultSet resultSet = ps.executeQuery();
			
			if(resultSet.next() == false) throw new SQLException();
			
			do
			{
				int idEtudiant = resultSet.getInt("id");
				Etudiant etudiant = new DAOEtudiant().find(idEtudiant);
				etudiant.setSec(section);
				listeEtudiant.add(etudiant);
				
			} while(resultSet.next());
		}
		catch (SQLException ex)
		{
			System.out.println("Erreur: findListeEtudiant failed !");
			listeEtudiant = null;
		}
		finally
		{
			try
			{
				ps.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return listeEtudiant;
	}
	
	/**
	 * 
	 * @param	section est initialisé
	 * @return 	si une plusieurs lignes ont été trouvées dans la table
	 *  		contact, elles seront retournées sous forme d'objet de type ArrayList
	 *  		sinon return null
	 *
	 */
	public ArrayList<Contact> findListeContact(Section section)
	{
		String query = "select c.* from CONTACT c, SECTION s, INTERESSE i where c.id = i.REFCONTACT and s.id = i.REFSECT and s.id = ?";

		PreparedStatement ps = null;
		ArrayList<Contact> listeContact = new ArrayList<Contact>();
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, section.getId());
			ResultSet resultSet = ps.executeQuery();
			
			if(resultSet.next() == false) throw new SQLException();
			
			do
			{
				int idContact = resultSet.getInt("id");
				listeContact.add(new DAOContact().find(idContact));
				
			} while(resultSet.next());
		}
		catch (SQLException ex)
		{
			System.out.println("Erreur: findListeContact failed !");
			listeContact = null;
		}
		finally
		{
			try
			{
				ps.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return listeContact;
	}
	
	/**
	 * 
	 * @param	section est initialisé
	 * @return 	si une plusieurs lignes ont été trouvées dans la table
	 *  		professeur, elles seront retournées sous forme d'objet de type ArrayList
	 *  		sinon return null
	 *
	 */
	public ArrayList<Professeur> findListeProfesseur(Section section)
	{
		String query = "select p.* from PROFESSEUR p, SECTION s, ENSEIGNE e where p.id = e.REFPROF and s.id = e.REFSECT and s.id = ?";

		PreparedStatement ps = null;
		ArrayList<Professeur> listeProf = new ArrayList<Professeur>();
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, section.getId());
			ResultSet resultSet = ps.executeQuery();
			
			if(resultSet.next() == false) throw new SQLException();
			
			do
			{
				int idProf = resultSet.getInt("id");
				listeProf.add(new DAOProfesseur().find(idProf));
				
			} while(resultSet.next());
		}
		catch (SQLException ex)
		{
			System.out.println("Erreur: findListeProf failed !");
			listeProf = null;
		}
		finally
		{
			try
			{
				ps.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return listeProf;
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
		String query = "insert into section values(null, ?, ?)";
		PreparedStatement ps = null;
		boolean resultat = false;
		
		try
		{
			ps = connection.prepareStatement(query, new String[] {"id"});
			ps.setString(1, section.getNom());
			ps.setInt(2, section.getRelais().getId());
			
			if(ps.executeUpdate() == 0) throw new SQLException();
			
			ResultSet resultSet = ps.getGeneratedKeys();
			if(resultSet.next()) section.setId(resultSet.getInt(1));

			resultat = true;
		}
		catch (SQLException e)
		{
			System.out.println("Erreur: createSection failed !");
		}
		finally
		{
			try
			{
				ps.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return resultat;
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
		String query = "update section set NOM = ?, refRelais = ? where id = ?";
		PreparedStatement ps = null;
		boolean resultat = false;
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setString(1, section.getNom());
			ps.setInt(2, section.getRelais().getId());
			ps.setInt(3, section.getId());
			
			if(ps.executeUpdate() == 0) throw new SQLException();

			resultat = true;
		}
		catch (SQLException e)
		{
			System.out.println("Erreur: updateSection failed !");
		}
		finally
		{
			try
			{
				ps.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return resultat;
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
		PreparedStatement ps = null;
		boolean resultat = false;
		
		try
		{
			ps = connection.prepareStatement(query);
			ps.setInt(1, section.getId());
			
			if(ps.executeUpdate() == 0) throw new SQLException();
			
			resultat = true;
		}
		catch (SQLException e)
		{
			System.out.println("Erreur: deleteSection failed !");
		}
		finally
		{
			try
			{
				ps.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return resultat;
	}

}
