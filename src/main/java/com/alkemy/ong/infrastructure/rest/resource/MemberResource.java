package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.member.usecase.ICreateMemberUseCase;
import com.alkemy.ong.application.service.member.usecase.IDeleteMemberUseCase;
import com.alkemy.ong.application.service.member.usecase.IListMemberUseCase;
import com.alkemy.ong.application.service.member.usecase.IUpdateMemberUseCase;
import com.alkemy.ong.domain.Member;
import com.alkemy.ong.infrastructure.common.PaginatedResultsRetrieved;
import com.alkemy.ong.infrastructure.rest.mapper.member.CreateMemberMapper;
import com.alkemy.ong.infrastructure.rest.mapper.member.ListMemberMapper;
import com.alkemy.ong.infrastructure.rest.mapper.member.UpdateMemberMapper;
import com.alkemy.ong.infrastructure.rest.request.member.CreateMemberRequest;
import com.alkemy.ong.infrastructure.rest.request.member.UpdateMemberRequest;
import com.alkemy.ong.infrastructure.rest.response.member.GetMemberResponse;
import com.alkemy.ong.infrastructure.rest.response.news.ListMemberResponse;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberResource {

  private final IDeleteMemberUseCase deleteMemberUseCase;
  private final IListMemberUseCase listMemberUseCase;
  private final ListMemberMapper listMemberMapper;
  private final PaginatedResultsRetrieved resultsRetrieved;
  private final ICreateMemberUseCase createMemberUseCase;
  private final IUpdateMemberUseCase updateMemberUseCase;
  private final CreateMemberMapper createMemberMapper;
  private final UpdateMemberMapper updateMemberMapper;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ListMemberResponse> list(@PageableDefault(size = 10) Pageable pageable,
      UriComponentsBuilder uriBuilder, HttpServletResponse response) {
    Page<Member> resultPage = listMemberUseCase.findAll(pageable);
    resultsRetrieved.addLinkHeaderOnPagedResourceRetrieval(
        uriBuilder,
        response,
        "/members",
        resultPage.getNumber(),
        resultPage.getTotalPages(),
        resultPage.getSize()
    );
    return ResponseEntity.ok().body(listMemberMapper.toResponse(resultPage));
  }

  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    deleteMemberUseCase.delete(() -> id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GetMemberResponse> add(
      @Valid @RequestBody CreateMemberRequest createMemberRequest) {
    Member member = createMemberMapper.toDomain(createMemberRequest);
    Member createdMember = createMemberUseCase.add(member);
    return new ResponseEntity<>(createMemberMapper.toResponse(createdMember), HttpStatus.CREATED);
  }

  @PutMapping(
      value = "{id}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GetMemberResponse> update(
      @PathVariable Long id,
      @Valid @RequestBody UpdateMemberRequest request) {
    Member updatedMember = updateMemberUseCase.update(updateMemberMapper.toDomain(id, request));
    return ResponseEntity.ok().body(updateMemberMapper.toResponse(updatedMember));
  }

}
