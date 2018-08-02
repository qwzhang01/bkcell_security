/**
 * 用于高级查询的js on 2014-3-22 by 陈灿
 */
$(function() {
	$("#firstType").change(function() {
		if ($(this).val() == "1") {
			$("#type3").addClass("hide");
		} else {
			$("#type3").removeClass("hide");
		}
		$("#hidType").val("");
		nextType(1);
	});

	if ($("#firstType").val() != "") {
		$("#firstType").change();
	}
})
// 类别选择
function typeChange(obj) {
	var id = obj.id;
	var levels = parseInt(id.replace("type", ""));
	var type = $(obj).val();
	if (type != "") {
		$("#hidType").val(type);
		if (levels < 3) {
			++levels;
			nextType(levels);
		}
	} else {
		var etype = $("#type" + (levels - 1)).val();
		if (levels > 1 && etype != "") {
			$("#hidType").val(etype);

		} else {
			$("#hidType").val("");
			$("#hidTypeCode").val("");
		}
		var html = '<option value="">请选择</option>';
		while (levels < 3) {
			++levels;
			$("#type" + levels).empty().append(html);
		}
	}
}

// 获取下一级类别
function nextType(levels) {
	var html = '<option value="">请选择</option>';
	if ($("#firstType").val().trim() == "") {
		$("#type" + levels).empty().append(html);
		if (levels < 3) {
			nextType(++levels);
		}
		return;
	}
	$
			.ajax({
				type : "POST",
				contentType : "application/json; charset=utf-8",
				url : '/complaint/gettypelist?' + "parentId="
						+ $("#hidType").val() + "&type="
						+ $("#firstType").val(),
				data : "{}",
				dataType : 'json',
				complete : function(obj) {
					if (obj.status == 200 && obj.readyState == 4
							&& obj.responseText != "error") {
						if (obj.responseText != "" && obj.responseText != null) {
							var list = eval(obj.responseText);
							for (var i = 0; i < list.length; i++) {
								if ($("#hidType" + levels).val() == list[i].ComplaintTypeId) {
									html += '<option value="'
											+ list[i].ComplaintTypeId
											+ '" selected="selected">'
											+ list[i].Name + '</option>';
								} else {
									html += '<option value="'
											+ list[i].ComplaintTypeId + '">'
											+ list[i].Name + '</option>';
								}
							}
							$("#type" + levels).empty().append(html);
							if (levels < 3
									&& $("#hidType" + levels).val() != "") {
								typeChange(document.getElementById("type"
										+ levels));
							}
						}
					} else {
						artdialog.msg({
							title : "提示信息",
							width : 200,
							height : 50,
							content : "加载失败！"
						});
					}
				}
			});
}