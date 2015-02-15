//GLOBALS
var pTable;
var statusMessage;
var loadSuccess;
var ERROR_TIMEOUT = 3000; //1000ms = 1s
//END GLOBAL



$(document).ready(function(){
	loadSuccess = false;
	statusMessage = $('#statusMessage');
	createTable();
	//updateTable();
	
	
});

function createTable(){
	startLoad();
	pTable = $("#customerProductTable").dataTable({
		"dom":'lprtip',
		"lengthMenu": [[25, 50, 500], [25, 50, 500]],
		'bAutoResize': true,
		"bProcessing": true,
		 "bServerSide": true,
		 "bFilter": true,
         "paging": true,
		"bDeferRender": true,
		 'ajax': "/french_ventures_secure/rest/product/customerProductTable",

		columns: [
		           { data: 'imageHtml'},
		           { data: 'cost'},
		           { data: 'weightInGrams'},
		           {data: "qtyPerUnit"},
		           {data: "unitOnHand"},
		           
		       ],
		       
		 "initComplete": function(settings, json) {
		    $('#customerProductTable_length > label').append(" per page");
			 loadComplete();
		   },
	});
}

function startLoad()
{
	statusMessage.html("<h1 style='padding-left: 100px;'>Loading...</h1>");
	setTimeout(loadError, ERROR_TIMEOUT);
}

function loadComplete()
{
	statusMessage.html("");
    loadSuccess = true;
}

function loadError()
{
	if(!loadSuccess)
		{
		statusMessage.html("An unknown ajax error has occurred. Please try again later.");
		}
}

/*
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
*/

