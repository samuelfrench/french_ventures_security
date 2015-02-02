//GLOBALS
var pTable;
//END GLOBAL



$(document).ready(function(){

	createTable();
	//updateTable();
	
	
});

function createTable(){
	pTable = $("#customerProductTable").dataTable({
		"bProcessing": true,
		 "bServerSide": true,
		 "bFilter": false,
         "paging": true,
		"bDeferRender": true,
		 'ajax': "/french_ventures_secure/rest/product/customerProductTable",

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



