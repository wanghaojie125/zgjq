package com.zhangongjx.controller;

import com.zhangongjx.infrastructure.util.Base64Helper;
import com.zhangongjx.message.auth.request.*;
import com.zhangongjx.message.auth.response.*;
import com.zhangongjx.message.auth.view.UserViewModel;
import com.zhangongjx.repository.s.IUserRepository;
import com.zhangongjx.service.IAuthService;
import com.zhangongjx.auth.User;
import com.zhangongjx.infrastructure.domain.ResutlMsg;
import com.zhangongjx.message.auth.view.UserViewModel;
import com.zhangongjx.model.s.UserEntity;
import com.zhangongjx.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IAuthService authService;
    @Autowired
    private IUserRepository userRepository;

    //dep
    @RequestMapping(value = "/dep", method = RequestMethod.GET)
    public String dep() {
        return "permission/dep";
    }

    @ResponseBody
    @RequestMapping(value = "/dep_datas", method = RequestMethod.GET)
    public ResutlMsg<Object> getDepDatas(int draw, int length, int start) {
        DepDataRequest req = new DepDataRequest();
        req.setDraw(draw);
        req.setLength(length);
        req.setStart(start);

        DepDataResponse resp = authService.getDepDatas(req);
        return resp.getResultMsg();
    }

    @ResponseBody
    @RequestMapping(value = "/dep_all_datas", method = RequestMethod.GET)
    public ResutlMsg<Object> getAllDepDatas() {
        DepDataRequest req = new DepDataRequest();
        DepDataResponse resp = authService.getAllDepDatas(req);
        return resp.getResultMsg();
    }

    @ResponseBody
    @RequestMapping(value = "/dep_data", method = RequestMethod.GET)
    public ResutlMsg<Object> getDepData(int id) {
        DepDataRequest req = new DepDataRequest();
        req.setId(id);

        DepDataResponse resp = authService.getDepData(req);
        return resp.getResultMsg();
    }

    @ResponseBody
    @RequestMapping(value = "/save_dep", method = RequestMethod.POST)
    public ResutlMsg saveDep(@RequestBody SaveDepRequest req) {
        if (req == null) {
            return ResutlMsg.error("未接受到请求参数");
        }
        SaveDepResponse resp = authService.saveDep(req);
        return resp.getResultMsg();
    }

    @ResponseBody
    @RequestMapping(value = "/delete_dep", method = RequestMethod.POST)
    public ResutlMsg deleteDep(@RequestBody DeleteDepRequest req) {
        if (req == null) {
            return ResutlMsg.error("未接受到请求参数");
        }
        DeleteDepResponse resp = authService.deleteDep(req);
        return resp.getResultMsg();
    }

    @ResponseBody
    @RequestMapping(value = "/enable_dep", method = RequestMethod.POST)
    public ResutlMsg enableDep(@RequestBody EnableDepRequest req) {
        if (req == null) {
            return ResutlMsg.error("未接受到请求参数");
        }
        EnableDepResponse resp = authService.enabledDep(req);
        return resp.getResultMsg();
    }

    //source
    @ResponseBody
    @RequestMapping(value = "/sources", method = RequestMethod.GET)
    public ResutlMsg<Object> sources(int type, int id) {
        GetSourceRequest req = new GetSourceRequest();
        req.setType(type);
        req.setId(id);
        GetSourceResponse resp = authService.getSources(req);
        return resp.getResultMsg();
    }

    /**
     * 菜单列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cur_sources", method = RequestMethod.GET)
    public ResutlMsg<Object> curSources() {
        GetSourceResponse resp = authService.getCurSources(User.getUser_id(), User.getDep_id());
        return resp.getResultMsg();
    }

    @ResponseBody
    @RequestMapping(value = "/cur_user", method = RequestMethod.GET)
    public ResutlMsg<Object> curUser() {
        ResutlMsg res = ResutlMsg.success();

        UserEntity resp = userRepository.getData(User.getUser_id());
        UserViewModel vi = new UserViewModel();
        vi.setUsername(resp.getUser_name());
        vi.setHead_portraits(resp.getHead_portraits());
        vi.setDep(resp.getDep_name());

        res.setData(vi);
        return res;
    }


    @ResponseBody
    @RequestMapping(value = "/save_sources", method = RequestMethod.POST)
    public ResutlMsg saveSources(@RequestBody SaveSourceRequest req) {
        if (req == null) {
            return ResutlMsg.error("未接受到请求参数");
        }
        SavetSourceResponse resp = authService.saveSources(req);
        return resp.getResultMsg();
    }

    //user
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user() {
        return "permission/user";
    }

    @ResponseBody
    @RequestMapping(value = "/user_datas", method = RequestMethod.GET)
    public ResutlMsg<Object> getUserDatas(int draw, int length, int start) {
        UserDataRequest req = new UserDataRequest();
        req.setDraw(draw);
        req.setLength(length);
        req.setStart(start);

        UserDataResponse resp = authService.getUserDatas(req);
        return resp.getResultMsg();
    }

    @ResponseBody
    @RequestMapping(value = "/user_data", method = RequestMethod.GET)
    public ResutlMsg<Object> getUserData(int id) {
        UserDataRequest req = new UserDataRequest();
        req.setId(id);

        UserDataResponse resp = authService.getUserData(req);
        return resp.getResultMsg();
    }

    @ResponseBody
    @RequestMapping(value = "/save_user", method = RequestMethod.POST)
    public ResutlMsg saveUser(@RequestBody SaveUserRequest req) {
        if (req == null) {
            return ResutlMsg.error("未接受到请求参数");
        }
        SaveUserResponse resp = authService.saveUser(req);
        return resp.getResultMsg();
    }

    @ResponseBody
    @RequestMapping(value = "/delete_user", method = RequestMethod.POST)
    public ResutlMsg deleteUser(@RequestBody DeleteUserRequest req) {
        if (req == null) {
            return ResutlMsg.error("未接受到请求参数");
        }
        DeleteUserResponse resp = authService.deleteUser(req);
        return resp.getResultMsg();
    }

    @ResponseBody
    @RequestMapping(value = "/enable_user", method = RequestMethod.POST)
    public ResutlMsg enableUser(@RequestBody EnableUserRequest req) {
        if (req == null) {
            return ResutlMsg.error("未接受到请求参数");
        }
        EnableUserResponse resp = authService.enabledUser(req);
        return resp.getResultMsg();
    }


    @ResponseBody
    @RequestMapping(value = "/get_curr_user", method = RequestMethod.GET)
    public ResutlMsg<Object> getCurrUser() {
        ResutlMsg res = ResutlMsg.success();

        UserViewModel data = new UserViewModel();
        UserEntity item = userRepository.getData(User.getUser_id());
        data.setEmail(item.getEmail());
        data.setHead_portraits(item.getHead_portraits());
        data.setUsername(item.getUser_name());
        res.setData(data);

        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/update_user_sys", method = RequestMethod.POST)
    public ResutlMsg updateUserSys(@RequestBody UserEntity item) {
        ResutlMsg res = ResutlMsg.success();

        if (item == null) {
            return ResutlMsg.error("未接受到请求参数");
        }
        UserEntity user = userRepository.getData(User.getUser_id());
        if (user != null) {
            String old_pwd = Base64Helper.Base64(item.getPass_word());
            if (user.getPass_word().equals(old_pwd)) {
                item.setId(User.getUser_id());
                item.setPass_word(Base64Helper.Base64(item.getConfirm_pass_word()));
                userRepository.updatePwdAndHead(item);
                res.setMsg("修改成功");
            } else {
                return ResutlMsg.error("请输入正确的旧密码");
            }
        }
        return res;
    }
}
