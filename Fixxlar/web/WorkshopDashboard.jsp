<%-- 
    Document   : workshop
    Created on : May 5, 2016, 10:00:14 AM
    Author     : joanne.ong.2014
--%>

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
    <body class="bg-3">
        <!--<h1>Welcome</h1>-->
        <%            
            String successChangePasswordMsg = (String) request.getAttribute("successChangePasswordMsg");
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
                                                                Ongoing Requests
                                                                <h2 class="media-heading animate-number" data-value="5" data-animation-duration="1500">0</h2>
                                                            </div>
                                                        </div> 

                                                    </div>
                                                    <div class="back">
                                                        <a href="ViewRequest.jsp#Ongoing">
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
                                                                <h2 class="media-heading animate-number" data-value="11" data-animation-duration="1500">0</h2>
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
                            <div class="col-lg-12 col-md-12">


                                <!-- tile -->
                                <section class="tile color transparent-black">




                                    <!-- tile header -->
                                    <div class="tile-header">
                                        <h1><strong>NEW</strong> Requests</h1>
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


                                    <!-- tile body -->
                                    <div class="tile-body no-vpadding">
                                        <div class="table-responsive">
                                            <table class="table table-custom table-sortable nomargin">
                                                <thead>
                                                    <tr>
                                                        <th class="sortable sort-numeric sort-asc">ID</th>
                                                        <th class="sortable sort-numeric">Date Time</th>
                                                        <th class="sortable sort-alpha">Customer Name</th>
                                                        <th class="sortable sort-alpha">Car Make</th>
                                                        <th class="sortable sort-numeric">Year of Manufacture</th>
                                                        <th class="sortable sort-alpha">Service</th>
                                                        <th class="text-center">Attachment</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td>1</td>
                                                        <td class="text-left">01/07/2016 19:04</td>
                                                        <td>Joanne Ong</td>
                                                        <td class="text-left">Toyota Camry</td>
                                                        <td class="text-left">1992</td>
                                                        <td>Gearbox</td>
                                                        <!--Picture Attachment-->
                                                        <td class="text-center"><a href="#myModal1" id="myBtn" data-toggle="modal"><img src="images/file.png"/></a></td>

                                                        <!-- Modal -->
                                                <div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog-img">
                                                        <div class="modal-content">
                                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                                            <div class="modal-header">
                                                                <h4 class="modal-title">Toyota Camry - 1992</h4>
                                                            </div>
                                                            <div class="modal-body">
                                                                <img class="img-responsive"src="http://starmoz.com/images/toyota-camry-1992-18.jpg"/>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                                <!--<button type="button" class="btn btn-primary">Save changes</button>-->
                                                            </div>
                                                        </div> <!--/.modal-content--> 
                                                    </div><!-- /.modal-dialog -->
                                                </div><!-- /.modal -->

                                                </tr>
                                                <tr>
                                                    <td>2</td>
                                                    <td class="text-left">01/07/2016 19:04</td>
                                                    <td>John Koh</td>
                                                    <td class="text-left">Toyota Camry</td>
                                                    <td class="text-left">1992</td>
                                                    <td>Piston Change</td>
                                                    <!--Picture Attachment-->
                                                    <td class="text-center"><a href="#myModal2" id="myBtn" data-toggle="modal"><img src="images/file.png"/></a></td>

                                                    <!-- Modal -->
                                                <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog-img">
                                                        <div class="modal-content">
                                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                                            <div class="modal-header">
                                                                <h4 class="modal-title">Toyota Camry - 1992</h4>
                                                            </div>
                                                            <div class="modal-body">
                                                                <img class="img-responsive"src="http://starmoz.com/images/toyota-camry-1992-18.jpg"/>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                                <!--<button type="button" class="btn btn-primary">Save changes</button>-->
                                                            </div>
                                                        </div> <!--/.modal-content--> 
                                                    </div><!-- /.modal-dialog -->
                                                </div><!-- /.modal -->

                                                </tr>
                                                <tr>
                                                    <td>3</td>
                                                    <td class="text-left">01/07/2016 19:04</td>
                                                    <td>Joshua Huang</td>
                                                    <td class="text-left">Toyota Camry</td>
                                                    <td class="text-left">1992</td>
                                                    <td>Tyre Change</td>
                                                    <!--Picture Attachment-->
                                                    <td class="text-center"><a href="#myModal3" id="myBtn" data-toggle="modal"><img src="images/file.png"/></a></td>

                                                    <!-- Modal -->
                                                <div class="modal fade" id="myModal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog-img">
                                                        <div class="modal-content">
                                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                                            <div class="modal-header">
                                                                <h4 class="modal-title">Toyota Camry - 1992</h4>
                                                            </div>
                                                            <div class="modal-body">
                                                                <img class="img-responsive"src="http://starmoz.com/images/toyota-camry-1992-18.jpg"/>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                                <!--<button type="button" class="btn btn-primary">Save changes</button>-->
                                                            </div>
                                                        </div> <!--/.modal-content--> 
                                                    </div><!-- /.modal-dialog -->
                                                </div><!-- /.modal -->
                                                </tr>
                                                <tr>
                                                    <td>4</td>
                                                    <td class="text-left">01/07/2016 19:04</td>
                                                    <td>Melvin Lee</td>
                                                    <td class="text-left">Toyota Camry</td>
                                                    <td class="text-left">1992</td>
                                                    <td>New PaintJob</td>
                                                    <!--Picture Attachment-->
                                                    <td class="text-center"><a href="#myModal3" id="myBtn" data-toggle="modal"><img src="images/file.png"/></a></td>

                                                    <!-- Modal -->
                                                <div class="modal fade" id="myModal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog-img">
                                                        <div class="modal-content">
                                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                                            <div class="modal-header">
                                                                <h4 class="modal-title">Toyota Camry - 1992</h4>
                                                            </div>
                                                            <div class="modal-body">
                                                                <img class="img-responsive"src="http://starmoz.com/images/toyota-camry-1992-18.jpg"/>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                                <!--<button type="button" class="btn btn-primary">Save changes</button>-->
                                                            </div>
                                                        </div> <!--/.modal-content--> 
                                                    </div><!-- /.modal-dialog -->
                                                </div><!-- /.modal -->
                                                </tr>
                                                <tr>
                                                    <td>5</td>
                                                    <td class="text-left">01/07/2016 19:04</td>
                                                    <td>Desmond</td>
                                                    <td class="text-left">Toyota Camry</td>
                                                    <td class="text-left">1992</td>
                                                    <td>Faulty Signal Lights</td>
                                                    <!--Picture Attachment-->
                                                    <td class="text-center"><a href="#myModal3" id="myBtn" data-toggle="modal"><img src="images/file.png"/></a></td>

                                                    <!-- Modal -->
                                                <div class="modal fade" id="myModal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog-img">
                                                        <div class="modal-content">
                                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                                            <div class="modal-header">
                                                                <h4 class="modal-title">Toyota Camry - 1992</h4>
                                                            </div>
                                                            <div class="modal-body">
                                                                <img class="img-responsive"src="http://starmoz.com/images/toyota-camry-1992-18.jpg"/>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                                <!--<button type="button" class="btn btn-primary">Save changes</button>-->
                                                            </div>
                                                        </div> <!--/.modal-content--> 
                                                    </div><!-- /.modal-dialog -->
                                                </div><!-- /.modal -->
                                                </tr>
                                                <tr>
                                                    <td>6</td>
                                                    <td class="text-left">01/07/2016 19:04</td>
                                                    <td>Tan Ah Beng</td>
                                                    <td class="text-left">Toyota Camry</td>
                                                    <td class="text-left">1992</td>
                                                    <td>Rear Bumper</td>
                                                    <!--Picture Attachment-->
                                                    <td class="text-center"><a href="#myModal3" id="myBtn" data-toggle="modal"><img src="images/file.png"/></a></td>

                                                    <!-- Modal -->
                                                <div class="modal fade" id="myModal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog-img">
                                                        <div class="modal-content">
                                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                                            <div class="modal-header">
                                                                <h4 class="modal-title">Toyota Camry - 1992</h4>
                                                            </div>
                                                            <div class="modal-body">
                                                                <img class="img-responsive"src="http://starmoz.com/images/toyota-camry-1992-18.jpg"/>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                                <!--<button type="button" class="btn btn-primary">Save changes</button>-->
                                                            </div>
                                                        </div> <!--/.modal-content--> 
                                                    </div><!-- /.modal-dialog -->
                                                </div><!-- /.modal -->
                                                </tr>
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
                        </div>
                        <!-- /row -->

                        <!-- row -->
                        <div class="row">

                        </div>
                        <!-- /row -->

                        <div class="main vertical-mail">




                            <!-- row -->
                            <div class="row">

                                <%--
                                <div class="col-md-2">

                <ul class="navigation" id="mail-navigation">
                  <li class="mark"><i class="fa fa-envelope-o"></i></li>
                  <li class="compose"><a href="#" class="btn btn-red">Compose Mail</a></li>
                  <li class="folders heading">
                    <h5><i class="fa fa-folder"></i> Folders <a href="#" class="pull-right"><i class="fa fa-plus"></i></a></h5>
                    <ul>
                      <li class="active"><a href="#"><span class="badge badge-red">5</span>Inbox</a></li>
                      <li><a href="#">Sent</a></li>
                      <li><a href="#">Draft</a></li>
                      <li><a href="#">Trash</a></li>
                      <li class="divider"></li>
                      <li class="other"><a href="#">Private</a></li>
                      <li class="other"><a href="#">Work</a></li>
                    </ul>
                  </li>
                  <li class="labels heading">
                    <h5><i class="fa fa-tags"></i> Labels <a href="#" class="pull-right"><i class="fa fa-plus"></i></a></h5>
                    <ul>
                      <li class="tag"><a href="#">Family <span class="label label-cyan"></span></a></li>
                      <li class="tag"><a href="#">Work <span class="label label-red"></span></a></li>
                      <li class="tag"><a href="#">Shop <span class="label label-orange"></span></a></li>
                      <li class="tag"><a href="#">Themeforest <span class="label label-amethyst"></span></a></li>
                      <li class="tag"><a href="#">Google <span class="label label-green"></span></a></li>
                      <li class="tag"><a href="#">Facebook <span class="label label-hotpink"></span></a></li>
                    </ul>
                </ul>
