var showXML = {
	init : function() {
		showXML.initCodeMirror();
		showXML.generate();
	},

	initCodeMirror : function() {
		var mime = "text/html";
		window.editor = CodeMirror.fromTextArea(document
				.getElementById('xmlContent'), {
			mode : mime,
			indentWithTabs : true,
			smartIndent : true,
			lineNumbers : true,
			matchBrackets : true,
			autofocus : true,
			readOnly : true
		});
	},
	
	generate : function() {
		var options = {
			type : 'GET',
			url : 'generate',
			success : function(data, textStatus, jqXHR) {
				var xmlContent = data;
				editor.setValue(xmlContent);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log("fail to generate xml");
			}
		};

		$.ajax(options);
	}
};

$(document).ready(function() {
	showXML.init();
});