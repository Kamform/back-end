package com.design.platform.resourceplatform.interfaces;


import com.design.platform.resourceplatform.services.UserService;
import com.design.platform.resourceplatform.transfer.*;
import com.design.platform.resourceplatform.transfer.params.PageQuery;
import com.design.platform.resourceplatform.transfer.patch.UserFollow;
import com.design.platform.resourceplatform.transfer.results.PageHolder;
import org.springframework.web.bind.annotation.*;

//      GET /               用户列表       无权限
//      GET /{id}           单个用户信息   开放权限
//      GET /{id}/followers 用户的关注者   开放权限
//      GET /{id}/followed  用户关注的人   开放权限
//      GET /{id}/published 用户发布的资源 开放权限
//      GET /{id}/favorites 用户收藏的资源 开放权限
//      GET /{id}/files     用户上传的文件 主人权限
//      POST /              更新用户信息   主人权限
//      PUT /               注册用户       开放权限
//      PATCH /follow       关注用户       用户权限
//      DELETE /            注销用户       主人权限

@RestController
@RequestMapping("/api/user")
public class UserController {

    // Autowired
    // ===============================================

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // Methods GET
    // ===============================================

    @GetMapping
    public PageHolder<UserBooth> GetUserList(PageQuery query) {
        return service.GetUserBoothList(query.toRequest());
    }

    @GetMapping("/{id}/followers")
    public PageHolder<UserBooth> GetUserFollowersList(@PathVariable int id, PageQuery query) {
        return service.GetUserFollowersBoothList(id, query.toRequest());
    }

    @GetMapping("/{id}/followed")
    public PageHolder<UserBooth> GetUserFollowedList(@PathVariable int id, PageQuery query) {
        return service.GetUserFollowedBoothList(id, query.toRequest());
    }

    @GetMapping("/{id}/published")
    public PageHolder<ResourceBooth> GetUserPublished(@PathVariable int id, PageQuery query) {
        return service.GetUserPublishedBoothList(id, query.toRequest());
    }

    @GetMapping("/{id}/favorites")
    public PageHolder<ResourceBooth> GetUserFavorites(@PathVariable int id, PageQuery query) {
        return service.GetUserFavoritesBoothList(id, query.toRequest());
    }

    @GetMapping("/{id}/files")
    public PageHolder<FileBooth> GetUserFiles(@PathVariable int id, PageQuery query) {
        return service.GetUserFilesBoothList(id, query.toRequest());
    }

    @GetMapping("/{id}")
    public UserBooth GetUser(@PathVariable int id) {
        return service.GetUserBooth(id);
    }

    // Methods POST
    // ===============================================

    @PostMapping
    public void UpdateUser(@RequestBody UserRecorder recorder) {
        service.UpdateUser(recorder);
    }

    @PutMapping
    public void CreateUser(@RequestBody UserDefiner definer) {
        service.CreateUser(definer);
    }

    @PatchMapping
    public void FollowUser(@RequestBody UserFollow follow) {
        service.FollowUser(follow);
    }

    @DeleteMapping("/{id}")
    public void DestroyUser(@PathVariable int id) {
        service.DestroyUser(id);
    }
}
