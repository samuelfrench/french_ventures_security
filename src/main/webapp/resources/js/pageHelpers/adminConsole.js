$(document).ready(documentReady);

function documentReady()
{
	loadDataTable();
}

function loadDataTable()
{
	//TODO - Add TableTools
	    $('#productTable').dataTable( {
	        "dom": '<"top"iflp<"clear">>rt<"bottom"iflp<"clear">>',
	        "lengthMenu": [[25, 50, 100, 200, -1], [25, 50, 100, 200, "All"]],
	       
	        
	    } );
	    
	    $('#example tbody').on( 'click', 'tr', function () {
	        $(this).toggleClass('selected');
	    } );
}