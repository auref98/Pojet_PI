/**
 * 
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import Bean.Contact;
import Bean.Evenement;
import Bean.Section;

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
				Evenement eve = new Evenement();
				eve.setId(this.resSet.getInt("REFEVEN"));
				cont = new Contact(this.resSet.getInt("ID"),this.resSet.getString("MAIL"),eve);
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

	public ArrayList<Section> findAvecSection(int id){
		ArrayList<Section> section = new ArrayList<Section>();
		String sql = "SELECT * FROM interesse WHERE id = ?";
		int tab[] = null;
		try {
			this.prStat = connection.prepareStatement(sql);
			this.prStat.setInt(1,id);
			this.resSet = this.prStat.executeQuery();
			tab = new int[this.resSet.getFetchSize()];
			int i = 0;
			while(this.resSet.next() && i < tab.length){
				tab[i] = this.resSet.getInt("refsect");
				i++;
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
		DAOSection daoSec = new DAOSection();
		for(int i = 0; i < tab.length;i++){
			section.add(daoSec.find(tab[i]));
		}
		return section;
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
