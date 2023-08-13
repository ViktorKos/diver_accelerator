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
                            <legend class="card-title text-center">Declaration</legend>
                            <div id="source-html">

                                <h2>Declaration №  <u>${declaration.id}</u></h2>
                                <h2>about choosing an instructor who provides services</h2>


                                <p><b>1. Diver</b></p>
                                <p>1.1 Surname: <u>${declaration.diver.surname}</u></p>
                                <p>1.2 Name: <u>${declaration.diver.name}</u></p>
                                <p>1.3 Middle Name: <u>${declaration.diver.middle_name}</u></p>
                                <p>1.4 Date of birth: <u>${declaration.diver.date_of_birth}</u></p>
                                <p>1.5 Sex: <u>${declaration.diver.sex== 0 ? "M" : "F"}</u></p>
                                <p><b>1.7 Contact list</b></p>
                                <p>1.7.1 Phone Number: <u>0${declaration.diver.telephone_number}</u></p>
                                <p>1.7.2 E-mail: <u>${declaration.diver.email}</u></p>
                                <p>1.8 Document ID: <u>${declaration.document_identifier}</u></p>

                                <p><b>2. Instructor</b></p>
                                <p>2.1 Surname: <u>${declaration.diverPro_dec.surname}</u></p>
                                <p>2.2 Name: <u>${declaration.diverPro_dec.name}</u></p>
                                <p>2.3 Middle Name: <u>${declaration.diverPro_dec.middle_name}</u></p>
                                <p>2.4 Phone Number: <u>0${declaration.diverPro_dec.telephone_number}</u></p>
                                <p>2.5 E-mail: <u>${declaration.diverPro_dec.email}</u></p>

                                <p><b>3. Consent to the processing of personal data</b></p>
                                <p>I, <u>${declaration.diver.surname} ${declaration.diver.name} ${declaration.diver.middle_name}</u>
                                    accept to the processing of my personal data: ${declaration.consent==false ? "Y,<u>N</u>" : "<u>Y</u>, N" } (choose)</p>

                                <p><b>4. Diver</b></p>
                                <p>Sign up:__________________________________</p>
                                <p><b>5. Date</b></p>
                                <p>${date}</p>
                            </div>
                            <center>
                                <a href="/diverPro1/divers/1" class="btn btn_form_add">Next</a>
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