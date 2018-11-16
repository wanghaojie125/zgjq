package com.zhangongjx.repository.s;

import com.zhangongjx.result.SourceResultEntity;
import com.zhangongjx.result.SourceResultEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ISourceRepository {
    @Select({"select s.id,s.`name`,s.sort,s.par_id,s.url,s.css,s.type,IFNULL(r.relate_id,0) as relate_id \n" +
            "from s_source as s left join s_source_relate as r\n" +
            "on s.id=r.source_id and s.deleted=0 and r.deleted=0 and r.type=#{relate_type} and r.relate_id=#{relate_id}"})
    List<SourceResultEntity> getSources(@Param("relate_type") int relate_type, @Param("relate_id") int relate_id);
    @Update("update s_source_relate set deleted=1 where relate_id=#{id} and type=#{type}")
    void deleteRelate(@Param("id") int id,@Param("type") int type);
    @Insert("insert into s_source_relate(relate_id,source_id,type) VALUES #{value}")
    void insertRelates(@Param("value") String value);
    @Insert("insert into s_source_relate(relate_id,source_id,type) VALUES (#{relate_id},#{source_id},#{type})")
    void insertRelate(@Param("relate_id") int relate_id,@Param("source_id") int source_id,@Param("type") int type);
}
