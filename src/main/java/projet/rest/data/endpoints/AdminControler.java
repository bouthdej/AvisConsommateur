package projet.rest.data.endpoints;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import projet.rest.data.models.CategoryEntity;
import projet.rest.data.models.ProductEntity;
import projet.rest.data.models.UserEntity;
import projet.rest.data.services.UserService;

@Controller
@Data
@AllArgsConstructor
public class AdminControler {
	@Autowired
	UserService service ;
	@GetMapping("/admin")
	public String returnindexadmin() {
	    return "admin/indexadmin";
	}
	
	/*Users*/
	@GetMapping("/userlist")
	public String AllUsers(Model model) {
		List<UserEntity> users =  service.getAllUserEntity();
		model.addAttribute("users",users);
		UserEntity user = new UserEntity();
		model.addAttribute("user",user);
		
	    return "admin/userlistadmin";
	}
	@GetMapping("/adduser")
	public String AddUsers(Model model) {
		UserEntity user = new UserEntity();
		model.addAttribute("user",user);
	    return "admin/adduseradmin";
	}
	@PostMapping("/adduser")
	public String UserregisterSuccess(@ModelAttribute("user") UserEntity user, Model model) {
		service.createUserEntity(user);
		
		return this.AllUsers(model);
	}
	@GetMapping("/deluser/{id}")
	public String DelUsers(@PathVariable("id") Long id, Model model) {
	    service.deleteUserEntity(id);
		return this.AllUsers(model);
	}
	@GetMapping("/upduser")
	public String UpdUsers() {
	    return "admin/upduseradmin";
	}
	
	/*Categories*/
	@GetMapping("/categorielist")
	public String AllCategoris(Model model) {
		List<CategoryEntity> categories =  service.getAllCategories();
		model.addAttribute("categories",categories);
		CategoryEntity Category = new CategoryEntity() ;
		model.addAttribute("Category",Category);
	    return "admin/categorielistadmin";
	}
	@GetMapping("/addcategorie")
	public String AddCategories(Model model) {
		CategoryEntity c = new CategoryEntity() ;
		model.addAttribute("Category",c);
	    return "admin/addcategorieadmin";
	}

	@PostMapping("/addcategorie")
	public String registerSuccess(@ModelAttribute("Category") CategoryEntity Category, Model model) {
		service.createCategory(Category);
		return this.AllCategoris(model);
	}
	@GetMapping("/delcategorie/{id}")
	public String DelCategories(@PathVariable("id") int id, Model model) {
		service.deleteCategoryEntity(id);
		return this.AllCategoris(model);
	}
	
	/*Products*/
	@GetMapping("/productlist")
	public String AllProducts(Model model) {
		
			List<ProductEntity> products =  service.getAllProduct();
			model.addAttribute("products",products);
			ProductEntity product = new ProductEntity();
			model.addAttribute("product",product);
			
	    return "admin/productlistadmin";
	}
	
	
	@GetMapping("/addproductadmin")
	public String AddProducts(Model model) {
		List<CategoryEntity> categories = service.getAllCategories();
		model.addAttribute("categories",categories);
		CategoryEntity cat = new CategoryEntity ();
		model.addAttribute("category",cat);
	    return "admin/addproductadmin";
	}
	@PostMapping("/addproductadmin")
	public String registerSuccess( @RequestParam ("pcat") String catname , @RequestParam ("pname") String nom , @RequestParam("marque") String marque, @RequestParam("desc") String description , @RequestParam ("file") MultipartFile file , Model model ) {
		service.createProduct(catname,nom,marque,description,file);
		 return this.AllProducts(model);
		
	}
	@GetMapping("/delproduct/{id}")
	public String DelProducts(@PathVariable("id") int id, Model model) {
		service.deleteProductEntity(id);
		return this.AllProducts(model);
	}
	@GetMapping("/updproduct")
	public String UpdProducts() {
	    return "admin/updproductadmin";
	}
	
	/*Reviews*/
	@GetMapping("/reviewlist")
	public String AllReviews() {
	    return "admin/reviewlistadmin";
	}
	@GetMapping("/addreview")
	public String AddReviews() {
	    return "admin/addreviewadmin";
	}
	@GetMapping("/delreview")
	public String DelReviews() {
	    return "admin/delreviewadmin";
	}
	@GetMapping("/updreview")
	public String UpdReviews() {
	    return "admin/updreviewadmin";
	}
	@GetMapping("/reportreview")
	public String ReportReviews() {
	    return "admin/reportreviewadmin";
	}
	
}
