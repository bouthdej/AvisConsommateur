package projet.rest.data.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor

public  class AvisEntity {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int id;
	private int nblike;
	private int nbdislike;
	private float note;
	@JsonIgnore
	@ManyToOne
	private ProductEntity product;

	private float QalityPrice;
	private float nbtours ;
	private float cameraquality;
	private float durability;
	private float design ;
	private float comfort ;
	
	
}
