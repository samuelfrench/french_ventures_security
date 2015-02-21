
$(document).ready(function(){
	getExchangeRate();
});

function getExchangeRate(){
	$.get("/french_ventures_secure/misc/exchangeRate", function(data){
		$('#exchangeRate').html("Current Exchange Rate: <br />" + data.usdrate + 
				"USD<img class='widget_flag widget_flag_usa' src='/french_ventures_secure/resources/image/flag/USA_min.png' />/ " + 
				data.cnyrate + "CNY<img class='widget_flag widget_flag_prc' src='/french_ventures_secure/resources/image/flag/PRC_min.png' />" +
						"/" + data.gbprate + "GBP<img class='widget_flag widget_flag_uk' src='/french_ventures_secure/resources/image/flag/UK_min.png' />" +
								"/" + data.eurrate + "EUR<img class='widget_flag widget_flag_eur' src='/french_ventures_secure/resources/image/flag/EUR_min.png' /><br /> Last updated: " + data.displayUpdated + "(See legal for full disclaimer)");
	}).fail(function(){
		$('#exchangeRate').html("Try again later.");
	}).always(function(){
		console.log("info: exchange rate loaded.");
	});
}
