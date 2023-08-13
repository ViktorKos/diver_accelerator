<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../../template/head.jsp" />
<body style="background-color: #ffffff;">
<div class ="container-fluid">
    <div class="row">
        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 p-0">
            <jsp:include page="../../template/nav.jsp" />
            <div style="background-color: #ffffff;">
                <content>

                    <h1 class="pt-4">Conducted tests</h1>

                    <c:if test="${!testList.isEmpty()}">
                    <div class="table-wrapper-scroll-y my-custom-scrollbar">

                        <table class="table tableFixHead">
                            <thead>
                            <tr>
                                <th>Date</th>
                                <th>Test type</th>
                                <th>Diver</th>
                                <th>Result</th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:forEach var="test" items="${testList}" varStatus="i">
                                <tr onclick='document.location="<c:url value='/lab/${test.id}'/>"'>
                                    <td>${test.date}</td>
                                    <td>${test.testsType.name}</td>
                                    <td>${test.diver.surname} ${test.diver.name.charAt(0)}.${test.diver.middle_name.charAt(0)}.</td>
                                    <td>${empty test.result ? "-" : test.result }</td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                    </div>
                    </c:if>

                    <c:if test="${testList.isEmpty()}">
                        <h2 class="pt-5"><em><center>no tests yet</center></em></h2>
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