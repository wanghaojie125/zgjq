package com.zhangongjx.service.impl;

import com.zhangongjx.repository.m.IGoodsBrandRepository;
import com.zhangongjx.repository.m.IGoodsSkuRepository;
import com.zhangongjx.infrastructure.domain.ResutlMsg;
import com.zhangongjx.message.auth.view.SourceViewModel;
import com.zhangongjx.message.goods.request.GetGoodsTypeRequest;
import com.zhangongjx.message.goods.request.SaveGoodsTypeRequest;
import com.zhangongjx.message.goods.response.GetGoodsTypeResponse;
import com.zhangongjx.message.goods.response.SaveGoodsTypeResponse;
import com.zhangongjx.message.goods.view.GoodsTypeViewModel;
import com.zhangongjx.model.m.GoodsTypeEntity;
import com.zhangongjx.repository.m.IGoodsTypeRepository;
import com.zhangongjx.service.IFileService;
import com.zhangongjx.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsService implements IGoodsService {
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private IGoodsBrandRepository goodsBrandRepository;
    @Autowired
    private IGoodsSkuRepository goodsSkuRepository;
    @Autowired
    private IGoodsTypeRepository goodsTypeRepository;


    @Override
    public GetGoodsTypeResponse getGoodsType(GetGoodsTypeRequest request) {
        GetGoodsTypeResponse resp=new GetGoodsTypeResponse();
        resp.setResultMsg(ResutlMsg.success());
        List<GoodsTypeEntity> types=goodsTypeRepository.getGoodsTypes();

        List<GoodsTypeViewModel> res=recursionTypes(0,(ArrayList<GoodsTypeEntity>) types);
        resp.getResultMsg().setData(res);
        return  resp;
    }

    @Override
    public SaveGoodsTypeResponse saveGoodsType(SaveGoodsTypeRequest request) {
        SaveGoodsTypeResponse resp=new  SaveGoodsTypeResponse();
        resp.setResultMsg(ResutlMsg.success());

        GoodsTypeEntity item=new GoodsTypeEntity();
        item.setId(request.getId());
        item.setName(request.getAdd_type_name());
        item.setPar_id(request.getAdd_type_parent());
        if(item.getId()==0)
        {
            goodsTypeRepository.insert(item);
            resp.getResultMsg().setMsg("添加商品分类成功");
        }
        else {
            goodsTypeRepository.update(item);
            resp.getResultMsg().setMsg("修改商品分类成功");
        }
        return resp;
    }

    private List<GoodsTypeViewModel> recursionTypes(int par_id, ArrayList<GoodsTypeEntity> sources) {
        List<GoodsTypeViewModel> res = new ArrayList<GoodsTypeViewModel>();
        List<GoodsTypeEntity> sub = sources.stream().filter((GoodsTypeEntity i) -> par_id == i.getPar_id()).collect(Collectors.toList());
        if (sub.size() == 0) {
            return res;
        }
        for (GoodsTypeEntity i : sub) {
            GoodsTypeViewModel vi = new GoodsTypeViewModel();

            vi.setId(i.getId());
            vi.setName(i.getName());

            vi.setSub(recursionTypes(i.getId(), sources));
            res.add(vi);
        }
        return res;
    }
}
