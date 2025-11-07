/**
 * 해당 자료를 local_pathfile에 다운로드 받는다. local_pathfile == undefined 이면 임시 폴더에 저장한다.
 * 다운로드 실패할경우 ""를 리턴합니다.
 *
 * @param docseqno
 *            받고자 하는 도면 시퀀스
 * @param local_pathfile
 *            Local 경로를 포함하는 파일명 없으면 임시경로에 저장하고 return받습니다.
 * @returns 로컬에 떨어지는 경로를 포함하는 파일명
 */
//var _contextPathURL = "http://127.0.0.1/edims" ;
//var _contextPathURL = "http://127.0.0.1/edims" ;
var _contextPath = "/edims" ;
//var _contextPathURL = "http://<%=request.getLocalAddr()%>/edims" ;
var _contextPathURL ;

var Openfile = new _Openfile();

function _Openfile() {
	this.retryCnt = 5;
}

String.prototype.endsWith = function (s) {
	  return this.length >= s.length && this.substr(this.length - s.length) == s;
} ;

_Openfile.prototype.isFtp = function() {
//	var openEDM_contextPath = "/edims" ;
	if (document.OpenFtp == null) {
		var div = document.createElement('div');
		div.innerHTML = '<OBJECT ID="OpenFTPCom"'
				+ 'CLASSID="CLSID:51EFD9E0-9CFE-4C43-9FD3-E9CBBCEA139B"'
				+ 'CODEBASE="'
				+ _contextPath
				+ '/common/edms/edims.exe#version=1,3,20,0" width="0" height="0" visible=0 name="OpenFtp">'
				+ '</OBJECT>';
		document.body.appendChild(div);
	}
};
_Openfile.prototype.fsexecPgm = function(local_pathfile) {
	this.isFtp();
	document.OpenFtp.fsexecPgm(local_pathfile);
};
_Openfile.prototype.downloadFile = function(fileDTO, local_pathfile) {
	if (local_pathfile == undefined) {
		local_pathfile = this.getTempPath("");
	} else {
		if (!local_pathfile.endsWith('\\')) {
			local_pathfile = local_pathfile + '\\';
		}
	}
	var res = "";
	var url = _contextPath + "/edms/fi/seDownloadinfo.action";

	var data = {
			'fileDTO.file_id' : fileDTO.file_id ,
			'fileDTO.f_category' : fileDTO.f_category,
			'fileDTO.f_category_id' : fileDTO.f_category_id,
			'kind' : fileDTO.kind
	} ;

	var _this = this;

    jQuery.ajax({
        type:"POST",
        url: url ,
        async: false,
        data : data,
        dataType:"text",
        success : function(data) {
        	alert('data=' + data) ;
			var json = eval('(' + data + ')');
			for ( var i = 0; i < json.length; i++) {
				var file = json[i];
				if (file.bPrint == undefined) {
					if (file.serverInfoDTO == null
							|| file.serverInfoDTO == "") {

						if (file.fileDTO.com_new_file != null
								&& file.fileDTO.com_new_file != ""){
							_this
									.httpDownloadFile(
											file.fileDTO.file_id,
											local_pathfile
													+ file.fileDTO.org_file,
											local_pathfile
													+ file.fileDTO.com_org_file);
						}else{
							_this.httpDownloadFile(
									file.fileDTO.file_id,
									local_pathfile
											+ file.fileDTO.org_file);
						}
					} else {
						_this.ftpDownloadFile(file.serverInfoDTO,
								local_pathfile + file.fileDTO.org_file,
								file.directoryDTO.dir_path + "/"
										+ file.fileDTO.new_file);
						if (file.fileDTO.com_new_file != null
								&& file.fileDTO.com_new_file != "")
							_this
									.ftpDownloadFile(
											file.serverInfoDTO,
											local_pathfile
													+ file.fileDTO.com_org_file,
											file.directoryDTO.dir_path
													+ "/"
													+ file.fileDTO.com_new_file);
					}
					res = local_pathfile + file.fileDTO.org_file;
				} else {
					if (document.XRapidView != undefined) {
						if (file.bPrint == "true") {
							document.XRapidView.HasPrintPriv = true;
						} else {
							document.XRapidView.HasPrintPriv = false;
						}
					}

				}
			}

        },
        error : function(xhr, status, error) {
              alert("Error !");
        }
    });

	return res;
};

