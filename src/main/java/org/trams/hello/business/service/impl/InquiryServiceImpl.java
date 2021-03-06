/*
 * Created on 23 thg 11 2016 ( Time 13:38:12 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.trams.hello.bean.Inquiry;
import org.trams.hello.bean.jpa.InquiryEntity;
import org.trams.hello.business.service.InquiryService;
import org.trams.hello.business.service.mapping.InquiryServiceMapper;
import org.trams.hello.data.repository.jpa.InquiryJpaRepository;
import org.trams.hello.web.common.utils.DataUtils;
/**
 * Implementation of InquiryService
 */
@Component
@Transactional
public class InquiryServiceImpl implements InquiryService {

	@Resource
	private InquiryJpaRepository inquiryJpaRepository;

	private static final Integer PAGE_SIZE   = 15;

	@Resource
	private InquiryServiceMapper inquiryServiceMapper;
	
	@Override
	public Inquiry findById(Integer id) {
		InquiryEntity inquiryEntity = inquiryJpaRepository.findOne(id);
		return inquiryServiceMapper.mapInquiryEntityToInquiry(inquiryEntity);
	}

	@Override
	public Page<InquiryEntity> findAll(Integer pageNumber) {

		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(new Order(Direction.DESC, "id")));
		return inquiryJpaRepository.findAll(request);
	}

	@Override
	public List<Inquiry> findAll() {
		Iterable<InquiryEntity> entities = inquiryJpaRepository.findAll();
		List<Inquiry> beans = new ArrayList<Inquiry>();
		for(InquiryEntity inquiryEntity : entities) {
			beans.add(inquiryServiceMapper.mapInquiryEntityToInquiry(inquiryEntity));
		}
		return beans;
	}

	/**
	 * Count total entit
	 * @return Long
	 */
	public Long countTotal(){
		Long count = inquiryJpaRepository.count();
		return count;
	}

	@Override
	public Inquiry save(Inquiry inquiry) {
		return update(inquiry) ;
	}

	@Override
	public Inquiry create(Inquiry inquiry) {
/*
		InquiryEntity inquiryEntity = inquiryJpaRepository.findOne(inquiry.getId());
		if( inquiryEntity != null ) {
			throw new IllegalStateException("already.exists");
		}

		inquiryEntity = new InquiryEntity();
		inquiryServiceMapper.mapInquiryToInquiryEntity(inquiry, inquiryEntity);
		InquiryEntity inquiryEntitySaved = inquiryJpaRepository.save(inquiryEntity);
		return inquiryServiceMapper.mapInquiryEntityToInquiry(inquiryEntitySaved);
*/
		InquiryEntity inquiryEntity = new InquiryEntity();
		inquiryServiceMapper.mapInquiryToInquiryEntity(inquiry, inquiryEntity);
		InquiryEntity inquiryEntitySaved = inquiryJpaRepository.save(inquiryEntity);
		return inquiryServiceMapper.mapInquiryEntityToInquiry(inquiryEntitySaved);
	}

	@Override
	public Inquiry update(Inquiry inquiry) {
		InquiryEntity inquiryEntity = inquiryJpaRepository.findOne(inquiry.getId());
		inquiryServiceMapper.mapInquiryToInquiryEntity(inquiry, inquiryEntity);
		InquiryEntity inquiryEntitySaved = inquiryJpaRepository.save(inquiryEntity);
		return inquiryServiceMapper.mapInquiryEntityToInquiry(inquiryEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		inquiryJpaRepository.delete(id);
	}

	public InquiryJpaRepository getInquiryJpaRepository() {
		return inquiryJpaRepository;
	}

	public void setInquiryJpaRepository(InquiryJpaRepository inquiryJpaRepository) {
		this.inquiryJpaRepository = inquiryJpaRepository;
	}

	public InquiryServiceMapper getInquiryServiceMapper() {
		return inquiryServiceMapper;
	}

	public void setInquiryServiceMapper(InquiryServiceMapper inquiryServiceMapper) {
		this.inquiryServiceMapper = inquiryServiceMapper;
	}

	@Override
	public Page<InquiryEntity> listPaging(Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return inquiryJpaRepository.listPaging(pageable);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public List<Integer> totalInquiry() {
		try {
			List<Object[]> list_object = inquiryJpaRepository.totalInquiry();
			List<Integer> list_integet = new ArrayList<>();
			if(list_object != null){
				Object[] ob = list_object.get(0);
				list_integet.add(DataUtils.parseInt(ob[0]));
				list_integet.add(DataUtils.parseInt(ob[1]));
			}
			return list_integet;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	


}
