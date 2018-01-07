<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/webTagLib.jsp"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>批次编辑</title>
		<meta name="keywords" content="jqgruid" />
		<meta name="description" content="jqgruid" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<%@ include file="../meta.jsp" %> 
		<script type="text/javascript" src="${resourceRoot}/js/batch/batchEdit.js"></script>
	</head>
	<body>
		<div class="form-edit">
			<form class="layui-form layui-form-pane" action="">
				<input type="hidden" class="layui-input" id="cbatId" name="cbatId" value="${caseBat.cbatId }"> 
			  	<div class="layui-form-item">
			    	<label class="layui-form-label">批次号<span class="required">*</span></label>
			    	<div class="layui-input-block">
						<input type="text" class="layui-input" lay-verify="cbatCode" id="cbatCode" name="cbatCode" value="${caseBat.cbatCode }" placeholder="请输入批次号">
			    	</div>
			  	</div>
			  	<div class="layui-form-item">
			  		<div class="layui-inline">
				    	<label class="layui-form-label">委托方<span class="required">*</span></label>
				    	<div class="layui-input-inline">
				    		<select id="cbatTypBid" name="cbatTypBid" lay-verify="cbatTypBid" placeholder="请选择委托方">
							  	<option value=""></option>
							  	<option value="010">北京</option>
							  	<option value="021">上海</option>
							  	<option value="0571">杭州</option>
							</select>  
				    	</div>
			    	</div>
			    	<div class="layui-inline">
				    	<label class="layui-form-label">催收区域</label>
				    	<div class="layui-input-inline">
							<input type="text" class="layui-input" lay-verify="cbatAreaId" id="cbatAreaId" name="cbatAreaId" value="${caseBat.cbatAreaId }" placeholder="请选择催收区域">
				    	</div>
			    	</div>
			  	</div>
			  	<div class="layui-form-item">
			  		<div class="layui-inline">
				    	<label class="layui-form-label">委案日期<span class="required">*</span></label>
				    	<div class="layui-input-inline">
							<input type="text" class="layui-input" lay-verify="cbatDate" id="cbatDate" id="cbatDate" name="cbatDate" value="${caseBat.cbatDate }" placeholder="请选择委案日期">				    	
						</div>
			    	</div>
			    	<div class="layui-inline">
				    	<label class="layui-form-label">案件类型</label>
				    	<div class="layui-input-inline">
							<input type="text" class="layui-input" lay-verify="cbatTypeId" id="cbatTypeId" name="cbatTypeId" value="${caseBat.cbatTypeId }" placeholder="请选择案件类型">
				    	</div>
			    	</div>
			  	</div>
			  	<div class="layui-form-item">
			  		<div class="layui-inline">
				    	<label class="layui-form-label">预计退案日期</label>
				    	<div class="layui-input-inline">
							<input type="text" class="layui-input" lay-verify="cbatBackdateP" id="cbatBackdateP" id="cbatBackdateP" name="cbatBackdateP" value="${caseBat.cbatBackdateP }" placeholder="请选择预计退案日期">				    	
				    	</div>
			    	</div>
			    	<div class="layui-inline">
				    	<label class="layui-form-label">目标回款率</label>
				    	<div class="layui-input-inline">
							<input type="text" class="layui-input" lay-verify="cbatTarget" id="cbatTarget" name="cbatTarget" value="${caseBat.cbatTarget }" placeholder="请输入目标回款率">
				    	</div>
				    </div>
			  	</div>
			  	<div class="layui-form-item layui-form-text">
			    	<label class="layui-form-label">批次备注</label>
			    	<div class="layui-input-block">
						<textarea class="layui-textarea" rows="3" lay-verify="cbatRemark" id="cbatRemark" name="cbatRemark" placeholder="请输入批次备注">${caseBat.cbatRemark }</textarea>
			    	</div>
			  	</div>
			  	<div class="layui-form-item">
			    	<div class="text-center">
				      	<button class="layui-btn layui-btn-sm layui-btn-normal" lay-submit lay-filter="form-edit">确定</button>
				      	<button type="button" id="cancelBtn" class="layui-btn layui-btn-sm layui-btn-primary">取消</button>
			    	</div>
			  	</div>
			</form>
		</div>
	</body>
</html>
