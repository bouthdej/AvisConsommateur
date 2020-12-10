package projet.rest.data.services;

import java.util.List;

import projet.rest.data.models.AvisEntity;
import projet.rest.data.models.CategoryEntity;
import projet.rest.data.models.ProductEntity;
import projet.rest.data.models.UserEntity;




public interface UserService {
	List<UserEntity> getAllUserEntity();
	UserEntity getUserEntityById(long id);
	UserEntity createUserEntity(UserEntity entity );
	UserEntity modifyUserEntity(long id,UserEntity newentity);
	UserEntity deleteUserEntity(long id);
	//List<AvisEntity>	getAllAvisEntity();
	
	
	/**** category  *****/ 
	List<CategoryEntity> getAllCategories() ; 
	CategoryEntity getCategoryById ( int id) ; 
	CategoryEntity createCategory ( CategoryEntity catEntity) ; 
	CategoryEntity modifyCategory ( int id , CategoryEntity newEntityCat);
	CategoryEntity deleteCategoryEntity( int id ) ; 
	
	/**produit**/
	List<ProductEntity> getAllProduct() ; 
	ProductEntity getProductById( int id) ; 
	ProductEntity createProduct ( ProductEntity productEntity) ; 
	ProductEntity modifyProduct ( int id , ProductEntity newEntityProd);
	ProductEntity deleteProductEntity( int id ) ; 
	Float rate( int id);
	
	/**avis**/
	public ProductEntity createAvis(int id, AvisEntity a ) ;
	ProductEntity deleteAvisEntity( int id ) ; 
	List<AvisEntity> getAllAvisEntity(int id);
	

	
	
	
}
