/**
 * Created by adam on 6/5/16.
 */
app.service("AgentsService",function(){

    var tmpAgents = [
        {
            "type":"Car",
            "currPosition" :{
                "x": 52.26881,
                "y": 21.04666
            },
            "destination":{
                "x": 52.22875,
                "y": 21.06319
            }
        },
        {
            "type":"ChargingStation",
            "currPosition" :{
                "x": 52.25469,
                "y": 21.03508
            },
            "destination":null
        },
        {
            "type":"ChargingStation",
            "currPosition" :{
                "x": 52.23917,
                "y": 21.05124
            },
            "destination":null
        },
        {
            "type":"Parking",
            "currPosition" :{
                "x": 52.25491,
                "y": 21.03487
            },
            "destination":null
        }
    ];

    this.GetAgents = function () {
        return tmpAgents;
    };
});