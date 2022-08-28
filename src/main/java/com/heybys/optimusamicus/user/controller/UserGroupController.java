package com.heybys.optimusamicus.user.controller;

import com.heybys.optimusamicus.common.aspect.LogExecutionTime;
import com.heybys.optimusamicus.common.model.CommonResponse;
import com.heybys.optimusamicus.common.model.CommonResponse.StatusCode;
import com.heybys.optimusamicus.user.dto.create.UserGroupCreate;
import com.heybys.optimusamicus.user.dto.search.UserGroupSearch;
import com.heybys.optimusamicus.user.entity.UserGroup;
import com.heybys.optimusamicus.user.exception.UserGroupNotCreatedException;
import com.heybys.optimusamicus.user.exception.UserGroupNotFoundException;
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
@LogExecutionTime
public class UserGroupController {

  private final UserGroupService userGroupService;

  @GetMapping("/{userGroupId}")
  public ResponseEntity<CommonResponse> retrieveUserGroup(@PathVariable Long userGroupId) {

    try {
      // call service
      UserGroup retrievedUserGroup = userGroupService.retrieveUserGroup(userGroupId);

      // convert entity to dto
      UserGroupSearch.Response response = UserGroupSearch.Response.from(retrievedUserGroup);

      return new ResponseEntity<>(new CommonResponse(StatusCode.SUCCESS, response), HttpStatus.OK);
    } catch (Exception e) {
      throw new UserGroupNotFoundException();
    }
  }

  @PostMapping("")
  public ResponseEntity<CommonResponse> createUserGroup(
      @RequestBody @Valid UserGroupCreate.Request request) {

    try {
      // convert dto to entity
      UserGroup userGroup = request.toUserGroup();

      // call service
      UserGroup createdUserGroup = userGroupService.createUserGroup(userGroup);

      // convert entity to dto
      UserGroupCreate.Response response = UserGroupCreate.Response.from(createdUserGroup);

      return new ResponseEntity<>(
          new CommonResponse(StatusCode.SUCCESS, response), HttpStatus.CREATED);
    } catch (Exception e) {
      throw new UserGroupNotCreatedException();
    }
  }
}
