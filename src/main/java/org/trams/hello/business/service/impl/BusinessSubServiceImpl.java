package org.trams.hello.business.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.trams.hello.bean.BusinessSub;
import org.trams.hello.bean.Voucher;
import org.trams.hello.bean.jpa.BusinessSubEntity;
import org.trams.hello.bean.jpa.VoucherEntity;
import org.trams.hello.bean.web.admin.BusinessSubDate;
import org.trams.hello.business.service.BusinessSubService;
import org.trams.hello.business.service.VoucherService;
import org.trams.hello.business.service.mapping.BusinessSubServiceMapper;
import org.trams.hello.data.repository.jpa.BusinessSubJpaRepository;
import org.trams.hello.web.common.utils.DataUtils;

/**
 * Implementation of BusinessSubService
 */
@Component
@Transactional
public class BusinessSubServiceImpl implements BusinessSubService {

	@Resource
	private BusinessSubJpaRepository businessSubJpaRepository;

	@Resource
	private BusinessSubServiceMapper businessSubServiceMapper;
	
	@Resource
	private VoucherService voucherService;
	
	@Override
	public BusinessSub findById(Integer id) {
		BusinessSubEntity businessSubEntity = businessSubJpaRepository.findOne(id);
		return businessSubServiceMapper.mapBusinessSubEntityToBusinessSub(businessSubEntity);
	}

	@Override
	public List<BusinessSub> findAll() {
		Iterable<BusinessSubEntity> entities = businessSubJpaRepository.findAll();
		List<BusinessSub> beans = new ArrayList<BusinessSub>();
		for(BusinessSubEntity businessSubEntity : entities) {
			beans.add(businessSubServiceMapper.mapBusinessSubEntityToBusinessSub(businessSubEntity));
		}
		return beans;
	}

	@Override
	public BusinessSub save(BusinessSub businessSub) {
		return update(businessSub) ;
	}

	@Override
	public BusinessSub create(BusinessSub businessSub) {
		BusinessSubEntity businessSubEntity = businessSubJpaRepository.findOne(businessSub.getId());
		if( businessSubEntity != null ) {
			throw new IllegalStateException("already.exists");
		}
		businessSubEntity = new BusinessSubEntity();
		businessSubServiceMapper.mapBusinessSubToBusinessSubEntity(businessSub, businessSubEntity);
		BusinessSubEntity businessSubEntitySaved = businessSubJpaRepository.save(businessSubEntity);
		return businessSubServiceMapper.mapBusinessSubEntityToBusinessSub(businessSubEntitySaved);
	}

