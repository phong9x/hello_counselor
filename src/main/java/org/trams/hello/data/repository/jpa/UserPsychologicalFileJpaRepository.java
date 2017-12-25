package org.trams.hello.data.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.UserPsychologicalFileEntity;

/**
 * Repository : UserPsychologicalFile.
 */
public interface UserPsychologicalFileJpaRepository extends PagingAndSortingRepository<UserPsychologicalFileEntity, Integer> {
	@Query("Select u From UserPsychologicalFileEntity u ")
	Page<UserPsychologicalFileEntity> listPaging(Pageable pageable);
	
	@Query(value="Select uf.id, u.id as userPsychologicalId, p.test_name, r.status, uf.file_name, uf.file_url, "
			+ " uf.create_date, uf.update_date , p.id as psychologicalTestSettingId "
			+ " From user_psychological u LEFT JOIN user_psychological_file uf ON u.id = uf.user_psychological_id "
			+ " JOIN psychological_test_setting p on u.psychological_test_id = p.id "
			+ " INNER JOIN request_user_psychological r on u.request_psychological_id = r.id"
			+ " where u.request_psychological_id = ?1 and r.status = 1 order by uf.create_date DESC ", nativeQuery=true)
	List<Object[]> listByRequestId(Integer reqId);
	
	@Query(value="Select uf.id, u.id as userPsychologicalId, p.test_name, r.status, uf.file_name, uf.file_url, "
			+ " uf.create_date, uf.update_date , p.id as psychologicalTestSettingId "
			+ " From user_psychological u LEFT JOIN user_psychological_file uf ON u.id = uf.user_psychological_id "
			+ " INNER JOIN request_user_psychological r ON u.request_psychological_id = r.id "
			+ " JOIN psychological_test_setting p on u.psychological_test_id = p.id where u.user_id = ?1 and r.status = 1 order by p.test_name ASC, uf.create_date  DESC ", nativeQuery=true)
	List<Object[]> listByUserId(Integer userId);
	
	@Query(value="Select uf.id, u.id as userPsychologicalId, p.test_name, r.status, uf.file_name, uf.file_url, "
			+ " uf.create_date, uf.update_date , p.id as psychologicalTestSettingId "
			+ " From user_psychological u LEFT JOIN user_psychological_file uf ON u.id = uf.user_psychological_id "
			+ " INNER JOIN request_user_psychological r ON u.request_psychological_id = r.id "
			+ " JOIN psychological_test_setting p on u.psychological_test_id = p.id where u.request_psychological_id = ?1 order by uf.create_date DESC ", nativeQuery=true)
	List<Object[]> listByRequestPsychologicalIdInWebCouselor(Integer request_psychological_id);
	
	@Query(value="Select DISTINCT(p.id), p.test_name "
			+ " From user_psychological u LEFT JOIN user_psychological_file uf ON u.id = uf.user_psychological_id  INNER JOIN request_user_psychological r ON u.request_psychological_id = r.id  "
			+ "JOIN psychological_test_setting p on u.psychological_test_id = p.id where u.user_id = ?1 and r.status = 1  order by uf.create_date DESC ", nativeQuery=true)
	List<Object[]> listUserPsyTestIds(Integer userId);
	
	
	@Query(value="Select uf.id, u.id as userPsychologicalId, p.test_name, r.status, uf.file_name, uf.file_url, "
			+ " uf.create_date, uf.update_date , p.id as psychologicalTestSettingId "
			+ " From user_psychological u LEFT JOIN user_psychological_file uf ON u.id = uf.user_psychological_id "
			+ " INNER JOIN request_user_psychological r ON u.request_psychological_id = r.id "
			+ " JOIN psychological_test_setting p on u.psychological_test_id = p.id where u.user_id = ?1 and p.id = ?2 and r.status = 1 order by uf.create_date DESC ", nativeQuery=true)
	List<Object[]> listByUserIdAndPsyTestSetting(Integer userId, Integer testSettingId) ;
}
