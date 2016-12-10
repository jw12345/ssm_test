package com.jw.ssm.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.jw.ssm.pojo.Book;

public interface IBookService {
	public Book getBookById(int bookId);
	
	public List<Book> getBooks();
	
	public void showBooksByPage(HttpServletRequest request,Model model);
	
	public void saveBook(Book book);
	
	public void updateBook(Book book);
	
	public void deleteBookById(int bookId);
	
	public void batchDelBook(Integer[] Ids);
	
}
