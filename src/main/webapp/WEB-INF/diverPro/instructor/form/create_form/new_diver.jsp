<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../../../template/head.jsp" />

<c:url value="/diverPro1/add_diver" var="addUrl"/>
<body>
<div class="container">

    <div class="row">
        <content>

            <div class="col-xl-7 col-lg-10 col-md-10 col-sm-10 mx-auto p-3 pt-3">

                <div class="card card_form">

                    <div id="return">
                        <button onclick="document.location = '/diverPro1/divers/1';" type="button" class="btn btn-circle btn-lg d-flex justify-content-center align-items-center">
                            <i class="bi bi-arrow-left ar"></i>
                        </button>
                    </div>

                    <div class="card-body">
                        <legend class="card-title text-center">Diver</legend>
                        <form:form action="${addUrl}" method="POST" name="diver"  class="was-validated">


                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Id:</label>
                                <div class="col-sm-6">
                                    <input class="form-control " type="text" pattern="[0-9]{10}" name="id" value="${diver.id}" required>
                                </div>
                            </div>

                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Surname:</label>
                                <div class="col-sm-6">
                                    <input type="text" name="surname" class="form-control" value="${diver.surname}" required>
                                </div>
                            </div>

                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Name:</label>
                                <div class="col-sm-6">
                                    <input type="text" name="name" class="form-control" value="${diver.name}" maxlength="100" required>
                                </div>
                            </div>

                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Birth:</label>
                                <div class="col-sm-6">
                                    <input type="date" name="date_of_birth" class="form-control" value="${diver.date_of_birth}" maxlength="20" data-inputmask="'alias': 'date','placeholder': '*'" required>
                                </div>
                            </div>

                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Sex:</label>
                                <div class="col-sm-6">
                                    <div class="form-check form-check-inline">
                                        <input type="radio" id="sex1" class="form-check-input" name="sex" value="0" required  <c:if test="${ (!empty diver.id) && (diver.sex== 0)}">checked</c:if>>
                                        <label for="sex1" class="form-check-label" >Male</label>

                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input type="radio" id="sex2" class="form-check-input" name="sex" value="1" required  <c:if test="${ (!empty diver.id) && (diver.sex== 1)}">checked</c:if>>
                                        <label for="sex2" class="form-check-label">Female</label>
                                    </div>
                                </div>
                            </div>

                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Phone:</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="tel" pattern="0[0-9]{9}" name="telephone_number" value="<c:if test="${!empty diver.id}">0${diver.telephone_number}</c:if>" required>
                                </div>
                            </div>

                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">E-mail:</label>
                                <div class="col-sm-6">
                                    <input type="email"  name="email" class="form-control  no-validate" value="${diver.email}">
                                </div>
                            </div>

                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Adress:</label>
                                <div class="col-sm-6">
                                    <input type="text"  name="address" class="form-control" value="${diver.address}" required>
                                </div>
                            </div>

                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Diver lvl:</label>
                                <div class="col-sm-6">
                                    <input type="text"  name="diveLevel" class="form-control" value="${diver.diveLevel}" required>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Max deep:</label>
                                <div class="col-sm-6">
                                    <input type="text"  name="max_deep" class="form-control" value="${diver.max_deep}" required>
                                </div>
                            </div>


                            <c:set value="edit_diver" var="edit_diver"/>
                            <center>
                                <c:set value="add_diver" var="add_diver"/>
                                <input type="submit" id="in" class="btn btn_form_add" name="${add_diver}" value="Create">

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
                                <span class="ml-2" style="font-size:16pt;">Diver is already exist</span>
                            </div>
                            <center><button type="button" class="btn btn-outline-primary mt-3" data-bs-dismiss="modal">OK</button></center>
                        </div>


                    </div>
                </div>
            </div>


        </content>
        <footer></footer>
    </div>
</div>




<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>



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