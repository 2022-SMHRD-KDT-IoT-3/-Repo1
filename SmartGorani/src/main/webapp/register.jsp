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

<title>SG - Register</title>

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

	<div class="container">

		<div class="card o-hidden border-0 shadow-lg my-5">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
					<div class="col-lg-7">
						<div class="p-5">
							<div class="text-center">
								<h1 class="h4 text-gray-900 mb-4">회원가입 type 지정해서 넣어야함</h1>
							</div>
							<form class="user" action="RegisterService.do" method="post">
								<div class="form-group">
									<input type="text" class="form-control form-control-user"
										id="exampleLastName" name="name" placeholder="사용자 이름을 입력하세요.">
								</div>

								<div class="form-group">
									<input type="text" class="form-control form-control-user"
										id="exampleInputEmail" name="id" placeholder="아이디를 입력해주세요.">
									<div class="my-2"></div>
									<a href="#" class="btn btn-success btn-icon-split" id="idcheck_btn">
                                        <span class="icon text-white-50">
                                            <i class="fas fa-check"></i>
                                        </span>
                                        <span class="text"> 아이디 중복체크</span>
                                    </a>
                                    <p id="idcheck"></p>
                                    <div class="my-2"></div>
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
								<input type="submit" class="btn btn-primary btn-user btn-block" value="회원가입">
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

	</div>
	
	<!-- 스크립트 파일 작성 -->
	<script type="text/javascript">
		// 0. 아이디 중복체크 버튼을 클릭했을 때
		$('#idcheck_btn').on('click', function() {
			// 1. 입력한 email 가져오기
			let id = $('input[name=id]').val()
			console.log(id)

			// 2. ajax로 email 보내기 (IdCheckServiceCon)
			$.ajax({
				url : 'IDcheckService.do', /* 어디로 보낼지*/
				data : { /* 입력한 email data 보내기*/
					email : email
				},
				dataType : "text", /* 중복체크 결과값 text로 받아오기*/
				success : function(result) {
					alert('성공')
					if (result == 'false') {
						// 중복 x
						$('#idcheck').html('중복되는 아이디가 없습니다.')
					} else {
						// 중복 O
						$('#idcheck').html('아이디가 중복됩니다.')
					}
				},
				error : function() {
					alert('실패')
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