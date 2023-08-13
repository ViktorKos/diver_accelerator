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
            <h2 class="pt-3">Divers of ${diveCenter.name} </h2>
            <c:if test="${!diverProList.isEmpty()}">

                <div class="col-xl-5 col-lg-5 col-md-7 col-sm-5 pt-1 px-2 pb-2 mx-auto">
                    <form class="d-flex" id="search" method="GET" action="/master/${diveCenter.id}/diveCenter_diverPro/1">
                        <input class="form-control me-2 shadow bg-body rounded rounded-pill border-0" id="search_input" type="number" name="telephone_number" placeholder="phone"  required>
                        <button class="btn btn_find mx-2" id="search_button" type="submit" name="search">Search</button>
                        <a href="/master/${diveCenter.id}/diveCenter_diverPro/1" class="btn btn_find_all">All</a>
                    </form>
                </div>

                <div class="table-wrapper-scroll-y my-custom-scrollbar table-responsive">

                    <table class="table tableFixHead table-striped">
                        <thead>
                        <tr>
                            <th>Id<a href="/master/${diveCenter.id}/diveCenter_diverPro/1" class="btn btn-sm "><i class="bi bi-sort-down text-light"></i></a></th>
                            <th>Name<a href="/master/${diveCenter.id}/diveCenter_diverPro/2" class="btn btn-sm "><i class="bi bi-sort-down text-light"></i></a></th>
                            <th>Sex<a href="/master/${diveCenter.id}/diveCenter_diverPro/3" class="btn btn-sm "><i class="bi bi-sort-down text-light"></i></a></th>
                            <th>Phone<a href="/master/${diveCenter.id}/diveCenter_diverPro/4" class="btn btn-sm "><i class="bi bi-sort-down text-light"></i></a></th>
                            <th>Certification<a href="/master/${diveCenter.id}/diveCenter_diverPro/5" class="btn btn-sm "><i class="bi bi-sort-down text-light"></i></a></th>
                            <th>Notes</th>
                            <th>Visits</th>
                            <th>Action</th>

                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="diverPro" items="${diverProList}" varStatus="i">
                            <tr>
                                <td>${diverPro.id}</td>
                                <td>${diverPro.surname} ${diverPro.name.charAt(0)}.${diverPro.middle_name.charAt(0)}.</td>
                                <td>${diverPro.sex== 0 ? "M" : "F" }</td>
                                <td>0${diverPro.telephone_number}</td>
                                <td>${diverPro.specialization.name}</td>
                                <td>${diverPro.getActiveVisits().size()}</td>
                                <td>${diverPro.visits.size()}</td>

                                <td>
                                    <a href="/master/${diverPro.id}/diverPro_info/" class="btn btn_look_at"><i class="bi bi-eye"></i></a>
                                    <a href="/master/${diveCenter.id}/${diverPro.id}/delete_diverPro_from_diveCenter" class="btn btn_delete"><i class="bi bi-trash"></i></a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                </div>
            </c:if>
            <c:if test="${diverProList.isEmpty()}">
                <h2><em><center>no divers</center></em></h2>
            </c:if>

            <c:if test="${canAdd}">
                <center>
                    <button onclick="document.location = '/master/${diveCenter.id}/add_diverPro_to_diveCenter';" type="button" class="btn my-2 btn_add">
                        Add
                    </button>
                </center>
            </c:if>

            <c:if test="${!canAdd}">
                <center>
                    <button onclick="document.location = '/master/${diveCenter.id}/staffing_scheme/1';" type="button" class="btn my-2 btn_add">
                        Create employee scheme
                    </button>
                </center>
            </c:if>

        </content>

    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>



</body>

</html>