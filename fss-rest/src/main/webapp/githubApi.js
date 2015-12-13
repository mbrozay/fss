var MainController = function($scope, $http){
	var onUserComplete = function(response){
		$scope.horses = response.data;
	};
	
	var onError = function(reason){
		$scope.error = "could not fetch data";
	};
	
	$http.get("https://api.github.com/users/robconery").then(onUserComplete, onError);
};


