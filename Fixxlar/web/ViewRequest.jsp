<%-- 
    Document   : View Request
    Created on : May 5, 2016, 10:00:14 AM
    Author     : joanne.ong.2014
--%>

<%@page import="entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@include file="ProtectWorkshop.jsp"%>--%>
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


                        <div class="breadcrumbs">
                            <ol class="breadcrumb">
                                <li>You are here</li>
                                <li><a href="index.html">Minimal</a></li>
                                <li><a href="#">Example Pages</a></li>
                                <li class="active">Blank Page</li>
                            </ol>
                        </div>


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
                                                <div class="input-group table-options">
                                                    <select class="chosen-select form-control">
                                                        <option>Bulk Action</option>
                                                        <option>Delete Selected</option>
                                                        <option>Copy Selected</option>
                                                        <option>Archive Selected</option>
                                                    </select>
                                                    <span class="input-group-btn">
                                                        <button class="btn btn-default" type="button">Apply</button>
                                                    </span>
                                                </div>
                                            </div>

                                            <div class="col-sm-8 col-xs-6 text-right">

                                                <div class="btn-group btn-group-xs table-options">
                                                    <button type="button" class="btn btn-default">New</button>
                                                    <button type="button" class="btn btn-default">Ongoing</button>
                                                    <button type="button" class="btn btn-default">Completed</button>
                                                    <button type="button" class="btn btn-default">All</button>
                                                </div>

                                            </div>


                                        </div>
                                    </div>
                                    <!-- /tile widget -->



                                    <!-- tile body -->
                                    <div class="tile-body no-vpadding">

                                        <table class="table table-custom table-sortable">
                                            <thead>
                                                <tr>
                                                    <th style="width: 40px;">
                                            <div class="checkbox check-transparent">
                                                <input type="checkbox" value="1" id="allchck2">
                                                <label for="allchck2"></label>
                                            </div>
                                            </th>
                                            <th class="sortable sort-numeric">DateTime</th>
                                            <th class="sortable sort-asc">Name</th>
                                            <th class="sortable sort-asc">Car Make</th>
                                            <th class="sortable sort-numeric">Year</th>
                                            <th class="sortable sort-asc">Services</th>
                                            <th class="sortable sort-asc">Email</th>
                                            <th class="sortable sort-numeric">Phone</th>
                                            <th>Attachment</th>
                                            <th>Quote</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>
                                                        <div class="checkbox check-transparent">
                                                            <input type="checkbox" value="1" id="chck04">
                                                            <label for="chck04"></label>
                                                        </div>
                                                    </td>
                                                    <td>DateTime</td>
                                                    <td>Otto</td>
                                                    <td>Honda Civic</td>
                                                    <td>2000</td>
                                                    <td>Service A</td>
                                                    <td>@mdo</td>
                                                    <td>91234567</td>
                                                    <td class="text-center"><a href="#" class="check-toggler checked"></a></td>
                                                    <td class="text-center"><button class="btn btn-default btn-xs" type="button">Quote</button></td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <div class="checkbox check-transparent">
                                                            <input type="checkbox" value="1" id="chck05">
                                                            <label for="chck05"></label>
                                                        </div>
                                                    </td>
                                                    <td>DateTime</td>
                                                    <td>Otto</td>
                                                    <td>Honda Civic</td>
                                                    <td>2000</td>
                                                    <td>Service A</td>
                                                    <td>@mdo</td>
                                                    <td>91234567</td>
                                                    <td class="text-center"><a href="#" class="check-toggler"></a></td>
                                                    <td class="text-center"><button class="btn btn-default btn-xs" type="button">Quote</button></td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <div class="checkbox check-transparent">
                                                            <input type="checkbox" value="1" id="chck06">
                                                            <label for="chck06"></label>
                                                        </div>
                                                    </td>
                                                    <td>DateTime</td>
                                                    <td>Otto</td>
                                                    <td>Honda Civic</td>
                                                    <td>2000</td>
                                                    <td>Service A</td>
                                                    <td>@mdo</td>
                                                    <td>91234567</td>
                                                    <td class="text-center"><a href="#" class="check-toggler checked"></a></td>
                                                    <td class="text-center"><button class="btn btn-default btn-xs" type="button">Quote</button></td>
                                                </tr>
                                            </tbody>
                                        </table>

                                    </div>
                                    <!-- /tile body -->


                                    <!-- tile footer -->
                                    <div class="tile-footer bg-transparent-white-2 rounded-bottom-corners">
                                        <div class="row">  

                                            <div class="col-sm-4">
                                                <div class="input-group table-options">
                                                    <select class="chosen-select form-control">
                                                        <option>Bulk Action</option>
                                                        <option>Delete Selected</option>
                                                        <option>Copy Selected</option>
                                                        <option>Archive Selected</option>
                                                    </select>
                                                    <span class="input-group-btn">
                                                        <button class="btn btn-default" type="button">Apply</button>
                                                    </span>
                                                </div>
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
                })

            })
        </script>
    </body>
</html>
