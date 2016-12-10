<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'indextest.jsp' starting page</title>
    
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
      <h1><font color="red">ssm_test</font></h1>
      <a href="book/savePage">Insert Book</a>  <br/><br/>
      <a href="book/showByPage">Display Books</a>  <br/><br/>
      <form action="book/findBookById" >
      	<label>请输入ID号：</label>
      	<input name="bookId" id="bookId"/>  <br/><br/>
      	<input type="submit" value="查找"/>
      </form>
      <%=basePath %>
    </center>
  </body>
</html>
