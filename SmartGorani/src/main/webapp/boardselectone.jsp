<%@page import="Model.ReplyDTO"%>
<%@page import="Model.ReplyDAO"%>
<%@page import="Model.BoardDTO"%>
<%@page import="Model.BoardDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<head>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Smart Grani - Board</title>

<!-- Custom fonts for this template -->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/sb-admin-2.min.css" rel="stylesheet">

<!-- Custom styles for this page -->
<link href="vendor/datatables/dataTables.bootstrap4.min.css"
	rel="stylesheet">

</head>

<body id="page-top">

	<%
	MemberDTO info = (MemberDTO) session.getAttribute("info");
	int num = Integer.parseInt(request.getParameter("num"));
	BoardDAO dao = new BoardDAO();
	BoardDTO dto = dao.boardSelectOne(num);
	ReplyDAO rdao = new ReplyDAO();
	ReplyDTO rdto = rdao.replySelectOne(num);
	%>

	<!-- @ strat : Page Wrapper -->
	<div id="wrapper">
		<!-- Sidebar -->
		<ul
			class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
			id="accordionSidebar">

			<!-- Sidebar - Brand -->
			<a href="main.jsp"> <img src="img/goraniface.png"
				style="width: 25%; display: block; margin: 0px auto; margin-top: 20px"></a>



			<a
				class="sidebar-brand d-flex align-items-center justify-content-center"
				href="main.jsp">
				<div class="sidebar-brand-icon rotate-n-15"></div>
				<div class="sidebar-brand-text mx-3">SmartGorani</div>
			</a>

			<!-- Divider -->
			<hr class="sidebar-divider my-0">

			<!-- Nav Item - Dashboard -->
			<li class="nav-item active"><a class="nav-link" href="main.jsp">
					<i class="fas fa-fw fa-tachometer-alt"></i> <span>메인</span>
			</a></li>

			<!-- Divider -->
			<hr class="sidebar-divider my-0">

			<!-- Nav Item - Charts -->
			<li class="nav-item"><a class="nav-link" href="check.html">
					<i class="fas fa-fw fa-chart-area"></i> <span>조회</span>
			</a></li>

			<!-- Divider -->
			<hr class="sidebar-divider my-0">

			<!-- Nav Item - Utilities Collapse Menu -->
			<li class="nav-item"><a class="nav-link collapsed"
				href="power_control.jsp"> <i class="fas fa-fw fa-wrench"></i> <span>전기제어</span>
			</a></li>

			<!-- Divider -->
			<hr class="sidebar-divider my-0">

			<!-- Nav Item - Tables -->
			<li class="nav-item"><a class="nav-link" href="board.jsp"> <i
					class="fas fa-fw fa-table"></i> <span>게시판</span>
			</a></li>

			<!-- Divider -->
			<hr class="sidebar-divider my-0">

			<!-- Nav Item - Pages Collapse Menu -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapsePages"
				aria-expanded="true" aria-controls="collapsePages"> <i
					class="fas fa-fw fa-cog"></i> <span>환경설정</span>
			</a>
				<div id="collapsePages" class="collapse"
					aria-labelledby="headingPages" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">my page</h6>
						<a class="collapse-item" href="port.jsp">보유 제품 현황</a> <a
							class="collapse-item" href="editinfo.jsp">회원 정보 수정</a>
						<div class="collapse-divider"></div>
					</div>
				</div></li>

			<!-- Divider -->
			<hr class="sidebar-divider my-0">
			<!-- @ end -->

			<!-- Divider -->
			<hr class="sidebar-divider">
			<a href="main.jsp"><img src="img/logo4.png"
				style="width: 70%; display: block; margin: 0px auto; margin-top: 20px">
			</a>
		</ul>
		<!-- @ end -->


		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- @@ start Topbar -->
				<nav
					class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

					<!-- Sidebar Toggle (Topbar) -->
					<form class="form-inline">
						<button id="sidebarToggleTop"
							class="btn btn-link d-md-none rounded-circle mr-3">
							<i class="fa fa-bars"></i>
						</button>
					</form>



					<!-- Topbar Navbar -->
					<ul class="navbar-nav ml-auto">

	
						<div class="topbar-divider d-none d-sm-block"></div>

						<!-- Nav Item - User Information -->
						<li class="nav-item dropdown no-arrow"><a
							class="nav-link dropdown-toggle" href="#" id="userDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <%
 if (info != null) {
 %> <span class="mr-2 d-none d-lg-inline text-gray-600 small"> <%=info.getMb_name()%>
							</span> <%
 } else {
 %> <span class="mr-2 d-none d-lg-inline text-gray-600 small">user
									name<%
 }
 %>
							</span>
						</a> <!-- Dropdown - User Information -->
							<div
								class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="userDropdown">
								<a class="dropdown-item" href="#"> <i
									class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> Profile
								</a> <a class="dropdown-item" href="#"> <i
									class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
									Settings
								</a> <a class="dropdown-item" href="#"> <i
									class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
									Activity Log
								</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="#" data-toggle="modal"
									data-target="#logoutModal"> <i
									class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
									Logout
								</a>
							</div></li>
					</ul>

				</nav>
				<!-- @@ End of Topbar -->

				<!-- 1. start Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<h1 class="h3 mb-2 text-gray-800">📝 게시판</h1>
					<p class="mb-4">
					<li>게시글을 확인하는 페이지입니다.</li>
					</p>


					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">게시글 작성</h6>
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-bordered" id="dataTable" width="100%"
									cellspacing="0">
									<tr>
										<td>제목</td>
										<td><%=dto.getQna_title()%></td>
									</tr>
									<tr>
										<td>작성자</td>
										<td><%=dto.getMb_id()%></td>
									</tr>
									<tr>
										<td colspan="2">내용</td>
									</tr>
									<tr>
										<td colspan="2"><%=dto.getContent()%> <img alt=""
											src="file/<%=dto.getFile()%>"></td>
									</tr>


								</table>
								<%
								if (rdto != null) {
								%>
								<br>
								<table class="table table-bordered" id="dataTable" width="100%"
									cellspacing="0">
									<h3>답글내용</h3>
									<tr>
										<td>작성자</td>
										<td>관리자</td>
									</tr>
									<tr>
										<td colspan="2">내용</td>
									</tr>
									<tr>
										<td colspan="2"><%=rdto.getREPLY_CONTENT()%> <img alt=""
											src="file/<%=rdto.getREPLY_FILE()%>"></td>
									</tr>
								</table>
								<%
								} else {
								%>
								<h1>답변대기중</h1>
								<%
								}
								%>
								<a class="btn btn-primary" href="board.jsp">목록으로</a>
								<a class="btn btn-primary" href="boardDeleteService.do?qna_seq=<%=dto.getQna_seq()%>">삭제</a>
								
								<% if(info.getMb_id().equals(dto.getMb_id())){ %>
								<a class="btn btn-primary" href="boardUpdate.jsp?num=<%=dto.getQna_seq()%>&mb_id=<%=dto.getMb_id()%>">수정</a>
								<%} %>
							</div>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<footer class="sticky-footer bg-white">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright &copy; Smart Gorani</span>
					</div>
				</div>
			</footer>
			<!-- End of Footer -->

		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>
	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<%
				if (info != null) {
				%>
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">로그아웃</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">로그아웃 하시겠습니까?</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">취소</button>
					<a class="btn btn-primary" href="LogoutService.do">확인</a>
				</div>
				<%
				} else {%>
					
					<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">로그인</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">로그인 하시겠습니까?</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">취소</button>
					<a class="btn btn-primary" href="login.html">확인</a>
				</div>
					
					
					
			<%	}
				%>
			</div>
		</div>
	</div>


	<!-- Bootstrap core JavaScript-->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="js/sb-admin-2.min.js"></script>

	<!-- Page level plugins -->
	<script src="vendor/datatables/jquery.dataTables.min.js"></script>
	<script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

	<!-- Page level custom scripts -->
	<script src="js/demo/datatables-demo.js"></script>
</body>
</html>