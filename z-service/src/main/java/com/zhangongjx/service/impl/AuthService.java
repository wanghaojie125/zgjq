package com.zhangongjx.service.impl;

import com.zhangongjx.exception.AuthUsernameNotFoundException;
import com.zhangongjx.message.auth.request.*;
import com.zhangongjx.message.auth.response.*;
import com.zhangongjx.message.auth.view.SourceViewModel;
import com.zhangongjx.message.auth.view.UserViewModel;
import com.zhangongjx.repository.s.ISourceRepository;
import com.zhangongjx.result.SourceResultEntity;
import com.zhangongjx.exception.AuthLockedErrorException;
import com.zhangongjx.infrastructure.domain.ResutlMsg;
import com.zhangongjx.infrastructure.util.Base64Helper;
import com.zhangongjx.message.auth.AuthUser;
import com.zhangongjx.message.auth.view.DepViewModel;
import com.zhangongjx.model.s.DepEntity;
import com.zhangongjx.model.s.UserEntity;
import com.zhangongjx.repository.s.IDepRepository;
import com.zhangongjx.repository.s.IUserRepository;
import com.zhangongjx.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService implements IAuthService, UserDetailsService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IDepRepository depRepository;
    @Autowired
    private ISourceRepository sourceRepository;
    /*
     * 登录*/
    @Override
    public LoginResponse login(LoginRequest req) throws AuthLockedErrorException {

        LoginResponse resp = new LoginResponse();
        resp.setResultMsg(ResutlMsg.success());

        UserEntity item = userRepository.getUserBy(req.getUserName());
        if (item != null) {
            if (item.getEnabled() == 0) {
                throw new AuthLockedErrorException("用户未启用，请联系平台管理者处理");
            }
            resp.setUser_name(item.getUser_name());
            resp.setPassword(item.getPass_word());
            resp.setDep_id(item.getDep_id());
            resp.setUser_id(item.getId());
        } else {
            resp.setResultMsg(ResutlMsg.error("用户名或密码不正确"));
        }

        return resp;
    }

    /*
     * spring-security 获取用户信息*/
    @Override
    public UserDetails loadUserByUsername(String username) throws AuthUsernameNotFoundException {
        LoginRequest req = new LoginRequest(username, "");
        LoginResponse resp = this.login(req);
        if (resp.getResultMsg().getSuccess() == false) {
            throw new AuthUsernameNotFoundException(resp.getResultMsg().getMsg());
        } else {
            return new AuthUser(resp.getUser_name(), resp.getPassword(), resp.getDep_id(), resp.getUser_id());
        }
    }

    /*
     * 获取部门列表*/
    @Override
    public DepDataResponse getDepDatas(DepDataRequest req) {
        DepDataResponse resp = new DepDataResponse();
        resp.setResultMsg(ResutlMsg.success());

        List<DepEntity> items = depRepository.getDatas(req.getStart(), req.getLength());
        int count = depRepository.getCount();
        List<DepViewModel> data = new ArrayList<DepViewModel>();
        for (DepEntity i : items) {
            DepViewModel vi = new DepViewModel();
            vi.setId(i.getId());
            vi.setName(i.getName());
            vi.setCount(i.getUser_count());
            vi.setDate(i.getCreate_at().toString());
            vi.setDesp(i.getDesp());
            vi.setEnabled(i.getEnabled());
            data.add(vi);
        }
        resp.getResultMsg().setData(data);
        resp.getResultMsg().setDraw(req.getDraw());
        resp.getResultMsg().setRecordsFiltered(count);
        resp.getResultMsg().setRecordsTotal(count);
        return resp;
    }

    /*
     * 获取所有部门*/
    @Override
    public DepDataResponse getAllDepDatas(DepDataRequest req) {
        DepDataResponse resp = new DepDataResponse();
        resp.setResultMsg(ResutlMsg.success());

        List<DepEntity> items = depRepository.getAllDatas();
        List<DepViewModel> data = new ArrayList<DepViewModel>();
        for (DepEntity i : items) {
            DepViewModel vi = new DepViewModel();
            vi.setId(i.getId());
            vi.setName(i.getName());
            data.add(vi);
        }
        resp.getResultMsg().setData(data);
        return resp;
    }

    /*
     * 获取部门列表信息*/
    @Override
    public DepDataResponse getDepData(DepDataRequest req) {
        DepDataResponse resp = new DepDataResponse();
        resp.setResultMsg(ResutlMsg.success());

        DepEntity i = depRepository.getData(req.getId());


        DepViewModel vi = new DepViewModel();
        vi.setId(i.getId());
        vi.setName(i.getName());
        vi.setCount(i.getUser_count());
        vi.setDate(i.getCreate_at().toString());
        vi.setDesp(i.getDesp());
        vi.setEnabled(i.getEnabled());

        resp.getResultMsg().setData(vi);
        return resp;
    }

    /*
     * 保存部门*/
    @Override
    public SaveDepResponse saveDep(SaveDepRequest req) {
        SaveDepResponse resp = new SaveDepResponse();
        resp.setResultMsg(ResutlMsg.success());

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        DepEntity item = new DepEntity();
        item.setId(req.getId());
        item.setName(req.getName());
        item.setDesp(req.getDesp());
        item.setUpdate_at(timestamp.toString());

        if (req.getId() == 0) {
            depRepository.insert(item);
            resp.getResultMsg().setMsg("添加成功");
        } else {
            depRepository.update(item);
            resp.getResultMsg().setMsg("修改成功");
        }
        return resp;
    }

    /*
     * 删除部门*/
    @Override
    public DeleteDepResponse deleteDep(DeleteDepRequest req) {
        DeleteDepResponse resp = new DeleteDepResponse();
        resp.setResultMsg(ResutlMsg.success());


        depRepository.delete(req.getId());
        resp.getResultMsg().setMsg("删除成功");
        return resp;
    }

    /*
     * 部门开关*/
    @Override
    public EnableDepResponse enabledDep(EnableDepRequest req) {
        EnableDepResponse resp = new EnableDepResponse();
        resp.setResultMsg(ResutlMsg.success());

        depRepository.enable(req.getId(), req.getEnabled());
        if (req.getEnabled() == 0) {
            resp.getResultMsg().setMsg("关闭成功");
        } else {
            resp.getResultMsg().setMsg("开启成功");
        }
        return resp;
    }

    /*
     *保存资源*/
    @Override
    public SavetSourceResponse saveSources(SaveSourceRequest req) {
        SavetSourceResponse resp = new SavetSourceResponse();
        resp.setResultMsg(ResutlMsg.success());

        sourceRepository.deleteRelate(req.getId(), req.getType());

        //StringBuffer buffer = new StringBuffer();

        String[] ids = req.getIds().split(",");
        for (String i : ids) {
            sourceRepository.insertRelate(req.getId(), Integer.parseInt(i), req.getType());
            //buffer.append("(" + req.getId() + "," + Integer.parseInt(i) + "," + req.getType() + "),");
        }
        //String strValue = buffer.deleteCharAt(buffer.length()-1).toString();

        resp.getResultMsg().setMsg("权限设置成功");

        return resp;
    }

    /*
     * 获取资源*/
    @Override
    public GetSourceResponse getSources(GetSourceRequest req) {
        GetSourceResponse resp = new GetSourceResponse();
        resp.setResultMsg(ResutlMsg.success());

        ArrayList<SourceResultEntity> items = (ArrayList<SourceResultEntity>) sourceRepository.getSources(req.getType(), req.getId());

        List<SourceViewModel> roots = recursionSource(0, items);
        for (SourceViewModel i : roots) {
            i.setSub(recursionSource(i.getId(), items));
        }
        resp.getResultMsg().setData(roots);
        resp.getResultMsg().setMsg("获取成功");
        return resp;
    }

    @Override
    public GetSourceResponse getCurSources(int userId, int depId) {
        GetSourceResponse resp = new GetSourceResponse();
        resp.setResultMsg(ResutlMsg.success());


        ArrayList<SourceResultEntity> sources = (ArrayList<SourceResultEntity>) sourceRepository.getSources(2, userId);
        if (sources.size() == 0) {
            sources = (ArrayList<SourceResultEntity>) sourceRepository.getSources(2, depId);
        }
        List<SourceViewModel> roots = recursionCurSource(0, sources);
        for (SourceViewModel i : roots) {
            i.setSub(recursionCurSource(i.getId(), sources));
        }
        resp.getResultMsg().setData(roots);
        resp.getResultMsg().setMsg("获取成功");
        return resp;
    }


    /*
     * 获取成员列表*/
    @Override
    public UserDataResponse getUserDatas(UserDataRequest req) {
        UserDataResponse resp = new UserDataResponse();
        resp.setResultMsg(ResutlMsg.success());

        List<UserEntity> items = userRepository.getDatas(req.getStart(), req.getLength());
        int count = userRepository.getCount();
        List<UserViewModel> data = new ArrayList<UserViewModel>();
        for (UserEntity i : items) {
            UserViewModel vi = new UserViewModel();
            vi.setId(i.getId());
            vi.setUsername(i.getUser_name());
            vi.setRealname(i.getReal_name());
            vi.setDate(i.getCreate_at().toString());
            vi.setLastlogin(i.getLast_login_at().toString());
            vi.setDep(i.getDep_name());
            vi.setEmail(i.getEmail());
            vi.setEnabled(i.getEnabled());
            data.add(vi);
        }
        resp.getResultMsg().setData(data);
        resp.getResultMsg().setDraw(req.getDraw());
        resp.getResultMsg().setRecordsFiltered(count);
        resp.getResultMsg().setRecordsTotal(count);
        return resp;
    }

    /*
     * 获取成员信息*/
    @Override
    public UserDataResponse getUserData(UserDataRequest req) {
        UserDataResponse resp = new UserDataResponse();
        resp.setResultMsg(ResutlMsg.success());

        UserEntity i = userRepository.getData(req.getId());
        UserViewModel vi = new UserViewModel();
        vi.setId(i.getId());
        vi.setUsername(i.getUser_name());
        vi.setRealname(i.getReal_name());
        vi.setDep(i.getDep_name());
        vi.setDep_id(i.getDep_id());
        vi.setEmail(i.getEmail());
        vi.setRemark(i.getRemark());

        resp.getResultMsg().setData(vi);
        return resp;
    }

    @Override
    public UserEntity getUserEntityByPhone(String phone) {
        UserEntity userEntity = userRepository.getUserByPhone(phone);
        return userEntity;
    }

    /*
     * 保存成员*/
    @Override
    public SaveUserResponse saveUser(SaveUserRequest req) {
        SaveUserResponse resp = new SaveUserResponse();
        resp.setResultMsg(ResutlMsg.success());
        //校验用户名是否重复
        UserEntity user = userRepository.getUserBy(req.getUsername());
        if (user != null && req.getId() == 0) {
            resp.setResultMsg(ResutlMsg.error("该用户名已经存在"));
            return resp;
        }

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        UserEntity item = new UserEntity();
        item.setId(req.getId());
        item.setUser_name(req.getUsername());
        item.setReal_name(req.getRealname());
        item.setPass_word(Base64Helper.Base64(req.getPwd()));
        item.setDep_id(req.getDep_id());
        item.setDep_name(req.getDep());
        item.setEmail(req.getEmail());
        item.setRemark(req.getRemark());
        item.setUpdate_at(timestamp.toString());
        item.setPhone(req.getPhone());

        if (req.getId() == 0) {
            userRepository.insert(item);
            resp.getResultMsg().setMsg("添加成功");
        } else {
            userRepository.update(item);
            resp.getResultMsg().setMsg("修改成功");
        }
        return resp;
    }

    /*
     * 删除成员*/
    @Override
    public DeleteUserResponse deleteUser(DeleteUserRequest req) {
        DeleteUserResponse resp = new DeleteUserResponse();
        resp.setResultMsg(ResutlMsg.success());

        userRepository.delete(req.getId());
        resp.getResultMsg().setMsg("删除成功");
        return resp;
    }

    /*
     * 成员开关*/
    @Override
    public EnableUserResponse enabledUser(EnableUserRequest req) {
        EnableUserResponse resp = new EnableUserResponse();
        resp.setResultMsg(ResutlMsg.success());

        userRepository.enable(req.getId(), req.getEnabled());
        if (req.getEnabled() == 0) {
            resp.getResultMsg().setMsg("关闭成功");
        } else {
            resp.getResultMsg().setMsg("开启成功");
        }
        return resp;
    }




    private List<SourceViewModel> recursionSource(int par_id, ArrayList<SourceResultEntity> sources) {
        List<SourceViewModel> res = new ArrayList<SourceViewModel>();
        List<SourceResultEntity> sub = sources.stream().filter((SourceResultEntity i) -> par_id == i.getPar_id()).collect(Collectors.toList());
        if (sub.size() == 0) {
            return res;
        }
        for (SourceResultEntity i : sub) {
            SourceViewModel vi = new SourceViewModel();
            vi.setId(i.getId());
            vi.setName(i.getName());
            vi.setCss(i.getCss());
            vi.setSub(recursionSource(i.getId(), sources));
            vi.setChecked(i.getRelate_id() == 0 ? false : true);
            vi.setSort(i.getSort());
            vi.setType(i.getType());
            vi.setUrl(i.getUrl());
            res.add(vi);
        }
        res.sort(new Comparator<SourceViewModel>() {
            @Override
            public int compare(SourceViewModel o1, SourceViewModel o2) {
                return o1.getSort() > o2.getSort() ? 1 : -1;
            }
        });
        return res;
    }

    private List<SourceViewModel> recursionCurSource(int par_id, ArrayList<SourceResultEntity> sources) {
        List<SourceViewModel> res = new ArrayList<SourceViewModel>();
        List<SourceResultEntity> sub = sources.stream().filter((SourceResultEntity i) -> par_id == i.getPar_id()).collect(Collectors.toList());
        if (sub.size() == 0) {
            return res;
        }
        for (SourceResultEntity i : sub) {
            if (i.getRelate_id() != 0) {
                SourceViewModel vi = new SourceViewModel();

                vi.setId(i.getId());
                vi.setName(i.getName());
                vi.setCss(i.getCss());
                vi.setSub(recursionSource(i.getId(), sources));
                vi.setChecked(i.getRelate_id() == 0 ? false : true);
                vi.setSort(i.getSort());
                vi.setType(i.getType());
                vi.setUrl(i.getUrl());
                res.add(vi);
            }
        }
        res.sort(new Comparator<SourceViewModel>() {
            @Override
            public int compare(SourceViewModel o1, SourceViewModel o2) {
                return o1.getSort() > o2.getSort() ? 1 : -1;
            }
        });
        return res;
    }
}
