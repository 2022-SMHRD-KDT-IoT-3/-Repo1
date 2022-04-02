<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="shortcut icon" href="./img/favi.ico">
<title>Join</title>

<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="css/sb-admin-2.min.css" rel="stylesheet">

<!-- font -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap"
	rel="stylesheet">

<style>
body, h1, p, span, a {
	font-family: 'Nanum Gothic', cursive;
}
</style>

</head>

<body class="bg-gradient-primary">

	<div class="container">

		<div class="card o-hidden border-0 my-5">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div style="width: 100%">

					<div class="p-5">
						<div class="text-center">
							<h1 class="h4 text-gray-900 mb-4">회원가입</h1>
						</div>
						<form class="user" action="RegisterService.do" method="post">
							<div class="form-group">
								<input type="text" class="form-control form-control-user"
									id="exampleInputEmail" name="id" placeholder="아이디를 입력해주세요.">
								<div class="my-2"></div>
								<a href="#" class="btn btn-success btn-icon-split"
									id="idcheck_btn"> <span class="icon text-white-50">
										<i class="fas fa-check"></i>
								</span> <span class="text"> 아이디 중복체크</span> <a id="idcheck"></a>
								</a>
							</div>
							<div class="form-group row">
								<div class="col-sm-6 mb-3 mb-sm-0">
									<input type="password" class="form-control form-control-user"
										id="exampleInputPassword" name="pw" placeholder="비밀번호 입력">
								</div>
								<div class="col-sm-6">
									<input type="password" class="form-control form-control-user"
										id="exampleRepeatPassword" placeholder="비밀번호 확인">
								</div>
							</div>
							<div class="form-group">
								<input type="text" class="form-control form-control-user"
									id="exampleLastName" name="name" placeholder="사용자 이름을 입력하세요.">
							</div>
							<div class="form-group">
								<input type="text" class="form-control form-control-user"
									id="exampleLastName" name="serial" placeholder="포트시리얼을 입력하세요.">
							</div>
							<div class="form-group">
								구분 &nbsp; &nbsp; &nbsp; &nbsp; <input type="radio" name="type"
									value="HOME"><label>가정용</label> &nbsp; &nbsp; &nbsp; <input
									type="radio" name="type" value="FARMING_INDUSTRY"><label>농업용</label>
								&nbsp; &nbsp; &nbsp; <input type="radio" name="type"
									value="INDUSTRY"><label>산업용</label>
							</div>
							<input type="submit" class="btn btn-primary btn-user btn-block"
								value="회원가입" onclick="functionAlert()" id="join">
						</form>
						<hr>
						<div class="text-center">
							<a class="small" href="forgot-password.html">비밀번호를 잊으셨나요?</a>
						</div>
						<div class="text-center">
							<a class="small" href="login.html">로그인하러 가기!</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 스크립트 파일 작성 -->
	<script type="text/javascript">
		// 0. 아이디 중복체크 버튼을 클릭했을 때
		$('#idcheck_btn').on('click', function() {
			// 1. 입력한 id 가져오기
			let id = $('input[name=id]').val()
			console.log(id)

			// 2. ajax로 id 보내기 (IdCheckServiceCon)
			$.ajax({
				url : 'IDcheckService.do', /* 어디로 보낼지*/
				data : { /* 입력한 email data 보내기*/
					id : id
				},
				dataType : "text", /* 중복체크 결과값 text로 받아오기*/
				success : function(result) {
					if (result == 'false') {
						// 중복 x
						$('#idcheck').html('&emsp; 중복되는 아이디가 없습니다.')
					} else {
						// 중복 O
						$('#idcheck').html('&emsp; 아이디가 중복됩니다.')
					}
				},
				error : function() {
				}
			})

		})

		// 회원가입 알림창
		let joinalert = document.getElementById("join")
		joinalert.addEventListener('click', function() {
			if(confirm("회원가입을 하시겠습니까?")==true){
				alert("회원가입이 완료되었습니다.")
				document.form.submit()
				
			}
		})
	</script>

	<!-- Bootstrap core JavaScript-->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="js/sb-admin-2.min.js"></script>

</body>

</html>