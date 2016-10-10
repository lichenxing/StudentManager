<%@page import="com.student.bean.Student"%>
<%@page import="com.student.bean.Classes"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD html 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

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
  <form action="AddScores.score" method="post" name="fom" id="fom" >
<div class="MainDiv">

<table width="50%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >分数信息页</th>
  </tr>
  <tr>
    <td class="CPanel">
		
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
	
        <%
            List<Classes> lsc = (List<Classes>)request.getAttribute("lsc"); 
            List<Student> lss = (List<Student>)request.getAttribute("lss"); 
        %>
		<TR>
			<TD width="100%">
				<fieldset style="height:100%;">
				<legend>添加分数信息</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					 
					  <tr>
					    <td align="right" width="13%">学生:</td>
					    <td width="41%">
					        <select name="SID">
					            <%
                                    for (int i = 0; i < lss.size(); i++)
                                    {
                                        Student stu = lss.get(i);    
					            %>
					                <option value="<%=stu.getId() %>"><%=stu.getUsername() %></option>
					            <%} %>
					        </select>
					    </td>
					  </tr>
					 
					  <tr>
					    <td align="right">课程:</td>
					    <td>
					        <select name="CID">
					             <%
                                     for (int i = 0; i < lsc.size(); i++)
                                    {
                                        Classes cls = lsc.get(i);    
					            %>
					                <option value="<%=cls.getId() %>"><%=cls.getName()%></option>
					            <%} %>
					        </select>
					    </td>
					    
					  </tr>
			        
					  <tr>
					    <td align="right" width="19%">分数</td>
					    <td width="27%"><input name="score" id="Input22" value="" class="text" style="width:154px" /></td>
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