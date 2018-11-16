package com.zhangongjx.controller;

import com.alibaba.fastjson.JSON;
import com.zhangongjx.conf.Conf;
import com.zhangongjx.infrastructure.domain.ResutlMsg;
import com.zhangongjx.infrastructure.util.RandomHelper;
import com.zhangongjx.message.auth.request.SavePicsRequest;
import com.zhangongjx.message.goods.response.UploadFileResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping()
public class UploadController {
    private Logger logger = LoggerFactory.getLogger(getClass());
//    @Autowired
//    private Conf conf;
//
//    @RequestMapping(value = "/uploadPicFile", method = RequestMethod.POST)
//    public ResutlMsg<Object> upload_file(@RequestBody MultipartFile file) {
//        try {
//            User.getUsername();
//        } catch (Exception e) {
//            return ResutlMsg.error("您未正确登录网站，请重新登录");
//        }
//
//        if (file.isEmpty()) {
//            return ResutlMsg.error("没有接收到上传的文件");
//        }
//        ResutlMsg res = ResutlMsg.success();
//        // 获取文件名
//        String fileName = file.getOriginalFilename();
//        // 获取文件的后缀名
//        String suffixName = fileName.substring(fileName.lastIndexOf("."));
//        //网站上传的图纸
//        String key = "website";
//        // 文件上传后的路径
//        String filePath = conf.getUpload_path();
//        // 解决中文问题，liunx下中文路径，图片显示问题
//        fileName = RandomHelper.RandomNumber() + suffixName;
//        File dest = new File(filePath + key + "/" + fileName);
//        // 检测是否存在目录
//        if (!dest.getParentFile().exists()) {
//            dest.getParentFile().mkdirs();
//        }
//        try {
//            file.transferTo(dest);
//            UploadFileResponse resp = new UploadFileResponse();
//            resp.setUrl(conf.getImg_url() + key + "/" + fileName);
//            res.setData(resp);
//            logger.info(JSON.toJSONString(resp));
//            logger.info("user:"+User.getUsername() + User.getUser_id());
//            return res;
//        } catch (IllegalStateException e) {
//            logger.error("upload_file 上传文件失败" + e.getMessage(),e);
//            return ResutlMsg.error("上传失败" + e.getMessage());
//        } catch (IOException e) {
//            logger.error("upload_file 上传文件失败" + e.getMessage(),e);
//            return ResutlMsg.error("上传失败" + e.getMessage());
//        }
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/save_pics", method = RequestMethod.POST)
//    public ResutlMsg savePics(@RequestBody SavePicsRequest req) {
//        if (req == null) {
//            return ResutlMsg.error("未接受到请求参数");
//        }
//        try {
//            logger.info("/api/save_user 接收参数 req={},userName={}" , JSON.toJSONString(req),User.getUsername());
//
//        } catch (Exception e) {
//            logger.error("上传图纸失败"+e.getMessage(),e);
//            return ResutlMsg.error("上传图纸失败"+e.getMessage());
//        }
//
//        return ResutlMsg.success();
//    }

}
