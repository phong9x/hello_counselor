/*
 * Created on 20 Mar 2017 ( Time 10:36:26 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.trams.hello.bean.UserPsychologicalFile;
import org.trams.hello.bean.api.PsychologicalTestSetting;
import org.trams.hello.bean.api.UserPsychologicalFileApi;
import org.trams.hello.bean.jpa.UserPsychologicalFileEntity;
import org.trams.hello.business.service.UserPsychologicalFileService;
import org.trams.hello.business.service.mapping.UserPsychologicalFileServiceMapper;
import org.trams.hello.data.repository.jpa.UserPsychologicalFileJpaRepository;
/**
 * Implementation of UserPsychologicalFileService
 */
@Component
@Transactional
public class UserPsychologicalFileServiceImpl implements UserPsychologicalFileService {

	@Resource
	private UserPsychologicalFileJpaRepository userPsychologicalFileJpaRepository;

	private static final Integer PAGE_SIZE   = 15;

	@Resource
	private UserPsychologicalFileServiceMapper userPsychologicalFileServiceMapper;
	
	@Override
	public UserPsychologicalFile findById(Integer id) {
		UserPsychologicalFileEntity userPsychologicalFileEntity = userPsychologicalFileJpaRepository.findOne(id);
		return userPsychologicalFileServiceMapper.mapUserPsychologicalFileEntityToUserPsychologicalFile(userPsychologicalFileEntity);
	}

