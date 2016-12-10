package com.jw.ssm.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.jw.ssm.pojo.Book;
import com.jw.ssm.service.IBookService;

@RunWith(SpringJUnit4ClassRunner.class) //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations={"classpath:spring-mybatis.xml"})
public class TestBookService{
	
	private static Logger logger = Logger.getLogger(TestBookService.class);
	
	//private ApplicationContext at = null; //不用注解方式
	
	//@Autowired和@Resource效果一样
	@Resource
	private IBookService bookService = null;
	
	/*@Test
	public void testGetBookById(){
		Book book = null;
		book = bookService.getBookById(18);
		if(book!=null){
		
			System.out.println(book);
		}else{
			System.out.println("图书不存在");
		}
	}
	*/
	
//  @Before  
//  public void before() {  
//      ac = new ClassPathXmlApplicationContext("spring-mybatis.xml");  
//      bookService = (IBookService) ac.getBean("bookService");
//  }  
  
    @Test  
    public void testGetBookById() {
        Book book = bookService.getBookById(18);  
        if(book==null){
        	System.out.println("此图书不存在");
        }else{
        	  logger.info(JSON.toJSONString(book));
        }
    }  
	
    
}
