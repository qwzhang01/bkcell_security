/**
 * 初始化分页列表
 * 
 * @param $table
 * @param options
 */
function initPageList($table, options) {
	var defaults = {
		classes : 'table table-hover table-main',
		cache : false,
		buttonsClass : 'default',
		undefinedText : ' ',
		striped : true,
		method : 'post',
		contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
		dataType : 'json',
		dataField : 'data',
		totalField : 'totalRows',
		queryParamsType : 'limits',
		pagination : true,
		paginationLoop : false,
		sidePagination : 'server',
		totalRows : 0,
		pageNumber : 1,
		pageSize : 10,
		pageList : [ 10, 25, 50, 100 ],
		maintainSelected : false,
		clickToSelect : false,
		cardView : false,
		detailView : false,
		showFooter : false
	};
	options = $.extend(defaults, options);// 需要传入url，搜索参数
	$table.bootstrapTable(options);
}
/**
 * 列表序号方法（服务器端分页列表序号）
 */
function initIndex($table, value, item, index) {
	var ind = $table.bootstrapTable('getOptions').pageNumber;
	var size = $table.bootstrapTable('getOptions').pageSize;
	return (ind - 1) * size + index + 1;
}
/**
 * 默认列表分页序号（服务器端分页列表序号）
 * 
 * @param value
 * @param item
 * @param index
 * @returns
 */
function indexNum(value, item, index) {
	return initIndex($listTabId, value, item, index);
}
/**
 * 客户端分页列表序号
 */
function indexNumForCient(value, item, index) {
	return index + 1;
}

function phoneNumber(value, row, index) {
	return '<a href="#" onclick="send_sms(this)" title="点击发送短信" style="word-break:break-all;text-decoration: none">'
			+ value + '</a>';
}

/**
 * 千分位格式方法
 */
function toThousandFormat(num) {
	return '￥'
			+ (num.toFixed(2) + '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g,
					'$&,');
}
/**
 * 将搜索条件与插件的排序、分页 参数合并
 * 
 * @param $
 */
(function($) {
	$.fn.serializeJson = function(obj) {
		var serializeObj = {};
		var array = this.serializeArray();
		var str = this.serialize();
		$(array).each(
				function() {
					if (serializeObj[this.name]) {
						if ($.isArray(serializeObj[this.name])) {
							serializeObj[this.name].push(this.value);
						} else {
							serializeObj[this.name] = [
									serializeObj[this.name], this.value ];
						}
					} else {
						serializeObj[this.name] = this.value;
					}
				});
		return $.extend(serializeObj, obj);
	};
})(jQuery);