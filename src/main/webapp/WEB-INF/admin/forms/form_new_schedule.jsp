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
                            <title>Add</title>

                        <legend class="card-title text-center">Visit time</legend>

                        <form:form action="/admin/add_schedule" method="POST" name="schedule"  class="was-validated">

                            <input type="hidden" name="id_diverPro" value="${id_diverPro}" class="form-control" required>

                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Day:</label>
                                <div class="col-sm-6">
                                    <select  name="day"  class="form-select">
                                        <option value="1" <c:if test="${!empty schedule.day && schedule.day==6}">
                                            selected
                                        </c:if>>Sunday </option>
                                        <option value="2" <c:if test="${!empty schedule.day && schedule.day==2}">
                                            selected
                                        </c:if>>Monday</option>
                                        <option value="3" <c:if test="${!empty schedule.day && schedule.day==3}">
                                            selected
                                        </c:if>>Tuesday</option>
                                        <option value="4" <c:if test="${!empty schedule.day && schedule.day==4}">
                                            selected
                                        </c:if>>Wednesday</option>
                                        <option value="5" <c:if test="${!empty schedule.day && schedule.day==5}">
                                            selected
                                        </c:if>>Thursday</option>
                                        <option value="6" <c:if test="${!empty schedule.day && schedule.day==6}">
                                            selected
                                        </c:if>>Friday </option>
                                        <option value="7" <c:if test="${!empty schedule.day && schedule.day==6}">
                                            selected
                                        </c:if>>Saturday </option>




                                    </select>
                                </div>
                            </div>

                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Time from:</label>
                                <div class="col-sm-6">
                                    <input type="time" name="timeStart" class="form-control" maxlength="5" required>
                                </div>
                            </div>

                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Time to:</label>
                                <div class="col-sm-6">
                                    <input type="time" name="timeEnd" class="form-control" maxlength="5" required>
                                </div>
                            </div>

                            <div class="row mb-3">
                                <label class="col-sm-6 col-form-label ln">Duration:</label>
                                <div class="col-sm-6">
                                    <input type="number" name="interval" class="form-control" min="10"  max="60" required>
                                </div>
                            </div>


                            <center>
                                    <input type="submit" id="in"  class="btn btn_add" name="add_visit" value="Create">

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
                                <span class="ml-2" style="font-size:16pt;">The schedule is already exist</span>
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