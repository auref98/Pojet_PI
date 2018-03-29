package Class_PI;

import java.sql.*;

public abstract class DAO<T>
	{
	public Connection connection = ConnectionOracle.getInstance();
	
	public abstract T find(String id);
	public abstract boolean create(T object);
	public abstract boolean update(T object);
	public abstract boolean delete(T object);
	}
