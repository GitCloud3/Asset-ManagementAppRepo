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
		$scope.myData.splice($scope.myData.indexOf(row),1);
	}
	$scope.viewOwner = function(row){
		//$scope.myData.splice($scope.myData.indexOf(row),1);
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

  /*  $scope.sendPost = function() {
			var data = $.param({
				json: JSON.stringify({ownerId:$scope.title,title:$scope.title,name:$scope.name,surname:$scope.surname,gender:$scope.gender,
					race:$scope.race,country:$scope.country,province:$scope.province,city:$cope.city
					})
        });
        $http.post('http://localhost:8080/Asset-Management/declaration-ws/createOwner', data).success(function(data, status) {
            $scope.hello = data;
        })
    }*/
		$scope.step2 = function(path){
			$location.path(path);
		}

});

app.controller('addContactCtrl',function($scope, $http, $location) {


		$scope.step3 = function(path){
			$location.path(path);
		}
});

app.controller('addItemCtrl',function($scope, $http, $location) {

	alert('final stage');
});
