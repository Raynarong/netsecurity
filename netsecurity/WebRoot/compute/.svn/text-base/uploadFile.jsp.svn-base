<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>账号管理</title>
<link rel="stylesheet" href="../easyui-resourse/Public/Themes/gray/easyui.css" type="text/css" media="screen" />
<link rel="stylesheet" href="../easyui-resourse/Public/Themes/icon.css" type="text/css" media="screen" />
<link rel="stylesheet" href="../easyui-resourse/Public/Css/css.css" type="text/css" media="screen" />
<link rel="stylesheet" type="text/css" href="../easyui-resourse/demo.css">
<script type="text/javascript" src="../easyui-resourse/Public/Js/core/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../easyui-resourse/Public/Js/core/jquery.easyui.min.js"></script>
</head>
<body>
<script language="javascript">
	 function trim(str){
       str = str.replace(/^\s*|\s*$/g,'');
       return str;
     };
     function check(){
       var version = trim(document.getElementById("libVersion").value);
       if(version == ''){
        alert("请输入新版本号");
        document.getElementById("libVersion").focus();
        return false;
       }
       
       if(isNaN(version))
       {
        alert("版本号必须是数字");
        return false;
       }
       
       var curVersion = document.getElementById("curVersion").value;
       if(!isNaN(curVersion))
       {
        var cur = parseFloat(curVersion);
        var newVersion = parseFloat(trim(version));
        if(newVersion < cur)
        {
         alert("请注意:新版本号比当前版本号低");
        }
       }
       
       var file = document.getElementById("file").value;
       if(file == "")
       {
        alert("请选择要上传的系统文件");
        return false;
       }
       if(!checkSufix())
       {
       	alert('后缀名不对');
       	return false;
       }
       return true;
     }
</script>
    <FORM onSubmit="return check()" encType="multipart/form-data" method="post" action="uploadOs.action" id="ff">
      <TABLE>
        <TBODY>
          <tr><th colspan="2">文件上传</th></tr>
<!--          <TR>-->
<!--            <TD class="tit"><SPAN class=leftbg>当前版本</SPAN></TD>-->
<!--            <TD><INPUT id=curVersion value="" type=hidden>-->
<!--              ${requestScope.province.osVersion }</TD>-->
<!--          </TR>-->
          <TR>
            <TD class="tit"><SPAN class=leftbg>任务名称</SPAN></TD>
            <TD><INPUT id="name" name="name" size=30></TD>
          </TR>
          <TR>
            <TD class="tit">任务程序打包文件</TD>
            <TD><INPUT id='srcFile' name=upload size=30 type=file accept=".zip"></TD>
          </TR>
          <TR>
            <TD class="tit">任务数据打包文件</TD>
            <TD><INPUT id='dataFile'  size=30 type=file accept=".zip"></TD>
          </TR>
          <TR>
          <td class="tit">运行平台选择</td>
          <td>  win32:<input type="checkbox"> win64:<input type="checkbox"> linux32:<input type="checkbox"> linux64:<input type="checkbox"> </td>
          </TR>
          <TR>
            <TD>&nbsp;</TD>
            <TD><INPUT id=submit onClick="" name=submit value=上传 type=submit style="width:auto;"></TD>
          </TR>
        </TBODY>
      </TABLE>
    </FORM>
  <div style="text-align:left;padding-left:50px;"> </div>

<script>
		function checkSufix(){
		var filename=$('#file').val();
		var filename2=filename.toLowerCase();
		var subfix=filename2.substring(filename2.length-5,filename2.length);
		if(subfix==".spkg")
		return true;
		else
		return false;
		}
		function submitForm(){
			$('#ff').form('submit');
		}
		function clearForm(){
			$('#ff').form('clear');
		}
	</script>
</body>
</html>
