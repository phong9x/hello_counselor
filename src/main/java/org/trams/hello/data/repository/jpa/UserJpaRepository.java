package org.trams.hello.data.repository.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.UserEntity;
/**
 * Repository : User.
 */
public interface UserJpaRepository extends JpaSpecificationExecutor<UserEntity>, PagingAndSortingRepository<UserEntity, Integer> {
	@Query(
			"Select u From UserEntity u Where u.isDelete = 0"
			)
	Page<UserEntity> listPaging(Pageable pageable);
	
	@Query(
			"Select u From UserEntity u Where u.businessSub.id = ?1 and u.isDelete = 0"
			)
	Page<UserEntity> listPagingBy_BusinessId(Integer id, Pageable pageable);
	
	@Query(
			"Select u From UserEntity u Where u.changeHeartDate > ?1 and u.statusActive !=3 and u.isDelete = 0"
			)
	List<UserEntity> listByChangeHeartDate(Date date);
	
	@Query(
			"Select u From UserEntity u Where Year(u.lastLogin) = ?1 and Month(u.lastLogin) = ?2 and DAY(u.lastLogin) = ?3 and u.isDelete = 0"
			)
	List<UserEntity> listByLastLogin(Integer year, Integer month, Integer date);

	@Modifying
	@Query(
			value="UPDATE user SET is_delete = 1 WHERE id = ?1",nativeQuery=true
			)
	void update_isDelete(Integer id);
	
	@Query(
			"Select u From UserEntity u Where u.username = ?1 and (u.userRole.id = 1 or u.userRole.id = 5 or u.userRole.id =9) and (statusActive = 0 or statusActive = 2)  and u.isDelete = 0"
			)
	UserEntity loginApp(String username);
	
	
	@Query(
			"Select u From UserEntity u Where u.username = ?1 and u.password = ?2 and u.userRole.id in ?3 and u.isDelete = 0"
			)
	UserEntity loginAdmin(String username, String password, Integer[] roleId);
	
	@Query(
			"Select u From UserEntity u Where u.username = ?1 and u.password = ?2 and u.userRole.id in (2,3,4) and u.isDelete = 0"
			)
	UserEntity loginCounselor(String username, String password);

	@Query(
			"Select u From UserEntity u Where u.username = ?1 and u.passwordTemp = ?2 and u.userRole.id in (2,3,4) and u.isDelete = 0"
	)
	UserEntity loginCounselorTemp(String username, String passwordTemp);

	@Query(
			"Select u From UserEntity u Where u.username like ?1 and u.isDelete = 0"
			)
	Page<UserEntity> findByUsername(String username, Pageable pageable);
	@Query(
			"Select u From UserEntity u Where u.nickname like ?1 and u.isDelete = 0"
			)
	Page<UserEntity> findByNickname(String nickname, Pageable pageable);
	@Query(
			"Select u From UserEntity u Where u.fullname like ?1 and u.isDelete = 0"
			)
	Page<UserEntity> findByFullname(String fullname, Pageable pageable);
	
	@Query(
			"Select u From UserEntity u Where u.email like ?1 and u.isDelete = 0"
			)
	Page<UserEntity> findByEmail(String email, Pageable pageable);
	
	@Query(
			"Select u From UserEntity u Where u.email = ?1 and u.isDelete = 0"
			)
	UserEntity findOneByEmail(String email);
	
	@Query(
			value="Select count(*) from user u where u.username = ?1 and u.is_delete = 0", nativeQuery = true
			)
	Integer findOneByUsername(String username);

	@Query(
			"Select u From UserEntity u Where u.phone = ?1 and u.isDelete = 0"
	)
	UserEntity findOneByPhone(String phone);
	
	@Query(
			"Select u From UserEntity u Where u.username = ?1 and u.isDelete = 0"
	)
	UserEntity findOneBy_Username(String username);
	
	@Query(
			value="select "
					+ "	(select count(*) from user where is_delete =0 and status_active != 3 and role_id in(1,5) and DATE(create_date) <= DATE(?1) ) as count_1,"
					+ " (select count(*) from user u inner join counselor c on u.id = c.id where  u.status_active in(0,1) and c.status in(1,2) and DATE(u.create_date) <= DATE(?1)) as count_2,"
					+ " (select count(*) from business where is_delete =0 and contract_status in(1,3) and DATE(create_date) <= DATE(?1)) as count_3,"
					+ " (select count(*) from counseling_center where is_delete =0 and status in (0,1,3,4)  and DATE(create_date) <= DATE(?1) ) as count_4,"
					+ " (select count(*) from business b inner join user u on b.id =u.id"
					+ " where DATEDIFF(NOW(), b.end_contract_date) < 90 and u.is_delete =0 ) as count_5"
					,nativeQuery=true
			)	
	List<Object[]> totalUserHome(Date date);
	
