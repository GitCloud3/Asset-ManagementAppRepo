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
		.when('/updateDeclaration/:serialnumber', {
			templateUrl:'updateDeclarations.html',
			controller:'updateDeclarationCtrl'
		})
		.when('/updateContact/:contactId', {
			templateUrl:'updateContact.html',
			controller:'updateContactCtrl'
		})
		.when('/updateOwner/:ownerId', {
			templateUrl:'updateOwner.html',
			controller:'updateOwnerCtrl'
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

app.controller('updateDeclarationCtrl',function($scope, $http, $routeParams, $location)
{
	var serialnumber = $routeParams.serialnumber;
	$http.get('http://localhost:8080/Asset-Management/declaration-ws/getAsset?serial='+serialnumber).success(function(response)
	{
		$scope.myData = response;
	});

	$scope.updateItem = function(path){
		var data = $scope.myData;
		$http.put('http://localhost:8080/Asset-Management/declaration-ws/updateItem', data);

		$location.path(path);
	}

});

app.controller('updateOwnerCtrl',function($scope, $http, $routeParams, $location)
{
	var ownerId = $routeParams.ownerId;
	$http.get('http://localhost:8080/Asset-Management/declaration-ws/getOwnerRecord/'+ownerId).success(function(response)
	{
		$scope.myData = response;
	});

	$scope.updateOwner = function(path){

		var data = $scope.myData;
		$http.put('http://localhost:8080/Asset-Management/declaration-ws/updateOwner', data);

		$location.path(path);

	}
});

app.controller('updateContactCtrl',function($scope, $http, $routeParams, $location)
{
	var contactId = $routeParams.contactId;
	$http.get('http://localhost:8080/Asset-Management/declaration-ws/getContactById/'+contactId).success(function(response)
	{
		$scope.myData = response;
	});

	$scope.updateContact = function(path){
		var data = $scope.myData;
		$http.put('http://localhost:8080/Asset-Management/declaration-ws/updateContact', data);

		$location.path(path);
	}

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

	$scope.list = {
		items: [{}]
	};

	$scope.newItem = function(){
		$scope.list.items.push({});
	}

	$scope.step4 = function(){

		var data = $scope.list;

		$http.post('http://localhost:8080/Asset-Management/declaration-ws/createItem', data);
	}
});

app.directive('confirmationNeeded', ['$location',function(){
	return{
		priority: 1,
		terminal: true,
		link: function(scope, element, attr){
			var msg = attr.confirmationNeeded || "Please note that you are attempting to delete the record!";
			var clickAction = attr.ngClick;
			element.bind('click', function(){
				if(window.confirm(msg)){
					scope.$eval(clickAction)
					location.path(path);
				}
			});
		}
	};
}]);
