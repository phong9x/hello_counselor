package org.trams.hello.data.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.DeviceEntity;
/**
 * Repository : Device.
 */
public interface DeviceJpaRepository extends PagingAndSortingRepository<DeviceEntity, Integer> {
	@Query(
			"Select u From DeviceEntity u "
			)
	Page<DeviceEntity> listPaging(Pageable pageable);

	DeviceEntity findByUuid(String uuid);


}
