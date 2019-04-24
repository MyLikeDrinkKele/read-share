package com.share.book.service;

import com.share.book.entity.ReadBook;
import com.baomidou.mybatisplus.extension.service.IService;
import com.share.book.mapper.ReadBookMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 * 章节保存表 服务类
 * </p>
 *
 * @author zoubin
 * @since 2018-10-27
 */
public interface IReadBookService extends IService<ReadBook> {
    List<ReadBook> selectListAll();
}
