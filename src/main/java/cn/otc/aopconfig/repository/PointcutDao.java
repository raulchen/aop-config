package cn.otc.aopconfig.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import cn.otc.aopconfig.model.Pointcut;

@Repository
public interface PointcutDao extends PagingAndSortingRepository<Pointcut,Long>,
		JpaSpecificationExecutor<Pointcut> {

	Pointcut findByExpression(String expression);
}
