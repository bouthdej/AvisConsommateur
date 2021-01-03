package projet.rest.data.endpoints;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
	@GetMapping("/deluser")
	public String DelUsers() {
	    return "admin/deluseradmin";
	}
	@GetMapping("/upduser")
	public String UpdUsers() {
	    return "admin/upduseradmin";
	}
	
	/*Categories*/
	@GetMapping("/categorielist")
	public String AllCategoris() {
	    return "admin/categorielistadmin";
	}
	@GetMapping("/addcategorie")
	public String AddCategories() {
	    return "admin/addcategorieadmin";
	}
	@GetMapping("/delcategorie")
	public String DelCategories() {
	    return "admin/delcategorieadmin";
	}
	
	/*Products*/
	@GetMapping("/productlist")
	public String AllProducts() {
	    return "admin/productlistadmin";
	}
	@GetMapping("/addproductadmin")
	public String AddProducts(Model model) {
		ProductEntity p = new ProductEntity ();
		model.addAttribute("product",p);
		List<CategoryEntity> categories =  service.getAllCategories();
		model.addAttribute("categories",categories);
		CategoryEntity cat = new CategoryEntity ();
		model.addAttribute("category",cat);
	    return "admin/addproductadmin";
	}
	@PostMapping("/addproductadmin")
	public String registerSuccess(@ModelAttribute("product") ProductEntity product) {
		service.createProduct(product);
		return "forms/productcreated";
	}
	
	@GetMapping("/delproduct")
	public String DelProducts() {
	    return "admin/delproductadmin";
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
