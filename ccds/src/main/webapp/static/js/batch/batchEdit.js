$(document).ready(function(){
	$("#cancelBtn").bind("click", function(){
		TOP_TIP_UTIL.closeIframe();
	});
	
	//自定义验证规则
	form.verify({
		cbatCode: function(value, obj){
			return COMMONS_JS.valid.init(value, true, false, '1-20', obj);
		},
		cbatTypBid: function(value, obj){
			return COMMONS_JS.valid.init(value, true, false, false, obj);
		},
		cbatAreaId: function(value, obj){
			return COMMONS_JS.valid.init(value, false, false, false, obj);
		},
		cbatDate: function(value, obj){
			return COMMONS_JS.valid.init(value, true, false, false, obj);
		},
		cbatTypeId: function(value, obj){
			return COMMONS_JS.valid.init(value, false, false, false, obj);
		},
		cbatBackdateP: function(value, obj){
			return COMMONS_JS.valid.init(value, false, false, false, obj);
		},
		cbatTarget: function(value, obj){
			return COMMONS_JS.valid.init(value, false, /^\d+\.?\d{0,2}$/, false, obj);
		},
		cbatRemark: function(value, obj){
			return COMMONS_JS.valid.init(value, false, false, '1-20', obj);
		}
		
	});

	//监听提交
	form.on('submit(form-edit)', function(formData){
		var url = ctx + "/caseBatch/add.do";
		if(formData.field.roleUuid) url = ctx + "/caseBatch/update.do";
		var loadIndex = TOP_TIP_UTIL.loading("处理中....");
		var serviceRollBack = function(flag, data){
			TOP_TIP_UTIL.unLoading(loadIndex);
			if(!flag) return;
			TOP_TIP_UTIL.msg({msg:data.msg, rollBack:function(){
				if(data.code == "0000"){
					TOP_TIP_UTIL.closeIframe({});
				}
			}, icon:(data.code == "0000" ? 6 : 5)});
		};
		SERVER_REQ_UTIL.doAsyncAndPostReq(url, formData.field, serviceRollBack);
		return false;
	});


	laydate.render({
		elem: '#cbatDate'
	});
	
	laydate.render({
		elem: '#cbatBackdateP'
	});
	
	form.render();
});