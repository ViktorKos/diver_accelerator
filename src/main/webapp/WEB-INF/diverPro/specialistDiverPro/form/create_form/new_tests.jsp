<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../../../template/head.jsp" />

<body onload="validDate()">
<div class="container">

    <div class="row">
        <content>

            <div class="col-xl-7 col-lg-10 col-md-10 col-sm-10 mx-auto p-3 pt-3">

                <div class="card card_form">


                    <div class="card-body">
                        <legend class="card-title text-center">Test</legend>
                        <form:form  method="POST" action="/instructor/add_tests" class="was-validated">

                            <input name="id_visit" type="hidden" value="${id_visit}" maxlength="100" readonly>

                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Date:</label>
                                <div class="col-sm-6">
                                    <input id="valid_date" class="form-control" type="date" name="date"  value="${tests_JSP.date}" maxlength="20" required>
                                </div>
                            </div>

                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Laboratory:</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="text" name="laboratory" value="${tests_JSP.laboratory}" maxlength="20" required>
                                </div>
                            </div>

                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Result:</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="text" name="res" value="${visit.immersion.name}" maxlength="20" readonly>
                                </div>
                            </div>

                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Aim of study:</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="text" name="target" value="${tests_JSP.target}" maxlength="20" required>
                                </div>
                            </div>

                            <c:set value="add_tests" var="add_tests"/>
                            <center>
                                <input type="submit" id="in" name="${add_tests}" class="btn btn_form_add" value="Create">
                            </center>

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
