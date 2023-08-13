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

                            <div id="return">
                                <button onclick="document.location = '/${id_visit}/visits/1';" type="button" class="btn btn-circle btn-lg d-flex justify-content-center align-items-center">
                                    <i class="bi bi-arrow-left ar"></i>
                                </button>
                            </div>


                    <div class="card-body">
                        <legend class="card-title text-center">Diver</legend>
                        <c:url value="/edit_diver" var="editUrl"/>
                        <form action="${editUrl}" name="diver" method="POST">
                            <input type="hidden" name="id_visit" value="${id_visit}">
                            <input type="hidden" name="user_id" value="${diver.user.id}">


                                <div class="row mb-3">
                                    <label class="col-sm-6 col-form-label ln">Diver ID:</label>
                                    <div class="col-sm-6">
                                        <input type="number"  name="id" class="form-control" placeholder="id" value="${diver.id}" maxlength="100" readonly>
                                    </div>
                                </div>

                                <div class="row mb-3">
                                    <label class="col-sm-6 col-form-label ln">First Name:</label>
                                    <div class="col-sm-6">
                                        <input type="text" name="surname" class="form-control" placeholder="surname" value="${diver.surname}" required>
                                    </div>
                                </div>

                                <div class="row mb-3">
                                    <label class="col-sm-6 col-form-label ln">Name</label>
                                    <div class="col-sm-6">
                                        <input type="text" name="name" class="form-control" placeholder="name" value="${diver.name}" maxlength="100" required>
                                    </div>
                                </div>

                                <div class="row mb-3">
                                    <label class="col-sm-6 col-form-label ln">Middle Name:</label>
                                    <div class="col-sm-6">
                                        <input type="text" name="middle_name" class="form-control" placeholder="middle_name" value="${diver.middle_name}" required>
                                    </div>
                                </div>

                                <div class="row mb-3">
                                    <label class="col-sm-6 col-form-label ln">Date of birth:</label>
                                    <div class="col-sm-6">
                                        <input type="date" name="date_of_birth" class="form-control" placeholder="date_of_birth" value="${diver.date_of_birth}" maxlength="20" readonly>
                                    </div>
                                </div>

                                <div class="row mb-3">
                                    <label class="col-sm-6 col-form-label ln">Sex:</label>
                                    <div class="col-sm-6">
                                        <div class="form-check form-check-inline">
                                            <input type="radio" id="sex1" class="form-check-input" name="sex" value="0"  <c:if test="${diver.sex== 0}">checked</c:if>>
                                            <label for="sex1" class="form-check-label" >M</label>

                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input type="radio" id="sex2" class="form-check-input" name="sex" value="1"  <c:if test="${diver.sex== 1}">checked</c:if>>
                                            <label for="sex2" class="form-check-label">F</label>
                                        </div>
                                    </div>
                                </div>

                                <div class="row mb-3">
                                    <label class="col-sm-6 col-form-label ln">Phone number:</label>
                                    <div class="col-sm-6">
                                        <input type="number" name="telephone_number" class="form-control" value="${diver.telephone_number}" required>
                                    </div>
                                </div>

                                <div class="row mb-3">
                                    <label class="col-sm-6 col-form-label ln">E-mail:</label>
                                    <div class="col-sm-6">
                                        <input type="email"  name="email" class="form-control" value="${diver.email}">
                                    </div>
                                </div>

                                <div class="row mb-3">
                                    <label class="col-sm-6 col-form-label ln">Address:</label>
                                    <div class="col-sm-6">
                                        <input type="text"  name="address" class="form-control" value="${diver.address}" required>
                                    </div>
                                </div>

                                <div class="row mb-3">
                                    <label class="col-sm-6 col-form-label ln">Blood type:</label>
                                    <div class="col-sm-6">
                                        <input type="number" name="blood_type" class="form-control" value="${diver.blood_type}" maxlength="1" readonly>
                                    </div>
                                </div>

                                <div class="row mb-3">
                                    <label class="col-sm-6 col-form-label ln">RH:</label>
                                    <div class="col-sm-6">
                                        <input type="text" name="rh" class="form-control" value="${diver.rh}" maxlength="1" readonly>
                                    </div>
                                </div>

                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Count of recommendation:</label>
                                <div class="col-sm-6">
                                    <input type="number" name="count_of_recipe" class="form-control no-validate"  value="${diver.count_of_recipe}" readonly>
                                </div>
                            </div>

                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Count of recreation:</label>
                                <div class="col-sm-6">
                                    <input type="number" name="count_of_recreation" class="form-control no-validate"  value="${diver.count_of_recreation_list}" readonly>
                                </div>
                            </div>

                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Height:</label>
                                <div class="col-sm-6">
                                    <input type="number" name="height" class="form-control" value="${diver.height}" step="0.01">
                                </div>
                            </div>

                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Width:</label>
                                <div class="col-sm-6">
                                    <input type="number" name="width" class="form-control" value="${diver.width}" step="0.01">
                                </div>
                            </div>


                            <c:set value="edit_diver" var="edit_diver"/>
                                <center>
                                    <input type="submit" id="in"  class="btn btn_form_add" name="${edit_diver}" value="Change">
                                </center>
                        </form>
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