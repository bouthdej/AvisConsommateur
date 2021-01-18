package projet.rest.data.endpoints;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import projet.rest.data.services.SendEmailService;
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
	
	@Autowired
    SendEmailService SendEmailService;
	
	@GetMapping("/")
	public String returnindex() {
		Collection<? extends GrantedAuthority> authorities;
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    authorities = auth.getAuthorities();
	    String myRole = authorities.toArray()[0].toString();
	    String user = "USER";
	    
	    if (myRole.equals(user)) {
	        return "redirect:/user/home";
	    }
	    return "/index";
	}

    @GetMapping("/Products")
	public String AllProducts(Model model ) { 
    	Collection<? extends GrantedAuthority> authorities;
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    authorities = auth.getAuthorities();
	    String myRole = authorities.toArray()[0].toString();
	    String user = "USER";
	    
	    if (myRole.equals(user)) {
	        return "redirect:/user/Products";
	    }
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
		    return "Other/products";
		}
			else {
				
		return "Other/productsnotfounds";}
	}


	@GetMapping("/Contact")
	public String Contact(Model model) {
		Collection<? extends GrantedAuthority> authorities;
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    authorities = auth.getAuthorities();
	    String myRole = authorities.toArray()[0].toString();
	    String user = "USER";
	    
	    if (myRole.equals(user)) {
	        return "redirect:/user/Contact";
	    }
	    return "Other/contact";
	}
	
	@PostMapping("/Contact")
	public String ContactMail(@RequestParam("email") String to,@RequestParam("message") String body, @RequestParam("subject") String topic,@RequestParam("name") String name) {
		System.out.println("Sending : "+to+" "+body+" "+topic);
		SendEmailService.sendEmail(to,body,"By "+name+": "+topic);
		System.out.println("Success : "+to+" "+body+" "+topic);
	    return "Other/contact";
	}
	
	@GetMapping("/Login")
	public String login() {
		Collection<? extends GrantedAuthority> authorities;
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    authorities = auth.getAuthorities();
	    String myRole = authorities.toArray()[0].toString();
	    String user = "USER";
	    
	    if (myRole.equals(user)) {
	        return "redirect:/user/home";
	    }
	    return "Other/login";
	}
	
	@GetMapping("/logout-Success")
	public String logout() {
	    return "redirect:/Login";
	}
	
	@GetMapping("/Sign-up")
	public String SignUp(Model model) {
		Collection<? extends GrantedAuthority> authorities;
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    authorities = auth.getAuthorities();
	    String myRole = authorities.toArray()[0].toString();
	    String user = "USER";
	    
	    if (myRole.equals(user)) {
	        return "redirect:/user/home";
	    }
		UserEntity userentity = new UserEntity();
		model.addAttribute("user",userentity);
		return "Other/Sign-up";
	}
	
	@PostMapping("/Sign-up")
	public String UserregisterSuccess(@ModelAttribute("user") UserEntity user) {
		service.createUserEntity(user);
		SendEmailService.welcomeMail(user.getEmail(),user.getUsername());
		return "Other/login";
	}
	@GetMapping("/forgotpass")
	public String forgotpass() {
	    return "Other/forgot-password";
	}
	
	@GetMapping("/register")
	public String register() {
	    return "aa/register";
	}
	
	@RequestMapping("/default")
	public String defaultAfterLogin() {
	    Collection<? extends GrantedAuthority> authorities;
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    authorities = auth.getAuthorities();
	    String myRole = authorities.toArray()[0].toString();
	    String user = "USER";
	    
	    if (myRole.equals(user)) {
	        return "redirect:/user/home";
	    }
	    return "redirect:/admin/home";
	}


}
