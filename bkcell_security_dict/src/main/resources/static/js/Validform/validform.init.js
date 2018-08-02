/**
 * @author Yl 初始化带上传控件的表单验证
 */
$(function() {
	/** *初始化表单验证** */
	$(".form1").Validform(
			{
				tiptype : function(msg, o, cssctl) {
					var objtip = o.obj.siblings(".Validform_checktip");
					cssctl(objtip, o.type);
					objtip.text(msg);
				},
				ignoreHidden : true,
				// tipSweep : true,
				usePlugin : {},
				callback : function() {
					if (swfuploadhandler.SWFUPLOAD_demo2_0.customSettings.form
							.find("[plugin*='swfupload']").val() === "") {
						swfuploadhandler.SWFUPLOAD_demo2_0.customSettings.form
								.get(0).submit();
					}
					;
					// 针对非ajax表单提交方式，可以在这里触发上传事件，这样可以实现表单验证全部通过才上传文件;
					swfuploadhandler.SWFUPLOAD_demo2_0.startUpload();
					return false;
				}
			});

	/** ***移除logo图片的按钮事件**** */
	$("#removeig").click(function() {
		$("#ig").attr("src", "/App_Themes/Global/images/list/none_img.jpg");
		$("#hidFileID").val("");
		$("#boundarymap").val("");
		$("#removeig").css("display", "none");
	});

});