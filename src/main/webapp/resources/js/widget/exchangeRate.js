
$(document).ready(function(){
	getExchangeRate();
});

function getExchangeRate(){
	$.get("/french_ventures_secure/misc/exchangeRate", function(data){
		$('#exchangeRate').html("Current Exchange Rate: <br />" + data.usdrate + 
				"USD<img class='widget_flag widget_flag_usa' src='/french_ventures_secure/resources/image/flag/USA_min.png' /> / " + 
				data.cnyrate + "CNY <img class='widget_flag widget_flag_prc' src='/french_ventures_secure/resources/image/flag/PRC_min.png' />" +
						"<br /> (Updated hourly - See legal for full disclaimer)");
		resizeWidgetFlag('widget_flag_usa');
		resizeWidgetFlag('widget_flag_prc');
	}).fail(function(){
		$('#exchangeRate').html("Try again later.");
	}).always(function(){
		console.log("info: exchange rate loaded.");
	});
}

function resizeWidgetFlag(imgClass)
{
	$('.' + imgClass).css('width','18px');
	$('.' + imgClass).css('height','12px');
}