function getStatusValue(ValueType, ValueIndex) {
    var LODOP = getLodop();
    if (LODOP.CVERSION) LODOP.On_Return = function (TaskID, Value) { printVal = Value; };
    var strResult = LODOP.GET_VALUE(ValueType, ValueIndex);
    if (!LODOP.CVERSION) return strResult; else return "";
};
function MyPrint() {
    if (tNoCode.length <= 5) { return false; }
    $("#msgtrA").show();
    $("#msgtrB").hide();
    $("#alMsgA").html("");
    $("#alMsgB").html("");
    var tMsg = "";
    var printIndex = -1, printName = "", driverName = "", portName = "";
    try {
        var LODOP = getLodop();

        tMsg = lopMsg;
        var iCount = LODOP.GET_PRINTER_COUNT();
        for (var i = 0; i < iCount; i++) {
            printName = LODOP.GET_PRINTER_NAME(i);//获取0号设备的打印机名称。"TSC TDP-245"
            driverName = LODOP.GET_PRINTER_NAME(i + ":DriverName");//获取1号设备的驱动名称。TSC TDP-245
            portName = LODOP.GET_PRINTER_NAME(i + ":PortName");//获取1号设备的端口名称"USB001"
            if (printName.toLowerCase().indexOf("TSC TDP-245".toLowerCase()) >= 0) { printIndex = i; break; }
        }
        /*** 指定要进行打印输出的设备 ***/
        if (printIndex<=-1||!LODOP.SET_PRINTER_INDEX(printIndex)) {
            $("#alMsgA").html("<br><font color='#FF00FF'>请确认打印机是否安装。如果已经安装，请确认打印机名称为（TSC TDP-245）并已<a href='http://xiazai.aibaobg.com/驱动下载/BC-58120TFBC-58180TF.zip' >下载并安装驱动</a></font>");

            //https://www.zebra.com/us/en/support-downloads/printers/desktop/gx430t.html#downloadlistitem_9aa
            return false;
        }

        LODOP.SET_PRINT_STYLEA(0, "Color", "#FF0000");
        LODOP.SET_PRINT_STYLE("Bold", 0);
        LODOP.SET_PRINT_STYLE("Alignment", 2);
        LODOP.SET_PRINT_MODE("CATCH_PRINT_STATUS", true);
        /*** 设定打印纸张为固定纸张或自适应内容高，并设定相关大小值或纸张名及打印方向。 ***/
        LODOP.SET_PRINT_PAGESIZE(1, 300, 150, 0);
        for (var i = 0; i < 9; i++) {
            LODOP.NewPage();
            LODOP.SET_PRINT_STYLE("FontSize", 8);
            LODOP.ADD_PRINT_TEXT("0mm", "0mm", "30mm", "2mm", tBName);
            LODOP.SET_PRINT_STYLE("FontSize", 7);
            LODOP.ADD_PRINT_BARCODE("3mm", "4.2mm", "30mm", "9.6mm", "128A", tNoCode);
        }
        LODOP.PRINT();
        $("#msgtrA").hide();
        $("#msgtrB").show();
        $("#alMsgA").html("");
        $("#alMsgB").html("打印中…………");
        var timer = window.setInterval(function () {
            window.clearInterval(timer); printDialog.close().remove();
        },2000);
    }
    catch (e) {
        if (lopMsg.length <= 1) { lopMsg = "打印插件异常！请检查打印插件安装是否正确"; }
        $("#alMsgA").html(lopMsg); return false;
    }
}

//从JOB代码找出打印机序号：
function GetPrinterIDfromJOBID(strJOBID) {
    var intPos = strJOBID.indexOf("_");
    if (intPos < 0) { return strJOBID; } else { return strJOBID.substr(0, intPos); }
}

//打印状态输出
function getStatuMessage(statusID) {
    var messages = "";
    if (statusID & 1) messages += "已暂停 -";
    if (statusID & 2) messages += "错误 -";
    if (statusID & 4) messages += "正删除 -";
    if (statusID & 8) messages += "进入队列 -";
    if (statusID & 16) messages += "正在打印 -";
    if (statusID & 32) messages += "脱机 -";
    if (statusID & 64) messages += "缺纸 -";
    if (statusID & 128) messages += "打印结束 -";
    if (statusID & 256) messages += "已删除 -";
    if (statusID & 512) messages += "堵了 -";
    if (statusID & 1024) messages += "用户介入 -";
    if (statusID & 2048) messages += "正在重新启动 -";
    return messages;
}