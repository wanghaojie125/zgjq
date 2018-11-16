package com.zhangongjx.service;

import com.zhangongjx.model.s.TableData;

import java.util.List;

/**
 * @author: wanghaojie
 * @description：
 * @date: 2018/10/18 18:35
 */
public interface ITableDataService {
    TableData getData(int id);

    List<TableData> getDatas();


}
