<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Shop Homepage</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
    </head>
    <body>
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container px-4 px-lg-5">
                <form action="home?action=searchPet" method="post">
                    <input class="nav-item"  type="text" name="searchPet"/>
                <button class="nav-item"  type="submit" >Search</button>

                </form>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="home">Home</a></li>
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
                        <c:if test="${sessionScope.account.email == 'admin@gmail.com'}">
                            <li class="nav-item"><a class="nav-link" href="ManagerPet">Pet Manager</a></li>
                        </c:if>
                        <c:if test="${sessionScope.account.email == 'admin@gmail.com'}">
                            <li class="nav-item"><a class="nav-link" href="CartServlet?action=adminManagerOder">Cart Manager</a></li>
                        </c:if>

                         <c:if
                                 test="${sessionScope.account.email != 'admin@gmail.com'}">
                            <li class="nav-item"><a class="nav-link" href="CartServlet?action=cartManager">Cart Manager</a></li>
                        </c:if>

                        <c:if test="${sessionScope.account.email != 'admin@gmail.com'}">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">All Species</a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <c:forEach items="${listSpecies}" var="o">
                                <li><a class="dropdown-item" href="home?action=showSpecies&id=${o.id}">${o.name}</a></li>
                                </c:forEach>
                            </ul>
                        </li></c:if>
                      <c:if test="${sessionScope.account != null}">
                      <li class="nav-item" style="padding:8px" >
                     Hello, ${sessionScope.account.fullName}
                      </li>
                      </c:if>
                        <li class="text-danger" style="padding:8px" >
                              ${messPayment}
                        </li>
                    </ul>
                    <form action="CartServlet?action=paymentReview" method="post" class="d-flex">
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
        <!-- Header-->
        <header class="bg-dark py-5">
            <div class="container px-4 px-lg-5 my-5">
                <div class="text-center text-white">
                    <h1 class="display-4 fw-bolder">C10 PetShop</h1>
                    <p class="lead fw-normal text-white-50 mb-0">Always bring best for you</p>
                </div>
            </div>
        </header>
        <!-- Section-->
        <section class="py-5">
            <div class="container px-4 px-lg-5 mt-5">
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                    <c:forEach items="${listPet}" var="p">
                         <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="${p.image}" alt="..." />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">${p.name}</h5>
                                    <!-- Product price-->
                                    ${p.price} $ <br>
                                    <c:choose>
                                    <c:when test="${p.quantity > 0}">
                                        Status: Available
                                    </c:when>
                                    <c:otherwise>
                                        Status: Unavailable
                                    </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <!-- Product actions-->
                            <c:if test="${p.quantity > 0}">
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="CartServlet?action=add&id=${p.id}">Add to cart</a></div>
                            </div>
                            </c:if>
                        </div>
                    </div>
                    </c:forEach>
                    </div>
                </div>
        </section>
        <!-- Footer-->
        <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">Copyright &copy; CodeGym 2023</p></div>
        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
    </body>
</html>
