package com.linan.blogging_platform.repository;

import com.linan.blogging_platform.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    @Query(value = "SELECT * FROM blog_table WHERE :tag = ANY(tags)", nativeQuery = true)
    List<Blog> findAllByTag(@Param("tag") String tag);

}
