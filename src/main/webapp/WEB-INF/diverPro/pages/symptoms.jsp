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

                    <h1 class="pt-4">Electronic Diver card: ${diver.surname} ${diver.name.charAt(0)}.${diver.middle_name.charAt(0)}.
                        <a href="/${id_visit}/visits/edit_diver" class="link link-userLink" > <i class="bi bi-person-circle"></i></a></h1>

                    <ul class="nav nav-tabs">
                        <li class="nav-item">
                            <a href="/${id_visit}/visits/1" class="nav-link link-custom" tabindex="-1"  aria-disabled="true">
                                Visits
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="/${id_visit}/symptoms/1"  aria-current="page" >
                                Complaints
                            </a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link link-custom"  href="/${id_visit}/tests/1" tabindex="-1" aria-disabled="true">
                                Tests
                            </a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link link-custom"  href="/${id_visit}/graph" tabindex="-1" aria-disabled="true">
                                Graph
                            </a>
                        </li>
                    </ul>

                    <c:if test="${!symptomsList.isEmpty()}">
                    <div class="table-wrapper-scroll-y my-custom-scrollbar">

                        <table class="table tableFixHead">
                            <thead>
                            <tr>
                                <th>Date<a href="/${id_visit}/symptoms/1" class="btn btn-sm "><i class="bi bi-sort-down"></i></a></th>
                                <th>Complaints</th>
                                <th>Notes</th>
                            </tr>
                            </thead>



                            <tbody>
                            <c:forEach var="record" items="${symptomsList}" varStatus="i">
                                <tr onclick='document.location="<c:url value='/${id_visit}/${record.id}/symptom'/>"'>
                                    <td>${record.date}</td>
                                    <td>${record.symptoms}</td>
                                    <td>${empty record.notes ? "-" : record.notes }</td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                    </div>
                    </c:if>

                    <c:if test="${symptomsList.isEmpty()}">
                        <h2 class="pt-5"><em><center>no Complaints</center></em></h2>
                    </c:if>
                    <c:if test="${isActive==true}">
                    <center>
                        <button onclick="document.location = '/${id_visit}/add_visit';" type="button" class="btn my-2 btn_find">
                            Start visit
                        </button>
                    </center>
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