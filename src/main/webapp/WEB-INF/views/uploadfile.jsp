<%--
  Created by IntelliJ IDEA.
  User: tsamsiyu
  Date: 01.03.16
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Testing of file uploading</title>
</head>
    <body>
        <form action="upload-file" enctype="multipart/form-data" method="POST">
            <div>
                <input type="file" name="file">
            </div>
            <div>
                <button>Submit form</button>
            </div>
        </form>
    </body>
</html>
