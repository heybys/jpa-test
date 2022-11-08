package com.heybys.optimusamicus.user.controller;

import com.heybys.optimusamicus.common.annotation.LogExecutionTime;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@LogExecutionTime
@RequiredArgsConstructor
@RequestMapping("/api/v1/user-groups")
public class UserGroupController {

  // private final UserGroupService userGroupService;

  // @GetMapping("/{userGroupId}")
  // public ResponseEntity<CommonResponse> retrieveUserGroup(@PathVariable Long userGroupId) {
  //
  //   try {
  //     UserGroup retrievedUserGroup = userGroupService.retrieveUserGroup(userGroupId);
  //
  //     UserGroupCommon.Response response = UserGroupCommon.Response.from(retrievedUserGroup);
  //
  //     return ResponseEntity.ok(CommonResponse.success(response));
  //   } catch (Exception e) {
  //     throw new UserGroupNotFoundException();
  //   }
  // }

  // @PostMapping("")
  // public ResponseEntity<CommonResponse> createUserGroup(
  //     @RequestBody @Valid UserGroupCreate.Request request) {
  //
  //   try {
  //     UserGroup userGroup = request.toUserGroup();
  //
  //     UserGroup createdUserGroup = userGroupService.createUserGroup(userGroup);
  //
  //     UserGroupCommon.Response response = UserGroupCommon.Response.from(createdUserGroup);
  //     URI uri = URI.create("/api/v1/user-groups/" + response.getId());
  //
  //     return ResponseEntity.created(uri).body(CommonResponse.success(response));
  //   } catch (Exception e) {
  //     throw new UserGroupNotCreatedException();
  //   }
  // }
}
