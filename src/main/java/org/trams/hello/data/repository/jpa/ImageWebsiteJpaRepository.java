package org.trams.hello.data.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.ImageWebsiteEntity;

/**
 * Repository : ImageWebsite.
 */
public interface ImageWebsiteJpaRepository extends PagingAndSortingRepository<ImageWebsiteEntity, Integer> {
	@Query("Select u From ImageWebsiteEntity u ")
	Page<ImageWebsiteEntity> listPaging(Pageable pageable);
	
	@Query("Select u From ImageWebsiteEntity u where u.type = ?1 order by u.createDate DESC ")
	List<ImageWebsiteEntity> list(Short type);

}
