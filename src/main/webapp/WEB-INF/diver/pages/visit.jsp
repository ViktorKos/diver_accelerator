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
                        <button onclick="document.location = '/diver/1';" type="button" class="btn btn-circle btn-lg d-flex justify-content-center align-items-center">
                            <i class="bi bi-arrow-left ar"></i>
                        </button>
                    </div>


                    <div class="card-body">
                        <legend class="card-title text-center">Visit</legend>
                        <div class="form" name="visit">


                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Date:</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="date" placeholder="date" value="${visit.date}" maxlength="100" readonly>
                                </div>
                            </div>

                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Equipment:</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="text" placeholder="base equipment" value="${visit.equipment}" readonly>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Diver specialization:</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="text" placeholder="master" value="${visit.diverPro.specialization.name}: ${visit.diverPro.surname} ${visit.diverPro.name} " readonly>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Dive center:</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="text" placeholder="local squba camp" value="${visit.diverPro.diveCenter.name}" readonly>
                                </div>
                            </div>

                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Complaints:</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="text" placeholder="no complains" value="${visit.complaints}" maxlength="50" readonly>
                                </div>
                            </div>

                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Actions:</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="text" placeholder="base diving deep: 20m" value="${visit.actions}" maxlength="100" readonly>
                                </div>
                            </div>

                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Notes:</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="text" placeholder="-" value="${visit.notes}" maxlength="100" readonly>
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