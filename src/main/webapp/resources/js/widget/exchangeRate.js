
$(document).ready(function(){
	getExchangeRate();
});

function getExchangeRate(){
	$.get("/french_ventures_secure/misc/exchangeRate", function(data){
		$('#exchangeRate').html(data.usdrate + "USD/" + data.cnyrate + "CNY");
	}).fail(function(){
		$('#exchangeRate').html("Try again later.");
	}).always(function(){
		console.log("info: exchange rate loaded.");
	});
}