	@Query(
			value="select (select count(*) from counselor where is_delete =0 and status = 0) as count_1,"
					+ " (select count(*) from counselor_temp where status =0 and status = 0) as count_2,"
					+ " (select count(*) from promotion_page where status =0) as count_3",nativeQuery=true
			)
	List<Object[]> totalRequestAndCounselingHome();
	
	@Query(
			value="SELECT count(case when  DATE(DATE_ADD(?1,INTERVAL 0 DAY))= DATE(e.create_date) then e.id end) As Day1, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 1 DAY))= DATE(e.create_date) then e.id end) As Day2, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 2 DAY))= DATE(e.create_date) then e.id end) As Day3, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 3 DAY))= DATE(e.create_date) then e.id end) As Day4, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 4 DAY))= DATE(e.create_date) then e.id end) As Day5, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 5 DAY))= DATE(e.create_date) then e.id end) As Day6, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 6 DAY))= DATE(e.create_date) then e.id end) As Day7 "
					+ "from user e where is_delete = 0 and role_id in (1,5) "
					,nativeQuery=true
			)
	List<Object[]> getDataChart_JoinNumber( Date date);
	
	@Query(
			value="SELECT count(case when  DATE(DATE_ADD(?1,INTERVAL 0 DAY))= DATE(e.withdrawal_date) then e.id end) As Day1, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 1 DAY))= DATE(e.withdrawal_date) then e.id end) As Day2, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 2 DAY))= DATE(e.withdrawal_date) then e.id end) As Day3, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 3 DAY))= DATE(e.withdrawal_date) then e.id end) As Day4, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 4 DAY))= DATE(e.withdrawal_date) then e.id end) As Day5, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 5 DAY))= DATE(e.withdrawal_date) then e.id end) As Day6, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 6 DAY))= DATE(e.withdrawal_date) then e.id end) As Day7 "
					+ "from user e where status_active = ?2 "
					,nativeQuery=true
			)
	List<Object[]> getDataChart_RemoveAccount( Date date, Short status);
	
	@Query(
			value=  "SELECT case when sum(e.login_number) is null then 0 else sum(e.login_number) end from report_daily e where DATE(e.create_date) = DATE(DATE_ADD(?1,INTERVAL 0 DAY)) "
					+ "union "
					+ "SELECT case when sum(e.login_number) is null then 0 else sum(e.login_number) end from report_daily e where DATE(e.create_date) = DATE(DATE_ADD(?1,INTERVAL 1 DAY)) "
					+ " union "
					+ " SELECT case when sum(e.login_number) is null then 0 else sum(e.login_number) end from report_daily e where DATE(e.create_date) = DATE(DATE_ADD(?1,INTERVAL 2 DAY)) "
					+ "union "
					+ "SELECT case when sum(e.login_number) is null then 0 else sum(e.login_number) end from report_daily e where DATE(e.create_date) = DATE(DATE_ADD(?1,INTERVAL 3 DAY)) "
					+ "union "
					+ "SELECT case when sum(e.login_number) is null then 0 else sum(e.login_number) end from report_daily e where DATE(e.create_date) = DATE(DATE_ADD(?1,INTERVAL 4 DAY)) "
					+ "union "
					+ "SELECT case when sum(e.login_number) is null then 0 else sum(e.login_number) end from report_daily e where DATE(e.create_date) = DATE(DATE_ADD(?1,INTERVAL 5 DAY)) "
					+ "union "
					+ "SELECT case when sum(e.login_number) is null then 0 else sum(e.login_number) end from report_daily e where DATE(e.create_date) = DATE(DATE_ADD(?1,INTERVAL 6 DAY)) "
,nativeQuery=true
			)
	Object[] getDataChart_LoginNumber( Date date);
	
