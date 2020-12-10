package projet.rest.data.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table ( name = "Product" )
public class ProductEntity {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)  
private int idp;
private String nom;
private String marque;
@OneToMany  (mappedBy = "product")
private List<AvisEntity> avis = new ArrayList();
@Column(nullable = false)
private String category;

private float rate;
}
