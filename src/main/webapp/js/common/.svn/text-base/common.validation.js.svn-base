/**
 * @author 박성룡
 * @version 1.0
 *
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.06.19 박성룡 최초 작성
 */
$.validator = function(option){
	var isValidate = false;
	var selector="";
	var focus="";
	var isLength = false;
	var msg = "";
    for(var name in option) {
    	selector="";
    	focus="";
    	if(option[name].method=="class") {
    		selector="." +name;
    	} else {
    		selector="#" +name;
    	}

    	if(option[name].focus==null){
    		focus = selector;
    	    $(focus).css("border-style","solid");
    	    $(focus).css("border-color","#e5e5e5");
    	    $(focus).css("border-width","1px");
    	} else {
        	if(option[name].method=="class") {
        		focus="." +option[name].focus;
        	} else {
        		focus="#" +option[name].focus;
        	}
    	    $(focus).css("border-style","solid");
    	    $(focus).css("border-color","#fff");
    	    $(focus).css("border-width","0px");
    	}

    	switch (option[name].type) {
    	case "text":
    		isValidate = $.textValidate(selector);
    		if(isValidate && option[name].varType != null) {
    			isLength = $.lengthValidate(option[name].min, option[name].max, option[name].varType, selector);
    		} else if (isValidate && option[name].varType == null) {
    			isLength = true;
    		}
    		break;
    	case "select":
    		isValidate = $.selectValidate(selector);
    		isLength = true;
    		break;
    	case "check":
    		isValidate = $.checkValidate(selector);
    		isLength = true;
    		break;
    	case "list":
    		isValidate = $.listValidate(selector);
    		isLength = true;
    		break;
    	}

    	if(!isValidate) {
    	    var position = $(focus).offset();
    	    $('html, body').animate({scrollTop : position.top}, 2000);

    	    $(focus).focus();
    	    $(focus).css("border-style","solid");
    	    $(focus).css("border-color","#91ce44");
    	    $(focus).css("border-width","2px");

    		if(option[name].msg!=null) {
    			alert(option[name].msg);
    		} else {
    			alert($(selector).attr("title") + " 입력하세요.");
    		}
    		return isValidate;
    	}

    	if(!isLength){
    	    $(focus).focus();
    	    $(focus).css("border-style","solid");
    	    $(focus).css("border-color","#91ce44");
    	    $(focus).css("border-width","2px");

    	    if(option[name].min != null) {
    			msg += ' 최소 : ' + option[name].min;
    		}

    		if(option[name].max != null) {
    			msg += ' 최대 : ' + option[name].max;
    		}
    		msg += ' 까지 입력가능합니다.';


    		if(option[name].msg!=null) {
    			alert(option[name].msg + " " + msg);
    		} else {
    			alert($(selector).attr("title") + " 확인 하세요." + msg);
    		}

    		return false;
    	}



    }
	return isValidate;

};

$.textValidate = function(selector) {
	if($(selector).val()==""){
		return false;
	}
	return true;
};

$.selectValidate = function(selector) {
	if($.type($(selector+" option:selected").val())==="undefined" || $(selector+" option:selected").val()==""){
		return false;
	}
	return true;
};

$.checkValidate = function(selector) {
	if($.type($(selector+":checked").val())==="undefined" || $(selector+":checked").val()==""){
		return false;
	}
	return true;
};

$.listValidate = function(selector){
	var count = 0;
	$(selector).children().each(function (){
		count++;
	});
	if(count<1) {
		return false;
	}
	return true;
};

$.lengthValidate = function(min, max, type, selector){
	var textLength = 0;
	if(type=='num') {
		if((min!=null && min < parseInt($.removeSeparator($(selector).val()))) || (max!=null && parseInt($.removeSeparator($(selector).val())) > max)){
			return false;
		} else {
			return true;
		}
	} else {
		textLength = $(selector).val().length;
		if((min!=null && textLength < min) || (max!=null && max< textLength)) {
			return false;
		} else {
			return true;
		}
	}


}