package it.uniroma3.persistence;

import java.util.List;
import it.uniroma3.model.Gruppo;

public interface GruppoDao {
	
	public void save(Gruppo gruppo);  // Create
	public Gruppo findByPrimaryKey(Long id);     // Retrieve
	public List<Gruppo> findAll();       
	public void update(Gruppo gruppo); //Update
	public void delete(Gruppo gruppo); //Delete
}
