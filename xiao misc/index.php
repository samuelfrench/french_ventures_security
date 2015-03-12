<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>Libscan_2</title>
	<!--.js files-->
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="http://cdn.datatables.net/1.10.5/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.7.0/underscore-min.js"></script>
    <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
  	<script type="text/javascript" src="http://jquery-datatables-row-grouping.googlecode.com/svn/trunk/media/js/jquery.dataTables.rowGrouping.js"></script>  
    <!--.css files-->
    <link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/1.10.5/css/jquery.dataTables.min.css">
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
  
</head>

<body>
	
	<button id="expandall_btn" class="btn" type="button">Expand All Groups</button>
	<button id="closeall_btn"  class="btn" type="button">Close All Groups</button>
       
	<div id="tabs">
    	<ul>
        	<li class="active"><a href="#mira_table">Mira/Cetus</a></li>
            <li><a href="#vesta_table">Vesta</a></li>
            <li><a href="#tukey_table">Tukey</a></li>
        </ul>
		<!--button id="expandall_btn" type="button">Expand All</button-->
        
        <div id="mira_table">
            <table id="mira" class="display responsive stripe" >
                <thead>
                    <tr>
                        <th></th>
                        <th>Category</th>
                        <th>Name</th>
                        <th>Version</th>
                        <th>Compiler</th>
                        <th>Location</th>
                    </tr>
                </thead>
            </table>
        </div>
        <div id="vesta_table">
            <table id="vesta" class="display responsive stripe" >
                <thead>
                    <tr>
                        <th></th>
                        <th>Category</th>
                        <th>Name</th>
                        <th>Version</th>
                        <th>Compiler</th>
                        <th>Location</th>
                    </tr>
                </thead>
            </table>
        </div>
        <div id="tukey_table">
            <table id="tukey" class="display responsive stripe" >
                <thead>
                    <tr>
                        <th></th>
                        <th>Category</th>
                        <th>Name</th>
                        <th>Version</th>
                        <th>Compiler</th>
                        <th>Location</th>
                    </tr>
                </thead>
            </table>
        </div>
   	</div>
    
    <!--JavaScript code-->
    <script>
		$(document).ready( function () {
	
			var json_files=['data/mira.json','data/vesta.json','data/tukey.json'];
			
			$.when($.getJSON(json_files[0]), $.getJSON(json_files[1]), $.getJSON(json_files[2])).done(function(mira_json, vesta_json, tukey_json){
				
				//Process json file
				var formatted_mira = [];
				var formatted_vesta = [];
				var formatted_tukey = [];
				var mira_data = mira_json[0].data;
				var vesta_data = vesta_json[0].data;
				var tukey_data = tukey_json[0].data;
				
				mira_data.forEach( function(element, index) {
					formatted_mira[index] = _.object(mira_json[0].fields, element);
				});
				vesta_data.forEach( function(element, index) {
					formatted_vesta[index] = _.object(vesta_json[0].fields, element);
				});
				tukey_data.forEach( function(element, index) {
					formatted_tukey[index] = _.object(tukey_json[0].fields, element);
				});
				
				create_table(formatted_mira, '#mira');
				create_table(formatted_vesta, '#vesta');
				create_table(formatted_tukey, '#tukey');
	
				
			});// end of $.when function
			
			//Tabbed by machine
			$('#tabs').tabs( {
				"activate": function(event, ui) {
					var table = $.fn.dataTable.fnTables(true);
					if ( table.length > 0 ) {
						$(table).dataTable().fnAdjustColumnSizing();
					}		
				}
					
			});//end of tabbed by machine 
			
			
			function create_table(table_data, table_id) {
				var table = $(table_id).DataTable( {
					'data': table_data,
					'columns': [
						{ 'className': 'details-control',
						  'orderable': false,
						  'data':      null,
						  'defaultContent': ''
						},
						{ 'data': 'Category' },
						{ 'data': 'Name' },
						{ 'data': 'Version' },
						{ 'data': 'Compiler' },
						{ 'data': 'Location' }
						
					],
					'order': [[1, 'asc']],
			    	'bLengthChange': false, 
			    	'bPaginate': false
				}); 

				//Row grouping
				$(table_id).dataTable().rowGrouping({	
			    	iGroupingColumnIndex: 1,
			    	bExpandableGrouping: true,
			    	asExpandedGroups: [""],

				});
				
				// Add event listener for opening and closing details
				var id = table_id + ' tbody';
				$(id).on('click', 'td.details-control', function () {
					var tr = $(this).closest('tr');
					var row = table.row( tr );
			 		
					if ( row.child.isShown() ) {
						// This row is already open - close it
						row.child.hide();
						tr.removeClass('shown');
						tr.addClass('hidden');
					}
					else {
						// Open this row
						row.child( format(row.data()) ).show();
						tr.removeClass('hidden');
						tr.addClass('shown');
					}
				});//end of details event listener
				
				// Add event listener for Expand all button
				$("#expandall_btn").on('click',function () {
					//$('tr:not(.shown)').find('.details-control').click();
					$(table_id).find('.collapsed-group').trigger('click');
					
				});
				// Add event listener for Close all button
				$("#closeall_btn").on('click',function () {
					$('tr:not(.hidden)').find('.details-control').click();
					$(table_id).find('.expanded-group').trigger('click');
					
				});
			
			}//end of function create_table
		});//end of document ready function

		/* Formatting function for row details - modify as you need */
		function format ( d ) {
			// `d` is the original data object for the row
			return '<table cellpadding="0" cellspacing="0" border="0">'+
				'<tr>'+
					'<td>Build Date:</td>'+
					'<td>'+d.Build_date+'</td>'+
				'</tr>'+
				'<tr>'+
					'<td>Driver:</td>'+
					'<td>'+d.Driver+'</td>'+
				'</tr>'+
				'<tr>'+
					'<td>Softenv key/path:</td>'+
					'<td>'+d.Softenv_key_path+'</td>'+
				'</tr>'+
				'<tr>'+
					'<td>Url:</td>'+
					'<td><a href="'+d.Url+'">'+d.Url+'</a></td>'+
				'</tr>'+
				'<tr>'+
					'<td>Notes:</td>'+
					'<td>'+d.Notes+'</td>'+
				'</tr>'+
			'</table>';
		}


	</script>
    
    <style>

    body {
    	font-size: 14px;
    	font-family: Arial;
    	width: 700px;

    }

    .btn {
    	font-size: 10px !important;
    }

    td.group {
    	background-color: #ede8e0 !important;
    	border-left-width: 10px;
    	border-left-style: solid;
    	border-color: transparent;
    	text-transform: capitalize;

    }

	.expanded-group{
		text-indent: 2%;
    	background: url('http://www.mcs.anl.gov/~xiaowang/Libscan/img/details_close.png') no-repeat left center;
    	background-size: 2%;
    	cursor: pointer;
	}
	.collapsed-group{
		text-indent: 2%;
		background: url('http://www.mcs.anl.gov/~xiaowang/Libscan/img/details_open.png') no-repeat left center;
		background-size: 2%;
		cursor: pointer;
	}

	td.details-control {
    	background: url('http://www.mcs.anl.gov/~xiaowang/Libscan/img/details_open.png') no-repeat center center;
    	background-size: 40%;
    	border-left-width: 10px;
    	border-left-style: solid;
    	border-color: transparent;
    	cursor: pointer;
	}
	
	tr.shown td.details-control {
		background: url('http://www.mcs.anl.gov/~xiaowang/Libscan/img/details_close.png') no-repeat center center;
    	background-size: 40%;
    	border-left-width: 10px;
    	border-left-style: solid;
    	border-color: transparent;
		cursor: pointer;
	}
	
	
	#closeall_btn{
		position: relative;
		width:120px;
		height:30px;
		border-radius:20px;
		text-align:center;
		font-size:14px;
		margin: 10px;
	}
	#expandall_btn{
		position: relative;
		width:120px;
		height:30px;
		border-radius:20px;
		text-align:center;
		font-size:14px;
		margin: 10px;
	}
	#tabs{ 
	    background: transparent; 
	    border: none; 

	} 
	#tabs a{
		font-weight: bold !important;
	}
	#tabs .ui-widget-header { 
	    background: transparent; 
	    border: none; 
	    border-bottom: 1px solid #c0c0c0; 
	    -moz-border-radius: 0px; 
	    -webkit-border-radius: 0px; 
	    border-radius: 0px; 
	} 
	#tabs .ui-tabs-nav .ui-state-default { 
	    background: transparent; 
	    border: none; 
	} 
	#tabs .ui-tabs-nav .ui-state-active { 
	    background: transparent url('http://www.mcs.anl.gov/~xiaowang/Libscan/img/uiTabsArrow.png') no-repeat bottom center; 
	    border: none; 
	} 
	#tabs .ui-tabs-nav .ui-state-default a { 
	    color: #c0c0c0; 
	} 
	#tabs .ui-tabs-nav .ui-state-active a { 
	    color: #459e00; 
	}

	table.dataTable{
		border: none !important;
		text-align: left !important;
	}

	.dataTable table {
		width: 100%;
	}

	table.dataTable thead th {
		font-weight: normal !important; 
	}
	
	</style>
</body>
</html>