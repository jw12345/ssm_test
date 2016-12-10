package com.jw.ssm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.jw.ssm.dao.IBookDao;
import com.jw.ssm.pojo.Book;
import com.jw.ssm.service.IBookService;
import com.jw.ssm.util.Page;

@Service(value="bookService")
public class BookServiceImpl implements IBookService{
	@Autowired
	private IBookDao bookDao;
	
	public void deleteBookById(int bookId) {
		// TODO Auto-generated method stub
		bookDao.deleteById(bookId);
	}

	public Book getBookById(int bookId) {
		// TODO Auto-generated method stub
		return bookDao.selectById(bookId);
	}

	public void saveBook(Book book) {
		// TODO Auto-generated method stub
		bookDao.insert(book);
	}

	public void updateBook(Book book) {
		// TODO Auto-generated method stub
		bookDao.updateById(book);
	}

	public List<Book> getBooks() {
		// TODO Auto-generated method stub
		return bookDao.selectAll();
	}

	public void batchDelBook(Integer[] Ids) {
		// TODO Auto-generated method stub
		List<Integer> listId = new ArrayList<Integer>();
		for(Integer Id : Ids){
			listId.add(Id);
		}
		if(!listId.isEmpty()){
			bookDao.batchDelete(listId);
		}
	}
	//Controller调用的时候，参数由Controller传入
	public void showBooksByPage(HttpServletRequest request, Model model) {
		// TODO Auto-generated method stub
		Page page = null;
		//对象集合用来存放当前页的书
		List<Book> books = new ArrayList<Book>();
		//得到当前页数
		String pageNow = request.getParameter("pageNow");
		//得到总记录数
		int totalCount = (int) bookDao.getBooksCount();
		
		if(pageNow!=null){
			page = new Page(totalCount,Integer.parseInt(pageNow));
		}else{
			page = new Page(totalCount,1);
		}
		books = bookDao.selectBooksByPage(page.getStartPos(), page.getPageSize());
		//传到前端
		model.addAttribute("books", books);
		model.addAttribute("page",page);
	}
	
}
