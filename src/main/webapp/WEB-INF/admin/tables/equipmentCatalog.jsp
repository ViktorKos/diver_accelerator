<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<jsp:include page="../template/head.jsp" />

<body class="d-flex flex-column min-vh-100">
<jsp:include page="../template/header.jsp" />
<div class="container-fluid">
    <div class="row">
        <jsp:include page="../template/nav.jsp" />
        <content class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <h2 class="pt-3">Equipment</h2>

            <div class="table-wrapper-scroll-y my-custom-scrollbar table-responsive">

                <table class="table tableFixHead table-striped">
                    <thead>
                    <tr>
                        <th>Id<a href="/admin/equipmentCatalog/1" class="btn btn-sm "><i class="bi bi-sort-down text-light"></i></a></th>
                        <th>Name<a href="/admin/equipmentCatalog/2" class="btn btn-sm "><i class="bi bi-sort-down text-light"></i></a></th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <c:if test="${!equipmentCatalogList.isEmpty()}">
                    <tbody>
                    <c:forEach var="equipment" items="${equipmentCatalogList}" varStatus="i">
                        <tr>
                            <td>${equipment.id}</td>
                            <td>${equipment.name}</td>

                            <td><a href="/admin/${equipment.id}/edit_equipmentCatalog/" class="btn btn_edit"><i class="bi bi-vector-pen "></i></a>
                                <a href="/admin/${equipment.id}/delete_equipmentCatalog" class="btn btn_delete"><i class="bi bi-trash "></i></a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                </c:if>
                <c:if test="${equipmentCatalogList.isEmpty()}">
                    <h2><em><center>no Equipment</center></em></h2>
                </c:if>

            </div>
            <center>
                <button onclick="document.location = '/admin/add_equipmentCatalog';" type="button" class="btn my-2 btn_add">
                    Create
                </button>
            </center>
        </content>

    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>

</body>
</html>