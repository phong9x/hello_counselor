package org.trams.hello.data.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.CommentEntity;
/**
 * Repository : Comment.
 */
public interface CommentJpaRepository extends PagingAndSortingRepository<CommentEntity, Integer>, JpaSpecificationExecutor<CommentEntity> {
	@Query(
			"Select u From CommentEntity u "
			)
	Page<CommentEntity> listPaging(Pageable pageable);
	
	@Query(
			"Select u From CommentEntity u where u.counselorId = ?1"
			)
	Page<CommentEntity> listPagingBy_CounsolorId(Integer counsolorId, Pageable pageable);
	


}
