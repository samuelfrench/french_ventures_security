//GLOBALS
var pTable;
//END GLOBAL



$(document).ready(function(){

	
	$.get("/french_ventures_secure/view/product/customerProductTable", function(data)
			{
		console.log(data);
			});
	createTable();
	//updateTable();
	
	
});

function createTable(){
	pTable = $("#customerProductTable").dataTable({
		"processing": true,
		"serverSide": true,
		"deferRender": true,
		"ajax": "/french_ventures_secure/view/product/customerProductTable",
		//data: null,
		
		columns: [
		           { data: 'resourceURL' },
		           { data: 'retailPriceUSD'},
		           { data: null },
		           { data: null },
		           { data: 'weightInGrams'},
		           {data: "qtyPerUnit"},
		           {data: "unitOnHand"},
		           
		       ]
	});
}


function updateTable(){
	var productRequest = $.ajax({
		"url": "/french_ventures_secure/view/product/customerProductTable",
		"type": "GET",
		//"data": {
			//"start": 1,
			//"length": 10,
		//},
		"dataType": "json",

	});
	
	productRequest.done(function(data){
		pTable.fnAddData(data)
	});
	
	productRequest.fail(function(jqXHR, textStatus){
		alert("Request failed: " + textStatus);
	});
}



