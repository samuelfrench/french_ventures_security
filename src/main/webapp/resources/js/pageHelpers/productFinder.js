//GLOBALS
var pTable;
//END GLOBAL



$(document).ready(function(){

	createTable();
	
	
});

function createTable(){
	pTable = $("#customerProductTable").dataTable({
		"processing": true,
		 "serverSide": true,
		 "sorting": false,
         "paging": true,
		"deferRender": true,
		 'ajax': "/french_ventures_secure/rest/product/customerProductTable",
		 'width': '100%',

		columns: [
		           { data: 'resourceURL', width:'40%' },
		           { data: 'retailPriceUSD', width: '10%'},
		           { data: null, width:'10%' },
		           { data: null, width:'10%' },
		           { data: 'weightInGrams', width:'10%'},
		           {data: "qtyPerUnit", width:'10%'},
		           {data: "unitOnHand", width:'10%'},
		           
		       ]
	});
}