_Openfile.prototype.downloadFileByFC = function(edmsURL, f_category, f_category_id,
		local_pathfile, kind) {
	_contextPathURL = 'http://' + edmsURL + _contextPath ;

	var fileDTO = {
			'f_category' : f_category ,
			'f_category_id' : f_category_id ,
			'kind' : kind
	} ;

	return this.downloadFile(fileDTO, local_pathfile);
};

_Openfile.prototype.downloadFileByFile_id = function(file_id, local_pathfile,
		kind) {
	var fileDTO = new Hash();
	fileDTO.set("file_id", file_id);
	fileDTO.set("kind", kind);
	return this.downloadFile(fileDTO, local_pathfile);
};

_Openfile.prototype.httpDownloadFile = function(file_id, localfile, bComfile) {
	this.isFtp();
/*	document.OpenFtp.bShowProgress = 1;
	document.OpenFtp.bCrtLocalFolder = 1;
	document.OpenFtp.nRetries = 20;
	document.OpenFtp.bFileEncryption = 1;*/
	this.createLocalFolder(this.parsingFilePath(localfile));
	var url = _contextPathURL + "/edms/fi/dwFile.action?"
			+ "fileDTO.file_id=" + file_id;
	if (bComfile) {
		if (!this.HttpDownAction(url, localfile))
			return "";
		url = _contextPathURL + "/edms/fi/dwFile.action?"
				+ "fileDTO.file_id=" + file_id + "&isCom=Y";
		if (!this.HttpDownAction(url, bComfile))
			return "";
	} else {
		if (!this.HttpDownAction(url, localfile))
			return "";
	}

};

_Openfile.prototype.HttpDownAction = function(a, a2) {
	var ret = 0;
	var b = document.OpenFtp.HttpDownloadFile(a, a2);
	var size = document.OpenFtp.fsLocalGetFileSize(a2);
	if (size <= 7)
		b = false;

	while (ret < this.retryCnt && !b) {
		b = document.OpenFtp.HttpDownloadFile(a, a2);
		var size = document.OpenFtp.fsLocalGetFileSize(a2);
		if (size <= 7)
			b = false;
		ret++;
	}
	if (ret >= this.retryCnt)
		return false;
	return true;
};

_Openfile.prototype.ftpDownloadFile = function(server, localfile, ftpfile) {
	this.isFtp();
	document.OpenFtp.bUsePASVMode = server.bUsePASVMode;
	document.OpenFtp.bShowProgress = server.bShowProgress;
	document.OpenFtp.bCrtLocalFolder = server.bCrtLocalFolder;
	document.OpenFtp.bCrtRemoteFolder = server.bCrtRemoteFolder;
	document.OpenFtp.nRetries = server.nRetries;
	document.OpenFtp.bFileEncryption = server.bFileEncryption;
	document.OpenFtp.strAddress = server.ip;
	document.OpenFtp.strLogin = server.id;
	document.OpenFtp.strPassword = server.pass;
	document.OpenFtp.nPort = server.port;
	ret = document.OpenFtp.Connect();
	this.createLocalFolder(this.parsingFilePath(localfile));
	ret = this.ftpDownAction(localfile, ftpfile);
	ret = document.OpenFtp.Disconnect();
	return ret;
};
_Openfile.prototype.ftpDownAction = function(a, a2) {
	var ret = 0;
	var b = document.OpenFtp.DownloadFile(a, a2);
	var size = document.OpenFtp.fsLocalGetFileSize(a2);
	if (size <= 7)
		b = false;

	while (ret < this.retryCnt && !b) {
		b = document.OpenFtp.DownloadFile(a, a2);
		var size = document.OpenFtp.fsLocalGetFileSize(a2);
		if (size <= 7)
			b = false;
		ret++;
	}
	if (ret >= this.retryCnt)
		return false;
	return true;
};




