package com.zhangongjx.controller;

import com.zhangongjx.infrastructure.util.Base64Helper;
import com.zhangongjx.message.statistics.view.StatisticsMemberViewModel;
import com.zhangongjx.model.l.LogLoginEntity;
import com.zhangongjx.model.o.ConsigneeEntity;
import com.zhangongjx.model.w.MemberEntity;
import com.zhangongjx.repository.l.ILogLoginRepository;
import com.zhangongjx.repository.o.IOrderRepository;
import com.zhangongjx.repository.w.IDWGRepository;
import com.zhangongjx.repository.w.IMemberRepository;
import com.zhangongjx.infrastructure.domain.ResutlMsg;
import com.zhangongjx.repository.o.IConsigneeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.image.BufferStrategy;
import java.nio.Buffer;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private IMemberRepository memberRepository;
    @Autowired
    private IConsigneeRepository consigneeRepository;
    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private IDWGRepository idwgRepository;
    @Autowired
    private ILogLoginRepository logLoginRepository;

    //member
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String goods() {
        return "member/list";
    }

    @ResponseBody
    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public ResutlMsg memberData(int draw, int length, int start, String search_id, String search_name, String search_date) {

        ResutlMsg res = ResutlMsg.success();

        StringBuffer buffer = new StringBuffer();
        buffer.append("1=1 ");
        if (search_id != null && search_id != "") {
            buffer.append(" and user_name like '%" + search_id + "%' ");
        }
        if (search_name != null && search_name != "") {
            buffer.append(" and nick_name like '%" + search_name + "%' ");
        }
        if (search_date != null && search_date != "") {
            buffer.append(" and create_at<='" + search_date + "' ");
        }
        List<MemberEntity> items = memberRepository.getDatas(start, length, buffer.toString());
        int count = memberRepository.getCount(buffer.toString());

        res.setData(items);
        res.setDraw(draw);
        res.setRecordsFiltered(count);
        res.setRecordsTotal(count);

        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/list_log_login_data", method = RequestMethod.GET)
    public ResutlMsg listLogLoginData(int draw, int length, int start, int member_id) {

        ResutlMsg res = ResutlMsg.success();

        List<LogLoginEntity> items = logLoginRepository.getDataByMemberId(start, length, 2, member_id);
        int count = logLoginRepository.getCountByUserId(2, member_id);

        res.setData(items);
        res.setDraw(draw);
        res.setRecordsFiltered(count);
        res.setRecordsTotal(count);

        return res;
    }


    @ResponseBody
    @RequestMapping(value = "/all_consignee", method = RequestMethod.GET)
    public ResutlMsg consigneeAllData(int member_id) {
        ResutlMsg res = ResutlMsg.success();

        List<ConsigneeEntity> items = consigneeRepository.getAllDatas(member_id);
        res.setData(items);
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/list_consignee", method = RequestMethod.GET)
    public ResutlMsg consigneeData(int draw, int length, int start, int member_id) {
        ResutlMsg res = ResutlMsg.success();

        List<ConsigneeEntity> items = consigneeRepository.getDatas(start, length, member_id);
        int count = consigneeRepository.getCount(member_id);

        res.setData(items);
        res.setDraw(draw);
        res.setRecordsFiltered(count);
        res.setRecordsTotal(count);
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/all_data", method = RequestMethod.GET)
    public ResutlMsg memberAllData() {
        ResutlMsg res = ResutlMsg.success();

        List<MemberEntity> items = memberRepository.getAllDatas();
        res.setData(items);
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public ResutlMsg statistics(int member_id) {
        ResutlMsg res = ResutlMsg.success();
        StatisticsMemberViewModel item = new StatisticsMemberViewModel();
        item.setDwgCount(idwgRepository.getCountByMemberId(member_id));
        item.setLoginCount(logLoginRepository.getCountByUserId(2, member_id));
        item.setOrderAmount(orderRepository.getOrderAmountByMemberId(member_id));
        item.setOrderCount(orderRepository.getOrderCountByMemberId(member_id));

        res.setData(item);
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/delete_member", method = RequestMethod.POST)
    public ResutlMsg deleteMember(@RequestBody MemberEntity item) {

        ResutlMsg res = ResutlMsg.success();
        if (item == null) {
            return ResutlMsg.error("没有接收到请求参数");
        }
        memberRepository.delete(item.getId());
        res.setMsg("删除成功");

        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/enable_member", method = RequestMethod.POST)
    public ResutlMsg enableMember(@RequestBody MemberEntity req) {
        ResutlMsg res = ResutlMsg.success();
        if (req == null) {
            return ResutlMsg.error("未接受到请求参数");
        }
        memberRepository.updateStatus(req);

        res.setMsg("账户状态更新成功");
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/save_member", method = RequestMethod.POST)
    public ResutlMsg saveMember(@RequestBody MemberEntity req) {
        ResutlMsg res = ResutlMsg.success();
        if (req == null) {
            return ResutlMsg.error("未接受到请求参数");
        }
        StringBuffer buffer = new StringBuffer();

        if (req.getPhone() != null && req.getPhone() != "") {
            buffer.append("phone='" + req.getPhone() + "',");
        }
        if (req.getLevel() != null && req.getLevel() != "") {
            buffer.append("level='" + req.getLevel() + "',");
        }
        if (req.getSex() != null && req.getSex() != "") {
            buffer.append("sex='" + req.getSex() + "',");
        }
        if (req.getBirthday() != null && req.getBirthday() != "") {
            buffer.append("birthday='" + req.getBirthday().substring(0, 10) + "',");
        }
        if (req.getProvince() != null && req.getProvince() != "") {
            buffer.append("province='" + req.getProvince() + "',");
        }
        if (req.getCity() != null && req.getCity() != "") {
            buffer.append("city='" + req.getCity() + "',");
        }
        if (req.getJob() != null && req.getJob() != "") {
            buffer.append("job='" + req.getJob() + "',");
        }
        if (req.getSignature() != null && req.getSignature() != "") {
            buffer.append("signature='" + req.getSignature() + "',");
        }
        if (req.getConfirm_pass_word() != null && req.getConfirm_pass_word() != "") {
            buffer.append("pass_word='" + Base64Helper.Base64(req.getConfirm_pass_word()) + "',");
        }
        buffer.append("enabled='" + req.getEnabled() + "',");
        buffer.append("update_at='" + new Timestamp(new Date().getTime()).toString() + "' ");


        String query = "id=" + req.getId() + " ";
        memberRepository.updateInfo(buffer.toString(), query);

        res.setMsg("更新成功");
        return res;
    }

}
