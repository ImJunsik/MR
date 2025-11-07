/**
 * @author 박성룡
 * @version 1.0
 *
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.06.19 박성룡 최초 작성
 * 2014.07.28 박성룡 담당자 중복체크 기능 추가
 */

/**
 * 1000단위 표시 반환 함수
 * ==================================================
 * double    : HTML 상의 클래스 이름
 * @returns 1000단위 표시된 String
 */
var targetName = "";
var overlap = null;
var popupCheck = "";
$.separator = function (double) {
	var isMinus = double.substring(0,1) == "-" ? true : false;
	if(double.lastIndexOf(".")>0)
		double = double.substring(0,double.lastIndexOf(".")).replace(/[^0-9]/g, '') + "." +double.substring(double.lastIndexOf("."),double.length).replace(/[^0-9]/g, '') ;
	var parts = double.toString().split(".");
	parts[0] = parts[0].replace(/[^0-9]/g, '');
	if(parseInt(parts[0])>0)
		parts[0] = parseInt(parts[0])+"";
	parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	return isMinus ? "-"+parts.join(".") : parts.join(".");
};

/**
 * 1000단위 표시 제거 함수
 * ==================================================
 * double    : HTML 상의 클래스 이름
 * @returns 1000단위 표시된 String
 */
$.removeSeparator = function (double) {

	var isMinus = double.substring(0,1) == "-" ? true : false;
	if(double.lastIndexOf(".")>0)
		double = double.substring(0,double.lastIndexOf(".")).replace(/[^0-9]/g, '') + "." +double.substring(double.lastIndexOf("."),double.length).replace(/[^0-9]/g, '') ;
	var parts = double.toString().split(".");
	parts[0] = parts[0].replace(/[^0-9]/g, '');
	if(parseInt(parts[0])>0)
		parts[0] = parseInt(parts[0])+"";
	return isMinus ? "-"+parts.join(".") : parts.join(".");
};

/**
 * 팝업창 띄우는 함수
 */
$.popup = function(option){
	var popupName = "Window";
	var scrollbars = "no";
    if(!popupCheck.closed && popupCheck){
        alert("다른 팝업창이 이미 존재합니다. 팝업창 종료후 다시 시도하세요.");
        return;
    }
    if(option.popupName != null) {
    	popupName = option.popupName;
    }
    if(option.scroll!=null) {
    	scrollbars = option.scroll;
    }
	popupCheck = window.open(option.url, popupName, "scrollbars="+scrollbars+", width="+option.width+", height="+option.height);
	targetName = option.targetName;
	if (option.overlap != null){
		overlap = option.overlap;
	} else {
		overlap = null;
	}
};

/**
 * 팝업창의 팝업창 띄우는 함수
 */
$.popupPopup = function(option){
	var scrollbars = "no";
    if(!popupCheck.closed && popupCheck){
        alert("다른 팝업창이 이미 존재합니다. 팝업창 종료후 다시 시도하세요.");
        return;
    }
    if(option.scroll!=null) {
    	scrollbars = option.scroll;
    }
	popupCheck = window.open(option.url, "WindowPop", "scrollbars="+scrollbars+", width="+option.width+", height="+option.height);
	targetName = option.targetName;
};


/**
 * 팝업창으로 띄워서 불러온 데이터를 inputBox에 셋팅
 */
$.injectValue = function(option){

	var isValid = false;
	
	if(overlap != null){
		$("."+overlap).find("input").each(function () {
			if(option.value==$(this).val()) {
				isValid = true;
				return false;
			}
		});
	}

	if(isValid) {
		alert("담당자가 중복됩니다.\n그래도 해당 담당자를 지정하시겠습니까?");

	} //else {

		$("."+targetName+"Text").val(option.text);
		$("."+targetName+"Value").val(option.value);

		if(option.teamText!=null)
			$("."+targetName+"TeamText").val(option.teamText);

		if(option.teamValue!=null)
			$("."+targetName+"TeamValue").val(option.teamValue);

		if(option.dutyText!=null)
			$("."+targetName+"DutyText").val(option.dutyText);

		targetName="";
		overlap=null;
	//}
};

/**
 * 현재 날짜를 반환함 YYYY-DD-MM
 */
$.getCurrentDate = function(){
	  var nowDate = new Date();
	  return leadingZeros(nowDate.getFullYear(), 4) + '-' + leadingZeros(nowDate.getMonth() + 1, 2) + '-' + leadingZeros(nowDate.getDate(), 2);
};

function leadingZeros(n, digits) {
	  var zero = '';
	  n = n.toString();

	  if (n.length < digits) {
	    for (i = 0; i < digits - n.length; i++)
	      zero += '0';
	  }
	  return zero + n;
}

/**
 * sort
 */