_Openfile.prototype.UploadFile = function(filepathname, svrfilepathname, server) {
	this.isFtp();
	var ret = true;
	if (server == null || server == "") {
		var url = _contextPathURL + "/UploadFile";
		document.OpenFtp.bShowProgress = 1;
		document.OpenFtp.bCrtLocalFolder = 1;
		document.OpenFtp.nRetries = 20;
		document.OpenFtp.bFileEncryption = 1;
		if (!document.OpenFtp
				.HttpUploadFile(url, filepathname, svrfilepathname)) {
			alert($L('W_FAIL'));
			ret = false;
		}
		var url = _contextPath + "/edms/fi/seFileSize.action";
		var param = "fileDTO.new_file=" + svrfilepathname;
		new Ajax.Request(url, {
			method : 'POST',
			parameters : param.toQueryParams(),
			asynchronous : false,
			onSuccess : function(response) {
				if (response.responseText != ""
						&& response.responseText == document.OpenFtp
								.fsLocalGetFileSize(filepathname))
					ret = true;
				else
					ret = false;
			}
		});
	} else {
		document.OpenFtp.bUsePASVMode = server.bUsePASVMode;
		document.OpenFtp.bShowProgress = server.bShowProgress;
		document.OpenFtp.bCrtLocalFolder = server.bCrtLocalFolder;
		document.OpenFtp.bCrtRemoteFolder = server.bCrtRemoteFolder;
		document.OpenFtp.nRetries = server.nRetries;
		document.OpenFtp.bFileEncryption = server.bFileEncryption;
		document.OpenFtp.strAddress = server.ip;
		document.OpenFtp.strLogin = server.id;
		document.OpenFtp.strPassword = server.pass;
		document.OpenFtp.nPort = server.port;

		ret = document.OpenFtp.Connect();
		this.createRemoteFolder(this.parsingFilePath(svrfilepathname));
		ret = document.OpenFtp.UploadFile(filepathname, svrfilepathname);
		ret = document.OpenFtp.Disconnect();
	}
	return ret;
};

_Openfile.prototype.parsingExt = function(filename) {
	extension = filename.substring(filename.lastIndexOf(".") + 1,
			filename.length);
	return extension.toLowerCase();
};
_Openfile.prototype.getComplexFileName = function(file) {
	this.isFtp();
	if (file == "")
		return;
	var ext = this.parsingExt(file);
	var comfile = "";
	if (ext == "dwg")
		comfile = file.substring(0, file.lastIndexOf(".") + 1) + "tif";
	else if (ext == "tif")
		comfile = file.substring(0, file.lastIndexOf(".") + 1) + "dwg";
	else
		return "";
	if (!OpenFtp.fsLocalExistFile(comfile))
		return "";
	return comfile;
};
_Openfile.prototype.fsLocalExistFile = function(filename) {
	this.isFtp();
	return OpenFtp.fsLocalExistFile(filename);
};
_Openfile.prototype.existFile = function(filename) {
	this.isFtp();
	if (!OpenFtp.fsLocalExistFile(filename))
		return false;
	return true;
};
_Openfile.prototype.createLocalFolder = function(sLocalPath) {
	this.isFtp();
	sLocalPath = sLocalPath.replace(new RegExp("/", "g"), "\\");
	var paths = sLocalPath.split("\\");
	var checkpath = "";
	for ( var i = 0; i < paths.length; i++) {
		if (paths[i] == null || paths[i] == "")
			continue;
		checkpath += paths[i] + "\\";
		document.OpenFtp.fsLocalCreateFolder(checkpath);
	}
};

_Openfile.prototype.createRemoteFolder = function(sRemotePath) {
	this.isFtp();
	sRemotePath = sRemotePath.gsub('/', '\\');
	var paths = sRemotePath.split("\\");
	var checkpath = "";
	for ( var i = 0; i < paths.length; i++) {
		if (paths[i] == null || paths[i] == "")
			continue;
		checkpath += "/" + paths[i];
		document.OpenFtp.fsRemoteCreateFolder(checkpath);
	}
};
_Openfile.prototype.fsSelectFolder = function(input) {
	this.isFtp();
	input.value = document.OpenFtp.fsSelectFolder(input.value);
};
_Openfile.prototype.fsSelMultiplefiles = function() {
	this.isFtp();
	var files = document.OpenFtp.fsSelMultiplefiles();
	if (files.length - 1 == files.lastIndexOf('|'))
		files = files.substring(0, files.lastIndexOf('|'));
	if (files == "")
		return null;
	var file = files.split('|');
	if (file[0] == "")
		return null;
	for ( var i = 0; i < file.length; i++) {
		var com = this.getComplexFileName(file[i]);
		var ext = this.parsingExt(com);
		if (ext == "tif") {
			var at = file.indexOf(com);
			if (at != -1)
				file.splice(at, 1);
		} else if (ext == "dwg") {
			var at = file.indexOf(file[i]);
			if (at != -1)
				file.splice(at, 1);
			at = file.indexOf(com);
			if (at == -1)
				file.push(com);
		}
	}
	return file;
};

