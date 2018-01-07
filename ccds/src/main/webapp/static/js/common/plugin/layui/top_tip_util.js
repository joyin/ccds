var TOP_TIP_UTIL = {
};

//自定义一个弹出层的风格
/*layer.config({
	//注意，目录是相对layer.js根目录。如果加载多个，则 [a.js, b.js, …]
	extend: 'extend/layer.ext.js', //不懂为何扩展无效，需要在页面中用script单独引入才生效
	//1、type layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
	type : 0, 
	//2、title类型3种，string/Array/Boolean ,false 不现实标题
	title: false,
	//3、content String/DOM/Array，默认：''     ,iframe:'http://www.baidu.com', 或无滚动条:['http://www.baidu.com', 'no']
	content : '', 
	//4、skin 皮肤可以自定义，a、使用默认的，b、自定义， 3、下载别人扩展的，通过
	skin : '', 
	//5、area 宽高 area: ['500px', '300px'], 默认auto, 可以只设定宽或高，另一个任然自适应
	area : 'auto', 
	//6、offset - 坐标默认居中 String/Array，默认：'auto' 即垂直水平居中 offset: '100px'只设置顶部top， offset: ['100px', '100px']top和left一起设置， offset:'rb'右下角
	offset : 'auto',
	//7、icon - 图标。信息框和加载层的私有参数 信息框默认不显示图标。当你想显示图标时，默认皮肤可以传入0-6如果是加载层，可以传入0-2
	icon : 0,
	//8、btn 按钮 类型：String/Array，默认：'确认' 信息框模式时，btn默认是一个确认按钮，其它层类型则默认不显示，加载层和tips层则无效。
	//当您只想自定义一个按钮时，你可以btn: '我知道了'，当你要定义两个按钮时，你可以btn: ['yes', 'no']。
	//当然，你也可以定义更多按钮，比如：btn: ['按钮1', '按钮2', '按钮3', …]，按钮1和按钮2的回调分别是yes和cancel，
	//而从按钮3开始，则回调为btn3: function(){}
	btn : '确认',
	//9、closeBtn - 关闭按钮 类型：String/Boolean，默认：1 layer提供了两种风格的关闭按钮，可通过配置1和2来展示，如果不显示，则closeBtn: 0
	closeBtn : 1, 
	//10、shade 类型：String/Array/Boolean，默认：0.3 即弹层外区域。默认是0.3透明度的黑色背景（'#000'）。如果你想定义别的颜色，可以shade: [0.8, '#393D49']；如果你不想显示遮罩，可以shade: 0
	shade : [0.3, '#000'],
	//11、shadeClose - 是否点击遮罩关闭  类型：Boolean，默认：false 如果你的shade是存在的，那么你可以设定shadeClose来控制点击弹层外区域关闭
	shadeClose : false,
	//12、time - 自动关闭所需毫秒 单位毫秒
	time : 0,
	//13、id - 用于控制弹层唯一标识 类型：String，默认：空字符 设置该值后，不管是什么类型的层，都只允许同时弹出一个。一般用于页面层和iframe层模式
	id : '',
	//14、shift - 动画 类型：Number，默认：0 从1.9开始，我们的出场动画全部采用CSS3。这意味着除了ie6-9，其它所有浏览器都是支持的。目前shift可支持的动画类型有0-6
	shift : 0,
	//15、maxmin - 最大最小化。 类型：Boolean，默认：false 该参数值对type:1和type:2有效。默认不显示最大小化按钮。需要显示配置maxmin: true即可
	maxmin : false,
	//16、fix - 固定 类型：Boolean，默认：true 即鼠标滚动时，层是否固定在可视区域。如果不想，设置fix: false即可
	fix : true,
	//17、scrollbar - 是否允许浏览器出现滚动条 类型：Boolean，默认：true 默认允许浏览器滚动，如果设定scrollbar: false，则屏蔽
	scrollbar : true,
	//18、maxWidth - 最大宽度 类型：，默认：360 当area: 'auto'时，maxWidth的设定才有效
	maxWidth : '360',
	//19、zIndex - 层叠顺序 类型：，默认：19891014（贤心生日 0.0） 一般用于解决和其它组件的层叠冲突
	zIndex : 19891014,
	//20、move - 触发拖动的元素 类型：String/DOM/Boolean，默认：'.layui-layer-title' 默认是触发标题区域拖拽。如果你想单独定义，指向元素的选择器或者DOM即可。如move: '.mine-move'。你还配置设定move: false来禁止拖拽
	move : '.layui-layer-title',
	//21、moveType - 拖拽风格 类型：Number，默认：0 默认的拖拽风格正如你所见到的，会有个过度的透明框。但是如果你不喜欢，你可以设定moveType: 1切换到传统的拖拽模式
	moveType : 1,
	//22、moveOut - 是否允许拖拽到窗口外 类型：Boolean，默认：false 默认只能在窗口内拖拽，如果你想让拖到窗外，那么设定moveOut: true即可
	moveOut : false,
	//23、moveEnd - 拖动完毕后的回调方法 类型：Function，默认：null 默认不会触发moveEnd，如果你需要，设定moveEnd: function(){}即可
	moveEnd : null,
	//24、tips - tips方向和颜色 类型：Number/Array，默认：2 tips层的私有参数。支持上右下左四个方向，通过1-4进行方向设定。如tips: 3则表示在元素的下面出现。有时你还可能会定义一些颜色，可以设定tips: [1, '#c00']
	tips : 2,
	//25、tipsMore - 是否允许多个tips 类型：Boolean，默认：false 允许多个意味着不会销毁之前的tips层。通过tipsMore: true开启
	tipsMore : false,
	//26、success - 层弹出后的成功回调方法 类型：Function，默认：null 当你需要在层创建完毕时即执行一些语句，可以通过该回调。success会携带两个参数，分别是当前层DOM当前层索引
	success : null,
	//27、yes - 确定按钮回调方法 类型：Function，默认：null 该回调携带两个参数，分别为当前层索引、当前层DOM对象
	yes : null,
	//28、cancel - 取消和关闭按钮触发的回调 类型：Function，默认：null 该回调同样只携带当前层索引一个参数，无需进行手工关闭。如果不想关闭，return false即可，如 cancel: function(index){ return false; } 则不会关闭；
	cancel : null,
	//29、end - 层销毁后触发的回调 类型：Function，默认：null 无论是确认还是取消，只要层被销毁了，end都会执行，不携带任何参数
	end : null,
	//30、full/min/restore -分别代表最大化、最小化、还原 后触发的回调 类型：Function，默认：null 携带一个参数，即当前层DOM
	full : null,
	min : null,
	restore : null
});*/

