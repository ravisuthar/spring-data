package com.example.demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

	@Autowired
	private DemoRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@RequestMapping("/hi")
	public String hi(){
		return "Hi";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/save")
	public void save(@RequestBody Demo demo){
		repository.save(demo);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/get")
	public List<Demo> get(){
		List<Demo> list=new ArrayList<Demo>();
		Iterable<Demo>  itb= repository.findAll();
		Iterator<Demo> itr =itb.iterator();
		while(itr.hasNext()){
			list.add(itr.next());
		}
		return list;
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/getById/{id}")
	public Demo getById(@PathVariable Long id){
		return repository.findById(id);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/getByName/{name}")
	public Demo getByName(@PathVariable String name){
		return repository.findByName(name);
	}
	

	@RequestMapping(method=RequestMethod.GET, value="/getByValue/{value}")
	public Demo getByValue(@PathVariable String value){
		return repository.findByValue(value);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/find/{id}/{name}/{value}")
	public Demo find(@PathVariable(name="id") Long id, @PathVariable(name="name") String name, @PathVariable(name="value") String value){
		return repository.findByIdAndNameAndValue(id, name, value);
	}
}
