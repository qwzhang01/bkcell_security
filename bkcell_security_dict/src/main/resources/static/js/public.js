/**
 * Created by Administrator on 2015/10/19.
 */
$(function() {
	$.fn.serializeObject = function() {
		var o = {};
		var a = this.serializeArray();
		$.each(a, function() {
			if (o[this.name] !== undefined) {
				if (!o[this.name].push) {
					o[this.name] = [ o[this.name] ];
				}
				o[this.name].push(this.value || '');
			} else {
				o[this.name] = this.value || '';
			}
		});
		return o;
	};
	if (typeof String.prototype.trim !== 'function') {
		String.prototype.trim = function() {
			return this.replace(/^\s+|\s+$/g, '');
		}
	}
	if (!Array.prototype.indexOf) {
		Array.prototype.indexOf = function(obj, fromIndex) {
			if (fromIndex == null) {
				fromIndex = 0;
			} else if (fromIndex < 0) {
				fromIndex = Math.max(0, this.length + fromIndex);
			}
			for (var i = fromIndex, j = this.length; i < j; i++) {
				if (this[i] === obj)
					return i;
			}
			return -1;
		};
	}
	$("#menu-box li a").click(function(e) {
		var ul = $(this).closest("ul");
		if (!ul.hasClass("sub-menu")) {
			var $li = $(this).closest("li");
			var link_status = $li.hasClass("current");
			ul.find("li").removeClass("current");
			if (!link_status) {
				$li.addClass("current");
			}
		}
	});
});

function formateParams(paramnames, json) {
	for ( var p in json) { // 遍历属性,将非数组属性变为数组
		if (json.hasOwnProperty(p) && $.inArray(p, paramnames) == -1) {
			if (Object.prototype.toString.apply(json[p]) != "[object Array]") {
				var temp = [];
				temp.push(json[p]);
				json[p] = temp;
			}
		}
	}
	return json;
}

// 绑定enter键行为
function bindEnterKey(elementId) {
	$(document).off("keydown").on("keydown", function(e) {
		e = e || event;
		if (e.keyCode == 13) {
			$(elementId).click();
		}
	});
}
function layoutResize() {
	var h = $(window).height();
	var w = $(window).width();
	$("#menu-box").height(h - 50); // height of the left menu
	$(".collapse").height(h - 50); // height of the left menu
	$("#content-box").height(h - 50);
	$(".panel-default").height(h - 115);
}

function jumpTo(url) {
	var isIE = !-[ 1, ];
	if (isIE) {
		var link = document.createElement("a");
		link.href = url;
		link.style.display = 'none';
		document.body.appendChild(link);
		link.click();
	} else {
		window.location.href = url;
	}
}

// 多行文本框内容格式转换
function textareaHTML(str) {
	return ((str.replace(/<(.+?)>/gi, "&lt;$1&gt;")).replace(/ /gi, "&nbsp;"))
			.replace(/\n/gi, "<br>");
}

function changepw(obj) {
	artdialog.addOrEdit({
		url : '/changepw',
		title : "修改密码",
		width : 600,
		height : 260
	});
	return false;
}

Date.prototype.pattern = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, // 小时
		"H+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	var week = {
		"0" : "/u65e5",
		"1" : "/u4e00",
		"2" : "/u4e8c",
		"3" : "/u4e09",
		"4" : "/u56db",
		"5" : "/u4e94",
		"6" : "/u516d"
	};
	if (/(y+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}
	if (/(E+)/.test(fmt)) {
		fmt = fmt
				.replace(
						RegExp.$1,
						((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "/u661f/u671f"
								: "/u5468")
								: "")
								+ week[this.getDay() + ""]);
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
		}
	}
	return fmt;
}