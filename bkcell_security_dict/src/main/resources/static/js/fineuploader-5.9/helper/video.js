function initVideoUploader(uploaderId, fileBoxId, extensions, multiple) {
	var element=document.getElementById(uploaderId);
	if(!element){
		return;
	}
	var defaults = [ 'mp4',"ogg"];
	var options = extensions || defaults;
	return {
		element : element,
		autoUpload : true, // 是否自动上传文件选项
		multiple : false, // 是否允许选择多个文件进行上传
		request : {
			endpoint :'/upload/fine_upload?folder='+folder// 上传文件服务器端处理地址，
		},
		validation : {
			allowedExtensions : options,
			// itemLimit: 4,
			sizeLimit : 1024 * 1024 * 500
		// （kb）
		},
		cors : {
			allowXdr : true,
			expected : true
		},
		callbacks : {
			onComplete : function(id, name, response) {
				if (response.success) {
					// 将上传文件放入文件列表
					var html = generateFileItem(response.fileName,
							response.filePath, $("#" + fileBoxId).attr(
									"filename"));
					$("#videoThumbnail .Validform_checktip").removeClass("Validform_wrong").text("");
					$("#"+uploaderId).find(".qq-upload-success").remove();
					if (multiple == true) {
						$("#" + fileBoxId).prepend(html);
					} else {
						$("#" + fileBoxId).html(html);
					}
				}
			},
			onSubmit : function(id, name) {
				// 多文件上传时建议使用此方法进行控制上传文件数
				if ($("#" + fileBoxId).children().length >= 20) {
					alert("最多只允许上传 20个文件。");
					return false;
				}
				return true;
			}
		}
	};
}

// 用于验证视频扩展名的正则表达式
function checkSuffix(str) {
	var strRegex = "(.mp4|.ogg)$";
	var re = new RegExp(strRegex);
	if (re.test(str.toLowerCase())) {
		return true;
	} else {
		return false;
	}
}
// 生成上传文件链接子项
// 在提交表单时，上传文件信息会以数组（FileInfo['path1','path2']）的形式提交表单
function generateFileItem(name, path, filename) {
	var boxHeader = "<div style='margin:5px 0;position:relative;'>";
	var link;
	if (checkSuffix(path)) {
		link = "<video controls style='height:10em;' src='" + servicepath + "" + path + "'</video>";
	} else {
		link = "<a href='" + servicepath + path + "'>" + name + "</a>";
	}
	var close = "<span class='thumbnail-delete' onclick='deletefile(this)'>&times;</span>";
	var hidden = "<input type='hidden' name='" + filename + "' value='"
			+ path + "' />";
	var boxFooter = "</div>";
	return boxHeader + link + close + hidden + boxFooter;
}

// 删除文件
function deletefile(obj) {
	$(obj).parent().remove();
	var filepath = $(obj).parent().find("a").attr("href");
	$.get('/upload/delete?filepath=' + filepath);
}
