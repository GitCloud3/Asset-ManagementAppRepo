/*
 *tsithosr
 *
 */

var app = angular.module('AssetManagement', ['ngRoute']);

app.config(function($routeProvider){
	//setup routes
	$routeProvider
		.when('/test', {
			templateUrl:'test.html'
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
