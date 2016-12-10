package com.jw.ssm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.jw.ssm.pojo.Book;
import com.jw.ssm.service.IBookService;
import com.jw.ssm.util.Page;
/**
 * 对图书的CRUD等操作的Controller
 * @author jw
 *
 */
@Controller
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private IBookService bookService;
	//通过id查
	@RequestMapping("/findBookById")
	public String tofindById(HttpServletRequest request,Model model){
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		List<Book> list = new ArrayList<Book>();
		Book book = bookService.getBookById(bookId);
		
		if(book!=null){
			list.add(book);
			model.addAttribute("books",list);
		}
		return "list";
	}
	//显示图书（不分页）
	@RequestMapping("/showBook")
	public ModelAndView toShowBook(){
		//System.out.println("aaa");
		//int bookId = Integer.parseInt(request.getParameter("bookId"));
		//Book book = this.bookService.getBookById(bookId);
		List<Book> bookList = this.bookService.getBooks();
		//request.setAttribute("books",bookList);
		ModelAndView modelAndView = new ModelAndView("list");
		modelAndView.addObject("books", bookList);
		return modelAndView;
	}
	//显示图书（分页）
	@RequestMapping(value="/showByPage", method = RequestMethod.GET)
	public String toShowBookByPage(HttpServletRequest request,Model model){
		this.bookService.showBooksByPage(request, model);
		return "listbypage";
	}
	//跳到保存图书录入界面
	@RequestMapping("/savePage")
	public String toSavePage(){
		return "save";
	}
	//保存图书
	@RequestMapping("/saveBook")
	public ModelAndView toSaveBook(Book book){
		//struts2接受表单传过来的数据只要form表单里的name属性值是对象名.属性名，
		//但springMVC这里的form表单直接属性名就可以通过对象参数接收到（两个MVC的ioc区别）
		
		System.out.println("增加"+book);
		bookService.saveBook(book);
		//System.out.println(book);
		ModelAndView modelAndView = new ModelAndView();
		//防止刷新页面重复提交，重定向
		//modelAndView.setViewName("redirect:list");
		//modelAndView.addObject("books",bookService.getBooks());
		modelAndView.setViewName("redirect:showByPage");
		return modelAndView;
	}
	//去更新页面
	@RequestMapping("/updatePage")
	public String toUpdatePage(HttpServletRequest request,Map<String, Book> model,Book book){
		//String id = request.getParameter("bookId");
		//System.out.println(id);
		Integer bookId = book.getBookId();
		System.out.println(bookId);
		//bookService.getBookById(bookId);
		model.put("book", bookService.getBookById(bookId));
		//bookService.updateBook(this.book);
		return "update";
	}
	//更新
	@RequestMapping("/updateBook")
	public String toUpdateBook(Book book,Model model){
		bookService.updateBook(book);
		
		model.addAttribute("books", bookService.getBooks());
		//return "redirect:list";
		return "redirect:showByPage";
	}
	//删除
	@RequestMapping("/deleteBook")
	public String toDeleteBook(Book book,Page page,HttpServletRequest request){
		
		bookService.deleteBookById(book.getBookId());
		//request.setAttribute("books",bookService.getBooks());
		//return "redirect:list";
		
		return "redirect:showByPage?pageNow="+page.getPageNow();
	}
	//批量删除
	@RequestMapping("/batchDelete")
	@ResponseBody
	public void toBatchDelBook(@RequestParam(value="Ids[]") Integer[] Ids){
		//for(Integer bookId:listId){
		//System.out.println(Ids[0]+" "+Ids[1]);
		System.out.println(JSON.toJSONString(Ids));
		bookService.batchDelBook(Ids);
		//}
		//model.addAttribute("books", bookService.getBooks());
		//return "list";
	}
}
