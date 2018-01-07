var MENU_DATA = {};
MENU_DATA.menu = {
	dataMng: {
		url: '/index/subMenu/dataMng.do',
		name: '数据管理',
		sub:[{
			url: '/sys/Role/go2SysRoleLs.do',
			name: '案件导入',
			img: '/images/dataMng/impCase.gif'
		},{
			url: '/caseBatch/batchs.do',
			name: '批次管理',
			img: '/images/dataMng/showCase.gif'
		},{
			url: '',
			name: '案件管理',
			img: '/images/dataMng/searCase2.gif'
		},{
			url: '',
			name: '催记管理',
			img: '/images/dataMng/myCase.gif'
		}]
	},
	collectionMng: {
		url: '/index/subMenu/collectionMng.do',
		name: '催收管理',
		sub:[{
			url: '/sys/Role/go2SysRoleLs.do',
			name: '我的案件',
			img: '/images/collectionMng/searCase1.gif'
		},{
			url: '',
			name: '部门案件',
			img: '/images/collectionMng/searCase1.gif'
		},{
			url: '',
			name: '来电查询',
			img: '/images/collectionMng/searMyCase.gif'
		},{
			url: '',
			name: '主管协催',
			img: '/images/collectionMng/dirAss.gif'
		}]
	},
	escortMng: {
		url: '/index/subMenu/escortMng.do',
		name: '协催管理',
		sub:[{
			url: '/sys/Role/go2SysRoleLs.do',
			name: '待处理信函',
			img: '/images/escortMng/assMail1.gif'
		},{
			url: '',
			name: '信函记录',
			img: '/images/escortMng/assMail2.gif'
		},{
			url: '',
			name: '待处理协催',
			img: '/images/escortMng/caseAss.gif'
		},{
			url: '',
			name: '协催记录',
			img: '/images/escortMng/caseAss2.gif'
		},{
			url: '',
			name: '待银行对账',
			img: '/images/escortMng/bankCp.gif'
		},{
			url: '',
			name: '还款记录',
			img: '/images/escortMng/bankCp2.gif'
		}]
	},
	visitMng: {
		url: '/index/subMenu/visitMng.do',
		name: '外访管理',
		sub:[{
			url: '/sys/Role/go2SysRoleLs.do',
			name: '待审核外访',
			img: '/images/visitMng/un_vis.gif'
		},{
			url: '',
			name: '待排程外访',
			img: '/images/visitMng/vis1.gif'
		},{
			url: '',
			name: '已排程外访',
			img: '/images/visitMng/vis2.gif'
		},{
			url: '',
			name: '已完成外访',
			img: '/images/visitMng/vis3.gif'
		},{
			url: '',
			name: '我的外访',
			img: '/images/visitMng/my_vis.gif'
		}]
	}
};