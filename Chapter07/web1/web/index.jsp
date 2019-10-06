<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<html>
<head><title>TomcatA</title></head>
<body>

<table align="centre" border="1">
  <tr>
    <td>Session ID</td>
    <td><%= session.getId() %></td>
  </tr>
  <tr>
    <td>Created on</td>
    <td><%= session.getCreationTime() %></td>
  </tr>
</table>
</body>
</html>
sessionID:<%=session.getId()%>
<br>
SessionIP:<%=request.getServerName()%>
<br>
SessionPort:<%=request.getServerPort()%>
<br>
<%
  //为了区分，第二个可以是222
  out.println("This is Tomcat Server 111");

  Object obj = session.getAttribute("counter");
  if( null == obj){
    session.setAttribute("counter" , new Integer(1));
    out.println("该页面被访问了1次<br/>");
  }else{
    int counterVal = Integer.parseInt( obj.toString() );
    out.println("该页面被访问了 " + counterVal +" 次");
    counterVal ++;
    session.setAttribute("counter", counterVal);
  }

%>
