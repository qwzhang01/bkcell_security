/**
 * 实验室接收表单校验重新校验方法
 */
$(document).ready(function() {
	$(".form1").Validform({
		postonce : true,
		tiptype : function(msg, o, cssctl) {
			if (!o.obj.is("form")) {
				var objElement = o.obj
						.parent("td").length > 0 ? o.obj
						.parent("td")
						: o.obj.parent();
				var objtip = objElement
						.find(".Validform_checktip");
				cssctl(objtip, o.type);
				objtip.text(msg);

				var infoObj = objElement
						.find(".info");
				if (o.type == 2) {
					infoObj.fadeOut(200);
				} else {
					if (infoObj.is(":visible")) {
						return;
					}
					// 错误提示位置
					var left = o.obj.position().left, top = o.obj
							.position().top;
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
			"receivecode" : function(gets, obj,curform, datatype) {
				var receive = $('select[name="receive.Receiver"]').val();
				if ('' == receive) {
					return '请在选择接收人员后输入其校验码！';
				}
				if('' == gets){
					return '请在输入接收人员校验码！';
				}
				var flag = false;
				$.ajax({
					url : '/sample/authenticate_receive_code',
					type : "POST",
					async : false,
					cache : false,
					dataType : 'json',
					data : {
						receive : receive,
						validateCode : gets,
						date : new Date
					},
					success : function(
							data) {
						flag = data.flag;
					}
				});
				return flag;
			},
			"qacode" : function(gets, obj,
					curform, datatype) {
				var qa = $(
						'select[name="receive.QA"]')
						.val();
				if ('' == qa) {
					return '请在QA后输入其校验码！';
				}
				if('' == gets){
					return '请在输入QA校验码！';
				}
				var flag = false;
				$.ajax({
					url : '/sample/authenticate_qa_code',
					type : "POST",
					async : false,
					cache : false,
					dataType : 'json',
					data : {
						qa : qa,
						validateCode : gets,
						date : new Date
					},
					success : function(
							data) {
						flag = data.flag;
					}
				});
				return flag;
			},
			"sampleAdd":function(gets, obj,curform, datatype){
				var flag = $('input[name="isError"]:checked').val();
				if('good' == flag){
					return true;
				}else{
					var flag = false;
					$(".jsSampleAdd").find('input:checked').each(function(){
						var selfValue = $(this).val();
						if(1 == selfValue){
							flag =  true;
							return false;
						}
					});
					if(flag){
						return true;
					}else{
						return '请选择异常样本需要补采的项';
					}
				}
			},
			"sampleAdd1":function(gets, obj,curform, datatype){
				var flag = false;
				if (1 == $('select[name="deviate.HandMethod"]').val()) {
					$(".jsSampleAdd").find('input:checked').each(function(){
						var selfValue = $(this).val();
						if(1 == selfValue){
							flag =  true;
							return false;
						}
					});
				} else {
					flag = true;
				}
				if(flag){
					return true;
				}else{
					return '请选择异常样本需要补采的项';
				}
			}
		}
	});
});