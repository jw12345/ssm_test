<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'listbypage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="js/jquery-1.8.3.min.js" ></script>
	<!--<script src="http://code.jquery.com/jquery-1.10.2.js" />	-->
	<script type="text/javascript">
		function del(bookId,currentPage){
			//alert(currentPage);
			if(confirm("确定删除？")){
				window.location.href="book/deleteBook?bookId="+bookId+"&pageNow="+currentPage;
			}
		}
		
		$(document).ready(function(){
			//全选或不选
			//if($("#selectAll").is(":checked")){
			//	$(":checkbox").prop("checked",true);
			//}else{
			//	$(":checkbox").prop("checked",false);
			//}
			$("#selectAll").click(function(){
			if ($("#selectAll").attr("checked")=="checked") {
				$("input[name='bookId']").attr("checked","checked");
			} else {
				$("input[name='bookId']").removeAttr("checked","checked");
			}
			});
		});
		
		//批量删除
		function batchDel(){
			//判断至少选一项
			var checkedNum = $('input[name="bookId"]:checked').length;
			if(checkedNum==0){
				alert("请选择要删除项！");
				return false;
			}
			if(confirm("确定要删除？")){
				var book_ids = new Array();
				$('input[name="bookId"]:checked').each(
				function(){
					book_ids.push($.trim($(this).val()));
				});
			}
			
			console.log(book_ids);
			var indata = {Ids:book_ids};
			$.post("book/batchDelete?t="+(new Date()).getTime(),indata,function(data,status){
				//if(data!=null && data.result == "ok"){
				//	console.log(data.msg);
				//	alert(data.msg);
				//}else{
				//	alert(data.msg);
				//}
				//alert(status);
				if(status=="success"){
					//console.log(data.msg);
					//window.location.href="book/showBook"
					//setTimeout方法是异步执行，下面这句话会另外开一个线程等待1秒，主线程会继续执行下面的代码，等到等待完了再执行
					//setTimeout('window.location.href="book/showBook"',1000);
					//setTimeout('window.location.href="book/showByPage"',1000);
					//后台Controller已经重定向没有重复提交问题，如果后台没有redirect处理则可用上面这条语句
					setTimeout("window.location.reload()",1000);//延迟1秒刷新页面
					//$("input:checkbox").attr("checked",false);
					alert("删除成功！");
				}else{
					alert("删除失败！");
				}
			},'json');
		}
	</script>
  </head>
  
  <body>
  	<center>
    <h1><font color="red">Book List</font></h1>
    <a href="indextest.jsp">返回</a><br/>
    <table border="1" width="80%" align="center">
    	<tr>
    		<th>
    			<input type="checkbox" id="selectAll"/>
    			<a href="javascript:void(0)" onclick="batchDel()">删除</a>
    		</th>
    		<th>序号</th>
    		<th>书名</th>
    		<th>描述</th>
    		<th>删除</th>
    		<th>更新</th>
    	</tr>
    	<c:forEach var="book" items="${requestScope.books}" varStatus="status">
            <tr>
            	<td align="center">
            		<input type="checkbox" name="bookId" value="${book.bookId}">
            		<span style="visibility:hidden;">选项</span>
            	</td>  
                <td align="center">${book.bookId}</td>
                <td align="center">${book.name}</td>
                <td align="center">${book.description}</td>  
                <td align="center">
                 	<a href="javascript:del('${book.bookId}','${page.pageNow}')">Delete</a>
    				<!--
    				<a href="book/deleteBook?bookId=${book.bookId}">Delete</a>
    				<a href="javascript:void(0)" onclick="del()">Delete</a>
    				-->
    			</td>
    			<td align="center">
    				<a href="book/updatePage?bookId=${book.bookId}">Update</a>
    			</td>
             </tr>  
   		 </c:forEach>  
    </table>
    </center>
    <br/><br/>
    <!-- 分页功能 start -->
	<div align="center">
		<font size="2">共 ${page.totalPageCount} 页</font> 
		<font size="2">第 ${page.pageNow} 页</font> 
		<a href="book/showByPage?pageNow=1">首页</a>
		<c:choose>
			<c:when test="${page.pageNow - 1 > 0}">
				<a href="book/showByPage?pageNow=${page.pageNow - 1}">上一页</a>
			</c:when>
			<c:when test="${page.pageNow - 1 <= 0}">
				<a href="book/showByPage?pageNow=1">上一页</a>
			</c:when>
		</c:choose>
		<c:forEach var="i" begin="1" end="${page.totalPageCount}">
			<a href="book/showByPage?pageNow=${i}">${i}</a>
		</c:forEach>
		<c:choose>
			<c:when test="${page.totalPageCount==0}">
				<a href="book/showByPage?pageNow=${page.pageNow}">下一页</a>
			</c:when>
			<c:when test="${page.pageNow + 1 < page.totalPageCount}">
				<a href="book/showByPage?pageNow=${page.pageNow + 1}">下一页</a>
			</c:when>
			<c:when test="${page.pageNow + 1 >= page.totalPageCount}">
				<a href="book/showByPage?pageNow=${page.totalPageCount}">下一页</a>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${page.totalPageCount==0}">
				<a href="book/showByPage?pageNow=${page.pageNow}">尾页</a>
			</c:when>
			<c:otherwise>
				<a href="book/showByPage?pageNow=${page.totalPageCount}">尾页</a>
			</c:otherwise>
		</c:choose>
	</div>
	<!-- 分页功能 End -->
  </body>
</html>
