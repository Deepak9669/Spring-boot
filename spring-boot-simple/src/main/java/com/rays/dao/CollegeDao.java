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

import org.springframework.stereotype.Repository;

import com.rays.dto.CollegeDTO;

@Repository
public class CollegeDao {

	@PersistenceContext
	EntityManager entityManager;

	public long add(CollegeDTO dto) {
		entityManager.persist(dto);
		return dto.getId();

	}

	public void delete(CollegeDTO dto) {
		entityManager.remove(dto);

	}

	public void update(CollegeDTO dto) {
		entityManager.merge(dto);

	}

	public CollegeDTO findByPk(long pk) {
		CollegeDTO dto = entityManager.find(CollegeDTO.class, pk);
		return dto;

	}

	public List<CollegeDTO> search(CollegeDTO dto, int pageNo, int pageSize) {

		List<CollegeDTO> list = null;

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<CollegeDTO> cq = builder.createQuery(CollegeDTO.class);

		List<Predicate> predicateList = new ArrayList<Predicate>();

		Root<CollegeDTO> qRoot = cq.from(CollegeDTO.class);

		if (dto != null) {
			if (dto.getName() != null && dto.getName().length() > 0) {
				predicateList.add(builder.like(qRoot.get("name"), dto.getName() + "%"));

			}

			if (dto.getAddress() != null && dto.getAddress().length() > 0) {
				predicateList.add(builder.like(qRoot.get("address"), dto.getAddress() + "%"));

			}
			if (dto.getState() != null && dto.getState().length() > 0) {
				predicateList.add(builder.like(qRoot.get("state"), dto.getState() + "%"));

			}
			if (dto.getCity() != null && dto.getCity().length() > 0) {
				predicateList.add(builder.like(qRoot.get("city"), dto.getCity() + "%"));

			}

		}

		cq.where(predicateList.toArray(new Predicate[predicateList.size()]));

		TypedQuery<CollegeDTO> tq = entityManager.createQuery(cq);

		if (pageSize > 0) {
			tq.setFirstResult(pageNo * pageSize);
			tq.setMaxResults(pageSize);

		}
		list = tq.getResultList();

		return list;

	}
}
