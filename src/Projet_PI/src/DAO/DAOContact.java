/**
 * 
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import Bean.Contact;
import Bean.Evenement;

/**
 * @author Aurelien
 *
 */
public class DAOContact extends DAO<Contact>{
	PreparedStatement prStat;
	ResultSet resSet;

	@Override
	public Contact find(int id) {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM contact where id = ?";
		Contact cont = null;
		try{
			this.prStat = connection.prepareStatement(query);
			this.prStat.setInt(1, id);
			this.resSet = this.prStat.executeQuery();
			if(this.resSet.next()){
				Evenement eve = new DAOEvenement.find(this.resSet.getInt("REFEVEN"));
				cont = new Contact(this.resSet.getString("MAIL"),eve);
				cont.setId(id);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			try {
				resSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			try {
				prStat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
		return cont;
	}

	/**
	 * Recherche dans la BD la liste de contact relié a un evenement
	 * @param id
	 * @return une liste de contact qui est relié à l'id de l'evenement.
	 */
	public LinkedList<Contact> findAllEve(int id){
		LinkedList<Contact> cont = null;
		String sql = "SELECT * FROM contact WHERE refeven = ?";
		try{
			this.prStat = connection.prepareStatement(sql);
			this.prStat.setInt(1, id);
			this.resSet = this.prStat.executeQuery();
			while(this.resSet.next()){
				cont.add(new Contact(this.resSet.getInt("id"),this.resSet.getString("MAIL")));
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			try {
				resSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			try {
				prStat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
		return cont;
	}
	
	@Override
	public boolean create(Contact cont) {
		// TODO Auto-generated method stub
		String sql = "INSET INTO contact (mail,refeven) VALUES (?,?)";
		boolean change = false;
		try {
			this.prStat = connection.prepareStatement(sql);
			this.prStat.setString(1, cont.getMail());
			this.prStat.setInt(2, cont.getEve().getId());
			change = (this.prStat.executeUpdate()>0)? true:false;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally{
			try {
				this.prStat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
		return change;
	}

	@Override
	public boolean update(Contact cont) {
		// TODO Auto-generated method stub
		boolean change = false;
		String sql = "UPDATE contact SET mail = ? WHERE id = ?";
		try {
			this.prStat = connection.prepareStatement(sql);
			this.prStat.setString(1, cont.getMail());
			change = (this.prStat.executeUpdate()>0)?true:false;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally{
			try {
				prStat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
		return change;
	}

	@Override
	public boolean delete(Contact cont) {
		// TODO Auto-generated method stub
		boolean change = false;
		String sql = "DELETE FROM contact where id = ?";
		try {
			this.prStat = connection.prepareStatement(sql);
			this.prStat.setInt(1, cont.getId());
			change = (this.prStat.executeUpdate()>0)?true:false;
		} catch (Exception e) {
			// TODO: handle exception
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