	@Override
	public BusinessSub update(BusinessSub businessSub) {
		BusinessSubEntity businessSubEntity = businessSubJpaRepository.findOne(businessSub.getId());
		businessSubServiceMapper.mapBusinessSubToBusinessSubEntity(businessSub, businessSubEntity);
		BusinessSubEntity businessSubEntitySaved = businessSubJpaRepository.save(businessSubEntity);
		return businessSubServiceMapper.mapBusinessSubEntityToBusinessSub(businessSubEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		businessSubJpaRepository.delete(id);
	}

	public BusinessSubJpaRepository getBusinessSubJpaRepository() {
		return businessSubJpaRepository;
	}

	public void setBusinessSubJpaRepository(BusinessSubJpaRepository businessSubJpaRepository) {
		this.businessSubJpaRepository = businessSubJpaRepository;
	}

	public BusinessSubServiceMapper getBusinessSubServiceMapper() {
		return businessSubServiceMapper;
	}

	public void setBusinessSubServiceMapper(BusinessSubServiceMapper businessSubServiceMapper) {
		this.businessSubServiceMapper = businessSubServiceMapper;
	}

	@Override
	public List<Map<String, Object>> findForlist(Integer businessId, Integer page, Integer pageSize) {
		int offset = DataUtils.getOffset(page, pageSize);

		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		List<Object[]> list = businessSubJpaRepository.findForlist(businessId, offset, pageSize);
		for (Object[] obj : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", obj[0]);
			map.put("manageYear", obj[1]);
			map.put("businessSubName", obj[2]);
			map.put("userCount", obj[3]);
			Integer used =Integer.parseInt(String.valueOf(obj[4])); 
			Integer fee =Integer.parseInt(String.valueOf(obj[6]));
			map.put("used", used);
			map.put("totalPrice", used * fee);
			map.put("status", obj[5]);
			result.add(map);
		}

		return result;
	}
	
	@Override
	public Long countForList(Integer businessId) {
		return businessSubJpaRepository.countForList(businessId);
	}

	@Override
	public BusinessSubEntity findEntityById(Integer id) {
		return businessSubJpaRepository.findOne(id);
	}

	@Override
	public void updateBusinessSub(Integer businessSubId, BusinessSubEntity businessSubEntity, BusinessSubDate businessSubDate) {
		try {
			//update business sub
			BusinessSub businessSub = findById(businessSubId);
			businessSub.setBusinessSubNo(businessSubEntity.getBusinessSubNo());
			businessSub.setManageYear(businessSubEntity.getManageYear());
			businessSub.setBusinessSubName(businessSubEntity.getBusinessSubName());
			businessSub.setManagerName(businessSubEntity.getBusinessSubName());
			businessSub.setManagerPhone(businessSubEntity.getManagerPhone());
			businessSub.setManagerEmail(businessSubEntity.getManagerEmail());
			businessSub.setManagerPosition(businessSubEntity.getManagerPosition());
			businessSub.setStatus(businessSubEntity.getStatus());
			businessSub.setContractStartDate(businessSubDate.getContractDate().getFromDate());
			businessSub.setContractEndDate(businessSubDate.getContractDate().getToDate());
			update(businessSub);
			
			//update voucher
			VoucherEntity voucherEntity = businessSubEntity.getListOfVoucher().get(0);
			Voucher voucher = voucherService.findByBusinessSubId(businessSubId);
			voucher.setName(voucherEntity.getName());
			voucher.setNumber(voucherEntity.getNumber());
			voucher.setFee(voucherEntity.getFee());
			if(voucherEntity.getTypeTime() == 1){
				voucher.setFromTime(businessSubDate.getVoucherDate().getFromDate());
				voucher.setToTime(businessSubDate.getVoucherDate().getToDate());
			}else{
				voucher.setDateNumber(voucherEntity.getDateNumber());
			}
			voucher.setTypeTime(voucherEntity.getTypeTime());
			voucher.setContent(voucherEntity.getContent());
			voucher.setDateNumber(voucherEntity.getDateNumber());
			voucherService.update(voucher);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public List<BusinessSub> findByBusinessId(Integer businessId) {
		Iterable<BusinessSubEntity> entities = businessSubJpaRepository.findByBusinessId(businessId);
		List<BusinessSub> beans = new ArrayList<BusinessSub>();
		for(BusinessSubEntity businessSubEntity : entities) {
			beans.add(businessSubServiceMapper.mapBusinessSubEntityToBusinessSub(businessSubEntity));
		}
		return beans;
	}

	@Override
	public List<BusinessSub> findByBusinessIdAndManageYear(Integer businessId, Integer manageYear) {
		Iterable<BusinessSubEntity> entities = businessSubJpaRepository.findByBusinessIdAndManageYear(businessId, manageYear);
		List<BusinessSub> beans = new ArrayList<BusinessSub>();
		for(BusinessSubEntity businessSubEntity : entities) {
			beans.add(businessSubServiceMapper.mapBusinessSubEntityToBusinessSub(businessSubEntity));
		}
		return beans;
	}

	@Override
	public List<Map<String, Object>> findForlistByType(Short type, Integer page, Integer pageSize) {
		int offset = DataUtils.getOffset(page, pageSize);

		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		List<Object[]> list = businessSubJpaRepository.findForlistByType(type, offset, pageSize);
		for (Object[] obj : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", obj[0]);
			map.put("manageYear", obj[1]);
			map.put("businessSubName", obj[2]);
			map.put("userCount", obj[3]);
			Integer used =Integer.parseInt(String.valueOf(obj[4])); 
			Integer fee =Integer.parseInt(String.valueOf(obj[6]));
			map.put("used", used);
			map.put("totalPrice", used * fee);
			map.put("status", obj[5]);
			result.add(map);
		}

		return result;
	}

	@Override
	public Long countForListByType(Short type) {
		return businessSubJpaRepository.countForListByType(type);
		
	}
}
