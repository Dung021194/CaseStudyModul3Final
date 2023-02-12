

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Pet Manager</title>
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
                        <a class="col-sm-6" href="ManagerPet">
                            <h2>Manage <b>Pet</b></h2></a>
                        </div>
                        <p class="text-danger"> ${messDelete}</p>
                    <p class="text-light"> ${messCreate}</p>

                        <div class="col-sm-6">
                            <a href="#addPetModal"  class="btn btn-success" data-toggle="modal"><i
                                    class="material-icons">&#xE147;</i> <span>Add New Pet</span></a>
                            <a href="#addSpeciesModal"  class="btn btn-success" data-toggle="modal"><i
                                    class="material-icons">&#xE147;</i> <span>Add New Species</span></a>
                            <a href="/SpeciesServlet"  class="btn btn-success" data-toggle="modal"><i
                                    class="material-icons">&#xE147;</i> <span>Show Species List</span></a>
                        </div>
                    </div>
                </div>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>
                                <span class="custom-checkbox">
                                    <input type="checkbox" id="selectAll">
                                    <label for="selectAll"></label>
                                </span>
                            </th>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Age</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Species</th>
                            <th>Image</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listP}" var="o">
                            <tr>
                                <td>
                                    <span class="custom-checkbox">
                                        <input type="checkbox" id="checkbox1" name="options[]" value="1">
                                        <label for="checkbox1"></label>
                                    </span>
                                </td>
                                <td>${o.id}</td>
                                <td>${o.name}</td>
                                <td>${o.age}</td>
                                <td>${o.price}</td>
                                <td>${o.quantity}</td>
                                <td>${o.species.name}</td>

                                <td style="width: 200px">
                                    <img src="${o.image}" style="object-fit: cover; width: 100%; height: 100%">
                                </td>
                                <td>
                                    <a href="/ManagerPet?action=update&id=${o.id}"  class="edit" data-toggle="modal"><i 
                                            class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>

                                    <button onclick="confirmDelete(${o.id})" class="delete" ><i
                                            class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></button>

                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="clearfix">
                    <div class="hint-text">Showing <b>5</b> out of <b>25</b> entries</div>
                    <ul class="pagination">
                        <li class="page-item disabled"><a href="#">Previous</a></li>
                        <li class="page-item"><a href="#" class="page-link">1</a></li>
                        <li class="page-item"><a href="#" class="page-link">2</a></li>
                        <li class="page-item active"><a href="#" class="page-link">3</a></li>
                        <li class="page-item"><a href="#" class="page-link">4</a></li>
                        <li class="page-item"><a href="#" class="page-link">5</a></li>
                        <li class="page-item"><a href="#" class="page-link">Next</a></li>
                    </ul>
                </div>
            </div>
            <a href="home"><button type="button" class="btn btn-primary">Back to home</button> </a>

        </div>
        <!-- Edit Modal HTML -->
        <div id="addPetModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="/ManagerPet?action=create" method="post">
                        <div class="modal-header">						
                            <h4 class="modal-title">Add Pet</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <label>Name</label>
                                <input name="name" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Age</label>
                                <input name="age" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Price</label>
                                <input name="price" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Quantity</label>
                                <input name="quantity" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Image</label>
                                <input name="image" type="text" class="form-control" required>

                            </div>
                            <div class="form-group">
                                <label>Species</label>
                                <select name="species" class="form-select" aria-label="Default select example">
                                    <c:forEach items="${listSpecies}" var="o">
                                        <option value="${o.id}">${o.name}</option>
                                    </c:forEach>
                                </select>
                            </div>

                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-success" value="Add">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div id="addSpeciesModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="/SpeciesServlet?action=create" method="post">
                        <div class="modal-header">
                            <h4 class="modal-title">Add Species</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label>Name</label>
                                <input name="name" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Description</label>
                                <input name="description" type="text" class="form-control" required>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-success" value="Add">
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <script>
            function confirmDelete(id) {
                if (confirm("Are you sure you want to delete?")) {
                    window.location.href = "/ManagerPet?action=delete&id="+id
                }
            }
        </script>
    <script src="js/manager.js" type="text/javascript"></script>
</body>
</html>