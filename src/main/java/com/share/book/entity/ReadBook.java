package com.share.book.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 章节保存表
 * </p>
 *
 * @author zoubin
 * @since 2018-10-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ReadBook implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 章节名称
     */
    @TableField("TITLE")
    private String title;

    /**
     * 章节内容
     */
    @TableField("CONTENT")
    private String content;


}
