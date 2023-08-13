<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../template/head.jsp" />
<body>
    <div class="container">

        <div class="row">
            <content>

                <div class="col-xl-7 col-lg-10 col-md-10 col-sm-10 mx-auto p-3 pt-3">
                    <div class="card card_form">

                        <div class="card-body">
                            <legend class="card-title text-center">Recommendation list</legend>
                            <div id="source-html">

                                <h2>Recipe №  <u>${recipe.visit.diver.count_of_recipe}</u></h2>
                                <p><b>adult, <u>teenager</u> (select please)</b></p>


                                <p>Date ${date} year</p>

                                <p>Diver Initials, and age : <u>${recipe.visit.diver.surname} ${recipe.visit.diver.name.charAt(0)}.${recipe.visit.diver.middle_name.charAt(0)}.</u></p>
                                <p>Diver id: <u>${recipe.visit.diver.diver_id}</u></p>
                                <p>DiverPro name: <u>${recipe.visit.diverPro.surname} ${recipe.visit.diverPro.name.charAt(0)}.${recipe.visit.diverPro.middle_name.charAt(0)}.</u></p>                                <hr color="#000000">
                                <c:forEach var="Equipment" items="${recipe.equipmentList}" varStatus="i">
                                <p>I Recommendation:</p>
                                <p>I - Course Name: ${equipment.name} </p>
                                <p>I - Single : ${equipment.id} </p>
                                <hr color="#000000">
                                </c:forEach>
                                <p>DiverPro accepted  sign up:_____________________________</p>
                                <center><p>valid for 1 month</p></center>
                            </div>
                            <center>
                                <a href="/${id_visit}/choose_action_sickLeave" class="btn btn_form_add">Next</a>
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