<%--
  Created by IntelliJ IDEA.
  User: se-9
  Date: 25/09/2017
  Time: 4:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<html lang="en">
<head>
    <title>AppointmentUI</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        /* Remove the navbar's default margin-bottom and rounded borders */
        .navbar {
           /* margin-bottom: 0;*/
            border-radius: 0;
        }

        .navbar-inverse {
            background-color: rgb(255, 255, 255);
            border-color: #080808;
        }


        /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
        .row.content {height: 450px}

        /* Set gray background color and 100% height */
        .sidenav {
            padding-top: 20px;
            background-color: #f1f1f1;
            height: 100%;
        }

        /* Set black background color, white text and some padding */
        footer {
            background-color: #555;
            color: white;
            padding: 15px;
        }

        /* On small screens, set height to 'auto' for sidenav and grid */
        @media screen and (max-width: 767px) {
            .sidenav {
                height: auto;
                padding: 15px;
            }
            .row.content {height:auto;}
        }

        .navbar-inverse .navbar-brand {
            color: #000000;
        }

    </style>
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="www.appointment.lk"><img src="<c:url value="/resources/images/animal.png" />" alt="Appointment" style="width:64px;height:50px;"></a>
        </div>

            <p class="navbar-brand">Center Name</p>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav navbar-right">
                <li><span class="glyphicon glyphicon-log-in"></span> Sewwandi Ginige</li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-2 sidenav">
            <p><a href="#">Make Appointment</a></p>
            <p><a href="#">Create Session</a></p>
            <p><a href="#">Edit Session</a></p>
            <p><a href="#">Delete Session</a></p>
            <p><a href="#">Create User</a></p>
            <p><a href="#">View User</a></p>
            <p><a href="#">Search Appointment</a></p>
            <p><a href="#">View Appointment</a></p>
            <p><a href="#">Logout</a></p>
        </div>
        <div class="col-sm-8 text-left">
            <h1>Welcome</h1>
            <p>A veterinarian or a vet, is someone who gives animals medical treatment. They are doctors for animals. There are many different types of veterinarians; some work with small animals, large farm animals, or wild animals. Veterinarians learn to treat all animals, but sometimes work with a specific type. Other veterinarians specialize in a particular type of medicine - for example, veterinary dermatologists work with animals with skin problems. Finally, some veterinarians do research on animal or human diseases.</p>
            <hr>
            <div class="navbar-brand"><img src="<c:url value="/resources/images/pet.jpg" />" alt="Appointment" style="width:768px;height:200px;"></div>
        </div>
    </div>
</div>

<footer class="container-fluid text-center">
    <p>Copyright  Appointment.lk</p>
</footer>

</body>
</html>
