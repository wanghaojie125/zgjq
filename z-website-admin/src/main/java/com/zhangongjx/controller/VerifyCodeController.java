package com.zhangongjx.controller;

import com.zhangongjx.model.c.ConfVerifyCodeEntity;
import com.zhangongjx.repository.c.IConfVerifyCodeRepository;
import com.zhangongjx.infrastructure.util.VerifyCodeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

@RestController
@RequestMapping()
public class VerifyCodeController {

    @Autowired
    private IConfVerifyCodeRepository confVerifyCodeRepository;

    @RequestMapping(value = "/verify_code", method = RequestMethod.GET)
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {

        int w = 90;//图片长（默认）
        int h = 30;//图片宽（默认）
        int digit = 5;//位数（默认）
        int size = 18;//字体大小（默认）

        ConfVerifyCodeEntity item = confVerifyCodeRepository.getData();

        if (item != null) {
            w = item.getWidth();
            h = item.getHeight();
            digit = item.getDigit();
            size = item.getSize();
        }

        VerifyCodeHelper vc = new VerifyCodeHelper();//创建VerifyCode类的对象
        BufferedImage bi = vc.getImage(w, h, digit, size);//调用getImge()方法获得一个BufferedImage对象

        try {
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            //RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
            ImageIO.write(bi, "JPEG", response.getOutputStream());
            //randomValidateCode.getRandcode(request, response);//输出验证码图片方法
        } catch (Exception e) {
        }
    }

    @RequestMapping(value = "/verify_code_preview", method = RequestMethod.GET)
    public void verifyCodePreview(HttpServletRequest request, HttpServletResponse response) {

        String p_w = request.getParameter("w");
        String p_h = request.getParameter("h");
        String p_digit = request.getParameter("digit");
        String p_size = request.getParameter("size");
        int w = Integer.parseInt(p_w);
        int h = Integer.parseInt(p_h);
        int digit = Integer.parseInt(p_digit);
        int size = Integer.parseInt(p_size);

        ConfVerifyCodeEntity item = confVerifyCodeRepository.getData();
        VerifyCodeHelper vc = new VerifyCodeHelper();//创建VerifyCode类的对象
        BufferedImage bi = vc.getImage(w, h, digit, size);//调用getImge()方法获得一个BufferedImage对象

        try {
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            //RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
            ImageIO.write(bi, "JPEG", response.getOutputStream());
            //randomValidateCode.getRandcode(request, response);//输出验证码图片方法
        } catch (Exception e) {
        }
    }
}
