// http://stackoverflow.com/questions/16509755/how-to-use-checkbox-to-filter-results-with-angular

//var app = angular.module('plunker', []);
// var horseApp = angular.module('horseApp', []);

/*app.controller('MainCtrl', function($scope) {*/
  
  /*$scope.customers = [
    { firstName: 'Joe', lastName: 'Smith', address: 'some st.', city: 'City One' },
    { firstName: 'Bob', lastName: 'Mason', address: 'hello st.', city: 'City One' },
    { firstName: 'James', lastName: 'Henry', address: 'goodmorning st.', city: 'City One' },
    
    { firstName: 'Trevor', lastName: 'Senior', address: 'another rd.', city: 'Another City' },
    { firstName: 'Sally', lastName: 'Smith', address: 'rainbow rd.', city: 'Another City' },
     
    { firstName: 'Ben', lastName: 'Hornby', address: 'lonely ave.', city: 'Nonesuch' }
  ];*/
	
//	horseApp.controller('HorseCtrl', function ($scope, $http){
var HorseCtrl = (function ($scope, $http){
	var onLoadComplete = function(response){
			$scope.horses = response.data;
	};	
	var onError = function(reason){
		$scope.error = "Could not fetch data";	
	};
        $http.get('http://localhost:8080/greeting1').then(onLoadComplete, onError);
         /* $scope.horses = data;*/
        });
     /*   $scope.$watch('horses', function() {
            console.log($scope.horses);
        
      });*/
  
  
 /* $scope.$watch('customers', function() {
    console.log($scope.customers);
  })
});*/


 // });


// Define our filter
horseApp.filter('selectedHorseIds', function($filter) {
  return function(horses) {
	  
    var i, len, checkedHorses;
    
 
    // get customers that have been checked
    checkedHorses = $filter('filter')(horses, {checked: true});
    
    // Add in a check to see if any customers were selected. If none, return 
    // them all without filters
    if(checkedHorses.length === -1) {
      return horses;
    }
    
    // get all the unique cities that come from these checked customers
    var ids = {};
    for(i = 0, len = checkedHorses.length; i < len; ++i) {
      // if this checked customers cities isn't already in the cities object 
      // add it
      if(!ids.hasOwnProperty(checkedHorses[i].id)) {
        ids[checkedHorses[i].id] = true;
      }
    }
    
    // Now that we have the cities that come from the checked customers, we can
    //get all customers from those cities and return them
    var ret = [];
    for(i = 0, len = horses.length; i < len; ++i) {
      // If this customer's city exists in the cities object, add it to the 
      // return array
      if(ids[horses[i].id]) {
        ret.push(horses[i]);
      } 
    }
    
    // we have our result!
    return ret;
  };
});