	@Override
	public Page<UserPsychologicalFileEntity> findAll(Integer pageNumber) {

		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(new Order(Direction.DESC, "id")));
		return userPsychologicalFileJpaRepository.findAll(request);
	}

	@Override
	public List<UserPsychologicalFile> findAll() {
		Iterable<UserPsychologicalFileEntity> entities = userPsychologicalFileJpaRepository.findAll();
		List<UserPsychologicalFile> beans = new ArrayList<UserPsychologicalFile>();
		for(UserPsychologicalFileEntity userPsychologicalFileEntity : entities) {
			beans.add(userPsychologicalFileServiceMapper.mapUserPsychologicalFileEntityToUserPsychologicalFile(userPsychologicalFileEntity));
		}
		return beans;
	}

	/**
	 * Count total entit
	 * @return Long
	 */
	public Long countTotal(){
		Long count = userPsychologicalFileJpaRepository.count();
		return count;
	}

	@Override
	public UserPsychologicalFile save(UserPsychologicalFile userPsychologicalFile) {
		return update(userPsychologicalFile) ;
	}

	@Override
	public UserPsychologicalFile create(UserPsychologicalFile userPsychologicalFile) {
/*
		UserPsychologicalFileEntity userPsychologicalFileEntity = userPsychologicalFileJpaRepository.findOne(userPsychologicalFile.getId());
		if( userPsychologicalFileEntity != null ) {
			throw new IllegalStateException("already.exists");
		}

		userPsychologicalFileEntity = new UserPsychologicalFileEntity();
		userPsychologicalFileServiceMapper.mapUserPsychologicalFileToUserPsychologicalFileEntity(userPsychologicalFile, userPsychologicalFileEntity);
		UserPsychologicalFileEntity userPsychologicalFileEntitySaved = userPsychologicalFileJpaRepository.save(userPsychologicalFileEntity);
		return userPsychologicalFileServiceMapper.mapUserPsychologicalFileEntityToUserPsychologicalFile(userPsychologicalFileEntitySaved);
*/
		UserPsychologicalFileEntity userPsychologicalFileEntity = new UserPsychologicalFileEntity();
		userPsychologicalFileServiceMapper.mapUserPsychologicalFileToUserPsychologicalFileEntity(userPsychologicalFile, userPsychologicalFileEntity);
		UserPsychologicalFileEntity userPsychologicalFileEntitySaved = userPsychologicalFileJpaRepository.save(userPsychologicalFileEntity);
		return userPsychologicalFileServiceMapper.mapUserPsychologicalFileEntityToUserPsychologicalFile(userPsychologicalFileEntitySaved);
	}

	@Override
	public UserPsychologicalFile update(UserPsychologicalFile userPsychologicalFile) {
		UserPsychologicalFileEntity userPsychologicalFileEntity = userPsychologicalFileJpaRepository.findOne(userPsychologicalFile.getId());
		userPsychologicalFileServiceMapper.mapUserPsychologicalFileToUserPsychologicalFileEntity(userPsychologicalFile, userPsychologicalFileEntity);
		UserPsychologicalFileEntity userPsychologicalFileEntitySaved = userPsychologicalFileJpaRepository.save(userPsychologicalFileEntity);
		return userPsychologicalFileServiceMapper.mapUserPsychologicalFileEntityToUserPsychologicalFile(userPsychologicalFileEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		userPsychologicalFileJpaRepository.delete(id);
	}

	public UserPsychologicalFileJpaRepository getUserPsychologicalFileJpaRepository() {
		return userPsychologicalFileJpaRepository;
	}

	public void setUserPsychologicalFileJpaRepository(UserPsychologicalFileJpaRepository userPsychologicalFileJpaRepository) {
		this.userPsychologicalFileJpaRepository = userPsychologicalFileJpaRepository;
	}

	public UserPsychologicalFileServiceMapper getUserPsychologicalFileServiceMapper() {
		return userPsychologicalFileServiceMapper;
	}

	public void setUserPsychologicalFileServiceMapper(UserPsychologicalFileServiceMapper userPsychologicalFileServiceMapper) {
		this.userPsychologicalFileServiceMapper = userPsychologicalFileServiceMapper;
	}

	@Override
	public Page<UserPsychologicalFileEntity> listPaging(Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return userPsychologicalFileJpaRepository.listPaging(pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<UserPsychologicalFileApi> listByRequestId(Integer reqId) {
		try {
			List<Object[]> rs = userPsychologicalFileJpaRepository.listByRequestId(reqId);
			List<UserPsychologicalFileApi> beans = new ArrayList<>();
			for (Object[] o : rs) {
				UserPsychologicalFileApi up = new UserPsychologicalFileApi();
				Integer idConvert = (Integer) o[0];
				if (idConvert == null) {
					idConvert = 0;
				}
				up.setId(idConvert);
				up.setUserPsychologicalId((Integer) o[1]);
				up.setTestName((String) o[2]);
				up.setStatusPayment((Short) o[3]);
				up.setFileName((String) o[4]);
				up.setFileUrl((String) o[5]);
				up.setCreateDate((Date) o[6]);
				up.setUpdateDate((Date) o[7]);
				up.setPsychologicalTestSettingId((Integer) o[8]);
				beans.add(up);
			}
			
			return beans;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<UserPsychologicalFileApi> listByUserId(Integer userId) {
		try {
			List<Object[]> rs = userPsychologicalFileJpaRepository.listByUserId(userId);
			List<UserPsychologicalFileApi> beans = new ArrayList<>();
			for (Object[] o : rs) {
				UserPsychologicalFileApi up = new UserPsychologicalFileApi();
				up.setId((Integer) o[0]);
				up.setUserPsychologicalId((Integer) o[1]);
				up.setTestName((String) o[2]);
				up.setStatusPayment((Short) o[3]);
				up.setFileName((String) o[4]);
				up.setFileUrl((String) o[5]);
				up.setCreateDate((Date) o[6]);
				up.setUpdateDate((Date) o[7]);
				up.setPsychologicalTestSettingId((Integer) o[8]);
				
				if(up.getId() != null) {
					beans.add(up);
				}
			}
			
			return beans;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<UserPsychologicalFileApi> listByUserIdAndPsyTestSetting(Integer userId, Integer testSettingId) {
		try {
			List<Object[]> rs = userPsychologicalFileJpaRepository.listByUserIdAndPsyTestSetting(userId, testSettingId);
			List<UserPsychologicalFileApi> beans = new ArrayList<>();
			for (Object[] o : rs) {
				UserPsychologicalFileApi up = new UserPsychologicalFileApi();
				up.setId((Integer) o[0]);
				up.setUserPsychologicalId((Integer) o[1]);
				up.setTestName((String) o[2]);
				up.setStatusPayment((Short) o[3]);
				up.setFileName((String) o[4]);
				up.setFileUrl((String) o[5]);
				up.setCreateDate((Date) o[6]);
				up.setUpdateDate((Date) o[7]);
				up.setPsychologicalTestSettingId((Integer) o[8]);
				
				if(up.getId() != null) {
					beans.add(up);
				}
			}
			
			return beans;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<UserPsychologicalFileApi> listByRequestPsychologicalIdInWebCouselor(Integer request_psychological_id) {
		try {
			List<Object[]> rs = userPsychologicalFileJpaRepository.listByRequestPsychologicalIdInWebCouselor(request_psychological_id);
			List<UserPsychologicalFileApi> beans = new ArrayList<>();
			for (Object[] o : rs) {
				UserPsychologicalFileApi up = new UserPsychologicalFileApi();
				up.setId((Integer) o[0]);
				up.setUserPsychologicalId((Integer) o[1]);
				up.setTestName((String) o[2]);
				up.setStatusPayment((Short) o[3]);
				up.setFileName((String) o[4]);
				up.setFileUrl((String) o[5]);
				up.setCreateDate((Date) o[6]);
				up.setUpdateDate((Date) o[7]);
				up.setPsychologicalTestSettingId((Integer) o[8]);
				if(up.getId() == null) {
					up.setId(0);
				} else {
					up.setId((Integer) o[0]);
				}
				beans.add(up);
			}
			
			return beans;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<PsychologicalTestSetting> listUserPsyTestIds(Integer userId) {
		List<Object[]> rs = userPsychologicalFileJpaRepository.listUserPsyTestIds(userId);
		List<PsychologicalTestSetting> list = new ArrayList<>();
		for (Object[] objects : rs) {
			PsychologicalTestSetting p = new PsychologicalTestSetting();
			p.setId((Integer) objects[0]);
			p.setTestName((String) objects[1]);
			list.add(p);
		}
		return list;
	}
}
