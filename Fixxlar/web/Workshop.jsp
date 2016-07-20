<%-- 
    Document   : workshop
    Created on : May 5, 2016, 10:00:14 AM
    Author     : joanne.ong.2014
--%>

<%@page import="dao.FetchEmailDAO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="entity.Vehicle"%>
<%@page import="entity.Customer"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="entity.QuotationRequest"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.QuotationRequestDAO"%>
<%@page import="dao.EmailDAO"%>
<%@page import="javax.mail.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="ProtectWorkshop.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Hello workshop</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta charset="UTF-8" />

        <link rel="icon" type="image/ico" href="images/favicon.ico" />
        <!-- Bootstrap -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/animate.css">
        <link type="text/css" rel="stylesheet" media="all" href="css/jquery.mmenu.all.css" />
        <link rel="stylesheet" href="css/jquery.videobackground.css">
        <link rel="stylesheet" href="css/bootstrap-checkbox.css">

        <link href="css/minimal.css" rel="stylesheet">
        <link rel="stylesheet" href="css/custom.css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
    </head>
    <body class="bg-3">
        <!--<h1>Welcome</h1>-->
        <%            String successChangePasswordMsg = (String) request.getAttribute("successChangePasswordMsg");
            if (successChangePasswordMsg != null) {
                out.println(successChangePasswordMsg + "<br/><br/>");
            }
        %>

        <!-- Preloader -->
        <div class="mask"><div id="loader"></div></div>
        <!--/Preloader -->

        <!-- Wrap all page content here -->
        <div id="wrap">
            <!-- Make page fluid -->
            <div class="row">
                <!-- Top and leftbar -->
                <jsp:include page="include/topbar.jsp"/>
                <!-- Top and leftbar end -->

                <!-- Page content -->
                <div id="content" class="col-md-12">

                    <!-- page header -->
                    <div class="pageheader">

                        <h2><i class="fa fa-file-o" style="line-height: 48px;padding-left: 2px;"></i> Dashboard <span>// overview </span></h2>


                        <!--                        <div class="breadcrumbs">
                                                    <ol class="breadcrumb">
                                                        <li>You are here</li>
                                                        <li><a href="index.html">Minimal</a></li>
                                                        <li><a href="#">Example Pages</a></li>
                                                        <li class="active">Blank Page</li>
                                                    </ol>
                                                </div>-->


                    </div>
                    <!-- /page header -->

                    <!-- content main container -->
                    <div class="main">
                        <!-- row -->
                        <div class="row">

                            <!-- col 12 -->
                            <div class="col-md-12">

                                <section class="tile transparent">
                                    <%--
                                    <!-- tile header -->
                                    <div class="tile-header transparent">
                                        <h1><strong>Today</strong> at a glance</h1>
                                        <div class="controls">
                                            <a href="#" class="minimize"><i class="fa fa-chevron-down"></i></a>
                                            <a href="#" class="refresh"><i class="fa fa-refresh"></i></a>
                                        </div>
                                    </div>
                                    <!-- /tile header -->
                                    --%>
                                    <!-- tile body -->
                                    <div class="tile-body color transparent-black rounded-corners">

                                        <!-- cards -->
                                        <div class="row cards">

                                            <div class="card-container col-lg-3 col-sm-6 col-sm-12">
                                                <div class="card card-redbrown hover">
                                                    <div class="front"> 

                                                        <div class="media">        
                                                            <span class="pull-left">
                                                                <i class="fa fa-users media-object"></i>
                                                            </span>

                                                            <div class="media-body">
                                                                New Requests
                                                                <h2 class="media-heading animate-number" data-value="7" data-animation-duration="1500">0</h2>
                                                            </div>
                                                        </div> 

                                                    </div>
                                                    <div class="back">
                                                        <a href="ViewRequest.jsp#New">
                                                            <i class="fa fa-bar-chart-o fa-4x"></i>
                                                            <span>More Information</span>
                                                        </a>  
                                                    </div>
                                                </div>
                                            </div>


                                            <div class="card-container col-lg-3 col-sm-6 col-sm-12">
                                                <div class="card card-blue hover">
                                                    <div class="front">        

                                                        <div class="media">                  
                                                            <span class="pull-left">
                                                                <i class="fa fa-shopping-cart media-object"></i>
                                                            </span>

                                                            <div class="media-body">
                                                                Pending-Valet Requests
                                                                <h2 class="media-heading animate-number" data-value="5" data-animation-duration="1500">0</h2>
                                                            </div>
                                                        </div> 

                                                    </div>
                                                    <div class="back">
                                                        <a href="ViewRequest.jsp#Valet">
                                                            <i class="fa fa-bar-chart-o fa-4x"></i>
                                                            <span>More Information</span>
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>



                                            <div class="card-container col-lg-3 col-sm-6 col-sm-12">
                                                <div class="card card-greensea hover">
                                                    <div class="front">        

                                                        <div class="media">
                                                            <span class="pull-left">
                                                                <i class="fa fa-usd media-object"></i>
                                                            </span>

                                                            <div class="media-body">
                                                                Completed Requests
                                                                <h2 class="media-heading animate-number" data-value="23" data-animation-duration="1500">0</h2>
                                                            </div>
                                                        </div>



                                                    </div>
                                                    <div class="back">
                                                        <a href="ViewRequest.jsp#Completed">
                                                            <i class="fa fa-bar-chart-o fa-4x"></i>
                                                            <span>More Information</span>
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="card-container col-lg-3 col-sm-6 col-xs-12">
                                                <div class="card1 card-slategray hover">
                                                    <div class="front"> 

                                                        <div class="media">                   
                                                            <span class="pull-left">
                                                                <i class="fa fa-eye media-object"></i>
                                                            </span>

                                                            <div class="media-body">
                                                                Average Rating
                                                                <h2 class="media-heading animate-number" data-value="4.2" data-animation-duration="1500">0</h2>
                                                            </div>
                                                        </div> 
                                                    </div>
                                                    <!--                                                    <div class="back">
                                                                                                            <a href="#">
                                                                                                                <i class="fa fa-bar-chart-o fa-4x"></i>
                                                                                                                <span>Check Summary</span>
                                                                                                            </a>
                                                                                                        </div>-->
                                                </div>
                                            </div>
                                        </div>
                                        <!-- /cards -->
                                    </div>
                                    <!-- /tile body -->
                                </section>
                                <!-- /tile -->
                            </div>
                            <!-- /col 12 -->        
                        </div>
                        <!-- /row -->



                        <!-- row -->
                        <div class="row">
                            <!-- col 8 -->
                            <div class="col-lg-8 col-md-12">


                                <!-- tile -->
                                <section class="tile color transparent-black">




                                    <!-- tile header -->
                                    <div class="tile-header">
                                        <h1 style="margin-right: 20px;"><strong>NEW</strong> Requests</h1>
                                        <div class="search">
                                            <input type="search" class="light-table-filter" data-table="order-table" placeholder="Filter">
                                        </div>
                                        <div class="controls">
                                            <a href="#" class="minimize"><i class="fa fa-chevron-down"></i></a>
                                            <a href="#" class="refresh"><i class="fa fa-refresh"></i></a>
                                            <a href="#" class="remove"><i class="fa fa-times"></i></a>
                                        </div>
                                    </div>
                                    <!-- /tile header -->


                                    <!-- tile body -->
                                    <div class="tile-body no-vpadding">
                                        <div class="table-responsive">
                                            <table class="table table-custom1 table-sortable1 tablesorter order-table " id="myTable">
                                                <thead>
                                                    <tr>
                                                        <th class="sortable">DateTime</th>
                                                        <th class="sortable">Name</th>
                                                        <th class="sortable">No. Plate</th>
                                                        <th class="sortable">Services</th>
                                                        <th class="sortable">Email</th>
                                                        <th class="sortable">Phone</th>
                                                        <th>Attachment</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <!--Loop per new request-->
                                                    <%
                                                        QuotationRequestDAO qDAO = new QuotationRequestDAO();
                                                        HashMap<Integer, QuotationRequest> qList = qDAO.retrieveAllQuotationRequests(user.getStaffId(), user.getToken(), 0, 0, "", "requested_datetime", "desc");
                                                        Iterator it = qList.entrySet().iterator();
                                                        while (it.hasNext()) {
                                                            Map.Entry pair = (Map.Entry) it.next();
                                                            QuotationRequest qr = (QuotationRequest) pair.getValue();
                                                            int i = 1;
                                                            Timestamp timeStamp = qr.getDate();
                                                            String dateTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(timeStamp);
                                                            String serviceName = qr.getName();
                                                            String address = qr.getAddress();
                                                            String serviceAmenities = qr.getAmenities();
                                                            String serviceDescription = qr.getDescription();
                                                            String serviceDetails = qr.getDetails();
                                                            int serviceId = qr.getId();
                                                            String serviceMileage = qr.getMileage();
                                                            String photo = qr.getPhotos();
                                                            int serviceStatus = qr.getStatus();
                                                            String serviceUrgency = qr.getUrgency();

                                                            Customer cust = qr.getCustomer();
                                                            String custName = cust.getName();
                                                            String custEmail = cust.getEmail();
                                                            String custPhone = cust.getHandphone();

                                                            Vehicle vehicle = qr.getVehicle();
                                                            String carPlate = vehicle.getPlateNumber();
                                                            String carModel = vehicle.getModel();
                                                            String carMake = vehicle.getMake();
                                                            int carYear = vehicle.getYear();
                                                            String carColor = vehicle.getColour();
                                                            String carControl = vehicle.getControl();

                                                    %>
                                                    <tr>
                                                        <td><% out.print(dateTime);%></td>
                                                        <td><% out.print(custName);%></td>
                                                        <td><% out.print(carPlate);%></td>
                                                        <td><% out.print(serviceName);%></td>
                                                        <td><% out.print(custEmail);%></td>
                                                        <td><% out.print(custPhone);%></td>
                                                        <!--Picture Attachment-->
                                                        <td class="text-center"><a href="<% out.print("#myModal" + i);%>" id="myBtn" data-toggle="modal"><img src="images/file.png"/></a></td>

                                                        <!-- Modal -->
                                                <div class="modal fade" id="<% out.print("myModal" + i);%>" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog-img">
                                                        <div class="modal-content">
                                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                                            <div class="modal-header">
                                                                <h4 class="modal-title"><% out.print(carMake + " " + carModel + " - " + carYear);%></h4>
                                                            </div>
                                                            <div class="modal-body">
                                                                <img class="img-responsive"src="http://buyersguide.caranddriver.com/media/assets/submodel/6985.jpg"/>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                                <!--<button type="button" class="btn btn-primary">Save changes</button>-->
                                                            </div>
                                                        </div> <!--/.modal-content--> 
                                                    </div><!-- /.modal-dialog -->
                                                </div><!-- /.modal -->

                                                </tr>

                                                <%
                                                        i++;
                                                    }
                                                %>

                                                </tbody>

                                            </table>
                                        </div>

                                    </div>


                                    <!-- /tile body -->


                                    <!-- tile footer -->
                                    <div class="tile-footer text-center">
                                        <ul class="pagination pagination-sm nomargin pagination-custom">
                                            <li class="disabled"><a href="#"><i class="fa fa-angle-double-left"></i></a></li>
                                            <li class="active"><a href="#">1</a></li>
                                            <li><a href="#">2</a></li>
                                            <li><a href="#">3</a></li>
                                            <li><a href="#">4</a></li>
                                            <li><a href="#">5</a></li>
                                            <li><a href="#"><i class="fa fa-angle-double-right"></i></a></li>
                                        </ul>
                                    </div>
                                    <!-- /tile footer -->



                                </section>
                                <!-- /tile -->
                            </div>
                            <!-- /col 8 -->


                            <div class="main vertical-mail">        

                                <div class="col-md-4">
                                    <%-- Instantiate EmailDAO object to call methods --%>
                                    <%
                                        FetchEmailDAO email = new FetchEmailDAO();
                                        Message[] emailArr = email.fetchEmail();
                                    
                                    %>
                                    <%-- /instantiate fetchemailDAO --%>
                                    
                                    <ul class="inbox" id="mail-inbox">
                                        <li class="heading"><h3>Inbox</h3></li>
                                            <%--
                                            <li class="search"><i class="fa fa-search"></i> <input type="text" placeholder="Search in inbox..." /></li>
                                            --%>
                                        <li class="msg">
                                            <div class="checkbox check-transparent">
                                                <input type="checkbox" value="1" checked id="msg01">
                                                <label for="msg01"></label>
                                            </div>
                                            <a href="#" class="mail-favourite active"><i class="fa fa-star-o"></i></a>
                                            <div>
                                                <h5><strong>Lucius</strong> Cashmere (5)</h5>
                                                <h5><strong>Subject:</strong> <%out.print(emailArr[0].getSubject()); %></h5>
                                                <span class="delivery-time"><%out.print(emailArr[0].getSentDate()); %></span>
                                                <div class="mail-attachment"><i class="fa fa-paperclip"></i></div>
                                                <div class="mail-label bg-red"></div>
                                                <div class="mail-actions">
                                                    <a href="#" class="reply"><i class="fa fa-reply"></i></a> 
                                                    <a href="#" class="delete"><i class="fa fa-trash-o"></i></a>
                                                </div>
                                            </div>
                                        </li>
                                        <li class="msg">
                                            <div class="checkbox check-transparent">
                                                <input type="checkbox" id="msg02">
                                                <label for="msg02"></label>
                                            </div>
                                            <a href="#" class="mail-favourite"><i class="fa fa-star-o"></i></a>
                                            <div>
                                                <h5><strong>Jesse</strong> Phoenix</h5>
                                                <h5><strong>Subject:</strong> <%out.print(emailArr[1].getSubject()); %>;</h5>
                                                <span class="delivery-time"><%out.print(emailArr[1].getSentDate()); %></span>
                                                <div class="mail-attachment"><i class="fa fa-paperclip"></i></div>
                                                <div class="mail-label bg-red"></div>
                                                <div class="mail-actions">
                                                    <a href="#" class="reply"><i class="fa fa-reply"></i></a> 
                                                    <a href="#" class="delete"><i class="fa fa-trash-o"></i></a>
                                                </div>
                                            </div>
                                        </li>

                                        <li class="msg last">
                                            <div class="checkbox check-transparent">
                                                <input type="checkbox" id="msg10">
                                                <label for="msg10"></label>
                                            </div>
                                            <a href="#" class="mail-favourite active"><i class="fa fa-star-o"></i></a>
                                            <div>
                                                <h5><strong>Tex</strong> Montreal (4)</h5>
                                                <h5><strong>Subject:</strong> <%out.print(emailArr[2].getSubject()); %>;</h5>
                                                <span class="delivery-time"><%out.print(emailArr[2].getSentDate()); %></span>
                                                <div class="mail-label bg-greensea"></div>
                                                <div class="mail-actions">
                                                    <a href="#" class="reply"><i class="fa fa-reply"></i></a> 
                                                    <a href="#" class="delete"><i class="fa fa-trash-o"></i></a>
                                                </div>
                                            </div>
                                        </li>
                                        <li class="pagination pull-right">
                                            <span>1-3 of <%=emailArr.length  %></span>
                                            <div class="btn-group btn-group-sm">
                                                <button type="button" class="btn btn-default"><i class="fa fa-angle-left"></i></button>
                                                <button type="button" class="btn btn-default"><i class="fa fa-angle-right"></i></button>
                                            </div>
                                        </li>
                                    </ul>
                                </div>


                            </div>
                        </div>
                        <!-- /row -->

                    </div>

                </div>
            </div>

        </div>
        <!-- /wrap -->
    </div>
    <!-- /content container -->
