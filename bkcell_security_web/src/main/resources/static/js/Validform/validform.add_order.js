/**
 * 顾问下单表单校验重写校验方法
 */
$(document)
		.ready(
				function() {
					$(".form1")
							.Validform(
									{
										postonce : true,
										tiptype : function(msg, o, cssctl) {
											if (!o.obj.is("form")) {// 验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
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
											"phone" : /(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/,
											"activeCode" : function(gets, obj,
													curform, datatype) {
												var phone = $(
														'input[name="client.PhoneNumber"]')
														.val();
												if ('' == phone) {
													return '请在输入正确的客户手机号以后输入优惠码！';
												}
												var flag = false;
												$
														.ajax({
															url : '/market/client_coupons/validate_active_code',
															type : "POST",
															async : false,
															cache : false,
															dataType : 'json',
															data : {
																phone : phone,
																activeCode : gets,
																date : new Date
															},
															success : function(
																	data) {
																flag = data.flag;
															}
														});
												return flag;
											},
											"seller" : function(gets, obj,
													curform, datatype) {
												if ('' == gets) {
													return '请输入客户顾问！';
												}
												var flag = false;
												$
														.ajax({
															url : '/business/seller/isSeller',
															type : "POST",
															async : false,
															cache : false,
															dataType : 'json',
															data : {
																name : gets,
																date : new Date
															},
															success : function(
																	data) {
																flag = data.flag;
															}
														});
												return flag;
											},
											"idcard" : function(gets, obj,
													curform, datatype) {
												var cardType = $(
														'select[name="client.CardType"]')
														.val();
												if ('' == cardType) {
													return '请先选择证件类型';
												}
												if (1 != cardType) {
													return true;
												}
												var Wi = [ 7, 9, 10, 5, 8, 4,
														2, 1, 6, 3, 7, 9, 10,
														5, 8, 4, 2, 1 ];// 加权因子;
												var ValideCode = [ 1, 0, 10, 9,
														8, 7, 6, 5, 4, 3, 2 ];// 身份证验证位值，10代表X;
												if (gets.length == 15) {
													return isValidityBrithBy15IdCard(gets);
												} else if (gets.length == 18) {
													var a_idCard = gets
															.split("");// 得到身份证数组
													if (isValidityBrithBy18IdCard(gets)
															&& isTrueValidateCodeBy18IdCard(a_idCard)) {
														return true;
													}
													return false;
												}
												return false;

												function isTrueValidateCodeBy18IdCard(
														a_idCard) {
													var sum = 0; // 声明加权求和变量
													if (a_idCard[17]
															.toLowerCase() == 'x') {
														a_idCard[17] = 10;// 将最后位为x的验证码替换为10方便后续操作
													}
													for (var i = 0; i < 17; i++) {
														sum += Wi[i]
																* a_idCard[i];// 加权求和
													}
													valCodePosition = sum % 11;// 得到验证码所位置
													if (a_idCard[17] == ValideCode[valCodePosition]) {
														return true;
													}
													return false;
												}

												function isValidityBrithBy18IdCard(
														idCard18) {
													var year = idCard18
															.substring(6, 10);
													var month = idCard18
															.substring(10, 12);
													var day = idCard18
															.substring(12, 14);
													var temp_date = new Date(
															year,
															parseFloat(month) - 1,
															parseFloat(day));
													// 这里用getFullYear()获取年份，避免千年虫问题
													if (temp_date.getFullYear() != parseFloat(year)
															|| temp_date
																	.getMonth() != parseFloat(month) - 1
															|| temp_date
																	.getDate() != parseFloat(day)) {
														return false;
													}
													return true;
												}

												function isValidityBrithBy15IdCard(
														idCard15) {
													var year = idCard15
															.substring(6, 8);
													var month = idCard15
															.substring(8, 10);
													var day = idCard15
															.substring(10, 12);
													var temp_date = new Date(
															year,
															parseFloat(month) - 1,
															parseFloat(day));
													if (temp_date.getYear() != parseFloat(year)
															|| temp_date
																	.getMonth() != parseFloat(month) - 1
															|| temp_date
																	.getDate() != parseFloat(day)) {
														return false;
													}
													return true;
												}
											}
										}
									});
				});