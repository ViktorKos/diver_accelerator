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
                        <legend class="card-title text-center">Довідка</legend>
                        <div id="source-html">
                            <center>
                                <h1>Довідка № <u>${recreation_list.visit.diver.count_of_recreation_leave}</u></h1>
                                <p><b> Referral for recreation</b></p>
                            </center>
                            <p>Date: <u>${date}</u>р.</p>
                            <p>1. Diver level: <u>${recreation_list.Dive_site}</u></p>
                            <p>2. Diver Name: <u>${recreation_list.visit.diver.surname} ${recreation_leave.visit.diver.name} ${recreation_leave.visit.diver.middle_name}</u></p>
                            <p>3. Date of birth: <u>${recreation_list.visit.diver.dateToString}</u></p>
                            <p>4. Symptoms: <u>barotrauma</u></p>
                            <p>5. Other damage ("${recreation_listJSP.contact.length()== 0 ? "yes,<u>no</u>" : "<u>yes</u>, no" }"): <u>${recreation_listJSP.contact}</u></p>
                            <p>6. Mandatory visit to the pressure chamber:</p>
                            <p>з <u>${start_date}</u> to <u>${date}</u>
                        </div>
                        <center>
                            <a  <c:if test="${recreation_list.visit.diverPro.specialization.id==1}">href="/${id_visit}/visits/1"</c:if>
                                <c:if test="${recreation_list.visit.diverPro.specialization.id!=1}">href="/diverPro2/${id_visit}/choose_action_analysis"</c:if>
                                class="btn btn_form_add">Next</a>
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