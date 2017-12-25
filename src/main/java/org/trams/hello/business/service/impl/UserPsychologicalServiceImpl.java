/*
 * Created on 20 Mar 2017 ( Time 10:36:26 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
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
import org.trams.hello.bean.PageCustom;
import org.trams.hello.bean.UserPsychological;
import org.trams.hello.bean.api.PsychologicalTest;
import org.trams.hello.bean.jpa.UserPsychologicalEntity;
import org.trams.hello.bean.web.counselor.PsychologicalTestCounselor;
import org.trams.hello.bean.web.counselor.PsychologicalTestCounselorDetail;
import org.trams.hello.business.service.UserPsychologicalService;
import org.trams.hello.business.service.mapping.UserPsychologicalServiceMapper;
import org.trams.hello.data.repository.jpa.UserPsychologicalJpaRepository;
/**
 * Implementation of UserPsychologicalService
 */
@Component
@Transactional
public class UserPsychologicalServiceImpl implements UserPsychologicalService {

	@Resource
	private UserPsychologicalJpaRepository userPsychologicalJpaRepository;

	private static final Integer PAGE_SIZE   = 15;
	
	@Autowired
	private EntityManager em;

	@Resource
	private UserPsychologicalServiceMapper userPsychologicalServiceMapper;
	
	@Override
	public UserPsychological findById(Integer id) {
		UserPsychologicalEntity userPsychologicalEntity = userPsychologicalJpaRepository.findOne(id);
		return userPsychologicalServiceMapper.mapUserPsychologicalEntityToUserPsychological(userPsychologicalEntity);
	}
	
	@Override
	public UserPsychologicalEntity findOne(Integer id) {
		return userPsychologicalJpaRepository.findOne(id);
	}

