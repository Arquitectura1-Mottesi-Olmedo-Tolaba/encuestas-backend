package edu.unq.arqsoft.mottesi_olmedo_tolaba.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.unq.arqsoft.mottesi_olmedo_tolaba.backend.dto.AcademicOfferStatisticsDTO;
import edu.unq.arqsoft.mottesi_olmedo_tolaba.backend.dto.OptionCounterDTO;
import edu.unq.arqsoft.mottesi_olmedo_tolaba.backend.dto.StatisticDTO;
import edu.unq.arqsoft.mottesi_olmedo_tolaba.backend.dto.StudentInformationDTO;
import edu.unq.arqsoft.mottesi_olmedo_tolaba.backend.model.AcademicOffer;
import edu.unq.arqsoft.mottesi_olmedo_tolaba.backend.model.OptionCounter;

@Transactional
@Service
public class AcademicOfferStatisticsService {

	@Autowired
	private DegreeService degreeService;

	@Autowired
	private AcademicOfferService academicOfferService;

	@Autowired
	private DegreeStudentService degreeStudentService;

	@Autowired
	private SurveyService surveyService;

	@Transactional
	public AcademicOfferStatisticsDTO getAcademicOfferInformation(Long idDegree) {
		AcademicOffer academicOffer = this.getCurrentAcademicOfferFor(idDegree);
		String degreeName = this.getDegreeName(idDegree);
		StudentInformationDTO studentInformationDTO = new StudentInformationDTO(this.studentsOf(idDegree),
				this.surveysCompletedOf(academicOffer.getId()));
		return new AcademicOfferStatisticsDTO(this.getStatisticsFor(academicOffer), academicOffer.getEndDate(), degreeName,
                studentInformationDTO);
	}

	private List<StatisticDTO> getStatisticsFor(AcademicOffer academicOffer) {
		return academicOffer.getStatistics().stream()
				.map(statistic -> new StatisticDTO(statistic.getSubject(), this.getInfo(statistic.getOptionsCounter())))
				.collect(Collectors.toList());
	}

	private List<OptionCounterDTO> getInfo(List<OptionCounter> optionsCounter) {
		return optionsCounter.stream().map(optionCounter -> new OptionCounterDTO(optionCounter.getDescription(),
				optionCounter.getCapacity(), optionCounter.getAmount())).collect(Collectors.toList());
	}

	private AcademicOffer getCurrentAcademicOfferFor(Long idDegree) {
		return this.academicOfferService.getCurrentAcademicOfferFor(idDegree);
	}

	private String getDegreeName(Long idDegree) {
		return this.degreeService.getDegreeName(idDegree);
	}

	private Integer surveysCompletedOf(Long academicOfferId) {
		return this.surveyService.surveysCompletedOf(academicOfferId);
	}

	private Integer studentsOf(Long idDegree) {
		return this.degreeStudentService.studentsForDegree(idDegree);
	}

}
