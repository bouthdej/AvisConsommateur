package projet.rest.data.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table (name="UserEntity") 
public class UserEntity {
	@Id //primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	private long id; //auto generé
	//@Column(name = "First Name",length = 50,nullable = false)
	private String nom;
	private String prenom;
	private LocalDate dateOfBirth;
	private String Permission;


}
	