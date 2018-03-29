/*
 * NamingException(Aurélien, Killian, Robin, Louis, Christophe)
 */

package DAO;

import java.sql.*;

public abstract class DAO<T>
	{
	public Connection connection = ConnectionOracle.getInstance();
	
	public abstract T find(String champ, String value);
	public abstract boolean create(T object);
	public abstract boolean update(T object);
	public abstract boolean delete(T object);
	
	}
