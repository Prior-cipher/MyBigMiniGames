const http = require("http");
const app = require("express")();

const websocketServer = require("websocket").server
const httpServer = http.createServer();
httpServer.listen(7960, () => console.log("Listening.. on 9090"))

const clients = {};
const games = {};

const wsServer = new websocketServer({
    "httpServer": httpServer
})
    
wsServer.on("request", request => {
    //connect
    const connection = request.accept(null, request.origin);
 
    connection.on("open", () => console.log("opened!"))
    connection.on("close", () => console.log("closed!"))
    connection.on("message", message => 
    {

      
            try {
                JSON.parse(message.utf8Data);
            } catch (e) 
            {
                console.log(message.utf8Data);
                return;
            }
            
        
        const result = JSON.parse(message.utf8Data)
        //I have received a message from the client
        //a user want to create a new game
        if (result.method === "create") 
        {
           
        }

        //a client want to join
        if (result.method === "join") 
        {

        
        

          
         
            game.clients.forEach(c => {
                clients[c.clientId].connection.send(JSON.stringify(payLoad))
            })
        }
        //a user plays
        if (result.method === "play") 
        {
            
            
        }

    })

    //generate a new clientId
    const clientId = guid();
    clients[clientId] = {
        "connection":  connection
    }

    const payLoad = {
        "method": "connect",
        "clientId": clientId
    }
    //send back the client connect
    connection.send(JSON.stringify(payLoad))

})






function S4() {
    return (((1+Math.random())*0x10000)|0).toString(16).substring(1); 
}
 

const guid = () => (S4() + S4() + "-" + S4() + "-4" + S4().substr(0,3) + "-" + S4() + "-" + S4() + S4() + S4()).toLowerCase();
 