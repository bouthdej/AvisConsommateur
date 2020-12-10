package projet.rest.data.endpoints;

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
import projet.rest.data.models.ProductEntity;
import projet.rest.data.services.UserService;


@Controller
@Data
@AllArgsConstructor
public class Controlerr {
	@Autowired
UserService service ;
@GetMapping("/forms")
public String addProduct(Model model ) {
	ProductEntity p = new ProductEntity ();
	model.addAttribute("product",p);
	
	return "forms/createproduct";
}
@GetMapping("/Reviews")
public String addReview(Model model ) {
	AvisEntity a = new AvisEntity() ;
	model.addAttribute("avis",a);
	ProductEntity p = new ProductEntity ();
	model.addAttribute("product",p);
	
	return "Reviews/createavis";
}
@PostMapping("/forms")
public String registerSuccess(@ModelAttribute("product") ProductEntity product) {
	service.createProduct(product);
	return "forms/productcreated";
}

@PostMapping("/Reviews")
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
