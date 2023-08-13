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
                        <button onclick="document.location = '/lab/doneTests';" type="button" class="btn btn-circle btn-lg d-flex justify-content-center align-items-center">
                            <i class="bi bi-arrow-left ar"></i>
                        </button>
                    </div>
                    <div class="card-body">
                        <legend class="card-title text-center">Test</legend>
                        <div class="form" name="test">
                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Date:</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="date" placeholder="date" value="${test.date}" readonly>
                                </div>
                            </div>

                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Test type:</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="text" placeholder="level" value="${test.testsType.name}" readonly>
                                </div>
                            </div>

                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Diver:</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="text" placeholder="none" value="${test.diver.surname} ${test.diver.name.charAt(0)}.${test.diver.middle_name.charAt(0)}." maxlength="100" readonly>
                                </div>
                            </div>

                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Result:</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="text" placeholder="result" value="${test.result}" readonly>
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