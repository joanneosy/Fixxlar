<%-- 
    Document   : View Request
    Created on : May 5, 2016, 10:00:14 AM
    Author     : joanne.ong.2014
--%>

<%@page import="entity.WebUser"%>
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

        <link rel="stylesheet" href="css/custom.css">
        <link href="css/minimal.css" rel="stylesheet">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->

    </head>
    <body>
        <!--<h1>Welcome</h1>-->
        <%            String successChangePasswordMsg = (String) request.getAttribute("successChangePasswordMsg");
            if (successChangePasswordMsg != null) {
                out.println(successChangePasswordMsg + "<br/><br/>");
            }
        %>
    </body>
    <body class="bg-3">

        <!-- Preloader -->
        <div class="mask"><div id="loader"></div></div>
        <!--/Preloader -->

        <!-- Wrap all page content here -->
        <div id="wrap">
            <!-- Make page fluid -->
            <div class="row">

                <!-- Top and side nav bar -->
                <jsp:include page="include/topbar.jsp"/>
                <!-- Top and side nav bar -->





                <!-- Page content -->
                <div id="content" class="col-md-12">









                    <!-- page header -->
                    <div class="pageheader">


                        <h2><i class="fa fa-file-o" style="line-height: 48px;padding-left: 2px;"></i> View Request</h2>


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
                                <!-- tile -->
                                <section class="tile color transparent-white">



                                    <!-- tile header -->
                                    <div class="tile-header">
                                        <h1><strong>View Request</strong></h1>
                                        <div class="search">
                                            <input type="text" placeholder="Search...">
                                        </div>
                                        <div class="controls">
                                            <a href="#" class="minimize"><i class="fa fa-chevron-down"></i></a>
                                            <a href="#" class="refresh"><i class="fa fa-refresh"></i></a>
                                            <a href="#" class="remove"><i class="fa fa-times"></i></a>
                                        </div>
                                    </div>
                                    <!-- /tile header -->

                                    <!-- tile widget -->
                                    <div class="tile-widget bg-transparent-white-2">
                                        <div class="row">

                                            <div class="col-sm-4 col-xs-6">
                                                <!--                                                <div class="input-group table-options">
                                                                                                    <select class="chosen-select form-control">
                                                                                                        <option>Bulk Action</option>
                                                                                                        <option>Delete Selected</option>
                                                                                                        <option>Copy Selected</option>
                                                                                                        <option>Archive Selected</option>
                                                                                                    </select>
                                                                                                    <span class="input-group-btn">
                                                                                                        <button class="btn btn-default" type="button">Apply</button>
                                                                                                    </span>
                                                                                                </div>-->
                                            </div>

                                            <div class="col-sm-8 col-xs-6 text-right">

                                                <!--                                                <div class="btn-group btn-group-xs table-options">
                                                                                                    <button type="button active" class="btn btn-default" href="#New">New</button>
                                                                                                    <button type="button" class="btn btn-default" href="#Ongoing">Ongoing</button>
                                                                                                    <button type="button" class="btn btn-default" href="#Completed">Completed</button>
                                                                                                    <button type="button" class="btn btn-default" href="#All">All</button>
                                                                                                </div>-->
                                                <div class="btn-group btn-group-xs table-options">
                                                    <ul class="nav nav-pills">
                                                        <li class="active"><a href="#New" data-toggle="pill">New</a></li>
                                                        <li><a href="#Pending" data-toggle="pill">Pending</a></li>
                                                        <li><a href="#Ongoing" data-toggle="pill">Ongoing</a></li>
                                                        <li><a href="#Completed" data-toggle="pill">Completed</a></li>
                                                        <li><a href="#All" data-toggle="pill">All</a></li>
                                                    </ul>
                                                </div>

                                            </div>


                                        </div>
                                    </div>
                                    <!-- /tile widget -->



                                    <!-- tile body -->
                                    <div class="tile-body no-vpadding">
                                        <div class="tab-content">
                                            <div class="tab-pane fade active in" id="New">
                                                <table class="table table-custom table-sortable sortable">
                                                    <thead>
                                                        <tr>
                                                            <!--                                                    <th style="width: 40px;">
                                                                                                        <div class="checkbox check-transparent">
                                                                                                            <input type="checkbox" value="1" id="allchck2">
                                                                                                            <label for="allchck2"></label>
                                                                                                        </div>
                                                                                                        </th>-->
                                                            <th class="sortable">DateTime</th>
                                                            <th class="sortable">Name</th>
                                                            <th class="sortable">Car Make Model</th>
                                                            <th class="sortable">Year of Manufacture</th>
                                                            <th class="sortable">Services</th>
                                                            <th class="sortable">Email</th>
                                                            <th class="sortable">Phone</th>
                                                            <th>Attachment</th>
                                                            <th>Quote</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <!--                                                    <td>
                                                                                                                    <div class="checkbox check-transparent">
                                                                                                                        <input type="checkbox" value="1" id="chck04">
                                                                                                                        <label for="chck04"></label>
                                                                                                                    </div>
                                                                                                                </td>-->
                                                            <td>01/07/2016 19:04</td>
                                                            <td>Otto</td>
                                                            <td>Honda Civic</td>
                                                            <td>2000</td>
                                                            <td>Gearbox</td>
                                                            <td>otto@gmail.com</td>
                                                            <td>91234567</td>
                                                            <!--Picture Attachment-->
                                                            <td class="text-center"><a href="#myModal1" id="myBtn" data-toggle="modal"><img src="images/file.png"/></a></td>

                                                            <!-- Modal -->
                                                    <div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                        <div class="modal-dialog-img">
                                                            <div class="modal-content">
                                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                                                <div class="modal-header">
                                                                    <h4 class="modal-title">Honda Civic - 2000</h4>
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

                                                    <!--Quote-->
                                                    <td class="text-center"><button href="#myModal2" class="btn btn-default btn-xs" data-toggle="modal" id="quoteBtn" type="button"><span>Quote</span></button></td>

                                                    <!-- Modal -->
                                                    <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                        <div class="modal-dialog">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                                                    <h4 class="modal-title">New Request - Otto Tan</h4>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <div class="text-center">
                                                                        <img class="img-thumbnail-small"src="http://buyersguide.caranddriver.com/media/assets/submodel/6985.jpg"/>
                                                                    </div>
                                                                    <div class="line-across"></div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Name:</b> Otto Tan</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Date & Time:</b> 01/07/2016 19:04</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Email:</b> otto@gmail.com</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Contact No:</b> 91234567</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Vehicle Model:</b> Honda Civic</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Vehicle Year:</b> 2000</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>License Plate:</b> SAB 1234 A</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Vehicle Color:</b> Black</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Vehicle Type:</b> Manual</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Mileage:</b> 9999</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Service Request:</b> Gearbox</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Urgency:</b> xxxx</p>
                                                                    </div>

                                                                    <div>
                                                                        <p><b>Service Description:</b> Lorem ipsum dolor sit amet, sed voluptatum temporibus te, convenire deterruisset at vix, per laboramus aliquando id. At ius dicam oporteat. Odio iusto definiebas mel id. Ex ceteros copiosae eum. Augue labore incorrupte per at. Duo brute senserit patrioque ut, eu rebum graeco eum.</p>
                                                                    </div>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <form name="quote" action="quote">
                                                                        Quotation Amount: <input type="text"/>
                                                                        <button type="submit" class="btn btn-primary">Submit Quote</button>
                                                                    </form>
                                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                                </div>
                                                            </div><!-- /.modal-content -->
                                                        </div><!-- /.modal-dialog -->
                                                    </div><!-- /.modal -->
                                                    </tr> 
                                                    </tbody>

                                                </table>
                                            </div>
                                            <div class="tab-pane fade" id="Pending">
                                                <table class="table table-custom table-sortable sortable">
                                                    <thead>
                                                        <tr>
                                                            <!--                                                    <th style="width: 40px;">
                                                                                                        <div class="checkbox check-transparent">
                                                                                                            <input type="checkbox" value="1" id="allchck2">
                                                                                                            <label for="allchck2"></label>
                                                                                                        </div>
                                                                                                        </th>-->
                                                            <th class="sortable">DateTime</th>
                                                            <th class="sortable">Name</th>
                                                            <th class="sortable">Car Make Model</th>
                                                            <th class="sortable">Year of Manufacture</th>
                                                            <th class="sortable">Services</th>
                                                            <th class="sortable">Email</th>
                                                            <th class="sortable">Phone</th>
                                                            <th>Attachment</th>
                                                            <th>Quote</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        
                                                    </tbody>

                                                </table>
                                            </div>
                                            <div class="tab-pane fade" id="Ongoing">
                                                <table class="table table-custom table-sortable sortable">
                                                    <thead>
                                                        <tr>
                                                            <!--                                                    <th style="width: 40px;">
                                                                                                        <div class="checkbox check-transparent">
                                                                                                            <input type="checkbox" value="1" id="allchck2">
                                                                                                            <label for="allchck2"></label>
                                                                                                        </div>
                                                                                                        </th>-->
                                                            <th class="sortable">DateTime</th>
                                                            <th class="sortable">Name</th>
                                                            <th class="sortable">Car Make Model</th>
                                                            <th class="sortable">Year of Manufacture</th>
                                                            <th class="sortable">Services</th>
                                                            <th class="sortable">Email</th>
                                                            <th class="sortable">Phone</th>
                                                            <th>Attachment</th>
                                                            <th>Quote</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <!--                                                    <td>
                                                                                                                    <div class="checkbox check-transparent">
                                                                                                                        <input type="checkbox" value="1" id="chck04">
                                                                                                                        <label for="chck04"></label>
                                                                                                                    </div>
                                                                                                                </td>-->
                                                            <td>01/07/2016 19:04</td>
                                                            <td>Bob</td>
                                                            <td>Toyota Corolla</td>
                                                            <td>2005</td>
                                                            <td>Tyre Change</td>
                                                            <td>bob@gmail.com</td>
                                                            <td>92345678</td>
                                                            <!--Picture Attachment-->
                                                            <td class="text-center"><a href="#myModal3" id="myBtn" data-toggle="modal"><img src="images/file.png"/></a></td>

                                                            <!-- Modal -->
                                                    <div class="modal fade" id="myModal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                        <div class="modal-dialog-img">
                                                            <div class="modal-content">
                                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                                                <div class="modal-header">
                                                                    <h4 class="modal-title">Toyota Corolla - 2005</h4>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <img class="img-responsive"src="http://spidercars.net/wp-content/uploads/images/2005-Toyota-Corolla_13748.jpg"/>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                                    <!--<button type="button" class="btn btn-primary">Save changes</button>-->
                                                                </div>
                                                            </div> <!--/.modal-content--> 
                                                        </div><!-- /.modal-dialog -->
                                                    </div><!-- /.modal -->

                                                    <!--Quote-->
                                                    <td class="text-center"><button href="#myModal4" class="btn btn-default btn-xs" data-toggle="modal" id="quoteBtn" type="button"><span>Quote</span></button></td>

                                                    <!-- Modal -->
                                                    <div class="modal fade" id="myModal4" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                        <div class="modal-dialog">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                                                    <h4 class="modal-title">New Request - Bob Lim</h4>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <div class="text-center">
                                                                        <img class="img-thumbnail-small"src="http://spidercars.net/wp-content/uploads/images/2005-Toyota-Corolla_13748.jpg"/>
                                                                    </div>
                                                                    <div class="line-across"></div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Name:</b> Bob Lim</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Date & Time:</b> 01/07/2016 19:04</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Email:</b> bob@gmail.com</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Contact No:</b> 92345678</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Vehicle Model:</b> Toyota Corolla</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Vehicle Year:</b> 2005</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>License Plate:</b> Szz 6789 C</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Vehicle Color:</b> Black</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Vehicle Type:</b> Auto</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Mileage:</b> 1111</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Service Request:</b> Tyre Change</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Urgency:</b> xxxx</p>
                                                                    </div>

                                                                    <div>
                                                                        <p><b>Service Description:</b> Lorem ipsum dolor sit amet, sed voluptatum temporibus te, convenire deterruisset at vix, per laboramus aliquando id. At ius dicam oporteat. Odio iusto definiebas mel id. Ex ceteros copiosae eum. Augue labore incorrupte per at. Duo brute senserit patrioque ut, eu rebum graeco eum.</p>
                                                                    </div>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <form name="quote" action="quote">
                                                                        Quotation Amount: <input type="text"/>
                                                                        <button type="submit" class="btn btn-primary">Submit Quote</button>
                                                                    </form>
                                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                                </div>
                                                            </div><!-- /.modal-content -->
                                                        </div><!-- /.modal-dialog -->
                                                    </div><!-- /.modal -->
                                                        </tr>
                                                    </tbody>

                                                </table>
                                            </div>
                                            <div class="tab-pane fade" id="Completed">
                                                <table class="table table-custom table-sortable sortable">
                                                    <thead>
                                                        <tr>
                                                            <!--                                                    <th style="width: 40px;">
                                                                                                        <div class="checkbox check-transparent">
                                                                                                            <input type="checkbox" value="1" id="allchck2">
                                                                                                            <label for="allchck2"></label>
                                                                                                        </div>
                                                                                                        </th>-->
                                                            <th class="sortable">DateTime</th>
                                                            <th class="sortable">Name</th>
                                                            <th class="sortable">Car Make Model</th>
                                                            <th class="sortable">Year of Manufacture</th>
                                                            <th class="sortable">Services</th>
                                                            <th class="sortable">Email</th>
                                                            <th class="sortable">Phone</th>
                                                            <th>Attachment</th>
                                                            <th>Quote</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                        <!--                                                    <td>
                                                                                                                <div class="checkbox check-transparent">
                                                                                                                    <input type="checkbox" value="1" id="chck04">
                                                                                                                    <label for="chck04"></label>
                                                                                                                </div>
                                                                                                            </td>-->
                                                        <td>01/07/2016 19:04</td>
                                                        <td>Nigel</td>
                                                        <td>Nissan Sunny </td>
                                                        <td>2016</td>
                                                        <td>Piston Change</td>
                                                        <td>nigel@gmail.com</td>
                                                        <td>91212121</td>
                                                        <!--Picture Attachment-->
                                                        <td class="text-center"><a href="#myModal5" id="myBtn" data-toggle="modal"><img src="images/file.png"/></a></td>

                                                        <!-- Modal -->
                                                    <div class="modal fade" id="myModal5" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                        <div class="modal-dialog-img">
                                                            <div class="modal-content">
                                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                                                <div class="modal-header">
                                                                    <h4 class="modal-title">Nissan Sunny - 2016</h4>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <img class="img-responsive"src="https://www.nissan-cdn.net/content/dam/Nissan/eg/vehicles/sunny/product_code/product_version/overview/1920x800s3.jpg.ximg.m_12_h.smart.jpg"/>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                                    <!--<button type="button" class="btn btn-primary">Save changes</button>-->
                                                                </div>
                                                            </div> <!--/.modal-content--> 
                                                        </div><!-- /.modal-dialog -->
                                                    </div><!-- /.modal -->

                                                    <!--Quote-->
                                                    <td class="text-center"><button href="#myModal6" class="btn btn-default btn-xs" data-toggle="modal" id="quoteBtn" type="button"><span>Quote</span></button></td>

                                                    <!-- Modal -->
                                                    <div class="modal fade" id="myModal6" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                        <div class="modal-dialog">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                                                    <h4 class="modal-title">New Request - Nigel Tan</h4>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <div class="text-center">
                                                                        <img class="img-thumbnail-small"src="https://www.nissan-cdn.net/content/dam/Nissan/eg/vehicles/sunny/product_code/product_version/overview/1920x800s3.jpg.ximg.m_12_h.smart.jpg"/>
                                                                    </div>
                                                                    <div class="line-across"></div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Name:</b> Nigel Tan</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Date & Time:</b> 01/07/2016 19:04</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Email:</b> nigel@gmail.com</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Contact No:</b> 91212121</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Vehicle Model:</b> Nissan Sunny</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Vehicle Year:</b> 2016</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>License Plate:</b> SXY 2322 A</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Vehicle Color:</b> Silver</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Vehicle Type:</b> Manual</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Mileage:</b> 9999</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Service Request:</b> Piston Change</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Urgency:</b> xxxx</p>
                                                                    </div>

                                                                    <div>
                                                                        <p><b>Service Description:</b> Lorem ipsum dolor sit amet, sed voluptatum temporibus te, convenire deterruisset at vix, per laboramus aliquando id. At ius dicam oporteat. Odio iusto definiebas mel id. Ex ceteros copiosae eum. Augue labore incorrupte per at. Duo brute senserit patrioque ut, eu rebum graeco eum.</p>
                                                                    </div>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <form name="quote" action="quote">
                                                                        Quotation Amount: <input type="text"/>
                                                                        <button type="submit" class="btn btn-primary">Submit Quote</button>
                                                                    </form>
                                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                                </div>
                                                            </div><!-- /.modal-content -->
                                                        </div><!-- /.modal-dialog -->
                                                    </div><!-- /.modal -->
                                                    </tr>
                                                    </tbody>

                                                </table>
                                            </div>
                                            <div class="tab-pane fade" id="All">
                                                <table class="table table-custom table-sortable sortable">
                                                    <thead>
                                                        <tr>
                                                            <!--                                                    <th style="width: 40px;">
                                                                                                        <div class="checkbox check-transparent">
                                                                                                            <input type="checkbox" value="1" id="allchck2">
                                                                                                            <label for="allchck2"></label>
                                                                                                        </div>
                                                                                                        </th>-->
                                                            <th class="sortable">DateTime</th>
                                                            <th class="sortable">Name</th>
                                                            <th class="sortable">Car Make Model</th>
                                                            <th class="sortable">Year of Manufacture</th>
                                                            <th class="sortable">Services</th>
                                                            <th class="sortable">Email</th>
                                                            <th class="sortable">Phone</th>
                                                            <th>Attachment</th>
                                                            <th>Quote</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <!--                                                    <td>
                                                                                                                    <div class="checkbox check-transparent">
                                                                                                                        <input type="checkbox" value="1" id="chck04">
                                                                                                                        <label for="chck04"></label>
                                                                                                                    </div>
                                                                                                                </td>-->
                                                            <td>01/07/2016 19:04</td>
                                                            <td>Otto</td>
                                                            <td>Honda Civic</td>
                                                            <td>2000</td>
                                                            <td>Gearbox</td>
                                                            <td>otto@gmail.com</td>
                                                            <td>91234567</td>
                                                            <!--Picture Attachment-->
                                                            <td class="text-center"><a href="#myModal1" id="myBtn" data-toggle="modal"><img src="images/file.png"/></a></td>

                                                            <!-- Modal -->
                                                    <div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                        <div class="modal-dialog-img">
                                                            <div class="modal-content">
                                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                                                <div class="modal-header">
                                                                    <h4 class="modal-title">Honda Civic - 2000</h4>
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

                                                    <!--Quote-->
                                                    <td class="text-center"><button href="#myModal2" class="btn btn-default btn-xs" data-toggle="modal" id="quoteBtn" type="button"><span>Quote</span></button></td>

                                                    <!-- Modal -->
                                                    <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                        <div class="modal-dialog">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                                                    <h4 class="modal-title">New Request - Otto Tan</h4>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <div class="text-center">
                                                                        <img class="img-thumbnail-small"src="http://buyersguide.caranddriver.com/media/assets/submodel/6985.jpg"/>
                                                                    </div>
                                                                    <div class="line-across"></div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Name:</b> Otto Tan</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Date & Time:</b> 01/07/2016 19:04</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Email:</b> otto@gmail.com</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Contact No:</b> 91234567</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Vehicle Model:</b> Honda Civic</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Vehicle Year:</b> 2000</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>License Plate:</b> SAB 1234 A</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Vehicle Color:</b> Black</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Vehicle Type:</b> Manual</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Mileage:</b> 9999</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Service Request:</b> Gearbox</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Urgency:</b> xxxx</p>
                                                                    </div>

                                                                    <div>
                                                                        <p><b>Service Description:</b> Lorem ipsum dolor sit amet, sed voluptatum temporibus te, convenire deterruisset at vix, per laboramus aliquando id. At ius dicam oporteat. Odio iusto definiebas mel id. Ex ceteros copiosae eum. Augue labore incorrupte per at. Duo brute senserit patrioque ut, eu rebum graeco eum.</p>
                                                                    </div>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <form name="quote" action="quote">
                                                                        Quotation Amount: <input type="text"/>
                                                                        <button type="submit" class="btn btn-primary">Submit Quote</button>
                                                                    </form>
                                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                                </div>
                                                            </div><!-- /.modal-content -->
                                                        </div><!-- /.modal-dialog -->
                                                    </div><!-- /.modal -->
                                                    </tr>
                                                    <tr>
                                                        <!--                                                    <td>
                                                                                                                <div class="checkbox check-transparent">
                                                                                                                    <input type="checkbox" value="1" id="chck04">
                                                                                                                    <label for="chck04"></label>
                                                                                                                </div>
                                                                                                            </td>-->
                                                        <td>01/07/2016 19:04</td>
                                                        <td>Bob</td>
                                                        <td>Toyota Corolla</td>
                                                        <td>2005</td>
                                                        <td>Tyre Change</td>
                                                        <td>bob@gmail.com</td>
                                                        <td>92345678</td>
                                                        <!--Picture Attachment-->
                                                        <td class="text-center"><a href="#myModal3" id="myBtn" data-toggle="modal"><img src="images/file.png"/></a></td>

                                                        <!-- Modal -->
                                                    <div class="modal fade" id="myModal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                        <div class="modal-dialog-img">
                                                            <div class="modal-content">
                                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                                                <div class="modal-header">
                                                                    <h4 class="modal-title">Toyota Corolla - 2005</h4>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <img class="img-responsive"src="http://spidercars.net/wp-content/uploads/images/2005-Toyota-Corolla_13748.jpg"/>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                                    <!--<button type="button" class="btn btn-primary">Save changes</button>-->
                                                                </div>
                                                            </div> <!--/.modal-content--> 
                                                        </div><!-- /.modal-dialog -->
                                                    </div><!-- /.modal -->

                                                    <!--Quote-->
                                                    <td class="text-center"><button href="#myModal4" class="btn btn-default btn-xs" data-toggle="modal" id="quoteBtn" type="button"><span>Quote</span></button></td>

                                                    <!-- Modal -->
                                                    <div class="modal fade" id="myModal4" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                        <div class="modal-dialog">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                                                    <h4 class="modal-title">New Request - Bob Lim</h4>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <div class="text-center">
                                                                        <img class="img-thumbnail-small"src="http://spidercars.net/wp-content/uploads/images/2005-Toyota-Corolla_13748.jpg"/>
                                                                    </div>
                                                                    <div class="line-across"></div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Name:</b> Bob Lim</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Date & Time:</b> 01/07/2016 19:04</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Email:</b> bob@gmail.com</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Contact No:</b> 92345678</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Vehicle Model:</b> Toyota Corolla</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Vehicle Year:</b> 2005</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>License Plate:</b> Szz 6789 C</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Vehicle Color:</b> Black</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Vehicle Type:</b> Auto</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Mileage:</b> 1111</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Service Request:</b> Tyre Change</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Urgency:</b> xxxx</p>
                                                                    </div>

                                                                    <div>
                                                                        <p><b>Service Description:</b> Lorem ipsum dolor sit amet, sed voluptatum temporibus te, convenire deterruisset at vix, per laboramus aliquando id. At ius dicam oporteat. Odio iusto definiebas mel id. Ex ceteros copiosae eum. Augue labore incorrupte per at. Duo brute senserit patrioque ut, eu rebum graeco eum.</p>
                                                                    </div>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <form name="quote" action="quote">
                                                                        Quotation Amount: <input type="text"/>
                                                                        <button type="submit" class="btn btn-primary">Submit Quote</button>
                                                                    </form>
                                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                                </div>
                                                            </div><!-- /.modal-content -->
                                                        </div><!-- /.modal-dialog -->
                                                    </div><!-- /.modal -->
                                                    </tr>
                                                    <tr>
                                                        <!--                                                    <td>
                                                                                                                <div class="checkbox check-transparent">
                                                                                                                    <input type="checkbox" value="1" id="chck04">
                                                                                                                    <label for="chck04"></label>
                                                                                                                </div>
                                                                                                            </td>-->
                                                        <td>01/07/2016 19:04</td>
                                                        <td>Nigel</td>
                                                        <td>Nissan Sunny </td>
                                                        <td>2016</td>
                                                        <td>Piston Change</td>
                                                        <td>nigel@gmail.com</td>
                                                        <td>91212121</td>
                                                        <!--Picture Attachment-->
                                                        <td class="text-center"><a href="#myModal5" id="myBtn" data-toggle="modal"><img src="images/file.png"/></a></td>

                                                        <!-- Modal -->
                                                    <div class="modal fade" id="myModal5" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                        <div class="modal-dialog-img">
                                                            <div class="modal-content">
                                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                                                <div class="modal-header">
                                                                    <h4 class="modal-title">Nissan Sunny - 2016</h4>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <img class="img-responsive"src="https://www.nissan-cdn.net/content/dam/Nissan/eg/vehicles/sunny/product_code/product_version/overview/1920x800s3.jpg.ximg.m_12_h.smart.jpg"/>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                                    <!--<button type="button" class="btn btn-primary">Save changes</button>-->
                                                                </div>
                                                            </div> <!--/.modal-content--> 
                                                        </div><!-- /.modal-dialog -->
                                                    </div><!-- /.modal -->

                                                    <!--Quote-->
                                                    <td class="text-center"><button href="#myModal6" class="btn btn-default btn-xs" data-toggle="modal" id="quoteBtn" type="button"><span>Quote</span></button></td>

                                                    <!-- Modal -->
                                                    <div class="modal fade" id="myModal6" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                        <div class="modal-dialog">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                                                    <h4 class="modal-title">New Request - Nigel Tan</h4>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <div class="text-center">
                                                                        <img class="img-thumbnail-small"src="https://www.nissan-cdn.net/content/dam/Nissan/eg/vehicles/sunny/product_code/product_version/overview/1920x800s3.jpg.ximg.m_12_h.smart.jpg"/>
                                                                    </div>
                                                                    <div class="line-across"></div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Name:</b> Nigel Tan</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Date & Time:</b> 01/07/2016 19:04</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Email:</b> nigel@gmail.com</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Contact No:</b> 91212121</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Vehicle Model:</b> Nissan Sunny</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Vehicle Year:</b> 2016</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>License Plate:</b> SXY 2322 A</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Vehicle Color:</b> Silver</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Vehicle Type:</b> Manual</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Mileage:</b> 9999</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Service Request:</b> Piston Change</p>
                                                                    </div>

                                                                    <div class="col-xs-6">
                                                                        <p><b>Urgency:</b> xxxx</p>
                                                                    </div>

                                                                    <div>
                                                                        <p><b>Service Description:</b> Lorem ipsum dolor sit amet, sed voluptatum temporibus te, convenire deterruisset at vix, per laboramus aliquando id. At ius dicam oporteat. Odio iusto definiebas mel id. Ex ceteros copiosae eum. Augue labore incorrupte per at. Duo brute senserit patrioque ut, eu rebum graeco eum.</p>
                                                                    </div>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <form name="quote" action="quote">
                                                                        Quotation Amount: <input type="text"/>
                                                                        <button type="submit" class="btn btn-primary">Submit Quote</button>
                                                                    </form>
                                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                                </div>
                                                            </div><!-- /.modal-content -->
                                                        </div><!-- /.modal-dialog -->
                                                    </div><!-- /.modal -->
                                                    </tr>
                                                    </tbody>

                                                </table>
                                            </div>
                                        </div><!--tab-content-->


                                    </div>
                                    <!-- /tile body -->


                                    <!-- tile footer -->
                                    <div class="tile-footer bg-transparent-white-2 rounded-bottom-corners">
                                        <div class="row">  

                                            <div class="col-sm-4">
                                                <!--                                                <div class="input-group table-options">
                                                                                                    <select class="chosen-select form-control">
                                                                                                        <option>Bulk Action</option>
                                                                                                        <option>Delete Selected</option>
                                                                                                        <option>Copy Selected</option>
                                                                                                        <option>Archive Selected</option>
                                                                                                    </select>
                                                                                                    <span class="input-group-btn">
                                                                                                        <button class="btn btn-default" type="button">Apply</button>
                                                                                                    </span>
                                                                                                </div>-->
                                            </div>

                                            <div class="col-sm-4 text-center">
                                                <small class="inline table-options paging-info">showing 1-3 of 24 items</small>
                                            </div>

                                            <div class="col-sm-4 text-right sm-center">
                                                <ul class="pagination pagination-xs nomargin pagination-custom">
                                                    <li class="disabled"><a href="#"><i class="fa fa-angle-double-left"></i></a></li>
                                                    <li class="active"><a href="#">1</a></li>
                                                    <li><a href="#">2</a></li>
                                                    <li><a href="#">3</a></li>
                                                    <li><a href="#">4</a></li>
                                                    <li><a href="#">5</a></li>
                                                    <li><a href="#"><i class="fa fa-angle-double-right"></i></a></li>
                                                </ul>
                                            </div>

                                        </div>
                                    </div>
                                    <!-- /tile footer -->




                                </section>
                                <!-- /tile -->






                            </div>
                            <!-- /col 12 -->



                        </div>
                        <!-- /row -->






                    </div>
                    <!-- /content container -->






                </div>
                <!-- Page content end -->




                <!-- Right slider bar -->
                <jsp:include page="include/rightbar.jsp"/>
                <!-- Right slider bar -->






            </div>
            <!-- Make page fluid-->




        </div>
        <!-- Wrap all page content end -->



        <section class="videocontent" id="video"></section>



        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://code.jquery.com/jquery.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/bootstrap-dropdown-multilevel.js"></script>
        <script src="https://google-code-prettify.googlecode.com/svn/loader/run_prettify.js?lang=css&amp;skin=sons-of-obsidian"></script>
        <script type="text/javascript" src="js/jquery.mmenu.min.js"></script>
        <script type="text/javascript" src="js/jquery.sparkline.min.js"></script>
        <script type="text/javascript" src="js/jquery.nicescroll.min.js"></script>
        <script type="text/javascript" src="js/jquery.animateNumbers.js"></script>
        <script type="text/javascript" src="js/jquery.videobackground.js"></script>
        <script type="text/javascript" src="js/jquery.blockUI.js"></script>
        <script type="text/javascript" src="js/sorttable.js"></script>

        <script src="js/minimal.min.js"></script>

        <script>
            $(function () {

                //check all checkboxes
                $('table thead input[type="checkbox"]').change(function () {
                    $(this).parents('table').find('tbody input[type="checkbox"]').prop('checked', $(this).prop('checked'));
                });

                // sortable table
                $('.table.table-sortable th.sortable').click(function () {
                    var o = $(this).hasClass('sort-asc') ? 'sort-desc' : 'sort-asc';
                    $(this).parents('table').find('th.sortable').removeClass('sort-asc').removeClass('sort-desc');
                    $(this).addClass(o);
                });

                //chosen select input
                $(".chosen-select").chosen({disable_search_threshold: 10});

                //check toggling
                $('.check-toggler').on('click', function () {
                    $(this).toggleClass('checked');
                });
            });


            ////             Get the modal
            //            var modal = document.getElementById('myModal');
            //
            //// Get the button that opens the modal
            //            var btn = document.getElementById("myBtn");
            //
            //// Get the <span> element that closes the modal
            //            var span = document.getElementsByClassName("close")[0];
            //
            //// When the user clicks the button, open the modal
            //            btn.onclick = function () {
            //                modal.style.display = "block";
            //            }
            //
            //// When the user clicks on <span> (x), close the modal
            //            span.onclick = function () {
            //                modal.style.display = "none";
            //            }
            //
            //// When the user clicks anywhere outside of the modal, close it
            //            window.onclick = function (event) {
            //                if (event.target == modal) {
            //                    modal.style.display = "none";
            //                }
            //            }
//            $(window).load(function () {
//                [modal].click(function () {
//                    $(this).modal('show');
//                });
//            });

//            jQuery(document).ready(function () {
//                jQuery('.nav-pills a').on('click', function (e) {
//                    var currentAttrValue = jQuery(this).attr('href');
//
//                    // Show/Hide Tabs
//                    jQuery(currentAttrValue).show().siblings().hide();
//
//                    // Change/remove current tab to active
//                    jQuery(this).parent('li').addClass('active').siblings().removeClass('active');
//
//                    e.preventDefault();
//                });
//            });

        </script>
    </body>
</html>