!--%>
                            </div>              

                            <div class="col-md-3">

                                <ul class="inbox" id="mail-inbox">
                                    <li class="heading"><h1>Inbox</h1></li>
                                    <li class="search"><i class="fa fa-search"></i> <input type="text" placeholder="Search in inbox..." /></li>
                                    <li class="msg">
                                        <div class="checkbox check-transparent">
                                            <input type="checkbox" value="1" checked id="msg01">
                                            <label for="msg01"></label>
                                        </div>
                                        <a href="#" class="mail-favourite active"><i class="fa fa-star-o"></i></a>
                                        <div>
                                            <h5><strong>Lucius</strong> Cashmere (5)</h5>
                                            <p><strong>Mail Message Subject</strong>, Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                                            <span class="delivery-time">today, 4:23pm</span>
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
                                            <p><strong>Mail Message Subject</strong>, Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                                            <span class="delivery-time">yesterday, 7:58am</span>
                                            <div class="mail-attachment"><i class="fa fa-paperclip"></i></div>
                                            <div class="mail-label bg-red"></div>
                                            <div class="mail-actions">
                                                <a href="#" class="reply"><i class="fa fa-reply"></i></a> 
                                                <a href="#" class="delete"><i class="fa fa-trash-o"></i></a>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="msg active">
                                        <div class="checkbox check-transparent">
                                            <input type="checkbox" id="msg03">
                                            <label for="msg03"></label>
                                        </div>
                                        <a href="#" class="mail-favourite"><i class="fa fa-star-o"></i></a>
                                        <div>
                                            <h5><strong>Dell</strong> MacApple</h5>
                                            <p><strong>Mail Message Subject</strong>, Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                                            <span class="delivery-time">yesterday, 9:24am</span>
                                            <div class="mail-actions">
                                                <a href="#" class="reply"><i class="fa fa-reply"></i></a> 
                                                <a href="#" class="delete"><i class="fa fa-trash-o"></i></a>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="msg">
                                        <div class="checkbox check-transparent">
                                            <input type="checkbox" id="msg04">
                                            <label for="msg04"></label>
                                        </div>
                                        <a href="#" class="mail-favourite active"><i class="fa fa-star-o"></i></a>
                                        <div>
                                            <h5><strong>Roger</strong> Flopple (3)</h5>
                                            <p><strong>Mail Message Subject</strong>, Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                                            <span class="delivery-time">yesterday, 11:12am</span>
                                            <div class="mail-attachment"><i class="fa fa-paperclip"></i></div>
                                            <div class="mail-label bg-cyan"></div>
                                            <div class="mail-actions">
                                                <a href="#" class="reply"><i class="fa fa-reply"></i></a> 
                                                <a href="#" class="delete"><i class="fa fa-trash-o"></i></a>
                                            </div>
                                        </div>
                                    </li>
                                    <%--
                                    <li class="msg">
                                      <div class="checkbox check-transparent">
                                        <input type="checkbox" id="msg05">
                                        <label for="msg05"></label>
                                      </div>
                                      <a href="#" class="mail-favourite active"><i class="fa fa-star-o"></i></a>
                                      <div>
                                        <h5><strong>Nico</strong> Vulture (8)</h5>
                                        <p><strong>Mail Message Subject</strong>, Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                                        <span class="delivery-time">yesterday, 01:28pm</span>
                                        <div class="mail-label bg-red"></div>
                                        <div class="mail-actions">
                                          <a href="#" class="reply"><i class="fa fa-reply"></i></a> 
                                          <a href="#" class="delete"><i class="fa fa-trash-o"></i></a>
                                        </div>
                                      </div>
                                    </li>
                                    <li class="msg">
                                      <div class="checkbox check-transparent">
                                        <input type="checkbox" id="msg06">
                                        <label for="msg06"></label>
                                      </div>
                                      <a href="#" class="mail-favourite"><i class="fa fa-star-o"></i></a>
                                      <div>
                                        <h5><strong>Bobby</strong> Socks (2)</h5>
                                        <p>Mail Message Subject, Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                                        <span class="delivery-time">yesterday, 01:33pm</span>
                                        <div class="mail-actions">
                                          <a href="#" class="reply"><i class="fa fa-reply"></i></a> 
                                          <a href="#" class="delete"><i class="fa fa-trash-o"></i></a>
                                        </div>
                                      </div>
                                    </li>
                                    <li class="msg">
                                      <div class="checkbox check-transparent">
                                        <input type="checkbox" id="msg07">
                                        <label for="msg07"></label>
                                      </div>
                                      <a href="#" class="mail-favourite"><i class="fa fa-star-o"></i></a>
                                      <div>
                                        <h5><strong>Silk</strong> Teddy</h5>
                                        <p>Mail Message Subject, Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                                        <span class="delivery-time">yesterday, 04:18pm</span>
                                        <div class="mail-attachment"><i class="fa fa-paperclip"></i></div>
                                        <div class="mail-actions">
                                          <a href="#" class="reply"><i class="fa fa-reply"></i></a> 
                                          <a href="#" class="delete"><i class="fa fa-trash-o"></i></a>
                                        </div>
                                      </div>
                                    </li>
                                    <li class="msg">
                                      <div class="checkbox check-transparent">
                                        <input type="checkbox" id="msg08">
                                        <label for="msg08"></label>
                                      </div>
                                      <a href="#" class="mail-favourite active"><i class="fa fa-star-o"></i></a>
                                      <div>
                                        <h5><strong>Rodney</strong> Hacksaw</h5>
                                        <p>Mail Message Subject, Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                                        <span class="delivery-time">yesterday, 05:12pm</span>
                                        <div class="mail-actions">
                                          <a href="#" class="reply"><i class="fa fa-reply"></i></a> 
                                          <a href="#" class="delete"><i class="fa fa-trash-o"></i></a>
                                        </div>
                                      </div>
                                    </li>
                                    
                                    <li class="msg">
                                      <div class="checkbox check-transparent">
                                        <input type="checkbox" id="msg09">
                                        <label for="msg09"></label>
                                      </div>
                                      <a href="#" class="mail-favourite"><i class="fa fa-star-o"></i></a>
                                      <div>
                                        <h5><strong>Paul</strong> Corduroy</h5>
                                        <p>Mail Message Subject, Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                                        <span class="delivery-time">yesterday, 05:35pm</span>
                                        <div class="mail-attachment"><i class="fa fa-paperclip"></i></div>
                                        <div class="mail-label bg-orange"></div>
                                        <div class="mail-actions">
                                          <a href="#" class="reply"><i class="fa fa-reply"></i></a> 
                                          <a href="#" class="delete"><i class="fa fa-trash-o"></i></a>
                                        </div>
                                      </div>
                                    </li>
                                    --%>
                                    <li class="msg last">
                                        <div class="checkbox check-transparent">
                                            <input type="checkbox" id="msg10">
                                            <label for="msg10"></label>
                                        </div>
                                        <a href="#" class="mail-favourite active"><i class="fa fa-star-o"></i></a>
                                        <div>
                                            <h5><strong>Tex</strong> Montreal (4)</h5>
                                            <p>Mail Message Subject, Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                                            <span class="delivery-time">yesterday, 07:12pm</span>
                                            <div class="mail-label bg-greensea"></div>
                                            <div class="mail-actions">
                                                <a href="#" class="reply"><i class="fa fa-reply"></i></a> 
                                                <a href="#" class="delete"><i class="fa fa-trash-o"></i></a>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="pagination pull-right">
                                        <span>1-5 of 154</span>
                                        <div class="btn-group btn-group-sm">
                                            <button type="button" class="btn btn-default"><i class="fa fa-angle-left"></i></button>
                                            <button type="button" class="btn btn-default"><i class="fa fa-angle-right"></i></button>
                                        </div>
                                    </li>
                                </ul>
                            </div>


                            <div class="col-md-9">
                                <div class="mail-content" id="mail-content">
                                    <%--
                                  <div class="quick-navigation">
                                    <button type="button" class="btn btn-default pull-left prev"><i class="fa fa-angle-left"></i></button>
                                    <h3><strong>Inbox</strong> (3/154)</h3>
                                    <button type="button" class="btn btn-default pull-right next"><i class="fa fa-angle-right"></i></button>
                                  </div>
                                    --%>
                                    <div class="message">
                                        <div class="header">
                                            <h1><strong>Mail Message</strong> Subject</h1>

                                            <ul class="message-info">
                                                <li>From: <em>Dell MacApple</em></li>
                                                <li>To: <em>me, admin@minoral.com</em></li>
                                                <li>Date: <em>10.02.2014, 9:24am</em></li>
                                                <li><i class="fa fa-paperclip"></i> <em>(1 file, 12.9 KB)</em></li>
                                            </ul>

                                            <div class="actions">
                                                <div class="btn-group">
                                                    <button type="button" class="btn btn-default"><i class="fa fa-reply"></i></button>
                                                    <button type="button" class="btn btn-default"><i class="fa fa-mail-reply-all"></i></button>
                                                    <button type="button" class="btn btn-default"><i class="fa fa-share"></i></button>
                                                </div>

                                                <button type="button" class="btn btn-default pull-right"><i class="fa fa-trash-o"></i></button>

                                            </div>
                                        </div>

                                        <div class="content">

                                            <p>
                                                Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                                            </p>
                                            <%--
                                                                  <blockquote class="filled withoutHeader">
                                                                    <p><i class="fa fa-quote-left pull-left"></i>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                                                                  </blockquote>
                                            --%>
                                            <h4 class="filled"><i class="icon-tags"></i> Attachments</h4>
                                            <div class="filled">
                                                <ul class="attachments">
                                                    <li><span class="label label-warning">zip</span> <em>zipped_file.zip (2,9MB) <span class="att-actions"><i class="fa fa-eye att-show"></i><i class="fa fa-share att-forward"></i><i class="fa fa-download att-download"></i></span></em></li>
                                                    <li><span class="label label-success">doc</span> <em>word_document.docx (1,2MB) <span class="att-actions"><i class="fa fa-eye att-show"></i><i class="fa fa-share att-forward"></i><i class="fa fa-download att-download"></i></span></em></li>
                                                    <li><span class="label label-primary">xls</span> <em>excel_document.xlsx (459KB) <span class="att-actions"><i class="fa fa-eye att-show"></i><i class="fa fa-share att-forward"></i><i class="fa fa-download att-download"></i></span></em></li>

                                                </ul>
                                            </div>
                                            <%--
                                              <h4 class="filled"><i class="icon-mail-reply"></i> Reply</h4>
                                            --%>
                                            <div style="text-align:right">
                                                <%--
                                              <form>
                                                <div id="message-wysiwyg"></div>
                                                --%>
                                                <br>
                                                <button type="submit" class="btn btn-greensea">Reply</button>
                                                <%--
                                              </form>
                                                --%>
                                            </div>


                                        </div>

                                    </div>
                                </div>


                            </div>



                        </div>
                        <!-- /row -->



                    </div>
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

    <script>
        $(function () {
            // Initialize card flip
            $('.card.hover').hover(function () {
                $(this).addClass('flip');
            }, function () {
                $(this).removeClass('flip');
            });

            // sortable table
            $('.table.table-sortable th.sortable').click(function () {
                var o = $(this).hasClass('sort-asc') ? 'sort-desc' : 'sort-asc';
                $('th.sortable').removeClass('sort-asc').removeClass('sort-desc');
                $(this).addClass(o);
            });

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

        })

    </script>
</body>
</html>