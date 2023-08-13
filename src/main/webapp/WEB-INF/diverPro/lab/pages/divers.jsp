<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<jsp:include page="../../template/head.jsp" />
<body style="background-color: #ffffff;">
<div class ="container-fluid">
    <div class="row">
        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 p-0">
            <jsp:include page="../../template/nav.jsp" />


<div style="background-color: #ffffff;">
            <content>

                <h1 class="pt-4">Diver list</h1>


                <div class="col-xl-5 col-lg-5 col-md-7 col-sm-5 pt-1 px-2 pb-2 mx-auto">
                        <form class="d-flex" id="search" method="GET" action="/lab/divers/searchTelephone_number">
                            <input class="form-control me-2 shadow bg-body rounded rounded-pill border-0" id="search_input" type="number" name="telephone_number" placeholder="номер телефону"  required>
                            <button class="btn btn_find mx-2" id="search_button" type="submit">Find</button>
                            <a href="/lab/divers/1" class="btn btn_find_all">All</a>
                        </form>
                </div>

                <c:if test="${!diversList.isEmpty()}">
                <div class="table-wrapper-scroll-y my-custom-scrollbar">

                    <table class="table tableFixHead">
                        <thead>
                        <tr>
                            <th>ID<a href="/lab/divers/1" class="btn btn-sm "><i class="bi bi-sort-down"></i></a></th>
                            <th>Name<a href="/lab/divers/2" class="btn btn-sm "><i class="bi bi-sort-down"></i></a></th>
                            <th>Sex<a href="/lab/divers/3" class="btn btn-sm "><i class="bi bi-sort-down"></i></a></th>
                            <th>Phone number<a href="/lab/divers/4" class="btn btn-sm "><i class="bi bi-sort-down"></i></a></th>
                            <th>User</th>
                        </tr>
                        </thead>



                            <tbody>
                                    <c:forEach var="diver" items="${diversList}" varStatus="i">
                                        <tr onclick='document.location="<c:url value='/lab/${diver.id}/tests'/>"'>
                                            <td>${diver.id}</td>
                                            <td>${diver.surname} ${diver.name.charAt(0)}.${diver.middle_name.charAt(0)}.</td>
                                            <td>${diver.sex== 0 ? "M" : "F" }</td>
                                            <td>0${diver.telephone_number}</td>
                                            <td>${diver.user.username}</td>
                                        </tr>
                                    </c:forEach>
                            </tbody>
                    </table>
                    </c:if>

                    <c:if test="${diversList.isEmpty()}">
                    <h2><em><center>no Divers</center></em></h2>
                    </c:if>

                </div>

            </content>
            <footer></footer>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>