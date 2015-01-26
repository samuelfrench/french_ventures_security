$(document).ready(function(){
	$("#customerProductTable").dataTable({
		"processing": true,
		"serverSide": true,
		"deferRender": true,
		"ajax": {
			"url": "/french_ventures_secure/view/product/customerProductTable",
			"data": function(d){
				d.length = 10;
				d.start = 10;
			},
		}
	});
})