	@Query(
			value="SELECT "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 0 DAY))= DATE(e.end_time) then e.id end) As Day1, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 1 DAY))= DATE(e.end_time) then e.id end) As Day2, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 2 DAY))= DATE(e.end_time) then e.id end) As Day3, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 3 DAY))= DATE(e.end_time) then e.id end) As Day4, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 4 DAY))= DATE(e.end_time) then e.id end) As Day5, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 5 DAY))= DATE(e.end_time) then e.id end) As Day6, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 6 DAY))= DATE(e.end_time) then e.id end) As Day7 "
					+ "from counseling_session e where status !=0 and e.counsel_type =2 "
					,nativeQuery=true
			)
	List<Object[]> getDataChart_ReservationNumber( Date date);
	
	@Query(
			value="SELECT "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 0 DAY))= DATE(e.end_time) then e.id end) As Day1, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 1 DAY))= DATE(e.end_time) then e.id end) As Day2, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 2 DAY))= DATE(e.end_time) then e.id end) As Day3, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 3 DAY))= DATE(e.end_time) then e.id end) As Day4, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 4 DAY))= DATE(e.end_time) then e.id end) As Day5, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 5 DAY))= DATE(e.end_time) then e.id end) As Day6, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 6 DAY))= DATE(e.end_time) then e.id end) As Day7 "
					+ "from counseling_session e where status !=0 and e.counsel_type =1 "
					,nativeQuery=true
			)
	List<Object[]> getDataChart_CounselingNumber( Date date);
	
	@Query(
			value="SELECT "
					+ "sum(case when  DATE(DATE_ADD(?1,INTERVAL 0 DAY))= DATE(e.create_date) then e.coin end) As Day1, "
					+ "sum(case when  DATE(DATE_ADD(?1,INTERVAL 1 DAY))= DATE(e.create_date) then e.coin end) As Day2, "
					+ "sum(case when  DATE(DATE_ADD(?1,INTERVAL 2 DAY))= DATE(e.create_date) then e.coin end) As Day3, "
					+ "sum(case when  DATE(DATE_ADD(?1,INTERVAL 3 DAY))= DATE(e.create_date) then e.coin end) As Day4, "
					+ "sum(case when  DATE(DATE_ADD(?1,INTERVAL 4 DAY))= DATE(e.create_date) then e.coin end) As Day5, "
					+ "sum(case when  DATE(DATE_ADD(?1,INTERVAL 5 DAY))= DATE(e.create_date) then e.coin end) As Day6, "
					+ "sum(case when  DATE(DATE_ADD(?1,INTERVAL 6 DAY))= DATE(e.create_date) then e.coin end) As Day7 "
					+ "from payment_history e inner join counseling_session c on e.id = c.payment_id where e.type_coin = 1 and e.type_payment = 2 and c.status !=0"
					,nativeQuery=true
			)
	List<Object[]> getDataChart_CounselingFee( Date date);
	
	@Query(
			value="select("
					+ "(select case when sum(p.coin) is null then 0 else sum(p.coin) end FROM payment_history p where p.type_coin = 1 and p.status = 1 and p.type_payment = 1 and p.user_id = ?1) - "
					+ "(select case when sum(p.coin) is null then 0 else sum(p.coin) end FROM payment_history p where p.type_coin = 1 and p.status = 1 and p.type_payment = 2 and p.user_id = ?1  )"
					+ ") as totalCoin"
					,nativeQuery=true
			)
	Integer getTotalCoin( Integer userId);
	
	@Modifying
	@Query(
			value="update user u set u.heart =("
					+ "(select case when sum(p.coin) is null then 0 else sum(p.coin) end FROM payment_history p where p.type_coin = 2 and p.status = 1 and p.type_payment = 1  and p.user_id = u.id) - "
					+ "(select case when sum(p.coin) is null then 0 else sum(p.coin) end FROM payment_history p where p.type_coin = 2 and p.status = 1 and p.type_payment = 2  and p.user_id = u.id)"
					+ ") where id = ?1"
					,nativeQuery=true
			)
	void updateHeartByUserId( Integer userId);
	
	@Modifying
	@Query(
			value="update user set voucher_number = (select count(*) from voucher_user where status_use = 0 and user_id = ?1) "
					+ " where id = ?1"
					,nativeQuery=true
			)
	void updateVoucherNumberByUserId( Integer userId);

	UserEntity findByFullnameAndPhone(String fullname, String phone);

	UserEntity findByFullnameAndUsernameAndPhone(String fullname, String username, String phone);
	
	@Query("Select u From UserEntity u Where u.email = ?1 and DATE(u.dayOfBirth) = ?2 and u.isDelete = 0 ")
	UserEntity findOneByEmailAndBirthDay(String email, Date birthDay);
}
