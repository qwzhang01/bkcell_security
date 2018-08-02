/**
 * 产品表单校验重写校验方法
 */
$(document).ready(function() {
	$(".form1").Validform({
		postonce : true,
		tiptype : function(msg, o, cssctl) {
			if (!o.obj.is("form")) {
				var objElement = o.obj.parent("td").length > 0 ? o.obj.parent("td"): o.obj.parent();
				var objtip = objElement.find(".Validform_checktip");
				cssctl(objtip, o.type);
				objtip.text(msg);
				var infoObj = objElement.find(".info");
				if (o.type == 2) {
					infoObj.fadeOut(200);
				} else {
					if (infoObj.is(":visible")) {
						return;
					}
					var left = o.obj.position().left, top = o.obj.position().top;
					infoObj.css({
						left : left + 150,
						top : top - 45
					}).show().animate({
						top : top - 35
					}, 200);
				}
			}
		},
		datatype : {
			/*合同模板 MB1501037778979*/
			"contactTemplate":function(gets,obj, curform, datatype) {
				if("" == gets){
					return "请填写合同模板！";
				}
				var reg = /MB[0-9]{13}/;
				if(!reg.test(gets)){
					return "合同编号模板格式有误！";
				}
				return true;			
			}
		}
	});
});