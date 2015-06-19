/*
 *tsithosr
 *
 */

var app = angular.module('AssetManagement', ['ngRoute']);

app.config(function($routeProvider){
	//setup routes
	$routeProvider
		.when('/owners', {
			templateUrl:'owners.html',
			controller:'listOwnersCtrl'
		})
		.when('/contacts', {
			templateUrl:'contacts.html',
			controller:'listContactsCtrl'
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

app.controller('listOwnersCtrl',function($scope, $http)
{

		alert("killing");

	$http.get('http://localhost:8080/Asset-Management/declaration-ws/getOwners?clientId=unisa&signature=v6SKoryUPxWONw84oi1F6ekXsXE%3D').success(function(response)
	{
		$scope.myData = response;
	});

});

app.controller('listContactsCtrl',function($scope, $http)
{

	$http.get('http://localhost:8080/Asset-Management/declaration-ws/getContacts?clientId=unisa&signature=jnnx5vPpB0jhFH6qJKvpAM_c0w4%3D').success(function(response)
	{
		$scope.myData = response;
	});

});

app.controller('addOwnerCtrl',function($scope, $http) {

    $scope.sendPost = function() {
			var data = $.param({
				json: JSON.stringify({ownerId:$scope.title,title:$scope.title,name:$scope.name,surname:$scope.surname,gender:$scope.gender,
					race:$scope.race,country:$scope.country,province:$scope.province,city:$cope.city
					})
        });
        $http.post('http://localhost:8080/Asset-Management/declaration-ws/createOwner', data).success(function(data, status) {
            $scope.hello = data;
        })
    }

});
