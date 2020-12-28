package projet.rest.data.endpoints;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import projet.rest.data.models.AvisEntity;
import projet.rest.data.models.ProductEntity;
import projet.rest.data.services.UserService;

@RestController


public class ProductRest {
	
 UserService service ;
 
@Autowired
public ProductRest(UserService service) {
	super();
	this.service = service;
}

@PostMapping(path ="/addproduct")

public ProductEntity createproduct(@RequestBody ProductEntity productEntity)
{
	return service.createProduct(productEntity);

}
@PostMapping(path ="/addavis/{id}")

public ProductEntity createavis(@PathVariable("id") int i, @RequestBody AvisEntity a ) {
	return service.createAvis(i,a);

}


@GetMapping(path="/getAvis/{id}")

public List<AvisEntity> getallavis(@PathVariable("id")int i)
{
return service.getAllAvisEntity(i)	;

}

@PostMapping(path ="/addlike/{id}/{userid}")
public AvisEntity addlike(@PathVariable("id") int id, @PathVariable("userid") int userid ) {
	 return service.addLike(id, userid);

}
@PostMapping(path ="/addDislike/{id}/{userid}")
public AvisEntity addDislike(@PathVariable("id") int id, @PathVariable("userid") int userid ) {
	 return service.addDisLike(id, userid);

}
}
