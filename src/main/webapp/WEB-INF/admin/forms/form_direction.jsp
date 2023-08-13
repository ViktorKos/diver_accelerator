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
                        <c:if test="${empty direction.number}">
                            <title>Add</title>
                        </c:if>
                        <c:if test="${!empty direction.number}">
                            <title>Edit</title>
                        </c:if>
                        <legend class="card-title text-center">Appointment</legend>


                        <form:form action="/admin/add_direction" method="POST" name="direction"  class="was-validated">

                            <input type="hidden" name="id_diver" class="form-control" value="${id_diver}">
                            <input type="hidden" name="number" class="form-control" value="${direction.number}">

                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Certification:</label>
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

                            <c:if test="${!empty direction.number}">
                                <div class="row mb-3">
                                    <label class="col-sm-6 col-form-label ln">Status:</label>
                                    <div class="col-sm-6">
                                        <div class="form-check form-check-inline">
                                            <input type="radio" id="sex2" class="form-check-input" name="status" value="1"  <c:if test="${direction.status}">checked</c:if>>
                                            <label for="sex2" class="form-check-label">Active</label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input type="radio" id="sex1" class="form-check-input" name="status" value="0" <c:if test="${!direction.status}">checked</c:if>>
                                            <label for="sex1" class="form-check-label" >Done</label>

                                        </div>
                                    </div>
                                </div>

                            </c:if>


                            <center>
                                <c:if test="${empty direction.number}">
                                    <input type="submit" id="in"  class="btn btn_add" name="add_direction" value="Save">
                                </c:if>
                                <c:if test="${!empty direction.number}">
                                    <input type="submit" id="edit"  class="btn btn_edit_form" name="edit" value="Change">
                                </c:if>


                            </center>


                        </form:form>
                    </div>
                </div>
            </div>

            <!-- Modal -->
            <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-body">
                            <div class="text-center text-justify">
                                <i class="bi bi-exclamation-triangle-fill text-danger pl-2 pt-2 dang  position-relative"></i>
                                <span class="ml-2" style="font-size:16pt;">The appointment exist</span>
                            </div>
                            <center><button type="button" class="btn btn-outline-primary mt-3" data-bs-dismiss="modal">OK</button></center>
                        </div>


                    </div>
                </div>
            </div>



        </content>

    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>



</body>

</html>

<c:if test="${message!=null}">
    <script type="text/javascript">
        var myModal = new bootstrap.Modal(document.getElementById("staticBackdrop"), {});
        document.onreadystatechange = function () {
            myModal.show();
        };
    </script>
</c:if>