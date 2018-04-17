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

import java.sql.*;

public abstract class DAO<T>
	{
	public Connection connection = ConnectionOracle.getInstance();
	
	public abstract T find(int id);
	public abstract boolean create(T object);
	public abstract boolean update(T object);
	public abstract boolean delete(T object);
	
	}
