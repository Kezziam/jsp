package ma.gov.pfe.modeles;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="ROLES")
public class Role {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	public Role() {		}

	public Role(Long id) {
		super();
		this.id = id;
		}

	
	public Role(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	

	@Override
	public String toString() {
		return "Role [name=" + name + "]";
	}

	public Long getId() {
		return id;
		}
	
	public void setId(Long id) {
		this.id = id;
		}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