</div>
<!-- Page content end -->




<jsp:include page="include/rightbar.jsp"/>






</div>
<!-- Make page fluid-->




</div>
<!-- Wrap all page content end -->



<section class="videocontent" id="video"></section>
</body>



<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://code.jquery.com/jquery.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-dropdown-multilevel.js"></script>
<script src="https://google-code-prettify.googlecode.com/svn/loader/run_prettify.js?lang=css&amp;skin=sons-of-obsidian"></script>
<script type="text/javascript" src="js/jquery.mmenu.min.js"></script>
<script type="text/javascript" src="js/jquery.sparkline.min.js"></script>
<script type="text/javascript" src="js/jquery.nicescroll.min.js"></script>
<script type="text/javascript" src="js/jquery.animateNumbers.js"></script>
<script type="text/javascript" src="js/jquery.videobackground.js"></script>
<script type="text/javascript" src="js/jquery.blockUI.js"></script>

<script src="js/minimal.min.js"></script>

<script type="text/javascript" src="js/jquery.tablesorter.js"></script> 

<script>
    $(document).ready(function () {
        $('#myTable').tablesorter();
    });
</script>
<script>
    $(function () {
        // Initialize card flip
        $('.card.hover').hover(function () {
            $(this).addClass('flip');
        }, function () {
            $(this).removeClass('flip');
        });

        // sortable table
//            $('.table.table-sortable th.sortable').click(function () {
//                var o = $(this).hasClass('sort-asc') ? 'sort-desc' : 'sort-asc';
//                $('th.sortable').removeClass('sort-asc').removeClass('sort-desc');
//                $(this).addClass(o);
//            });

        //todo's
        $('#todolist li label').click(function () {
            $(this).toggleClass('done');
        });


    })

    $(function () {

        var contentHeight = $('#content').height();
        var chatInboxHeight = contentHeight - 178;
        var chatContentHeight = contentHeight - 178 - 200;

        var setChatHeight = function () {
            $('#chat-inbox').css('height', chatInboxHeight);
            $('#chat-content').css('height', chatContentHeight);
        };

        setChatHeight();

        $(window).resize(function () {
            contentHeight = $('#content').height();
            chatInboxHeight = contentHeight - 178;
            chatContentHeight = contentHeight - 178 - 200;

            setChatHeight();
        });

        $("#chat-inbox").niceScroll({
            cursorcolor: '#000000',
            zindex: 999999,
            bouncescroll: true,
            cursoropacitymax: 0.4,
            cursorborder: '',
            cursorborderradius: 0,
            cursorwidth: '5px'
        });

        $("#chat-content").niceScroll({
            cursorcolor: '#000000',
            zindex: 999999,
            bouncescroll: true,
            cursoropacitymax: 0.4,
            cursorborder: '',
            cursorborderradius: 0,
            cursorwidth: '5px'
        });

        $('#chat-inbox .chat-actions > span').tooltip({
            placement: 'top',
            trigger: 'hover',
            html: true,
            container: 'body'
        });

        $('#initialize-search').click(function () {
            $('#chat-search').toggleClass('active').focus();
        });

        $(document).click(function (e) {
            if (($(e.target).closest("#initialize-search").attr("id") != "initialize-search") && $(e.target).closest("#chat-search").attr("id") != "chat-search") {
                $('#chat-search').removeClass('active');
            }
        });

        $(window).mouseover(function () {
            $("#chat-inbox").getNiceScroll().resize();
            $("#chat-content").getNiceScroll().resize();
        });

    });


</script>
<script>
    (function (document) {
        'use strict';

        var LightTableFilter = (function (Arr) {

            var _input;

            function _onInputEvent(e) {
                _input = e.target;
                var tables = document.getElementsByClassName(_input.getAttribute('data-table'));
                Arr.forEach.call(tables, function (table) {
                    Arr.forEach.call(table.tBodies, function (tbody) {
                        Arr.forEach.call(tbody.rows, _filter);
                    });
                });
            }

            function _filter(row) {
                var text = row.textContent.toLowerCase(), val = _input.value.toLowerCase();
                row.style.display = text.indexOf(val) === -1 ? 'none' : 'table-row';
            }

            return {
                init: function () {
                    var inputs = document.getElementsByClassName('light-table-filter');
                    Arr.forEach.call(inputs, function (input) {
                        input.oninput = _onInputEvent;
                    });
                }
            };
        })(Array.prototype);

        document.addEventListener('readystatechange', function () {
            if (document.readyState === 'complete') {
                LightTableFilter.init();
            }
        });

    })(document);
</script>

</html>