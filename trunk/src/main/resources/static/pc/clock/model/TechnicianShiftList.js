var myPage;
var self;
var query = {};
query.pageNo=1;
query.pageSize = 20;
if(getQueryString('page')!=undefined){
	query.pageNo=getQueryString('page');
}

function Node(obj) {
	this.shiftId = ko.observable(obj.shiftId); 
	this.shiftName = ko.observable(obj.shiftName); 
	this.inTime = ko.observable(obj.inTime); 
	this.outTime = ko.observable(obj.outTime); 
	this.status = ko.observable(obj.status); 
	this.createdBy = ko.observable(obj.createdBy); 
	this.createdDate = ko.observable(obj.createdDate); 
	this.updatedBy = ko.observable(obj.updatedBy); 
	this.updatedDate = ko.observable(obj.updatedDate); 
}

function doQueryActionSuccess(data){
	var mappedTasks = $.map(data.data, function(item) { return new Node(item) });  
	if(self){self.technicianShiftList(mappedTasks);}
	myPage = data.page;
	bindPage();
	    
	$("table tbody td .tomodify").bind(function(){
		ChangeUrl('./clock/TechnicianShift.html?action=Edit&id='+$(this).attr('data'));
	});
}

function reloadDate(data){
	myAjax("/technicianshifts", "GET", query, doQueryActionSuccess, true);
}

//定义ViewModel对象
var TechnicianShiftViewModel = function () {  
	self=this;
    //添加动态监视数组对象
    self.technicianShiftList = ko.observableArray([]);
    	
    //初始化数据
    reloadDate(null);
	
	//搜索
	self.search = function(obj) {
		query.pageNo=1;
		query.pageSize = 20;
		qeury.keyWord = $("txtKeywords").val(); //查询参数格式
		myAjax("/technicianshifts", "GET", query, doQueryActionSuccess, true);
    };
    
    //新增
    self.add = function(obj) {
    	ChangeUrl('./clock/TechnicianShift.html?action=Add');
    };
    
    //修改
    self.modify=function(obj){
    	ChangeUrl('./clock/TechnicianShift.html?action=Edit&id='+obj.shiftId());
    };
    
    //删除
    self.delete=function(obj){
    	parent.dialog({
            title: '提示',
            content: '确定要删除该记录！',
            okValue: '确定',
            ok: function () {
		    	var id = $(event.currentTarget).attr('data');
		    	myAjax("/technicianshift/"+id, "DELETE", null, reloadDate, false);
			}
        }).showModal();
    }
    
    //批量删除
    self.deletes = function(obj) {
    	if ($(".checkall input:checked").size() < 1) {
        	parent.dialog({
	            title: '提示',
	            content: '对不起，请选中您要操作的记录！',
	            okValue: '确定',
	            ok: function () { }
	        }).showModal();
	        return false;
	    }
	    var msg = "删除记录后不可恢复，您确定吗？";
	    parent.dialog({
	        title: '提示',
	        content: msg,
	        okValue: '确定',
	        ok: function () {
	        	$(".checkall input:checked").each(function(i){
	        		myAjax("/technicianshift/"+id, "DELETE", null, null, false);
	        	});
	        	location.reload();
	        },
	        cancelValue: '取消',
	        cancel: function () { }
	    }).showModal();
    };

};

$().ready(function(){
    ko.applyBindings(new TechnicianShiftViewModel());
});

var bindPage =function(){
	//分页控件加载处理
    $.jqPaginator('#pagination', {
        totalPages: myPage.totalPages,
        visiblePages: myPage.limit,
        currentPage: myPage.page,
        onPageChange: function (num, type) {
        	query.pageNo=num;
        	reloadDate(null);
//            if (type != 'init') {
//            	ChangeUrl('./clock/TechnicianShiftList.html?page=' + num);
//            }
        }
    });
}

