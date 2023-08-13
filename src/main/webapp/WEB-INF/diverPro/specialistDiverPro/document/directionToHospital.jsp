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
                        <legend class="card-title text-center">Referal to hospital</legend>
                        <div id="source-html">
                            <center>
                                <h1>Direction/Order № <u>${directionToHospital.number}</u></h1>
                                <p>На ${directionToHospital.typeHospital== false ? "emergency/<u>schedualed</u>" : "<u>emergency</u>/schedualed" } survey,</p>
                                <p>issued <u>${directionToHospital.dateToString}</u></p>

                            </center>

                            <p>To diver med center:&nbsp;<u>${directionToHospital.hospital}</u>&nbsp;&nbsp;&nbsp;department: <u>${directionToHospital.department}</u></p>
                            <p>Surname:&nbsp;<u>${directionToHospital.visit.diver.surname}</u>&nbsp;&nbsp;&nbsp;</p>
                            <p>Name:&nbsp;<u>${directionToHospital.visit.diver.name}</u>&nbsp;&nbsp;&nbsp;Middle name: <u>${directionToHospital.visit.diver.middle_name}</u></p>
                            <p>Date of birth&nbsp;<u>${directionToHospital.visit.diver.dateToString}</u></p>

                            <p>Disease: &nbsp;<u>${directionToHospital.disease}</u></p>
                            <p>Fluorography: &nbsp;<u>${directionToHospital.fluorography}${empty directionToHospital.fluorography ? "<u>none</u>" : "" }</u>&nbsp;&nbsp;Is hi pressure?: &nbsp;<u>${directionToHospital.is_hi_pressure}${empty directionToHospital.is_hi_pressure ? "<u>none</u>" : "" }</u></p>
                            <p>Temperature t <sup>&deg;</sup>&nbsp;<u>${directionToHospital.temperature}</u>&nbsp;&nbsp;Pulse &nbsp;<u>${directionToHospital.pulse}</u>
                            <p>DiverPro &nbsp;<u>${directionToHospital.visit.diverPro.specialization.name}</u>&nbsp;&nbsp;<u>${directionToHospital.visit.diverPro.surname} ${directionToHospital.visit.diverPro.name.charAt(0)}.${directionToHospital.visit.diverPro.middle_name.charAt(0)}.</u>&nbsp;&nbsp;тел. &nbsp;<u>0${directionToHospital.visit.diverPro.telephone_number}</u></p>

                        </div>
                        <center>
                            <a href="/${id_visit}/visits/1" class="btn btn_form_add">Next</a>
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