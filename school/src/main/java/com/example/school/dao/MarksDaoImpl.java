package com.example.school.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.school.dto.MarksDto;
import com.example.school.model.Marks;
import com.example.school.repository.MarksRepository;
import com.example.school.repository.SubjectTeacherRepository;

@Repository
public class MarksDaoImpl implements MarksDao{

	@Autowired
	MarksRepository marksRepo;
    @Autowired
    SubjectTeacherRepository courseRepository;
    @PersistenceContext
    private EntityManager entityManager;

	
	@Override
	public List<MarksDto> getMarksListByTeacherId(Integer teacherId){
		List<Marks> markList = (List<Marks>) entityManager.createQuery(getMarksByTeacherIdQuery).setParameter(1, teacherId).getResultList();
		List<MarksDto> markListDto = new ArrayList<MarksDto>();
		for(Marks m: markList) {
			MarksDto marksDto = new MarksDto();
			BeanUtils.copyProperties(m,marksDto);
			marksDto.setStudentId(m.getStudentId().getStudentId());
			marksDto.setSubjectId(m.getSubjectId().getSubjectId());			
			markListDto.add(marksDto);
		}
        return markListDto;
	}

}
