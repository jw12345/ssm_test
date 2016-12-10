<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'update.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body> 
    <center>
    <h1><font color="red">Update Book</font></h1>
    
    <form action="book/updateBook" method="post">
    	&nbsp;<label>I D:</label>&nbsp;&nbsp;&nbsp;&nbsp;
    	<input type="text" name="bookId" id="bookId" value="${book.bookId}" readonly="readonly"/> <br/> <br/>
        <label>书名：</label>
        <input name="name" id="name" value="${book.name}" /><br/><br/>
        <label>描述：</label>
        <input name="description" id="description" value="${book.description}" /><br/><br/>
        <input type="submit" name="submit" value="submit" />
    </form>
   </center>
  </body>
</html>
