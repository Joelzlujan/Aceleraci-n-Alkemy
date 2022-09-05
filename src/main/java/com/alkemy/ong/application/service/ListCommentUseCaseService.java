package com.alkemy.ong.application.service;

import com.alkemy.ong.application.repository.ICommentRepository;
import com.alkemy.ong.application.service.usecase.IListCommentUseCase;
import com.alkemy.ong.domain.Comment;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ListCommentUseCaseService implements IListCommentUseCase {

  private final ICommentRepository commentRepository;

  @Override
  public List<Comment> findAllOrderedByTimestamp() {
    return commentRepository.findAllOrderedByTimestamp();
  }
}