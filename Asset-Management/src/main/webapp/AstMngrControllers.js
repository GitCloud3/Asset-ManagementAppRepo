/*
 *tsithosr
 *
 */

var app = angular.module('AssetManagement', ['ngRoute']);

app.config(function($routeProvider){
	//setup routes
	$routeProvider
		.when('/owners/:ownerId', {
			templateUrl:'owners.html',
			controller:'listOwnersCtrl'
		})
		.when('/contacts/:ownerId', {
			templateUrl:'contacts.html',
			controller:'listContactsCtrl'
		})
		.when('/addcontact', {
			templateUrl:'addcontact.html',
			controller:'addContactCtrl'
		})
		.when('/additem', {
			templateUrl:'additem.html',
			controller:'addItemCtrl'
		})
		.when('/list', {
			templateUrl:'declarations.html',
			controller:'listCtrl'
		})
		.when('/add', {
			templateUrl:'addowner.html',
			controller:'addOwnerCtrl'
		})
		.when('/addContact', {
			templateUrl:'addcontact.html'
		})
		.otherwise({
			redirectTo: '/'
		});

});

app.controller('listCtrl',function($scope, $http)
{
	$http.get('http://localhost:8080/Asset-Management/declaration-ws/getItems').success(function(response)
	{
		$scope.myData = response;
	});

	$scope.deleteRecord = function(row){
		$http.delete('http://localhost:8080/Asset-Management/declaration-ws/removeOwner/'+row.ownerId).success(function(response)
		{
			$scope.myData = response;
		});

		$scope.myData.splice($scope.myData.indexOf(row),1);
	}

});

app.controller('listOwnersCtrl',function($scope, $http, $routeParams)
{
	var ownerId = $routeParams.ownerId;

	$http.get('http://localhost:8080/Asset-Management/declaration-ws/getOwner/'+ownerId+'?clientId=unisa&signature=dQ0sIQxLHNjcMQo1-YTDSi9J8GU%3D').success(function(response)
	{
		$scope.myData = response;
	});

});

app.controller('listContactsCtrl',function($scope, $http, $routeParams)
{
	var ownerId = $routeParams.ownerId;
	$http.get('http://localhost:8080/Asset-Management/declaration-ws/getContact/'+ownerId+'?clientId=unisa&signature=jnnx5vPpB0jhFH6qJKvpAM_c0w4%3D').success(function(response)
	{
		$scope.myData = response;
	});

});

app.controller('addOwnerCtrl',function($scope, $http, $location) {

		$scope.step2 = function(path){
			var data = $scope.owner;
			$http.post('http://localhost:8080/Asset-Management/declaration-ws/createOwner', data);

			$location.path(path);
		}

});

app.controller('addContactCtrl',function($scope, $http, $location) {

		$scope.list = {
			contacts: [{}]
		};

		$scope.newContact = function(){
			$scope.list.contacts.push({});
		}

		$scope.step3 = function(path){

			var data = $scope.list;

			$http.post('http://localhost:8080/Asset-Management/declaration-ws/createContact', data);

			$location.path(path);
		}
});

app.controller('addItemCtrl',function($scope, $http, $location) {

	$scope.step4 = function(){
		var data = $scope.item;
		$http.post('http://localhost:8080/Asset-Management/declaration-ws/createItem', data);
	}

});
