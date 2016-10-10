<%@page import="com.student.bean.Classes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD Xhtml 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>项目管理系统</title>
<link rel="stylesheet" rev="stylesheet" href="css/style.css" type="text/css" media="all" />


<script language="JavaScript" type="text/javascript">

</script>
<style type="text/css">
<!--
.atten {font-size:12px;font-weight:normal;color:#F00;}
-->
</style>
</head>

<body class="ContentBody">
  <form action="UpdateClassess.classes" method="post" name="fom" id="fom" >
<div class="MainDiv">
<%
    Classes cls = (Classes)request.getAttribute("cls");
 %>
<table width="50%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >课程信息页</th>
  </tr>
  <tr>
    <td class="CPanel">
		
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
	

		<TR>
			<TD width="100%">
				<fieldset style="height:100%;">
				<legend>添加课程信息</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					 
					  <tr>
					    <td nowrap align="right" width="13%">课程名:</td>
					    <td width="41%">
					    <input name="ID" type="hidden" value="<%=cls.getId() %>" />
					    <input name="Name" id="Text1" class="text" value="<%=cls.getName() %>" style="width:154px" /></td>
					  </tr>
					 
					  
					  
					  </table>
			 <br />
				</fieldset>			</TD>
		</TR>
		
		</TABLE>
	
	
	 </td>
  </tr>
  

		
		
		
		
		<TR>
			<TD colspan="2" align="center" height="50px">
			<input type="submit" name="Submit" value="保存" class="button"/>　
</TD>
		</TR>
		</table>


</div>
</form>
</body>
</html>
