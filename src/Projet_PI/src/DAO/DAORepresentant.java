/**
 * 
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Bean.Representant;

/**
 * @author Aurelien
 *
 */
public class DAORepresentant extends DAO<Representant>{
	PreparedStatement prStat;
	ResultSet resSet;
	@Override
	public Representant find(int id) {
		// TODO Auto-generated method stub
		//C'est une class abstact donc on ne peux pas renvoyer d'objet Representant
		return null;
	}

	/**
	 * Fonction create du representant
	 * @param lastName
	 * @param firstName
	 * @param phone
	 * @param mail
	 * @param matricule
	 * @return true s'il y a eu des modifications.
	 */
	public boolean createRepresentant(String lastName,String firstName,String phone,String mail,String matricule){
		boolean change = false;
		String sql = "INSERT INTO representant(lastName, firstName, phone, mail, matricule) VALUES (?,?,?,?,?)";
		try {
			this.prStat = connection.prepareStatement(sql);
			this.prStat.setString(1, lastName);
			this.prStat.setString(2, firstName);
			this.prStat.setString(3, phone);
			this.prStat.setString(4, mail);
			this.prStat.setString(5, matricule);
			change = (this.prStat.executeUpdate()>0)?true:false;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally{
			try {
				this.prStat.close();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
		}
		return change;
	}
	@Override
	public boolean create(Representant object) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * vas faire une UPDATE du representant, tout les parrametre doivent etre initialiser.
	 * @param id
	 * @param lastName
	 * @param firstName
	 * @param phone
	 * @param mail
	 * @param matricule
	 * @return true si l'update a ete effectuer.
	 */
	public boolean updateRepresentant(int id,String lastName,String firstName,String phone,String mail, String matricule){
		boolean change = false;
		String sql = "UPDATE representant SET lastname = ?,"
											+ "firstname = ?,"
											+ "phone = ?,"
											+ "mail = ?,"
											+ "matricule = ? WHERE id = ?";
		try {
			this.prStat = connection.prepareStatement(sql);
			this.prStat.setString(1, lastName);
			this.prStat.setString(2, firstName);
			this.prStat.setString(3, phone);
			this.prStat.setString(4, mail);
			this.prStat.setString(5, matricule);
			this.prStat.setInt(6, id);
			change = (this.prStat.executeUpdate()>0)?true:false;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally{
			try {
				this.prStat.close();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
		}
		return change;
	}
	
	@Override
	public boolean update(Representant object) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Delete un representant correspondant a l'id.
	 * @param id
	 * @return true s'il y a eu des modification.
	 */
	public boolean delete(int id){
		boolean change = false;
		String sql = "DELETE FROM representant WHERE id = ?";
		try {
			this.prStat = connection.prepareStatement(sql);
			this.prStat.setInt(1, id);
			change = (this.prStat.executeUpdate()>0)?true:false;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally{
			try{
				this.prStat.close();
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		return change;
	}
	@Override
	public boolean delete(Representant object) {
		// TODO Auto-generated method stub
		return false;
	}
}
