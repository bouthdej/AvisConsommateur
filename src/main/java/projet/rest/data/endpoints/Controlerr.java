package projet.rest.data.endpoints;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import projet.rest.data.models.AvisEntity;
import projet.rest.data.models.CategoryEntity;
import projet.rest.data.models.ProductEntity;
import projet.rest.data.models.UserEntity;
import projet.rest.data.services.UserService;


@Controller
@Data
@AllArgsConstructor
public class Controlerr {
	@Autowired
UserService service ;
	@GetMapping("/")
	public String returnindex() {
	    return "/index";
	}
/*@GetMapping("/smartphone")
	public String smartphone() {
	    return "categorie/smartphone/smartphone";
	}
@GetMapping("/washmachine")
public String Washingmaching() {
    return "categorie/washmachine/washingmachine";
}*/
@GetMapping("/Products")
	public String AllProducts() {
	    return "Other/products";
	}


@GetMapping("/Contact")
public String Contact() {
    return "Other/contact";
}

@GetMapping("/add-categories")
public String AddCategories(Model model) {
	CategoryEntity c = new CategoryEntity() ;
	model.addAttribute("Category",c);
    return "forms/add-categories";
}
@GetMapping("/Login")
public String login() {
    return "Other/login";
}

@GetMapping("/Sign-up")
public String SignUp(Model model) {
	UserEntity user = new UserEntity();
	model.addAttribute("user",user);
	return "Other/Sign-up";
}
@PostMapping("/Sign-up")
public String UserregisterSuccess(@ModelAttribute("user") UserEntity user) {
	service.createUserEntity(user);
	return "Other/login";
}
@GetMapping("/register")
public String register() {
    return "aa/register";
}
@GetMapping("/forgotpass")
public String forgotpass() {
    return "aa/forgot-password";
}
@GetMapping("/user")
public String userindex() {
    return "/user/userindex";
}

@GetMapping("/add-product")
public String addProduct(Model model ) {
	ProductEntity p = new ProductEntity ();
	model.addAttribute("product",p);
	List<CategoryEntity> categories =  service.getAllCategories();
	model.addAttribute("categories",categories);
	CategoryEntity cat = new CategoryEntity ();
	model.addAttribute("category",cat);
	return "forms/add-product";
}

@GetMapping("/add-review")
public String addReview(Model model ) {
	AvisEntity a = new AvisEntity() ;
	model.addAttribute("avis",a);
	ProductEntity p = new ProductEntity ();
	model.addAttribute("product",p);
	
	return "Reviews/add-review";
}
@PostMapping("/add-product")
public String registerSuccess(@ModelAttribute("product") ProductEntity product) {
	service.createProduct(product);
	return "forms/productcreated";
}
@PostMapping("/add-categories")
public String registerSuccess(@ModelAttribute("Category") CategoryEntity Category) {
	service.createCategory(Category);
	return "forms/productcreated";
}

@PostMapping("/add-review")
public String ReviewSuccess(@ModelAttribute("avis") AvisEntity a , @ModelAttribute("product") ProductEntity p) {
	/*service.createProduct(a);*/
	int i =p.getIdp();
	System.out.println(" i = "+i);
	try {
		//a.setProduct(p);
		service.createAvis(i, a);
		//a.toString();
	}
	catch(NoSuchElementException e) {
		return "Reviews/ProductNotFound";
	}
	p.setRate(service.rate(i));
	return "Reviews/reviewcreated";
}



}
