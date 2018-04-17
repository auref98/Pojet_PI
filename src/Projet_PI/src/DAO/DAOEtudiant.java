/**
 * 
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import Bean.Etudiant;
import Bean.Representant;

/**
 * @author Aurelien
 *
 */
public class DAOEtudiant extends DAO<Etudiant>{

	PreparedStatement prStat;
	ResultSet resSet;
	
	@Override
	public Etudiant find(int id) {
		// TODO Auto-generated method stub
		Etudiant etu = null;
		String sql = "SELECT * FROM etudiant WHERE id = ?";
		try {
			this.prStat = connection.prepareStatement(sql);
			this.prStat.setInt(1, id);
			this.resSet = this.prStat.executeQuery();
			if(this.resSet.next()){
				Representant rep = new DAORepresentant().find(this.resSet.getInt("REFREPR"));
				etu = new Etudiant(rep.getLastname(),
									rep.getFirstname(),
									rep.getPhone(),
									rep.getMail(),
									rep.getMatricule(),
									this.resSet.getInt("ID"),
									new LocalDate(this.resSet.getString("DATENAISSANCE")),
									this.resSet.getString("PAYSNAISSANCE"),
									this.resSet.getString("LIEUNAISSANCE"),
									this.resSet.getString("NUMNATIONAL"),
									this.resSet.getString("NATIONALITE"),
									this.resSet.getString("NUMBANQUE"),
									(this.resSet.getInt("SOUTIENSOCIAL")==1)?true:false,
									this.resSet.getString("EMPLACEMENTECOLE"),
									this.resSet.getString("ROLE"));
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
			try{
				this.prStat.close();
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		return etu;
	}

	@Override
	public boolean create(Etudiant etu) {
		// TODO Auto-generated method stub
		new DAORepresentant().create(etu.getRepr());
		boolean change = false;
		String sql = "INSERT INTO etudiant (datenaissance,"
											+ "paysnaissance,"
											+ "lieunaissance,"
											+ "numnational,"
											+ "natianalite,"
											+ "numbanque,"
											+ "soutiensocial,"
											+ "emplacementecole,"
											+ "role,"
											+ "refrepr,"
											+ "refsect,"
											+ "refaddr)"
											+ "VALUES( to_date('?','yyyy-mm-dd'),?, ?,?,?,?,?,?,?,? ?,?";
		try {
			this.prStat = connection.prepareStatement(sql);
			this.prStat.setString(1, etu.getDateNaissance().toString());
			this.prStat.setString(2, etu.getPaysNaissance());
			this.prStat.setString(3, etu.getLieuNaissance());
			this.prStat.setString(4, etu.getNumNational());
			this.prStat.setString(5, etu.getNationalite());
			this.prStat.setString(6, etu.getNumBanque());
			this.prStat.setInt(7, (etu.isSoutienSocial())?1:0);
			this.prStat.setString(8, etu.getEmplacementEcole());
			this.prStat.setString(9, etu.getRole());
			this.prStat.setInt(10, etu.get);//representant
			this.prStat.setInt(11, etu.getSec().getId());
			this.prStat.setInt(12, etu.getAdr().getId());
			change = (this.prStat.executeUpdate() > 0)?true:false;
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
	public boolean update(Etudiant etu) {
		// TODO Auto-generated method stub
		boolean change = false;
		String sql = "UPDATE etudiant SET datenaissance = ?,"
											+ "paysnaissance = ?,"
											+ "lieunaissance = ?,"
											+ "numnational = ?,"
											+ "natianalite = ?,"
											+ "numbanque = ?,"
											+ "soutiensocial = ?,"
											+ "emplacementecole = ?,"
											+ "role = ?,"
											+ "refrepr = ?,"
											+ "refsect = ?,"
											+ "refaddr = ? WHERE id = ?";
		try {
			this.prStat = connection.prepareStatement(sql);
			this.prStat.setString(1, etu.getDateNaissance().toString());
			this.prStat.setString(2, etu.getPaysNaissance());
			this.prStat.setString(3, etu.getLieuNaissance());
			this.prStat.setString(4, etu.getNumNational());
			this.prStat.setString(5, etu.getNationalite());
			this.prStat.setString(6, etu.getNumBanque());
			this.prStat.setInt(7, (etu.isSoutienSocial())?1:0);
			this.prStat.setString(8, etu.getEmplacementEcole());
			this.prStat.setString(9, etu.getRole());
			this.prStat.setInt(10, etu.get);//representant
			this.prStat.setInt(11, etu.getSec().getId());
			this.prStat.setInt(12, etu.getAdr().getId());
			change = (this.prStat.executeUpdate() > 0)?true:false;
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
	public boolean delete(Etudiant etu) {
		String query = "DELETE FROM etudiant WHERE id=?";
		boolean change = false;
		try{
			this.prStat = connection.prepareStatement(query);
			this.prStat.setInt(1, etu.getId());
			change = (this.prStat.executeUpdate()>0)?true:false;
		}catch(Exception e){
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
