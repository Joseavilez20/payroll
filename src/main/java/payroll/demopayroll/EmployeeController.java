package payroll.demopayroll;

import  static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import  static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
public class EmployeeController {
    
    private final EmployeeRepository repository;

    private final EmployeeModelAssembler assembler;

    EmployeeController(EmployeeRepository repository, EmployeeModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }


    // @GetMapping("/employees")
    // CollectionModel<EntityModel<Employee>> all() {
    //     List<EntityModel<Employee>> employees = StreamSupport.stream(repository.findAll().spliterator(), false)
    //     .map(employee -> EntityModel.of(employee,
    //     linkTo(methodOn(EmployeeController.class).findOne(employee.getId())).withSelfRel(),
    //     linkTo(methodOn(EmployeeController.class).all()).withRel("employees")))
    //     .collect(Collectors.toList());

    //     return CollectionModel.of(employees,
    //     linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    // }
    
    @GetMapping("/employees")
    CollectionModel<EntityModel<Employee>> all(){
        
        List<EntityModel<Employee>> employees = repository.findAll().stream()
        .map(assembler::toModel)
        .collect(Collectors.toList());

        return CollectionModel.of(employees,
        linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    }

    // @PostMapping("/employees")
    // Employee newEmployee(@RequestBody Employee newEmployee) {
    //     return repository.save(newEmployee);
    // }


  
   @GetMapping("/employees/{id}")
   EntityModel<Employee> one(@PathVariable Long id){

    Employee employee = repository.findById(id)
    .orElseThrow(() -> new EmployeeNotFoundException(id));

    return assembler.toModel(employee);
   }
   


    // @PutMapping("/employees/{id}")
    // Employee replacEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        
    //     return repository.findById(id)
    //      .map(employee -> {
    //          employee.setName(newEmployee.getName());
    //          employee.setRole(newEmployee.getRole());
    //          return repository.save(employee);
    //      })
    //      .orElseGet(() -> {
    //         newEmployee.setId(id);
    //         return repository.save(newEmployee);
    //      });
    // }

    // @DeleteMapping("/employees/{id}")
    // void deleteEmployee(@PathVariable Long id) {
    //     repository.deleteById(id);
    // }
}
