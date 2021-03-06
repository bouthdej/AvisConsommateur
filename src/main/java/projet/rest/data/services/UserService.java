package projet.rest.data.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import projet.rest.data.models.AvisEntity;
import projet.rest.data.models.CategoryEntity;
import projet.rest.data.models.ProductEntity;
import projet.rest.data.models.UserEntity;

public interface UserService {
    List<UserEntity> getAllUserEntity();
    UserEntity getUserEntityById(long id);
    UserEntity createUserEntity(UserEntity entity );
    void modifyUserEntity(long id,UserEntity newentity);
    UserEntity deleteUserEntity(long id);
    //List<AvisEntity>    getAllAvisEntity();
    
    
    /**** category  *****/ 
    List<CategoryEntity> getAllCategories() ; 
    CategoryEntity getCategoryById (int s) ; 
    CategoryEntity createCategory ( CategoryEntity catEntity) ; 
    
    CategoryEntity deleteCategoryEntity( int  id ) ; 
    
    /**produit**/
    List<ProductEntity> getAllProduct() ; 
    ProductEntity getProductById( int id) ; 
    ProductEntity createProduct(String cat ,String nom, String marque , String description , MultipartFile file, String Username )   ; 
    ProductEntity modifyProduct ( int id , ProductEntity newEntityProd);
    ProductEntity deleteProductEntity( int id ) ; 
    Float rate( int id);
    
    /**avis**/
    public ProductEntity createAvis(int id, AvisEntity a ) ;
    AvisEntity deleteAvisEntity( int id ) ; 
    List<AvisEntity> getAllAvisEntity(int id);
    List<AvisEntity> getAllReviews();
    AvisEntity getAvisById( int id) ; 
    AvisEntity modifyAvis( int id , AvisEntity newEntityAvis);
    public AvisEntity addLike(int id , long userid);
    public void DelTokenByIdUser(double i) ;
    //public AvisEntity addDisLike(int id , int userid);
}