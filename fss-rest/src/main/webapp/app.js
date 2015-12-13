// http://stackoverflow.com/questions/16509755/how-to-use-checkbox-to-filter-results-with-angular

var app = angular.module('plunker', []);

app.controller('MainCtrl', function($scope) {
  
  $scope.customers = [
    { firstName: 'Joe', lastName: 'Smith', address: 'some st.', city: 'City One' },
    { firstName: 'Bob', lastName: 'Mason', address: 'hello st.', city: 'City One' },
    { firstName: 'James', lastName: 'Henry', address: 'goodmorning st.', city: 'City One' },
    
    { firstName: 'Trevor', lastName: 'Senior', address: 'another rd.', city: 'Another City' },
    { firstName: 'Sally', lastName: 'Smith', address: 'rainbow rd.', city: 'Another City' },
     
    { firstName: 'Ben', lastName: 'Hornby', address: 'lonely ave.', city: 'Nonesuch' }
  ];
  
  
  $scope.$watch('customers', function() {
    console.log($scope.customers);
  })
});

// Define our filter
app.filter('selectedCustomerCities', function($filter) {
  return function(customers) {
    var i, len;
    
    // get customers that have been checked
    var checkedCustomers = $filter('filter')(customers, {checked: true});
    
    // Add in a check to see if any customers were selected. If none, return 
    // them all without filters
    if(checkedCustomers.length == 0) {
      return customers;
    }
    
    // get all the unique cities that come from these checked customers
    var cities = {};
    for(i = 0, len = checkedCustomers.length; i < len; ++i) {
      // if this checked customers cities isn't already in the cities object 
      // add it
      if(!cities.hasOwnProperty(checkedCustomers[i].city)) {
        cities[checkedCustomers[i].city] = true;
      }
    }
    
    // Now that we have the cities that come from the checked customers, we can
    //get all customers from those cities and return them
    var ret = [];
    for(i = 0, len = customers.length; i < len; ++i) {
      // If this customer's city exists in the cities object, add it to the 
      // return array
      if(cities[customers[i].city]) {
        ret.push(customers[i]);
      } 
    }
    
    // we have our result!
    return ret;
  };
});