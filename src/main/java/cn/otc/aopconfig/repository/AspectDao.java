package cn.otc.aopconfig.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import cn.otc.aopconfig.model.Aspect;
import cn.otc.aopconfig.model.Bean;

@Repository
public interface AspectDao extends PagingAndSortingRepository<Aspect,Long>,
		JpaSpecificationExecutor<Aspect> {

	Aspect findByRefBean(Bean bean);
}
