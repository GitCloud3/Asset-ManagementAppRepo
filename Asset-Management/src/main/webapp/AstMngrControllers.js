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
		.when('/retrieve', {
			templateUrl:'search.html',
			controller:'searchItemCtrl'
		})
		.when('/declarationRecord', {
			templateUrl:'record.html',
			controller:'loadSearchItemCtrl'
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

	$http.get('http://localhost:8080/Asset-Management/declaration-ws/getOwner/'+ownerId).success(function(response)
	{
		$scope.myData = response;
	});

});

app.controller('listContactsCtrl',function($scope, $http, $routeParams)
{
	var ownerId = $routeParams.ownerId;
	$http.get('http://localhost:8080/Asset-Management/declaration-ws/getContact/'+ownerId).success(function(response)
	{
		$scope.myData = response;
	});

});

app.controller('updateDeclarationCtrl',function($scope, $http, $routeParams, $location)
{
	var serialnumber = $routeParams.serialnumber;
	$http.get('http://localhost:8080/Asset-Management/declaration-ws/getAsset/'+serialnumber).success(function(response)
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

		$scope.$watch('owner.ownerId', function(){
			var iden = $scope.owner;
			var myjson

			$http.get('http://localhost:8080/Asset-Management/declaration-ws/getOwnerRecord/'+iden.ownerId).success(function(response)
			{
				myjson = response.ownerId;

				$scope.duplicateValue = angular.equals(iden.ownerId, myjson);

			})

		});

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

app.controller('searchItemCtrl',function($scope, $http, $routeParams, $location)
{

	$scope.$watch('item.serialnumber', function(){
		var iden = $scope.item;
		var myjson;

		$http.get('http://localhost:8080/Asset-Management/declaration-ws/getAsset/'+iden.serialnumber).success(function(response)
		{
			myjson = response.serialnumber;

			$scope.availableRecord = angular.equals(iden.serialnumber, myjson);

		})

	});

	/*$scope.retrieveData = function(path){

		var serial = $scope.item;
		$http.get('http://localhost:8080/Asset-Management/declaration-ws/getAsset/'+serial.serialnumber).success(function(response)
		{
			$scope.myData = response;

			//$location.path(path);
		});
	}
*/
});

app.controller('loadSearchItemCtrl',function($scope, $http, $routeParams, $location)
{

	$scope.retrieveData = function(path){

		var serial = $scope.item;
		$http.get('http://localhost:8080/Asset-Management/declaration-ws/getAsset/'+serial.serialnumber).success(function(response)
		{
			$scope.myData = response;

			//$location.path(path);
		});
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
