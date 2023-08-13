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
            <h2 class="pt-3">Visitors</h2>
            <c:if test="${!diversList.isEmpty()}">

                <div class="col-xl-5 col-lg-5 col-md-7 col-sm-5 pt-1 px-2 pb-2 mx-auto">
                    <form class="d-flex" id="search" method="GET" action="/admin/divers/1">
                        <input class="form-control me-2 shadow bg-body rounded rounded-pill border-0" id="search_input" type="number" name="telephone_number" placeholder="phone number"  required>
                        <button class="btn btn_find mx-2" id="search_button" type="submit" name="search">Search</button>
                        <a href="/admin/divers/1" class="btn btn_find_all">All</a>
                    </form>
                </div>

                <div class="table-wrapper-scroll-y my-custom-scrollbar table-responsive">

                    <table class="table tableFixHead table-striped">
                        <thead>
                        <tr>
                            <th>id<a href="/admin/divers/1" class="btn btn-sm "><i class="bi bi-sort-down text-light"></i></a></th>
                            <th>name<a href="/admin/divers/2" class="btn btn-sm "><i class="bi bi-sort-down text-light"></i></a></th>
                            <th>cert level<a href="/admin/divers/3" class="btn btn-sm "><i class="bi bi-sort-down text-light"></i></a></th>
                            <th>phone<a href="/admin/divers/4" class="btn btn-sm "><i class="bi bi-sort-down text-light"></i></a></th>
                            <th>visits</th>
                            <th>user</th>
                            <th>appointment</th>
                            <th>action</th>

                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="diver" items="${diversList}" varStatus="i">
                            <tr>
                                <td>${diver.id}</td>
                                <td>${diver.surname} ${diver.name.charAt(0)}.</td>
                                <td>${diver.diveLevel}</td>
                                <td>0${diver.telephone_number}</td>
                                <td><a href="/admin/${diver.id}/visit/1" class="btn btn_find"><i class="bi bi-journal-bookmark"></i></a></td>
                                <td>
                                    <c:if test="${diver.user.username==null}"><a href="/admin/${diver.id}/set_user_for_diver/" class="btn btn_add"><i class="bi bi-person-bounding-box"></i></a> </c:if>
                                    <c:if test="${diver.user.username!=null}">${diver.user.username}</c:if></td>
                                <td>
                                    <a href="/admin/${diver.id}/direction/2" class="btn btn_find_all"><i class="bi bi-signpost"></i></a>
                                </td>

                                <td><a href="/admin/${diver.id}/edit_diver/" class="btn btn_edit"><i class="bi bi-vector-pen "></i></a>
                                    <a href="/admin/${diver.id}/delete_diver" class="btn btn_delete"><i class="bi bi-trash "></i></a>
                                </td>
                            </tr>

                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
            <c:if test="${diversList.isEmpty()}">
                <h2><em><center>Diver not found</center></em></h2>
            </c:if>

            <center>
                <button onclick="document.location = '/admin/add_diver';" type="button" class="btn my-2 btn_add">
                    Create
                </button>
            </center>
        </content>

    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>

</body>
</html>