package com.heybys.optimusamicus.user.controller;

import com.heybys.optimusamicus.common.aspect.LogExecutionTime;
import com.heybys.optimusamicus.common.model.CommonResponse;
import com.heybys.optimusamicus.common.model.CommonResponse.StatusCode;
import com.heybys.optimusamicus.user.dto.UserGroupCreate;
import com.heybys.optimusamicus.user.dto.UserGroupSearch;
import com.heybys.optimusamicus.user.entity.UserGroup;
import com.heybys.optimusamicus.user.service.UserGroupService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user-groups")
@RequiredArgsConstructor
public class UserGroupController {

  private final UserGroupService userGroupService;

  @GetMapping("/{userGroupId}")
  @LogExecutionTime
  public ResponseEntity<CommonResponse> retrieveUserGroup(@PathVariable Long userGroupId) {

    UserGroup retrievedUserGroup = userGroupService.retrieveUserGroup(userGroupId);

    UserGroupSearch.Response response =
        UserGroupSearch.Response.builder().userGroup(retrievedUserGroup).build();

    return new ResponseEntity<>(new CommonResponse(StatusCode.SUCCESS, response), HttpStatus.OK);
  }

  @PostMapping("")
  @LogExecutionTime
  public ResponseEntity<CommonResponse> createUser(
      @RequestBody @Valid UserGroupCreate.Request request) {

    UserGroup userGroup = request.toUserGroup();

    UserGroup createdUserGroup = userGroupService.createUserGroup(userGroup);

    UserGroupCreate.Response response =
        UserGroupCreate.Response.builder().userGroup(createdUserGroup).build();

    return new ResponseEntity<>(
        new CommonResponse(StatusCode.SUCCESS, response), HttpStatus.CREATED);
  }
}
