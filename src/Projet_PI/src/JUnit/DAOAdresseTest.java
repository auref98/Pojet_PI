package JUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import Bean.Adresse;
import DAO.DAOAdresse;

class DAOAdresseTest
	{
	Adresse adr;
	Adresse fake;
	int id;
	
	@Before
	public void setUp() throws Exception
		{
		adr = new Adresse(0, "Libramont-Chevigny", 6800, "Rue de la Cité", 64 , "", "Belgique");
		fake = new Adresse(999, "", -6800, "", -64 , "", "");
		}
	
	@Test
	public void testCreate()
		{
		assertTrue(new DAOAdresse().create(adr));
		id = adr.getId();
		assertFalse(new DAOAdresse().create(adr));
		assertFalse(new DAOAdresse().create(fake));
		}

	@Test
	public void testFind()
		{
		assertEquals(adr, new DAOAdresse().find(id));
		assertEquals(null, new DAOAdresse().find(999));
		}
	
	@Test
	public void testUpdate()
		{
		adr.setRue("Rue Fontaine aux mûres");
		adr.setNumero(13);
		assertTrue(new DAOAdresse().update(adr));
		Adresse test = new DAOAdresse().find(id);
		assertEquals(13, test.getNumero());
		assertEquals("Rue Fontaine aux mûres", test.getRue());
		adr.setNumero(-13);
		assertFalse(new DAOAdresse().update(adr));
		}
	
	public void testDelete()
		{
		assertFalse(new DAOAdresse().delete(fake));
		assertTrue(new DAOAdresse().delete(adr));
		}
	
	}
