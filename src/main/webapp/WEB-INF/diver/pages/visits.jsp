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

                    <h1 class="pt-4">Electronic Diver card: ${diver.surname} ${diver.name.charAt(0)}.${diver.middle_name.charAt(0)}.  </h1>

                    <ul class="nav nav-tabs ">
                        <li class="nav-item">
                            <a href="/diver/1" class="nav-link link-custom active" aria-current="page">
                                Visits
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link link-custom" href="/diver/symptoms/1" tabindex="-1" aria-disabled="true">
                                Comments
                            </a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link link-custom" href="/diver/doneTests" tabindex="-1" aria-disabled="true">
                                Tests
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link link-custom "  href="/diver/graph"  aria-disabled="true" tabindex="-1" >
                                Diver Graph
                            </a>
                        </li>
                    </ul>


                    <c:if test="${!visitsList.isEmpty()}">
                    <div class="table-wrapper-scroll-y my-custom-scrollbar">

                        <table class="table tableFixHead">
                            <thead>
                            <tr>
                                <th>Date<a href="/diver/1" class="btn btn-sm "><i class="bi bi-sort-down"></i></a></th>
                               <th>Result<a href="/diver/2" class="btn btn-sm "><i class="bi bi-sort-down"></i></a></th>
                               <th>Instructor<a href="/diver/3" class="btn btn-sm "><i class="bi bi-sort-down"></i></a></th>
                                <th>Notes</th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:forEach var="visit" items="${visitsList}" varStatus="i">
                                <tr onclick='document.location="<c:url value='/diver/${visit.number}/visit'/>"'>
                                    <td>${visit.date}</td>
                                    <td>${visit.immersion.max_deep}</td>
                                    <td>${visit.diverPro.specialization.name}</td>
                                    <td>${empty visit.equipment ? "-" : visit.equipment }</td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                    </div>
                    </c:if>

                    <c:if test="${visitsList.isEmpty()}">
                        <h2 class="pt-5"><em><center>Візитів немає</center></em></h2>
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