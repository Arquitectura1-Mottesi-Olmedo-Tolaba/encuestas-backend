package edu.unq.arqsoft.mottesi_olmedo_tolaba.backend.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.unq.arqsoft.mottesi_olmedo_tolaba.backend.model.AcademicOffer;
import edu.unq.arqsoft.mottesi_olmedo_tolaba.backend.model.Course;
import edu.unq.arqsoft.mottesi_olmedo_tolaba.backend.model.Offer;
import edu.unq.arqsoft.mottesi_olmedo_tolaba.backend.model.Period;
import edu.unq.arqsoft.mottesi_olmedo_tolaba.backend.model.Professor;
import edu.unq.arqsoft.mottesi_olmedo_tolaba.backend.model.Student;
import edu.unq.arqsoft.mottesi_olmedo_tolaba.backend.model.Subject;
import edu.unq.arqsoft.mottesi_olmedo_tolaba.backend.model.Timeline;
@Transactional
@Service
public class AcademicOfferFactoryService {

	@Autowired
	private AcademicOfferService academicOfferService;
		
	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private PeriodService periodService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private TimelineService timelineService;
	
	@Transactional
	public Offer createOrgaOffer(Subject subject){
		Offer offer = new Offer();
		Course cursada = new Course();
		Professor mara = new Professor("Mara Dalponte");
		mara = professorService.save(mara);
		
		
		Timeline lunes = new Timeline("Lunes","18:00","22:00");
		Timeline miercoles = new Timeline("Miercoles","18:00","22:00");
		lunes = timelineService.save(lunes);
		miercoles = timelineService.save(miercoles);
		
		cursada.setProfessor(mara);
		cursada.setName("Comision 1");
		cursada.setTimelines(Arrays.asList(lunes, miercoles));

		courseService.save(cursada);
		
		offer.setSubject(subject);
		offer.setCourses(Arrays.asList(cursada));
		
		return offer;
	}
	
	@Transactional
	public Offer createIntroOffer(Subject subject){
		Offer offer = new Offer();
		Course cursada = new Course();
		Professor eduardo = new Professor("Eduardo Bonelli");
		eduardo = professorService.save(eduardo);
		
		cursada.setName("Comision 1");
		cursada.setProfessor(eduardo);
		
		Timeline lunes = new Timeline("Lunes","18:00","22:00");
		Timeline miercoles = new Timeline("Miercoles","18:00","22:00");
		lunes = timelineService.save(lunes);
		miercoles = timelineService.save(miercoles);
		cursada.setTimelines(Arrays.asList(lunes, miercoles));

		courseService.save(cursada);
		
		offer.setSubject(subject);
		offer.setCourses(Arrays.asList(cursada));
		return offer;
	}
	
	@Transactional
	public Offer createMatematicaOffer(Subject subject){
		Offer offer = new Offer();
		Course cursada = new Course();
		Course cursada2 = new Course();
		
		Timeline lunes = new Timeline("Lunes","18:00","22:00");
		Timeline miercoles = new Timeline("Miercoles","18:00","22:00");
		lunes = timelineService.save(lunes);
		miercoles = timelineService.save(miercoles);
		
		Timeline lunes1 = new Timeline("Lunes","18:00","22:00");
		Timeline miercoles1 = new Timeline("Miercoles","18:00","22:00");
		lunes1 = timelineService.save(lunes);
		miercoles1 = timelineService.save(miercoles);
		
		Professor hortensia = new Professor("Hortensia");
		hortensia = professorService.save(hortensia);
		Professor jano = new Professor("Jano");
		jano = professorService.save(jano);
		
		cursada.setName("Comision 1");
		cursada.setProfessor(hortensia);
		cursada.setTimelines(Arrays.asList(lunes, miercoles));
		
		cursada2.setName("Comision 2");
		cursada2.setProfessor(jano);
		cursada2.setTimelines(Arrays.asList(lunes1, miercoles1));

		cursada = courseService.save(cursada);
		cursada2 = courseService.save(cursada2);
		
		
		offer.setSubject(subject);
		offer.setCourses(Arrays.asList(cursada,cursada2));
		
		return offer;
	}
	
	
	public AcademicOffer initialize(String nameDegree, List<Subject> subjects) {
		
		AcademicOffer academicOffer = new AcademicOffer();
		academicOffer.setActive(true);
		// TODO: SET DEGREE, STUDENT ??? EL NAME ES EL NOMBRE DEL DEGREE ...
		academicOffer.setName(nameDegree);
		Integer quarter = 1;
		Period period = new Period(2017,quarter);
		periodService.save(period);
				
		Student student = new Student();
		student.setName("Homero");
		student.setLastName("Simpson");
		student.addAprovedSubject(subjects.get(0));
		studentService.save(student);
				
		academicOffer.setPeriod(period);
		academicOffer = academicOfferService.save(academicOffer);
		
		Offer offerMatematica = createMatematicaOffer(subjects.get(0));
		Offer offerOrga = createOrgaOffer(subjects.get(1));
		Offer offerIntro = createIntroOffer(subjects.get(2));
		
		academicOffer.setOffers(Arrays.asList(offerMatematica, offerOrga,offerIntro));
		academicOffer = academicOfferService.update(academicOffer);
				
		return academicOffer;
	}
	
	
	
}
