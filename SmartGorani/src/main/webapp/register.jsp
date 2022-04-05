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

<title>회원가입</title>

<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body class="bg-gradient-primary">

	<div class="container" style="width: 50%">

		<div class="card o-hidden border-0 shadow-lg my-5">
			<div class="row justify-content-center">
				<!-- Nested Row within Card Body -->
				<div class="col-lg-9">
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
										id="pw" name="pw" placeholder="비밀번호 입력">
								</div>
								<div class="col-sm-6">
									<input type="password" class="form-control form-control-user"
										id="pwcheck" name="pwcheck" placeholder="비밀번호 확인"> <span
										class="icon text-white-60"> </span> <span class="text">
									</span> <a id="pwcheck2"></a>

								</div>
							</div>
							<div class="form-group">
								<input type="text" class="form-control form-control-user"
									id="exampleLastName" name="name" placeholder="사용자 이름을 입력하세요.">
							</div>
							<div class="form-group">
								구분 &nbsp; &nbsp; &nbsp; &nbsp; <input type="radio" name="type"
									value="HOME"><label>가정용</label> &nbsp; &nbsp; &nbsp; <input
									type="radio" name="type" value="FARMING_INDUSTRY"><label>농업용</label>
								&nbsp; &nbsp; &nbsp; <input type="radio" name="type"
									value="INDUSTRY"><label>산업용</label>
							</div>
							<div class="form-group">
								<input type="text" class="form-control form-control-user"
									id="exampleLastName" name="serial" placeholder="포트시리얼을 입력하세요.">
							</div>
							<input type="submit" class="btn btn-primary btn-user btn-block"
								id="pwc" value="회원가입">
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
		function pwCheck() {

			let pw = $('input[name=pw]').val()
			console.log(pw)
			let pwcheck = $('input[name=pwcheck]').val()
			console.log(pwcheck)

			// 2. ajax로 id 보내기 (IdCheckServiceCon)
			$.ajax({
				url : 'PWcheckService.do', /* 어디로 보낼지*/
				data : { /* 입력한 email data 보내기*/
					pw : pw,
					pwcheck : pwcheck
				},
				dataType : "text", /* 중복체크 결과값 text로 받아오기*/
				success : function(result) {
					if (result == 'false') {
						// 중복 x
						$('#pwcheck2').html('&emsp; 비밀번호가 일치하지 않습니다.')
					} else {
						// 중복 O
						$('#pwcheck2').html('&emsp; 비밀번호가 일치합니다.')
					}
				},
				error : function() {
				}
			})

		}

		$('#pwcheck').on('input', pwCheck)

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