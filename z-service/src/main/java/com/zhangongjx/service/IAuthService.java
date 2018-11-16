package com.zhangongjx.service;

import com.zhangongjx.message.auth.request.EnableDepRequest;
import com.zhangongjx.message.auth.request.GetSourceRequest;
import com.zhangongjx.message.auth.response.*;
import com.zhangongjx.message.auth.request.*;
import com.zhangongjx.message.auth.response.*;
import com.zhangongjx.model.s.UserEntity;

public interface IAuthService {
    //login
    LoginResponse login(LoginRequest req);

    //dep
    DepDataResponse getDepDatas(DepDataRequest req);
    DepDataResponse getAllDepDatas(DepDataRequest req);
    DepDataResponse getDepData(DepDataRequest req);
    SaveDepResponse saveDep(SaveDepRequest req);
    DeleteDepResponse deleteDep(DeleteDepRequest req);
    EnableDepResponse enabledDep(EnableDepRequest req);

    //source
    GetSourceResponse getSources(GetSourceRequest req);
    GetSourceResponse getCurSources(int userId,int depId);
    SavetSourceResponse saveSources(SaveSourceRequest req);

    //user
    UserDataResponse getUserDatas(UserDataRequest req);
    UserDataResponse getUserData(UserDataRequest req);
    SaveUserResponse saveUser(SaveUserRequest req);
    DeleteUserResponse deleteUser(DeleteUserRequest req);
    EnableUserResponse enabledUser(EnableUserRequest req);

    /**
     * 根据手机号获取用户信息
     * @param phone
     * @return
     */
    UserEntity getUserEntityByPhone(String phone);
}
