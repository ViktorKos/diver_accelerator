<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../../../template/head.jsp" />
<body>
<div class="container">

    <div class="row">
        <content>

            <div class="col-xl-7 col-lg-10 col-md-10 col-sm-10 mx-auto p-3 pt-3">

                <div class="card card_form">

                    <div id="return">
                        <button onclick="document.location = '/doctor1/${id_visit}/choose_action_direction/';" type="button" class="btn btn-circle btn-lg d-flex justify-content-center align-items-center">
                            <i class="bi bi-arrow-left ar"></i>
                        </button>
                    </div>


                    <div class="card-body">
                        <legend class="card-title text-center">Направлення</legend>
                        <form:form  method="POST" modelAttribute="direction" action="/doctor1/add_direction">
                            <input name="id_visit" type="hidden" value="${id_visit}" maxlength="100" readonly>

                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Спеціальність лікаря:</label>
                                <div class="col-sm-6">
                                    <select  name="selected_spec"  class="form-select">

                                        <c:forEach var="specialization" items="${specializationsList}" varStatus="i">
                                            <c:if test="${specialization.id!=1}">
                                                <option value="${specialization.id}" <c:if test="${!empty direction.number && direction.specialization.id==specialization.id}">
                                                    selected
                                                </c:if>>${specialization.name}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>


                            <c:set value="add_direction" var="add_direction"/>
                            <center>
                                <input type="submit" id="in" name="${add_direction}" class="btn btn_form_add" value="Далі">
                            </center>
                            <p>${message}</p>


                        </form:form >
                    </div>
                </div>
            </div>
        </content>
        <footer></footer>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
</body>
</html>
