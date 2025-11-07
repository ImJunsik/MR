/**
 * @author 박성룡
 * @version 1.0
 *
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.06.19 박성룡 최초 작성
 */

/**
 * selectBox용 Ajax
 * ==================================================
 * className    : HTML 상의 클래스 이름
 * url          : ajax 호출 option.url
 * defaultText  : selectBox에 아무것도 선택하지 않았을때 보여질 Text
 * defaultValue : selectBox에 아무것도 선택하지 않았을때 Value 값
 */
$.selectLoad = function(option){
    var selected = "";
    $("."+option.className).find("option").remove().end();

    if(option.defaultText!=null) {
    	if(option.defaultValue==null)
    		option.defaultValue = "";
		$("."+option.className).append("<option value='"+option.defaultValue+"'>"+option.defaultText+"</option>");
    }

	    $.ajax({
	           type:"GET",
	           url:option.url,
	           dataType:"JSON",
	           success : function(data) {

	        	   if(data.selectList==null) {
	        		   $("."+option.className).hide();
	        	   } else {
		        	   $.each(data.selectList, function() {
		        		   selected = "";
			        	   if(option.selectValue!=null && option.selectValue==this.value)
			        		   selected = "selected";
		                   $("."+option.className).append("<option value='"+this.value+"' "+selected+">"+this.text+"</option>");
		                });
	        	   }
	           },
	           error:function(e){
	           }
	    });
};


$.newSelectLoad = function(option){
    var checked = "";

    var checkCount = 0;

    if(option.defaultText!=null) {
    	if(option.defaultValue==null)
    		option.defaultValue = "";
		$("."+option.className).append("<li><input type='radio' name='"+option.name+"' id='b1' class='option' value='"+option.defaultValue+"' /><label for='b1'>"+option.defaultText+"</label></li>");
    }

	    $.ajax({
	           type:"GET",
	           url:option.url,
	           dataType:"JSON",
	           success : function(data) {

	        	   if(data.selectList==null) {
	        		   $("."+option.className).hide();
	        	   } else {
		        	   $.each(data.selectList, function() {
		        		   checked = "";
			        	   if(option.selectValue!=null && option.selectValue==this.value) {
			        		   checked = "checked";
			        		   checkCount++;
			        	   }
		                   $("."+option.className).append("<li><input type='radio' name='"+option.name+"' id='b1' class='option' value='"+this.value+"' "+checked+" /><label for='b1'>"+this.text+"</label></li>");
		                });

		        	   if(checkCount==0){
		        		   $("."+option.className).children("li:eq(0)").children("input").attr("checked",true);
		        	   }
	        	   }
	           },
	           error:function(e){

	           }
	    });
};

$.checkLoad = function(option){
    var checked = "";
    var count = 0;
    var br = "";
    var getClass = "";
    var disabled="";
    if(option.isDisabled) {
    	disabled="disabled='disabled'";
    }
    if(option.isBr) {
    	br = "<br/>";
    }

    if(option.setClass!=null) {
    	getClass = " class='"+option.setClass+" i_check' ";
    }

	$.ajax({
	       type:"GET",
	       url:option.url,
	       dataType:"JSON",
	       success : function(data) {
		      $.each(data.selectList, function() {
		          if(option.checkValue!=null) {
		        	  checked = "";
		        	  for (var loop in option.checkValue) {
		        		  if(option.checkValue[loop]==this.value)
		        			  checked = "checked";
		        	  }
		          }
		          /* checkbox validation 추가를 위해 id를 추가함 */
	              $("."+option.className).append("<input type='checkbox'"+ " id='"+option.className+"'"+getClass+"name='"+option.fristName+"["+count+"]."+option.lastName+"' value='"+this.value+"' "+checked+" "+disabled+">"+this.text+br);
	              count++;
	          });
	       },
	       error:function(e){

	       }
	});
	return count;
};


$.radioLoad = function(option){
    var checked = "";
    var count = 0;
    var br = "";
    var getClass = "";
    var disabled="";
    if(option.isDisabled) {
    	disabled="disabled='disabled'";
    }
    if(option.isBr) {
    	br = "<br/>";
    }

    if(option.setClass!=null) {
    	getClass = " class='"+option.setClass+" i_radio' ";
    }

	$.ajax({
	       type:"GET",
	       url:option.url,
	       dataType:"JSON",
	       success : function(data) {
		      $.each(data.selectList, function() {
		          if(option.checkValue!=null) {
		        	  checked = "";
		        	  for (var loop in option.checkValue) {
		        		  if(option.checkValue[loop]==this.value)
		        			  checked = "checked";
		        	  }
		          }
	              $("."+option.className).append("<input type='radio'"+getClass+"name='"+option.name+"' value='"+this.value+"' "+checked+" "+disabled+">"+this.text+br);
	              count++;
	          });
	       },
	       error:function(e){

	       }
	});
	return count;
};