/*
 * Created on 15 thg 12 2016 ( Time 12:02:13 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.trams.hello.bean.Business;
import org.trams.hello.bean.BusinessCustom;
import org.trams.hello.bean.PageCustom;
import org.trams.hello.bean.jpa.BusinessEntity;
import org.trams.hello.business.service.BusinessService;
import org.trams.hello.business.service.mapping.BusinessServiceMapper;
import org.trams.hello.data.repository.jpa.BusinessJpaRepository;

/**
 * Implementation of BusinessService
 */
@Component
@Transactional
public class BusinessServiceImpl implements BusinessService {

	@Resource
	private BusinessJpaRepository businessJpaRepository;
	
	@Autowired
	private EntityManager em;
	
	private static final Integer PAGE_SIZE = 15;

	@Resource
	private BusinessServiceMapper businessServiceMapper;

	@Override
	public Business findById(Integer id) {
		BusinessEntity businessEntity = businessJpaRepository.findOne(id);
		return businessServiceMapper.mapBusinessEntityToBusiness(businessEntity);
	}
	
	@Override
	public BusinessEntity findOne(Integer id) {
		return businessJpaRepository.findOne(id);
	}

	@Override
	public Page<BusinessEntity> findAll(Integer pageNumber) {

		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE, new Sort(new Order(Direction.DESC, "id")));
		return businessJpaRepository.findAll(request);
	}

	@Override
	public List<Business> findAll() {
		Iterable<BusinessEntity> entities = businessJpaRepository.findAll();
		List<Business> beans = new ArrayList<Business>();
		for (BusinessEntity businessEntity : entities) {
			beans.add(businessServiceMapper.mapBusinessEntityToBusiness(businessEntity));
		}
		return beans;
	}

	/**
	 * Count total entit
	 * 
	 * @return Long
	 */
	public Long countTotal() {
		Long count = businessJpaRepository.count();
		return count;
	}

	@Override
	public Business save(Business business) {
		return update(business);
	}

	@Override
	public Business create(Business business) {
		/*
		 * BusinessEntity businessEntity =
		 * businessJpaRepository.findOne(business.getId()); if( businessEntity
		 * != null ) { throw new IllegalStateException("already.exists"); }
		 * 
		 * businessEntity = new BusinessEntity();
		 * businessServiceMapper.mapBusinessToBusinessEntity(business,
		 * businessEntity); BusinessEntity businessEntitySaved =
		 * businessJpaRepository.save(businessEntity); return
		 * businessServiceMapper.mapBusinessEntityToBusiness(businessEntitySaved
		 * );
		 */
		BusinessEntity businessEntity = new BusinessEntity();
		businessServiceMapper.mapBusinessToBusinessEntity(business, businessEntity);
		BusinessEntity businessEntitySaved = businessJpaRepository.save(businessEntity);
		return businessServiceMapper.mapBusinessEntityToBusiness(businessEntitySaved);
	}

	@Override
	public Business update(Business business) {
		BusinessEntity businessEntity = businessJpaRepository.findOne(business.getId());
		businessServiceMapper.mapBusinessToBusinessEntity(business, businessEntity);
		BusinessEntity businessEntitySaved = businessJpaRepository.save(businessEntity);
		return businessServiceMapper.mapBusinessEntityToBusiness(businessEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		businessJpaRepository.delete(id);
	}

	public BusinessJpaRepository getBusinessJpaRepository() {
		return businessJpaRepository;
	}

	public void setBusinessJpaRepository(BusinessJpaRepository businessJpaRepository) {
		this.businessJpaRepository = businessJpaRepository;
	}

	public BusinessServiceMapper getBusinessServiceMapper() {
		return businessServiceMapper;
	}

	public void setBusinessServiceMapper(BusinessServiceMapper businessServiceMapper) {
		this.businessServiceMapper = businessServiceMapper;
	}

	@Override
	public Page<BusinessEntity> listPaging(Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page - 1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return businessJpaRepository.listPaging(pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void update_isDelete(Integer id) {
		try {
			businessJpaRepository.update_isDelete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<BusinessEntity> listPagingByName(String businessName) {
		try {
			return businessJpaRepository.listPagingByName("%" + businessName + "%");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public PageCustom<BusinessCustom> listPagingBy_BusineesTypeAndBusinessNameAndUsername(Short[] businessType,
			String type, String key, String order, Integer page, Integer size) {
			try {
				String str_where = "";
				
				if (businessType.length == 1) {
					str_where += " and b.business_type = "+businessType[0];
				}

				if (type != null & key != null) {
					if (type.equals("company_name")) {
						str_where += " and b.business_name like '%" + key + "%'";
					}  else if (type.equals("username")) {
						str_where += " and u.fullname like '%" + key + "%'";
					}
				}
				
				String orderBy = " order by ";
				 if(order.equals("business_name")){
					 orderBy += "b.business_name ASC";
				 }else if(order.equals("total_member")){
					 orderBy += "total_member DESC";
				 }else if(order.equals("total_counseling_fee")){
					 orderBy += "total_counseling_fee DESC";
				 }else{
					 orderBy += "b.create_date DESC";
				 }
				 
				String select_atribute = ""
						+ "select b.id, b.business_name, b.business_type, u.fullname, "
						+ "(select count(*) from business_sub sub where sub.business_id = b.id and sub.status = 1 ) as sub_active_total,"
						+ "(select count(*) from business_sub sub where sub.business_id = b.id) as sub_total,"
						+ "(select count(*) from user u inner join business_sub b2 on u.business_sub_id = b2.id where b2.business_id = b.id and u.is_delete != 1) as total_member,"
						+ "(select count(*) from (user u inner join business_sub b2 on u.business_sub_id = b2.id) inner join counseling_session cs on cs.user_id = u.id where b2.business_id = b.id and cs.status !=0) as total_counseling,"
						+ "(select case when sum(p.coin) is null then 0 else sum(p.coin) end from (user u inner join business_sub b2 on u.business_sub_id = b2.id) inner join counseling_session cs on cs.user_id = u.id "
						+ "inner join payment_history p on cs.payment_id = p.id where b2.business_id = b.id and cs.status !=0) as total_counseling_fee ";
				String select_count = "select count(*) ";
				String str_clause = "from business b inner join user u  on b.id = u.id where b.parent_business_id = 0 " + str_where  ;

				Query q = em.createNativeQuery(select_atribute + str_clause + orderBy +" limit " + (page-1) + ", " + size);
				@SuppressWarnings("unchecked")
				List<Object[]> reservation = q.getResultList();
				List<BusinessCustom> list = new ArrayList<>();
				for (Object[] i : reservation) {
					BusinessCustom b = new BusinessCustom();
					b.setId((Integer)i[0]);
					b.setBusinessName((String)i[1]);
					b.setBusinessType((Short)i[2]);
					b.setAdminName((String)i[3]);
					b.setSubBusinessActive(Integer.parseInt(String.valueOf(i[4])));
					b.setTotalSubBusiness((Integer.parseInt(String.valueOf(i[5]))));
					b.setTotalMember(Integer.parseInt(String.valueOf(i[6])));
					b.setTotalCounseling(Integer.parseInt(String.valueOf(i[7])));
					b.setTotalFeeCounseling(Integer.parseInt(String.valueOf(i[8])));
					list.add(b);
				}
				Query c = em.createNativeQuery(select_count + str_clause);
				Integer totalCount = Integer.valueOf(c.getSingleResult().toString());
				System.out.println("totalCount: "+totalCount);
				PageCustom<BusinessCustom> pageCustom = new PageCustom<>(list, totalCount, page, size);
				return pageCustom;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<BusinessEntity> listPagingBy_BusinessNameAndUsername(String businessName, String username, String order,
			Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page - 1, size, new Sort(new Order(Direction.DESC, order)));
			return businessJpaRepository.listPagingBy_BusinessNameAndUsername("%" + businessName + "%",
					"%" + username + "%", pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<BusinessEntity> listPagingBy_BusinessNameAndUsername(String businessName, String username, Integer page,
			Integer size) {
		try {
			PageRequest pageable = new PageRequest(page - 1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return businessJpaRepository.listPagingBy_BusinessNameAndUsername(businessName, username, pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<BusinessEntity> listPagingSubBusiness(Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page - 1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return businessJpaRepository.listPagingSubBusiness(pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<BusinessEntity> listPagingBy_ParentBusinessId(Integer parentBusinessId, Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page - 1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return businessJpaRepository.listPagingBy_ParentBusinessId(parentBusinessId, pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<BusinessEntity> listPagingBy_BusinessType(Short businessType) {
		try {
			return businessJpaRepository.listBy_BusinessType(businessType);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<BusinessEntity> listPagingBy_BusinessType(Short businessType, Integer page,Integer size) {
		try {
			PageRequest pageable = new PageRequest(page - 1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return businessJpaRepository.listPagingBy_BusinessType(businessType, pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<BusinessEntity> findAllOrderBy() {
		try {
			PageRequest pageable = new PageRequest(0, Integer.MAX_VALUE, new Sort(new Order(Direction.ASC, "businessName")));
			return businessJpaRepository.findAllOrderBy(pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<BusinessEntity> listParentBusiness() {
		try {
			return businessJpaRepository.listParentBusiness();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Object[]> getTotalSubActiveAndTotalSubBy_BusinessId(Integer businessId) {
		try {
			return businessJpaRepository.getTotalSubActiveAndTotalSubBy_BusinessId(businessId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public PageCustom<BusinessCustom> filterSubBusiness(Short businessType, Integer parentBusinessId, Integer page, Integer size) {
			try {
				String str_where = "";
				
				if (businessType > 0) {
					str_where += " and b.business_type = "+businessType;
				}
				
				if (parentBusinessId > 0) {
					str_where += " and b.parent_business_id = "+parentBusinessId;
				}

				String select_atribute = ""
						+ "select b.id, b.year_manager, b.business_name, "
						+ " (select count(*) from user u where u.business_sub_id = b.id and u.status_active !=3 and u.is_delete != 1) as total_member,"
						+ " (select count(*) from user u inner join counseling_session cs on cs.user_id = u.id where u.business_sub_id = b.id and u.status_active !=3) as total_counseling,"
						+ " (select case when sum(p.coin) is null then 0 else sum(p.coin) end from user u inner join counseling_session cs on cs.user_id = u.id"
						+ " inner join payment_history p on cs.payment_id = p.id where u.business_sub_id = b.id and cs.status !=0) as total_counseling_fee,"
						+ " b.contract_status";
						
				String select_count = "select count(*) ";
				String str_clause = " from business b inner join user u  on b.id = u.id where b.parent_business_id > 0 and b.contract_status !=0 " + str_where ;
				String orderBy = " order by b.create_date DESC ";
				Query q = em.createNativeQuery(select_atribute + str_clause + orderBy +" limit " + (page-1) + ", " + size);
				@SuppressWarnings("unchecked")
				List<Object[]> reservation = q.getResultList();
				List<BusinessCustom> list = new ArrayList<>();
				for (Object[] i : reservation) {
					BusinessCustom b = new BusinessCustom();
					b.setId((Integer)i[0]);
					b.setYearManager((Integer)i[1]);
					b.setBusinessName((String)i[2]);
					b.setTotalMember(Integer.parseInt(String.valueOf(i[3])));
					b.setTotalCounseling(Integer.parseInt(String.valueOf(i[4])));
					b.setTotalFeeCounseling(Integer.parseInt(String.valueOf(i[5])));
					b.setContractStatus((Short)i[6]);
					list.add(b);
				}
				Query c = em.createNativeQuery(select_count + str_clause);
				Integer totalCount = Integer.valueOf(c.getSingleResult().toString());
				System.out.println("totalCount: "+totalCount);
				PageCustom<BusinessCustom> pageCustom = new PageCustom<>(list, totalCount, page, size);
				return pageCustom;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
