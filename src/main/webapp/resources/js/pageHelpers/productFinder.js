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
		"bProcessing": true,
		 "bServerSide": true,
		 "bFilter": true,
         "sPaginationType": "full_numbers",
		"bDeferRender": true,
		 "iDisplayLength": 2,
		 'sAjaxSource': "/french_ventures_secure/view/product/customerProductTable",

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



