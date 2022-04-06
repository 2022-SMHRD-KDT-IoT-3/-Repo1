<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src="../js/jquery-3.3.1.min.js"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	google.charts.load('current', {
		'packages' : [ 'corechart' ]
	});

	
	google.charts.setOnLoadCallback(function() {

		setInterval(columnChart2(), 30000);

	});

	function columnChart2(arrayList) {

		var dataTable = google.visualization.arrayToDataTable(arrayList);

		var options = {
			title : 'My Daily Activities'
		};

		var chart = new google.visualization.PieChart(document
				.getElementById('piechart'));

		chart.draw(dataTable, options);
	}
	
	$(document).ready(function() {

		$.ajax({

			url : 'json_jsp/jsonmonthusage.jsp',

			success : function(result) {

				columnChart2(result);

			}

		});

	});
</script>
</head>
<body>
	<div id="piechart" style="width: 900px; height: 500px;"></div>
</body>
</html>