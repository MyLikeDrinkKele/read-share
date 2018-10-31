package com.share.book.service.impl;

import com.share.book.entity.ReadBook;
import com.share.book.mapper.ReadBookMapper;
import com.share.book.service.IReadBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 章节保存表 服务实现类
 * </p>
 *
 * @author zoubin
 * @since 2018-10-27
 */
@Service
public class ReadBookServiceImpl extends ServiceImpl<ReadBookMapper, ReadBook> implements IReadBookService {

}
