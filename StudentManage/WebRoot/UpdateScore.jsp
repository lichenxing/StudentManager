<%@page import="com.student.bean.Score"%>
<%@page import="com.student.bean.Student"%>
<%@page import="com.student.bean.Classes"%>
<%@page import="java.util.List"%>
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
  <form action="UpdateScores.score" method="post" name="fom" id="fom" >
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
            Score score = (Score)request.getAttribute("score"); 
        %>
		<TR>
			<TD width="100%">
				<fieldset style="height:100%;">
				<legend>修改分数信息</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					 
					  <tr>
					    <td align="right" width="13%">学生:</td>
					    <td width="41%">
					        <input type="hidden" value="<%=score.getId() %>" name="ID" />
					        <select name="SID"  id="SID">
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
					        <select name="CID" id="CID">
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
					    <td width="27%"><input name="score" id="Input22" value="<%=score.getScore() %>" class="text" style="width:154px" /></td>
					    </tr>
					  
					  
					  </table>
					  
					 <script type="text/javascript">
					     var SID = document.getElementById("SID");
					     var CID = document.getElementById("CID");

					     var SIDS = '<%=score.getSID() %>';
					     var CIDS = '<%=score.getCID() %>';

					     for (var i = 0; i < CID.options.length; i++) {
					         if (CID.options[i].value == CIDS) {
					             CID.options[i].selected = true;
					         }
					     }

					     for (var i = 0; i < SID.options.length; i++) {
					         if (SID.options[i].value == SIDS) {
					             SID.options[i].selected = true;
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
