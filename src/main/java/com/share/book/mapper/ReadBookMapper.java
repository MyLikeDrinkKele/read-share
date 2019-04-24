package com.share.book.mapper;

import com.share.book.entity.ReadBook;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 章节保存表 Mapper 接口
 * </p>
 *
 * @author zoubin
 * @since 2018-10-27
 */
@Repository
public interface ReadBookMapper extends BaseMapper<ReadBook> {
    List<ReadBook> selectList();
}
