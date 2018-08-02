/**
 * 初始化日期插件（默认激活情况）
 */
function initDateTimePicker(widget, options) {
	var defaults = {
		format : "YYYY-MM-DD",
		minDate : '1940-01-01',
		maxDate : '2050-12-30',
		useCurrent : true,
		dayViewHeaderFormat : "YYYY MMMM",
		locale : 'zh-CN',
		stepping : 5
	};
	options = $.extend(defaults, options);
	$('.' + widget).datetimepicker(options);
}

/**
 * 对于前后两个的时间段，设置前面的是时间不大于后面的时间，后面的时间不小于前面的时间
 * 
 * @param $widget
 * @param options
 */
function initTwoDateTimePicker($widget, options) {
	var defaults = {
		format : "YYYY-MM-DD",
		minDate : '1940-01-01',
		maxDate : '2050-12-30',
		useCurrent : true,
		locale : 'zh-CN',
		stepping : 5
	};
	options = $.extend(defaults, options);
	var start = $widget.find("input").get(0);
	var end = $widget.find("input").get(1);
	$(start).datetimepicker(options);
	options = $.extend(options, {
		useCurrent : false
	});
	$(end).datetimepicker(options);
	$(start).on("dp.change", function(e) {
		$(end).data("DateTimePicker").minDate(e.date);
	});
	$(end).on("dp.change", function(e) {
		$(start).data("DateTimePicker").maxDate(e.date);
	});
}