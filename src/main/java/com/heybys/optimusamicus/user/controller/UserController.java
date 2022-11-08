package com.heybys.optimusamicus.user.controller;

// @Slf4j
// @RestController
// @LogExecutionTime
// @RequiredArgsConstructor
// @RequestMapping("/api/v1/users")
public class UserController {

  // private final UserService userService;

  // private final UserGroupService userGroupService;

  // private final UserValidator userValidator;

  // @GetMapping("/{userId}")
  // public ResponseEntity<CommonResponse> retrieveUser(@PathVariable Long userId) {
  //
  //   try {
  //     User retrievedUser = userService.retrieveUser(userId);
  //
  //     UserCommon.Response response = UserCommon.Response.from(retrievedUser);
  //
  //     return ResponseEntity.ok(CommonResponse.success(response));
  //   } catch (Exception e) {
  //     throw new UserNotFoundException(e);
  //   }
  // }

  // @GetMapping("")
  // public ResponseEntity<CommonResponse> retrieveUsers(
  //     UserSearch.Request request, @PageableDefault() Pageable pageable)  {
  //
  //   try {
  //     List<User> retrievedUsers = userService.retrieveUsers(request, pageable);
  //
  //     List<UserCommon.Response> responses =
  //         retrievedUsers.stream().map(UserCommon.Response::from).collect(Collectors.toList());
  //
  //     return ResponseEntity.ok(CommonResponse.success(responses));
  //   } catch (Exception e) {
  //     throw new UserNotFoundException(e);
  //   }
  // }

  // @PostMapping("")
  // public ResponseEntity<CommonResponse> createUser(@RequestBody @Valid UserCreate.Request
  // request) {
  //
  //   try {
  //     User user = request.toUser();
  //     Long userGroupId = request.getUserGroupId();
  //     if (userGroupId != null) {
  //       user.joinTo(userGroupService.retrieveUserGroup(userGroupId));
  //     }
  //
  //     User createdUser = userService.createUser(user);
  //
  //     UserCommon.Response response = UserCommon.Response.from(createdUser);
  //     URI uri = URI.create("api/v1/users/" + response.getId());
  //
  //     return ResponseEntity.created(uri).body(CommonResponse.success(response));
  //   } catch (Exception e) {
  //     throw new UserNotCreatedException(e);
  //   }
  // }

  // @PatchMapping("/{userId}")
  // public ResponseEntity<CommonResponse> patchUser(
  //     @PathVariable Long userId, @RequestBody Map<String, Object> params) {
  //
  //   try {
  //     userValidator.validate(params);
  //
  //     User patchedUser = userService.updateUser(userId, params);
  //
  //     UserCommon.Response response = UserCommon.Response.from(patchedUser);
  //
  //     return ResponseEntity.ok().body(CommonResponse.success(response));
  //   } catch (Exception e) {
  //     throw new UserNotPatchedException(e);
  //   }
  // }
}
