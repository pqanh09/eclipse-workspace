'use strict';

var stompClient = null; 
function setConnected(connected) {
    document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;
    document.getElementById('calculationDiv').style.visibility = connected ? 'visible' : 'hidden';
    document.getElementById('calResponse').innerHTML = '';
}
function connect() {
    var socket = new SockJS('/Spring4MVCAngularJSExample/register');
	stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/aaa/showResult', function(calResult){
        	showResult(JSON.parse(calResult.body).result);
        });
        stompClient.subscribe('/topic/greetings', function(calResult){
        	showGreetings(calResult.body);
        });
    });
}
function disconnect() {
    stompClient.disconnect();
    setConnected(false);
    console.log("Disconnected");
}
function sendNum() {
    var num1 = document.getElementById('num1').value;
    var num2 = document.getElementById('num2').value;
//    stompClient.send("/calcApp/add");
     stompClient.send("/wsrq/sum", {}, JSON.stringify({ 'num1': num1, 'num2': num2 }));
}
function showResult(message) {
    var response = document.getElementById('calResponse');
    var p = document.createElement('p');
    p.style.wordWrap = 'break-word';
    p.appendChild(document.createTextNode(message));
    response.appendChild(p);
}
function showGreetings(message) {
    var response = document.getElementById('greetingResponse');
    var p = document.createElement('p');
    p.style.wordWrap = 'break-word';
    p.appendChild(document.createTextNode(message));
    response.appendChild(p);
}