<%@ page
        import="teaches.Teaches,registers.Registers,java.util.ArrayList,java.util.HashMap"
        language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Courses</title>
    <style>
        body {
            background: url(pictures/studentPortal.jpg) no-repeat;
            background-size: cover;
            margin: 0;
            padding: 0;
            font-family: Arial, Helvetica, sans-serif;
        }

        .topnav {
            overflow: hidden;
            background-color: #030024;
        }

        .topnav a {
            float: left;
            color: #f2f2f2;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 17px;
        }

        .topnav a:hover {
            background-color: #ddd;
            color: black;
        }

        .topnav a.active {
            background-color: #010d42;
            color: white;
        }
    </style>
</head>
<body>
<% String instructorID = request.getParameter("instructorID"); // get ID from previous page %>
<div class="topnav">
    <a class="active" href=<%="/CMS/instructorPortal.jsp?instructorID="+instructorID%>>Instructor Portal</a>
    <a href=<%="ins_add.jsp?instructorID="+instructorID%>>Add Courses</a>
    <a href=<%="ins_drop.jsp?instructorID="+instructorID%>>Drop Courses</a>
    <%--    <a href=<%="viewCourses.jsp?studentID="+studentID%>>View Courses</a>--%>
    <%--    <a href=<%="transcript.jsp?studentID="+studentID%>>Transcript</a>--%>
    <%--    <a href=<%="transactions.jsp?studentID="+studentID%>>Transactions</a>--%>
    <a href="/CMS/index.jsp">Logout</a>
</div>
<%--<h1>Assigned Courses</h1>--%>
<%--<table style="width: 100%">--%>
<%--    <tr>--%>
<%--        <td>Term</td>--%>
<%--        <td>Year</td>--%>
<%--        <td>Department</td>--%>
<%--        <td>Number</td>--%>
<%--        <td>ConfigID</td>--%>
<%--    </tr>--%>
<%--    <%--%>
<%--        ArrayList<HashMap<String, String>> regCourses = Registers.ins_viewAssignedCourses(instructorID);--%>
<%--        for (int i = 0; i < regCourses.size(); i++) {--%>
<%--            HashMap<String, String> regCourse = regCourses.get(i);--%>
<%--    %>--%>
<%--    <tr>--%>
<%--        <td><%=regCourse.get("term")%></td>--%>
<%--        <td><%=regCourse.get("year")%></td>--%>
<%--        <td><%=regCourse.get("department")%></td>--%>
<%--        <td><%=regCourse.get("number")%></td>--%>
<%--        <td><%=regCourse.get("configID")%></td>--%>
<%--    </tr>--%>

<%--    <%--%>
<%--        }--%>
<%--    %>--%>
<%--</table>--%>
<h1>Add Courses</h1>
<table style="width: 100%">
    <tr>
        <td>Department</td>
        <td>Number</td>
        <td>Title</td>
        <td>Units</td>
        <td>ConfigID</td>
        <td>Term</td>
        <td>Year</td>
        <td>Days</td>
        <td>Time</td>
        <td>Room</td>
        <td>Seats</td>
    </tr>
    <%
        ArrayList<HashMap<String, String>> courses = Teaches.ins_viewAssignedCourses(instructorID);
        for (int i = 0; i < courses.size(); i++) {
            HashMap<String, String> course = courses.get(i);
    %>

    <tr>
        <td><%=course.get("department")%></td>
        <td><%=course.get("number")%></td>
        <td><%=course.get("title")%></td>
        <td><%=course.get("units")%></td>
        <td><%=course.get("configID")%></td>
        <td><%=course.get("term")%></td>
        <td><%=course.get("year")%></td>
        <td><%=course.get("days")%></td>
        <td><%=course.get("time")%></td>
        <td><%=course.get("room")%></td>
        <td><%=course.get("seats")%></td>
        <td>
            <form action="/CMS/addCourse" method="post">
                <input type="submit" value="Add">
                <input type="hidden" id="studentID" name="studentID" value = "Enter Student ID">
                <input type="hidden" id="configID" name="configID" value=<%=course.get("configID")%>>
                <input type="hidden" id="department" name="department" value=<%=course.get("department")%>>
                <input type="hidden" id="number" name="number" value=<%=course.get("number")%>>
            </form>
        </td>
    </tr>
    <%
        }
    %>
</table>


</body>
</html>