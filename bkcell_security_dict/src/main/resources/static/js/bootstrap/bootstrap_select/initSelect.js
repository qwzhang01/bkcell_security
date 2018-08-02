/**
 * 初始下拉框
 * 
 * @param $select
 * @param options
 */
function initSelect($select, options) {
	var defaults = {
		width : '86%',
		style : 'form-control',
		liveSearch : true,
		actionsBox : true,
		liveSearchNormalize : true
	};
	options = $.extend(defaults, options);
	$select.selectpicker(options);
}