$.sort = function(option){
	var count = 0;
	if(option.startCount !=null)
		count = option.startCount;
	$("."+option.className).each(function(){
		$(this).attr("name",option.fristName + "["+count+"]."+option.lastName);
		count++;
	});
	return count;
};


/**
 * 결재라인 셋팅
 */
$.appLineSet= function(option) {
		$("."+option.className+"Text").val(option.text);
		$("."+option.className+"Value").val(option.value);

		if(option.teamText!=null) {
			$("."+option.className+"TeamText").val(option.teamText);
		}

		if(option.DetNo!=null) {
			$("."+option.className+"DetNo").val(option.DetNo);
		}

		if(option.teamValue!=null) {
			$("."+option.className+"TeamValue").val(option.teamValue);
		}

		if(option.dutyText!=null) {
			$("."+option.className+"DutyText").val(option.dutyText);
		}

		if(option.sugg != null) {
			$("."+option.className+"Sugg").val(option.sugg);
			$("."+option.className+"SuggText").html(option.sugg);
		}

		if(option.endDate!=null && option.endDate!="") {
			if("9999-12-31" != option.endDate) {
				$("."+option.className+"EndDate").val(option.endDate);
			} else {
				$("."+option.className+"EndDate").val("");
			}

			if(option.className == 'agree')		//yoo 20230313 (결재중) 추가
				$("."+option.className+"EndDate").html("기한 : " + option.endDate + " (결재중) ");
			if(option.className == 'app')		//yoo 20240927 (결재중) 추가
				$("."+option.className+"EndDate").html("기한 : " + option.endDate + " (결재중) ");
			$("."+option.className+"EmptyDate").html(option.endDate);
		}

		if(option.endDate=="") {
			$("."+option.className+"Date").html(option.date);
		}
		
		//console.log("className::"+option.className+"Text");
		//console.log("textname,dutytext::" + option.teamText +" / "+ option.dutyText +" / "+ option.text);
		$("."+option.className+"Text").html(option.teamText +" / "+ option.dutyText +" / "+ option.text);
};

$.fileUpload = function(option){
	var docSeq = "";
	Openfile.addFileForAttachment(option.localAddr, option.filePath, option.empNo, option.plantNo, option.mrNo, "" , function(doc_seq) {
		docSeq = doc_seq;
	});

	return docSeq;
};


jQuery(function($){

    // Common
    var select_root = $('div.select');
    var select_value = $('.myValue');
    var select_a = $('div.select>ul>li>a');
    var select_input = $('div.select>ul>li>input[type=radio]');
    var select_label = $('div.select>ul>li>label');

    // Radio Default Value
    $('div.myValue').each(function(){
        var default_value = $(this).next('.iList').find('input[checked]').next('label').text();
        $(this).append(default_value);
    });

    // Line
    select_value.bind('focusin',function(){$(this).addClass('outLine');});
    select_value.bind('focusout',function(){$(this).removeClass('outLine');});
    select_input.bind('focusin',function(){$(this).parents('div.select').children('div.myValue').addClass('outLine');});
    select_input.bind('focusout',function(){$(this).parents('div.select').children('div.myValue').removeClass('outLine');});

    // Show
    function show_option(){
        $(this).parents('div.select:first').toggleClass('open');
    }

    // Hover
    function i_hover(){
        $(this).parents('ul:first').children('li').removeClass('hover');
        $(this).parents('li:first').toggleClass('hover');
    }

    // Hide
    function hide_option(){
        var t = $(this);
        setTimeout(function(){
            t.parents('div.select:first').removeClass('open');
        }, 1);
    }

    // Set Input
    function set_label(){
        var v = $(this).next('label').text();
        $(this).parents('ul:first').prev('.myValue').text('').append(v);
        $(this).parents('ul:first').prev('.myValue').addClass('selected');
    }

    // Set Anchor
    function set_anchor(){
        var v = $(this).text();
        $(this).parents('ul:first').prev('.myValue').text('').append(v);
        $(this).parents('ul:first').prev('.myValue').addClass('selected');
    }

    // Anchor Focus Out
    $('*:not("div.select a")').focus(function(){
        $('.aList').parent('.select').removeClass('open');
    });

    select_value.click(show_option);
    select_root.removeClass('open');
    select_root.mouseleave(function(){$(this).removeClass('open');});
    select_a.click(set_anchor).click(hide_option).focus(i_hover).hover(i_hover);
    select_input.change(set_label).focus(set_label);
    select_label.hover(i_hover).click(hide_option);

    // Form Reset
    $('input[type="reset"], button[type="reset"]').click(function(){
        $(this).parents('form:first').find('.myValue').each(function(){
            var origin = $(this).next('ul:first').find('li:first label').text();
            $(this).text(origin).removeClass('selected');
        });
    });

});
