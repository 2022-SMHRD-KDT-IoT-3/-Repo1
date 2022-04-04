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
<link href="css/NewFile.css" rel="stylesheet">

<!-- Custom styles for this page -->
<link href="vendor/datatables/dataTables.bootstrap4.min.css"
	rel="stylesheet">

</head>

<body id="page-top">

	<%
	MemberDTO info = (MemberDTO) session.getAttribute("info");
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
									<%
									} else {
									%> user name <%
									}
									%>
							</span>
						</a> 
						
						
						<!-- Dropdown - User Information -->
						
						<%if(info !=null) {%>
							<div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="userDropdown">
								<a class="dropdown-item" href="editinfo.jsp"> <i
									class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
									Settings
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="#" data-toggle="modal"
									data-target="#logoutModal"> <i
									class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
									Logout
								</a> 
							</div></li>
							
							
							
							<% } else { %>
							<div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="userDropdown">
								<a class="dropdown-item" href="#" data-toggle="modal"
									data-target="#logoutModal"> <i
									class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
									Login
								</a> 
							</div></li>
							
							<% } %>

					</ul>

				</nav>
				<!-- @@ End of Topbar -->

				<!-- 1. start Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<h1 class="h3 mb-2 text-gray-800">📝 게시판</h1>
					<p class="mb-4">
					<li>관리자가 공지사항을 게시합니다</li>
					<li>사용시 불편사항을 해당 게시판에 작성하시면 관리자가 답변해줍니다.</li> <a target="_blank"
						href="https://datatables.net">official DataTables
						documentation</a> 데이터 테이블 사용설명서
					</p>

					<!-- 2. DataTales Example -->

					<form action="WriteBoardService.do?mb_id=<%=info.getMb_id()%>"
						method="post" enctype="multipart/form-data">
						<div class="card shadow mb-4">
							<div class="card-header py-3">
								<h6 class="m-0 font-weight-bold text-primary">게시글 입력하기</h6>
								<div class="card-body">
									<div class="table-responsive">
										<table hight="100%" width="100%">
											<tr>
												<td>제목</td>
												<td><input type="text" name="title" size="20"
													style="width: 100%;"></td>
											</tr>
											<tr>
												<td>작성자</td>
												<%
												if (info != null) {
												%>
												<td><%=info.getMb_id()%></td>
												<%
												} else {
												%>
												<td><input type="text" name="mb_id" size="20"
													style="width: 100%;"></td>
												<%
												}
												%>
											</tr>
											<tr>
												<td colspan="2">내용</td>
											</tr>
											
											<tr>
												<td colspan="2"><textarea name="content" rows="10"
														style="width: 100%; resize: none;"></textarea></td>
											</tr>
											<tr>
												<td colspan="2"><input name="file" type="file"
													style="float: right;"></td>
											</tr>
											<tr>
												<td colspan="2"><input class="btn btn-primary" type="reset" value="초기화">
													<input class="btn btn-primary" type="submit" value="업로드"></td>
											</tr>
										</table>
									</div>
								</div>
							</div>
						</div>

					</form>

				</div>

			</div>

		</div>
	</div>

</body>
</html>