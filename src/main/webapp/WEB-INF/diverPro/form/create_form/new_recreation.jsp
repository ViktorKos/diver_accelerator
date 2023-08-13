<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../../template/head.jsp" />
<body onload="validDateBeforeCurrent()">
<div class="container">
    <div class="row">
        <content>
            <div class="col-xl-7 col-lg-10 col-md-10 col-sm-10 mx-auto p-3 pt-3">
                <div class="card card_form">
                    <div class="card-body">
                        <legend class="card-title text-center">InformList</legend>
                        <form:form  method="POST" action="/add_recreation_act" class="was-validated">
                            <input name="id_visit" type="hidden" value="${id_visit}" maxlength="100" readonly>
                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Start recreation date:</label>
                                <div class="col-sm-6">
                                    <input id="valid_date" class="form-control" type="date" name="start_date" placeholder="start_date" value="${recreationJSP.start_date}" maxlength="20" required>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">End recreation date:</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="date" name="d" placeholder="d" value="${visit.date}" maxlength="20" readonly>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Dive Center:</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="text" name="diveCenter" value="${recreationJSP.diveCenter}" maxlength="30" required>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Result:</label>
                                <div class="col-sm-6">
                                    <select  name="selected_immersion1" class="form-select">
                                        <c:forEach var="immersion" items="${immersionsList}" varStatus="i">
                                            <option value="${immersion.max_deep}">${immersion.max_deep}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Fauna contacts:</label>
                                <div class="col-sm-6">
                                    <input class="form-control no-validate" type="text" name="contact" value="${recreationJSP.contact}" maxlength="50">
                                </div>
                            </div>
                            <c:set value="add_recreation_act" var="add_recreation_act"/>
                            <center>
                                <input type="submit" id="in" name="${add_recreation_act}" class="btn btn_form_add" value="Create">
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