	@Override
	public Page<UserPsychologicalEntity> findAll(Integer pageNumber) {

		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(new Order(Direction.DESC, "id")));
		return userPsychologicalJpaRepository.findAll(request);
	}

	@Override
	public List<UserPsychological> findAll() {
		Iterable<UserPsychologicalEntity> entities = userPsychologicalJpaRepository.findAll();
		List<UserPsychological> beans = new ArrayList<UserPsychological>();
		for(UserPsychologicalEntity userPsychologicalEntity : entities) {
			beans.add(userPsychologicalServiceMapper.mapUserPsychologicalEntityToUserPsychological(userPsychologicalEntity));
		}
		return beans;
	}

	/**
	 * Count total entit
	 * @return Long
	 */
	public Long countTotal(){
		Long count = userPsychologicalJpaRepository.count();
		return count;
	}

	@Override
	public UserPsychological save(UserPsychological userPsychological) {
		return update(userPsychological) ;
	}

	@Override
	public UserPsychological create(UserPsychological userPsychological) {
/*
		UserPsychologicalEntity userPsychologicalEntity = userPsychologicalJpaRepository.findOne(userPsychological.getId());
		if( userPsychologicalEntity != null ) {
			throw new IllegalStateException("already.exists");
		}

		userPsychologicalEntity = new UserPsychologicalEntity();
		userPsychologicalServiceMapper.mapUserPsychologicalToUserPsychologicalEntity(userPsychological, userPsychologicalEntity);
		UserPsychologicalEntity userPsychologicalEntitySaved = userPsychologicalJpaRepository.save(userPsychologicalEntity);
		return userPsychologicalServiceMapper.mapUserPsychologicalEntityToUserPsychological(userPsychologicalEntitySaved);
*/
		UserPsychologicalEntity userPsychologicalEntity = new UserPsychologicalEntity();
		userPsychologicalServiceMapper.mapUserPsychologicalToUserPsychologicalEntity(userPsychological, userPsychologicalEntity);
		UserPsychologicalEntity userPsychologicalEntitySaved = userPsychologicalJpaRepository.save(userPsychologicalEntity);
		return userPsychologicalServiceMapper.mapUserPsychologicalEntityToUserPsychological(userPsychologicalEntitySaved);
	}

	@Override
	public UserPsychological update(UserPsychological userPsychological) {
		UserPsychologicalEntity userPsychologicalEntity = userPsychologicalJpaRepository.findOne(userPsychological.getId());
		userPsychologicalServiceMapper.mapUserPsychologicalToUserPsychologicalEntity(userPsychological, userPsychologicalEntity);
		UserPsychologicalEntity userPsychologicalEntitySaved = userPsychologicalJpaRepository.save(userPsychologicalEntity);
		return userPsychologicalServiceMapper.mapUserPsychologicalEntityToUserPsychological(userPsychologicalEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		userPsychologicalJpaRepository.delete(id);
	}

	public UserPsychologicalJpaRepository getUserPsychologicalJpaRepository() {
		return userPsychologicalJpaRepository;
	}

	public void setUserPsychologicalJpaRepository(UserPsychologicalJpaRepository userPsychologicalJpaRepository) {
		this.userPsychologicalJpaRepository = userPsychologicalJpaRepository;
	}

	public UserPsychologicalServiceMapper getUserPsychologicalServiceMapper() {
		return userPsychologicalServiceMapper;
	}

	public void setUserPsychologicalServiceMapper(UserPsychologicalServiceMapper userPsychologicalServiceMapper) {
		this.userPsychologicalServiceMapper = userPsychologicalServiceMapper;
	}

	@Override
	public Page<UserPsychologicalEntity> listPaging(Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return userPsychologicalJpaRepository.listPaging(pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	

	@Override
	public Page<UserPsychologicalEntity> listPagingByUserId(Integer page, Integer size, Integer userId) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "requestUserPsychological.createDate")));
			return userPsychologicalJpaRepository.listPagingByUserId(userId, pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<UserPsychological> listByUserIdNotPayment(Integer userId, Integer counselorId, Date requestPsychological) {
		try {
			List<UserPsychologicalEntity> results = userPsychologicalJpaRepository.listByUserIdNotPayment(userId, counselorId, requestPsychological);
			List<UserPsychological> beans = new ArrayList<>();
			for (UserPsychologicalEntity userPsychologicalEntity : results) {
				beans.add(userPsychologicalServiceMapper.mapUserPsychologicalEntityToUserPsychological(userPsychologicalEntity));
			}
			return beans;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<PsychologicalTest> list(Integer userId) {
		List<Object[]> results = userPsychologicalJpaRepository.list(userId);
		List<PsychologicalTest> beans = new ArrayList<>();
		for (Object[] obj : results) {
			PsychologicalTest p = new PsychologicalTest();
			p.setNameCouselor((String) obj[0]);
			p.setCouselorId((Integer) obj[1]);
			p.setRequestPsychological((Date) obj[2]);
			p.setId((Integer) obj[3]);
			p.setEffectPaymentPsychological((Date) obj[4]);
			p.setTestName((String) obj[5]);
			p.setFee((Integer) obj[6]);
			beans.add(p);
		}
		return beans;
	}

	@Override
	public PageCustom<PsychologicalTestCounselor> filterUserPsychologicals(Integer[] testId, Short[] statuss, String fullname, Integer counselorId, Integer page, Integer size, String orderBy) {
		try {
			String str_where = "";
			String str_count = " SELECT count(*) ";
			String str_atribute = "select u.id as userId, u.fullname,r.request_psychological, r.effect_payment_psychological,r.status, "
					+ " (SELECT GROUP_CONCAT(p.test_name) from user_psychological up inner join psychological_test_setting p on p.id = up.psychological_test_id where up.request_psychological_id = r.id) as testname , "
					+ "  r.id as requestId, u.email, r.payment_psychological ";
			String str_clause = " from request_user_psychological r inner join user u on u.id = r.user_id"
					+ " where r.counselor_id = "+counselorId;
			if(testId.length >= 1){
				str_where +=" and r.id in (select r2.id from request_user_psychological r2 "
						+ "inner join user_psychological up2 on r2.id = up2.request_psychological_id "
						+ "where up2.psychological_test_id in ";
				str_where +="(";
				for (int i = 0; i < testId.length; i++) {
					str_where +=testId[i];
					if(i < (testId.length -1)){
						str_where +=", ";
					}
				}
				str_where +="))";
			}
			
			if (statuss.length > 0) {
				str_where+=" and r.status in (";
				for (int i = 0; i < statuss.length; i++) {
					str_where +=statuss[i];
					if(i < (statuss.length -1)){
						str_where +=", ";
					}
				}
				str_where +=")";
			}
			
			if (fullname != null) {
				str_where+=" and u.fullname like '%"+fullname+"%'";
			}
			
			if(orderBy.equals("request_psychological")) {
				str_where +=" ORDER BY r.request_psychological DESC ";
			}else if(orderBy.equals("payment_psychological")) {
				str_where +=" ORDER BY r.payment_psychological DESC ";
			} else if(orderBy.equals("fuName")) {
				str_where +=" ORDER BY u.fullname ASC ";
			}
			
			Query q = em.createNativeQuery(str_atribute +str_clause+ str_where + " limit " + (page-1)*size  + ", " + size);
			System.out.println(str_atribute +str_clause+ str_where + " limit " + (page-1)*size  + ", " + size);
			@SuppressWarnings("unchecked")
			List<Object[] > rss = q.getResultList();
			List<PsychologicalTestCounselor> list = new ArrayList<>();
			int count = 0; 
			for (Object[] i : rss) {
				PsychologicalTestCounselor p = new PsychologicalTestCounselor();
				p.setUserId((Integer) i[0]);
				p.setUserFullName((String) i[1]);
				p.setRequestPsychological((Date) i[2]);
				p.setEffectPaymentPsychological((Date) i[3]);
				p.setStatusPayment((Short) i[4]);
				String str = (String) i[5];
				if(str != null){
					str = "<p>"+str.replace(",", "</p><p>")+"</p>";
				}
				p.setTestName(str);
				p.setId((Integer) i[6]);
				p.setEmail((String) i[7]);
				count ++;
				p.setPsychologicalTestCounselorId(count);
				p.setPaymentPsychological((Date) i[8]);
				list.add(p);
			}
			
			Query c = em.createNativeQuery(str_count +str_clause+ str_where);
			Integer totalCount = Integer.valueOf(c.getSingleResult().toString());
			PageCustom<PsychologicalTestCounselor> pageCustom = new PageCustom<>(list, totalCount, page, size);
			return pageCustom;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	
	
	
	@Override
	public PageCustom<PsychologicalTestCounselor> filterUserPsychologicalAdmin(String startDate, String endDate, Integer psyId, Short[] status, String type, String key, Integer page, Integer size) {
		try {
			String str_where = " ";
			String str_count = " SELECT count(*) ";
			String str_select = "SELECT * ";
			String str_where2 = "";
			String testId = "";
			
			
			
			str_where = str_where + str_where2 ;
			
			Query q = em.createNativeQuery(str_select + str_where + " limit " + (page-1)*size  + ", " + size);
			System.out.println(str_select + str_where +" limit " + (page-1)*size + ", " + size);
			@SuppressWarnings("unchecked")
			List<Object[] > rss = q.getResultList();
			List<PsychologicalTestCounselor> list = new ArrayList<>();
			int count = 0; 
			for (Object[] i : rss) {
				PsychologicalTestCounselor p = new PsychologicalTestCounselor();
				p.setUserId((Integer) i[0]);
				p.setUserFullName((String) i[1]);
				p.setRequestPsychological((Date) i[2]);
				p.setEffectPaymentPsychological((Date) i[3]);
				p.setStatusPayment((Short) i[4]);
				p.setTestName((String) i[5]);
				p.setUserPsychologicalTestIds((String) i[6]);
				p.setPsychologicalTestIds((String) i[7]);
				p.setEmail((String) i[8]);
				count ++;
				p.setPsychologicalTestCounselorId(count);
				list.add(p);
			}
			
			Query c = em.createNativeQuery(str_count + str_where);
			Integer totalCount = Integer.valueOf(c.getSingleResult().toString());
			PageCustom<PsychologicalTestCounselor> pageCustom = new PageCustom<>(list, totalCount, page, size);
			return pageCustom;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public PsychologicalTestCounselorDetail getPsychologicalTestCounselorDetail(Integer userPsychological) {
		try {
			String str_where = "SELECT u.id AS userId, u.fullname, p.test_name, p.id AS psychological_test_id, uf.id AS user_psychological_file_id, uf.file_name, uf.file_url "
					+ " FROM user_psychological up "
					+ " INNER JOIN psychological_test_setting p ON up.psychological_test_id = p.id "
					+ " INNER JOIN user u ON up.user_id = u.id "
					+ " LEFT JOIN user_psychological_file uf ON up.id = uf.user_psychological_id "
					+ " Where up.id = "+userPsychological+" ";
			Query c = em.createNativeQuery(str_where);
			Object[]  rss = (Object[]) c.getSingleResult();
			PsychologicalTestCounselorDetail p = new PsychologicalTestCounselorDetail();
			p.setUserId((Integer) rss[0]);
			p.setUserFullName((String) rss[1]);
			p.setTestName((String)rss[2]);
			p.setPsychologicalTestId((Integer) rss[3]);
			p.setUserPsychologicalFileId((Integer) rss[4]);
			p.setFileName((String) rss[5]);
			p.setFileUrl((String) rss[6]);
			if(p.getFileUrl() == null || p.getFileUrl().isEmpty()){
				p.setStatusResultTest(0);
			} else {
				p.setStatusResultTest(1);
			}
			p.setUserPsychologicalTestId(userPsychological);
			
			return p;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<UserPsychologicalEntity> listByRequestId(Integer reqId) {
		try {
			return userPsychologicalJpaRepository.listByRequestId(reqId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
