var noSelect_html = '<option value="">请选择</option>';
$(function() {
	$("#parentId").click(
			function() {
				var value = $(this).val();
				if (value == "") {
					$("#groupId").empty().append(noSelect_html);
					return;
				}
				linkage('/statistics/market/getgroupsbyparentid?parentId='
						+ value, '#groupId', "#groupTpl", "#groupId");
			});
	if ($("#parentId").val() != "") {
		$("#parentId").click();
	}
});

// 联动控件
function linkage(url, targetId, templateId, containerId) {
	// url:请求地址，targetId：目标控件id，templateId:模板id，containerId:目标控件容器id
	$.ajax({
		type : "GET",
		url : url,
		dataType : "json",
		success : function(data) {
			if (data.length == 0) {
				$(targetId).empty().append(noSelect_html);
			} else {
				var template = _.template($(templateId).html());
				var html = template({
					data : data
				});
				$(targetId).empty().append(html);
			}
		}
	});
}