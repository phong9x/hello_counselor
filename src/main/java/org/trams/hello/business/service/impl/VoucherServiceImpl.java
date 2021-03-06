/*
 * Created on 23 thg 11 2016 ( Time 16:49:52 )
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
import org.trams.hello.bean.Voucher;
import org.trams.hello.bean.jpa.VoucherEntity;
import org.trams.hello.business.service.VoucherService;
import org.trams.hello.business.service.mapping.VoucherServiceMapper;
import org.trams.hello.data.repository.jpa.VoucherJpaRepository;
/**
 * Implementation of VoucherService
 */
@Component
@Transactional
public class VoucherServiceImpl implements VoucherService {

	@Resource
	private VoucherJpaRepository voucherJpaRepository;

	private static final Integer PAGE_SIZE   = 15;

	@Resource
	private VoucherServiceMapper voucherServiceMapper;
	
	@Override
	public Voucher findById(Integer id) {
		VoucherEntity voucherEntity = voucherJpaRepository.findOne(id);
		return voucherServiceMapper.mapVoucherEntityToVoucher(voucherEntity);
	}

	@Override
	public Page<VoucherEntity> findAll(Integer pageNumber) {

		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(new Order(Direction.DESC, "id")));
		return voucherJpaRepository.findAll(request);
	}

	@Override
	public List<Voucher> findAll() {
		Iterable<VoucherEntity> entities = voucherJpaRepository.findAll();
		List<Voucher> beans = new ArrayList<Voucher>();
		for(VoucherEntity voucherEntity : entities) {
			beans.add(voucherServiceMapper.mapVoucherEntityToVoucher(voucherEntity));
		}
		return beans;
	}

	/**
	 * Count total entit
	 * @return Long
	 */
	public Long countTotal(){
		Long count = voucherJpaRepository.count();
		return count;
	}

	@Override
	public Voucher save(Voucher voucher) {
		return update(voucher) ;
	}

	@Override
	public Voucher create(Voucher voucher) {
/*
		VoucherEntity voucherEntity = voucherJpaRepository.findOne(voucher.getId());
		if( voucherEntity != null ) {
			throw new IllegalStateException("already.exists");
		}

		voucherEntity = new VoucherEntity();
		voucherServiceMapper.mapVoucherToVoucherEntity(voucher, voucherEntity);
		VoucherEntity voucherEntitySaved = voucherJpaRepository.save(voucherEntity);
		return voucherServiceMapper.mapVoucherEntityToVoucher(voucherEntitySaved);
*/
		VoucherEntity voucherEntity = new VoucherEntity();
		voucherServiceMapper.mapVoucherToVoucherEntity(voucher, voucherEntity);
		VoucherEntity voucherEntitySaved = voucherJpaRepository.save(voucherEntity);
		return voucherServiceMapper.mapVoucherEntityToVoucher(voucherEntitySaved);
	}

	@Override
	public Voucher update(Voucher voucher) {
		VoucherEntity voucherEntity = voucherJpaRepository.findOne(voucher.getId());
		voucherServiceMapper.mapVoucherToVoucherEntity(voucher, voucherEntity);
		VoucherEntity voucherEntitySaved = voucherJpaRepository.save(voucherEntity);
		return voucherServiceMapper.mapVoucherEntityToVoucher(voucherEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		voucherJpaRepository.delete(id);
	}

	public VoucherJpaRepository getVoucherJpaRepository() {
		return voucherJpaRepository;
	}

	public void setVoucherJpaRepository(VoucherJpaRepository voucherJpaRepository) {
		this.voucherJpaRepository = voucherJpaRepository;
	}

	public VoucherServiceMapper getVoucherServiceMapper() {
		return voucherServiceMapper;
	}

	public void setVoucherServiceMapper(VoucherServiceMapper voucherServiceMapper) {
		this.voucherServiceMapper = voucherServiceMapper;
	}

	@Override
	public Page<VoucherEntity> listPaging(Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return voucherJpaRepository.listPaging(pageable);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	

	@Override
	public Page<VoucherEntity> findByName(String name, Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return voucherJpaRepository.findByName(name, pageable);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public VoucherEntity findOneByBusinessId(Integer businessId) {
		try {
			return voucherJpaRepository.findOneByBusinessId(businessId);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public Voucher findByTypeHeart(Short type) {
		try {
			return voucherServiceMapper.mapVoucherEntityToVoucher(voucherJpaRepository.findByTypeHeart(type));
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public Voucher findByBusinessSubId(Integer businessSubId) {
		VoucherEntity voucherEntity = voucherJpaRepository.findByBusinessSubId(businessSubId);
		return voucherServiceMapper.mapVoucherEntityToVoucher(voucherEntity);
	}

	@Override
	public List<VoucherEntity> listByBusinessId(Integer businessId) {
		try {
			return voucherJpaRepository.listByBusinessId(businessId);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

}
