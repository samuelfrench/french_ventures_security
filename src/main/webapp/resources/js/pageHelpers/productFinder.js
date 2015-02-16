/*
 * Page TODO:
 * 1. Add a window width change handler to re-size the data table
 * 2. General styling - maybe add jQueryUI style or ask Xiao for recommendations
 * 3. add support for other column ordering buttons (only price is implemented now)
 */

//GLOBALS
var pTable;
var addDialog;
var statusMessage;
var loadSuccess;
var ERROR_TIMEOUT = 15000; //1000ms = 1s
//END GLOBAL



$(document).ready(function(){
	loadSuccess = false;
	$('#adminAddButton').click(openAddDialog);
	statusMessage = $('#statusMessage');
	createTable();
	createAddDialog();
	//updateTable();
	
	
});

/* BEGIN MAIN DATA TABLE FUNCTIONALITY */

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
/* END MAIN DATA TABLE FUNCTIONALITY */
function createAddDialog()
{
	addDialog = $( "#addProductModal" ).dialog({
	    autoOpen: false,
	    autoHeight: true,
	    width: 700,
	    modal: true,
	    buttons: {
	      "Add Product To Database": addProduct,
	      Cancel: function() {
	    	  addDialog.dialog('close');
	      }
	    },
	    close: function() {
	      form[ 0 ].reset();
	     addDialog.find('.ui-state-error').removeClass( "ui-state-error" );
	     $('#formErrors').html('');
	    }
	  });
	
	form = addDialog.find( "form" ).on( "submit", function( event ) {
	      event.preventDefault();
	      addProduct();
	    });
}

function addProduct()
{
	var inputError = false;
	$('.required').each(function(){
		if($(this).val().length < 1)
			{
			inputError = true;
			$(this).addClass('ui-state-error');
			
			}
	});
	if(inputError){
		$('#formErrors').html("<h2>Please populate all required fields.</h2>")
	} else {
		var data = addDialog.find( "form" ).serializeArray();
		console.log(data);
		$.post('/french_ventures_secure/rest/product/insert',data,function(res){
			alert(res.success);
		});
	}
}

function openAddDialog(){
	addDialog.dialog('open');
}
