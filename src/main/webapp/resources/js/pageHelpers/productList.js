$(document).ready(documentReady);

function documentReady()
{
	console.log("debug: document ready callback");
	$('.productListingContainer').click(function(){
		$('#selectedProduct').load('/french_ventures_secure/view/product/getDetails/' + $(this).attr("id"));
	});
}
