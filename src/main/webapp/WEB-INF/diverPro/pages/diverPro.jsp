<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../template/head.jsp" />
<body>
<div class="container">

    <div class="row">
        <content>
            <div class="col-xl-7 col-lg-10 col-md-10 col-sm-10 mx-auto p-3 pt-3">
                <div class="card card_form">

                    <div id="return">
                        <button onclick="document.location = '/';" type="button" class="btn btn-circle btn-lg d-flex justify-content-center align-items-center">
                            <i class="bi bi-arrow-left ar"></i>
                        </button>
                    </div>
                    
                    <div class="card-body">
                        <legend class="card-title text-center pb-1">My profile</legend>
                        <div>


                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">ID:</label>
                                <div class="col-sm-6">
                                    <input type="number" class="form-control" placeholder="id" value="${diverPro.id}" maxlength="100" readonly>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Specialization:</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" placeholder="specialization" value="${diverPro.specialization.name}" readonly>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Surname:</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" placeholder="surname" value="${diverPro.surname}" readonly>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Name:</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" placeholder="name" value="${diverPro.name}" maxlength="100" readonly>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Middle name:</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" placeholder="middle_name" value="${diverPro.middle_name}" readonly>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Date of birth:</label>
                                <div class="col-sm-6">
                                    <input type="date" class="form-control" placeholder="date_of_birth" value="${diverPro.date_of_birth}" maxlength="20" readonly>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Sex:</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" value="${diverPro.sex== 0 ? "M" : "F" }" readonly>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Phone number:</label>
                                <div class="col-sm-6">
                                    <input type="number" class="form-control" placeholder="telephone_number" value="${diverPro.telephone_number}" readonly>
                                </div>
                            </div>
                            <c:if test="${diverPro.specialization.id!=8}">
                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">The number of visits for this month:</label>
                                <div class="col-sm-6">
                                    <input type="number" class="form-control" placeholder="count of visits" value="${visitsList.size()}" readonly>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">The number of daily visits:</label>
                                <div class="col-sm-6">
                                    <input type="number" class="form-control" placeholder="count of daily visits" value="${todayVisitsList.size()}" readonly>
                                </div>
                            </div>
                            </c:if>
                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Experience:</label>
                                <div class="col-sm-6">
                                    <input type="number" class="form-control" placeholder="experience" value="${standing}" readonly>
                                </div>
                            </div>

                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Email:</label>
                                <div class="col-sm-6">
                                    <input type="email" class="form-control" name="email" value="${diverPro.email}" readonly>
                                </div>
                            </div>

                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Address:</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" name="address" value="${diverPro.address}" readonly>
                                </div>
                            </div>
                        </div>
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