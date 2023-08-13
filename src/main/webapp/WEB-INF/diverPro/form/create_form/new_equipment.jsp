<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../../template/head.jsp" />
<body>
<div class="container">

    <div class="row">
        <content>

            <div class="col-xl-7 col-lg-10 col-md-10 col-sm-10 mx-auto p-3 pt-3">
                <div class="card card_form">
                    <div class="card-body">
                        <legend class="card-title text-center">Equipment</legend>

                        <c:url value="/add_new_equipment_act" var="addUrl"/>
                        <form action="${addUrl}" method="POST" name="equipment" class="was-validated">
                            <input name="id_visit" type="hidden" value="${id_visit}" maxlength="100" readonly>

                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Name:</label>
                                <div class="col-sm-6">
                                    <select name="selected_equipment" class="form-select">
                                        <c:forEach var="medItem" items="${equipmentCatalogList}" varStatus="i">
                                            <option value="${medItem.name}">${medItem.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Size:</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="text" name="dose" value="0" required>
                                </div>
                            </div>

                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Count:</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="number" name="number_of_day" value="0" required>
                                </div>
                            </div>

                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Instruction:</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="text" name="instruction">
                                </div>
                            </div>

                            <center>
                                <button class="btn btn_form_add d-block mb-2" name="add_new_equipment" value="add_new_equipment">Add to recommendation</button>
                                <button id="rec" class="btn btn_form_safe d-block" name="safe_Recipe" value="safe_Recipe">Save recommendation</button>
                            </center>

                            <c:if test="${!recipeJSP.equipmentList.isEmpty()}">
                            <div class="table-wrapper-scroll-y my-custom-scrollbar">
                                <legend class="card-title text-center">Equipment list:</legend>
                                <table class="table tableFixHead">
                                    <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Size</th>
                                        <th>Count</th>
                                        <th>Instruction</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <c:forEach var="equipment" items="${recipeJSP.equipmentList}" varStatus="i">
                                        <tr id="equipment_table">
                                            <td>${equipment.name}</td>
                                            <td>${equipment.size}</td>
                                            <td>${equipment.cnt}</td>
                                            <td>${equipment.instruction}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>

                            </div>

                        </form>
                        </c:if>
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