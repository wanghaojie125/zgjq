package com.zhangongjx.controller;

import com.zhangongjx.infrastructure.util.RandomHelper;
import com.zhangongjx.message.goods.response.UploadFileResponse;
import com.zhangongjx.conf.Conf;
import com.zhangongjx.infrastructure.domain.ResutlMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping()
public class UploadController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private Conf conf;

    @RequestMapping(value = "/upload_file", method = RequestMethod.POST)
    public ResutlMsg<Object> upload_file(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "key") String key) {
        if (file.isEmpty()) {
            return ResutlMsg.error("没有接收到上传的文件");
        }
        ResutlMsg res = ResutlMsg.success();
        // 获取文件名
        String fileName = file.getOriginalFilename();
        long size = file.getSize();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 文件上传后的路径
        String filePath = conf.getUpload_path();
        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;
        fileName = RandomHelper.RandomNumber() + suffixName;
        File dest = new File(filePath + key + "/" + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            UploadFileResponse resp = new UploadFileResponse();
            resp.setPath(filePath + key + "/");
            resp.setUrl(conf.getImg_url() + key + "/" + fileName);
            res.setData(resp);
            return res;
        } catch (IllegalStateException e) {
            logger.error("upload_file 上传文件失败" + e.getMessage(),e);
            return ResutlMsg.error("上传失败" + e.getMessage());
        } catch (IOException e) {
            logger.error("upload_file 上传文件失败" + e.getMessage(),e);
            return ResutlMsg.error("上传失败" + e.getMessage());
        }
    }
}
