package com.zhangongjx.repository.t;

import com.zhangongjx.model.s.TableData;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: wanghaojie
 * @description：
 * @date: 2018/10/18 17:18
 */
@Mapper
@Repository
public interface ITableDataRepository {

    @Select("select * from t_table_data where deleted=0 ")
    List<TableData> getDatas();
    @Select("select * from t_table_data where deleted=0 and id=#{id}")
    TableData getData(int id);
    @Insert("insert into t_table_data (t_key, t_value) VALUES(#{tKey}, #{tValue})")
    void insert(TableData item);
    @Update("update t_table_data set t_key=#{tKey},t_value=#{tValue},update_at=#{update_at},deleted=#{deleted} where id=#{id}")
    void update(TableData item);

}
