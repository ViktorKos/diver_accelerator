package com.example.diver_accelerator.repository;


import com.example.diver_accelerator.entity.Immersion;
import com.example.diver_accelerator.transientClasses.ImmersionStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository


public interface ImmersionRepository extends JpaRepository<Immersion, String> {
    @Query("SELECT new com.example.diver_accelerator.transientClasses.ImmersionStatistic(i, count(v)) FROM Immersion i inner join Visit v ON v.immersion.max_deep = i.max_deep group by (i.max_deep)")
    List<ImmersionStatistic> countImmersions(@Param("m") int month);
//todo
@Query("SELECT new com.example.diver_accelerator.transientClasses.ImmersionStatistic(d, count(v)) FROM Immersion d inner join Visit v ON v.immersion.max_deep = d.max_deep where month (v.date) =:m group by (d.max_deep)")
List<ImmersionStatistic> countImmersionMonth(@Param("m") int month);
}
