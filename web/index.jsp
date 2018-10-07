<%@page contentType="text/html" pageEncoding="UTF-8"
        language="java"
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>PIP_Lab_2</title>
    <style> <%@include file='css/main.css' %> </style>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"> </script>
    <script type="text/javascript" src="scripts.js"> </script>
</head>
<body>

    <header>
        P3217 &nbsp;
        Кузенкова Елизавета Владимировна &nbsp;
        Вариант 66666666
    </header>

    <!--<canvas id="canvas" style="background-color:lavender; " width="300" height="300"></canvas>-->
    <img src="area.jsp?R=1" id="areaImg" />
    <form class="form" id="form" action="control" method="post" onsubmit="return validate(this);">

        <label> X = </label>
        <input type="checkbox" class="X" id="-4" name="X" value="-4">-4
        <input type="checkbox" class="X" id="-3" name="X" value="-3">-3
        <input type="checkbox" class="X" id="-2" name="X" value="-2">-2
        <input type="checkbox" class="X" id="-1" name="X" value="-1">-1
        <input type="checkbox" class="X" id="0" name="X" value="0" checked>0
        <input type="checkbox" class="X" id="1" name="X" value="1">1
        <input type="checkbox" class="X" id="2" name="X" value="2">2
        <input type="checkbox" class="X" id="3" name="X" value="3">3
        <input type="checkbox" class="X" id="4" name="X" value="4">4
        <br/>

        <label> Y = </label>
        <input class="input_Y" id="Y" type="text" name="Y" placeholder="(-5 .. 3)" />
        <br/>

        <label> R = </label>
        <input type="checkbox" class="R" id="R_1" name="R1" value="1" checked />1
        <input type="checkbox" class="R" id="R_2" name="R2" value="1.5" />1.5
        <input type="checkbox" class="R" id="R_3" name="R3" value="2" />2
        <input type="checkbox" class="R" id="R_4" name="R4" value="2.5" />2.5
        <input type="checkbox" class="R" id="R_5" name="R5" value="3" />3
        <br />

        <div id="empty_div"></div>

        <input type="hidden" name="formAction" value="check" />
        <input class="submit" type="submit" value=" ПРОВЕРИТЬ " />
    </form>

</body>
</html>