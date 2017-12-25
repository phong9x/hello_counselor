/*
 * Created on 23 thg 11 2016 ( Time 16:44:47 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.business.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.data.domain.Page;
import org.trams.hello.bean.DataChart;
import org.trams.hello.bean.User;
import org.trams.hello.bean.jpa.UserEntity;
import org.trams.hello.web.bean.search.SearchMember;
import org.trams.hello.web.dto.UserDto;

/**
 * Business Service Interface for entity User.
 */
public interface UserService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param id
	 * @return entity
	 */
	User findById( Integer id  ) ;

	
	UserEntity findOne(Integer id);
	/** 
	 * Loads all entities.
	 * @return all entities
	 */
	List<User> findAll();
	
	/**
	 * Loads entities in page number.
	 * @return entities
	 */

	Page<UserEntity> findAll(Integer page);

	/**
	 * Count all entities
	 * @return Long
	 */
	Long countTotal();
	

	/**
	 * Saves the given entity in the database (create or update)
	 * @param entity
	 * @return entity
	 */
	User save(User entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	User update(User entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	User create(User entity);
	
	/**
	 * Deletes an entity using its Primary Key
	 * @param id
	 */
	void delete( Integer id );

	Page<UserEntity> listPaging(Integer page,Integer size);
	
	Page<UserEntity> findByUsername(String username, Integer page, Integer size);

	Page<UserEntity> findByNickname(String nickname, Integer page, Integer size);

	Page<UserEntity> findByFullname(String fullname, Integer page, Integer size);

	Page<UserEntity> findByEmail(String email, Integer page, Integer size);

	void update_isDelete(Integer id);

	User loginApp(String username);

	User loginAdmin(String username, String password, Integer[] roleId);
	
	User loginCounselor(String username, String password);

	User loginCounselorTemp(String username, String password);
	
	List<Integer> totalUserHome(Date date);
	
	List<Integer> totalRequestAndCounselingHome();
	
	
	UserEntity findOneByEmail(String email);
	
	UserEntity findOneByEmailAndBirthDay(String email, Date birthDay);

	UserEntity findOneByPhone(String phone);
	
	Page<UserEntity> listPagingBy_BusinessId(Integer id, Integer page, Integer size);
	
	Integer findOneByUsername(String username);
	
	UserEntity findOneBy_Username(String username);

	Page<UserEntity> filter(Map<String, Object> params);
	
	List<DataChart> getDataChart(Date date, String type) ;
	
	Integer[] updateCoin(User user,Integer coin);

	UserEntity findByFullnameAndPhone(String fullname, String phone);

	UserEntity findByFullnameAndUsernameAndPhone(String fullname, String username, String phone);

    void register(UserDto param, ServletContext servletContext);

	void postpone(UserDto param, ServletContext servletContext);

	void updateBasicInfo(UserDto param);

	void updateServiceInfo(UserDto param, ServletContext servletContext);

    List<UserEntity> listByLastLogin(Integer year, Integer month, Integer date);;

    public void updateHeartByUserId(Integer userId, Integer heart, Integer typePayment, Integer typeUse, String typeShare );
    
    List<UserEntity> listByChangeHeartDate(Date date);

    void updateLogin(Integer userId);
    
    void updateHeartByUserId( Integer userId);
    
    Page<UserEntity> filter(SearchMember searchMember, Integer page, Integer size);
    
    void updateVoucherNumberByUserId( Integer userId);
}
