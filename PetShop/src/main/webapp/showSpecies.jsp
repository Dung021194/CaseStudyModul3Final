
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Update Pet</title>
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
  <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link href="css/manager.css" rel="stylesheet" type="text/css"/>
  <style>
    img{
      width: 200px;
      height: 120px;
    }
  </style>
<body>
  <div class="container">
    <div class="table-wrapper">
      <div class="table-title">
        <div class="row">
          <div class="col-sm-6">
            <h2>Edit <b>Pet</b></h2>
          </div>
          <div class="col-sm-6">
          </div>
        </div>
      </div>
    </div>
    <div id="editEmployeeModal">
      <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
              <h4 class="modal-title">Update Pet</h4>
              <button onclick="backHome()" type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            </div>
            <div class="modal-body">
              <table>
                <tr>
                  <th class="text-light">ID</th>
                  <th class="text-light">Name</th>
                  <th>Action</th>
                </tr>
                <c:forEach items="${listSpecies}" var="s">
                <tr>
                  <td class="text-light">${s.id}</td>
                  <td class="text-light">${s.name}</td>
                   <td>
                     <button onclick="confirmDeleteSpecies(${s.id})" class="delete" ><i
                          class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></button>

                </tr>
                </c:forEach>
              </table>
            </div>
<%--            <div class="modal-footer">--%>
<%--              <input type="submit" class="btn btn-success" value="Edit">--%>
<%--            </div>--%>
        </div>
      </div>
    </div>

  </div>
  <script>
    function confirmDeleteSpecies(id) {
      if (confirm("Are you sure you want to delete?")) {
        window.location.href = "/SpeciesServlet?action=delete&id="+id
      }
    }
  </script>
<script>
  function backHome(){
    window.location.href="/ManagerPet"
  }
</script>
<script src="js/manager.js" type="text/javascript"></script>
</body>
</html>
