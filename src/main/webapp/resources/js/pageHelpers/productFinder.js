/*
 * Page TODO:
 * 1. Make thumbnails smaller to fit more rows on screen
 * 2. Add a window width change handler to re-size the data table
 * 3. General styling - maybe add jQueryUI style or ask Xiao for recommendations
 */

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
	pTable = $("#customerProductTable").DataTable({
		"dom":'lpfrtip',
		"lengthMenu": [[10, 50, 200, 500], [10, 50, 200, 500]],
		'bAutoResize': true,
		"bProcessing": true,
		 "bServerSide": true,
		 "bFilter": true,
         "paging": true,
		"bDeferRender": true,
		 'ajax': "/french_ventures_secure/rest/product/customerProductTable",

		columns: [
		           { data: 'imageHtml'},
		           {data: "productCode"},
		           { data: 'cost'},
		           { data: 'weightInGrams'},
		           {data: "qtyPerUnit"},
		           {data: "unitOnHand"},
		           {data: "inStock"},
		           
		       ],
		       
		 "initComplete": function(settings, json) {
		    $('#customerProductTable_length > label').append(" per page");
		   },
	});
	pTable.on( 'preXhr.dt', startLoad);
	pTable.on('draw.dt', loadComplete);
}

function startLoad()
{
	//TODO - fix this styling
	statusMessage.html("<h1 style='padding-left: 400px; padding-top:200px;'>Loading...</h1>");
	setTimeout(loadError, ERROR_TIMEOUT);
}

function loadComplete()
{
	statusMessage.html("");
    loadSuccess = true;
    $('.tableImageItem').unbind('hover');
    $('.tableImageItem').hover(function(){
    	$(this).animate({width: 640, height:460}, 50);
		$(this).attr('src', '/french_ventures_secure/resources/image/product/compressed/' + $(this).attr('id'));
	},
	function(){
		$(this).animate({width: 240, height:180}, 100);
		$(this).attr('src', '/french_ventures_secure/resources/image/product/thumb/' + $(this).attr('id'));
	});
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

