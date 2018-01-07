var CASE_= {
	//内容id
	rootId: 'root-id',
	//表格id
	tableId: 'table-data-ls',
	//表单id
	formId: 'seach-form',
	//表格配置对象
	tbConfig: undefined,
	//初始化表格
	initTable: function() {
		var that = this;
		var obj = table.render({
			elem: '#' + that.tableId,
			url:ctx + '/caseBatch/batchLs.do',
			//如果无需传递额外参数，可不加该参数
			//where: {token: 'sasasas', id: 123} 
			//如果无需自定义HTTP类型，可不加该参数
			method: 'post',
			//如果无需自定义请求参数，可不加该参数
			//request: {}
			//如果无需自定义数据响应名称，可不加该参数
			//response: {} 
			cols: [[
			      {type:'checkbox'},
		          {field:'cbatAreaId', title: '催收区域'},
		          {field:'cbatCode', title: '批次号', templet: function(d){
		              return '<a href="javascript:void(0);" cbat-id="' + d.cbatId + '" class="link" definition-filter="go2Cases">' + d.cbatCode + '</a>';
		          }},
		          {field:'cbatTypBid', title: '委托方'},
		          {field:'cbatDate', title: '委案日期', templet: function(d){
		              return COMMONS_JS.formatDate(d.cbatDate, "yyyy-MM-dd");
		          }},
		          {field:'cbatNum', title: '户数'},
		          {field:'cbatMon', title: '总金额'},
		          {field:'cbatTypeId', title: '案件类型'},
		          {field:'cbatBackdateP', title: '预计退案日期', templet: function(d){
		              return COMMONS_JS.formatDate(d.cbatBackdateP, "yyyy-MM-dd");
		          }},
		          {field:'cbatUpDate', title: '录入日期', templet: function(d){
		              return COMMONS_JS.formatDate(d.cbatUpDate, "yyyy-MM-dd");
		          }},
		          {field:'cbatInsUser', title: '录入人'},
		          {field:'cbatTips', title: '分配提示'},
		          {field:'cbatRemark', title: '批次备注'},
		          {title: '操作',width:'180', toolbar: '#table-tool-bar'}
		   ]],
		   done: function(res, curr, count){
        	  //如果是异步请求数据方式，res即为你接口返回的信息。
        	  //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
        	  console.log(res);
        	  //得到当前页码
        	  console.log(curr); 
        	  //得到数据总量
        	  console.log(count);
    	   },
    	   limit:1,
    	   limits:[1,30,50],
		   page: true
		});
		that.tbConfig = obj.config;
		return that;
	},
	//查询
	go2Seach: function() {
		var that = this;
		var params = $.formParams("#"+that.formId) || {};
		table.reload(that.tableId, {
			where: params
		});
		return that;
	},
	//获取选中数据
	getCheckData: function(){ 
		var that = this;
		var checkStatus = table.checkStatus(that.tableId)
			,data = checkStatus.data;
    },
	//弹出dialog
	openDialog:function(title, url){
		var that  = this;
		//弹出新增窗口
		TOP_TIP_UTIL.openIframe({
			url:url,
			title:title, 
			closeBtn:true,
			w:"670px",
			h:"500px",
			rollBack : function(pObj){
				//关闭弹出窗口后的回调函数，可以在这里写业务操作后的处理
				if(pObj) that.go2Seach();
			}
		});
	},
	//新增
	go2Add: function() {
		var that = this;
		that.openDialog("添加批次", ctx+"/caseBatch/edit.do");
	},
	//修改
	go2Update: function(id) {
		var that = this;
		if(!id) {
			var datas = that.getCheckData();
			if(!datas || datas.length != 1) {
				TOP_TIP_UTIL.msg({msg:"请选择一条批次修改!", rollBack:null});
				return;
			}
			id = datas[0].id;
		}
		that.openDialog("修改批次", ctx+"/caseBatch/edit/"+id+".do");
	},
	//删除
	go2Del: function(ids) {
		var that = this;
		if(!ids){
			var datas = that.getCheckData();
			if(!datas || datas.length <= 0){
				TOP_TIP_UTIL.msg({msg:"请选择要删除的批次!", rollBack:null});
				return;
			}
			var idsTemp = "";
			for(var i=0; i<datas.length; i++){
				idsTemp = idsTemp + "," + datas[i].id;
			}
			if(idsTemp.length > 0) ids = idsTemp.substring(1);
		}
		//删除前调用确认框
		TOP_TIP_UTIL.confirm({title:"删除提示", content:"确认删除所选批次？", rollBack:function(flag){
			if(!flag) return;
			var callBack = function(flag, result){
				if(!flag) return;
				that.go2Seach();
			};
			SERVER_REQ_UTIL.doAsyncAndPostReq(
				ctx + "/caseBatch/delete/"+ids+".do",
				{},
				callBack
			);
		}});
	},
	go2Cases: function(obj) {
		
	},
	go2StopCase: function(obj) {
	},
	go2ExportBatch: function(obj) {
	},
	go2ExportSel: function(obj) {
		var that = this;
		that.go2ExportSeach(obj, true);
	},
	go2ExportSeach: function(obj, all) {
		var that = this;
		var fields = '', heads = '';
		var cols = that.tbConfig.cols[0]||[];
		for(var i=0; i<cols.length; i++) {
			if(!cols[i].field)  continue;
			fields += ',' + cols[i].field;
			heads += ',' + cols[i].title;
		}
		if(fields.length > 0) fields = fields.substring(1);
		if(heads.length > 0) heads = heads.substring(1);
		var url = ctx + '/caseBatch/export.do';
		var params = $.formParams("#"+that.formId) || {};
		params.page = that.tbConfig.page.curr||0;
		params.limit = that.tbConfig.page.limit||0;
		params.title = "案件批次号";
		if(all) params.flag = "all";
		COMMONS_JS.doExportExcel(heads, fields, url, params);
	},
	go2ReviewCase: function(obj) {
	},
	go2CloseCase: function(obj) {
	},
	go2Del4BarEvent: function(data) {
		var that = this;
		that.go2Del(data.cbatId);
	},
	go2Edit4BarEvent: function(data) {
		var that = this;
		that.go2Update(data.cbatId);
	},
	go2CaseBack4BarEvent: function(data) {
		alert(3)
	},
	go2CommentEdit4BarEvent: function(data) {
		alert(4)
	},
	go2MergeCase4BarEvent: function(data) {
		alert(5)
	},
	go2ExportCollectRecords4BarEvent: function(data) {
		alert(6)
	},
	//绑定事件
	bindEvent: function() {
		var that = this;
		table.on('tool('+that.tableId+')', function(obj){
			var data = obj.data;
			var event = obj.event+'4BarEvent';
			if(that[event]) that[event](data);
			else console.info("未定义" + event + "方法");
		});
		$('#'+that.rootId).find('a[definition-filter]').each(function(index, item) {
			var eventName = item.getAttribute('definition-filter');
			$(item).bind('click', function() {
				if(that[eventName]) that[eventName](this);
				else console.info("未定义" + eventName + "方法");
			});
		});
	}
};

$(document).ready(function() {
	CASE_.initTable().bindEvent();
	form.render();
});

