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
            <h2 class="pt-3">Schedule. DiverPro: ${diverPro.surname} ${diverPro.name.charAt(0)}.${diverPro.middle_name.charAt(0)}.</h2>

            <div class="table-wrapper-scroll-y my-custom-scrollbar table-responsive">
                <c:if test="${!diverPro.schedules.isEmpty()}">

                <table class="table tableFixHead table-striped">
                    <thead>
                    <tr>
                        <th>Day<a href="/admin/${diverPro.id}/schedule/2" class="btn btn-sm "><i class="bi bi-sort-down text-light"></i></a></th>
                        <th>Time<a href="/admin/${diverPro.id}/schedule/3" class="btn btn-sm "><i class="bi bi-sort-down text-light"></i></a></th>
                        <th>Action</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach var="schedule" items="${diverPro.schedules}" varStatus="i">
                        <tr>
                            <td>${schedule.dayName}</td>
                            <td>${schedule.time}</td>
                            <td><a href="/admin/${diverPro.id}/${schedule.id}/edit_schedule/" class="btn btn_edit"><i class="bi bi-vector-pen "></i></a>
                                <a href="/admin/${diverPro.id}/${schedule.id}/delete_schedule" class="btn btn_delete"><i class="bi bi-trash "></i></a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                </c:if>
                <c:if test="${diverPro.schedules.isEmpty()}">
                    <h2><em><center>no Schedule</center></em></h2>
                </c:if>

            </div>

            <center>
                <button onclick="document.location = '/admin/${diverPro.id}/add_schedule';" type="button" class="btn my-2 btn_add">
                    Create
                </button>
            </center>
        </content>

    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>



</body>

</html>