_Openfile.prototype.addFileByFileName = function(file, method) {
	if (file == null)
		return;
	var ext = this.parsingExt(file);
	if (ext == "com") {
		file = file.substring(0, file.lastIndexOf(".") + 1) + "dwg";
	}

	var comfile = this.getComplexFileName(file);

	var url = _contextPath + "/edms/fi/inUpload.action";
	var param = new Hash();
	param.set("fileDTO.org_file", this.parsingFileName(file));
	param.set("fileDTO.com_org_file", this.parsingFileName(comfile));

	var _this = this;
	var ret = true;
	new Ajax.Request(url, {
		method : 'POST',
		parameters : param.toQueryString(),
		asynchronous : false,
		onSuccess : function(response) {
			var json = eval('(' + response.responseText + ')');
			if (!json.directoryDTO.dir_path.endsWith('/')){
				json.directoryDTO.dir_path = json.directoryDTO.dir_path + "/";
			}
			var svrfilepathname = json.directoryDTO.dir_path
					+ json.fileDTO.new_file;
			var comsvrfilepathname = json.directoryDTO.dir_path
					+ json.fileDTO.com_new_file;
			ret = _this.UploadFile(file, svrfilepathname, json.serverInfoDTO);
			if (comfile != "")
				ret &= _this.UploadFile(comfile, comsvrfilepathname,
						json.serverInfoDTO);
			if (ret)
				method(json.fileDTO.file_id);
			else
				method("");
		}
	});
	return ret;
};


_Openfile.prototype.deleteTemp = function() {
	this.isFtp();
	var temp_path = document.OpenFtp.GetAppTemp("");
	document.OpenFtp.fsLocalDeleteFile(temp_path + "*.*", true);

};

_Openfile.prototype.getTempPath = function(filename) {
	this.isFtp();
	if (filename == undefined)
		return document.OpenFtp.GetAppTemp("");
	return document.OpenFtp.GetAppTemp(filename);
};

_Openfile.prototype.parsingFileName = function(filename) {
	filename = filename.replace(new RegExp("/", "g"), "\\");
	filename = filename.substring(filename.lastIndexOf("\\") + 1,
			filename.length);
	return filename;
};

_Openfile.prototype.parsingFilePath = function(filename) {
	filename = filename.replace(new RegExp("/", "g"), "\\");
	filename = filename.substring(0, filename.lastIndexOf("\\") + 1);
	return filename;
};
_Openfile.prototype.fsLocalGetFileSize = function(filepathname) {
	if (this.existFile(filepathname))
		return document.OpenFtp.fsLocalGetFileSize(filepathname);
	return 0;
};


/*
 * 	created by WooChul Jung. 2014.05.26
 * 	Add file by other system for attachment, but those files should be searched in OpenEDM
 *
 * parameter
 * 	filePath : Local file absolute path
 * 	user id : user id
 * 	com_code = "HDO , HDOT , HCT, .."
 * 	method : call back function (if successful, doc_seq return. Otherwise , empty string return)
*/
_Openfile.prototype.addFileForAttachment = function(edmsURL, filePath, userid, com_code, mr_code, mr_file_id, method) {
//	var openEDM_contextPath = "/edims" ;
	edmsURL = 'edims.oilbank.co.kr';		//yoo
	this.isFtp();
	if (filePath == null)
		return;
	_contextPathURL = 'http://' + edmsURL;
	var ext = this.parsingExt(filePath);
	if (ext == "com") {
		filePath = filePath.substring(0, filePath.lastIndexOf(".") + 1) + "dwg";
	}
	var comfile = this.getComplexFileName(filePath);
	var url = _contextPathURL + "/edms/fi/other/inUploadForAttachment.action";		

/*	var sendingUrl = _contextPath + "/edms/fi/other/inUploadForAttachment.action?" + "fileDTO.org_file=" + this.parsingFileName(filePath) + "&" +
		"fileDTO.com_org_file=" + this.parsingFileName(comfile) + "&" +
		"fileDTO.i_emp_id=" + userid + "&" +
		"fileDTO.com_code=" + com_code + "&" +
		"fileDTO.mr_code=" + mr_code + "&" +
		"fileDTO.mr_file_id=" + mr_file_id ;*/

	var data = {
			'fileDTO.org_file' : this.parsingFileName(filePath) ,
			'fileDTO.com_org_file' : this.parsingFileName(comfile) ,
			'fileDTO.i_emp_id' : userid,
			'fileDTO.com_code' : com_code ,
			'fileDTO.mr_code' : mr_code,
			'fileDTO.mr_file_id' : mr_file_id
	} ;

	var _this = this;
	var ret = true;

    jQuery.ajax({
        type:"POST",
        url: url ,
        data : data,
        async : false,
        dataType:"json",
        success : function(data) {
			var json = data ;
			if (!json.directoryDTO.dir_path.endsWith('/')){
				json.directoryDTO.dir_path = json.directoryDTO.dir_path + "/";
			}
			var svrfilepathname = json.directoryDTO.dir_path
					+ json.fileDTO.new_file;
			var comsvrfilepathname = json.directoryDTO.dir_path
					+ json.fileDTO.com_new_file;
			ret = _this.UploadFileForAttachment(filePath, svrfilepathname, json.serverInfoDTO);
			if (comfile != "")
				ret &= _this.UploadFileForAttachment(comfile, comsvrfilepathname,
						json.serverInfoDTO);
ret=true;
			if (ret)
				method(json.doc_seq);
			else
				method("");
        },
        error : function(xhr, status, error) {
              alert("Error !");
        }
    });

	return ret;
};


