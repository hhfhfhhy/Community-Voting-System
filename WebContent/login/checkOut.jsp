<%
	session.setAttribute("userID","");
 %>
<script type="text/javascript">
var browserName = navigator.appName;
    if (browserName=="Netscape") {
        window.open('', '_self', '');
        window.close();
    }
    else {
        window.opener = "whocares";
        window.opener = null;
        window.open('', '_top');
        window.close();
    }
</script>