package projet.rest.data.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projet.rest.data.models.AvisEntity;

import projet.rest.data.models.CategoryEntity;
import projet.rest.data.models.ProductEntity;
import projet.rest.data.models.UserEntity;
import projet.rest.data.repositories.AvisRepository;
import projet.rest.data.repositories.CategoryRepository;
import projet.rest.data.repositories.ProductRepository;
import projet.rest.data.repositories.UserRepository;




@Service
public class UserServiceImp implements UserService {

	private UserRepository reposUser;
    private CategoryRepository reposCat ; 
    private ProductRepository reposProduct ; 
    private AvisRepository reposAvis ; 
	
	
	@Autowired
	public UserServiceImp(UserRepository reposUser, CategoryRepository reposCat, ProductRepository reposProduit,
			AvisRepository reposAvis) {
		super();
		this.reposUser = reposUser;
		this.reposCat = reposCat;
		this.reposProduct = reposProduit;
		this.reposAvis = reposAvis;
	}
	
	@Override
	public List<UserEntity> getAllUserEntity() {
		return reposUser.findAll();
	}
	


	@Override
	public UserEntity getUserEntityById(long id) {
		Optional<UserEntity> opt = reposUser.findById(id);
		
		UserEntity entity;
		if (opt.isPresent())
			entity = opt.get();
		else
			throw new NoSuchElementException("User with id : "+id+" is not found");
		return entity; 
	}

	@Override
	public UserEntity createUserEntity(UserEntity entity) {
		
				UserEntity user = reposUser.save(entity);
								
				return user;
	}

	@Override
	public UserEntity modifyUserEntity(long id, UserEntity newUser) {
		UserEntity oldUser = this.getUserEntityById(id);
		if (newUser.getNom() != null)	
			oldUser.setNom(newUser.getNom());
		if (newUser.getPrenom() != null)
			oldUser.setPrenom(newUser.getPrenom());
		if (newUser.getDateOfBirth() != null)
			oldUser.setDateOfBirth(newUser.getDateOfBirth());
		if (newUser.getPermission() != null)
			oldUser.setPermission(newUser.getPermission());
		
		return reposUser.save(oldUser); // watwat
	}

	@Override
	public UserEntity deleteUserEntity(long id) {
		UserEntity entity = this.getUserEntityById(id);
		reposUser.deleteById(id);
		return entity;
	}
    /************  Category *****************/
	@Override
	public List<CategoryEntity> getAllCategories() {
		
		return reposCat.findAll() ;
	}

	@Override
	public CategoryEntity getCategoryById(int id) {
	Optional<CategoryEntity> opt = reposCat.findById(id);
		
		CategoryEntity catEntity;
		if (opt.isPresent())
			catEntity = opt.get();
		else
			throw new NoSuchElementException("Categorie with this id is not found");
		return catEntity; 
	}
	

	@Override
	public CategoryEntity createCategory(CategoryEntity catEntity) {
	
		CategoryEntity category = reposCat.save(catEntity) ; 
		
		return category;
	}

	@Override
	public CategoryEntity modifyCategory(int id, CategoryEntity newEntityCat) {
	   CategoryEntity oldcategory = this.getCategoryById(id);
       if (newEntityCat.getTitle() != null )
    	   oldcategory.setTitle(newEntityCat.getTitle()) ; 
		return reposCat.save(oldcategory);
	}

	@Override
	public CategoryEntity deleteCategoryEntity(int id) {
	CategoryEntity catEntity = this.getCategoryById(id);
		reposCat.deleteById(id);
		return catEntity;

		 
	}

	@Override
	public List<ProductEntity> getAllProduct() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductEntity getProductById(int id) {
Optional<ProductEntity> opt = reposProduct.findById(id);
		
		ProductEntity productentity;
		if (opt.isPresent())
			productentity = opt.get();
		else
			throw new NoSuchElementException("Product with this id is not found with this id is not found");
		return productentity; 
	}



	@Override
	public ProductEntity modifyProduct(int id, ProductEntity newEntityProd) {
		  ProductEntity oldproduct = this.getProductById(id);
	       if (newEntityProd.getMarque()!= null )
	    	   oldproduct.setMarque(newEntityProd.getMarque());
			
		
	      if (newEntityProd.getNom()!= null )
  	      oldproduct.setNom(newEntityProd.getNom());
		   return reposProduct.save(oldproduct);
		
	}

	@Override
	public ProductEntity deleteProductEntity(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	@Override
	public List<AvisEntity> getAllAvisEntity(int id) {
		Optional<ProductEntity> opt = reposProduct.findById(id);
		
		ProductEntity product;
		if (opt.isPresent()) {

			 product = opt.get();
			 return product.getAvis();
		}
		else
			throw new NoSuchElementException("User with id : "+id+" is not found"); 
	}
	@Override
	public ProductEntity createProduct(ProductEntity productEntity) {
		ProductEntity product=reposProduct.save(productEntity);	
				return product;
	}

	@Override
	public ProductEntity createAvis(int id, AvisEntity a ) {
		ProductEntity productEntity = getProductById(id);
		a.setProduct(productEntity);
		reposAvis.save(a);
		
		return productEntity;
	
	}


	@Override
	public ProductEntity deleteAvisEntity(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Float rate(int id) {
		ProductEntity p = this.getProductById(id);
		float rate=0;
		int i=0;
		//if (p.getCategory().equalsIgnoreCase("smartphone"))
			for(AvisEntity a : this.getAllAvisEntity(id)) {
				rate=rate+((a.getCameraquality()+a.getDesign()+a.getQalityPrice()+a.getNote())/4);
				i++;
				
			}
			
		return rate/i;
		
	}
}