/*
 * requester_id : request id
 * receiverid : id for enrolling document
 * mr_code : mr code
 * doc_seqList : doc_seq list
 * remark : comment
*/
_Openfile.prototype.requestRegister = function(requser_id, receiverid, mr_code, doc_seqList, remark, method) {
//	var _contextPath = "/edims" ;

	var url = _contextPath + "/edms/wf/Request.action";

	var data = {
			'workFlowFormDTO.doc_seq' : doc_seqList ,
			'workFlowFormDTO.workflowDTO.wf_kind' : "RMR" ,
			'workFlowFormDTO.workflowAppDTO.remark' : remark ,
			'workFlowFormDTO.appList.app_user' : requser_id ,
			'workFlowFormDTO.documentDTO.mr_code' : mr_code ,
			'workFlowFormDTO.workflowDTO.requser_id' : requser_id ,
			'workFlowFormDTO.workflowAppDTO.app_user' : requser_id ,
			'workFlowFormDTO.workflowDTO.emp_id' : receiverid
	} ;

    jQuery.ajax({
        type:"POST",
        url: url ,
        data : data,
        dataType:"text",
        success : function(data) {
			var json = eval('(' + data + ')');
			method(json.workflowDTO.work_seq) ;
        },
        error : function(xhr, status, error) {
              alert("Error !");
        }
    });

};


_Openfile.prototype.UploadFileForAttachment = function(filepathname, svrfilepathname, server) {
//	var openEDM_contextPath = "/edims" ;

	this.isFtp();
	var ret = true;
	if (server == null || server == "") {
		var url = _contextPathURL + "/UploadFile";
		document.OpenFtp.bShowProgress = 1;
		document.OpenFtp.bCrtLocalFolder = 1;
		document.OpenFtp.nRetries = 20;
		document.OpenFtp.bFileEncryption = 1;
		if (!document.OpenFtp
				.HttpUploadFile(url, filepathname, svrfilepathname)) {
			alert("Download Error !");
			ret = false;
		}
		var url = _contextPath + "/edms/fi/other/seFileSizeForAttachment.action";

		var data = {
				'fileDTO.new_file' : svrfilepathname
		} ;

	    jQuery.ajax({
	        type:"POST",
	        url: url ,
	        data : data,
	        dataType:"text",
	        success : function(data) {
	        	var text = data.result ;
				if (text != ""
					&& text == document.OpenFtp
							.fsLocalGetFileSize(filepathname))
				ret = true;
			else
				ret = false;
	        },
	        error : function(xhr, status, error) {
	              alert("Error !");
	        }
	    });
	} else {
		document.OpenFtp.bUsePASVMode = server.bUsePASVMode;
		document.OpenFtp.bShowProgress = server.bShowProgress;
		document.OpenFtp.bCrtLocalFolder = server.bCrtLocalFolder;
		document.OpenFtp.bCrtRemoteFolder = server.bCrtRemoteFolder;
		document.OpenFtp.nRetries = server.nRetries;
		document.OpenFtp.bFileEncryption = server.bFileEncryption;
		document.OpenFtp.strAddress = server.ip;
		document.OpenFtp.strLogin = server.id;
		document.OpenFtp.strPassword = server.pass;
		document.OpenFtp.nPort = server.port;

		ret = document.OpenFtp.Connect();
		this.createRemoteFolder(this.parsingFilePath(svrfilepathname));
		ret = document.OpenFtp.UploadFile(filepathname, svrfilepathname);
		ret = document.OpenFtp.Disconnect();
	}
	return ret;
};
