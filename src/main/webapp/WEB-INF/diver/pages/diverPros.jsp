<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<jsp:include page="../template/head.jsp" />
<body style="background-color: #ffffff;">
<div class ="container-fluid">
    <div class="row">
        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 p-0">
            <jsp:include page="../template/nav.jsp" />

            <div style="background-color: #ffffff;">
                <content>
                    <h2 class="text-center mt-3"> List of Dive Instructors</h2>

                    <c:if test="${!diverProsList.isEmpty()}">
                        <div class="row row-cols-1 row-cols-md-4 g-4 mx-2 mt-1 mb-3">
                            <c:forEach var="diverPro" items="${diverProsList}" varStatus="i">
                                <div class="col" onclick='document.location="<c:url value='/diver/${diverPro.id}/diverPro_info'/>"'>
                                    <div class="card my_card" >
                                        <div class="card-body">
                                            <center> <h4 class="card-title">${diverPro.surname} ${diverPro.name.charAt(0)}.${diverPro.middle_name.charAt(0)}.</h4></center>
                                            <center> <h5 class="card-title">${diverPro.specialization.name}</h5></center>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </c:if>

                    <c:if test="${diverProsList.isEmpty()}">
                        <h2 class="pt-5"><em><center>No available Dive Instructors</center></em></h2>
                    </c:if>

                </content>
                <footer></footer>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>