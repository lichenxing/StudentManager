<%@page import="com.student.bean.Classes"%>
<%@page import="java.util.List"%>
<%@page import="com.student.bean.Teacher"%>
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
  <form action="UpdateTeachers.teacher" method="post" name="fom" id="fom" >
<div class="MainDiv">

<table width="50%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >个人信息页</th>
  </tr>
  <tr>
    <td class="CPanel">
		
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
	<%
	    Teacher tea = (Teacher)request.getAttribute("teacher");
	    %>

		<TR>
			<TD width="100%">
				<fieldset style="height:100%;">
				<legend>编辑个人信息</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					 
					  <tr>
					    <td nowrap align="right" width="13%">用户名:</td>
					    <td width="41%">
					    <input name="ID" value="<%=tea.getId() %>" type="hidden" />
					    <input name="Username" id="Text1" value="<%=tea.getUsername() %>" class="text" style="width:154px" /></td>
					  </tr>
					 
			        
					  <tr>
					    <td align="right" width="19%">密码:</td>
					    <td width="27%"><input name="Password" id="Input22" value="<%=tea.getPassword() %>" class="text" style="width:154px" /></td>
					    </tr>
					  <tr>
					    <td align="right" width="19%">教授课程:</td>
					    <td>
					        <select name="CID" id="CID">
					            <%
					                List<Classes> cls = (List<Classes>)request.getAttribute("cls");
					                for(int i=0;i<cls.size();i++)  {
                                        Classes c = cls.get(i);
					             %>
					                <option value="<%=c.getId() %>"><%=c.getName() %></option>
					             <%} %>
					        </select>
					    </td>
					  </tr>
					  
					  </table>
					  <script type="text/javascript">
					      var CID = document.getElementById("CID");
					      var CIDS = '<%=tea.getCID() %>';
					      for (var i = 0; i < CID.options.length; i++) {
					          if (CID.options[i].value == CIDS) {
					              CID.options[i].selected = true;
					          }
					      }
					  </script>
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
