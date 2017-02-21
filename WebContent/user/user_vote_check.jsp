<%@ page import="com.ovs.db.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.ovs.model.*"%>
<%@ page import="com.ovs.beans.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网上投票系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="/OVS/admin.css" rel="stylesheet" type="text/css" />
<script src="/CSIOSystem/js/clock.js"></script>
</head>
<jsp:useBean id="logcurrentUser" class="com.ovs.model.User"
	scope="session"></jsp:useBean>
<%
	String result=request.getParameter("result");
//图表样式。1饼图，2条状图，3折线图,0为首页
    String VoteID=request.getParameter("VoteID");

    //VoteEventBean
    VoteEventBean veb=new VoteEventBean();
    int VoteEventCount=veb.getVoteEventCount(VoteID);//count为选项个数
    //System.out.println(VoteEventCount);
    
    //我还需要获取用户投了单个选项的个数，以及用户对此投票一共投了几票
    //UserVoteBean。获得VoteID每个选项的得票数
    UserVoteBean uvb=new UserVoteBean();
    ArrayList<Integer> ali=new  ArrayList<Integer>();
    for(int i=0;i<VoteEventCount;i++){
        int CertainOptionCount=uvb.getCertainOptionCount(VoteID, ""+i);  
        ali.add(CertainOptionCount);
    }//ali为每个选项得票数
    int TotalOptionCount=uvb.getTotalOptionCount(VoteID);
    
    //VoteInfoBean,获得VoteID的投票。含有标题等信息
    VoteInfoBean vib=new VoteInfoBean();
    Vote temp=vib.getVote(VoteID);
    
  	if(logcurrentUser.getUserID()==null){
  		logcurrentUser.setUser(session.getAttribute("userID").toString());
  	}
  	//System.out.println("userID为：  "+logcurrentUser.getUserID());
  	System.out.println("ali为"+ali.get(0));
%>
<script type="text/javascript" src="jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="highcharts.js"></script>
<script>
//饼图
$(function () {
    $('#container1').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: '<%=temp.getVoteName()%>'
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            type: 'pie',
            name: '比例',
            data: [
                   <%for(int i=0;i<ali.size();i++){
                	   out.println("['"+veb.getOptionContent(VoteID, ""+i)+"',"+ali.get(i)+"]");
                	   if(i!=ali.size()-1){
                		   out.print(",");
                	   }
                   }%>
            ]
        }]
    });
});				
   </script>
<script>
   $(function () {           
	   //条状图
	    $('#container2').highcharts({                                           
	        chart: {                                                           
	            type: 'bar'                                                    
	        },                                                                 
	        title: {                                                           
	            text: '<%=temp.getVoteName()%>'                    
	        },                                                                                                                                 
	        xAxis: {                                                           
	            categories: [<%for(int i=0;i<ali.size();i++){
	            	out.print("'"+veb.getOptionContent(VoteID, i+"")+"'");
	            	if(i!=ali.size()-1){out.print(",");}
	            }%>],
	            title: {                                                       
	                text: null                                                 
	            }                                                              
	        },                                                                 
	        yAxis: {                                                           
	            min: 0,                                                        
	            title: {                                                       
	                text: '得票数',                             
	                align: 'high'                                              
	            },                                                             
	            labels: {                                                      
	                overflow: 'justify'                                        
	            }                                                              
	        },                                                                 
	        tooltip: {                                                         
	            valueSuffix: '得票数'                                       
	        },                                                                 
	        plotOptions: {                                                     
	            bar: {                                                         
	                dataLabels: {                                              
	                    enabled: true                                          
	                }                                                          
	            }                                                              
	        },                                                                 
	        legend: {                                                          
	            layout: 'vertical',                                            
	            align: 'right',                                                
	            verticalAlign: 'top',                                          
	            x: -40,                                                        
	            y: 100,                                                        
	            floating: true,                                                
	            borderWidth: 1,                                                
	            backgroundColor: '#FFFFFF',                                    
	            shadow: true                                                   
	        },                                                                 
	        credits: {                                                         
	            enabled: false                                                 
	        },                                                                 
	        series: [{                                                         
	            name: '得票数',                                             
	            data: [<%for(int i=0;i<ali.size();i++){
	            	out.print(ali.get(i));
	            	if(i!=ali.size()-1){
	            		out.print(",");
	            	}
	            }%>]                                   
	        }]                                                                 
	    });                                                                    
	});                                                                                                                                              				
   </script>
