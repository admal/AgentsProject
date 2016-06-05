/**
 * Created by adam on 6/5/16.
 */
app.service("AgentsService",function(){

    var tmpAgents = [
        {
            "type":"Car",
            "currPosition" :{
                "x": 0,
                "y": 0
            },
            "destination":{
                "x": 5,
                "y": 10
            }
        },
        {
            "type":"ChargingStation",
            "currPosition" :{
                "x": 15,
                "y": 20
            },
            "destination":null
        },
        {
            "type":"ChargingStation",
            "currPosition" :{
                "x": 5,
                "y": 6
            },
            "destination":null
        },
        {
            "type":"Parking",
            "currPosition" :{
                "x": 0,
                "y": 0
            },
            "destination":null
        }
    ];

    this.GetAgents = function () {
        return tmpAgents;
    };
});