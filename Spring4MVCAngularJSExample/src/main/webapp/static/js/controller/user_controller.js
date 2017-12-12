'use strict';

angular.module('myApp').controller('UserController', ['$scope', 'UserService', function($scope, UserService) {
    var self = this;
    self.user={username:'',address:'',email:''};
    self.users=[];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;


    fetchAllUsers();

    function fetchAllUsers(){
        UserService.fetchAllUsers()
            .then(
            function(d) {
                self.users = d;
            },
            function(errResponse){
                console.error('Error while fetching Users');
            }
        );
    }

    function createUser(user){
        UserService.createUser(user)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while creating User');
            }
        );
    }

    function updateUser(user){
        UserService.updateUser(user)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while updating User');
            }
        );
    }

    function deleteUser(username){
        UserService.deleteUser(username)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while deleting User');
            }
        );
    }

    function submit() {
        if(self.user.instanceid === undefined){
            console.log('Saving New User', self.user);
            createUser(self.user);
        }else{
            updateUser(self.user);
            console.log('User updated with UserName ', self.user.username);
        }
        reset();
    }

    function edit(user){
        console.log('UserName to be edited', user.username);
        for(var i = 0; i < self.users.length; i++){
            if(self.users[i].username === user.username) {
                self.user = angular.copy(self.users[i]);
                break;
            }
        }
    }

    function remove(username){
        console.log('UserName to be deleted', username);
        if(self.user.username === username) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteUser(username);
    }


    function reset(){
        self.user={username:'',address:'',email:''};
        $scope.myForm.$setPristine(); //reset Form
    }
    
    
    
    
    
    
    
    var stompClient = null; 
    function setConnected(connected) {
        document.getElementById('connect').disabled = connected;
        document.getElementById('disconnect').disabled = !connected;
        document.getElementById('calculationDiv').style.visibility = connected ? 'visible' : 'hidden';
        document.getElementById('calResponse').innerHTML = '';
    }
    function connect() {
        var socket = new SockJS('/Spring4WebSocket/add');
		stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            setConnected(true);
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/showResult', function(calResult){
            	showResult(JSON.parse(calResult.body).result);
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
        stompClient.send("/calcApp/add", {}, JSON.stringify({ 'num1': num1, 'num2': num2 }));
    }
    function showResult(message) {
        var response = document.getElementById('calResponse');
        var p = document.createElement('p');
        p.style.wordWrap = 'break-word';
        p.appendChild(document.createTextNode(message));
        response.appendChild(p);
    }
    
    
    
    
    
    
    
    
    

}]);
