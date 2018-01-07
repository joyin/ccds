<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/webTagLib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>批次管理</title>
	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<%@ include file="../meta.jsp" %>
	<script type="text/javascript" src="${resourceRoot}/js/batch/batchs.js"></script>
</head>
<body id="root-id">
	<div class="layui-form-item">
	  	<form id="seach-form" class="layui-form seach-form" name="seach-form">
	  		<div class="layui-inline">
	    		<label class="layui-form-label layui-form-label-self">催收区域</label>
	    		<div class="layui-input-inline">
	      			<select name="city" lay-verify="">
					  	<option value="">请选择一个城市</option>
					  	<option value="010">北京</option>
					  	<option value="021">上海</option>
					  	<option value="0571">杭州</option>
					</select>  
	    		</div>
	  		</div>
	  		<div class="layui-inline">
	    		<label class="layui-form-label layui-form-label-self">批次状态</label>
	    		<div class="layui-input-inline">
	      			<input type="text"  id="roleName" name="roleName" class="layui-input">
	    		</div>
	  		</div>
	  		<div class="layui-inline">
	    		<label class="layui-form-label layui-form-label-self">批次号</label>
	    		<div class="layui-input-inline">
	      			<input type="text"  id="roleName" name="roleName" class="layui-input">
	    		</div>
	  		</div>
	  		<div class="layui-inline">
	    		<label class="layui-form-label layui-form-label-self">委托方</label>
	    		<div class="layui-input-inline">
	      			<input type="text"  id="roleName" name="roleName" class="layui-input">
	    		</div>
	  		</div>
	  		<div class="layui-inline">
	    		<label class="layui-form-label layui-form-label-self">案件类型</label>
	    		<div class="layui-input-inline">
	      			<input type="text"  id="roleName" name="roleName" class="layui-input">
	    		</div>
	  		</div>
	  		<div class="layui-inline">
	    		<label class="layui-form-label layui-form-label-self">委案日期</label>
	    		<div class="layui-input-inline">
	      			<input type="text"  id="roleName" name="roleName" class="layui-input">
	    		</div>
	  		</div>
		  	<div class="layui-inline">
		      	<a href="javascript:void(0);" class="layui-btn layui-btn-sm layui-btn-normal" definition-filter="go2Seach">
			  		<i class="layui-icon">&#xe615;</i> 查询
				</a>
				<button type="reset" class="layui-btn layui-btn-sm layui-btn-primary">
			  		<i class="layui-icon">&#x1002;</i> 重置
				</button>
		  	</div>
	  	</form>
	</div>
	<div class="clear"></div>
	<div class="table-content">
		<div id="case-table-tool-bar">
			<div class="fl">
				<a href="javascript:void(0);" class="layui-btn layui-btn-sm layui-btn-normal" definition-filter="go2Add">
					<i class="layui-icon">&#xe608;</i> 添加批次
				</a>
			</div>
			<div class="fr">
			  	<a href="javascript:void(0);" definition-filter="go2StopCase" class="layui-btn layui-btn-sm layui-btn-normal">
			  		<img src="${resourceRoot }/images/stop.png" /> 暂停案件
			  	</a>
			  	<a href="javascript:void(0);" definition-filter="go2CloseCase" class="layui-btn layui-btn-sm layui-btn-normal">
			  		<img src="${resourceRoot }/images/close.png" /> 关闭案件
			  	</a>
			  	<a href="javascript:void(0);" definition-filter="go2ReviewCase" class="layui-btn layui-btn-sm layui-btn-normal">
			  		<img src="${resourceRoot }/images/review.png" /> 回复案件
			  	</a>
			  	<a href="javascript:void(0);" definition-filter="go2ExportSeach" class="layui-btn layui-btn-sm layui-btn-normal">
			  		<img src="${resourceRoot }/images/export.png" /> 导出查询结果
			  	</a>
			  	<a href="javascript:void(0);" definition-filter="go2ExportSel" class="layui-btn layui-btn-sm layui-btn-normal">
			  		<img src="${resourceRoot }/images/export.png" /> 导出所选批次
			  	</a>
			  	<a href="javascript:void(0);" definition-filter="go2ExportBatch" class="layui-btn layui-btn-sm layui-btn-normal">
			  		<img src="${resourceRoot }/images/export.png" /> 批量导出批次催记 
			  	</a>
			</div>
		</div>
		<div class="clear"></div>
		<table class="layui-hide" id="table-data-ls" lay-filter="table-data-ls"></table>
 		<div id="table-tool-bar" class="table-tool-bar">
			<img lay-event="go2ExportCollectRecords" title=导出催收记录 src="${resourceRoot}/images/working.gif">&nbsp;
			<img lay-event="go2MergeCase" title=归并案件(归并批次下同证件号案件,提高批次共债案件查询速度) src="${resourceRoot}/images/arrow_join.gif">&nbsp;
			<img lay-event="go2CommentEdit" title=分配提示 src="${resourceRoot}/images/comment_edit.gif">&nbsp;
			<img lay-event="go2CaseBack" title=退案 src="${resourceRoot}/images/case_back.gif">&nbsp;
			<img lay-event="go2Edit" title=编辑 src="${resourceRoot}/images/edit.gif">&nbsp;
			<img lay-event="go2Del" title=删除 src="${resourceRoot}/images/del.gif">&nbsp;
		</div>  
	</div>
</body>
</html>