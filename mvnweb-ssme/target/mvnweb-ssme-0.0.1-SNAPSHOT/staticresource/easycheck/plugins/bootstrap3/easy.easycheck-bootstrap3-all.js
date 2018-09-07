/**
 * jQuery EasyCheck Bootstrap3 Plugin all in one file
 * 
 * Version 5.2.0
 * 
 * http://easyproject.cn
 * https://github.com/ushelp/EasyCheck
 * 
 * Author: Ray [ inthinkcolor@gmail.com ]
 * Since: 2011
 * 
 * Dependencies: jQuery
 * 
 */

(function(window) {
	var addChkForm = function(chkrule, fromChkInfo) {
			var chkElements = EasyCheck.getMatches(chkrule.chkName);
			var promiseArray = new Array();
			$(fromChkInfo.eleArea + chkElements).each(function(index, element) {
				var divStuf = $(element).attr("id") || $(element).attr("name");
				if(!fromChkInfo.errorEleArray[divStuf]) {
					var flag = chkrule.chkFunction(element);
					if(window.Promise && flag instanceof Promise) {
						var p = flag.then(function(data) {
							if(!data) {
								fromChkInfo.errorEleArray[divStuf] = "E";
								fromChkInfo.chkFlag = false;
							}
						}, function() {
							fromChkInfo.errorEleArray[divStuf] = "E";
							fromChkInfo.chkFlag = false;
						})
						promiseArray.push(p);
					} else {
						if(!flag) {
							fromChkInfo.errorEleArray[divStuf] = "E";
							fromChkInfo.chkFlag = false;
						}
					}

				} else {
					fromChkInfo.chkFlag = false;
				}
			});
			return promiseArray;
		},
		checkVc = function(o, e) {
			return EasyCheck.chkDef.addChkMethod("[vc]", o, e, function(o) {
				var val = $(o).val();
				if(window.Promise) {
					// 如果支持 Promise
					var p = new Promise(function(resolve, reject) {
						$.ajax({
							url: $(o).attr("vc"),
							data: $(o).attr("name") + "=" + val + "&n=" + new Date(),
							type: "POST",
							success: function(data) {
								var d=data;
		      					if(d){
		      						d=d+"";
		      					}else{
		      						d="false";
		      					}
		      					var res=d.replace(/\r\n/g, "");
		      					if(res!="true" || res!="false"){
		      						console.error("Captcha validation result must is 'true' or 'false', but your is: ",data);
		      					}
		      					resolve(d=="true");
							},
							error: function() {
								reject(false);
							},
							xhrFields: {
								withCredentials: EasyCheck.withCredentials //跨域认证
							}
						});
					});
					return p;
				} else {
					var res = false;
					$.ajaxSetup({
						async: false
					});
					$.post($(o).attr("vc"), $(o).attr("name") + "=" + val + "&n=" + new Date(), function(d) {
						res = d.replace(/\r\n/g, "");
					}, "text");
					return res == "true";
				}

			}, EasyCheck.msg["vc"]);
		},
		checkRegExp = function(o, e) {
			return EasyCheck.chkDef.addChkMethod("[reg]", o, e, function(o) {
				var val = $(o).val();
				var reg = new RegExp("^(?:" + $(o).attr("reg") + ")$");
				return !($.trim(val) != "" && !reg.test(val));
			}, EasyCheck.msg["regexp"]);
		},
		checkExtension = function(o, e) {
			return EasyCheck.chkDef.addChkMethod("[extension]", o, e, function(o) {
				var ex = $(o).attr("extension");
				var val = $(o).val();
				var extensionList = ex != "" ? ex.replace(/,/g, "|") : "png|jpe?g|gif";
				return !($.trim(val) != "" && !val.match(new RegExp(".(" + extensionList + ")$", "i")));
			}, EasyCheck.formatMsg(EasyCheck.msg["extension"], $(o).attr("extension")));
		},
		checkRequired = function(o, e) {
			return EasyCheck.chkDef.addChkMethod(".required", o, e, function(o) {
				var val = $(o).val();
				return $.trim(val) != "";
			}, EasyCheck.msg["required"]);
		},
		checkEmail = function(o, e) {
			return EasyCheck.chkDef.addChkMethod(".email", o, e, function(o) {
				var val = $(o).val();
				return !($.trim(val) != "" && !EasyCheck.validator.email.test(val));
			}, EasyCheck.msg["email"]);
		},
		checkUrl = function(o, e) {
			return EasyCheck.chkDef.addChkMethod(".url", o, e, function(o) {
				var val = $(o).val();
				return !($.trim(val) != "" && !EasyCheck.validator.url.test(val));
			}, EasyCheck.msg["url"]);
		},
		checkNumber = function(o, e) {
			return EasyCheck.chkDef.addChkMethod(".number", o, e, function(o) {
				var val = $(o).val();
				return !($.trim(val) != "" && !EasyCheck.validator.number.test(val));
			}, EasyCheck.msg["number"]);
		},
		checkInteger = function(o, e) {
			return EasyCheck.chkDef.addChkMethod(".integer", o, e, function(o) {
				var val = $(o).val();
				return !($.trim(val) != "" && !EasyCheck.validator.integer.test(val));
			}, EasyCheck.msg["integer"]);
		},
		checkEqualto = function(o, e) {
			return EasyCheck.chkDef.addChkMethod("[equalto]", o, e, function(o) {
				var val = $(o).val();
				return val == $("[id='" + $(o).attr("equalto") + "']").val();
			}, EasyCheck.msg["equalto"]);
		},
		checkEquallength = function(o, e) {
			return EasyCheck.chkDef.addChkMethod("[equallength]", o, e, function(o) {
				var val = $(o).val();
				return !(val.length != $(o).attr("equallength"));
			}, EasyCheck.formatMsg(EasyCheck.msg["equallength"], $(o).attr("equallength")));
		},
		checkRangeLength = function(o, e) {
			return EasyCheck.chkDef.addChkMethod("[minlength][maxlength]", o, e, function(o) {
				var val = $(o).val();
				return !($.trim(val).length < $(o).attr("minlength") || $.trim(val).length > $(o).attr("maxlength"));
			}, EasyCheck.formatMsg(EasyCheck.msg["lengthrange"], $(o).attr("minlength"), $(o).attr("maxlength")));
		},
		checkMinlength = function(o, e) {
			return EasyCheck.chkDef.addChkMethod("[minlength]", o, e, function(o) {
				var val = $(o).val();
				return !($.trim(val).length < $(o).attr("minlength"));
			}, EasyCheck.formatMsg(EasyCheck.msg["minlength"], $(o).attr("minlength")));
		},
		checkMaxlength = function(o, e) {
			return EasyCheck.chkDef.addChkMethod("[maxlength]", o, e, function(o) {
				var val = $(o).val();
				return !($.trim(val).length > $(o).attr("maxlength"));
			}, EasyCheck.formatMsg(EasyCheck.msg["maxlength"], $(o).attr("maxlength")));
		},
		checkRange = function(o, e) {
			return EasyCheck.chkDef.addChkMethod("[min][max]", o, e, function(o) {
				var val = $(o).val();
				return !(parseFloat($.trim(val)) < parseFloat($(o).attr("min")) || parseFloat($.trim(val)) > parseFloat($(o).attr("max")) || isNaN(val));
			}, EasyCheck.formatMsg(EasyCheck.msg["numberrange"], $(o).attr("min"), $(o).attr("max")));
		},
		checkMin = function(o, e) {
			return EasyCheck.chkDef.addChkMethod("[min]", o, e, function(o) {
				var val = $(o).val();
				return !(parseFloat($.trim(val)) < parseFloat($(o).attr("min")) || isNaN(val));
			}, EasyCheck.formatMsg(EasyCheck.msg["min"], $(o).attr("min")));
		},
		checkMax = function(o, e) {
			return EasyCheck.chkDef.addChkMethod("[max]", o, e, function(o) {
				var val = $(o).val();
				return !(parseFloat($.trim(val)) > parseFloat($(o).attr("max")) || isNaN(val));
			}, EasyCheck.formatMsg(EasyCheck.msg["max"], $(o).attr("max")));
		};

	var EasyCheck = {
		chkList: "",
		msg: {},
		msgs: {},
		msgMark: "{0}",
		errorMsg: "{0}",
		correctMsg: "{0}",
		defMsg: "{0}",
		errorMsgs: {},
		correctMsgs: {},
		defMsgs: {},
		cacheCorrectMsg: {},
		cacheDefMsg: {},
		cacheErrorMsg: {},
		// 表单元素获得焦点时是否还原为默认提示
		resetOnFocus: false,
		// 验证码跨域认证
		withCredentials: true,
		validator: {
			email: /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
			url: /^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i,
			number: /^-?(?:\d+|\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$/,
			integer: /^-?^\d+$/,
			English: /^[A-Za-z]+$/,
			Chinese: /^[\u0391-\uFFE5]+$/,
			Zip: /^[1-9]\d{5}$/,
			Currency: /^\d+(\.\d+)?$/,
			Require: /.+/,
			ipv4: /^(25[0-5]|2[0-4]\d|[01]?\d\d?)\.(25[0-5]|2[0-4]\d|[01]?\d\d?)\.(25[0-5]|2[0-4]\d|[01]?\d\d?)\.(25[0-5]|2[0-4]\d|[01]?\d\d?)$/i,
			ipv6: /^((([0-9A-Fa-f]{1,4}:){7}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){6}:[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){5}:([0-9A-Fa-f]{1,4}:)?[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){4}:([0-9A-Fa-f]{1,4}:){0,2}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){3}:([0-9A-Fa-f]{1,4}:){0,3}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){2}:([0-9A-Fa-f]{1,4}:){0,4}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){6}((\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b)\.){3}(\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b))|(([0-9A-Fa-f]{1,4}:){0,5}:((\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b)\.){3}(\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b))|(::([0-9A-Fa-f]{1,4}:){0,5}((\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b)\.){3}(\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b))|([0-9A-Fa-f]{1,4}::([0-9A-Fa-f]{1,4}:){0,5}[0-9A-Fa-f]{1,4})|(::([0-9A-Fa-f]{1,4}:){0,6}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){1,7}:))$/
		},
		easyCheckIgnore: {},
		easyCheckBlurIgnore: {},
		easyCheckKeyupIgnore: {},
		easyCheckEleIgnore: {},
		easyCheckEleBlurIgnore: {},
		easyCheckEleKeyupIgnore: {},
		easyCheckSubmitDisable: true,
		ecss: "yes",
		formEcss: {},
		focusCss: "",
		errorCss: "",
		formFocusCss: {},
		formErrorCss: {},
		txtClass: {},
		loadChk: true,
		blurChk: true,
		keyupChk: true,
		bootstrap3: {
			//  Add feedback icons
			icon:true,
			// Display * after required element
			required:true,
			// * poisition: left(label left), right(label right), after(form element after, only for 'form-horizontal', 'form-inline')
			requiredPosition:'after',
			// Display Dismissible alerts
			alert:true,
			alertMsg:'Validation failed!'
		},
		formatMsg: function() {
			var ary = [];
			for(var i = 1; i < arguments.length; i++) {
				ary.push(arguments[i]);
			}

			return arguments[0].replace(/\{(\d+)\}/g, function(m, i) {
				return ary[i];
			});
		},
		getMatches: function(chkName) {
			var regAttr = /(\[[A-Za-z0-9]+\])/g;
			var regClass = /(\.[A-Za-z0-9]+)/g;
			var chkElements = "";
			var matches = chkName.match(regAttr);
			if(matches != null) {
				$.each(matches, function(i, e) {
					chkElements += e;
				});
			}
			matches = chkName.match(regClass);
			if(matches != null) {
				$.each(matches, function(i, e) {
					chkElements += "[class~='" + e.substring(1) + "']";
				});
			}
			return chkElements;
		},
		clearAllError: function(formId) {
			EasyCheck.chkDef.errorManger({
				formId: formId
			});
			if(EasyCheck.chkDef.clearAllErrorComplete) {
				EasyCheck.chkDef.clearAllErrorComplete(formId);
			}
		},
		restoreAll: function(formId) {
			EasyCheck.chkDef.errorManger({
				formId: formId,
				restore: true
			});
			if(EasyCheck.chkDef.restoreAllComplete) {
				EasyCheck.chkDef.restoreAllComplete(formId);
			}
		},
		showError: function(o, msg) {
			EasyCheck.chkDef.showError(o, msg);
		},
		clearError: function(o, msg) {
			EasyCheck.chkDef.clearError(o, msg);
		},
		chk: function(o, e, chkFunction) {
			chkFunction(o, e);
		},
		initDefMsg: function() {
			for(var i = 0; i < EasyCheck.chkList.length; i++) {
				var chkrule = EasyCheck.chkList[i];
				if(!EasyCheck.easyCheckIgnore[chkrule.chkName]) {
					var chkElements = EasyCheck.getMatches(chkrule.chkName);
					$(chkElements).each(function() {
						var o = $(this);
						var formId = $("form").has(o).attr("id");
						var domId = o.attr("id") || o.attr("name");
						var defDiv = $("[id='default_" + domId + "']");
						if(defDiv.length > 0) {
							EasyCheck.chkDef.showDef(defDiv, formId, domId);
						}
					});
				}
			}
		},
		initChk: function(chkrule) {
			EasyCheck.chkDef.initChk(chkrule);
		},
		addChk: function(vName, vFn, msgFn) {
			var newChk = function(o, e) {
				return EasyCheck.chkDef.addChkMethod(vName, o, e, vFn, typeof msgFn == "string" ? msgFn : msgFn(o));
			};
			var chkRule = new EasyCheck.ChkRule(vName, newChk);
			EasyCheck.chkList.push(chkRule);
			EasyCheck.initChk(chkRule);
		},
		ChkRule: function(chkName, chkFunction) {
			this.chkName = chkName;
			this.chkFunction = chkFunction;
		},
		checkForm: function(eleArea) {
			if(typeof eleArea == "string") {
				eleArea = $(eleArea);
			}
			var formId = $(eleArea).attr("id");
			var formChkInfo = {
				eleArea: "[id='" + formId + "'] ",
				chkFlag: true,
				errorEleArray: new Array()
			};
			var promiseArrayAll = new Array();

			for(var i = 0; i < EasyCheck.chkList.length; i++) {
				var chkrule = EasyCheck.chkList[i];
				var promiseArray = addChkForm(chkrule, formChkInfo);

				promiseArrayAll = promiseArrayAll.concat(promiseArray);
			}

			if(window.Promise && promiseArrayAll.length > 0) {
				var p = Promise.all(promiseArrayAll).then(function(values) {
					subForm(formChkInfo, formId);
					return formChkInfo.chkFlag;
				});
				// return Promise
				return p;
			} else {
				// return true or false
				return subForm(formChkInfo, formId);
			}

			function subForm(formChkInfo, formId) {
				if(formChkInfo.chkFlag) {
					if(EasyCheck["easyCheckSubmitDisable"]) {
						$(":submit", $(formChkInfo.eleArea)).prop("disabled", true);
					}
					// 验证完成回调
					if(EasyCheck.chkDef.complete) {
						EasyCheck.chkDef.complete(formId, formChkInfo.chkFlag);
					}
				} else {
					// 验证完成回调
					if(EasyCheck.chkDef.complete) {
						EasyCheck.chkDef.complete(formId, formChkInfo.chkFlag);
					}
					$(":submit", $(formChkInfo.eleArea)).prop("disabled", false);
				}
				return formChkInfo.chkFlag;
			}
			return false;
		},
		// plugins 可覆盖扩展的默认检查配置
		chkDef: {
					// 错误消息清除管理
		errorManger : function(param) {
	        var s = "";
	        if (param.formId) {
	            s = "[id='" + param.formId + "'] ";
	        }
	        
	        // clearAllError
	        $(s + "[id^='error_']").each(function() {
	            var oNameOrId = $(this).attr("id").replace("error_", "");
	            
	            var controlGroup=$(this).parents(".form-group");
            	controlGroup.find('.form-control-feedback').remove();
            	controlGroup.removeClass('has-feedback'); //移除后面图示
            	controlGroup.removeClass('has-error'); // 移除状态
            	controlGroup.removeClass('has-success'); 
	            
	            var n = null;
	            if ($(s + "[id='" + oNameOrId + "']").length > 0) {
	                n = $(s + "[id='" + oNameOrId + "']");
	            } else {
	                n = $(s + "[name='" + oNameOrId + "']");
	            }
	            var formId = $("form").has(n).attr("id");
	            if (EasyCheck.ecss != "no" && EasyCheck.formEcss[formId] != "no") {
	                if (!(n.attr("ecss") && n.attr("ecss") != "yes")) {
	                    n.removeClass(EasyCheck.errorCss);
	                    if (formId && EasyCheck.formErrorCss[formId]) {
	                        n.removeClass(EasyCheck.formErrorCss[formId]);
	                    }
	                		var domId=n.attr("id")||n.attr("name");
	                		var oid=formId+"_"+domId;
	                        n.addClass(EasyCheck.txtClass[oid+"_class"]);
	                }
	            }
	            $(s + "[id^='error_']").hide();
	            
	        });
	        
	        //restoreAll
	        if (param.restore) {
	            var form=$("#"+param.formId);
	            form.find('.form-control-feedback').remove();
	            form.find('div').removeClass('has-feedback'); //移除后面图示
	            form.find('div').removeClass('has-error'); // 移除状态
	            form.find('div').removeClass('has-success'); 
	        	
	            $(s + "[id^='correct_']").each(function() {
	                var controlGroup=$(this).parents(".form-group");
	            	controlGroup.find('.form-control-feedback').remove();
	            	controlGroup.removeClass('has-feedback'); //移除后面图示
	            	controlGroup.removeClass('has-error'); // 移除状态
	            	controlGroup.removeClass('has-success'); 
	            	
	            	$(this).hide(); 
	            });
	            $(s + "[id^='default_']").each(function() {
	            	 var nowForm=$("form").has(this);
	            	 var defDiv=$(this);
	            	 if(nowForm){
	                	 var formId=nowForm.attr("id");
	                     var oid=defDiv.attr("id").substr("default_".length);
	                     EasyCheck.chkDef.showDef(defDiv,formId,oid ); 
	            	 }else{
	            		 EasyCheck.chkDef.showDef(defDiv); 
	            	 }
	            	
	            });
	        }
	    },
	    // 默认状态管理
	    showDef:function(defDiv,formId,oid){
	        if(formId){
	        	  var defMsg=defDiv.html();
	        	  
	        	  var controlGroup=$("#"+oid).parents(".form-group");
	        	  if(EasyCheck.bootstrap3.icon){ 
		            	controlGroup.find('.form-control-feedback').remove();
		            	controlGroup.removeClass('has-feedback'); //移除后面图示
		            	controlGroup.removeClass('has-error'); // 移除状态
		            	controlGroup.removeClass('has-success'); 
		           }
	        	  
	              if(EasyCheck.cacheDefMsg[formId+"_"+oid]!=undefined){
	            	  defMsg=EasyCheck.cacheDefMsg[formId+"_"+oid];
	              }else{
	            	  if(defDiv.attr("info")){
	            		  defMsg=defDiv.attr("info");
	                   }
	              	EasyCheck.cacheDefMsg[formId+"_"+oid]=defMsg;
	              }
	             
	              if(EasyCheck.defMsgs[oid]){
	            	  defDiv.html(EasyCheck.defMsgs[oid].replace(EasyCheck.msgMark,defMsg));
	              }else if(EasyCheck.defMsgs[formId]){
	            	  defDiv.html(EasyCheck.defMsgs[formId].replace(EasyCheck.msgMark,defMsg));
	              }else{ 
	            	  defDiv.html(EasyCheck.defMsg.replace(EasyCheck.msgMark,defMsg));
	              }
	      	}
	    	defDiv.show();
	    }, 
	    // 添加检测方法管理
	    addChkMethod : function(rule, o, e, chkCode, msg) {
	        var divSuf = $(o).attr("id") || $(o).attr("name");
	        var de = $("[id='default_" + divSuf + "']");
	        if (de) {
	            de.hide();
	        }
	        
	       
	         var flag=chkCode(o);
	         if(window.Promise && flag instanceof Promise){
				// ES6 Promise Support
				var p=flag.then(function(data){
					return execute(data);
				},function(){ 
					return execute(false);
				});
				return p;
			}else{
				return execute(flag);
			}
			
	        
	        function execute(res){
	        	var divSuf = $(o).attr("id") || $(o).attr("name");
	        	var de = $("[id='default_" + divSuf + "']");
	        	// 验证未通过
		        if (!res) {
		            var de = $("[id='default_" + divSuf + "']");
		            if (de) {
		                de.hide();
		            }
		            if (EasyCheck.msgs[divSuf] && EasyCheck.msgs[divSuf][rule]) {
		                msg = typeof EasyCheck.msgs[divSuf][rule] == "string" ? EasyCheck.msgs[divSuf][rule] :EasyCheck.msgs[divSuf][rule]($(o));
		            }
		            EasyCheck.showError(o, msg);
		            if (e) {
		                e.stopImmediatePropagation();
		            }
		            return false;
		        } else {
		        	// 验证通过
		  
		            EasyCheck.clearError(o);
		            var defaultDiv = $("[id='default_" + divSuf + "']");
		            if (defaultDiv) {
		                defaultDiv.hide();
		            }
		            
		            var okIcon='<span class="glyphicon glyphicon-ok form-control-feedback"></span>';
			          
		            var okDiv = $("[id='correct_" + divSuf + "']");
		            
		            var formId = $("form").has(o).attr("id");
		            
		            var controlGroup=$(o).parents(".form-group");
		            controlGroup.removeClass('has-error has-success');
		            controlGroup.addClass('has-success');
		           
		            if(EasyCheck.bootstrap3.icon){ 
		            	controlGroup.addClass('has-feedback'); //增加后面图示
		            	controlGroup.find('.form-control-feedback').remove();
		            	 $(o).after(okIcon);
		            }
		         
		            if (okDiv.length>0) {
		                okDiv.addClass("help-block");
		                var correctMsg=okDiv.html();
		                if(EasyCheck.cacheCorrectMsg[formId+"_"+divSuf]!=undefined){
		                	correctMsg=EasyCheck.cacheCorrectMsg[formId+"_"+divSuf];
		                }else{
		            	  if(okDiv.attr("info")){
		            		  correctMsg=okDiv.attr("info");
		                   }
		                	EasyCheck.cacheCorrectMsg[formId+"_"+divSuf]=correctMsg;
		                }
		                
		                if(EasyCheck.correctMsgs[divSuf]){
		                	okDiv.html(EasyCheck.correctMsgs[divSuf].replace(EasyCheck.msgMark,correctMsg));
		                }else if(EasyCheck.correctMsgs[formId]){
		                	okDiv.html(EasyCheck.correctMsgs[formId].replace(EasyCheck.msgMark,correctMsg));
		                }else{
		                	okDiv.html(EasyCheck.correctMsg.replace(EasyCheck.msgMark,correctMsg));
		                }
		                
		                okDiv.show();
		            }
		            if (e) {
		                if (e.type == "keyup") {
		                    $(o).removeClass(EasyCheck.errorCss);
		                    var nowForm = $("form").has(o);
		                    if (nowForm.length > 0 && EasyCheck.formErrorCss[nowForm.attr("id")]) {
		                        $(o).removeClass(EasyCheck.formErrorCss[nowForm.attr("id")]);
		                    }
		                    if (nowForm.length > 0 && EasyCheck.formFocusCss[nowForm.attr("id")]) {
		                        $(o).addClass(EasyCheck.formFocusCss[nowForm.attr("id")]);
		                    } else {
		                        $(o).addClass(EasyCheck.focusCss);
		                    }
		                }
		            }
		            return true;
		        }
	        }
	        
	    },
	    // 显示错误
	    showError: function(o, msg){
	    	 if (typeof o == "string") {
	                o = $("#" + o)[0] || $("[name='" + o + "']")[0];
	            }
	            o=$(o);
	             
	            var controlGroup=o.parents(".form-group");
	            controlGroup.removeClass('has-error has-success');
	            controlGroup.addClass('has-error');
	            
	            var divSuf = o.attr("id") || o.attr("name");
	            $("[id='correct_" + divSuf + "']").hide();
	            $("[id='default_" + divSuf + "']").hide();
	            var eo = $("[id='error_" + divSuf + "']");
	            if (eo.length == 0) {
	            	
	            	var req=o.next('[name="required"]'); 
	            	
	            	if(req.length>0){
	            		 req.after("<span id='error_" + divSuf + "' class='help-block'></span>");
	            	}else{
	            		 o.after("<span id='error_" + divSuf + "' class='help-block'></span>");
	            	}
	               
	                eo = $("[id='error_" + divSuf + "']");
	            }
	            eo.addClass("help-block");
	            var formId = $("form").has(o).attr("id");
	            if (EasyCheck.ecss != "no" && EasyCheck.formEcss[formId] != "no") {
	                if (!(o.attr("ecss") && o.attr("ecss") != "yes")) {
	                	controlGroup.removeClass('has-error has-success');
	                    if (formId && EasyCheck.formErrorCss[formId]) {
	                        o.addClass(EasyCheck.formErrorCss[formId]);
	                    } else {
	                    	controlGroup.addClass('has-error');
	                    }
	                }
	            }
	            
	            var errorMsg="";
	            var prefix = eo.attr("prefix");
	            if (prefix) {
	            	errorMsg=prefix + msg;
	            } else {
	                prefix = "";
	                errorMsg=msg;
	            }
	            
	            
	            var info=eo.html();
	            if(EasyCheck.cacheErrorMsg[formId+"_"+divSuf]!=undefined){
	            	info=EasyCheck.cacheErrorMsg[formId+"_"+divSuf];
	            }else{
	            	 if(eo.attr("info")){
	                 	info=eo.attr("info");
	                 }
	            	EasyCheck.cacheErrorMsg[formId+"_"+divSuf]=info;
	            }
	            
	            if (info) {
	            	errorMsg=prefix + info;
	            }
	            
	            var errorIcon='<span class="glyphicon glyphicon-remove form-control-feedback"></span>'
	            
	            if(EasyCheck.bootstrap3.icon){ 
	            	controlGroup.addClass('has-feedback'); //增加后面图示
	            	controlGroup.find('.form-control-feedback').remove();
//	            	eo.before(errorIcon);
	            	o.after(errorIcon);
	            }
	            
	            if(EasyCheck.errorMsgs[divSuf]){
	            	eo.html(EasyCheck.errorMsgs[divSuf].replace(EasyCheck.msgMark,errorMsg));
	            }else if(EasyCheck.errorMsgs[formId]){
	            	eo.html(EasyCheck.errorMsgs[formId].replace(EasyCheck.msgMark,errorMsg));
	            }else{
	            	eo.html(EasyCheck.errorMsg.replace(EasyCheck.msgMark,errorMsg));
	            }
	            eo.show();
	            
	    },
	    // 清除错误
	    clearError:function(o, msg){
	    	 if (typeof o == "string") {
	                o = $("#" + o)[0] || $("[name='" + o + "']")[0];
	            }
	            o=$(o);
	            var divSuf = o.attr("id") || o.attr("name");
	            var eo = $("[id='error_" + divSuf + "']");
	          
	            var formId = $("form").has(o).attr("id");
	            if (eo.length>0) {
	                if (EasyCheck.ecss != "no" && EasyCheck.formEcss[formId] != "no") {
	                    if (!(o.attr("ecss") && o.attr("ecss") != "yes")) {
	                        o.removeClass(EasyCheck.errorCss);
	                        if (formId && EasyCheck.formErrorCss[formId]) {
	                            o.removeClass(EasyCheck.formErrorCss[formId]);
	                        }
	                      		var domId=o.attr("id")||o.attr("name");
	                      		var oid=formId+"_"+domId;
	                            o.addClass(EasyCheck.txtClass[oid+"_class"]);
	                        	
	                    }
	                }
	                if (msg) {
	                    eo.html(msg);
	                } else {
	                    eo.html("");
	                }
	                eo.hide();
	                
	                var defDiv=$("[id='default_" + divSuf + "']");
	                $("[id='correct_" + divSuf + "']").hide();
	                EasyCheck.chkDef.showDef(defDiv,formId,divSuf ); 
	            }
	            
	            o.removeClass(EasyCheck.focusCss);
	            if (formId && EasyCheck.formFocusCss[formId]) {
	                o.removeClass(EasyCheck.formFocusCss[formId]);
	            }
	            
	            var domId=o.attr("id")||o.attr("name");
	      		var oid=formId+"_"+domId;
	            o.addClass(EasyCheck.txtClass[oid+"_class"]);
	    },
	    // 初始化Chk框
	    initChk:function(chkrule) {
	    	
            var chkElements = EasyCheck.getMatches(chkrule.chkName);
            $(chkElements).each(function(){
            		var o=$(this);
            		var nowForm=$("form").has(o);
            		var formId = nowForm.attr("id");
            		var domId=o.attr("id")||o.attr("name");
            		// 为必填元素添加必填符号
            		var req=EasyCheck.bootstrap3.required;
            		if(req){
            			if(chkrule.chkName=='.required'){
            				var pst=EasyCheck.bootstrap3.requiredPosition;
	            			var s='<span name="required" class="text-muted" style="color:#FF9966;">*</span>';
	            			if(pst=='after' && (nowForm.hasClass("form-horizontal") || nowForm.hasClass("form-inline")) ){
	                   			 	o.parent().after(s); 
	            			}else if(pst=='left'){
	            				 var label=$(this).parents(".form-group").find("label:first");
	            				 label.prepend(s);
	            			}else if(pst=='right'){
		           				 var label=$(this).parents(".form-group").find("label:first");
		        				 label.append(s);
		        			}
            			}
            		}  
            }); 
           
            
            $(chkElements).on("blur change", function(e) {
                if (!EasyCheck.easyCheckBlurIgnore[chkrule.chkName] && !EasyCheck.easyCheckEleIgnore[e.target.id || e.target.name]) {
                    if (!EasyCheck.easyCheckEleBlurIgnore[e.target.id || e.target.name]) {
                        EasyCheck.blurChk ? EasyCheck.chk(this, e, chkrule.chkFunction) :"";
                    }
                }
            }).on("focus", function(e) {
            	if(!EasyCheck.resetOnFocus){
            		return;
            	}
                EasyCheck.clearError(this);
                var o = $(this);
                var domId = o.attr("id") || o.attr("name");
                var okDiv = $("[id='correct_" + domId + "']");
                if (okDiv.length > 0) {
                    if (okDiv.filter(":hidden").length > 0) {
                        var defDiv = $("[id='default_" + domId + "']");
                        if (defDiv) {
                        	  $("[id='correct_" + domId + "']").hide();
                        	  EasyCheck.chkDef.showDef(defDiv,$("form").has($(domId)).attr("id"),domId ); 
                        }
                    }
                } else {
                    var defDiv = $("[id='default_" + domId + "']");
                    if (defDiv) {
                    	  $("[id='correct_" + domId + "']").hide();
                    	  EasyCheck.chkDef.showDef(defDiv,$("form").has($(domId)).attr("id"),domId ); 
                    }
                }
                var nowForm = $("form").has(o);
        		var formId = nowForm.attr("id");
                
                var formId = nowForm.attr("id");
                if (formId && EasyCheck.formFocusCss[formId]) {
                    o.addClass(EasyCheck.formFocusCss[formId]);
                } else {
                    o.addClass(EasyCheck.focusCss);
                }
            }).on("keyup", function(e) {
                if (!EasyCheck.easyCheckKeyupIgnore[chkrule.chkName] && !EasyCheck.easyCheckEleIgnore[e.target.id || e.target.name]) {
                    if (!EasyCheck.easyCheckEleKeyupIgnore[e.target.id || e.target.name]) {
                        EasyCheck.keyupChk ? EasyCheck.chk(this, e, chkrule.chkFunction) :"";
                    }
                }
            });
        },
        complete:function(formId,result){
	    	 if(EasyCheck.bootstrap3.alert){
	    	    	if($("#alert_"+formId).length==0){
	    	    		var submit=$("#"+formId).find(':submit:last');
	    	    		if(submit.length>0){
	    	    			submit.before('<div style="display:none" id="alert_'+formId+'" class="alert alert-warning alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>'+EasyCheck.bootstrap3.alertMsg+'</div>');
	    	    		}
	    	    	}
	    	    	if(result){
	    	    		$("#alert_"+formId).hide(); 	
	    		    }else{
	    		    	$("#alert_"+formId).show(); 	
	    		    }
	    	    }
	    },
	    restoreAllComplete:function(formId){
	    	$("#alert_"+formId).hide(); 
	    },
	    clearAllErrorComplete:function(formId){
	    	$("#alert_"+formId).hide(); 
	    }
		}
	};

	EasyCheck.chkList = [new EasyCheck.ChkRule(".required", checkRequired), new EasyCheck.ChkRule(".email", checkEmail), new EasyCheck.ChkRule(".url", checkUrl), new EasyCheck.ChkRule(".number", checkNumber), new EasyCheck.ChkRule(".integer", checkInteger), new EasyCheck.ChkRule("[equalto]", checkEqualto), new EasyCheck.ChkRule("[equallength]", checkEquallength), new EasyCheck.ChkRule("[minlength][maxlength]", checkRangeLength), new EasyCheck.ChkRule("[minlength]", checkMinlength), new EasyCheck.ChkRule("[maxlength]", checkMaxlength), new EasyCheck.ChkRule("[min][max]", checkRange), new EasyCheck.ChkRule("[min]", checkMin), new EasyCheck.ChkRule("[max]", checkMax), new EasyCheck.ChkRule("[reg]", checkRegExp), new EasyCheck.ChkRule("[extension]", checkExtension), new EasyCheck.ChkRule("[vc]", checkVc)];
	EasyCheck.easyCheckIgnore["[vc]"] = true;
	window.EasyCheck = EasyCheck;
})(window);

