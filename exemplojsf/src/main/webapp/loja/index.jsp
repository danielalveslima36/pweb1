<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>${initParam.nomeapp}</title>

<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="css/sb-admin-2.min.css" rel="stylesheet">
<style>
.order-table{   
	border-collapse:collapse;
}

.order-table-header{
	text-align:center;
	background:none repeat scroll 0 0 #E5E5E5;
	border-bottom:1px solid #BBBBBB;
	padding:16px;
}

.order-table-odd-row{
	text-align:center;
	background:none repeat scroll 0 0 #FFFFFFF;
	border-top:1px solid #BBBBBB;
}

.order-table-even-row{
	text-align:center;
	background:none repeat scroll 0 0 #F9F9F9;
	border-top:1px solid #BBBBBB;
}
</style>
</head>
<body id="page-top">
	<f:view>
		<!-- Page Wrapper -->
		<div id="wrapper">

			<jsp:include page="pages/menu.jsp" />

			<!-- Content Wrapper -->
			<div id="content-wrapper" class="d-flex flex-column">

				<!-- Main Content -->
				<div id="content">

					<jsp:include page="pages/topo.jsp" />

					<!-- Begin Page Content -->
					<div class="container-fluid">

						<!-- Page Heading -->
						<div class="d-sm-flex align-items-center row">
							<h1 class="h3 mb-0 text-gray-800">Produtos</h1>
						</div>
					
						<h:form>
							<h:commandButton action="#{produtosBean.abrirCadastro}" value="Cadastrar Produto" />
						</h:form>			

						<h:dataTable var="produto" value="#{produtosBean.produtos}"
							styleClass="order-table" headerClass="order-table-header"
							rowClasses="order-table-odd-row,order-table-even-row">
							<h:column>
								<f:facet name="header"><h:outputText value="Nome" /></f:facet>
								
								<h:outputText value="#{produto.nome}" />
							</h:column>
							<h:column>
								<f:facet name="header"><h:outputText value="Preço" /></f:facet>
								<h:outputText value="#{produto.preco}" />
							</h:column>
							<h:column>
								<f:facet name="header"><h:outputText value="Data de Cadastro"/></f:facet>
								<h:outputText value="#{produto.dataCadastro}" />
							</h:column>
							<h:column>
								<f:facet name="header"><h:outputText value="Quantidade em Estoque" /></f:facet>
								<h:outputText value="#{produto.estoque}" />
							</h:column>
							
						</h:dataTable>
					</div>

					<!-- /.container-fluid -->

				</div>
				<!-- End of Main Content -->

				<%@include file="pages/rodape.jsp"%>

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
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Deseja
							realmente sair?</h5>
						<button class="close" type="button" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">Ã—</span>
						</button>
					</div>
					<div class="modal-body">Clique no botão "Sair" abaixo somente
						se você tem certeza que quer sair.</div>
					<div class="modal-footer">
						<button class="btn btn-secondary" type="button"
							data-dismiss="modal">Cancel</button>
						<a class="btn btn-primary"
							href="./api?comando=UsuariosController&acao=logout">Sair</a>
					</div>
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
		<script src="vendor/chart.js/Chart.min.js"></script>

		<!-- Page level custom scripts -->
		<script src="js/demo/chart-area-demo.js"></script>
		<script src="js/demo/chart-pie-demo.js"></script>
	</f:view>
</body>

</html>
