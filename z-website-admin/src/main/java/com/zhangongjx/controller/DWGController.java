package com.zhangongjx.controller;

import com.zhangongjx.auth.User;
import com.zhangongjx.infrastructure.domain.ResutlMsg;
import com.zhangongjx.infrastructure.util.RandomHelper;
import com.zhangongjx.model.o.OrderDetailEntity;
import com.zhangongjx.model.o.OrderEntity;
import com.zhangongjx.model.w.DWGEntity;
import com.zhangongjx.repository.o.IOrderDetailRepository;
import com.zhangongjx.repository.o.IOrderRepository;
import com.zhangongjx.repository.w.IDWGRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/dwg")
public class DWGController {

    @Autowired
    private IDWGRepository idwgRepository;

    //list
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "dwg/list";
    }
    @ResponseBody
    @RequestMapping(value = "/list_data", method = RequestMethod.GET)
    public ResutlMsg listData(int draw, int length, int start,String search_id,String search_name,String search_date,String select_status) {
        ResutlMsg res = ResutlMsg.success();

        StringBuffer buffer=new StringBuffer();
        buffer.append("1=1 ");
        if(search_id!=null&&search_id!="")
        {
            buffer.append(" and file_no like '%"+search_id+"%' ");
        }
        if(search_name!=null&&search_name!="")
        {
            buffer.append(" and (contact_name like '%"+search_name+"%' or phone like '%"+search_name+"%' ) ");
        }
        if(search_date!=null&&search_date!="")
        {
            buffer.append(" and create_at<='"+search_date+"' ");
        }
        if(select_status!=null&&select_status!="")
        {
            buffer.append(" and status='"+select_status+"' ");
        }
        List<DWGEntity> items = idwgRepository.getDatas(start, length,buffer.toString());
        int count = idwgRepository.getCount(buffer.toString());

        res.setData(items);
        res.setDraw(draw);
        res.setRecordsFiltered(count);
        res.setRecordsTotal(count);

        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/get_dwg", method = RequestMethod.GET)
    public ResutlMsg getdwg(int id) {
        ResutlMsg res = ResutlMsg.success();
        DWGEntity item = idwgRepository.getData(id);
        res.setData(item);
        return res;
    }
    @ResponseBody
    @RequestMapping(value = "/save_status", method = RequestMethod.POST)
    public ResutlMsg saveStatus(@RequestBody DWGEntity item) {
        ResutlMsg res = ResutlMsg.success();
        if (item == null) {
            return ResutlMsg.error("没有接收到请求参数");
        }
        item.setOper_id(User.getUser_id());
        item.setOper_name(User.getUsername());
        item.setStatus(item.getStatus());
        item.setOper_log(item.getOper_log());

        idwgRepository.updateStatus(item);
        res.setMsg("修改成功");

        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/save_confirm_path", method = RequestMethod.POST)
    public ResutlMsg saveConfirmPath(@RequestBody DWGEntity item) {
        ResutlMsg res = ResutlMsg.success();
        if (item == null) {
            return ResutlMsg.error("没有接收到请求参数");
        }
        item.setConfirm_path(item.getConfirm_path());

        idwgRepository.updateConfirmPath(item);
        res.setMsg("修改成功");
        return res;
    }
}
