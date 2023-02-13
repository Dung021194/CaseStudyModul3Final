
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>JSP Page</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>

<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container px-4 px-lg-5">
    <a class="navbar-brand" href="#!">C10 PetShop</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
        <li class="nav-item"><a class="nav-link active" aria-current="page" href="home">Home</a></li>
        <c:if test="${sessionScope.account.email != 'admin@gmail.com'}">
          <li class="nav-item"><a class="nav-link" href="#!">About</a></li>
        </c:if>
        <c:if test="${sessionScope.account.email == 'admin@gmail.com'}">
          <li class="nav-item"><a class="nav-link" href="ManagerPet">Pet Manager</a></li>
        </c:if>
        <c:if test="${sessionScope.account.email == 'admin@gmail.com'}">
          <li class="nav-item"><a class="nav-link" href="ManagerPet">Cart Manager</a></li>
        </c:if>
        <c:if test="${sessionScope.account.email != 'admin@gmail.com'}">
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" id="priceSearchDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Search Price</a>
            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
              <form action="/home?action=searchPrice" method="post">
                <li><input class="nav-item" type="number" name="from" placeholder="From"/></li>
                <li><input class="nav-item" type="number" name="to" placeholder="To"/></li>
                <li><button type="submit">Search</button> </li>
              </form>

            </ul>
          </li></c:if>
        <c:if test="${sessionScope.account != null}">
          <li class="nav-item" style="padding:8px" >
            Hello, ${sessionScope.account.fullName}
          </li> </c:if>
      </ul>
      <form class="d-flex">
        <button class="btn btn-outline-dark" type="submit">
          <i class="bi-cart-fill me-1"></i>
          Cart
          <span class="badge bg-dark text-white ms-1 rounded-pill">${sessionScope.cartDetailNum}</span>
        </button>
      </form>
      <div style="text-align: right">
        <c:if test="${sessionScope.account == null}">
          <a href="login.jsp">
            <button class="btn btn-outline-dark" type=button style="margin: 15px">
              Login
              <span class="badge bg-dark text-white ms-1 rounded-pill"></span>
            </button>
          </a>
        </c:if>
        <c:if test="${sessionScope.account != null}">
          <a href="LogOutServlet">
            <button class="btn btn-outline-dark" type=button style="margin-left: 10px">
              Logout
              <span class="badge bg-dark text-white ms-1 rounded-pill"></span>
            </button>
          </a>

        </c:if>
      </div>
    </div>
  </div>
</nav>
<header class="bg-dark py-5">
  <div class="container px-4 px-lg-5 my-5">
    <div class="text-center text-white">
      <h1 class="display-4 fw-bolder">C10 PetShop</h1>
      <p class="lead fw-normal text-white-50 mb-0">Always bring best for you</p>
    </div>
  </div>
</header>
<div class="shopping-cart">
  <div class="px-4 px-lg-0">

    <div class="pb-5">
      <div class="container">
        <div class="row">
          <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">

            <!-- Shopping cart table -->
            <div class="table-responsive">
              <table class="table">
                <thead>
                <p class="text-danger">${delPetMess}</p>
                <tr>
                  <th scope="col" class="border-0 bg-light">
                    <div class="p-2 px-3 text-uppercase">ID</div>
                  </th>
                  <th scope="col" class="border-0 bg-light">
                    <div class="p-2 px-3 text-uppercase">customer ID</div>
                  </th>
                  <th scope="col" class="border-0 bg-light">
                    <div class="py-2 text-uppercase">Total</div>
                  </th>
                  <th scope="col" class="border-0 bg-light">
                    <div class="py-2 text-uppercase">Order Date</div>
                  </th>
                  <th scope="col" class="border-0 bg-light">
                    <div class="py-2 text-uppercase">Delete</div>
                  </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${listOrder}" var="o">
                  <tr>
                    <td class="align-middle"><strong>${o.id}</strong></td>
                    <td class="align-middle"><strong>${o.customerId} </strong></td>
                    <td class="align-middle">
                      <strong>${o.totalPayment} $</strong>
                    </td>
                    <td class="align-middle"><strong>${o.paymentDate}</strong></td>
                    <td class="align-middle">
                      <button onclick="confirmDelete(${o.customerId})" class="text-dark" type="button" class="btn btn-danger">Delete</button>
                    </td>
                  </tr>
                </c:forEach>
                </tbody>
              </table>
            </div>
            <!-- End -->
          </div>
        </div>

      </div>
    </div>
  </div>
</div>
<script>
  function confirmDelete(id) {
    if (confirm("Are you sure you want to cancel this order?")) {
      window.location.href = "CartServlet?action=adminCancelOrder&id="+id
    }
  }
</script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
<footer class="py-5 bg-dark">
  <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2022</p></div>
</footer>
</html>
</html>