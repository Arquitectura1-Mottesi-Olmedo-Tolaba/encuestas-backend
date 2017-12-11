package edu.unq.arqsoft.mottesi_olmedo_tolaba.backend.service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.swing.tree.DefaultMutableTreeNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unq.arqsoft.mottesi_olmedo_tolaba.backend.model.AcademicOffer;
import edu.unq.arqsoft.mottesi_olmedo_tolaba.backend.model.Degree;
import edu.unq.arqsoft.mottesi_olmedo_tolaba.backend.model.Role;
import edu.unq.arqsoft.mottesi_olmedo_tolaba.backend.model.Subject;
import edu.unq.arqsoft.mottesi_olmedo_tolaba.backend.model.UserCredential;

@Service
public class UserRolesInitializationService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private DegreeService degreeService;
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired 
	private AcademicOfferFactoryService academicOfferFactoryService;
	
	@PostConstruct
	private void initialize() {
		List<Role> roles = new LinkedList<Role>();
		
		//Role admin = roleService.save(new Role("ADMIN"));
		Role director = roleService.save(new Role("DIRECTOR"));
		Role studentRole = roleService.save(new Role("STUDENT"));
		
		//roles.add(admin);
		roles.add(director);
		//roles.add(student);
		
		UserCredential adminUser = new UserCredential("director", "director");
		adminUser.addRoles(roles);
		
		String nameDegree = "Tecnicatura Universitaria en Programación Informática";
		
		adminUser = userService.save(adminUser);
		
		UserCredential student = new UserCredential("student", "student");
		student.addRole(studentRole);
		
		student = userService.save(student);
		
		Subject mate1 = new Subject("Matematica 1");
		mate1 = subjectService.save(mate1);
		
		Subject orga = new Subject("Organizaciòn de computadoras");
		orga = subjectService.save(orga);
		
		Subject intro = new Subject("Introducciòn a la programacion");
		intro = subjectService.save(intro);
		
		List<Subject> materias = Arrays.asList(mate1,orga,intro);
		AcademicOffer academicOffer = academicOfferFactoryService.initialize(nameDegree, materias);
		
		Degree degree = degreeService.save(new Degree(nameDegree, materias, Arrays.asList(academicOffer)));
		
		
	}
	
}