var uploader;
var cbs = {
    accessid: '',
    accesskey: '',
    host: '',
    policyBase64: '',
    signature: '',
    callbackbody: '',
    filename: '',
    key: '',
    expire: 0,
    g_object_name: '',
    g_object_name_type: '',
    timestamp: Date.parse(new Date()) / 1000
}

function getSignature() {
    var xmlhttp = null;
    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    if (xmlhttp != null) {
        serverUrl = '/oss_signature';
        xmlhttp.open("GET", serverUrl, false);
        xmlhttp.send(null);
        return xmlhttp.responseText
    } else {
        alert("Your browser does not support XMLHTTP.");
    }
};


function check_signature() {
    //可以判断当前expire是否超过了当前时间,如果超过了当前时间,就重新取一下.3s 做为缓冲
    var now = Date.parse(new Date()) / 1000;
    if (cbs.expire < now + 3) {
        var signature = JSON.Parse(getSignature());
        $.extend(cbs, body);
        return true;
    }
    return false;
};


function initUploader(uploaderId, fileBoxId, extensions, multiple) {
    var defaults = ['bmp', 'jpg', 'jpeg', 'png', "gif"];
    var options = extensions || defaults;
    return {
        element: document.getElementById(uploaderId),
        autoUpload: true, // 是否自动上传文件选项
        multiple: true, // 是否允许选择多个文件进行上传
        request: {
            endpoint: , // 上传文件服务器端处理地址，
            params: cbs
        },
        validation: {
            allowedExtensions: options,
            // itemLimit: 4,
            sizeLimit: 1024 * 1024 * 200 // （kb）
        },
        cors: {
            allowXdr: true,
            expected: true
        },
        callbacks: {
            onComplete: function(id, name, response) {
                if (response.success) {
                    // 将上传文件放入文件列表
                    var html = generateFileItem(response.fileName,
                        response.filePath, $("#" + fileBoxId).attr(
                            "filename"));
                    $(".qq-upload-list li").remove();
                    if (multiple == true) {
                        $("#" + fileBoxId).prepend(html);
                    } else {
                        $("#" + fileBoxId).html(html);
                    }
                }
            },
            onSubmit: function(id, name) {
                // 多文件上传时建议使用此方法进行控制上传文件数
                if ($("#" + fileBoxId).children().length >= 10) {
                    alert("最多只允许上传 10个文件。");
                    return false;
                }
                return true;
            }
        }
    };
}

/**
 * 导入数据
 * @param uploaderId
 * @param fileBoxId
 * @param extensions
 * @param multiple
 * @returns {___anonymous1426_2468}
 */
function initImporter(uploaderId, fileBoxId, extensions, multiple) {
    var defaults = ['bmp', 'jpg', 'jpeg', 'png', "gif"];
    var options = extensions || defaults;
    return {
        element: document.getElementById(uploaderId),
        autoUpload: true, // 是否自动上传文件选项
        multiple: true, // 是否允许选择多个文件进行上传
        request: {
            endpoint: servicepath + '/local_upload'
        },
        validation: {
            allowedExtensions: options,
            // itemLimit: 4,
            sizeLimit: 1024 * 1024 * 200
                // （kb）
        },
        cors: {
            allowXdr: true,
            expected: true
        },
        callbacks: {
            onComplete: function(id, name, response) {
                if (response.success) {
                    // 将上传文件放入文件列表
                    var html = generateFileItem(response.fileName,
                        response.filePath, $("#" + fileBoxId).attr(
                            "filename"));
                    $(".qq-upload-list li").remove();
                    if (multiple == true) {
                        $("#" + fileBoxId).prepend(html);
                    } else {
                        $("#" + fileBoxId).html(html);
                    }
                }
            },
            onSubmit: function(id, name) {
                // 多文件上传时建议使用此方法进行控制上传文件数
                if ($("#" + fileBoxId).children().length >= 10) {
                    alert("最多只允许上传 10个文件。");
                    return false;
                }
                return true;
            }
        }
    };
}

// 用于验证图片扩展名的正则表达式
function checkSuffix(str) {
    var strRegex = "(.bmp|.jpg|.png|.gif|.jpeg)$";
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
    var boxHeader = "<div style='margin:5px 0;'>";
    var link;
    if (checkSuffix(path)) {
        link = "<img class='thumbnail' src='" + servicepath + "" + path +
            "'>";
    } else {
        link = "<a href='" + servicepath + path + "'>" + name +
            "</a>";
    }
    var close = "<span style='margin-left:10px;cursor:pointer;' onclick='deletefile(this)'>&times;</span>";
    var hidden = "<input type='hidden' name='" + filename + "' value='" +
        path + "' />";
    var boxFooter = "</div>";
    return boxHeader + link + close + hidden + boxFooter;
}

// 删除文件
function deletefile(obj) {
    $(obj).parent().remove();
    var filepath = $(obj).parent().find("a").attr("href");
    $.get('/upload/delete?filepath=' + filepath);
}