<script>
   //折线图
   $(function () {
	    $('#container3').highcharts({
	        chart: {
	            type: 'line'
	        },
	        title: {
	            text: '<%=temp.getVoteName()%>'
					},
							xAxis : {
								categories : [
<%for(int i=0;i<ali.size();i++){
	            	out.print("'"+veb.getOptionContent(VoteID, i+"")+"'");
	            	if(i!=ali.size()-1){
	            		out.print(",");
	            	}
	            }%>
	]
							},
							yAxis : {
								title : {
									text : '得票数'
								}
							},
							tooltip : {
								enabled : false,
								formatter : function() {
									return '<b>' + this.series.name
											+ '</b><br/>' + this.x + ': '
											+ this.y + '°C';
								}
							},
							plotOptions : {
								line : {
									dataLabels : {
										enabled : true
									},
									enableMouseTracking : false
								}
							},
							series : [ {
								name : '得票数',
								data : [
<%for(int i=0;i<ali.size();i++){
	            	out.print(ali.get(i));
	            	if(i!=ali.size()-1){
	            		out.print(",");
	            	}
	            }%>
	]
							} ]
						});
	});
</script>
<div id="sitebranding">
	<h1>
		<font face="楷体" size="10">网上投票系统</font>
	</h1>
</div>
<div id="tagline">
	<p>
		<span class="right" style="font-size: 15px; font-style: normal;">
			<c>你好！ </c> <d> <%=logcurrentUser.getUserName()%> </d> <d id="txt"></d>
			<b> <a href="/CSIOSystem/login/checkOut.jsp"> 退出</a></b>
		</span>
	</p>
</div>
</header>
<nav>
<ul>
      <li><a href="user_index.jsp">投票事件</a></li>
      <li><a href="user_notice_list.jsp">通知公告</a></li>
      <li><a href="user_info_show.jsp">个人信息</a></li>
</ul>
</nav>


<div id="bodycontent">
	<h5>
		<a><span
			style="color: black; font-weight: normal; font-size: 15px;">您当前的位置：</span></a>->
		<a href="user_index.jsp"><span
			style="color: black; font-weight: normal; font-size: 15px;">首页</span></a>->
		<a href="#"><span
			style="color: black; font-weight: normal; font-size: 15px;">查看投票结果图</span></a>
	</h5>
	<%
		Vote currentVote = new Vote();
		ArrayList<VoteEvent> currentVoteEvent = new ArrayList<VoteEvent>();
		VoteInfoBean myVoteBean = new VoteInfoBean();
	%>

	<div>
		<%
			ArrayList<String> als = uvb.getTheUserOption(VoteID,
					logcurrentUser.getUserID());
			if (als.size() == 0 || als == null) {
				out.println("用户还没有选择任何选项");
			} else {
				out.println("<table align=\"\">");
				out.println("<tr><td><strong><font color=\"blue\">用户选项：</font></strong></td></tr>");
				for (int i = 0; i < als.size(); i++) {
					out.println("<tr><td><strong><font color=\"red\">" + als.get(i)
							+ "</font></strong></td></tr>");
				}
				out.println("</table><br><br>");
			}
		%>
		<table><tr><td><a href="user_vote_changeOption.jsp?VoteID=<%=VoteID%>">修改选项</a></td></tr></table>
	</div>
</div><br>
<div><strong>已投票人数：<%=uvb.getTheUserCount(VoteID) %></strong></div>
<div>
	<table align="center">
		<tr>
			<td><input type="button" value="饼图"
				onclick="window.location.href('user_vote_check.jsp?result=1&VoteID=<%=VoteID%>')">
			</td>
			<td><input type="button" value="条状图"
				onclick="window.location.href('user_vote_check.jsp?result=2&VoteID=<%=VoteID%>')">
			</td>
			<td><input type="button" value="折线图"
				onclick="window.location.href('user_vote_check.jsp?result=3&VoteID=<%=VoteID%>')">
			</td>
		</tr>
	</table>
</div>

<%	
	if (result.equals("0") || result == null) {
		out.println("<div><font color=\"red\">点击选择显示结果</font></div>");
	}
	if (result.equals("1")) {
		out.println("<div id=\"container1\" style=\"min-width: 800px; height: 400px;\"></div>");
	}
	if (result.equals("2")) {
		out.println("<div id=\"container2\" style=\"min-width: 800px; height: 400px;\"></div>");
	}
	if (result.equals("3")) {
		out.println("<div id=\"container3\" style=\"min-width: 800px; height: 400px;\"></div>");
	}
	//System.out.println(result);
%>

<script type="text/javascript" src="js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
</body>
</html>