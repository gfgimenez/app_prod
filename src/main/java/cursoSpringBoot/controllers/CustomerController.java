package cursoSpringBoot.controllers;

import cursoSpringBoot.domain.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class CustomerController {


    private List<Customer> customers=new ArrayList<>(Arrays.asList(
            new Customer(123, "Gerardo Lopez", "gerardo1", "contrasena123"),
            new Customer(456, "Alejandra García", "alegarcia", "clave456"),
            new Customer(789, "Laura Sanchez", "lauras", "secreto789"),
            new Customer(234, "Carlos Martinez", "carlosm", "password234")
    ));

    // @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public ResponseEntity<List<Customer>> getCustomer(){

        return ResponseEntity.ok(customers);
        //return customers;
    }

    // @RequestMapping(value = "/{userName}", method = RequestMethod.GET)
    @GetMapping("/{userName}")
    public ResponseEntity<?> getCliente(@PathVariable String userName){

        for(Customer c : customers){
            if(c.getUserName().equalsIgnoreCase(userName)){
                return ResponseEntity.ok(c);
                //return c;
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado con userName: " + userName);
    }

    // @RequestMapping(method = RequestMethod.POST)
    @PostMapping
    public ResponseEntity<?> postCliente(@RequestBody Customer customer){

        customers.add(customer);

        URI Location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{userName}")
                .buildAndExpand(customer.getUserName()).toUri();

        //return ResponseEntity.created(Location).build();
        return ResponseEntity.created(Location).body(customer);
    }

    // MODIFICA TODA LA INFORMACION DEL OBJETO
    // @RequestMapping(method = RequestMethod.PUT)
    @PutMapping
    public ResponseEntity<?> putCliente(@RequestBody Customer customer){
        for(Customer c: customers){
            if(c.getID() == customer.getID()){
                c.setName(customer.getName());
                c.setUserName(customer.getUserName());
                c.setPassword(customer.getPassword());

                return ResponseEntity.noContent().build();
            }
        }

        return ResponseEntity.notFound().build();
    }

    // ELIMINA TODO EL OBJETO
    // @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable int id){

        for(Customer c : customers){
            if(c.getID()==id){
                customers.remove(c);

                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    // MODIFICA PARTE DE LA INFORMACION DEL CLIENTE
    // @RequestMapping(method = RequestMethod.PATCH)
    @PatchMapping
    public ResponseEntity<?> pathCliente(@RequestBody Customer customer){

        for(Customer c : customers){
            if(c.getID() == customer.getID()){

                if(customer.getName()!=null){
                    c.setName(customer.getName());
                }
                if(customer.getUserName()!=null){
                    c.setUserName(customer.getUserName());
                }
                if(customer.getPassword()!=null){
                    c.setPassword(customer.getPassword());
                }

                return ResponseEntity.ok("Clienete modificado exitosamente: "+ customer.getID());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("Cliente no encontrado en el ID: "+customer.getID());
    }
}
