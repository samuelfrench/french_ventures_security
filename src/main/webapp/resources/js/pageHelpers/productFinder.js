$(document).ready(function(){
	$("#customerProductTable").dataTable({
		//"processing": true,
		//"serverSide": true,
		//"deferRender": true,
		"data": null,
	});
	
	$.get("/french_ventures_secure/view/product/customerProductTable", function(data)
			{
		console.log(data);
			});
	
	var productRequest = $.ajax({
		url: "/french_ventures_secure/view/product/customerProductTable",
		type: "GET",
		data: {
			start: 1,
			length: 10,
		},

	});
	
	productRequest.done(function(msg){
		console.log(msg);
	});
	
	request.fail(function(jqXHR, textStatus){
		alert("Request failed: " + textStatus);
	});
	
})