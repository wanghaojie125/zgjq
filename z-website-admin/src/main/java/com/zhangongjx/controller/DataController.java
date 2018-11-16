package com.zhangongjx.controller;

import com.zhangongjx.model.d.*;
import com.zhangongjx.repository.d.*;
import com.zhangongjx.auth.User;
import com.zhangongjx.infrastructure.domain.ResutlMsg;
import com.zhangongjx.result.DealResultEntity;
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
@RequestMapping("/data")
public class DataController {

    @Autowired
    private IMaterialsRepository materialsRepository;
    @Autowired
    private IDrillRepository drillRepository;
    @Autowired
    private IJointRepository jointRepository;
    @Autowired
    private IDealRepository dealRepository;
    @Autowired
    private IPoleRepository poleRepository;

    //materials
    @RequestMapping(value = "/materials", method = RequestMethod.GET)
    public String materials() {
        return "data/materials";
    }

    @ResponseBody
    @RequestMapping(value = "/save_materials", method = RequestMethod.POST)
    public ResutlMsg saveMaterials(@RequestBody MaterialsEntity item) {

        ResutlMsg res = ResutlMsg.success();
        if (item == null) {
            return ResutlMsg.error("没有接收到请求参数");
        }
        item.setOper_id(User.getUser_id());
        item.setOper_name(User.getUsername());
        if (item.getId() == 0) {
            materialsRepository.insert(item);
            res.setMsg("添加成功");
        } else {
            Timestamp date = new Timestamp(System.currentTimeMillis());
            item.setUpdate_at(date.toString());
            materialsRepository.update(item);
            res.setMsg("修改成功");
        }
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/materials_data", method = RequestMethod.GET)
    public ResutlMsg materialsData(int draw, int length, int start) {
        ResutlMsg res = ResutlMsg.success();
        List<MaterialsEntity> items = materialsRepository.getDatas(start, length);
        int count = materialsRepository.getCount();

        res.setData(items);
        res.setDraw(draw);
        res.setRecordsFiltered(count);
        res.setRecordsTotal(count);
        return res;
    }
    @ResponseBody
    @RequestMapping(value = "/materials_all_data", method = RequestMethod.GET)
    public ResutlMsg materialsAllData() {
        ResutlMsg res = ResutlMsg.success();
        List<MaterialsEntity> items = materialsRepository.getAllDatas();
        res.setData(items);
        return res;
    }

    //drill
    @RequestMapping(value = "/drill", method = RequestMethod.GET)
    public String drill() {
        return "data/drill";
    }

    @ResponseBody
    @RequestMapping(value = "/save_drill", method = RequestMethod.POST)
    public ResutlMsg saveDrill(@RequestBody DrillEntity item) {

        ResutlMsg res = ResutlMsg.success();
        if (item == null) {
            return ResutlMsg.error("没有接收到请求参数");
        }
        item.setOper_id(User.getUser_id());
        item.setOper_name(User.getUsername());
        if (item.getId() == 0) {
            drillRepository.insert(item);
            res.setMsg("添加成功");
        } else {
            Timestamp date = new Timestamp(System.currentTimeMillis());
            item.setUpdate_at(date.toString());
            drillRepository.update(item);
            res.setMsg("修改成功");
        }
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/drill_data", method = RequestMethod.GET)
    public ResutlMsg drillData(int draw, int length, int start, String type) {
        ResutlMsg res = ResutlMsg.success();
        List<DrillEntity> items = new ArrayList<>();
        items = drillRepository.getDatas(type, start, length);
        int count = drillRepository.getCount(type);
        res.setData(items);
        res.setDraw(draw);
        res.setRecordsFiltered(count);
        res.setRecordsTotal(count);
        return res;
    }


    //joint
    @RequestMapping(value = "/joint", method = RequestMethod.GET)
    public String joint() {
        return "data/joint";
    }

    @ResponseBody
    @RequestMapping(value = "/save_joint", method = RequestMethod.POST)
    public ResutlMsg saveJoint(@RequestBody JointEntity item) {

        ResutlMsg res = ResutlMsg.success();
        if (item == null) {
            return ResutlMsg.error("没有接收到请求参数");
        }
        item.setOper_id(User.getUser_id());
        item.setOper_name(User.getUsername());
        if (item.getId() == 0) {
            jointRepository.insert(item);
            res.setMsg("添加成功");
        } else {
            Timestamp date = new Timestamp(System.currentTimeMillis());
            item.setUpdate_at(date.toString());
            jointRepository.update(item);
            res.setMsg("修改成功");
        }
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/joint_data", method = RequestMethod.GET)
    public ResutlMsg jointData(int draw, int length, int start, String type) {
        ResutlMsg res = ResutlMsg.success();
        List<JointEntity> items = new ArrayList<>();
        items = jointRepository.getDatas(type, start, length);
        int count = jointRepository.getCount(type);
        res.setData(items);
        res.setDraw(draw);
        res.setRecordsFiltered(count);
        res.setRecordsTotal(count);
        return res;
    }

    //deal
    @RequestMapping(value = "/deal", method = RequestMethod.GET)
    public String deal() {
        return "data/deal";
    }

    @ResponseBody
    @RequestMapping(value = "/save_deal", method = RequestMethod.POST)
    public ResutlMsg saveDeal(@RequestBody DealEntity item) {

        ResutlMsg res = ResutlMsg.success();
        if (item == null) {
            return ResutlMsg.error("没有接收到请求参数");
        }
        item.setOper_id(User.getUser_id());
        item.setOper_name(User.getUsername());
        if (item.getId() == 0) {
            dealRepository.insert(item);
            res.setMsg("添加成功");
        } else {
            Timestamp date = new Timestamp(System.currentTimeMillis());
            item.setUpdate_at(date.toString());
            dealRepository.update(item);
            res.setMsg("修改成功");
        }
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/deal_data", method = RequestMethod.GET)
    public ResutlMsg dealData(int draw, int length, int start, String type) {
        ResutlMsg res = ResutlMsg.success();
        List<DealResultEntity> items = new ArrayList<>();
        items = dealRepository.getDatas(type, start, length);
        int count = dealRepository.getCount(type);
        res.setData(items);
        res.setDraw(draw);
        res.setRecordsFiltered(count);
        res.setRecordsTotal(count);
        return res;
    }

    //pole
    @RequestMapping(value = "/pole", method = RequestMethod.GET)
    public String pole() {
        return "data/pole";
    }

    @ResponseBody
    @RequestMapping(value = "/save_pole", method = RequestMethod.POST)
    public ResutlMsg savePole(@RequestBody PoleEntity item) {

        ResutlMsg res = ResutlMsg.success();
        if (item == null) {
            return ResutlMsg.error("没有接收到请求参数");
        }
        item.setOper_id(User.getUser_id());
        item.setOper_name(User.getUsername());
        if (item.getId() == 0) {
             poleRepository.insert(item);
            res.setMsg("添加成功");
        } else {
            Timestamp date = new Timestamp(System.currentTimeMillis());
            item.setUpdate_at(date.toString());
            poleRepository.update(item);
            res.setMsg("修改成功");
        }
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/pole_data", method = RequestMethod.GET)
    public ResutlMsg poleData(int draw, int length, int start, String type) {
        ResutlMsg res = ResutlMsg.success();
        List<PoleEntity> items = new ArrayList<>();
        items = poleRepository.getDatas(type, start, length);
        int count = poleRepository.getCount(type);
        res.setData(items);
        res.setDraw(draw);
        res.setRecordsFiltered(count);
        res.setRecordsTotal(count);
        return res;
    }
}
