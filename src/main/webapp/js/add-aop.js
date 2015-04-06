var addAOP = {
	init : function() {
		addAOP.getMethods(addAOP.fullFillMethodList);
	},

	getMethods : function(callback) {
		var options = {
			type : 'GET',
			url : 'signatures',
			dataType : 'json',
			success : function(data, textStatus, jqXHR) {
				var methods = data;
				callback(methods);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log("fail to get methods");
			}
		};

		$.ajax(options);
	},

	fullFillMethodList : function(methods) {
		var html = '';
		for(var i=0;i<methods.length;i++){
			var method = methods[i];
			html += '<li data="'+method+'" class="list-group-item">'+method+'</li>';
		}
		$("#methods").html(html);
		$("#methods li").unbind('click');
		$("#methods li").bind('click', function(){
			$("#methods li").removeClass("active");
			$(this).addClass("active");
			$("#pointcut").val($(this).attr('data'));
		});
	}
};

$(document).ready(function() {
	addAOP.init();
});