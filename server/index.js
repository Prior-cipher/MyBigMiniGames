const http = require("http");
//const app = require("express")();

const websocketServer = require("websocket").server
const {Worker,BroadcastChannel} = require("worker_threads");
//const BroadcastChannel = require('broadcast-channel');
const httpServer = http.createServer();
httpServer.listen(7960, () => console.log("Listening.. on 7960"))

let clients = {};

let clientsAuth ={};
const games1 = [];
let game1i=0;

const games2= {};
const games3 = {};
const games4={};
const bc = new BroadcastChannel('test_channel');
let i=0;
const wsServer = new websocketServer({
    "httpServer": httpServer
})

wsServer.on("request", request => 
{
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
                //clients[clientId].connection.send("i say"+ message);
                console.log(message.utf8Data);
                return;
            }
            
        
        const result = JSON.parse(message.utf8Data);
        console.log(result.method);
        //I have received a message from the client
        //a user want to create a new game
       if (result.method === "start") 
        {
            
        }
        if (result.method === "login") 
        {
            return;
        }
        if (result.method === "register") 
        {
            return;
        }
        if (result.method === "auth") 
        {
            return;
        } 
        if (result.method === "iwantgame1") 
        { 
            games1.push(result.clientId);
           
        
            return;
           
        } 
        if (result.method === "startgame1") 
        {
          

        }

        if (result.method === "gamePongInfo") 
        {
          
          bc.postMessage({"clientId":result.clientId,"speed":result.speed});
        }



        //a client want to join
        if (result.method === "join") 
        {

        
        

          
         
            game.clients.forEach(c => {
                clients[c.clientId].connection.send(JSON.stringify(payLoad))
            })
            return;
        }
        //a user plays
        if (result.method === "play") 
        {
            
            
        }
        

    })
    console.log("new connection");
    clients[i] = 
    {
        "connection":  connection
    }
    connection.send(JSON.stringify({
        "method":"start",
        "clientId":i
    }));
   

})

setInterval(raspred,10000);





raspred ()
{
if(games1.length>=2)
{
    const worker = new Worker("./pong.js", {workerData: {clientId1:games1[0],clientId2:games1[1]}});
    games1.shift(2);
    const bc = new BroadcastChannel('game1'+game1i);

    worker.on("message", result => 
      {
        //console.log("isend:" +result)
      wsServer.connections[result.id1].send(JSON.stringify(result))
    });

      worker.on("error", error => {
          console.log(error);
      });

    worker.on("exit", exitCode =>
     {
          console.log(`It exited with code ${exitCode}`);
          })
         
      
    }
}

  



