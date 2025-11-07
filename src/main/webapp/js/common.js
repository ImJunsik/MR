$.validator.addMethod("length", function(value, element, params) {
    return value.length == params;
}, $.validator.format("문자 {0} 자로 입력하세요."));

$.fn.initPagination = function (prefix) {
    if (prefix == "undefined" || $.trim(prefix).length == 0) {
        prefix = "pagination";
    }
    var $input = $("<input>", {type: "hidden", name: prefix + ".currentPageNo", value: "1"});
    $input.appendTo(this);
};

var documentViewExtension = ["doc", "docx", "xls", "xlsx", "ppt", "pptx"];
$.fn.appendDocumentViewAnchor = function () {
    $(this).each(function (index, element) {
        var filename = $(element).text();

        $.each(documentViewExtension, function (index, value) {
            if (filename.match(value)) {
                var $a = $("<a>", {
                    href: "attachFile/documentView.do?code=" + $(element).data("code"), text: "[바로보기]"
                });
                $a.insertAfter(element);
            }
        });
    });
};

$.fn.format = function (source, params) {
	if (arguments.length === 1) {
		return function() {
			var args = $.makeArray(arguments);
			args.unshift(source);
			return $.validator.format.apply( this, args );
		};
	}
	if (arguments.length > 2 && params.constructor !== Array) {
		params = $.makeArray(arguments).slice(1);
	}
	if (params.constructor !== Array) {
		params = [ params ];
	}
	$.each(params, function(i, n) {
		source = source.replace(new RegExp("\\{" + i + "\\}", "g"), function() {
			return n;
		});
	});
	return source;
};

$(document).ajaxError(function (evnet, xhr, settings, thrownError) {
    var errorWindow = window.open("", "", "width=800, height=700, resizeable, scrollbars");
    errorWindow.document.write(xhr.responseText);
});