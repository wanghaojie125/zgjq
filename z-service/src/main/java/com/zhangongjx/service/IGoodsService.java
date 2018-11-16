package com.zhangongjx.service;

import com.zhangongjx.message.goods.request.GetGoodsTypeRequest;
import com.zhangongjx.message.goods.request.SaveGoodsTypeRequest;
import com.zhangongjx.message.goods.response.GetGoodsTypeResponse;
import com.zhangongjx.message.goods.response.SaveGoodsTypeResponse;

public interface IGoodsService {
    GetGoodsTypeResponse getGoodsType(GetGoodsTypeRequest request);
    SaveGoodsTypeResponse saveGoodsType(SaveGoodsTypeRequest request);
}
