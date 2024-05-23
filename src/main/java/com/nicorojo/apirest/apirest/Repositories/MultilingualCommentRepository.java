package com.nicorojo.apirest.apirest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nicorojo.apirest.apirest.Entities.MultilingualComment;

public interface MultilingualCommentRepository extends JpaRepository<MultilingualComment, String> {

}
