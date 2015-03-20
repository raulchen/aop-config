package cn.otc.aopconfig.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import cn.otc.aopconfig.model.Notice;

@Repository
public interface NoticeDao extends PagingAndSortingRepository<Notice,Long>,
		JpaSpecificationExecutor<Notice> {

}
