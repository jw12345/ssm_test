package com.jw.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jw.ssm.pojo.Book;

public interface IBookDao {
	//方法名对应映射文件里的id属性值
    int deleteById(Integer bookId);
    
    int batchDelete(List<Integer> listId);

    int insert(Book book);

    int insertSelective(Book book);

    Book selectById(Integer bookId);
    
    List<Book> selectAll();
    //分页
    List<Book> selectBooksByPage(@Param(value="startPos") Integer startPos,@Param(value="pageSize") Integer pageSize);
    //总记录数
    long getBooksCount();
    
    int updateByIdSelective(Book book);

    int updateById(Book book);
}