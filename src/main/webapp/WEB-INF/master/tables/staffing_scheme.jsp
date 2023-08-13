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
            <h2 class="pt-3">1 Staffing scheme ${diveCenter.name}</h2>

            <div class="table-wrapper-scroll-y my-custom-scrollbar table-responsive">
                <c:if test="${!staffingSchemeList.isEmpty()}">
                <table class="table tableFixHead table-striped">
                    <thead>
                    <tr>
                        <th>Specialization<a href="/master/${diveCenter.id}/staffing_scheme/2" class="btn btn-sm "><i class="bi bi-sort-down text-light"></i></a></th>
                        <th>Staff cnt</th>
                        <th>Limit cnt</th>
                        <th>Дії</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach var="staffingScheme" items="${staffingSchemeList}" varStatus="i">
                        <tr>
                            <td>${staffingScheme.specialization.name}</td>
                            <td>${staffingScheme.maxCount}</td>
                            <td>${staffingScheme.count}</td>

                            <td><a href="/master/${diveCenter.id}/${staffingScheme.number}/edit_staffing_scheme/" class="btn btn_edit"><i class="bi bi-vector-pen "></i></a>
                                <a href="/master/${diveCenter.id}/${staffingScheme.number}/delete_staffing_scheme" class="btn btn_delete"><i class="bi bi-trash "></i></a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                </c:if>
                <c:if test="${staffingSchemeList.isEmpty()}">
                    <h2><em><center>no Scheme</center></em></h2>
                </c:if>

            </div>

            <center>
                <button onclick="document.location = '/master/${diveCenter.id}/add_staffing_scheme';" type="button" class="btn my-2 btn_add">
                    Create
                </button>
            </center>
        </content>

    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>



</body>

</html>