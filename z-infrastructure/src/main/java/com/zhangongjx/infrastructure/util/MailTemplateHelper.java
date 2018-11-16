package com.zhangongjx.infrastructure.util;

import com.zhangongjx.infrastructure.domain.ResutlMsg;

public class MailTemplateHelper {

    public static ResutlMsg CreateTemplate(String from, String to, String subject, String text)
    {
        ResutlMsg res=ResutlMsg.success();
        try
        {
            res.setMsg("发送成功");
        }
        catch(Exception ex)
        {
            res=ResutlMsg.error();
            res.setMsg("发送失败"+ex.getMessage());
        }
        return  res;
    }
}
