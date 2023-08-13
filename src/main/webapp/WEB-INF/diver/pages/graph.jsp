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

                    <h1 class="pt-4">Electronic Diver card: ${diver.surname} ${diver.name.charAt(0)}.${diver.middle_name.charAt(0)}.</h1>

                    <ul class="nav nav-tabs ">
                        <li class="nav-item">
                            <a href="/diver/1" class="nav-link link-custom" aria-disabled="true" tabindex="-1" >
                                Visits
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link link-custom " href="/diver/symptoms/1" aria-disabled="true" tabindex="-1">
                                Comments
                            </a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link link-custom "  href="/diver/doneTests"  aria-disabled="true" tabindex="-1" >
                                Tests
                            </a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link link-custom active"  href="/diver/graph"  aria-current="page">
                                Diver Graf
                            </a>
                        </li>
                    </ul>

                    <jsp:include page="../template/graphDiverImmersion.jsp"/>

                </content>
                <footer></footer>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>