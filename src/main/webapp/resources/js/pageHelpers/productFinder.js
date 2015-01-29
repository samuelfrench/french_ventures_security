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
})