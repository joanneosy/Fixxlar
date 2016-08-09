
var ortcClient;

window.onload = function () {

    $("#log").text("Connecting to Realtime server ...");

    loadOrtcFactory(IbtRealTimeSJType, function (factory, error) {
        if (error !== null) {
            alert("Factory error: " + error.message);
        } else {
            if (factory !== null) {
                ortcClient = factory.createClient();

                ortcClient.setId('clientId');
                ortcClient.setConnectionMetadata('clientConnMeta');
                ortcClient.setClusterUrl('http://ortc-developers.realtime.co/server/2.1');

                ortcClient.onConnected = function (ortc) {
                    $("#log").text("Connected to " + ortcClient.getUrl() + " using " + ortcClient.getProtocol());

                    ortcClient.subscribe('ChatChannel:1:1', true, function (ortc, channel, message) {
//                    $("#log").html(message + "<br><br>" + $("#log").html());
                        var sender = message.substring(0, message.indexOf(":"));
                        message = message.substring(message.indexOf(":") + 1);
                        console.log(sender);
                        console.log(message);
                        var monthNames = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
                        var date = new Date();
                        var day = date.getDate();
                        var monthIndex = date.getMonth();
                        var year = date.getFullYear();
                        var hour = date.getHours();
                        var minutes = date.getMinutes();

                        var time = day + " " + monthNames[monthIndex] + " " + year + " " + hour + ":" + minutes;

//                        console.log(day, monthNames[monthIndex], year);

                        if (sender != "Web") {
                            $("#log").html('<li class="message sent"><div class="media"><div class="pull-left user-avatar"><img class="media-object img-circle" src="assets/images/profile-photo.jpg"></div><div class="media-body"><p class="media-heading"><a href="#">John Douey</a> <span class="time">' + time + '</span></p>' + message + '</div></div></li>' + $("#log").html());
                        } else {
                            $("#log").html('<li class="message receive"><div class="media"><div class="pull-left user-avatar"><img class="media-object img-circle" src="assets/images/profile-photo.jpg"></div><div class="media-body"><p class="media-heading"><a href="#">John Douey</a> <span class="time">' + time + '</span></p>' + message + '</div></div></li>' + $("#log").html());

                        }
//                    $("#log").html(message + '<li class="message sent"><div class="media"><div class="pull-left user-avatar"><img class="media-object img-circle" src="assets/images/profile-photo.jpg"></div><div class="media-body"><p class="media-heading"><a href="#">John Douey</a> <span class="time">26.3.2014 18:38</span></p>' + $("#log").html() + '</div></div></li>');
                        flashDiv("#log");
                    });
                };

                ortcClient.onException = function (client, event) {
                    $("#log").html("Error: " + event + "<br>" + $("#log").html());
                }

                // Get your own application key at https://accounts.realtime.co/signup/
                applicationKey = 'D3omir';

                ortcClient.connect(applicationKey, 'fixir_auth_user1');
            }
        }
    });
}

sendMsg = function () {
//    var inputMsg = "Web:" + $("#msgInput").val();
//    ortcClient.send('ChatChannel:1:1', inputMsg);
//    $('#msgInput').val("");
    $.ajax({
        type: 'POST',
        url: 'http://119.81.43.85/chat/send_chat_message',
        crossDomain: true,
        data: {
            "channel_name": "Chat:Workshop:1:354",
            "topic_id": "2",
            "staff_id": "31",
            "token": "786f7aefa6d9299dfd84f61f1cd42dd5d84fa8c549da87bc63f2d95372c664a2c9bf6e09c16da6393cc84d8578431f9a9ab3d33424810e28d06feeec62b30924a60bffd021ae86069a052c150c99547876c225814d471bdb3857eb1c708b7105cb72c6a02699a85cd284cb9f89098b8c263017c9f3272287668a2098c1de80a268a430c24318fb92867b3ce0edff88579f29cd6997558b11ecebe423818eb1d5b8e037a5515d6f0ac4ffc8cbbdd34023ec2440fd6c036511899c9325be80df56941fe1b5685fe4ee7214a2a6ce1a06fae528ddffd10bc8b2a8d725c25207070fc8c033d2695ccbba1b2e6261e0fbe938a3ab8193f410a5d306144716fe991f0ddaa6032653a3d453e7365e791bd908247c664bf3114b3bbad3d30b834c27f4dcc9705bb89fbbdf060b2afc585e969f10b89a99ab1c8a6e37e16c15d5c9ad72341d2c09c68c008623e99bd6f796ef428524714cc12235080cb2cca33d10b8432491a9766babb55eb533a3c14d8c2974f1364fd51f86f61934c91af95c89edbedc0bb605fa8cc03cc52179984daee998939e964d9e92506369e2f792f91c50dc4a9e83b3629e3d9da509790f5e408fa3f282f9dae1b14b35fb846dace1e3d41b015fb81792b6ca0f5d338fbcfe3e27d97d7f0ede817a74f53ce6d7e532690ca8ac203f592b2bd4acec86212128447484436af9deea8e7fa6262159531693a16a7d",
            "message": "0:354:Test from web"
        },
        dataType: 'json',
        success: function (data) {
            console.log(data);
        },
        error: function (data) {
            console.log(data);
        }
    });
}

clearElement = function (e) {
    $(e).text("");
}

flashDiv = function (e) {
    $(e).fadeOut(100).fadeIn(100);
}
