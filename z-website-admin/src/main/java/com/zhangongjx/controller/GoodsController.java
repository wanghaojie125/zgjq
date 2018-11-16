package com.zhangongjx.controller;

import com.zhangongjx.auth.User;
import com.zhangongjx.message.goods.view.ImgViewModel;
import com.zhangongjx.model.l.LogGoodsEntity;
import com.zhangongjx.repository.i.IGalleryRepository;
import com.zhangongjx.repository.i.IImageRepository;
import com.zhangongjx.infrastructure.domain.ResutlMsg;
import com.zhangongjx.infrastructure.util.RandomHelper;
import com.zhangongjx.model.i.GalleryEntity;
import com.zhangongjx.model.i.ImageEntity;
import com.zhangongjx.model.m.GoodsEntity;
import com.zhangongjx.repository.l.ILogGoodsRepository;
import com.zhangongjx.repository.m.IGoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IGoodsRepository goodsRepository;
    @Autowired
    private IGalleryRepository galleryRepository;
    @Autowired
    private IImageRepository imageRepository;
    @Autowired
    private ILogGoodsRepository logGoodsRepository;

    //goods
    @RequestMapping(value = "/goods", method = RequestMethod.GET)
    public String goods() {
        return "goods/goods";
    }

    //add
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String good(Model model, int id) {
        return "goods/save";
    }

    //gallarys
    @RequestMapping(value = "/gallarys", method = RequestMethod.GET)
    public String gallarys() {
        return "goods/gallarys";
    }

    @ResponseBody
    @RequestMapping(value = "/save_goods", method = RequestMethod.POST)
    public ResutlMsg saveGoods(@RequestBody GoodsEntity item) {

        ResutlMsg res = ResutlMsg.success();
        if (item == null) {
            return ResutlMsg.error("没有接收到请求参数");
        }
        String oper = "";
        int oper_type = 1;
        int count = 0;
        int goodsId = 0;
        if (item.getGoods_no() == null || item.getGoods_no() == "") {
            item.setGoods_no(RandomHelper.RandomNumber());
        }
        if (item.getId() == 0) {
            goodsId = goodsRepository.insert(item);
            count = item.getStore();
            oper = "商品新增";
            res.setMsg("商品添加成功");
        } else {
            Timestamp date = new Timestamp(System.currentTimeMillis());
            item.setUpdate_at(date.toString());

            GoodsEntity source = goodsRepository.getData(item.getId());
            count = source.getStore() - item.getStore();
            oper = "商品修改";
            goodsId = item.getId();

            goodsRepository.update(item);

            res.setMsg("商品修改成功");
        }
        LogGoodsEntity log = new LogGoodsEntity();

        log.setOper_id(User.getUser_id());
        log.setOper_name(User.getUsername());
        log.setGoods_type(item.getType_name());
        log.setOper(oper);
        log.setOper_type(oper_type);
        log.setCount(count);
        log.setGoods_id(goodsId);
        log.setRemain(item.getStore());

        logGoodsRepository.insert(log);
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/goods_data", method = RequestMethod.GET)
    public ResutlMsg goodsData(int draw, int length, int start, String search_id, String search_type, String search_brand, String select_status) {

        ResutlMsg res = ResutlMsg.success();
        StringBuffer buffer = new StringBuffer();
        buffer.append("1=1 ");
        if (search_id != null && !search_id.equals("")) {
            buffer.append(" and (goods_no like '%" + search_id + "%' or name like '%" + search_id + "%') ");
        }
        if (search_type != null && search_type != "") {
            buffer.append(" and type_id like '%" + search_type + "%' ");
        }

        if (search_brand != null && search_brand != "") {
            buffer.append(" and brand_id=" + search_brand + " ");
        }
        if (select_status != null && select_status != "") {
            buffer.append(" and status=" + select_status + " ");
        }
        List<GoodsEntity> items = goodsRepository.getDatas(start, length, buffer.toString());
        int count = goodsRepository.getCount(buffer.toString());

        res.setData(items);
        res.setDraw(draw);
        res.setRecordsFiltered(count);
        res.setRecordsTotal(count);

        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/get_goods", method = RequestMethod.GET)
    public ResutlMsg getGoods(int id) {
        ResutlMsg res = ResutlMsg.success();
        GoodsEntity item = goodsRepository.getData(id);
        res.setData(item);
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/delete_goods", method = RequestMethod.POST)
    public ResutlMsg deleteGoods(@RequestBody GoodsEntity item) {

        ResutlMsg res = ResutlMsg.success();
        if (item == null) {
            return ResutlMsg.error("没有接收到请求参数");
        }
        goodsRepository.delete(item.getId());
        res.setMsg("删除商品成功");

        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/save_status", method = RequestMethod.POST)
    public ResutlMsg saveStatus(@RequestBody GoodsEntity item) {

        ResutlMsg res = ResutlMsg.success();
        if (item == null) {
            return ResutlMsg.error("没有接收到请求参数");
        }
        goodsRepository.updateStatus(item);
        res.setMsg("商品上下架成功");

        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/save_store", method = RequestMethod.POST)
    public ResutlMsg saveStore(@RequestBody GoodsEntity item) {

        ResutlMsg res = ResutlMsg.success();
        if (item == null) {
            return ResutlMsg.error("没有接收到请求参数");
        }
        GoodsEntity source = goodsRepository.getData(item.getId());

        goodsRepository.updateStore(item);

        LogGoodsEntity log = new LogGoodsEntity();

        log.setOper_id(User.getUser_id());
        log.setOper_name(User.getUsername());
        log.setGoods_type(item.getType_name());
        log.setOper("库存修改");
        log.setOper_type(1);
        log.setCount(source.getStore() - item.getStore());
        log.setGoods_id(item.getId());
        log.setRemain(item.getStore());
        logGoodsRepository.insert(log);

        res.setMsg("商品库存修改成功");

        return res;
    }


    @ResponseBody
    @RequestMapping(value = "/gallary_data", method = RequestMethod.GET)
    public ResutlMsg gallaryData(int draw, int length, int start) {

        ResutlMsg res = ResutlMsg.success();
        List<GalleryEntity> items = galleryRepository.getDatas(start, length);
        int count = galleryRepository.getCount();

        res.setData(items);
        res.setDraw(draw);
        res.setRecordsFiltered(count);
        res.setRecordsTotal(count);

        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/all_gallary", method = RequestMethod.GET)
    public ResutlMsg allGallaryData() {

        ResutlMsg res = ResutlMsg.success();
        List<GalleryEntity> items = galleryRepository.getAllDatas();
        int count = galleryRepository.getCount();

        res.setData(items);
        return res;
    }


    @ResponseBody
    @RequestMapping(value = "/save_gallary", method = RequestMethod.POST)
    public ResutlMsg saveGallary(@RequestBody GalleryEntity item) {

        ResutlMsg res = ResutlMsg.success();
        if (item == null) {
            return ResutlMsg.error("没有接收到请求参数");
        }

        if (item.getId() == 0) {
            galleryRepository.insert(item);
            res.setMsg("相册添加成功");
        } else {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            item.setUpdate_at(timestamp.toString());
            galleryRepository.update(item);
            res.setMsg("相册修改成功");
        }
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/delete_gallery", method = RequestMethod.POST)
    public ResutlMsg deleteGallary(@RequestBody GalleryEntity item) {

        ResutlMsg res = ResutlMsg.success();
        if (item == null) {
            return ResutlMsg.error("没有接收到请求参数");
        }
        galleryRepository.delete(item.getId());
        res.setMsg("相册删除成功");

        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/get_imgs", method = RequestMethod.GET)
    public ResutlMsg getImgs(int draw, int length, int start, int gallery_id) {

        ResutlMsg res = ResutlMsg.success();

        List<ImageEntity> imgs = imageRepository.getDatas(start, length, gallery_id);
        int total_count = imageRepository.getCount(gallery_id);
        galleryRepository.updateCount(gallery_id, total_count);
        int count = imgs.size();
        int split = 4;
        int page = count / 4;
        List<ImgViewModel> data = new ArrayList<>();
        for (int i = 0; i <= page; i++) {
            ImgViewModel vi = new ImgViewModel();
            List<ImageEntity> sub = new ArrayList<ImageEntity>();
            if (count - i * split >= 4) {
                sub = imgs.subList(i * split, (i + 1) * split);
            } else {
                sub = imgs.subList(i * split, count);
            }
            vi.setCol(sub);
            data.add(vi);
        }
        res.setData(data);
        res.setDraw(draw);
        res.setRecordsFiltered(total_count);
        res.setRecordsTotal(total_count);

        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/save_img", method = RequestMethod.POST)
    public ResutlMsg saveImg(@RequestBody ImageEntity item) {

        ResutlMsg res = ResutlMsg.success();
        if (item == null) {
            return ResutlMsg.error("没有接收到请求参数");
        }


        if (item.getId() == 0) {
            imageRepository.insert(item);
            res.setMsg("添加成功");
        } else {
//            Timestamp timestamp = new Timestamp(new Date().getTime());
//            item.setUpdate_at(timestamp.toString());
            imageRepository.insert(item);
            res.setMsg("修改成功");
        }
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/turn_img", method = RequestMethod.POST)
    public ResutlMsg turnImg(@RequestBody ImageEntity item) {

        ResutlMsg res = ResutlMsg.success();
        if (item == null) {
            return ResutlMsg.error("没有接收到请求参数");
        }
        imageRepository.updateGallary(item);
        res.setMsg("转移成功");
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/del_img", method = RequestMethod.POST)
    public ResutlMsg delImg(@RequestBody ImageEntity item) {

        ResutlMsg res = ResutlMsg.success();
        if (item == null) {
            return ResutlMsg.error("没有接收到请求参数");
        }
        imageRepository.delete(item.getId());
        res.setMsg("删除成功");
        return res;
    }
}