$(function() {
	function easyCheck() {
		for(var i = 0; i < EasyCheck.chkList.length; i++) {
			var chkrule = EasyCheck.chkList[i];
			if(!EasyCheck.easyCheckIgnore[chkrule.chkName]) {
				EasyCheck.initChk(chkrule);
			}
		}
	}

	EasyCheck.loadChk ? "" : (EasyCheck.blurChk = false, EasyCheck.keyupChk = false);
	easyCheck();

	$("[id*='correct_']").hide();
	$("[id*='error_']").hide();
	// 取消html默认验证提示
	$("form").attr("novalidate", "novalidate");

	var ecs = $("[easycheck='true']");

	// 清除禁用
	ecs.find(":submit").prop('disabled', false);

	ecs.on("submit", function() {
		var result = EasyCheck.checkForm(this);
		var form=this;
		if(window.Promise && result instanceof Promise) {
			result.then(function(data) {
				if(data) {
					form.submit(); // 提交
				}
			})
			return false;
		} else {
			return result;
		}
	});
	// 自动注册重置操作
	ecs.find(":reset").on("click", function() {
		var form = $(this).parents("form");
		if(form) {
			var formId = form.attr("id");
			if(formId) {
				EasyCheck.restoreAll(formId);
			}
		}
	});
	$(":submit").attr("autocomplete", "off");

});