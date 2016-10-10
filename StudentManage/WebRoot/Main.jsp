<%@page import="com.student.bean.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD Xhtml 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.tabfont01 {	
	font-family: "宋体";
	font-size: 9px;
	color: #555555;
	text-decoration: none;
	text-align: center;
}
.font051 {font-family: "宋体";
	font-size: 12px;
	color: #333333;
	text-decoration: none;
	line-height: 20px;
}
.font201 {font-family: "宋体";
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}
.button {
	font-family: "宋体";
	font-size: 14px;
	height: 37px;
}
html { overflow-x: auto; overflow-y: auto; border:0;} 
-->
</style>

<link href="css/css.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/xiangmu.js"></script>
</head>
<SCRIPT language=JavaScript>
function sousuo(){
	window.open("gaojisousuo.htm","","depended=0,alwaysRaised=1,width=800,height=510,location=0,menubar=0,resizable=0,scrollbars=0,status=0,toolbar=0");
}
function selectAll(){
	var obj = document.fom.elements;
	for (var i=0;i<obj.length;i++){
		if (obj[i].name == "delid"){
			obj[i].checked = true;
		}
	}
}

function unselectAll(){
	var obj = document.fom.elements;
	for (var i=0;i<obj.length;i++){
		if (obj[i].name == "delid"){
			if (obj[i].checked==true) obj[i].checked = false;
			else obj[i].checked = true;
		}
	}
}

function link(){
    document.getElementById("fom").action="xiangmu.htm";
   document.getElementById("fom").submit();

}

function search() {
    var searchText = document.getElementById("searchText").value;
    window.location.href = 'Main.student?searchText='+searchText;
}

</SCRIPT>
<%
        
 %>
<body>
<form name="fom" id="fom" method="post" action="">
<table width="100%" border="0" cellspacing="0" cellpadding="0">

  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="62" background="images/nav04.gif">
          
		   <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
			<td width="21"><img src="images/ico07.gif" width="20" height="18" /></td>
			<td width="550">查询：(姓名 or 学号)
              <input type="text" name="searchText" id="searchText" value="<%=request.getAttribute("searchText") %>"/>
              <input name="Submit" type="button" class="right-button02" value="查 询" onclick="search()"  /></td>
			 <td width="132" align="left"></td>	
		  </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table id="subtree1" style="DISPLAY: " width="100%" border="0" cellspacing="0" cellpadding="0">

        <tr>
          <td><table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">

          	 <tr>
               <td height="20">&nbsp;</td>
          	 </tr>
              <tr>
                <td height="40" class="font42"><table width="100%" border="0" cellpadding="4" cellspacing="1" bgcolor="#464646" class="newfont03">

					                  <tr>
                    <td height="20" colspan="13" align="center" bgcolor="#EEEEEE"class="tablestyle_title"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 学生信息列表 &nbsp;</td>
                    </tr>
                  <tr>
				    
                    <td width="20%" height="20" align="center" bgcolor="#EEEEEE">学号</td>
                    <td width="30%" align="center" bgcolor="#EEEEEE">用户名</td>
                    <td width="30%" align="center" bgcolor="#EEEEEE">密码</td>
                    <td width="20%" align="center" bgcolor="#EEEEEE">操作</td>
                    
                  </tr>
                  <%
                        List<Student> lss = (List<Student>)request.getAttribute("lss");
                      for(int i=0;i<lss.size();i++){
                          Student stu = lss.get(i);
                   %>
                  <tr>
				    <td align="center"  bgcolor="#FFFFFF"><%=stu.getNo() %></td>
                    <td align="center"  height="20" bgcolor="#FFFFFF"><%=stu.getUsername() %></td>
                    <td align="center"  bgcolor="#FFFFFF"><%=stu.getPassword() %></td>
                    <td align="center"  bgcolor="#FFFFFF">
                        <a href="UpdateStudent.student?id=<%=stu.getId() %>">修改</a>&nbsp;
                        <a href="DeleteStudent.student?id=<%=stu.getId() %>">删除</a>
                    </td>

                  </tr>
                  <%} %>
                 
                </table></td>
              </tr>
            </table></td>
        </tr>
      </table>
      </td>
  </tr>
</table>
</form>
</body>
</html>