TOP_TIP_UTIL.rightDownLoad = function(){
	var msg = o.msg;
	var rollBack = o.rollBack;
	var options = {
			icon : (o.icon ? 7 : o.icon),
			title : (o.title ? o.title : '信息提示')
	};
	var index = window.top.layer.alert(msg, options, function(index){
		window.top.layer.close(index);
		if(rollBack) rollBack(); 
	});
	return index;
};

/**
 * 警告框 o={title:'', msg:'', icon: 7, rollBack : null}
 * icon 可选1-7, 默认7
 * 返回该弹出层的z-index
 */
TOP_TIP_UTIL.alert = function(o){
	var msg = o.msg;
	var rollBack = o.rollBack;
	var options = {
			icon : (o.icon ? 7 : o.icon),
			title : (o.title ? o.title : '信息提示')
	};
	var index = window.top.layer.alert(msg, options, function(index){
		window.top.layer.close(index);
		if(rollBack) rollBack(); 
	});
	return index;
};

/**
 * 询问框 o = {title:'', content:'', rollBack : null}
 * 返回z-index, 回调函数：点击确认返回true,否则返回：false
 */
TOP_TIP_UTIL.confirm = function(o){
	var content = o.content;
	var rollBack = o.rollBack;
	var options = {
			icon : 3,
			title : (o.title ? o.title : '信息提示')
	};
	var index = window.top.layer.confirm(content, options, function(index){
		//确认
		window.top.layer.close(index);
		if(rollBack) rollBack(true); 
	}, function(index){
		//取消
		window.top.layer.close(index);
		if(rollBack) rollBack(false); 
	});
	return index;
};

/**
 * 消息提示框 o = {msg:'', rollBack:null, icon:5}
 * 返回z-index
 */
TOP_TIP_UTIL.msg = function(o){
	var index = window.top.layer.msg(o.msg, {
		icon: (o.icon ? o.icon : 5),
		time: 2000 //2秒关闭（如果不配置，默认是3秒）
	}, function(){
		if(o.rollBack) o.rollBack();
		window.top.layer.close(index);
	});
	return index;
};

/**
 * 加载层
 * 返回z-index
 */
TOP_TIP_UTIL.loading = function(msg, flag){
	msg = msg ? msg : '';
	var index = null;
	if(!flag) index = window.top.layer.msg(msg, {icon: 16, time:false,shade : [0.3, '#000']});
	else index = window.top.layer.load(0, {btn:false, time:false,shade : [0.3, '#000']});
	return index;
};

/**
 * 取消加载层
 */
TOP_TIP_UTIL.unLoading = function(index){
	window.top.layer.close(index);
};

/**
 * tip提示，可以定位到指定的节点上，这个在列表内容较多的时候特别适用
 * msg : 提示消息
 * dom : 是'#id' 或 $('#id') 或 this,this时指向节点的对象
 * direction:方向 1=上,2=右,3=下,4=左,
 * time : 显示时间，多少毫秒后自动隐藏，传0不自动隐藏，默认不自动隐藏
 */
TOP_TIP_UTIL.tips = function(msg, dom, direction, time){
	time = time ? time : 0;
	var index = layer.tips(msg, dom, {tips: direction, btn:false, time:time});
	return index;
};

