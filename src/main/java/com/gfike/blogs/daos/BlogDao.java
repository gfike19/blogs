package com.gfike.blogs.daos;

import com.gfike.blogs.models.Blog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface BlogDao extends CrudRepository<Blog, Integer> {
}
