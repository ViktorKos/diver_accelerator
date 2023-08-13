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
            <div class="col-xl-7 col-lg-10 col-md-10 col-sm-10 mx-auto p-3 pt-3">
                <div class="card card_form shadow">
                    <div class="card-body ">
                        <c:if test="${empty entity.user.id}">
                            <title>Add</title>
                        </c:if>
                        <c:if test="${!empty entity.user.id}">
                            <title>Edit</title>
                        </c:if>
                        <legend class="card-title text-center"><c:if test="${isDoc==true}">DiverPro: </c:if>
                            <c:if test="${isDoc==false}">Diver: </c:if>
                            ${entity.surname} ${entity.name.charAt(0)}.${entity.middle_name.charAt(0)}.</legend>


                        <form:form action="/admin/setUserDiver" method="POST" name="user"  class="was-validated">

                            <input type="hidden" name="id_diver" class="form-control" value="${entity.id}">

                            <c:if test="${!empty users}">

                                <div class="row mb-3">
                                    <label class="col-sm-6 col-form-label ln">Change User:</label>
                                    <div class="col-sm-6">
                                            <select name="selected_user" class="form-select">
                                                <c:if test="${!empty entity.user.id}">
                                                    <option value="${entity.user.id}" >${entity.user.username}</option>
                                                </c:if>
                                                <c:forEach var="user" items="${users}" varStatus="i">
                                                    <option value="${user.id}">${user.username}</option>
                                                </c:forEach>
                                            </select>
                                    </div>
                                </div>


                            <center>
                                <c:if test="${isDoc==false}">
                                    <c:if test="${empty entity.user.id}">
                                        <input type="submit" id="in"  class="btn btn_add" name="add_role" value="Create">
                                    </c:if>
                                    <c:if test="${!empty entity.user.id}">
                                        <input type="submit" id="edit"  class="btn btn_edit_form" name="edit" value="Update">
                                    </c:if>
                                </c:if>


                                <c:if test="${isDoc==true}">

                                    <c:if test="${empty entity.user.id}">
                                        <input type="submit" id="in"  class="btn btn_add" name="editD" value="Create">
                                    </c:if>
                                    <c:if test="${!empty entity.user.id}">
                                        <input type="submit" id="edit"  class="btn btn_edit_form" name="editD" value="Update">
                                    </c:if>
                                </c:if>

                            </center>
                            </c:if>

                            <c:if test="${empty users}">
                                <h3><center>no Users</center></h3>
                            </c:if>

                        </form:form>
                    </div>
                </div>
            </div>

        </content>

    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>



</body>

</html>
