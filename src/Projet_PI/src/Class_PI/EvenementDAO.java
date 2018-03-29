package Class_PI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;

import Class_PI.Evenement;

public class EvenementDAO extends DAO<Evenement>
	{
	public Connection connection = ConnectionOracle.getInstance();
	
	public  Evenement find(String nom)
		{
		LinkedList<Evenement> res = new LinkedList<Evenement>();
		String query = "SELECT * FROM EVENEMENT WHERE id = ?";
		PreparedStatement prStatement = null;
		ResultSet resultSet = null;
		try
			{
			prStatement = connection.prepareStatement(query);
			prStatement.setString(1, nom);
			resultSet = prStatement.executeQuery();
			Evenement evenement = null;		// A creer
			while(resultSet.next())
				{
				try
					{
					evenement = new Evenement	(
												resultSet.get
												);
					}
				catch(SQLException e)
					{
					System.out.println(e.getMessage());
					}
				evenement = new Evenement(resultSet.getString(1), resultSet.getString(2), LocalDate.parse(resultSet.getString(3)), section);
				} 
			}
		catch(SQLException e)
			{
			System.out.println(e.getMessage());
			}
		finally
			{
			try
				{
				if(resultSet != null) resultSet.close();
				}
			catch (SQLException e)
				{
				System.out.println(e.getMessage());
				}
			try
				{
				if(prStatement != null) prStatement.close();
				} 
			catch (SQLException e)
				{
				System.out.println(e.getMessage());
				}
			}
		return res;
		}
	@Override
	public boolean create(Evenement object) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean update(Evenement object) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean delete(Evenement object) {
		// TODO Auto-generated method stub
		return false;
	}
	}
	
	
	
	
