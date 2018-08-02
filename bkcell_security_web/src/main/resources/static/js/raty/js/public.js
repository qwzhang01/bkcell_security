$(document).ready(function() {
	$("#stars").raty({
		hints : [ '很不满意', '不满意', '就这样吧', '满意', '非常满意' ],
		path : '/Scripts/raty/img',
		size : 24,
		target : "#hint",
		targetKeep : true,
		click : function(score, evt) {
			$("#score").val(score);
		}
	});
	$("#ccstars").raty({
		hints : [ '很不满意', '不满意', '就这样吧', '满意', '非常满意' ],
		path : '/Scripts/raty/img',
		size : 24,
		target : "#cchint",
		targetKeep : true,
		click : function(score, evt) {
			$("#ccscore").val(score);
		}
	});
	$(".gstar").raty({
		readOnly : true,
		hints : [ '很不满意', '不满意', '就这样吧', '满意', '非常满意' ],
		path : '/Scripts/raty/img',
		size : 24,
		score : function() {
			return $(this).attr("data-rating");
		}
	});
});
