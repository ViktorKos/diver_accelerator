<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../../template/head.jsp" />

<body>
<div class="container">

    <div class="row">
        <content>

            <div class="col-xl-7 col-lg-10 col-md-10 col-sm-10 mx-auto p-3 pt-3">
                <div class="card card_form">

                    <div class="card-body">
                        <legend class="card-title text-center">special Test</legend>
                        <div id="source-html">
                            <center>
                                <h1>DIRECTION FOR TESTS № <u>${tests_JSP.number}</u></h1>
                                <p><u>${tests_JSP.dateToString}</u></p>

                            </center>

                            <p>To lab: <u>${tests_JSP.laboratory}</u></p>
                            <p>Name: &nbsp;<u>${tests_JSP.visit.diver.surname} ${tests_JSP.visit.diver.name} ${tests_JSP.visit.diver.middle_name}</u></p>
                            <p>Date of birth: &nbsp; <u>${tests_JSP.visit.diver.dateToString}</u></p>
                            <p>Res:&nbsp;<u>${tests_JSP.immersion}</u></p>
                            <p>Aim of the study:&nbsp;<u>${tests_JSP.target}</u></p>

                        </div>
                        <center>
                            <a href="/instructor/${id_visit}/choose_action_directionToHospital" class="btn btn_form_add">Next</a>
                        </center>
                    </div>
                </div>
            </div>



        </content>
        <footer></footer>
    </div>
</div>

</body>
</html>


<script>
  exportHTML();
</script>