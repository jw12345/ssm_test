<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.jw.ssm.pojo.Book"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>showBook测试</title>

  </head>
  
  <body>
    <% 
    List<Book> bookList = null;
    bookList = (ArrayList<Book>)request.getAttribute("books");
    if(bookList!=null){
    	for(Book book : bookList){
    		out.print(book.toString());
    %>
    <br/>
    <%
    	}
    }else{
    	out.print("不存在");
    }
    %>
  </body>
</html>
