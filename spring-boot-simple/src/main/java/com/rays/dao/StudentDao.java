package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rays.dto.CollegeDTO;
import com.rays.dto.RoleDTO;
import com.rays.dto.StudentDTO;
import com.rays.dto.UserDTO;

@Repository
public class StudentDao {

	@PersistenceContext
	public EntityManager entityManager;

	@Autowired
	CollegeDao collegeDao;

	public void populate(CollegeDTO dto) {
		if (dto.getName() != null && dto.getName().length() > 0) {

			CollegeDTO collegeDTO = collegeDao.findByPk(dto.getId());
			dto.setName(collegeDTO.getName());
		}

	}

	public long add(StudentDTO dto) {
		entityManager.persist(dto);
		return dto.getId();

	}

	public void delete(StudentDTO dto) {
		entityManager.remove(dto);

	}

	public void update(StudentDTO dto) {
		entityManager.merge(dto);

	}

	public StudentDTO findByPk(long pk) {
		StudentDTO dto = entityManager.find(StudentDTO.class, pk);
		return dto;

	}

	public List<StudentDTO> search(StudentDTO dto, int pageNo, int pageSize) {

		List<StudentDTO> list = null;

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<StudentDTO> cq = builder.createQuery(StudentDTO.class);

		List<Predicate> predicateList = new ArrayList<Predicate>();

		Root<StudentDTO> qRoot = cq.from(StudentDTO.class);

		if (dto != null) {

			if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
				predicateList.add(builder.like(qRoot.get("firstName"), dto.getFirstName() + "%"));

			}
			if (dto.getLastName() != null && dto.getLastName().length() > 0) {
				predicateList.add(builder.like(qRoot.get("lastName"), dto.getLastName() + "%"));

			}

		}
		cq.where(predicateList.toArray(new Predicate[predicateList.size()]));

		TypedQuery<StudentDTO> tq = entityManager.createQuery(cq);

		if (pageSize > 0) {
			tq.setFirstResult(pageNo * pageSize);
			tq.setMaxResults(pageNo);

		}

		tq.getResultList();
		return list;

	}

}
