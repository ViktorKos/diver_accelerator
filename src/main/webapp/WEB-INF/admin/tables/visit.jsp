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
            <h1 class="pt-4">MaxDeep=${diver.max_deep}m! Diver: ${diver.surname} ${diver.name.charAt(0)}.${diver.middle_name.charAt(0)}.  </h1>

            <div class="table-wrapper-scroll-y my-custom-scrollbar table-responsive">
                <c:if test="${!visitsList.isEmpty()}">
                <table class="table tableFixHead table-striped">
                    <thead>
                    <tr>
                        <th>Date<a href="/admin/${diver.id}/visit/2" class="btn btn-sm "><i class="bi bi-sort-down text-light"></i></a></th>
                        <th>Level<a href="/admin/${diver.id}/visit/3" class="btn btn-sm "><i class="bi bi-sort-down text-light"></i></a></th>
                        <th>Instructor<a href="/admin/${diver.id}/visit/4" class="btn btn-sm "><i class="bi bi-sort-down text-light"></i></a></th>
                        <th>Time<a href="/admin/${diver.id}/visit/5" class="btn btn-sm "><i class="bi bi-sort-down text-light"></i></a></th>
                        <th>Status<a href="/admin/${diver.id}/visit/6" class="btn btn-sm "><i class="bi bi-sort-down text-light"></i></a></th>
                        <th>Action</th>


                    </tr>
                    </thead>


                    <tbody>
                    <c:forEach var="visit" items="${visitsList}" varStatus="i">
                        <tr>
                            <td>${visit.date}</td>
                            <td>${visit.diver.diveLevel}</td>
                            <td>${visit.diverPro.surname} ${visit.diverPro.name.charAt(0)}.${visit.diverPro.middle_name.charAt(0)}</td>
                            <td>${visit.schedule.time}</td>
                            <td>
                                <c:if test="${visit.status}"><i class="bi bi-check-square text-success"></i></c:if>
                                <c:if test="${!visit.status}"><i class="bi bi-dash-square text-danger"></i> </c:if>
                            </td>
                            <td><a href="/admin/${diver.id}/${visit.number}/edit_visit/" class="btn btn_edit"><i class="bi bi-vector-pen "></i></a>
                                <a href="/admin/${diver.id}/${visit.number}/delete_visit" class="btn btn_delete"><i class="bi bi-trash "></i></a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                </c:if>

                <c:if test="${visitsList.isEmpty()}">
                    <h2><em><center>no Visits</center></em></h2>
                </c:if>
            </div>

            <center>
                <button onclick="document.location = '/admin/${diver.id}/add_visit';" type="button" class="btn my-2 btn_add">
                    Create
                </button>
            </center>
        </content>

    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>



</body>

</html>