//		定义一个iframe数据来保存，各个iframe弹出层的回调函数
TOP_TIP_UTIL.iframeRollBack = [];

/**
 * 打开iframe o = {title:'', w:'auto', h:'auto', url:'', maxmin:boolean,closeBtn:boolean, rollBack:null}
 */
TOP_TIP_UTIL.openIframe = function(o){
	if(typeof o.closeBtn == "undefined") 
		o.closeBtn = true;
	var index = window.top.layer.open({
		type: 2, 
		title: (o.title ? o.title : ' '),
		area: (o.w || o.h ? [o.w, o.h] : 'auto'),
		maxmin: (o.maxmin ? o.maxmin : false), 
		content: o.url,
		btn: false,
		closeBtn:(o.closeBtn ? 1 : 0)
	}); 
	window.top.TOP_TIP_UTIL.iframeRollBack.push(o.rollBack);
	return index;
};

/**
 * 关闭指定的iframe，parmas 为回传的参数对象
 */
TOP_TIP_UTIL.closeIframe = function(params){
	//触发iframe回调函数
	var rollBack =  window.top.TOP_TIP_UTIL.iframeRollBack.pop();
	if(rollBack) rollBack(params);
	//先得到当前iframe层的索引
	var index = window.top.layer.getFrameIndex(window.name); 
	//关闭该iframe
	window.top.TOP_TIP_UTIL.close(index);
};

/**
 * 自适应iframe
 */
TOP_TIP_UTIL.iframeAuto = function(){
	//先得到当前iframe层的索引
	var index = window.top.layer.getFrameIndex(window.name); 
	layer.iframeAuto(index);
};

/**
 * tab页 
 * o = {w:'', h:'', maxmin:boolean, tab:[{
 *	    title: '标题', 
 *	    content: '内容'
 *	  }], btn:['确定', '取消', '按钮3', '按钮4',....], yes:null, cancel:null, btn3:null, btn4:null,.....}
 *目前最多只支持5个按钮
 */
TOP_TIP_UTIL.tab = function(o){
	var area = o.w || o.h ? [o.w, o.h] : 'auto';
	var options = {
			maxmin: (o.maxmin ? o.maxmin : false), 
			area: area,
			tab: o.tab
	};
	options.btn = (o.btn ? o.btn : false);
	options.yes = function(index){
		if(o.yes) o.yes();
		window.top.layer.close(index);
	}
	options.cancel = function(index){
		if(o.cancel) o.cancel();
		window.top.layer.close(index);
	}
	if(o["btn"+3]) options["btn"+3] = function(index){
		if(o["btn"+3]) o["btn"+3]();
		window.top.layer.close(index);
	}
	if(o["btn"+4]) options["btn"+4] = function(index){
		if(o["btn"+4]) o["btn"+4]();
		window.top.layer.close(index);
	}
	if(o["btn"+5]) options["btn"+5] = function(index){
		if(o["btn"+5]) o["btn"+5]();
		window.top.layer.close(index);
	}
	var index = window.top.layer.tab(options);
	return index;
};

/**
 * 关闭指定弹出层， index是弹出层z-index的值
 */
TOP_TIP_UTIL.close = function(index){
	window.top.layer.close(index);
};

/**
 * 关闭所有弹出层,或按类型关闭指定类型的弹出层
 * type 可选：
 * 		dialog //关闭信息框
 * 		page //关闭页面框
 * 		iframe //关闭所有的iframe层
 * 		loading //关闭加载层
 * 		tips //关闭所有的tips层 
 */
TOP_TIP_UTIL.closeAll = function(type){
	if(type) window.top.layer.closeAll(type);
	else window.top.layer.closeAll();
}; 

/**
 * 相册展示 参数o 可以是json格式的图片对象，也可以是存放图片的节点id如：'#pics'
 * 1、o = {id:1, title:'', data:[ //相册包含的图片，数组格式
 *    {
 *      "alt": "图片名",
 *      "pid": 666, //图片id
 *      "src": "", //原图地址
 *      "thumb": "" //缩略图地址
 *    }
 *  ]}
 *  
 * 2、o = '#layer-photos-demo'
 * 该种情况必须在页面中建立如下节点形式:
 * <div id="layer-photos-demo" class="layer-photos-demo">
 *	<img layer-pid="图片id，可以不写" layer-src="大图地址" src="缩略图" alt="图片名">
 * </div>
 */
TOP_TIP_UTIL.photos = function(o){
	var json = o;
	if(typeof(o) == "object"){
		json = {
				"title": o.title, //相册标题
				"id": o.id, //相册id
				"start": o.start, //初始显示的图片序号，默认0
				"data": o.data
		};
	}
	window.top.layer.config({btn:false});
	var index = window.top.layer.ready(function(){ //为了layer.ext.js加载完毕再执行
		window.top.layer.photos({
			btn:false,
			shade : [0.7, '#000'],
			photos: json
		});
	});   
	return index;
};













