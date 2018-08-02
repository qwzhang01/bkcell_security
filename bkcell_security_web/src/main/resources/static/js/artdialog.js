/**
 * Created by YL on 14-6-26.
 */

var artdialog = (function($) {

	// 这里配置默认值
	var defaults = {
		width : undefined,
		height : undefined,
		time : 2000, // 弹出层自动关闭延时，只对msg有用
		zIndex : 8000, // 层的zIndex ，配置该值使其能够任意置于其他层的上方或者下方
		cancel : false, // 是否显示右上角的关闭按钮
		submitId : "submit", // 表单提交按钮Id
		formId : "form1", // 点击确定按钮时，提交哪个表单
		url : "", // 表单提交的url,或者加载页面的url
		title : "提示信息", // 弹出层的title
		content : "" // 弹出层的内容
	};

	return {

		// 消息框
		msg : function(options) {
			var setting = $.extend({}, defaults, options);
			var d = dialog({
				title : setting.title,
				content : setting.content,
				zIndex : setting.zIndex,
				width : setting.width,
				height : setting.height,
				cancel : setting.cancel,
				onremove : setting.onremove,
			});
			d.showModal();
			setTimeout(function() {
				d.close().remove();
			}, setting.time);
		},

		// 确认框
		confirm : function(options) {
			var setting = $.extend({}, defaults, options);
			var d = dialog({
				title : setting.title,
				content : setting.content,
				width : setting.width,
				height : setting.height,
				zIndex : setting.zIndex,
				okValue : "确定",
				ok : function() {
					$("#" + setting.formId).attr("action", setting.url)
							.submit();
				},
				cancelValue : "取消",
				cancel : function() {
					d.close().remove();
				}
			});
			d.showModal();
		},
		// 确认框
		confirmWithFn : function(options) {
			var setting = $.extend({}, defaults, options);
			var d = dialog({
				title : setting.title,
				content : setting.content,
				width : setting.width,
				height : setting.height,
				zIndex : setting.zIndex,
				okValue : "确定",
				ok : function() {
					d.close().remove();
					options.callFn();
				},
				cancelValue : "取消",
				cancel : function() {
					d.close().remove();
				}
			});
			d.showModal();
		},
		// 确认框2
		confirmLink : function(options) {
			var setting = $.extend({}, defaults, options);
			var d = dialog({
				title : setting.title,
				content : setting.content,
				width : setting.width,
				height : setting.height,
				zIndex : setting.zIndex,
				okValue : "确定",
				ok : function() {
					window.top.location.href = setting.url;
				},
				cancelValue : "取消",
				cancel : function() {
					d.close().remove();
				}
			});
			d.showModal();
		},

		// 确认框3
		confirmMoreBtn : function(options) {
			var setting = $.extend({}, defaults, options);
			var d = dialog({
				title : setting.title,
				content : setting.content,
				width : setting.width,
				height : setting.height,
				zIndex : setting.zIndex,
				cancelValue : "取消",
				button : [ {
					value : setting.btnName,
					callback : function() {
						window.top.location.href = setting.url1;
					},
					autofocus : true
				}, {
					value : "确定",
					callback : function() {
						window.top.location.href = setting.url;
					},
					autofocus : true
				} ],
				cancel : function() {
					d.close().remove();
				}
			});
			d.showModal();
		},

		// 用于新增和编辑
		addOrEdit : function(options) {
			var setting = $.extend({}, defaults, options);
			var content = $.ajax({
				type : 'GET',
				url : setting.url,
				async : false
			}).responseText;
			var d = dialog({
				title : setting.title,
				content : content,
				zIndex : setting.zIndex,
				width : setting.width,
				height : setting.height,
				button : [ {
					value : "取消"
				}, {
					value : "确定",
					autofocus : true,
					callback : function() {
						$("#" + setting.submitId).click();
						return false;
					}
				} ]
			});
			d.showModal();
		},

		// 用于新增和编辑
		addOrEditNOCancel : function(options) {
			var setting = $.extend({}, defaults, options);
			var content = $.ajax({
				type : 'GET',
				url : setting.url,
				async : false
			}).responseText;
			var d = dialog({
				title : setting.title,
				content : content,
				zIndex : setting.zIndex,
				width : setting.width,
				height : setting.height,
				cancel: setting.cancel,
				button : [ {
					value : "确定",
					autofocus : true,
					callback : function() {
						$("#" + setting.submitId).click();
						return false;
					}
				} ]
			});
			d.showModal();
		},
		// 用于新增和编辑,传入参数添加function
		addOrEdit1 : function(options) {
			var setting = $.extend({}, defaults, options);
			var content = $.ajax({
				type : 'GET',
				url : setting.url,
				async : false
			}).responseText;
			var d = dialog({
				title : setting.title,
				content : content,
				zIndex : setting.zIndex,
				width : setting.width,
				height : setting.height,
				cancel: setting.cancel,
				button : [ {
					value : "取消"
					},{
					value : "确定",
					autofocus : true,
					callback : setting.fn
				} ]
			});
			d.showModal();
		},
		// 打印
		print : function(options) {
			var setting = $.extend({}, defaults, options);
			var content = $.ajax({
				type : 'GET',
				url : setting.url,
				async : false
			}).responseText;
			var d = dialog({
				title : setting.title,
				content : content,
				zIndex : setting.zIndex,
				width : setting.width,
				height : setting.height,
				button : [ {
					value : "打印",
					autofocus : true,
					callback : function() {
						$("#" + setting.submitId).click();
						return false;
					}
				}, {
					value : "取消"
				} ]
			});
			d.showModal();
			return d;
		},

		// 用于多按钮操作
		operateMoreBtn : function(options) {
			var setting = $.extend({}, defaults, options);
			var content = $.ajax({
				type : 'GET',
				url : setting.url,
				async : false
			}).responseText;
			var d = dialog({
				title : setting.title,
				content : content,
				zIndex : setting.zIndex,
				width : setting.width,
				height : setting.height,
				button : [ {
					value : "取消"
				}, {
					value : setting.btnName,
					callback : function() {
						$("#" + setting.hiddenId).val(setting.hiddenValue);
						$("#" + setting.submitId).click();
						return false;
					},
					autofocus : true
				}, {
					value : "确定",
					autofocus : true,
					callback : function() {
						$("#" + setting.submitId).click();
						return false;
					}
				} ]
			});
			d.showModal();
		},

		// 查看
		view : function(options) {
			var setting = $.extend({}, defaults, options);
			var content = $.ajax({
				type : 'GET',
				url : setting.url,
				async : false
			}).responseText;
			var d = dialog({
				title : setting.title,
				content : content,
				zIndex : setting.zIndex,
				width : setting.width,
				height : setting.height
			});
			d.showModal();
		},

		// 弹出层中加载一个iframe
		openIframe : function(options) {
			var setting = $.extend({}, defaults, options);
			var content = "<iframe src='" + setting.url + "' width='"
					+ setting.width + "px' frameborder='0' height='"
					+ setting.height + "px'></iframe>";
			var d = dialog({
				id : "iframe1",
				title : setting.title,
				content : content,
				zIndex : setting.zIndex
			});
			d.showModal();
			return d;
		}
	}
})(jQuery);
