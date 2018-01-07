var pageUtil = {};

//{id:tId, formId:formId, url:url, page:page, rows:rows, sidx:sidx, sord:sord, columns:columns, headers:headers, ischecked:false, multiselect: false,rownumbers:false}
//headers:["用户名", "密码"]
//columns:[{name:字段名, dateformat:时间格式如果是时间类型的话, formatter:formatter(value, rowData)}]
pageUtil.init = function(opt){
	if(opt.columns == null || opt.headers == null) {
		TOP_TIP_UTIL.msg({msg:"columns 和  headers 不能为空!", rollBack:null, icon:5});
		return null;
	}
	if(opt.columns.length != opt.headers.length) {
		TOP_TIP_UTIL.msg({msg:"columns 与  headers 数量不对应!", rollBack:null, icon:5});
		return null;
	}
	var tbObj = {
			"lay-user-tId":opt.id,//用户定义的展示table节点
			"lay-self-tId":opt.id+"_table",//表格id
			"lay-self-tbodyId":opt.tId+"_tbody",//访问路径
			"lay-self-formId":opt.formId,//表单id
			"lay-self-url":opt.url,//访问路径
			"lay-self-page":1,//当前页
			"lay-self-rows":opt.rows,//一页显示多少条
			"lay-self-rowList":opt.rowList,//每页显示条数切换
			"lay-self-records":0,//总条数
			"lay-self-total":0,//总页数
			"lay-self-sidx":opt.sidx, //排序名称
			"lay-self-sord":opt.sord,//排序类型
			"lay-self-isChecked":opt.ischecked,//是否有可选按钮-默认单选按钮
			"lay-self-multiselect":opt.multiselect,//是否多选
			"lay-self-rownumbers":opt.rownumbers,//是否显示行,
			"lay-self-datas":undefined,//数据行
			//成功回调
			onsuccess:opt.onsuccess,
			//列名
			columns:opt.columns,
			//表头
			headers:opt.headers,
			//构造表头
			constructorTh : function(){
				var that = this;
				var tbodyId = that["lay-self-tbodyId"];
				var tId = that["lay-self-tId"];
				var columnsLs = that.columns;
				var headerLs = that.headers;
				var thStr = "";
				if(that["lay-self-rownumbers"]){
					thStr = thStr + '<th>行号</th>';
				}
				if(that["lay-self-isChecked"]){
					if(that["lay-self-multiselect"]){
						thStr = thStr + '<th><input type="checkbox" flag="allck"/></th>';
					}else{
						thStr = thStr + '<th></th>';
					}
				}
				for(var i=0; i<headerLs.length; i++){
					var c = columnsLs[i];
					if(c.width){
						thStr = thStr + '<th>'+$.trim(headerLs[i])+'</th>';
					}else{
						thStr = thStr + '<th>'+$.trim(headerLs[i])+'</th>';
					}
				}
				thStr = '<thead>' + thStr + '</thead>';
				var tbodyStr = '<tbody id="'+tbodyId+'"></tbody>';
				var tbStr = '<table id="'+tId+'"class="layui-table">'+thStr+tbodyStr+'</table>';
				$("#"+that["lay-user-tId"]).empty().append(tbStr);
			},
			//构造表格数据
			constructorTbody : function(data){
				var that = this;
				var tbodyStr = "";
				var tbodyId = that["lay-self-tbodyId"];
				if(data){
					var columnsLs = that.columns;
					for(var i=0; i<data.length; i++){
						var d = data[i];
						var trStr = "";
						if(that["lay-self-rownumbers"]){
							trStr = trStr + '<td style="width:30px;">'+(i+1)+'</td>';
						}
						if(that["lay-self-isChecked"]){
							if(that["lay-self-multiselect"]){
								trStr = trStr + '<td style="width:10px;"><input type="checkbox" name="aick" flag="ai-ck" value="'+i+'" /></td>';
							}else{
								trStr = trStr + '<td style="width:10px;"><input type="radio" name="aird" flag="ai-rd" value="'+i+'" /></td>';
							}
						}
						for(var j=0; j<columnsLs.length; j++){
							var c = columnsLs[j];
							var tdStr = d[c.name];
							var formatter = columnsLs[j].formatter;
							if(formatter){
								tdStr = formatter(tdStr, d);
							}
							if(c.dateformat){
								tdStr = pageUtil.formatDate(tdStr, c.dateformat);
							}
							trStr = trStr + '<td>'+tdStr+'水电费水电费是分</td>';
						}
						tbodyStr = tbodyStr + '<tr>'+ trStr +'</tr>';
					}
				}
				$("#"+tbodyId).empty().append(tbodyStr);
				that["lay-self-datas"] = data;
			},
			//构造分页
			constructorPage:function(data){
				var that = this;
				var pageStr = "";
				var allPage = data.total;
				var page = data.page;
				var dataTablesInfo = "共 "+data.records+" 条记录, 共 "+data.total+" 页, 当前显示第  "+data.page+" 页 .";
				var pageItemLs = '';
				if(page > 1){
					pageItemLs = pageItemLs + '<a href="javascript:void(0);" class="layui-laypage-prev" ai-page="'+(page-1)+'" >上一页</a>';
				}
				pageItemLs = pageItemLs + '<a href="javascript:void(0);" class="laypage_first" title="首页" ai-page="'+1+'">首页</a>';
				allPage = allPage ? allPage : "0";
				var i = page;
				i = i == 1 ? i : page - 1;
				var j = 0;
				if(page > parseInt(allPage) - 2){
					i = parseInt(allPage) - 4;
					i = i <= 1 ? 1 : i;
					for(;i<=parseInt(allPage) && j<5; i++, j++){
						var cls = "";
						if(i == page) cls = "background-color:#009688; color:#fff;";
						pageItemLs = pageItemLs+'<a href="javascript:void(0);" style="'+cls+'" ai-page="'+(i)+'" >'+i+'</a>';
					}
				}else{
					for(;i<=parseInt(allPage) && j<5; i++, j++){
						var cls = "";
						if(i == page) cls = "background-color:#009688; color:#fff;";
						pageItemLs = pageItemLs+'<a href="javascript:void(0);" style="'+cls+'" ai-page="'+(i)+'" >'+i+'</a>';
					}
				}
				if(allPage >= i) {
					pageStr = pageStr + '<span>…</span>';
					var j = i;
					var k = i + 2;
					for(;i<=parseInt(allPage) && j<k; i++, j++){
						pageItemLs = pageItemLs+'<a href="javascript:void(0);">'+i+'</a>';
					}
				}
				pageItemLs = pageItemLs + '<a href="javascript:void(0);" class="laypage_last" title="末页" ai-page="'+(allPage)+'" >末页</a>';
				if(page < allPage){
					pageItemLs = pageItemLs + '<a href="javascript:void(0);" class="layui-laypage-next" ai-page="'+(page+1)+'" >下一页</a>';
				}
				var rowList = that["lay-self-rowList"];
				var rowSel = "";
				if(rowList){
					for(var m=0; m<rowList.length; m++){
						var sel = "";
						var aiRows = that["lay-self-rows"];
						if(aiRows == rowList[m]) sel = "selected";
						rowSel = rowSel + '<option value="'+rowList[m]+'" '+sel+' >'+rowList[m]+'</option>';
					}
					rowSel = '<select flag="rowSel" style="float:right; margin:10px 10px;width:60px; height:30px;" class="layui-input" >'+rowSel+'</select>';
				}
				pageStr = 
					'<div class="dataTables_info" style="float:left; margin-top:15px; color:#666; font-size:14px;">' + dataTablesInfo + '</div>'+
					'<div class="layui-box layui-laypage layui-laypage-molv" style="float:right; padding-right:1px;">'+pageItemLs+'</div>'+
					rowSel;
				if(document.getElementById("page_"+that["lay-user-tId"])){
					$("#page_"+that["lay-user-tId"]).empty().append(pageStr);
				}else{
					pageStr = '<div style="clear:both;" id="page_'+that["lay-user-tId"]+'">' + pageStr+ '</div>';
					$("#"+that["lay-user-tId"]).append(pageStr);
				}
				$("#page_"+that["lay-user-tId"]).find("a").unbind("click");
				$("#page_"+that["lay-user-tId"]).find("a").bind("click", function(){
					var aiPage = $(this).attr("ai-page");
					that["lay-self-page"] = aiPage;
					that.doFind();
				});
				$("#page_"+that["lay-user-tId"]).find("select[flag='rowSel']").unbind("change");
				$("#page_"+that["lay-user-tId"]).find("select[flag='rowSel']").bind("change", function(){
					var aiRows = this.value;
					that["lay-self-rows"] = aiRows;
					that.doFind();
				});
			},
			//绑定复选框，选择框选中事件
			bindCkEvent : function(){
				var that = this;
				var tId = that["lay-self-tId"];
				$("#"+tId).find("input[flag='allck']").unbind("click");
				$("#"+tId).find("input[flag='allck']").bind("click", function(){
					var ck = this.checked;
					$("#"+tId).find("input[flag='ai-ck']").each(function(){
						this.checked = ck
					});
				});
				
				$("#"+tId).find("input[flag='ai-ck']").unbind("click");
				$("#"+tId).find("input[flag='ai-ck']").bind("click", function(){
					var ck = this.checked;
					if(ck){
						var ckSel = $("#"+tId).find("input[flag='ai-ck']");
						var allCk = true;
						for(var i=0; i<ckSel.length; i++){
							if(!ckSel[i].checked) {
								allCk = false;
								break;
							}
						}
						if(allCk) $("#"+tId).find("input[flag='allck']")[0].checked = true;
					}else{
						$("#"+tId).find("input[flag='allck']")[0].checked = false;
					}
				});
			},
			//获取列表
			doFind : function(){
				var that = this;
				var loadIndex = TOP_TIP_UTIL.loading("请稍等,正在拼命加载中....", false);
				var url = that["lay-self-url"];
				var callBack = function(flag, data){
					TOP_TIP_UTIL.unLoading(loadIndex);
					if(!flag) return;
					var rs = data.rows;
					//构造表数据
					that.constructorTbody(rs);
					//构造分页
					that.constructorPage(data);
					//绑定复选框，选择框选中事件
					that.bindCkEvent();
					if(that.onsuccess) that.onsuccess();
				};
				var params = $.formParams("#"+that["lay-self-formId"]);
				params.page = that["lay-self-page"];
				params.rows = that["lay-self-rows"];
				params.sidx = that["lay-self-sidx"];
				params.sord = that["lay-self-sord"];
				SERVER_REQ_UTIL.doAsyncAndPostReq(url, params, callBack);
			},
			//供外围发起查询调用
			go2Seach:function(){
				var that = this;
				that["lay-self-page"] = 1;
				that.doFind();
			},
			//获取选中的行
			getSelectRow:function(){
				var that = this;
				var tId = that["lay-self-tId"];
				var objSel = undefined;
				if(that["lay-self-isChecked"]){
					if(that["lay-self-multiselect"]){
						objSel = $("#"+tId).find("input[flag='ai-ck']");
					}else{
						objSel = $("#"+tId).find("input[flag='ai-rd']");
					}
				}
				var selData = [];
				var data = that["lay-self-datas"];
				if(objSel != null && data){
					for(var i=0; i<objSel.length; i++){
						if(!objSel[i].checked) continue;
						var index = $(objSel[i]).val();
						selData.push(data[index]);
					}
				}
				return selData;
			},
			//执行导出
			doExportExcel : function(columnNames, columnFields, url, param){
				var that = this;
				param.page = that["lay-self-page"];
				param.rows = that["lay-self-rows"];
				param.sidx = that["lay-self-sidx"];
				param.sord = that["lay-self-sord"];
				param["columnNames"] = columnNames;
				param["columnFields"] = columnFields;
				var excelPortFormId = "excelPortForm";
				var form = document.getElementById(excelPortFormId);
				if(!form){
					form = jQuery('<form action="'+url+'" method="post" id="'+excelPortFormId+'" name="'+excelPortFormId+'"></form>');	
				}
				$(form).empty();
				for ( var p in param ){
					$(form).append('<input type="hidden"  name="'+p+'" value="'+param[p]+'"/>');	
				}
				jQuery(form).css('position', 'absolute');
				jQuery(form).css('top', '-2000px');
				jQuery(form).css('left', '-2000px');
				jQuery(form).appendTo('body');	
				$("#"+excelPortFormId).submit();
			},
			//执行导出
			go2Export:function(columnNames, columnFields, url, param, isAll){
				var that = this;
				if(isAll) param.flag = "all";
				that.doExportExcel(columnNames, columnFields, url, param);
			}
	};
	tbObj.constructorTh();
	return tbObj;
};

//实现思路：获得毫秒数 再转化为需要时间格式。形如：yyyy-MM-dd
//比如：后台传一个date类型的数据，d,调用的时候只要formatDate(d, "yyyy-MM-dd")
pageUtil.formatDate = function(time, format) {
	if(!time || time == 0) return "";
	if(!format) format = "yyyy-MM-dd HH:mm:ss";
	var t = new Date(time);
	var tf = function(i) {
		return (i < 10 ? '0': '') + i;
	};
	return format.replace(/yyyy|MM|dd|HH|mm|ss/g,
			function(a) {
		switch (a) {
		case 'yyyy':
			return tf(t.getFullYear());
			break;
		case 'MM':
			return tf(t.getMonth() + 1);
			break;
		case 'mm':
			return tf(t.getMinutes());
			break;
		case 'dd':
			return tf(t.getDate());
			break;
		case 'HH':
			return tf(t.getHours());
			break;
		case 'ss':
			return tf(t.getSeconds());
			break;
		}
	});
};


