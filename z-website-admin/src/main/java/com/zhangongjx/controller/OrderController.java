package com.zhangongjx.controller;

import com.zhangongjx.model.o.OrderDetailEntity;
import com.zhangongjx.model.o.OrderEntity;
import com.zhangongjx.repository.o.IOrderRepository;
import com.zhangongjx.auth.User;
import com.zhangongjx.infrastructure.domain.ResutlMsg;
import com.zhangongjx.infrastructure.util.RandomHelper;
import com.zhangongjx.repository.o.IOrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private IOrderDetailRepository orderDetailRepository;

    //list
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "order/list";
    }

    //confirm_list
    @RequestMapping(value = "/confirm_list", method = RequestMethod.GET)
    public String confirmList() {
        return "order/confirm_list";
    }

    @ResponseBody
    @RequestMapping(value = "/list_data", method = RequestMethod.GET)
    public ResutlMsg listData(int draw, int length, int start, String search_id, String search_name, String search_start_date, String search_end_date, String select_status) {

        ResutlMsg res = ResutlMsg.success();

        StringBuffer buffer = new StringBuffer();
        buffer.append("1=1 ");
        if (search_id != null && search_id != "") {
            buffer.append(" and (contract_no like '%" + search_id + "%' or company like '%" + search_id + "%') ");
        }
        if (search_name != null && search_name != "") {
            buffer.append(" and (name like '%" + search_name + "%' or phone like '%" + search_name + "%' ) ");
        }
        if (search_start_date != null && search_start_date != "") {
            buffer.append(" and create_at>='" + search_start_date + "' ");
        }
        if (search_end_date != null && search_end_date != "") {
            buffer.append(" and create_at<='" + search_end_date + "' ");
        }
        if (select_status != null && select_status != "") {
            buffer.append(" and status='" + select_status + "' ");
        }
        List<OrderEntity> items = orderRepository.getDatas(start, length, buffer.toString());
        int count = orderRepository.getCount(buffer.toString());

        res.setData(items);
        res.setDraw(draw);
        res.setRecordsFiltered(count);
        res.setRecordsTotal(count);

        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/list_data_member", method = RequestMethod.GET)
    public ResutlMsg listDataByMemberId(int draw, int length, int start,int member_id) {

        ResutlMsg res = ResutlMsg.success();

        StringBuffer buffer = new StringBuffer();
        buffer.append("1=1 ");
        if (member_id != 0) {
            buffer.append(" and member_id='" + member_id + "' ");
        }
        List<OrderEntity> items = orderRepository.getDatas(start, length, buffer.toString());
        int count = orderRepository.getCount(buffer.toString());

        res.setData(items);
        res.setDraw(draw);
        res.setRecordsFiltered(count);
        res.setRecordsTotal(count);

        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/get_order", method = RequestMethod.GET)
    public ResutlMsg get_order(int id) {

        ResutlMsg res = ResutlMsg.success();
        OrderEntity item = orderRepository.getData(id);
        res.setData(item);
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/get_order_detail", method = RequestMethod.GET)
    public ResutlMsg get_order(String contract_no) {

        ResutlMsg res = ResutlMsg.success();
        List<OrderDetailEntity> items = orderDetailRepository.getDatas(contract_no);
        res.setData(items);
        return res;
    }


    @ResponseBody
    @RequestMapping(value = "/save_status", method = RequestMethod.POST)
    public ResutlMsg saveStatus(@RequestBody OrderEntity item) {
        ResutlMsg res = ResutlMsg.success();
        if (item == null) {
            return ResutlMsg.error("没有接收到请求参数");
        }
        item.setOperator_id(User.getUser_id());
        item.setOperator_name(User.getUsername());
        item.setStatus(item.getStatus());
        item.setWaybill_no(item.getWaybill_no());

        Timestamp date = new Timestamp(new Date().getTime());
        item.setUpdate_at(date.toString());

        orderRepository.updateStatus(item);
        res.setMsg("修改成功");

        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResutlMsg save(@RequestBody OrderEntity item) {
        ResutlMsg res = ResutlMsg.success();
        if (item == null) {
            return ResutlMsg.error("没有接收到请求参数");
        }
        item.setOperator_id(User.getUser_id());
        item.setOperator_name(User.getUsername());

        String[] goodss = item.getGoods().split("\\|");
        double amount = 0;
        int count = 0;
        List<OrderDetailEntity> orderDetailList = new ArrayList<>();
        for (String goods : goodss) {
            if (goods != null && goods != "") {
                String[] goodsInf = goods.split(",");
                OrderDetailEntity detailEntity = new OrderDetailEntity();
                detailEntity.setNo(goodsInf[0]);
                detailEntity.setGoods_name(goodsInf[1]);
                detailEntity.setGoods_model(goodsInf[2]);
                detailEntity.setCount(goodsInf[3]);
                detailEntity.setUnit(goodsInf[4]);
                detailEntity.setPrice(goodsInf[5]);
                detailEntity.setDate(goodsInf[6]);
                detailEntity.setRemark(goodsInf[7]);
                detailEntity.setMember_id(item.getMember_id());
                double total_price = Double.parseDouble(detailEntity.getCount()) * Double.parseDouble(detailEntity.getPrice());
                detailEntity.setTotal_price("" + total_price);
                orderDetailList.add(detailEntity);
                count += Integer.parseInt(detailEntity.getCount());
                amount += total_price;
            }
        }

        item.setAmount(amount);
        item.setCount(count);
        if (item.getContract_no() == null || item.getContract_no() == "") {
            item.setContract_no(RandomHelper.RandomNumber());
        }
        if (item.getId() == 0) {
            orderRepository.insert(item);
            res.setMsg("添加成功");
        } else {
            Timestamp date = new Timestamp(new Date().getTime());
            item.setUpdate_at(date.toString());

            orderRepository.update(item);
            orderDetailRepository.delete(item.getContract_no());
            res.setMsg("修改成功");
        }
        for (OrderDetailEntity i : orderDetailList) {
            i.setOrder_id(item.getContract_no());
            orderDetailRepository.insert(i);
        }
        return res;
    }

//    @ResponseBody
//    @RequestMapping(value = "/confirm_list_data", method = RequestMethod.GET)
//    public ResutlMsg confirmListData(int draw, int length, int start) {
//
//        ResutlMsg res = ResutlMsg.success();
//        List<OrderEntity> items = orderRepository.getDatas(start, length);
//        int count = orderRepository.getCount();
//
//        res.setData(items);
//        res.setDraw(draw);
//        res.setRecordsFiltered(count);
//        res.setRecordsTotal(count);
//
//        return res;
//    }
}
