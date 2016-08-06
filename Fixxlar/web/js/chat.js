
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
    var inputMsg = "Web:" + $("#msgInput").val();
    ortcClient.send('ChatChannel:1:1', inputMsg);
    $('#msgInput').val("");
}

clearElement = function (e) {
    $(e).text("");
}

flashDiv = function (e) {
    $(e).fadeOut(100).fadeIn(100);
}
