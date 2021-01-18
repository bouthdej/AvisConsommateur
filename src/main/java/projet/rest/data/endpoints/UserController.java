package projet.rest.data.endpoints;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import projet.rest.data.models.AvisEntity;
import projet.rest.data.models.CategoryEntity;
import projet.rest.data.models.ProductEntity;
import projet.rest.data.services.SendEmailService;
import projet.rest.data.services.UserService;

@Controller
@Data
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService service ;
	
	@Autowired
	SendEmailService SendEmailService;
	
	@GetMapping("/add-categories")
	public String AddCategories(Model model) {
		CategoryEntity c = new CategoryEntity() ;
		model.addAttribute("Category",c);
	    return "user/add-categories";
	}
	@GetMapping("/forgotpass")
	public String forgotpass() {
	    return "/user/forgot-password";
	}
	@GetMapping("/home")
	public String userindex() {
	    return "/user/userindex";
	}

	
	@GetMapping("/Products")
	public String AllProducts(Model model ) { 
		List <ProductEntity> products = service.getAllProduct();
		ProductEntity product = new ProductEntity();
		model.addAttribute("product", product);
		model.addAttribute("products", products);
		List <CategoryEntity> categories = service.getAllCategories() ;
			CategoryEntity category = new CategoryEntity();
			model.addAttribute("category", category);
			if(categories.isEmpty()==false)
			{
			model.addAttribute("categories",categories);
		    return "user/products";
		}
			else {
				
		return "user/productsnotfounds";}
	}
	
	@GetMapping("/add-product")
	public String addProduct(Model model) {
		
		List<CategoryEntity> categories = service.getAllCategories();
		model.addAttribute("categories",categories);
		CategoryEntity cat = new CategoryEntity ();
		model.addAttribute("category",cat);
		
		return "user/add-product";
	}
	
	@PostMapping("/add-product")
	public String registerSuccess( @RequestParam ("pcat") String catname , @RequestParam ("pname") String nom , @RequestParam("marque") String marque, @RequestParam("desc") String description , @RequestParam ("file") MultipartFile file ) {
		service.createProduct(catname,nom,marque,description,file);
		return "user/Products";
	}
	@PostMapping("/add-categories")
	public String registerSuccess(@ModelAttribute("Category") CategoryEntity Category) {
		service.createCategory(Category);
		return "user/productcreated";
	}
	
	@GetMapping("/add-review/{id}")
	public String addReview(Model model,@PathVariable int id ) {
		AvisEntity a = new AvisEntity() ;
		model.addAttribute("avis",a);
		ProductEntity p = service.getProductById(id);
		model.addAttribute("product",p);
		
		return "user/add-review";
	}
	@PostMapping("/add-review/{id}")
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
			return "user/ProductNotFound";
		}
		p.setRate(service.rate(i));
		return "user/reviewcreated";
	}

	
	@GetMapping("/Contact")
	public String Contact(Model model) {
	    return "user/contact";
	}
	
	@PostMapping("/Contact")
	public String ContactMail(@RequestParam("email") String to,@RequestParam("message") String body, @RequestParam("subject") String topic,@RequestParam("name") String name) {
		System.out.println("Sending : "+to+" "+body+" "+topic);
		SendEmailService.sendEmail(to,body,"By "+name+": "+topic);
		System.out.println("Success : "+to+" "+body+" "+topic);
	    return "user/contact";
	}

}
